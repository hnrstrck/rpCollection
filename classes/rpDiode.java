import com.pi4j.io.gpio.*;

public final class rpDiode {

    private GpioPinDigitalOutput pin;
    private GpioController gpio;

    private boolean boolInitialisierungErfolgt;
    private int intPin;

    /*
     * Damit es nur einen (!) Blinken-Thead gibt
     */
    public Thread threadEndlosBlinken;

    private boolean blinktGerade = false;

    rpDiode() {
        gpio = GpioFactory.getInstance();
        boolInitialisierungErfolgt = false;
    }

    rpDiode(int pPin) {
        gpio = GpioFactory.getInstance();
        boolInitialisierungErfolgt = false;

        this.setPin(pPin);
    }

    public void setPin(int pPin) {
        pin = null;

        System.out.println("Output-Pin gesetzt:");

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

    public int gibPin() {
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
            System.out.println("Zuerst Pin fuer die Diode angeben");
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
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }

    public void wechsel() {
        if (boolInitialisierungErfolgt == true){
            try{
                pin.toggle();
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }

    public boolean istAn() {
        if (boolInitialisierungErfolgt == true){
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

    public boolean istAus() {
        if (boolInitialisierungErfolgt == true){
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

    
    /*
     * Blinken
     */

    public void blinke() {
        if (boolInitialisierungErfolgt == true){
            try{
                for (int i = 0; i <= 5; i++) {
                    pin.toggle();
                    Thread.sleep(200);
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

    
    private void startBlinken(int pIntervall) {
        if (boolInitialisierungErfolgt == true){
            threadEndlosBlinken = new Thread(){

                public void run()  {
                    try {

                        while(true){

                            //Signal endlos blinken
                            an();
                            Thread.sleep(pIntervall);     
                            aus();
                            Thread.sleep(pIntervall); 

                        }

                    } catch (InterruptedException e) {
                        System.out.println("Blinken beendet");
                        Thread.currentThread().interrupt();
                        return;
                    }

                }
            };
            threadEndlosBlinken.start();
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }

    private void startBlinkenVariabel(rpADWandler meinWandler, int pChannel) {
        if (boolInitialisierungErfolgt == true){

            threadEndlosBlinken = new Thread(){

                public void run()  {
                    try {

                        while(true){

                            //Signal endlos blinken
                            an();
                            Thread.sleep((int)Math.round(    ((100f - (float)(rpADWandler.gibProzentwertVonChannel(pChannel,0)))/100f)*300f)      );     
                            aus();
                            Thread.sleep((int)Math.round(    ((100f - (float)(rpADWandler.gibProzentwertVonChannel(pChannel,0)))/100f)*300f)      );     

                        }
                    } catch (InterruptedException e) {
                        System.out.println("Blinken beendet");
                        Thread.currentThread().interrupt();
                        return;
                    }

                }
            };
            threadEndlosBlinken.start();
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }

    public void blinkeEndlosStart(){
        if (boolInitialisierungErfolgt == true){

            if(!blinktGerade){
                startBlinken(200);
                blinktGerade = true;
            } else {
                System.out.println("Diode blinkt schon");
            }

        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }

    public void blinkeEndlosStart(int pIntervall){
        if (boolInitialisierungErfolgt == true){

            if(!blinktGerade){
                startBlinken(pIntervall);
                blinktGerade = true;
            } else {
                System.out.println("Diode blinkt schon");
            }

        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }

    public void blinkeEndlosStart(rpADWandler pWandler, int pChannel){
        if (boolInitialisierungErfolgt == true){

            if(!blinktGerade){
                startBlinkenVariabel(pWandler, pChannel);
                blinktGerade = true;
            } else {
                System.out.println("Diode blinkt schon");
            }

        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }

    }

    public void blinkeEndlosStop(){
        if (boolInitialisierungErfolgt == true){

            threadEndlosBlinken.interrupt();
            blinktGerade = false;
            aus();
        }
        else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }

    public void destroy() {
        gpio.shutdown();
        gpio = null;
        pin = null;
        boolInitialisierungErfolgt = false;
    }

}
