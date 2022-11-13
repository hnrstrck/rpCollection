import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalInputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalState;

/**
 * Klasse fuer den Anschluss eines Phototransistors an den Raspberry Pi. Der Phototransistor kann gefragt werden, ob er gerade Lichteinfall hat. Ausserdem kann er fuer 10 Mal hintereinander gefragt werden, ob er gerade Lichteinfall hat oder nicht.
 *
 * @author Heiner Stroick, Johannes Pieper
 * @version 2.0
 */
public final class RPPhototransistor {

    private DigitalInput digitalInput = null;
    private boolean boolInitialisierungErfolgt = false;
    private int intPin = 0;
    /**
    * Erstellt ein neues Objekt der Klasse RPPhototransistor, ohne einen Pin anzugeben.
    */
    RPPhototransistor() {
    }

    /**
    * Erstellt ein neues Objekt der Klasse RPPhototransistor.
    * @param pPin Der Pin, an dem der Phototransistor angeschlossen ist.
    */
    RPPhototransistor(int pPin) {
        this.setPin(pPin);
    }

    /**
    * Setzt den Pin fuer den Phototransistor.
    * @param pPin Der Pin, an dem der Phototransistor angeschlossen ist.
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
    * Gibt den definierten Pin des Phototransistors zurueck.
    * @return Der Pin des Phototransistors.
    */
    public int gibPin(){
        return intPin;
    }

    /**
    * Ueberprueft, ob der Phototransistor Lichteinfall hat.
    * @return true oder false, je nach dem, ob der Phototransistor Lichteinfall hat (true = Phototransistor hat Lichteinfall, false = Phototransistor hat keinen Lichteinfall).
    */
    public boolean istLichteinfall() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (this.digitalInput.state() == DigitalState.HIGH) {
                    System.out.println("Ja, Licht gemessen");
                    return true;
                } else {
                    System.out.println("Nein, kein Licht gemessen");
                    return false;
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
                return false;
            }
        } else {
            System.out.println("Zuerst Pin fuer den Phototransistor angeben");
            return false;
        }
    }

    /**
    * Ueberprueft 10 Mal, ob der Phototransistor Lichteinfall hat (keie Rueckgabe). Die Ergebnisse werden in der Shell ausgegeben.
    */
    public void ueberwache10Mal() {
        System.out.println("Ueberwache 10 mal den Phototransistor");

        for (int count = 1; count <= 10; count++){
            System.out.println("Ueberwache " + count + "/10: " + istLichteinfall());
            Helfer.warte(800);
        }

        System.out.println("Ueberwachung beendet");
    }
}
