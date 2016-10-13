import com.pi4j.io.gpio.*;

public final class rpMotor {

    private GpioPinDigitalInput pin;
    private GpioController gpio;

    private boolean boolInitialisierungErfolgt;
    private int intPin;

    rpMotor() {
        gpio = GpioFactory.getInstance();
        boolInitialisierungErfolgt = false;
    }

    rpMotor(int pPinE, int pPinDIR1, int pPinDIR2){
        gpio = GpioFactory.getInstance();
        boolInitialisierungErfolgt = false;

        //this.setPins(pPinE, pPinDIR1, pPinDIR);
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

    public void destroy() {
        gpio.shutdown();
        gpio = null;
        pin = null;
    }

}
