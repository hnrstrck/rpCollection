import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.*;

/**
 * Klasse zum Anschluss einer RGB-LED an den Raspberry Pi. Insgesamt stehen ganz aehnliche Funktionen wie bei der noramlen LED zur Verfuegung (rpDiode) mit dem Zusatz, dass hier die Farbe frei gewaehlt werden kann.
 * 
 * @author Heiner Stroick
 * @version 0.9
 * @see rpDiode
 */
public final class rpRGB {

    private GpioPinDigitalOutput pin_r, pin_g, pin_b;
    private GpioController gpio;

    private boolean boolInitialisierungErfolgt;
    private int[] intPins;
    private int anteil_r, anteil_g, anteil_b;

    /*
     * Damit es nur einen (!) Blinken-Thead gibt.
     */
    private Thread threadEndlosBlinken;

    private boolean blinktGerade = false;

    /**
    * Erstellt ein Objekt der Klasse rpRGB, ohne die Pinne anzugeben.
    */
    rpRGB() {
        gpio = GpioFactory.getInstance();
        intPins = new int[3];
        boolInitialisierungErfolgt = false;
        anteil_r = 0;
        anteil_g = 0;
        anteil_b = 0;
    }

    /**
    * Erstellt ein Objekt der Klasse rpRGB und setzt die Pinne fuer die drei Farben.
    * @param roterPin Der Pin fuer die rote LED.
    * @param gruenerPin Der Pin fuer die gruene LED.
    * @param blauerPin Der Pin fuer die blaue LED.
    */
    rpRGB(int roterPin, int gruenerPin, int blauerPin) {
        gpio = GpioFactory.getInstance();
        intPins = new int[3];
        boolInitialisierungErfolgt = false;
        anteil_r = 0;
        anteil_g = 0;
        anteil_b = 0;

        this.setPins(roterPin, gruenerPin, blauerPin);
    }

    /**
    * Setzt die Pinne fuer die RGB-LED.
    * @param roterPin Der Pin fuer die rote LED.
    * @param gruenerPin Der Pin fuer die gruene LED.
    * @param blauerPin Der Pin fuer die blaue LED.
    */
    public void setPins(int roterPin, int gruenerPin, int blauerPin){
        System.out.println("Setze Pins:");
        setPinRot(roterPin);     
        setPinGruen(gruenerPin);  
        setPinBlau(blauerPin);    
    }

    /**
    * Setzt die Pin fuer die rote Farbe (die rote LED).
    * @param pPin Der Pin fuer die rote LED.
    */
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

    /**
    * Setzt die Pin fuer die gruene Farbe (die gruene LED).
    * @param pPin Der Pin fuer die gruene LED.
    */
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

    /**
    * Setzt die Pin fuer die blaue Farbe (die blaue LED).
    * @param pPin Der Pin fuer die blaue LED.
    */
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

    /**
    * Gibt den Pin der roten LED / der roten Farbe zurueck.
    * @return Pin der RGB-LED fuer die rote Farbe.
    */
    public int gibPinRot(){
        return intPins[0];
    }

    /**
    * Gibt den Pin der gruenen LED / der gruenen Farbe zurueck.
    * @return Pin der RGB-LED fuer die gruene Farbe.
    */
    public int gibPinGruen(){
        return intPins[1];
    }

    /**
    * Gibt den Pin der blauen LED / der blauen Farbe zurueck.
    * @return Pin der RGB-LED fuer die blaue Farbe.
    */
    public int gibPinBlau(){
        return intPins[2];
    }

    /**
    * Schaltet die rote Farbe an.
    */
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

    /**
    * Schaltet die gruene Farbe an.
    */
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

    /**
    * Schaltet die blaue Farbe an.
    */
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

    /**
    * Schaltet die rote Farbe aus.
    */
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

    /**
    * Schaltet die gruene Farbe aus.
    */
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

    /**
    * Schaltet die blaue Farbe aus.
    */
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

    /**
    * Schaltet die RGB-LED an (alle Farben an; volle Leuchtkraft).
    */
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
    
    /**
	* Schalte den die RGB-LED in Abhaengigkeit eines Wertes an oder aus. 
	* @param status Erforderlich ist ein Wahrheitswert (true / false). Ist der Parameterwert true, bleibt die RGB-LED aus. Ist der Parameterwert false, so geht die RGB-LED an. 
	*/
	public void schalten(boolean status){
		if (status == true){
			aus();
		} else {
			an();
		}
	}

    /**
    * Schaltet die RGB-LED aus (alle Farben aus).
    */
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

