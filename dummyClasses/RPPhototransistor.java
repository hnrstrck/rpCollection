/**
 * Dummy-Klasse fuer den Anschluss eines Phototransistors an den Raspberry Pi. Der Phototransistor kann gefragt werden, ob er gerade Lichteinfall hat. Ausserdem kann er fuer 10 Mal hintereinander gefragt werden, ob er gerade Lichteinfall hat oder nicht.
 * 
 * @author Heiner Stroick
 * @version 0.9
 */
public final class RPPhototransistor {

    private boolean boolInitialisierungErfolgt;
    private int intPin;
    
    /**
    * Erstellt ein neues Objekt der Klasse RPPhototransistor, ohne einen Pin anzugeben.
    */
    public RPPhototransistor() {
        boolInitialisierungErfolgt = false;
    }

    /**
    * Erstellt ein neues Objekt der Klasse RPPhototransistor.
    * @param pPin Der Pin, an dem der Phototransistor angeschlossen ist.
    */
    public RPPhototransistor(int pPin) {
        boolInitialisierungErfolgt = false;
        this.setPin(pPin);
    }

    /**
    * Setzt den Pin fuer den Phototransistor.
    * @param pPin Der Pin, an dem der Phototransistor angeschlossen ist.
    */
    public void setPin(int pPin) {
        System.out.println("Input-Pin gesetzt:");
        boolInitialisierungErfolgt = true;
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
            System.out.println("Ja, Licht gemessen");
            return true;
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
            sleepMilliseconds(800);
        }

        System.out.println("Ueberwachung beendet");
    }


    /**
    * Wartet die angegebene Zeit.
    * @param milliseconds Haelt den Thread fuer milliseconds Millisekunden an.
    */
    private void sleepMilliseconds(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        }
        catch( InterruptedException e)
        {
            System.out.println("Error: Thread-Sleep unterbrochen (InterruptedException)");
        }
    }

    /**
    * Schalte GPIO ab und dereferenziere den GPIO und den Pin.
    */ 
    public void herunterfahren() {
	}
}
