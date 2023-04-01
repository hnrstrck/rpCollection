import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfig;
import com.pi4j.io.gpio.digital.DigitalOutputConfigBuilder;
import com.pi4j.io.pwm.Pwm;
import com.pi4j.io.pwm.PwmConfig;
import com.pi4j.io.pwm.PwmConfigBuilder;
import com.pi4j.io.pwm.PwmType;


/**
 * Klasse zum Anschluss eines Motors am Raspberry Pi. Der Motor kann auf unbestimmte Zeit laufen oder nur fuer ein paar Sekunden. Zudem kann die Laufrichtung geaendert werden (waehrend der Motor laeuft).
 *
 * @author Heiner Stroick, Johannes Pieper
 * @version 2.0
 */
public final class RPMotor {

    private Pwm pinEnable = null;
    private DigitalOutput pinRichtung1 = null;
    private DigitalOutput pinRichtung2 = null;

    private boolean boolInitialisierungErfolgt;
    private int[] intPins;

    /*
     * Damit es nur einen (!) Motor-Thead gibt.
     */
    private Thread threadEndlosMotorlaufen;

    private boolean laeuftGerade = false;

    /**
    * Erstellt ein Objekt der Klasse RPMotor, ohne die Pinne anzugeben.
    */
    RPMotor() {
        intPins = new int[3];
        boolInitialisierungErfolgt = false;
    }

    /**
    * Erstellt ein Objekt der Klasse RPMotor.
    * @param pinEnable Der Pin fuer Enable.
    * @param pinRichtung1 Der Pin fuer Richtung 1.
    * @param pinRichtung2 Der Pin fuer Richtung2.
    */
    RPMotor(int pinEnable, int pinRichtung1, int pinRichtung2) {
        intPins = new int[3];
        boolInitialisierungErfolgt = false;

        this.setPins(pinEnable, pinRichtung1, pinRichtung2);
    }

    /**
    * Setzt die Pinne fuer den Motor.
    * @param pinEnable Der Pin fuer Enable.
    * @param pinRichtung1 Der Pin fuer Richtung 1.
    * @param pinRichtung2 Der Pin fuer Richtung2.
    */
    public void setPins(int pinEnable, int pinRichtung1, int pinRichtung2){
        System.out.println("Setze Pins:");

        setPinEnable(pinEnable);
        setPinRichtung1(pinRichtung1);
        setPinRichtung2(pinRichtung2);
    }

    /**
    * Setzt den Pin fuer Enable.
    * @param pPin Der Pin fuer Enable.
    */
    public void setPinEnable(int pPin) {
        if (this.pinEnable == null) {
            System.out.println("Output-Pin gesetzt fuer Enable (EN):");
            Context pi4j = RPEnvironment.getContext();
            PwmConfigBuilder pwmConfigBuilder = RPEnvironment.getPwmConfig();
            this.pinEnable = pi4j.create(pwmConfigBuilder
                .address(pPin)
                .id("pin" + pPin)
            );
            System.out.println("Pin " + pPin + " gesetzt");
            intPins[0] = pPin;
            boolInitialisierungErfolgt = true;
        } else {
            System.out.println("Output-Pin Enable bereits gesetzt:");
        }
    }

    /**
    * Setzt den Pin fuer Richtung 1.
    * @param pPin Der Pin fuer Richtung 1.
    */
    public void setPinRichtung1(int pPin) {
        if (this.pinRichtung1 == null) {
            System.out.println("Output-Pin gesetzt fuer Input 1 (Richtung 1):");
            Context pi4j = RPEnvironment.getContext();
            DigitalOutputConfigBuilder outputConfig = RPEnvironment.getOutputConfig();
            this.pinRichtung1 = pi4j.create(outputConfig
                .address(pPin)
                .id("pin" + pPin)
            );
            System.out.println("Pin " + pPin + " gesetzt");
            intPins[1] = pPin;
            boolInitialisierungErfolgt = true;
        } else {
            System.out.println("Output-Pin bereits gesetzt:");
        }
    }

    /**
    * Setzt den Pin fuer Richtung2.
    * @param pPin Der Pin fuer Richtung2.
    */
    public void setPinRichtung2(int pPin) {
        if (this.pinRichtung2 == null) {
            System.out.println("Output-Pin gesetzt fuer Input 2 (Richtung 2):");
            Context pi4j = RPEnvironment.getContext();
            DigitalOutputConfigBuilder outputConfig = RPEnvironment.getOutputConfig();
            this.pinRichtung2 = pi4j.create(outputConfig
                .address(pPin)
                .id("pin" + pPin)
            );
            System.out.println("Pin " + pPin + " gesetzt");
            intPins[2] = pPin;
            boolInitialisierungErfolgt = true;
        } else {
            System.out.println("Output-Pin bereits gesetzt:");
        }
    }

    /**
    * Gibt den Pin des Motors fuer Enable zurueck.
    * @return Pin des Motors fuer Enable.
    */
    public int gibPinEnable(){
        return intPins[0];
    }