    /**
    * Setzt eine beliebige Farbe fuer die RGB-LED.
    * @param r Anteil rot (0 &lt;= r &lt;= 255).
    * @param g Anteil gruen (0 &lt;= g &lt;= 255).
    * @param b Anteil blau (0 &lt;= b &lt;= 255).
    */
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
            System.out.println("Falsche Zahlenbereiche angegeben (r, g, b muessen zwischen 0 und 255) oder Fehler bei der Umrechnung vom Prozentsatz zum RGB-Wert.");
        }
    }

    /**
    * Ueberprueft, ob die RGB-LED an ist.
    * @return true oder false, je nach dem, ob die RGB-LED an ist (true = RGB-LED an, false = RGB-LED aus).
    */
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

    /**
    * Ueberprueft, ob die RGB-LED aus ist.
    * @return true oder false, je nach dem, ob die RGB-LED aus ist (true = RGB-LED aus, false = RGB-LED an).
    */
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

    /**
    * Gibt den Anteil der roten Farbe zurueck.
    * @return Anteil der roten Farbe (zwischen 0 und 255).
    */
    public int gibAnteilRot(){
        return anteil_r;
    }

    /**
    * Gibt den Anteil der gruenen Farbe zurueck.
    * @return Anteil der gruenen Farbe (zwischen 0 und 255).
    */
    public int gibAnteilGruen(){
        return anteil_g;
    }

    /**
    * Gibt den Anteil der blauen Farbe zurueck.
    * @return Anteil der blauen Farbe (zwischen 0 und 255).
    */
    public int gibAnteilBlau(){
        return anteil_b;
    }

    /**
    * Gibt ein Array mit allen drei Farben zurueck.
    * @return Array mit drei Eintraegen fuer die Anteile der drei Farben (Rot, Gruen, Blau). Reihenfolge im Array ist auch R, G, B.
    */
    public int[] gibFarbe(){
        int return_array[];

        return_array = new int[3];

        return_array[0] = anteil_r;
        return_array[1] = anteil_g;
        return_array[2] = anteil_b;

        return return_array;
    }

    /**
    * Laesst die RGB-LED kurz blinken.
    */
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

    /**
    * Laesst die RGB-LED blinken (auf unbestimmte Zeit).
    * @param pIntervall Angabe fuer die Blinkfrequenz in Millisekunden fuer die Pulsweitenmodulation der RGB-LED.
    */
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

    /**
    * Laesst die RGB-LED blinken (auf unbestimmte Zeit). Die Frequenz kann ueber die Stellung eines Reglers an einem AD-Wandler angepasst werden.
    * @param meinWandler Objekt der Klasse rpADWandler.
    * @param meinRegler Regler, der ausgelesen werden soll
    * @see rpADWandler
    * @see rpRegler
    * @see rpHelper
    */
    private void startBlinkenVariabel(rpADWandler meinWandler, rpRegler meinRegler) {
        if (boolInitialisierungErfolgt == true){

            threadEndlosBlinken = new Thread(){

                public void run()  {
                    try {

                        int temp_farbe[] = gibFarbe();

                        while(true){
                            //Signal endlos blinken
                            aus();
                            Thread.sleep((int)Math.round(    ((100f - (float)(rpADWandler.gibProzentwertVonRegler(meinRegler,0)))/100f)*300f)      );     
                            setzeFarbe(temp_farbe[0], temp_farbe[1], temp_farbe[2]);
                            Thread.sleep((int)Math.round(    ((100f - (float)(rpADWandler.gibProzentwertVonRegler(meinRegler,0)))/100f)*300f)      );     
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

    /**
    * Laesst die RGB-LED blinken (auf unbestimmte Zeit). 
    */
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

    /**
    * Laesst die RGB-LED blinken (auf unbestimmte Zeit). Die Frequenz kann in Millisekunden angepasst werden.
    * @param pIntervall Regelt die Blinkfrequenz ueber Pulsweitenmodulation (Angabe in Millisekunden).
    */
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

    /**
    * Laesst die RGB-LED blinken (auf unbestimmte Zeit). Die Frequenz kann mit einem AD-Wandler angepasst werden.
    * @param pWandler Objekt der Klasse rpADWandler.
    * @param pRegler Regler, der ausgelesen werden soll.
    * @see rpADWandler
    * @see rpRegler
    * @see rpHelper
    */  
    public void blinkeEndlosStart(rpADWandler pWandler, rpRegler pRegler){
        if (boolInitialisierungErfolgt == true){

            if(!blinktGerade){

                if(pin_r.getState() == PinState.HIGH || pin_g.getState() == PinState.HIGH || pin_b.getState() == PinState.HIGH){
                    startBlinkenVariabel(pWandler, pRegler);
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

    /**
    * Beendet das endlose blinken der RGB-LED.
    */   
    public void blinkeEndlosStop(){
        if (boolInitialisierungErfolgt == true){
            threadEndlosBlinken.interrupt();
            blinktGerade = false;
            aus();
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    /**
    * Schalte GPIO ab und dereferenziere den GPIO und die Pinne (roter Pin, gruener Pin, blauer Pin).
    */  
    public void destroy() {
        gpio.shutdown();
        gpio = null;
        pin_r = null;
        pin_g = null;
        pin_b = null;
    }
}
