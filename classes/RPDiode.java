import com.pi4j.io.gpio.*;

/**
 * Klasse zum Anschluss einer Diode an den Raspberry Pi. Die Diode kann an- und ausgeschaltet werden, blinken und auch in ihrer Blinkfrequenz geaendert weden (auch mit einem AD-Wandler).
 * 
 * @author Heiner Stroick
 * @version 0.9
 */
public final class RPDiode {

    private GpioPinDigitalOutput pin;
    private GpioController gpio;

    private boolean boolInitialisierungErfolgt;
    private int intPin;

    /*
     * Damit es nur einen (!) Blinken-Thead gibt.
     */
    private Thread threadEndlosBlinken;

    private boolean blinktGerade = false;

    /**
    * Erstellt ein Objekt der Klasse RPDiode, ohne einen Pin anzugeben.
    */
    RPDiode() {
        gpio = GpioFactory.getInstance();
        boolInitialisierungErfolgt = false;
    }

    /**
    * Erstellt ein Objekt der Klasse RPDiode.
    * @param pPin Der Pin, an dem die Diode angeschlossen ist.
    */
    RPDiode(int pPin) {
        gpio = GpioFactory.getInstance();
        boolInitialisierungErfolgt = false;

        this.setPin(pPin);
    }

    /**
    * Setzt den Pin fuer die Diode.
    * @param pPin Der Pin, an dem die Diode angeschlossen ist.
    */
    public void setPin(int pPin) {
        pin = null;

        System.out.println("Output-Pin gesetzt:");

        try {
            pin = gpio.provisionDigitalOutputPin(Helfer.pinArray[pPin]);
            pin.setShutdownOptions(true, PinState.LOW);
            System.out.println("Pin " + pPin + " gesetzt");

            boolInitialisierungErfolgt = true;

            intPin = pPin;

        } catch (NullPointerException f){
            System.out.println("Error: Pin nicht definiert? (NullPointerException)");
        } catch (com.pi4j.io.gpio.exception.GpioPinExistsException e){
            System.out.println("Error: Pin doppelt definiert? (GpioPinExistsException)");
        }
    }

    /**
    * Gibt den Pin der Diode zurueck.
    * @return Pin der Diode
    */
    public int gibPin() {
        return intPin;
    }

