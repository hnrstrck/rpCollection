import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public final class rpDiode {

    private GpioPinDigitalOutput pin;
    private GpioController gpio;
    private boolean bool_initialisierung_erfolgt;
    private int intPin;

    rpDiode() {
        gpio = GpioFactory.getInstance();
        bool_initialisierung_erfolgt = false;
    }

    public void set_Pin(int pPin) {
        pin = null;

        System.out.println("Output-Pin gesetzt:");

        try {
            switch(pPin) {

                case 0: {
                    pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 0 gesetzt");
                    break;
                }

                case 1: {
                    pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 1 gesetzt");
                    break;
                }

                case 2: {
                    pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 2 gesetzt");
                    break;
                }

                case 3: {
                    pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 3 gesetzt");
                    break;
                }

                case 4: {
                    pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 4 gesetzt");
                    break;
                }

                case 5: {
                    pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 5 gesetzt");
                    break;
                }

                case 6: {
                    pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 6 gesetzt");
                    break;
                }

                case 7: {
                    pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 7 gesetzt");
                    break;
                }

                case 8: {
                    pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 8 gesetzt");
                    break;
                }

                case 9: {
                    pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_09);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 9 gesetzt");
                    break;
                }


                case 10: {
                    pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 10 gesetzt");
                    break;
                }

                case 11: {
                    pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 11 gesetzt");
                    break;
                }

                case 12: {
                    pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 12 gesetzt");
                    break;
                }

                case 13: {
                    pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 13 gesetzt");
                    break;
                }

                case 14: {
                    pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_14);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 14 gesetzt");
                    break;
                }

                case 15: {
                    pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 15 gesetzt");
                    break;
                }

                case 16: {
                    pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 16 gesetzt");
                    break;
                }

                case 17: {
                    pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_17);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 17 gesetzt");
                    break;
                }

                case 18: {
                    pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_18);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 18 gesetzt");
                    break;
                }

                case 19: {
                    pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_19);
                    pin.setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 19 gesetzt");
                    break;
                }

                case 20: {
                    pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_20);
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
    
    
      public int gib_Pin() {
    	return intPin;
    }
    
    
       public void an() {
        if (bool_initialisierung_erfolgt == true){
            try{
                pin.setState(PinState.HIGH);
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }
    

    public void blinke () {
        if (bool_initialisierung_erfolgt == true){
            try{
                for (int i = 0; i <= 5; i++) {
                    pin.toggle();
                    Thread.sleep(300);
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
    
  

 

    public void aus() {
        if (bool_initialisierung_erfolgt == true){
            try{
                pin.setState(PinState.LOW);
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }


    public void wechsel() {
        if (bool_initialisierung_erfolgt == true){
            try{
                pin.toggle();
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }


    public boolean ist_an() {
        if (bool_initialisierung_erfolgt == true){
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

    public boolean ist_aus() {
        if (bool_initialisierung_erfolgt == true){
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

    public void destroy() {
		gpio.shutdown();
        gpio = null;
        pin = null;
    }

    public static void main(String[] args){
        System.out.println("start");
    }
}
