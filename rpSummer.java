import com.pi4j.io.gpio.*;

public final class rpSummer {

    private GpioPinDigitalOutput pin;
    private GpioController gpio;

    private boolean boolInitialisierungErfolgt;
    private int intPin;

    private static final Pin[] pinArray = new Pin[] {RaspiPin.GPIO_00, 
            RaspiPin.GPIO_01, RaspiPin.GPIO_02, RaspiPin.GPIO_03, RaspiPin.GPIO_04, 
            RaspiPin.GPIO_05, RaspiPin.GPIO_06, RaspiPin.GPIO_07, RaspiPin.GPIO_08, 
            RaspiPin.GPIO_09, RaspiPin.GPIO_10, RaspiPin.GPIO_11, RaspiPin.GPIO_12, 
            RaspiPin.GPIO_13, RaspiPin.GPIO_14, RaspiPin.GPIO_15, RaspiPin.GPIO_16, 
            RaspiPin.GPIO_17, RaspiPin.GPIO_18, RaspiPin.GPIO_19, RaspiPin.GPIO_20};

    rpSummer() {
        gpio = GpioFactory.getInstance();
        boolInitialisierungErfolgt = false;
    }

    public void setPin(int pPin) {
        pin = null;

        System.out.println("Input-Pin gesetzt:");

        try {

            pin = gpio.provisionDigitalOutputPin(pinArray[pPin]);
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
