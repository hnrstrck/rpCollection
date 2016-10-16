import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.*;

public final class rpRGB {

    private GpioPinDigitalOutput pin_r, pin_g, pin_b;
    private GpioController gpio;

    private boolean boolInitialisierungErfolgt;
    private int[] intPins;
    private int anteil_r, anteil_g, anteil_b;

    /*
     * Damit es nur einen (!) Blinken-Thead gibt
     */
    private Thread threadEndlosBlinken;

    private boolean blinktGerade = false;

    rpRGB() {
        gpio = GpioFactory.getInstance();
        intPins = new int[3];
        boolInitialisierungErfolgt = false;
        anteil_r = 0;
        anteil_g = 0;
        anteil_b = 0;
    }

    rpRGB(int roterPin, int gruenerPin, int blauerPin) {
        gpio = GpioFactory.getInstance();
        intPins = new int[3];
        boolInitialisierungErfolgt = false;
        anteil_r = 0;
        anteil_g = 0;
        anteil_b = 0;

        this.setPins(roterPin, gruenerPin, blauerPin);
    }

    public void setPins(int roterPin, int gruenerPin, int blauerPin){
        System.out.println("Setze Pins:");
        setPinRot(roterPin);     
        setPinGruen(gruenerPin);  
        setPinBlau(blauerPin);    
    }

    public void setPinRot(int pPin) {
        pin_r = null;

        System.out.println("Output-Pin gesetzt fuer ROT:");

        try {

            if (rpHelper.istBCMLayout == true){
			
				pin_r = gpio.provisionDigitalOutputPin(rpHelper.pinArrayJ8[rpHelper.pinZuordnungBCMzuJ8[pPin]]);
				pin_r.setShutdownOptions(true, PinState.LOW);
			
				SoftPwm.softPwmCreate(rpHelper.pinZuordnungBCMzuJ8[pPin],0,100);
			}
			
			if (rpHelper.istJ8Layout == true){
				
				pin_r = gpio.provisionDigitalOutputPin(rpHelper.pinArrayJ8[pPin]);
				pin_r.setShutdownOptions(true, PinState.LOW);
				
				SoftPwm.softPwmCreate(pPin,0,100);
			}
			
            System.out.println("Pin " + pPin + " gesetzt");
            boolInitialisierungErfolgt = true;

            intPins[0] = pPin;
        } catch (NullPointerException f){
            System.out.println("Error: Pin nicht definiert? (NullPointerException)");
        } catch (com.pi4j.io.gpio.exception.GpioPinExistsException e){
            System.out.println("Error: Pin doppelt definiert? (GpioPinExistsException)");
        }

    }

    public void setPinGruen(int pPin) {
        pin_g = null;

        System.out.println("Output-Pin gesetzt fuer GRUEN:");

        try {

     		if (rpHelper.istBCMLayout == true){
				
				pin_g = gpio.provisionDigitalOutputPin(rpHelper.pinArrayJ8[rpHelper.pinZuordnungBCMzuJ8[pPin]]);
				pin_g.setShutdownOptions(true, PinState.LOW);
	
				
				SoftPwm.softPwmCreate(rpHelper.pinZuordnungBCMzuJ8[pPin],0,100);
			}
			
			if (rpHelper.istJ8Layout == true){	
					
				pin_g = gpio.provisionDigitalOutputPin(rpHelper.pinArrayJ8[pPin]);
				pin_g.setShutdownOptions(true, PinState.LOW);
	
				SoftPwm.softPwmCreate(pPin,0,100);
			}
			
            System.out.println("Pin " + pPin + " gesetzt");
            boolInitialisierungErfolgt = true;

            intPins[1] = pPin;
        } catch (NullPointerException f){
            System.out.println("Error: Pin nicht definiert? (NullPointerException)");
        } catch (com.pi4j.io.gpio.exception.GpioPinExistsException e){
            System.out.println("Error: Pin doppelt definiert? (GpioPinExistsException)");
        }

    }

