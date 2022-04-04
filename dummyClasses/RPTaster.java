/**
 * Klasse fuer den Anschluss eines einfachen Tasters an den Raspberry Pi. Der Taster kann gefragt werden, ob er gerade gedrueckt ist. Ausserdem kann er fuer 10 Mal hintereinander gefragt werden, ob er gerade gedrueckt ist, oder nicht.
 * 
 * @author Heiner Stroick
 * @version 0.9
 */
public final class RPTaster {

    private boolean boolInitialisierungErfolgt;
    private int intPin;

    /**
    * Erstellt ein neues Objekt der Klasse RPTaster, ohne einen Pin anzugeben.
    */
    public RPTaster() {
        boolInitialisierungErfolgt = false;
    }

    /**
    * Erstellt ein neues Objekt der Klasse RPTaster.
    * @param pPin Der Pin, an dem der Taster angeschlossen ist.
    */
    public RPTaster(int pPin) {
        boolInitialisierungErfolgt = false;

        this.setPin(pPin);
    }

    /**
    * Setzt den Pin fuer den Taster.
    * @param pPin Der Pin, an dem der Taster angeschlossen ist.
    */
    public void setPin(int pPin) {
        System.out.println("Input-Pin gesetzt:");

        boolInitialisierungErfolgt = true;
        intPin = pPin;
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
        	return true;
        } else {
            System.out.println("Zuerst Pin fuer den Phototransistor angeben");
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
