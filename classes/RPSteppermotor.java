import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfig;
import com.pi4j.io.gpio.digital.DigitalOutputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalState;

/**
 * Klasse zum Anschluss eines Schrittmotors am Raspberry Pi.
 * Dem Motor gibt man die Anzahl der Schritte an, die er sich drehen muss.
 *
 * @author Johannes Pieper
 * @version 2.0
 */
public final class RPSteppermotor {

    private DigitalOutput[] pins = new DigitalOutput[4];

    private boolean boolInitialisierungErfolgt = false;
    private int[] intPins = new int[4];

    private int stepSleep = 2;
    private int stepCount = 4096;
    private DigitalState[][] stepSequence =
        {{DigitalState.HIGH, DigitalState.LOW, DigitalState.LOW, DigitalState.HIGH},
        {DigitalState.HIGH, DigitalState.LOW, DigitalState.LOW, DigitalState.LOW},
        {DigitalState.HIGH, DigitalState.HIGH, DigitalState.LOW, DigitalState.LOW},
        {DigitalState.LOW, DigitalState.HIGH, DigitalState.LOW, DigitalState.LOW},
        {DigitalState.LOW, DigitalState.HIGH, DigitalState.HIGH, DigitalState.LOW},
        {DigitalState.LOW, DigitalState.LOW, DigitalState.HIGH, DigitalState.LOW},
        {DigitalState.LOW, DigitalState.LOW, DigitalState.HIGH, DigitalState.HIGH},
        {DigitalState.LOW, DigitalState.LOW, DigitalState.LOW, DigitalState.HIGH}};
    private int motorStand = 0;

    /**
    * Erstellt ein Objekt der Klasse RPSteppermotor, ohne die Pinne anzugeben.
    */
    public RPSteppermotor() {}

    /**
    * Erstellt ein Objekt der Klasse RPSteppermotor.
    * @param pin1 Der Pin fuer den ersten Anschluss.
    * @param pin1 Der Pin fuer den zweiten Anschluss.
    * @param pin1 Der Pin fuer den dritten Anschluss.
    * @param pin1 Der Pin fuer den vierten Anschluss.
    */
    public RPSteppermotor(int pin1, int pin2, int pin3, int pin4) {
        this.setPins(pin1, pin2, pin3, pin4);
    }

    /**
    * Setzt die Pinne fuer den Motor.
    * @param pin1 Der Pin fuer den ersten Anschluss.
    * @param pin1 Der Pin fuer den zweiten Anschluss.
    * @param pin1 Der Pin fuer den dritten Anschluss.
    * @param pin1 Der Pin fuer den vierten Anschluss.
    */
    public void setPins(int pin1, int pin2, int pin3, int pin4){
        System.out.println("Setze Pins:");

        setPin(pin1, 0);
        setPin(pin2, 1);
        setPin(pin3, 2);
        setPin(pin4, 3);
        boolInitialisierungErfolgt = true;
    }

    /**
    * Setzt einen Pin
    * @param pPin Der Pin
    * @param nummer Nummer des Pins von 0 bis 3
    */
    private void setPin(int pPin, int nummer) {
        if (this.pins[nummer]== null) {
            System.out.println("Output-Pin gesetzt fuer " + nummer);
            Context pi4j = RPEnvironment.getContext();
            DigitalOutputConfigBuilder outputConfig = RPEnvironment.getOutputConfig();
            this.pins[nummer] = pi4j.create(outputConfig
                .address(pPin)
                .id("pin" + pPin)
            );
            System.out.println("Pin " + pPin + " gesetzt");
            intPins[nummer] = pPin;
        } else {
            System.out.println("Output-Pin für " + nummer + " bereits gesetzt:");
        }
    }


    /**
    * Dreht den Motor in Vorwärtsrichtung
    * Die Anzahl der Schritte mit angegeben werden
    * @param schritte Anzahl der Schritte
    */
    public void forward(int schritte) {
        if (boolInitialisierungErfolgt == true){
            for (int i = 0; i < schritte; i++) {
                this.motorStand = (this.motorStand + 1) % 8;
                for(int p= 0; p < 4; p++) {
                    this.pins[p].state(this.stepSequence[this.motorStand][p]);
                }
                Helfer.warteMS(this.stepSleep);
            }
            this.stop();
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben");
        }
    }

    /**
    * Dreht den Motor in Rückwärtsrichtung
    * Die Anzahl der Schritte mit angegeben werden
    * @param schritte Anzahl der Schritte
    */
    public void backward(int schritte) {
        if (boolInitialisierungErfolgt == true){
            for (int i = 0; i < schritte; i++) {
                this.motorStand = (this.motorStand - 1);
                if (this.motorStand == -1) {
                    this.motorStand = 7;
                }
                for(int p= 0; p < 4; p++) {
                    this.pins[p].state(this.stepSequence[this.motorStand][p]);
                }
                Helfer.warteMS(this.stepSleep);
            }
            this.stop();
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben");
        }
    }

    /**
    * Stopt die Versorgung der Pins mit Strom
    */
    private void stop() {
        if (boolInitialisierungErfolgt == true){
            for(int p= 0; p < 4; p++) {
                this.pins[p].state(DigitalState.LOW);
            }
        }
    }


    /**
    * Test-Methode
    */
    public static void main(String[] args) {
        RPSteppermotor motor = new RPSteppermotor(26, 22, 27, 17);
        motor.forward(3000);
        Helfer.warte(2);
        motor.backward(3000);
    }
}