    public void setPinBlau(int pPin) {
        pin_b = null;

        System.out.println("Output-Pin gesetzt fuer BLAU:");

        try {

			if (rpHelper.istBCMLayout == true){
				
				pin_b = gpio.provisionDigitalOutputPin(rpHelper.pinArrayJ8[rpHelper.pinZuordnungBCMzuJ8[pPin]]);
				pin_b.setShutdownOptions(true, PinState.LOW);
	
				SoftPwm.softPwmCreate(rpHelper.pinZuordnungBCMzuJ8[pPin],0,100);
			}
			
			if (rpHelper.istJ8Layout == true){
				
				pin_b = gpio.provisionDigitalOutputPin(rpHelper.pinArrayJ8[pPin]);
				pin_b.setShutdownOptions(true, PinState.LOW);
	
				SoftPwm.softPwmCreate(pPin,0,100);
			}
			
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
                
                if (rpHelper.istBCMLayout == true){
					SoftPwm.softPwmWrite(rpHelper.pinZuordnungBCMzuJ8[intPins[0]],100);
				}
			
				if (rpHelper.istJ8Layout == true){
					SoftPwm.softPwmWrite(intPins[0],100);
				}
                
                
                anteil_r = 255;
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    public void gruenAn() {
        if (boolInitialisierungErfolgt == true){
            try{
                
                if (rpHelper.istBCMLayout == true){
					SoftPwm.softPwmWrite(rpHelper.pinZuordnungBCMzuJ8[intPins[1]],100);
				}
			
				if (rpHelper.istJ8Layout == true){
					SoftPwm.softPwmWrite(intPins[1],100);
				}
                
                anteil_g = 255;
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    public void blauAn() {
        if (boolInitialisierungErfolgt == true){
            try{
               
                if (rpHelper.istBCMLayout == true){
					SoftPwm.softPwmWrite(rpHelper.pinZuordnungBCMzuJ8[intPins[2]],100);
				}
			
				if (rpHelper.istJ8Layout == true){
					SoftPwm.softPwmWrite(intPins[2],100);
				}
               
                anteil_b = 255;
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    public void rotAus() {
        if (boolInitialisierungErfolgt == true){
            try{
                
                if (rpHelper.istBCMLayout == true){
					SoftPwm.softPwmWrite(rpHelper.pinZuordnungBCMzuJ8[intPins[0]],0);
				}
			
				if (rpHelper.istJ8Layout == true){
					SoftPwm.softPwmWrite(intPins[0],0);
				}
                
                anteil_r = 0;
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    public void gruenAus() {
        if (boolInitialisierungErfolgt == true){
            try{
                
                if (rpHelper.istBCMLayout == true){
					SoftPwm.softPwmWrite(rpHelper.pinZuordnungBCMzuJ8[intPins[1]],0);
				}
			
				if (rpHelper.istJ8Layout == true){
					SoftPwm.softPwmWrite(intPins[1],0);
				}
                
                anteil_g = 0;
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    public void blauAus() {
        if (boolInitialisierungErfolgt == true){
            try{
                
                if (rpHelper.istBCMLayout == true){
					SoftPwm.softPwmWrite(rpHelper.pinZuordnungBCMzuJ8[intPins[2]],0);
				}
			
				if (rpHelper.istJ8Layout == true){
					SoftPwm.softPwmWrite(intPins[2],0);
				}
                
                anteil_b = 0;
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    public void an() {
        if (boolInitialisierungErfolgt == true){
            try{
                rotAn();
				gruenAn();
                blauAn();
                anteil_r = 255;
                anteil_g = 255;
                anteil_b = 255;

            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    public void aus() {
        if (boolInitialisierungErfolgt == true){
            try{
                rotAus();
                gruenAus();
                blauAus();
                anteil_r = 0;
                anteil_g = 0;
                anteil_b = 0;
            } catch (NullPointerException f){
                System.out.println("Error: Pin(s) nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
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
                    
                    
                    if (rpHelper.istBCMLayout == true){
						SoftPwm.softPwmWrite(rpHelper.pinZuordnungBCMzuJ8[intPins[0]],val_r);
						SoftPwm.softPwmWrite(rpHelper.pinZuordnungBCMzuJ8[intPins[1]],val_g);
						SoftPwm.softPwmWrite(rpHelper.pinZuordnungBCMzuJ8[intPins[2]],val_b);
					}
			
					if (rpHelper.istJ8Layout == true){
						SoftPwm.softPwmWrite(intPins[0],val_r);
						SoftPwm.softPwmWrite(intPins[1],val_g);
						SoftPwm.softPwmWrite(intPins[2],val_b);
					}
   
                } catch (NullPointerException f){
                    System.out.println("Error: Pin nicht definiert? (NullPointerException)");
                }
            } else {
                System.out.println("Zuerst Pins fuer die RGB-LED angeben");
            }
        } else {
            System.out.println("Falsche Zahlenbereiche angegeben. r, g, b muessen zwischen 0 und 255 (jew. einschliesslich) liegen");
        }
    }

    public boolean istAn() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (pin_r.getState() == PinState.HIGH || pin_g.getState() == PinState.HIGH || pin_b.getState() == PinState.HIGH ) {
                    System.out.println("Ja, RGB-LED ist an");
                    return true;
                } else {
                    System.out.println("Nein, RGB-LED ist aus");
                    return false; 
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                return false;
            }
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
            return false;
        }    
    }

    public boolean istAus() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (pin_r.getState() == PinState.HIGH || pin_g.getState() == PinState.HIGH || pin_b.getState() == PinState.HIGH ) {
                    System.out.println("Nein, RGB-LED ist an");
                    return false;
                } else {
                    System.out.println("Ja, RGB-LED ist aus");
                    return true; 
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                return false;
            }
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
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

    public void blinke()    {
        if (boolInitialisierungErfolgt == true){
            try{
                int temp_farbe[] = gibFarbe();

                for (int i = 0; i <= 5; i++) {
                    aus();
                    Thread.sleep(200);
                    setzeFarbe(temp_farbe[0], temp_farbe[1], temp_farbe[2]);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                System.out.println("Error: Timeout (InterruptedException)");
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    private void startBlinken(int pIntervall) {
        if (boolInitialisierungErfolgt == true){
            threadEndlosBlinken = new Thread(){

                public void run()  {
                    int temp_farbe[] = gibFarbe();

                    try {

                        while(true){

                            //Signal endlos blinken
                            aus();
                            Thread.sleep(pIntervall);     
                            setzeFarbe(temp_farbe[0], temp_farbe[1], temp_farbe[2]);
                            Thread.sleep(pIntervall); 
                            temp_farbe = gibFarbe();

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
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    private void startBlinkenVariabel(rpADWandler meinWandler, int pChannel) {
        if (boolInitialisierungErfolgt == true){

            threadEndlosBlinken = new Thread(){

                public void run()  {
                    try {

                        int temp_farbe[] = gibFarbe();

                        while(true){
                            //Signal endlos blinken
                            aus();
                            Thread.sleep((int)Math.round(    ((100f - (float)(rpADWandler.gibProzentwertVonChannel(pChannel,0)))/100f)*300f)      );     
                            setzeFarbe(temp_farbe[0], temp_farbe[1], temp_farbe[2]);
                            Thread.sleep((int)Math.round(    ((100f - (float)(rpADWandler.gibProzentwertVonChannel(pChannel,0)))/100f)*300f)      );     
                            temp_farbe = gibFarbe();
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
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    public void blinkeEndlosStart(){
        if (boolInitialisierungErfolgt == true){

            if(!blinktGerade){
               if(pin_r.getState() == PinState.HIGH || pin_g.getState() == PinState.HIGH || pin_b.getState() == PinState.HIGH){
                    startBlinken(200);
                    blinktGerade = true;
                    System.out.println("Blinken gestartet");
                } else {
                    System.out.println("RGB-LED zuerst einschlaten, damit eine Farbe vorhanden ist");
                }          
                
            } else {
                System.out.println("RGB-LED blinkt schon");
            }

        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    public void blinkeEndlosStart(int pIntervall){
        if (boolInitialisierungErfolgt == true){

            if(!blinktGerade){

                if(pin_r.getState() == PinState.HIGH || pin_g.getState() == PinState.HIGH || pin_b.getState() == PinState.HIGH){
                    startBlinken(pIntervall);
                    blinktGerade = true;
                    System.out.println("Blinken gestartet");
                } else {
                    System.out.println("RGB-LED zuerst einschlaten, damit eine Farbe vorhanden ist");
                }

            } else {
                System.out.println("RGB-LED blinkt schon");
            }

        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    public void blinkeEndlosStart(rpADWandler pWandler, int pChannel){
        if (boolInitialisierungErfolgt == true){

            if(!blinktGerade){

                if(pin_r.getState() == PinState.HIGH || pin_g.getState() == PinState.HIGH || pin_b.getState() == PinState.HIGH){
                    startBlinkenVariabel(pWandler, pChannel);
                    blinktGerade = true;
                    System.out.println("Blinken gestartet");
                } else {
                    System.out.println("RGB-LED zuerst einschlaten, damit eine Farbe vorhanden ist");
                }

            } else {
                System.out.println("RGB-LED blinkt schon");
            }
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }

    }

    public void blinkeEndlosStop(){
        if (boolInitialisierungErfolgt == true){
            threadEndlosBlinken.interrupt();
            blinktGerade = false;
            aus();
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    public void destroy() {
        gpio.shutdown();
        gpio = null;
        pin_r = null;
        pin_g = null;
        pin_b = null;
    }
}
