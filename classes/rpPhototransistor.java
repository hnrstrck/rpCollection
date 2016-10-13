import com.pi4j.io.gpio.*;

public final class rpPhototransistor {

    private GpioPinDigitalInput pin;
    private GpioController gpio;

    private boolean boolInitialisierungErfolgt;
    private int intPin;

    rpPhototransistor() {
        gpio = GpioFactory.getInstance();
        boolInitialisierungErfolgt = false;
    }

    rpPhototransistor(int pPin) {
        gpio = GpioFactory.getInstance();
        boolInitialisierungErfolgt = false;

        this.setPin(pPin);
    }

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

    public int gibPin(){
        return intPin;
    }

    public boolean istLichteinfall() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (pin.getState() == PinState.HIGH) {
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

    public void ueberwache10Mal() {
        System.out.println("Ueberwache 10 mal den Phototransistor");

        for (int count = 1; count <= 10; count++){
            System.out.println("Ueberwache " + count + "/10: " + istLichteinfall());
            sleepMilliseconds(800);
        }

        System.out.println("Ueberwachung beendet");
    }


    public void sleepMilliseconds(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        }
        catch( InterruptedException e)
        {
            System.out.println("Error: Thread-Sleep unterbrochen (InterruptedException)");
        }
    }

    public void destroy() {
        gpio.shutdown();
        gpio = null;
        pin = null;
        boolInitialisierungErfolgt = false;
    }
}
