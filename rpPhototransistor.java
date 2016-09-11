import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public final class rpPhototransistor {

    private GpioPinDigitalInput pin;
    private GpioController gpio;
    private boolean bool_initialisierung_erfolgt;
    private int intPin;

    rpPhototransistor() {
        gpio = GpioFactory.getInstance();
        bool_initialisierung_erfolgt = false;
    }

    public void set_Pin(int pPin) {
        pin = null;

        System.out.println("Input-Pin gesetzt:");

        try {
            switch(pPin) {

                case 0: {
                    pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 0 gesetzt");
                    break;
                }

                case 1: {
                    pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_01);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 1 gesetzt");
                    break;
                }

                case 2: {
                    pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 2 gesetzt");
                    break;
                }

                case 3: {
                    pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_03);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 3 gesetzt");
                    break;
                }

                case 4: {
                    pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_04);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 4 gesetzt");
                    break;
                }

                case 5: {
                    pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 5 gesetzt");
                    break;
                }

                case 6: {
                    pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_06);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 6 gesetzt");
                    break;
                }

                case 7: {
                    pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_07);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 7 gesetzt");
                    break;
                }

                case 8: {
                    pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_08);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 8 gesetzt");
                    break;
                }

                case 9: {
                    pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_09);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 9 gesetzt");
                    break;
                }

                case 10: {
                    pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_10);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 10 gesetzt");
                    break;
                }

                case 11: {
                    pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_11);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 11 gesetzt");
                    break;
                }

                case 12: {
                    pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_12);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 12 gesetzt");
                    break;
                }

                case 13: {
                    pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_13);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 13 gesetzt");
                    break;
                }

                case 14: {
                    pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_14);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 14 gesetzt");
                    break;
                }

                case 15: {
                    pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_15);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 15 gesetzt");
                    break;
                }

                case 16: {
                    pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_16);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 16 gesetzt");
                    break;
                }

                case 17: {
                    pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_17);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 17 gesetzt");
                    break;
                }

                case 18: {
                    pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_18);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 18 gesetzt");
                    break;
                }

                case 19: {
                    pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_19);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 19 gesetzt");
                    break;
                }

                case 20: {
                    pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_20);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 20 gesetzt");
                    break;
                }

            }

            bool_initialisierung_erfolgt = true;
            intPin = pPin;

        } catch (NullPointerException f){
            System.out.println("Error: Pin nicht definiert? (NullPointerException)");
        }
    }

    public int gib_Pin(){
        return intPin;
    }

    public boolean ist_lichteinfall() {
        if (bool_initialisierung_erfolgt == true){
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

    public void ueberwache_10_mal() {
        System.out.println("Ueberwache 10 mal");

        for (int count = 1; count <= 10; count++){
            System.out.println("Ueberwache " + count + "/10: " + ist_lichteinfall());
        }

        System.out.println("Ueberwachung beendet");
    }

    public void destroy() {
        gpio.shutdown();
        gpio = null;
        pin = null;
    }

    public static void main(String[] args){
        System.out.println("start");
    }
}
