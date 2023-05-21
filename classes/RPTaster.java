import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalInputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalState;

/**
 * Klasse fuer den Anschluss eines einfachen Tasters an den Raspberry Pi. Der Taster kann gefragt werden, ob er gerade gedrueckt ist. Ausserdem kann er fuer 10 Mal hintereinander gefragt werden, ob er gerade gedrueckt ist, oder nicht.
 *
 * @author Heiner Stroick, Johannes Pieper
 * @version 2.0
 */
public final class RPTaster {

    private DigitalInput digitalInput = null;
    private boolean boolInitialisierungErfolgt = false;
    private int intPin = 0;

    /**
    * Erstellt ein neues Objekt der Klasse RPTaster, ohne einen Pin anzugeben.
    */
    public RPTaster() {
    }

    /**
    * Erstellt ein neues Objekt der Klasse RPTaster.
    * @param pin Der Pin, an dem der Taster angeschlossen ist.
    */
    public RPTaster(int pin) {
        this.setPin(pin);
    }

    /**
    * Setzt den Pin fuer den Taster.
    * @param pin Der Pin, an dem der Taster angeschlossen ist.
    */
    public void setPin(int pin) {
        if (this.digitalInput == null) {
            Context pi4j = RPEnvironment.getContext();
            DigitalInputConfigBuilder inputConfig = RPEnvironment.getInputConfig();
            this.digitalInput = pi4j.create(inputConfig
                .address(pin)
                .id("pin" + pin)
            );
            System.out.println("Intput-Pin gesetzt:");
            boolInitialisierungErfolgt = true;
            intPin = pin;
        } else {
            System.out.println("Input-Pin bereits gesetzt:");
        }
    }

    /**
    * Gibt den definierten Pin des Tasters zurueck.
    * @return Der Pin des Tasters
    */
    public int gibPin(){
        return intPin;
    }

    /**
    * Ueberprueft, ob der Taster gedrueckt ist.
    * @return true oder false, je nach dem, ob der Taster an ist (true = Taster gedrueckt, false = Taster gedrueckt).
    */
    public boolean istGedrueckt() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (this.digitalInput.state() == DigitalState.HIGH) {
                    System.out.println("Ja, Taster gedrueckt");
                    return true;
                } else {
                    System.out.println("Nein, Taster nicht gedrueckt");
                    return false;
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
                return false;
            }
        } else {
            System.out.println("Zuerst Pin angeben");
            return false;
        }
    }

    /**
    * Ueberprueft 10 Mal, ob der Taster gedrueckt ist (keie Rueckgabe). Die Ergebnisse werden in der Shell ausgegeben.
    */
    public void ueberwache10Mal() {
        System.out.println("Ueberwache 10 mal den Taster");

        for (int count = 1; count <= 10; count++){
            System.out.println("Ueberwache " + count + "/10: " + istGedrueckt());
            Helfer.warte(1);
        }

        System.out.println("Ueberwachung beendet");
    }

    /**
    * Test-Methode
    */
    public static void main(String[] args)
    {
        RPTaster taster = new RPTaster(13);
        taster.ueberwache10Mal();
    }
}