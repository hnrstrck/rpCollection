import com.pi4j.io.gpio.*;

/**
 * Klasse fuer den Anschluss eines einfachen Tasters an den Raspberry Pi. Der Taster kann gefragt werden, ob er gerade gedrueckt ist. Ausserdem kann er fuer 10 Mal hintereinander gefragt werden, ob er gerade gedrueckt ist, oder nicht.
 * 
 * @author Heiner Stroick
 * @version 0.9
 */
public final class rpTaster {

    private GpioPinDigitalInput pin;
    private GpioController gpio;

    private boolean boolInitialisierungErfolgt;
    private int intPin;

    /**
    * Erstellt ein neues Objekt der Klasse rpTaster, ohne einen Pin anzugeben.
    */
    rpTaster() {
        gpio = GpioFactory.getInstance();
        boolInitialisierungErfolgt = false;
    }

    /**
    * Erstellt ein neues Objekt der Klasse rpTaster.
    * @param pPin Der Pin, an dem der Taster angeschlossen ist.
    */
    rpTaster(int pPin) {
        gpio = GpioFactory.getInstance();
        boolInitialisierungErfolgt = false;

        this.setPin(pPin);
    }

    /**
    * Setzt den Pin fuer den Taster.
    * @param pPin Der Pin, an dem der Taster angeschlossen ist.
    */
    public void setPin(int pPin) {
        pin = null;

        System.out.println("Input-Pin gesetzt:");

        try {

            pin = gpio.provisionDigitalInputPin(rpHelper.pinArray[pPin]);
            pin.setShutdownOptions(true, PinState.LOW);
            System.out.println("Pin " + pPin + " gesetzt");

            boolInitialisierungErfolgt = true;
            intPin = pPin;

        } catch (NullPointerException f){
            System.out.println("Error: Pin nicht definiert? (NullPointerException)");
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
                if (pin.getState() == PinState.HIGH) {
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
    public void destroy() {
        gpio.shutdown();
        gpio = null;
        pin = null;
    }
}
