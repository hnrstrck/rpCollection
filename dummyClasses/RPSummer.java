/**
 * Klasse fuer den Anschluss eines 3 Volt Summers an den Raspberry Pi. Der Summer kann angeschaltet und abgeschaltet werden. Mehtoden zum einfahcen beepen (zum Beispiel als Bestaetigung) stehen bereit. Der Summer kann auch gefragt werden, ob er gerade an ist oder aus ist.
 * 
 * @author Heiner Stroick
 * @version 0.9
 */
public final class RPSummer {

    private boolean boolInitialisierungErfolgt;
    private int intPin;

    /**
    * Erstellt ein neues Objekt der Klasse RPSummer, ohne einen Pin anzugeben.
    */
    public RPSummer() {
        boolInitialisierungErfolgt = false;
    }

   /**
    * Erstellt ein neues Objekt der Klasse RPSummer.
    * @param pPin Der Pin, an dem der Summer angeschlossen ist.
    */
    public RPSummer(int pPin) {
        boolInitialisierungErfolgt = false;

        this.setPin(pPin);
    }


    /**
    * Setzt den Pin fuer den Summer.
    * @param pPin Der Pin, an dem der Summer angeschlossen ist.
    */
    public void setPin(int pPin) {
        System.out.println("Output-Pin gesetzt:");
        boolInitialisierungErfolgt = true;
        intPin = pPin;
    }

    /**
    * Gibt den definierten Pin des Summers zurueck.
    * @return Der Pin des Summers.
    */
    public int gibPin(){
        return intPin;
    }

    /**
    * Schaltet den Summer an (auf unbestimmte Zeit).
    */
    public void an() {
        if (boolInitialisierungErfolgt == true){
        } else {
            System.out.println("Zuerst Pin fuer den Summer angeben");
        }
    }

	/**
	* Schalte den den Summer in Abhaengigkeit eines Wertes an oder aus. 
	* @param status Erforderlich ist ein Wahrheitswert (true / false). Ist der Parameterwert true, bleibt der Summer aus. Ist der Parameterwert false, so beep der Summer kurz zweimal. 
	*/
	public void schalten(boolean status){
		if (status == true){
			aus();
		} else {
			beepbeep();
		}
	}
	
    /**
    * Laesst den Summer kurz beepen.
    */
    public void beep() {
        if (boolInitialisierungErfolgt == true){
            try{
                this.an();
                Thread.sleep(200);
                this.aus();

            } catch (InterruptedException e) {
                System.out.println("Error: Timeout (InterruptedException)");
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer den Summer angeben");
        }
    }

    /**
    * Laesst den Summer kurz zweimal beepen.
    */
    public void beepbeep() {
        if (boolInitialisierungErfolgt == true){
            try{
                this.an();
                Thread.sleep(200);
                this.aus();
                Thread.sleep(100);
                this.an();
                Thread.sleep(200);
                this.aus();

            } catch (InterruptedException e) {
                System.out.println("Error: Timeout (InterruptedException)");
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer die Summer angeben");
        }
    }

    /**
    * Schaltet den Summer aus.
    */
    public void aus() {
        if (boolInitialisierungErfolgt == true){
        } else {
            System.out.println("Zuerst Pin fuer den Summer angeben");
        }
    }

    /**
    * Ueberprueft, ob der Summer an ist.
    * @return true oder false, je nach dem, ob der Summer an ist (true = Summer an, false = Summer aus).
    */
    public boolean istAn() {
        if (boolInitialisierungErfolgt == true){
        	return false;
        } else {
            System.out.println("Zuerst Pin fuer den Summer angeben");
            return false;
        }    
    }

    /**
    * Ueberprueft, ob der Summer aus ist.
    * @return true oder false, je nach dem, ob der Summer aus ist (true = Summer aus, false = Summer an).
    */    
    public boolean istAus() {
        if (boolInitialisierungErfolgt == true){
        	return false;
        } else {
            System.out.println("Zuerst Pin fuer den Summer angeben");
            return false;
        }    
    }
    
    /**
    * Schalte GPIO ab und dereferenziere den GPIO und den Pin.
    */  
    public void herunterfahren() {
    }

}