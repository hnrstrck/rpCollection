import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.*;

/**
 * Klasse zum Anschluss eines Fischertechnik-Motors am Raspberry Pi. Der Motor kann auf unbestimmte Zeit laufen oder nur fuer ein paar Sekunden. Zudem kann die Laufrichtung geaendert werden (waehrend der Motor laeuft).
 * 
 * @author Heiner Stroick
 * @version 0.9
 */
public final class rpMotor {

    private GpioPinDigitalOutput pinEnable, pinRichtung1, pinRichtung2;
    private GpioController gpio;

    private boolean boolInitialisierungErfolgt;
    private int[] intPins;

    /*
     * Damit es nur einen (!) Motor-Thead gibt.
     */
    private Thread threadEndlosMotorlaufen;

    private boolean laeuftGerade = false;
    
    /**
    * Erstellt ein Objekt der Klasse rpMotor, ohne die Pinne anzugeben.
    */
    rpMotor() {
        gpio = GpioFactory.getInstance();
        intPins = new int[3];
        boolInitialisierungErfolgt = false;
    }

    /**
    * Erstellt ein Objekt der Klasse rpMotor.
    * @param pinEnable Der Pin fuer Enable.
    * @param pinRichtung1 Der Pin fuer Richtung 1.
    * @param pinRichtung2 Der Pin fuer Richtung2.
    */
    rpMotor(int pinEnable, int pinRichtung1, int pinRichtung2) {
        gpio = GpioFactory.getInstance();
        intPins = new int[3];
        boolInitialisierungErfolgt = false;

        this.setPins(pinEnable, pinRichtung1, pinRichtung2);
    }

    /**
    * Setzt die Pinne fuer den Motor.
    * @param pinEnable Der Pin fuer Enable.
    * @param pinRichtung1 Der Pin fuer Richtung 1.
    * @param pinRichtung2 Der Pin fuer Richtung2.
    */
    public void setPins(int pinEnable, int pinRichtung1, int pinRichtung2){
        System.out.println("Setze Pins:");
        
        setPinEnable(pinEnable);     
        setPinRichtung1(pinRichtung1);  
        setPinRichtung2(pinRichtung2);    
    }
    
