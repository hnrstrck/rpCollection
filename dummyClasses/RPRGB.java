/**
 * Dummy Klasse zum Anschluss einer RGB-LED an den Raspberry Pi. Insgesamt stehen ganz aehnliche Funktionen wie bei der noramlen LED zur Verfuegung (RPDiode) mit dem Zusatz, dass hier die Farbe frei gewaehlt werden kann.
 * 
 * @author Heiner Stroick
 * @version 0.9
 * @see RPDiode
 */
public final class RPRGB {
    private boolean boolInitialisierungErfolgt;
    private int[] intPins;
    private int anteil_r, anteil_g, anteil_b;

    private boolean blinktGerade = false;

    /**
    * Erstellt ein Objekt der Klasse RPRGB, ohne die Pinne anzugeben.
    */
    public RPRGB() {
        intPins = new int[3];
        boolInitialisierungErfolgt = false;
        anteil_r = 0;
        anteil_g = 0;
        anteil_b = 0;
    }

    /**
    * Erstellt ein Objekt der Klasse RPRGB und setzt die Pinne fuer die drei Farben.
    * @param roterPin Der Pin fuer die rote LED.
    * @param gruenerPin Der Pin fuer die gruene LED.
    * @param blauerPin Der Pin fuer die blaue LED.
    */
    public RPRGB(int roterPin, int gruenerPin, int blauerPin) {
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
        System.out.println("Output-Pin gesetzt fuer ROT:");
        intPins[0] = pPin;
    }

    /**
    * Setzt die Pin fuer die gruene Farbe (die gruene LED).
    * @param pPin Der Pin fuer die gruene LED.
    */
    public void setPinGruen(int pPin) {
        System.out.println("Output-Pin gesetzt fuer GRUEN:");
        System.out.println("Pin " + pPin + " gesetzt");
        boolInitialisierungErfolgt = true;

        intPins[1] = pPin;
    }

    /**
    * Setzt die Pin fuer die blaue Farbe (die blaue LED).
    * @param pPin Der Pin fuer die blaue LED.
    */
    public void setPinBlau(int pPin) {
        System.out.println("Output-Pin gesetzt fuer BLAU:");
        System.out.println("Pin " + pPin + " gesetzt");
        boolInitialisierungErfolgt = true;
        intPins[2] = pPin;
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
            anteil_r = 255;
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    /**
    * Schaltet die gruene Farbe an.
    */
    public void gruenAn() {
        if (boolInitialisierungErfolgt == true){                
            anteil_g = 255;
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    /**
    * Schaltet die blaue Farbe an.
    */
    public void blauAn() {
        if (boolInitialisierungErfolgt == true){
            anteil_b = 255;
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    /**
    * Schaltet die rote Farbe aus.
    */
    public void rotAus() {
        if (boolInitialisierungErfolgt == true){
            anteil_r = 0;
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    /**
    * Schaltet die gruene Farbe aus.
    */
    public void gruenAus() {
        if (boolInitialisierungErfolgt == true){
            anteil_g = 0;
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    /**
    * Schaltet die blaue Farbe aus.
    */
    public void blauAus() {
        if (boolInitialisierungErfolgt == true){
            anteil_b = 0;
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    /**
    * Schaltet die RGB-LED an (alle Farben an; volle Leuchtkraft).
    */
    public void an() {
        if (boolInitialisierungErfolgt == true){
            rotAn();
			gruenAn();
            blauAn();
            anteil_r = 255;
            anteil_g = 255;
            anteil_b = 255;
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
            rotAus();
            gruenAus();
            blauAus();
            anteil_r = 0;
            anteil_g = 0;
            anteil_b = 0;
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
                anteil_r = r;
                anteil_g = g;
                anteil_b = b;
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
        	return (anteil_r + anteil_g + anteil_r > 0);
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
        	return (anteil_r + anteil_g + anteil_r == 0);
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
            setzeFarbe(10, 10, 10);
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    /**
    * Laesst die RGB-LED blinken (auf unbestimmte Zeit). Die Frequenz kann ueber die Stellung eines Reglers an einem AD-Wandler angepasst werden.
    * @param meinWandler Objekt der Klasse RPADWandler.
    * @param meinRegler Regler, der ausgelesen werden soll
    * @see RPADWandler
    * @see RPRegler
    * @see Helfer
    */
    private void startBlinkenVariabel(RPADWandler meinWandler, RPRegler meinRegler) {
        if (boolInitialisierungErfolgt == true){
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    /**
    * Laesst die RGB-LED blinken (auf unbestimmte Zeit). 
    */
    public void blinkeEndlosStart(){
        if (boolInitialisierungErfolgt == true){
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
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    /**
    * Laesst die RGB-LED blinken (auf unbestimmte Zeit). Die Frequenz kann mit einem AD-Wandler angepasst werden.
    * @param pWandler Objekt der Klasse RPADWandler.
    * @param pRegler Regler, der ausgelesen werden soll.
    * @see RPADWandler
    * @see RPRegler
    * @see Helfer
    */  
    public void blinkeEndlosStart(RPADWandler pWandler, RPRegler pRegler){
        if (boolInitialisierungErfolgt == true){
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }

    }

    /**
    * Beendet das endlose blinken der RGB-LED.
    */   
    public void blinkeEndlosStop(){
        if (boolInitialisierungErfolgt == true){
            aus();
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    /**
    * Schalte GPIO ab und dereferenziere den GPIO und die Pinne (roter Pin, gruener Pin, blauer Pin).
    */  
    public void herunterfahren() {
    }
}
