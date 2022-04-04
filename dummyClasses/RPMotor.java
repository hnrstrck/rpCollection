/**
 * Dummy Klasse zum Anschluss eines Fischertechnik-Motors am Raspberry Pi. Der Motor kann auf unbestimmte Zeit laufen oder nur fuer ein paar Sekunden. Zudem kann die Laufrichtung geaendert werden (waehrend der Motor laeuft).
 * 
 * @author Heiner Stroick
 * @version 0.9
 */
public final class RPMotor {

    private boolean boolInitialisierungErfolgt;
    private int[] intPins;
    private boolean laeuftGerade = false;
    private int richtung = 0;
    
    /**
    * Erstellt ein Objekt der Klasse RPMotor, ohne die Pinne anzugeben.
    */
    public RPMotor() {
        intPins = new int[3];
        boolInitialisierungErfolgt = false;
    }

    /**
    * Erstellt ein Objekt der Klasse RPMotor.
    * @param pinEnable Der Pin fuer Enable.
    * @param pinRichtung1 Der Pin fuer Richtung 1.
    * @param pinRichtung2 Der Pin fuer Richtung2.
    */
    RPMotor(int pinEnable, int pinRichtung1, int pinRichtung2) {
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
        System.out.println("Output-Pin gesetzt fuer Enable 1 (EN 1):");
        intPins[0] = pPin;
    }

    /**
    * Setzt den Pin fuer Richtung 1.
    * @param pPin Der Pin fuer Richtung 1.
    */
    public void setPinRichtung1(int pPin) {
        System.out.println("Output-Pin gesetzt fuer Input 1.1 (Richtung 1):");
        intPins[1] = pPin;
    }

    /**
    * Setzt den Pin fuer Richtung2.
    * @param pPin Der Pin fuer Richtung2.
    */
    public void setPinRichtung2(int pPin) {
        System.out.println("Output-Pin gesetzt fuer Input 1.2 (Richtung 2):");
        boolInitialisierungErfolgt = true;
        intPins[2] = pPin;
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
    		this.laeuftGerade = true;
    		System.out.println("Motor läuft");
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
    		this.laeuftGerade = true;
    		System.out.println("Motor läuft");
            try {
	            Thread.sleep(pIntervall * 1000);
	            Thread.sleep(20);
	        } catch (InterruptedException e) {
	        	
	        }
    		System.out.println("Motor beendet");
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
                if (this.richtung != 0){
            		this.laeuftGerade = true;
            		System.out.println("Motor läuft");
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
                if (this.richtung != 0){
            		this.laeuftGerade = true;
            		System.out.println("Motor läuft");
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
                if (this.richtung != 0){
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
                if (this.richtung != 0){
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
    		this.laeuftGerade = false;
    		System.out.println("Motor beendet");
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
                	this.richtung = -1;
                } else {
                	this.richtung = 1;
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
        	if (this.richtung != 0) {
        		this.richtung = - this.richtung;
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
        	return this.laeuftGerade;
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
        	return !this.laeuftGerade;
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
        	return this.richtung == -1;
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
        	return this.richtung == 1;
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (EN 1, Richtung 1, Richtung 2)");
            return false;
        }    
    } 

    /**
    * Schalte GPIO ab und dereferenziere den GPIO und die Pins (Enable, Richtung 1, Richtung2).
    */  
    public void herunterfahren() {
    }
}