    /**
    * Schaltet die Diode an.
    */
    public void an() {
        if (boolInitialisierungErfolgt == true){
            try{
                pin.setState(PinState.HIGH);
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }

	/**
	* Schalte den die Diode in Abhaengigkeit eines Wertes an oder aus. 
	* @param status Erforderlich ist ein Wahrheitswert (true / false). Ist der Parameterwert true, bleibt die Diode aus. Ist der Parameterwert false, so geht die Diode an. 
	*/
	public void schalten(boolean status){
		if (status == true){
			aus();
		} else {
			an();
		}
	}
	
    /**
    * Schaltet die Diode aus.
    */    
    public void aus() {
        if (boolInitialisierungErfolgt == true){
            try{
                pin.setState(PinState.LOW);
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }

    /**
    * Schaltet die Diode an oder aus, je nach dem, was vorher vorlag (toogle).
    */
    public void wechsel() {
        if (boolInitialisierungErfolgt == true){
            try{
                pin.toggle();
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }

    /**
    * Ueberprueft, ob die Diode an ist.
    * @return true oder false, je nach dem, ob die Diode an ist (true = Diode an, false = Diode aus).
    */
    public boolean istAn() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (pin.getState() == PinState.HIGH) {
                    System.out.println("Ja, Diode ist an");
                    return true;
                } else {
                    System.out.println("Nein, Diode ist aus");
                    return false; 
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
                return false;
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
            return false;
        }    
    }

    /**
    * Ueberprueft, ob die Diode aus ist.
    * @return true oder false, je nach dem, ob die Diode aus ist (true = Diode aus, false = Diode an).
    */
    public boolean istAus() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (pin.getState() == PinState.LOW) {
                    System.out.println("Ja, Diode ist aus");
                    return true;
                } else {
                    System.out.println("Nein, Diode ist an");
                    return false; 
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
                return false;
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
            return false;
        }    
    }

    
    /**
    * Laesst die Diode fuer kurze Zeit blinken.
    */
    public void blinke() {
        if (boolInitialisierungErfolgt == true){
            try{
                for (int i = 0; i <= 5; i++) {
                    pin.toggle();
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                System.out.println("Error: Timeout (InterruptedException)");
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }

    /**
    * Laesst die Diode blinken (auf unbestimmte Zeit). Die Frequenz kann in Millisekunden angepasst werden.
    * @param pIntervall Regelt die Blinkfrequenz ueber Pulsweitenmodulation (Angabe in Millisekunden).
    */    
    private void startBlinken(int pIntervall) {
        if (boolInitialisierungErfolgt == true){
            threadEndlosBlinken = new Thread(){

                public void run()  {
                    try {

                        while(true){

                            //Signal endlos blinken
                            an();
                            Thread.sleep(pIntervall);     
                            aus();
                            Thread.sleep(pIntervall); 

                        }

                    } catch (InterruptedException e) {
                        System.out.println("Blinken beendet");
                        Thread.currentThread().interrupt();
                        return;
                    }

                }
            };
            threadEndlosBlinken.start();
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }

    /**
    * Laesst die Diode blinken (auf unbestimmte Zeit). Die Frequenz kann mit einem AD-Wandler angepasst werden.
    * @param meinWandler Objekt der Klasse RPADWandler.
    * @param meinRegler Regler, der ausgelesen werden soll.
    * @see RPADWandler
    * @see RPRegler
    * @see Helfer
    */  
    private void startBlinkenVariabel(RPADWandler meinWandler, RPRegler meinRegler) {
        if (boolInitialisierungErfolgt == true){

            threadEndlosBlinken = new Thread(){

                public void run()  {
                    try {

                        while(true){

                            //Signal endlos blinken
                            an();
                            Thread.sleep((int)Math.round(((100f - (float)(RPADWandler.gibProzentwertVonRegler(meinRegler,0)))/100f)*300f));     
                            aus();
                            Thread.sleep((int)Math.round(((100f - (float)(RPADWandler.gibProzentwertVonRegler(meinRegler,0)))/100f)*300f));     

                        }
                    } catch (InterruptedException e) {
                        System.out.println("Blinken beendet");
                        Thread.currentThread().interrupt();
                        return;
                    }

                }
            };
            threadEndlosBlinken.start();
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }

    /**
    * Laesst die Diode blinken (auf unbestimmte Zeit). Die Frequenz ist voreingestellt (200 Millisekunden).
    */  
    public void blinkeEndlosStart(){
        if (boolInitialisierungErfolgt == true){

            if(!blinktGerade){
                startBlinken(200);
                blinktGerade = true;
            } else {
                System.out.println("Diode blinkt schon");
            }

        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }

    /**
    * Laesst die Diode blinken (auf unbestimmte Zeit). Die Frequenz kann in Millisekunden angepasst werden.
    * @param pIntervall Regelt die Blinkfrequenz ueber Pulsweitenmodulation (Angabe in Millisekunden).
    */   
    public void blinkeEndlosStart(int pIntervall){
        if (boolInitialisierungErfolgt == true){

            if(!blinktGerade){
                startBlinken(pIntervall);
                blinktGerade = true;
            } else {
                System.out.println("Diode blinkt schon");
            }

        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }

    /**
    * Laesst die Diode blinken (auf unbestimmte Zeit). Die Frequenz kann ueber die Stellung eines Reglers an einem AD-Wandler angepasst werden.
    * @see RPADWandler
    * @param pWandler Objekt der Klasse RPADWandler.
    * @param pRegler Regler, der ausgelesen werden soll.
    * @see RPADWandler
    * @see RPRegler
    * @see Helfer
    */  
    public void blinkeEndlosStart(RPADWandler pWandler, RPRegler pRegler){
        if (boolInitialisierungErfolgt == true){

            if(!blinktGerade){
                startBlinkenVariabel(pWandler, pRegler);
                blinktGerade = true;
            } else {
                System.out.println("Diode blinkt schon");
            }

        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }

    }

    /**
    * Beendet das endlose blinken der Diode.
    */  
    public void blinkeEndlosStop(){
        if (boolInitialisierungErfolgt == true){

            threadEndlosBlinken.interrupt();
            blinktGerade = false;
            aus();
        }
        else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }
    
    /**
    * Schalte GPIO ab und dereferenziere den GPIO und den Pin.
    */  
    public void herunterfahren() {
		aus();
        gpio.shutdown();
		try{
			gpio.unprovisionPin(pin);
		} catch (java.lang.NullPointerException e){
			System.out.println("Pin konnte nicht dereferenziert werden");
		}
    }

}
