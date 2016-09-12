import com.pi4j.io.gpio.*;

public final class rpRGB {

    private GpioPinDigitalOutput pin_r, pin_g, pin_b;
    private GpioController gpio;

    private boolean boolInitialisierungErfolgt;
    private int[] intPins;
    private int anteil_r, anteil_g, anteil_b;
    private static final Pin[] pinArray = new Pin[] {RaspiPin.GPIO_00, 
            RaspiPin.GPIO_01, RaspiPin.GPIO_02, RaspiPin.GPIO_03, RaspiPin.GPIO_04, 
            RaspiPin.GPIO_05, RaspiPin.GPIO_06, RaspiPin.GPIO_07, RaspiPin.GPIO_08, 
            RaspiPin.GPIO_09, RaspiPin.GPIO_10, RaspiPin.GPIO_11, RaspiPin.GPIO_12, 
            RaspiPin.GPIO_13, RaspiPin.GPIO_14, RaspiPin.GPIO_15, RaspiPin.GPIO_16, 
            RaspiPin.GPIO_17, RaspiPin.GPIO_18, RaspiPin.GPIO_19, RaspiPin.GPIO_20};

    rpRGB() {
        gpio = GpioFactory.getInstance();
        intPins = new int[3];
        boolInitialisierungErfolgt = false;
    }

    public void setPins(int roterPin, int gelberPin, int blauerPin){
        System.out.println("Setze Pins:");
        setPinRot(roterPin);     
        setPinGelb(gelberPin);  
        setPinBlau(blauerPin);    
    }

    private void setPinRot(int pPin) {
        pin_r = null;

        System.out.println("Output-Pin gesetzt fuer ROT:");

        try {
            pin_r = gpio.provisionDigitalOutputPin(pinArray[pPin]);
            pin_r.setShutdownOptions(true, PinState.LOW);

            System.out.println("Pin " + pPin + " gesetzt");
            boolInitialisierungErfolgt = true;

            intPins[0] = pPin;
        } catch (NullPointerException f){
            System.out.println("Error: Pin nicht definiert? (NullPointerException)");
        } catch (com.pi4j.io.gpio.exception.GpioPinExistsException e){
            System.out.println("Error: Pin doppelt definiert? (GpioPinExistsException)");
        }

    }

    private void setPinGelb(int pPin) {
        pin_g = null;

        System.out.println("Output-Pin gesetzt fuer GELB:");

        try {
            pin_g = gpio.provisionDigitalOutputPin(pinArray[pPin]);
            pin_g.setShutdownOptions(true, PinState.LOW);

            System.out.println("Pin " + pPin + " gesetzt");
            boolInitialisierungErfolgt = true;

            intPins[1] = pPin;
        } catch (NullPointerException f){
            System.out.println("Error: Pin nicht definiert? (NullPointerException)");
        } catch (com.pi4j.io.gpio.exception.GpioPinExistsException e){
            System.out.println("Error: Pin doppelt definiert? (GpioPinExistsException)");
        }

    }

    private void setPinBlau(int pPin) {
        pin_b = null;

        System.out.println("Output-Pin gesetzt fuer BLAU:");

        try {
            pin_b = gpio.provisionDigitalOutputPin(pinArray[pPin]);
            pin_b.setShutdownOptions(true, PinState.LOW);

            System.out.println("Pin " + pPin + " gesetzt");
            boolInitialisierungErfolgt = true;

            intPins[2] = pPin;
        } catch (NullPointerException f){
            System.out.println("Error: Pin nicht definiert? (NullPointerException)");
        } catch (com.pi4j.io.gpio.exception.GpioPinExistsException e){
            System.out.println("Error: Pin doppelt definiert? (GpioPinExistsException)");
        }

    }

    public int gibPinRot(){
        return intPins[0];
    }

    public int gibPinGelb(){
        return intPins[1];
    }

    public int gibPinBlau(){
        return intPins[2];
    }

    public void rotAn() {
        if (boolInitialisierungErfolgt == true){
            try{
                pin_r.setState(PinState.HIGH);
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer die Diode angeben");
        }
    }

    public void gelbAn() {
        if (boolInitialisierungErfolgt == true){
            try{
                pin_g.setState(PinState.HIGH);
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer die Diode angeben");
        }
    }

    public void blauAn() {
        if (boolInitialisierungErfolgt == true){
            try{
                pin_b.setState(PinState.HIGH);
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer die Diode angeben");
        }
    }

    public void rotAus() {
        if (boolInitialisierungErfolgt == true){
            try{
                pin_r.setState(PinState.LOW);
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer die Diode angeben");
        }
    }

    public void gelbAus() {
        if (boolInitialisierungErfolgt == true){
            try{
                pin_g.setState(PinState.LOW);
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer die Diode angeben");
        }
    }

    public void blauAus() {
        if (boolInitialisierungErfolgt == true){
            try{
                pin_b.setState(PinState.LOW);
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer die Diode angeben");
        }
    }
    
    public void an() {
        if (boolInitialisierungErfolgt == true){
            try{
                pin_r.setState(PinState.HIGH);
                pin_g.setState(PinState.HIGH);
                pin_b.setState(PinState.HIGH);
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer die Diode angeben");
        }
    }

    public void blinke()    {
        if (boolInitialisierungErfolgt == true){
            try{
                for (int i = 0; i <= 5; i++) {
                    /**
                     * Hier evtl. anders blinken lassen
                     */
                    pin_r.toggle();
                    pin_g.toggle();
                    pin_b.toggle();
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                System.out.println("Error: Timeout (InterruptedException)");
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer die Diode angeben");
        }
    }

    public void aus() {
        if (boolInitialisierungErfolgt == true){
            try{
                pin_r.setState(PinState.LOW);
                pin_g.setState(PinState.LOW);
                pin_b.setState(PinState.LOW);
            } catch (NullPointerException f){
                System.out.println("Error: Pin(s) nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer die Diode angeben");
        }
    }

    public void setzeFarbe(int r, int g, int b) {
        if (b <= 255){
            if (boolInitialisierungErfolgt == true){
                try{
                    pin_r.setState(PinState.HIGH);
                    pin_g.setState(PinState.LOW);
                    pin_b.setState(PinState.LOW);

                    anteil_r = r;
                    anteil_g = g;
                    anteil_b = b;
                } catch (NullPointerException f){
                    System.out.println("Error: Pin nicht definiert? (NullPointerException)");
                }
            } else {
                System.out.println("Zuerst Pins fuer die Diode angeben");
            }
        } else {
            System.out.println("Falsche Zahlenbereiche angegeben. R, G, B müssen zwischen 0 und 255 (jew. einschliesslich) liegen");
        }
    }

    public boolean istAn() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (pin_r.getState() == PinState.HIGH) {
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
            System.out.println("Zuerst Pins fuer die Diode angeben");
            return false;
        }    
    }

    public boolean istAus() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (pin_r.getState() == PinState.HIGH) {
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
            System.out.println("Zuerst Pins fuer die Diode angeben");
            return false;
        }    
    }

    public int gibAnteilRot(){
        return anteil_r;
    }

    public int gibAnteilGelb(){
        return anteil_g;
    }

    public int gibAnteilBlau(){
        return anteil_b;
    }

    public int[] gibFarbe(){
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
        pin_r = null;
        pin_g = null;
        pin_b = null;
    }

    public static void main(String[] args){
        System.out.println("start");
    }
}
