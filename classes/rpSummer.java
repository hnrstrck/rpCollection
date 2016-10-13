import com.pi4j.io.gpio.*;

public final class rpSummer {

    private GpioPinDigitalOutput pin;
    private GpioController gpio;

    private boolean boolInitialisierungErfolgt;
    private int intPin;

    rpSummer() {
        gpio = GpioFactory.getInstance();
        boolInitialisierungErfolgt = false;
    }

    rpSummer(int pPin) {
        gpio = GpioFactory.getInstance();
        boolInitialisierungErfolgt = false;

        this.setPin(pPin);
    }

    public void setPin(int pPin) {
        pin = null;

        System.out.println("Input-Pin gesetzt:");

        try {

            pin = gpio.provisionDigitalOutputPin(rpHelper.pinArray[pPin]);
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

    public void an() {
        if (boolInitialisierungErfolgt == true){
            try{
                pin.setState(PinState.HIGH);
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer den Summer angeben");
        }
    }

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

    public void aus() {
        if (boolInitialisierungErfolgt == true){
            try{
                pin.setState(PinState.LOW);
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer den Summer angeben");
        }
    }

    public boolean istAn() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (pin.getState() == PinState.HIGH) {
                    System.out.println("Ja, Summer ist an");
                    return true;
                } else {
                    System.out.println("Nein, Summer ist aus");
                    return false; 
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
                return false;
            }
        } else {
            System.out.println("Zuerst Pin fuer den Summer angeben");
            return false;
        }    
    }

    public boolean istAus() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (pin.getState() == PinState.LOW) {
                    System.out.println("Ja, Summer ist aus");
                    return true;
                } else {
                    System.out.println("Nein, Summer ist an");
                    return false; 
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
                return false;
            }
        } else {
            System.out.println("Zuerst Pin fuer den Summer angeben");
            return false;
        }    
    }

    public void destroy() {
        gpio.shutdown();
        gpio = null;
        pin = null;
    }

}