    /**
    * Gibt den Pin des Motors fuer Richtung 1 zurueck.
    * @return Pin des Motors fuer Richtung 1.
    */
    public int gibPinRichtung1(){
        return intPins[1];
    }

    /**
    * Gibt den Pin des Motors fuer Richtung2 zurueck.
    * @return Pin des Motors fuer Richtung2.
    */
    public int gibPinRichtung2(){
        return intPins[2];
    }

    /**
    * Startet den Motor (auf unbestimmte Zeit).
    * @param pGeschwindigkeit Geschwindigkeit des Motors in Prozent fuer die Pulsweitenmodulation
    */
    private void starteMotorGeschwindigkeit(int pGeschwindigkeit) {
        if (boolInitialisierungErfolgt == true){

            threadEndlosMotorlaufen = new Thread(){

                public void run() {
                    try {
                        pinEnable.on(pGeschwindigkeit);
                        Thread.sleep(15*200);
                    } catch (InterruptedException e) {
                        System.out.println("Motor beendet");
                        Thread.currentThread().interrupt();
                        motorstop();
                        return;
                    }
                }
            };

            threadEndlosMotorlaufen.start();

        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
        }
    }

    /**
    * Startet den Motor (auf unbestimmte Zeit).
    * @param pIntervall Zeitspanne (in Sekunden), die der Motor an sein soll.
    * @param pGeschwindigkeit Geschwindigkeit des Motors in Prozent fuer die Pulsweitenmodulation (ungueltige Prozentangaben werden ignoriert).
    */
    private void starteMotorZeitintervallGeschwindigkeit(int pIntervall, int pGeschwindigkeit) {
        if (boolInitialisierungErfolgt == true){

            threadEndlosMotorlaufen = new Thread(){

                public void run() {
                    try {
                        pinEnable.on(pGeschwindigkeit);
                        Thread.sleep(pIntervall * 1000);
                        Thread.sleep(20);
                        throw new InterruptedException();
                    } catch (InterruptedException e) {
                        System.out.println("Motor beendet");
                        Thread.currentThread().interrupt();
                        motorstop();
                        return;
                    }
                }
            };

            threadEndlosMotorlaufen.start();

        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
        }
    }

