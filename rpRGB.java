import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.*;

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
        anteil_r = 0;
        anteil_g = 0;
        anteil_b = 0;
    }

    public void setPins(int roterPin, int gruenerPin, int blauerPin){
        System.out.println("Setze Pins:");
        setPinRot(roterPin);     
        setPinGruen(gruenerPin);  
        setPinBlau(blauerPin);    
    }

    private void setPinRot(int pPin) {
        pin_r = null;

        System.out.println("Output-Pin gesetzt fuer ROT:");

        try {

            pin_r = gpio.provisionDigitalOutputPin(pinArray[pPin]);
            pin_r.setShutdownOptions(true, PinState.LOW);

            SoftPwm.softPwmCreate(pPin,0,100);
     
            System.out.println("Pin " + pPin + " gesetzt");
            boolInitialisierungErfolgt = true;

            intPins[0] = pPin;
        } catch (NullPointerException f){
            System.out.println("Error: Pin nicht definiert? (NullPointerException)");
        } catch (com.pi4j.io.gpio.exception.GpioPinExistsException e){
            System.out.println("Error: Pin doppelt definiert? (GpioPinExistsException)");
        }

    }

    private void setPinGruen(int pPin) {
        pin_g = null;

        System.out.println("Output-Pin gesetzt fuer GRUEN:");

        try {

            pin_g = gpio.provisionDigitalOutputPin(pinArray[pPin]);
            pin_g.setShutdownOptions(true, PinState.LOW);

            SoftPwm.softPwmCreate(pPin,0,100);

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

            SoftPwm.softPwmCreate(pPin,0,100);

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

    public int gibPinGruen(){
        return intPins[1];
    }

    public int gibPinBlau(){
        return intPins[2];
    }

    public void rotAn() {
        if (boolInitialisierungErfolgt == true){
            try{
                SoftPwm.softPwmWrite(intPins[0],100);
                anteil_r = 255;
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer die Diode angeben");
        }
    }

    public void gruenAn() {
        if (boolInitialisierungErfolgt == true){
            try{
                SoftPwm.softPwmWrite(intPins[1],100);
                anteil_g = 255;
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
                SoftPwm.softPwmWrite(intPins[2],100);
                anteil_b = 255;
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
                SoftPwm.softPwmWrite(intPins[0],0);
                anteil_r = 0;
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer die Diode angeben");
        }
    }

    public void gruenAus() {
        if (boolInitialisierungErfolgt == true){
            try{
                SoftPwm.softPwmWrite(intPins[1],0);
                anteil_g = 0;
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
                SoftPwm.softPwmWrite(intPins[2],0);
                anteil_b = 0;
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
                SoftPwm.softPwmWrite(intPins[0],100);
                SoftPwm.softPwmWrite(intPins[1],100);
                SoftPwm.softPwmWrite(intPins[2],100);
                anteil_r = 255;
                anteil_g = 255;
                anteil_b = 255;

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
                int temp_farbe[] = gibFarbe();

                for (int i = 0; i <= 5; i++) {
                    aus();
                    Thread.sleep(300);
                    setzeFarbe(temp_farbe[0], temp_farbe[1], temp_farbe[2]);
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
                SoftPwm.softPwmWrite(intPins[0],0);
                SoftPwm.softPwmWrite(intPins[1],0);
                SoftPwm.softPwmWrite(intPins[2],0);
                anteil_r = 0;
                anteil_g = 0;
                anteil_b = 0;
            } catch (NullPointerException f){
                System.out.println("Error: Pin(s) nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer die Diode angeben");
        }
    }

    public void setzeFarbe(int r, int g, int b) {
        if (((b <= 255) && (b >= 0)) && (g <= 255) && (g >= 0) && (b <= 255) && (b >= 0)){
            if (boolInitialisierungErfolgt == true){
                try{

                    anteil_r = r;
                    anteil_g = g;
                    anteil_b = b;

                    int val_r = (int)Math.round(((float)r/255f)*100f);
                    int val_g = (int)Math.round(((float)g/255f)*100f);
                    int val_b = (int)Math.round(((float)b/255f)*100f);

                    SoftPwm.softPwmWrite(intPins[0],val_r);
                    SoftPwm.softPwmWrite(intPins[1],val_g);
                    SoftPwm.softPwmWrite(intPins[2],val_b);

                } catch (NullPointerException f){
                    System.out.println("Error: Pin nicht definiert? (NullPointerException)");
                }
            } else {
                System.out.println("Zuerst Pins fuer die Diode angeben");
            }
        } else {
            System.out.println("Falsche Zahlenbereiche angegeben. r, g, b muessen zwischen 0 und 255 (jew. einschliesslich) liegen");
        }
    }

    public boolean istAn() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (pin_r.getState() == PinState.HIGH || pin_g.getState() == PinState.HIGH || pin_b.getState() == PinState.HIGH ) {
                    System.out.println("Ja, Diode ist an");
                    return true;
                } else {
                    System.out.println("Nein, Diode ist aus");
                    return false; 
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
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
                    System.out.println("Nein, Diode ist an");
                    return true;
                } else {
                    System.out.println("Ja, Diode ist aus");
                    return false; 
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
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

    public int gibAnteilGruen(){
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
}