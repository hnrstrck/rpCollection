import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalInputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.gpio.digital.PullResistance;


/**
 * Klasse fuer den Anschluss eines Ultraschallsenosrs (HC-SR04), mit dem die Entfernung gemessen werden kann
 *
 * @author Johannes Pieper
 */
public class RPSonicSensor {
    private long rejectionStart = 1000;
    private long rejectionTime = 20_000_000; //ns

    private DigitalOutput trigger;
    private DigitalInput echo;
    private int boolInitialisierungErfolgt = -2;

    /**
    * Erstellt ein neues Objekt der Klasse RPSonicSensor, ohne die beiden Pins anzugeben.
    */
    public RPSonicSensor() {
    }

    /**
    * Erstellt ein neues Objekt der Klasse RPSonicSensor
    * @param echo Pin für den Echo-Anschluss
    * @param trig Pin für den Trigger-Anschluss
    */
    public RPSonicSensor(int echo, int trig){
        this.setEchoPin(echo);
        this.setTriggerPin(trig);
    }

    /**
    * Setzt den Pin für den Trigger
    * @param pin Pin für den Trigger
    */
    public void setTriggerPin(int pin) {
        if (this.trigger == null) {
            Context pi4j = RPEnvironment.getContext();
            DigitalOutputConfigBuilder outputConfig = RPEnvironment.getOutputConfig();
            this.trigger = pi4j.create(outputConfig
                .address(pin)
                .id("pin" + pin)
                .initial(DigitalState.HIGH)
                .shutdown(DigitalState.LOW)
            );
            System.out.println("Trigger-Pin gesetzt:");
            boolInitialisierungErfolgt++;
        } else {
            System.out.println("Trigger-Pin bereits gesetzt:");
        }
    }

    /**
    * Setzt den Pin für die Echo Rückgabe
    * @param pin Pin für Echo
    */
    public void setEchoPin(int pin) {
        if (this.echo == null) {
            Context pi4j = RPEnvironment.getContext();
            DigitalInputConfigBuilder inputConfig = RPEnvironment.getInputConfig();
            this.echo = pi4j.create(inputConfig
                .address(pin)
                .id("pin" + pin)
                .pull(PullResistance.PULL_DOWN)
            );
            System.out.println("Echo-Pin gesetzt:");
            boolInitialisierungErfolgt++;
        } else {
            System.out.println("Echo-Pin bereits gesetzt:");
        }
    }

    /**
    * Liefert die gemessene Distanz in Millimetern. Kommt es zu Fehlern, so ist die Rückgabe -1
    * @return Distanz in Millimetern oder -1 bei einem Fehler
    */
    public int getDistance() { //in Millimeter
        if (boolInitialisierungErfolgt == 0){
            int distance = 0;
            long startTime;
            long endTime;
            long rejectionStart = 0;
            long rejectionTime = 0;

            // Starten des 10 Microsekunden langen Signals
            trigger.off();
            waitMicroseconds(2);
            trigger.on();
            waitMicroseconds(10);
            trigger.off();

            //distance calculation
            while (echo.isLow()) {
                waitNanoseconds(1);
                rejectionStart++;
                if (rejectionStart == this.rejectionStart) {
                    return -1; // Fehler
                }
            }
            startTime = System.nanoTime();

            while (echo.isHigh()) { //wait Warten bis das Signal wieder runter geht
                waitNanoseconds(1); //
                rejectionTime++;
                if (rejectionTime==this.rejectionTime) {
                    return -1; // Außerhalb der Reichweite
                }
            }
            endTime = System.nanoTime();

            // Wert aus 2 facher Weg * Umrechnung in Sekunden / Schallgeschwindigkeit mit 343 m/s
            distance = (int)((endTime-startTime) / 5831); //distance in mm
            return distance;
        } else {
            return -1;
        }
    }

    private static void waitMicroseconds(long micros){
        long waitUntil = System.nanoTime() + (micros * 1000);
        while (waitUntil > System.nanoTime()) {
        }
    }

    private static void waitNanoseconds(long nanos) {
        long waitUntil = System.nanoTime() + nanos;
        while (waitUntil > System.nanoTime()) {
        }
    }

    /**
    * Test-Methode
    */
    public static void main(String[] args) throws Exception{
         RPSonicSensor sonic = new RPSonicSensor(
             24,//Echo Pin
             18//Trigger Pin
         );
         for (int i = 0; i < 10; i++) {
              System.out.println("Abstand " + sonic.getDistance() + " mm");
               Thread.sleep(1000);
         }
         Helfer.herunterfahren();
    }
}