    /**
    * Startet den Motor (auf unbestimmte Zeit) mit voller Geschwindigkeit (100%).
    */
    public void start() {
        if (boolInitialisierungErfolgt == true){

            if(!laeuftGerade){

                if (pinRichtung1.isOn() || pinRichtung2.isOn()){
                    starteMotorGeschwindigkeit(100);
                    laeuftGerade = true;
                    System.out.println("Motor gestartet");
                } else {
                    System.out.println("Zuerst Laufrichtung angeben");
                }
            } else {
                System.out.println("Motor laeuft schon");
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
        }
    }

    /**
    * Startet den Motor (auf unbestimmte Zeit) mit voller Geschwindigkeit (100%).
    * @param pGeschwindigkeit Geschwindigkeit des Motors in Prozent (ungueltige Prozentangaben werden ignoriert).
    */
    public void start(int pGeschwindigkeit) {
        if (boolInitialisierungErfolgt == true){

            if(!laeuftGerade){
                if (pinRichtung1.isOn() || pinRichtung2.isOn()){
                    starteMotorGeschwindigkeit(pGeschwindigkeit);
                    laeuftGerade = true;
                    System.out.println("Motor gestartet");
                } else {
                    System.out.println("Zuerst Laufrichtung angeben");
                }
            } else {
                System.out.println("Motor laeuft schon");
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
        }
    }

    /**
    * Startet den Motor mit voller Geschwindigkeit (100%) fuer die angegebene Zeitspanne (in Sekunden).
    * @param pSekunden Zeitspanne (in Sekunden), die der Motor an sein soll.
    */
    public void startZeitintervall(int pSekunden) {
        if (boolInitialisierungErfolgt == true){

            if(!laeuftGerade){
                if (pinRichtung1.isOn() || pinRichtung2.isOn()){
                    starteMotorZeitintervallGeschwindigkeit(pSekunden, 100);
                    laeuftGerade = true;
                    System.out.println("Motor gestartet");
                } else {
                    System.out.println("Zuerst Laufrichtung angeben");
                }
            } else {
                System.out.println("Motor laeuft schon");
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
        }
    }

    /**
    * Startet den Motor in der angegebenen Geschwindigkeit (in Prozent) fuer die angegebene Zeitspanne (in Sekunden). Ungueltige Prozentangaben werden ignoriert.
    * @param pSekunden Zeitspanne (in Sekunden), die der Motor an sein soll.
    * @param pGeschwindigkeit Geschwindigkeit des Motors in Prozent (ungueltige Prozentangaben werden ignoriert).
    */
    public void startZeitintervall(int pSekunden, int pGeschwindigkeit) {
        if (boolInitialisierungErfolgt == true){

            if(!laeuftGerade){
                if (pinRichtung1.isOn() || pinRichtung2.isOn()){
                    starteMotorZeitintervallGeschwindigkeit(pSekunden, pGeschwindigkeit);
                    laeuftGerade = true;
                    System.out.println("Motor gestartet");
                } else {
                    System.out.println("Zuerst Laufrichtung angeben");
                }
            } else {
                System.out.println("Motor laeuft schon");
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
        }
    }

    /**
    * Stoppt den Motor.
    */
    public void stop(){
        this.motorstop();
    }

    /**
    * Stoppt den Motor.
    * Extra Methoden ist notwenig, da innerhalb des Threads nicht this.stop() aufgerufen werden kann (stop() ist schon eine eigene Methode im Thread).
    */
    private void motorstop() {
        if (boolInitialisierungErfolgt == true){
            try{
                threadEndlosMotorlaufen.interrupt();
                pinEnable.off();

                laeuftGerade = false;
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
        }
    }

    /**
    * Setzt die Laufrichtung des Motors.
    * @param pRichtung Die Richtung des Motors (0 = links, 1 = rechts).
    */
    public void setzeLaufrichtung(int pRichtung) {
        if (boolInitialisierungErfolgt == true){
            if (pRichtung == 0 || pRichtung == 1){
                if (pRichtung == 0){
                    try{
                        this.pinRichtung2.off();
                        this.pinRichtung1.on();
                    } catch (NullPointerException f){
                        System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                    }
                } else {
                    try{
                        this.pinRichtung1.off();
                        this.pinRichtung2.on();
                    } catch (NullPointerException f){
                        System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                    }
                }
            } else {
                System.out.println("Angabe fuer die Richtung muss 0 oder 1 sein.");
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
        }
    }

    /**
    * Wechselt die Laufrichtung des Motors.
    */
    public void wechselLaufrichtung() {
        if (boolInitialisierungErfolgt == true){
            if (pinRichtung1.isOn() || pinRichtung2.isOn()){
                if (pinRichtung1.isOn()){
                    try{
                        this.pinRichtung1.off();
                        this.pinRichtung2.on();
                        System.out.println("Laufrichtung geaendert");
                    } catch (NullPointerException f){
                        System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                    }
                } else {
                    try{
                        this.pinRichtung2.off();
                        this.pinRichtung1.on();

                        System.out.println("Laufrichtung geaendert");
                    } catch (NullPointerException f){
                        System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                    }
                }
            } else {
                System.out.println("Es wurde noch keine Richtung festgelegt.");
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
        }
    }

    /**
    * Ueberprueft, ob der Motor an ist.
    * @return true oder false, je nach dem, ob der Motor an ist (true = Motor an, false = Motor aus).
    */
    public boolean istAn() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (pinEnable.isOn()) {
                    System.out.println("Ja, Motor ist angeschaltet");
                    return true;
                } else {
                    System.out.println("Nein, Motor ist ausgeschaltet");
                    return false;
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                return false;
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
            return false;
        }
    }

    /**
    * Ueberprueft, ob der Motor aus ist.
    * @return true oder false, je nach dem, ob der Motor aus ist (true = Motor aus, false = Motor an).
    */
    public boolean istAus() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (pinEnable.isOn()) {
                    System.out.println("Nein, Motor ist angeschaltet");
                    return false;
                } else {
                    System.out.println("Ja, Motor ist ausgeschaltet");
                    return true;
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                return false;
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
            return false;
        }
    }

    /**
    * Ueberprueft, ob der Motor gerade nach links laeuft.
    * @return true oder false, je nach dem, ob der Motor gerade nach links laeuft (true = Motor laeuft nach links, false = Motor laeuft nicht oder nicht nach links).
    */
    public boolean hatLaufrichtungLinks() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (pinRichtung1.isOn()) {
                    System.out.println("Ja, Motor ist auf Links eingestellts");
                    return true;
                } else {
                    System.out.println("Nein, Motor ist auf Rechts eingestellt (oder hat noch keine Richtung)");
                    return false;
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                return false;
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
            return false;
        }
    }

    /**
    * Ueberprueft, ob der Motor gerade nach rechts laeuft.
    * @return true oder false, je nach dem, ob der Motor gerade nach rechts laeuft (true = Motor laeuft nach rechts, false = Motor laeuft nicht oder nicht nach rechts).
    */
    public boolean hatLaufrichtungRechts() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (pinRichtung2.isOff()) {
                    System.out.println("Nein, Motor ist auf Links eingestellt (oder hat noch keine Richtung)");
                    return false;
                } else {
                    System.out.println("Ja, Motor ist auf Rechts eingestellt");
                    return true;
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                return false;
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (EN 1, Richtung 1, Richtung 2)");
            return false;
        }
    }

    public static void main(String[] args) {
        RPMotor motor = new RPMotor(23, 24, 25);
        motor.setzeLaufrichtung(1);
        motor.start(100);
        Helfer.warte(1);
        motor.stop();
        motor.start(50);
        Helfer.warte(1);
        motor.wechselLaufrichtung();
        Helfer.warte(1);
        motor.wechselLaufrichtung();
        Helfer.warte(1);
        motor.stop();
        Helfer.warte(1);
        motor.setzeLaufrichtung(1);
        motor.start(70);
        motor.stop();
    }
}