    /**
    * Setzt den Pin fuer Enable.
    * @param pPin Der Pin fuer Enable.
    */
    public void setPinEnable(int pPin) {
        pinEnable = null;

        System.out.println("Output-Pin gesetzt fuer Enable 1 (EN 1):");

        try {
			
			if (rpHelper.istBCMLayout == true){

				pinEnable = gpio.provisionDigitalOutputPin(rpHelper.pinArrayJ8[rpHelper.pinZuordnungBCMzuJ8[pPin]]);
				pinEnable.setShutdownOptions(true, PinState.LOW);

				SoftPwm.softPwmCreate(rpHelper.pinZuordnungBCMzuJ8[pPin],0,100);
            
			}
			
			if (rpHelper.istJ8Layout == true){
			
				pinEnable = gpio.provisionDigitalOutputPin(rpHelper.pinArrayJ8[pPin]);
				pinEnable.setShutdownOptions(true, PinState.LOW);

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
    * Setzt den Pin fuer Richtung 1.
    * @param pPin Der Pin fuer Richtung 1.
    */
    public void setPinRichtung1(int pPin) {
        pinRichtung1 = null;

        System.out.println("Output-Pin gesetzt fuer Input 1.1 (Richtung 1):");

        try {

            if (rpHelper.istBCMLayout == true){

				pinRichtung1 = gpio.provisionDigitalOutputPin(rpHelper.pinArrayJ8[rpHelper.pinZuordnungBCMzuJ8[pPin]]);
				pinRichtung1.setShutdownOptions(true, PinState.LOW);

				SoftPwm.softPwmCreate(rpHelper.pinZuordnungBCMzuJ8[pPin],0,100);
            
			}
			
			if (rpHelper.istJ8Layout == true){
			
				pinRichtung1 = gpio.provisionDigitalOutputPin(rpHelper.pinArrayJ8[pPin]);
				pinRichtung1.setShutdownOptions(true, PinState.LOW);

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
    * Setzt den Pin fuer Richtung2.
    * @param pPin Der Pin fuer Richtung2.
    */
    public void setPinRichtung2(int pPin) {
        pinRichtung2 = null;

        System.out.println("Output-Pin gesetzt fuer Input 1.2 (Richtung 2):");

        try {

            if (rpHelper.istBCMLayout == true){

				pinRichtung2 = gpio.provisionDigitalOutputPin(rpHelper.pinArrayJ8[rpHelper.pinZuordnungBCMzuJ8[pPin]]);
				pinRichtung2.setShutdownOptions(true, PinState.LOW);

				SoftPwm.softPwmCreate(rpHelper.pinZuordnungBCMzuJ8[pPin],0,100);
            
			}
			
			if (rpHelper.istJ8Layout == true){
			
				pinRichtung2 = gpio.provisionDigitalOutputPin(rpHelper.pinArrayJ8[pPin]);
				pinRichtung2.setShutdownOptions(true, PinState.LOW);

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
    * Gibt den Pin des Motors fuer Enable zurueck.
    * @return Pin des Motors fuer Enable.
    */
    public int gibPinEnable(){
        return intPins[0];
    }

    /**
    * Gibt den Pin des Motors fuer Richtung 1 zurueck.
    * @return Pin des Motors fuer Richtung 1.
    */
    public int gibPinRichtung1(){
        return intPins[1];
    }

    /**
    * Gibt den Pin des Motors fuer Richtung2 zurueck.
    * @return Pin des Motors fuer Richtung2. 
    */
    public int gibPinRichtung2(){
        return intPins[2];
    } 

    /**
    * Startet den Motor (auf unbestimmte Zeit).
    * @param pGeschwindigkeit Geschwindigkeit des Motors in Prozent fuer die Pulsweitenmodulation 
    */
    private void starteMotorGeschwindigkeit(int pGeschwindigkeit) {
        if (boolInitialisierungErfolgt == true){

            threadEndlosMotorlaufen = new Thread(){
				
                public void run() {
                    try {
                        
						if (rpHelper.istBCMLayout == true){
							SoftPwm.softPwmWrite(rpHelper.pinZuordnungBCMzuJ8[intPins[0]],pGeschwindigkeit);
            			}
			
						if (rpHelper.istJ8Layout == true){
							SoftPwm.softPwmWrite(intPins[0],pGeschwindigkeit);	
						}
                                                
                        Thread.sleep(15*200);
                    } catch (InterruptedException e) {
                        System.out.println("Motor beendet");
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            };
            
            threadEndlosMotorlaufen.start();

        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
        }
    }

    /**
    * Startet den Motor (auf unbestimmte Zeit).
    * @param pIntervall Zeitspanne (in Sekunden), die der Motor an sein soll.
    * @param pGeschwindigkeit Geschwindigkeit des Motors in Prozent fuer die Pulsweitenmodulation (ungueltige Prozentangaben werden ignoriert).
    */
    private void starteMotorZeitintervallGeschwindigkeit(int pIntervall, int pGeschwindigkeit) {
        if (boolInitialisierungErfolgt == true){

            threadEndlosMotorlaufen = new Thread(){

                public void run() {
                    try {
                        
                        if (rpHelper.istBCMLayout == true){
							SoftPwm.softPwmWrite(rpHelper.pinZuordnungBCMzuJ8[intPins[0]],pGeschwindigkeit);
            			}
			
						if (rpHelper.istJ8Layout == true){
							SoftPwm.softPwmWrite(intPins[0],pGeschwindigkeit);	
						}
                               
                        Thread.sleep(pIntervall * 1000);
                        Thread.sleep(20);
                        throw new InterruptedException(); 
                    } catch (InterruptedException e) {
                        System.out.println("Motor beendet");
                        Thread.currentThread().interrupt();
                        motorstop();
                        return;
                    }
                }
            };

            threadEndlosMotorlaufen.start();

        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
        }
    }

    /**
    * Startet den Motor (auf unbestimmte Zeit) mit voller Geschwindigkeit (100%).
    */
    public void start() {
        if (boolInitialisierungErfolgt == true){

            if(!laeuftGerade){

                if (pinRichtung1.getState() == PinState.HIGH || pinRichtung2.getState() == PinState.HIGH){
                    starteMotorGeschwindigkeit(100);
                    laeuftGerade = true;
                    System.out.println("Motor gestartet");
                } else {
                    System.out.println("Zuerst Laufrichtung angeben");
                }
            } else {
                System.out.println("Motor laeuft schon");
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
        }
    }

    /**
    * Startet den Motor (auf unbestimmte Zeit) mit voller Geschwindigkeit (100%).
    * @param pGeschwindigkeit Geschwindigkeit des Motors in Prozent (ungueltige Prozentangaben werden ignoriert).
    */
    public void start(int pGeschwindigkeit) {
        if (boolInitialisierungErfolgt == true){

            if(!laeuftGerade){
                if (pinRichtung1.getState() == PinState.HIGH || pinRichtung2.getState() == PinState.HIGH){
                    starteMotorGeschwindigkeit(pGeschwindigkeit);
                    laeuftGerade = true;
                    System.out.println("Motor gestartet");
                } else {
                    System.out.println("Zuerst Laufrichtung angeben");
                }
            } else {
                System.out.println("Motor laeuft schon");
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
        }
    }

    /**
    * Startet den Motor mit voller Geschwindigkeit (100%) fuer die angegebene Zeitspanne (in Sekunden).
    * @param pSekunden Zeitspanne (in Sekunden), die der Motor an sein soll.
    */
    public void startZeitintervall(int pSekunden) {
        if (boolInitialisierungErfolgt == true){

            if(!laeuftGerade){
                if (pinRichtung1.getState() == PinState.HIGH || pinRichtung2.getState() == PinState.HIGH){
                    starteMotorZeitintervallGeschwindigkeit(pSekunden, 100);
                    laeuftGerade = true;
                    System.out.println("Motor gestartet");
                } else {
                    System.out.println("Zuerst Laufrichtung angeben");
                }
            } else {
                System.out.println("Motor laeuft schon");
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
        }
    }

    /**
    * Startet den Motor in der angegebenen Geschwindigkeit (in Prozent) fuer die angegebene Zeitspanne (in Sekunden). Ungueltige Prozentangaben werden ignoriert.
    * @param pSekunden Zeitspanne (in Sekunden), die der Motor an sein soll.
    * @param pGeschwindigkeit Geschwindigkeit des Motors in Prozent (ungueltige Prozentangaben werden ignoriert).
    */
    public void startZeitintervall(int pSekunden, int pGeschwindigkeit) {
        if (boolInitialisierungErfolgt == true){

            if(!laeuftGerade){
                if (pinRichtung1.getState() == PinState.HIGH || pinRichtung2.getState() == PinState.HIGH){
                    starteMotorZeitintervallGeschwindigkeit(pSekunden, pGeschwindigkeit);
                    laeuftGerade = true;
                    System.out.println("Motor gestartet");
                } else {
                    System.out.println("Zuerst Laufrichtung angeben");
                }
            } else {
                System.out.println("Motor laeuft schon");
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
        }
    }

    /**
    * Stoppt den Motor.
    */
    public void stop(){
        this.motorstop();
    }

    /**
    * Stoppt den Motor. 
    * Extra Methoden ist notwenig, da innerhalb des Threads nicht this.stop() aufgerufen werden kann (stop() ist schon eine eigene Methode im Thread).
    */
    private void motorstop() {
        if (boolInitialisierungErfolgt == true){
            try{
                threadEndlosMotorlaufen.interrupt();
                
                if (rpHelper.istBCMLayout == true){
					SoftPwm.softPwmWrite(rpHelper.pinZuordnungBCMzuJ8[intPins[0]],0);
            	}
			
				if (rpHelper.istJ8Layout == true){
					SoftPwm.softPwmWrite(intPins[0],0);	
				}
          
                
                laeuftGerade = false;
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
        }
    }

    /**
    * Setzt die Laufrichtung des Motors.
    * @param pRichtung Die Richtung des Motors (0 = links, 1 = rechts).
    */
    public void setzeLaufrichtung(int pRichtung) {
        if (boolInitialisierungErfolgt == true){
            if (pRichtung == 0 || pRichtung == 1){
                if (pRichtung == 0){
                    try{
								
						if (rpHelper.istBCMLayout == true){
							SoftPwm.softPwmWrite(rpHelper.pinZuordnungBCMzuJ8[intPins[1]],100);
							SoftPwm.softPwmWrite(rpHelper.pinZuordnungBCMzuJ8[intPins[2]],0);
						}
			
						if (rpHelper.istJ8Layout == true){
							SoftPwm.softPwmWrite(intPins[1],100);
							SoftPwm.softPwmWrite(intPins[2],0);	
						}		

                    } catch (NullPointerException f){
                        System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                    }
                } else {
                    try{
                        
                        if (rpHelper.istBCMLayout == true){
							SoftPwm.softPwmWrite(rpHelper.pinZuordnungBCMzuJ8[intPins[1]],0);
							SoftPwm.softPwmWrite(rpHelper.pinZuordnungBCMzuJ8[intPins[2]],100);
						}
			
						if (rpHelper.istJ8Layout == true){
							SoftPwm.softPwmWrite(intPins[1],0);
							SoftPwm.softPwmWrite(intPins[2],100);	
						}		


                    } catch (NullPointerException f){
                        System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                    }							
                }
            } else {
                System.out.println("Angabe fuer die Richtung muss 0 oder 1 sein.");
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
        }
    }

    /**
    * Wechselt die Laufrichtung des Motors.
    */
    public void wechselLaufrichtung() {
        if (boolInitialisierungErfolgt == true){
            if (pinRichtung1.getState() == PinState.HIGH || pinRichtung2.getState() == PinState.HIGH){
                if (pinRichtung1.getState() == PinState.HIGH){
                    try{
						
						if (rpHelper.istBCMLayout == true){
							SoftPwm.softPwmWrite(rpHelper.pinZuordnungBCMzuJ8[intPins[1]],0);
							SoftPwm.softPwmWrite(rpHelper.pinZuordnungBCMzuJ8[intPins[2]],100);
						}
			
						if (rpHelper.istJ8Layout == true){
							SoftPwm.softPwmWrite(intPins[1],0);
							SoftPwm.softPwmWrite(intPins[2],100);	
						}	
						
                        System.out.println("Laufrichtung geaendert");
                    } catch (NullPointerException f){
                        System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                    }
                } else {
                    try{
						
                        if (rpHelper.istBCMLayout == true){
							SoftPwm.softPwmWrite(rpHelper.pinZuordnungBCMzuJ8[intPins[1]],100);
							SoftPwm.softPwmWrite(rpHelper.pinZuordnungBCMzuJ8[intPins[2]],0);
						}
			
						if (rpHelper.istJ8Layout == true){
							SoftPwm.softPwmWrite(intPins[1],100);
							SoftPwm.softPwmWrite(intPins[2],0);	
						}		
						
                        System.out.println("Laufrichtung geaendert");
                    } catch (NullPointerException f){
                        System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                    }							
                }
            } else {
                System.out.println("Es wurde noch keine Richtung festgelegt.");
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
        }
    }

    /**
    * Ueberprueft, ob der Motor an ist.
    * @return true oder false, je nach dem, ob der Motor an ist (true = Motor an, false = Motor aus).
    */
    public boolean istAn() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (pinEnable.getState() == PinState.HIGH) {
                    System.out.println("Ja, Motor ist angeschaltet");
                    return true;
                } else {
                    System.out.println("Nein, Motor ist ausgeschaltet");
                    return false; 
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                return false;
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
            return false;
        }    
    }

    /**
    * Ueberprueft, ob der Motor aus ist.
    * @return true oder false, je nach dem, ob der Motor aus ist (true = Motor aus, false = Motor an).
    */
    public boolean istAus() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (pinEnable.getState() == PinState.HIGH) {
                    System.out.println("Nein, Motor ist angeschaltet");
                    return false;
                } else {
                    System.out.println("Ja, Motor ist ausgeschaltet");
                    return true; 
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                return false;
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
            return false;
        }    
    } 

    /**
    * Ueberprueft, ob der Motor gerade nach links laeuft.
    * @return true oder false, je nach dem, ob der Motor gerade nach links laeuft (true = Motor laeuft nach links, false = Motor laeuft nicht oder nicht nach links).
    */
    public boolean hatLaufrichtungLinks() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (pinRichtung1.getState() == PinState.HIGH) {
                    System.out.println("Ja, Motor ist auf Links eingestellts");
                    return true;
                } else {
                    System.out.println("Nein, Motor ist auf Rechts eingestellt (oder hat noch keine Richtung)");
                    return false; 
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                return false;
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (Enable, Richtung 1, Richtung 2)");
            return false;
        }    
    }

    /**
    * Ueberprueft, ob der Motor gerade nach rechts laeuft.
    * @return true oder false, je nach dem, ob der Motor gerade nach rechts laeuft (true = Motor laeuft nach rechts, false = Motor laeuft nicht oder nicht nach rechts).
    */
    public boolean hatLaufrichtungRechts() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (pinRichtung2.getState() == PinState.LOW) {
                    System.out.println("Nein, Motor ist auf Links eingestellt (oder hat noch keine Richtung)");
                    return false;
                } else {
                    System.out.println("Ja, Motor ist auf Rechts eingestellt");
                    return true; 
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                return false;
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (EN 1, Richtung 1, Richtung 2)");
            return false;
        }    
    } 

    /**
    * Schalte GPIO ab und dereferenziere den GPIO und die Pins (Enable, Richtung 1, Richtung2).
    */  
    public void herunterfahren() {
		stop();
        gpio.shutdown();
        gpio.unprovisionPin(pinEnable);
        gpio.unprovisionPin(pinRichtung1);
        gpio.unprovisionPin(pinRichtung2);
    }
}
