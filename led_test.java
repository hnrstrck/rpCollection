import com.pi4j.io.gpio.*;

public final class led_test {

    private GpioPinDigitalOutput pin_r;
    private GpioController gpio;
    private boolean boolInitialisierungErfolgt;

    private static final Pin[] pinArray = new Pin[] {RaspiPin.GPIO_00, 
            RaspiPin.GPIO_01, RaspiPin.GPIO_02, RaspiPin.GPIO_03, RaspiPin.GPIO_04, 
            RaspiPin.GPIO_05, RaspiPin.GPIO_06, RaspiPin.GPIO_07, RaspiPin.GPIO_08, 
            RaspiPin.GPIO_09, RaspiPin.GPIO_10, RaspiPin.GPIO_11, RaspiPin.GPIO_12, 
            RaspiPin.GPIO_13, RaspiPin.GPIO_14, RaspiPin.GPIO_15, RaspiPin.GPIO_16, 
            RaspiPin.GPIO_17, RaspiPin.GPIO_18, RaspiPin.GPIO_19, RaspiPin.GPIO_20};

    private int intPin;

    led_test() {
        gpio = GpioFactory.getInstance();
        boolInitialisierungErfolgt = false;
    }

    public void setPin(int pPin) {

        System.out.println("Output-Pin gesetzt:");

        try {
            pin_r = gpio.provisionDigitalOutputPin(pinArray[pPin]);
            pin_r.setShutdownOptions(true, PinState.LOW);
            System.out.println("Pin " + pPin + " gesetzt");

            boolInitialisierungErfolgt = true;

            intPin = pPin;

        } catch (NullPointerException f){
            System.out.println("Error: Pin nicht definiert? (NullPointerException)");
        }
    }

    public int gibPin() {
        return intPin;
    }

    public void an() {
        if (boolInitialisierungErfolgt == true){
            try{
                pin_r.setState(PinState.HIGH);
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }

}