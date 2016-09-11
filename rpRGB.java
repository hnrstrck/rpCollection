import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public final class rpRGB {

    private GpioPinDigitalOutput pins[];
    private GpioController gpio;
    private boolean bool_initialisierung_erfolgt;
    private int intPinR, intPinG, intPinB;
    private int anteil_r, anteil_g, anteil_b;

    rpRGB() {
        gpio = GpioFactory.getInstance();
        pins = new GpioPinDigitalOutput[3];
        bool_initialisierung_erfolgt = false;
    }

    
    public void set_Pins(int roterPin, int gelberPin, int blauerPin){
        System.out.println("Setze Pins:");
        set_R_Pin(roterPin);     
        set_G_Pin(gelberPin);  
        set_B_Pin(blauerPin);    
    }

    
    public void set_R_Pin(int roterPin){
        System.out.println("ROT gesetzt:");
        set_Pin(0, roterPin);
        intPinR = roterPin;        
    }

    
    public void set_G_Pin(int gelberPin){
        System.out.println("GELB gesetzt:");
        set_Pin(1, gelberPin);    
        intPinG = gelberPin;  
    }

    
    public void set_B_Pin(int blauerPin){
        System.out.println("BLAU gesetzt:");
        set_Pin(2, blauerPin); 
        intPinB = blauerPin;       
    }

    
    private void set_Pin(int pNr, int pPin) {
        pins = null;

        System.out.println("Output-Pin gesetzt:");

        try {
            switch(pPin) {

                case 0: {
                    pins[pNr] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);
                    pins[pNr].setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 0 gesetzt");
                    break;
                }

                case 1: {
                    pins[pNr] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01);
                    pins[pNr].setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 1 gesetzt");
                    break;
                }

                case 2: {
                    pins[pNr] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02);
                    pins[pNr].setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 2 gesetzt");
                    break;
                }

                case 3: {
                    pins[pNr] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03);
                    pins[pNr].setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 3 gesetzt");
                    break;
                }

                case 4: {
                    pins[pNr] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04);
                    pins[pNr].setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 4 gesetzt");
                    break;
                }

                case 5: {
                    pins[pNr] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05);
                    pins[pNr].setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 5 gesetzt");
                    break;
                }

                case 6: {
                    pins[pNr] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06);
                    pins[pNr].setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 6 gesetzt");
                    break;
                }

                case 7: {
                    pins[pNr] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07);
                    pins[pNr].setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 7 gesetzt");
                    break;
                }

                case 8: {
                    pins[pNr] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08);
                    pins[pNr].setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 8 gesetzt");
                    break;
                }

                case 9: {
                    pins[pNr] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_09);
                    pins[pNr].setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 9 gesetzt");
                    break;
                }

                case 10: {
                    pins[pNr] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10);
                    pins[pNr].setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 10 gesetzt");
                    break;
                }

                case 11: {
                    pins[pNr] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11);
                    pins[pNr].setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 11 gesetzt");
                    break;
                }

                case 12: {
                    pins[pNr] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12);
                    pins[pNr].setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 12 gesetzt");
                    break;
                }

                case 13: {
                    pins[pNr] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13);
                    pins[pNr].setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 13 gesetzt");
                    break;
                }

                case 14: {
                    pins[pNr] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_14);
                    pins[pNr].setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 14 gesetzt");
                    break;
                }

                case 15: {
                    pins[pNr] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15);
                    pins[pNr].setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 15 gesetzt");
                    break;
                }

                case 16: {
                    pins[pNr] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16);
                    pins[pNr].setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 16 gesetzt");
                    break;
                }

                case 17: {
                    pins[pNr] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_17);
                    pins[pNr].setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 17 gesetzt");
                    break;
                }

                case 18: {
                    pins[pNr] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_18);
                    pins[pNr].setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 18 gesetzt");
                    break;
                }

                case 19: {
                    pins[pNr] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_19);
                    pins[pNr].setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 19 gesetzt");
                    break;
                }

                case 20: {
                    pins[pNr] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_20);
                    pins[pNr].setShutdownOptions(true, PinState.LOW);
                    System.out.println("Pin 20 gesetzt");
                    break;
                }

            }
            bool_initialisierung_erfolgt = true;
        } catch (NullPointerException f){
            System.out.println("Error: Pin nicht definiert? (NullPointerException)");
        }
    }

    
    public int gib_Pin_R(){
        return intPinR;
    }

    
    public int gib_Pin_G(){
        return intPinG;
    }

    
    public int gib_Pin_B(){
        return intPinB;
    }

    
    public void R_an() {
        if (bool_initialisierung_erfolgt == true){
            try{
                pins[0].setState(PinState.HIGH);
                pins[1].setState(PinState.LOW);
                pins[2].setState(PinState.LOW);
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }

    
    public void G_an() {
        if (bool_initialisierung_erfolgt == true){
            try{
                pins[0].setState(PinState.LOW);
                pins[1].setState(PinState.HIGH);
                pins[2].setState(PinState.LOW);
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }

    
    public void B_an() {
        if (bool_initialisierung_erfolgt == true){
            try{
                pins[0].setState(PinState.LOW);
                pins[1].setState(PinState.LOW);
                pins[2].setState(PinState.HIGH);
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }

    
    public void an() {
        if (bool_initialisierung_erfolgt == true){
            try{
                pins[0].setState(PinState.HIGH);
                pins[1].setState(PinState.HIGH);
                pins[2].setState(PinState.HIGH);
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }


    public void blinke ()
    {
        if (bool_initialisierung_erfolgt == true){
            try{
                for (int i = 0; i <= 5; i++) {
                    pins[1].toggle();
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
                pins[0].setState(PinState.LOW);
                pins[1].setState(PinState.LOW);
                pins[2].setState(PinState.LOW);
            } catch (NullPointerException f){
                System.out.println("Error: Pin(s) nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer die Diode angeben");
        }
    }


    public void setze_Farbe(int r, int g, int b) {
        if (b <= 255){
            if (bool_initialisierung_erfolgt == true){
                try{
                    pins[0].setState(PinState.HIGH);
                    pins[1].setState(PinState.LOW);
                    pins[2].setState(PinState.LOW);

                    anteil_r = r;
                    anteil_g = g;
                    anteil_b = b;
                } catch (NullPointerException f){
                    System.out.println("Error: Pin nicht definiert? (NullPointerException)");
                }
            } else {
                System.out.println("Zuerst Pin fuer die Diode angeben");
            }
        } else {
            System.out.println("Falsche Zahlenbereiche angegeben. R, G, B müssen zwischen 0 und 255 (jew. einschliesslich) liegen");
        }
    }

    
    public boolean ist_an() {
        if (bool_initialisierung_erfolgt == true){
            try{
                if (pins[0].getState() == PinState.HIGH) {
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
                if (pins[0].getState() == PinState.HIGH) {
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

    
    public int gib_Anteil_R(){
        return anteil_r;
    }

    
    public int gib_Anteil_G(){
        return anteil_g;
    }

    
    public int gib_Anteil_B(){
        return anteil_b;
    }

	
    public int[] gib_Farbe(){
        int return_array[];
        return_array = new int[3];

        return_array[0] = anteil_r;
        return_array[1] = anteil_g;
        return_array[2] = anteil_b;

        return return_array;
    }

    
    public void destroy() {
        gpio.shutdown();
        gpio = null;
        pins = null;
    }

    
    public static void main(String[] args){
        System.out.println("start");
    }
}
