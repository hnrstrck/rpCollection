import com.pi4j.context.Context;
import com.pi4j.io.pwm.Pwm;
import com.pi4j.io.pwm.PwmConfig;
import com.pi4j.io.pwm.PwmConfigBuilder;
import com.pi4j.io.pwm.PwmType;
/**
 * Klasse zum Anschluss einer RGB-LED an den Raspberry Pi. Insgesamt stehen ganz aehnliche Funktionen wie bei der normalen LED zur Verfuegung (RPDiode) mit dem Zusatz, dass hier die Farbe frei gewaehlt werden kann.
 *
 * @author Heiner Stroick, Johannes Pieper
 * @version 2.0
 * @see RPDiode
 */
public final class RPRGB {

    private Pwm pwmRed = null;
    private Pwm pwmGreen = null;
    private Pwm pwmBlue = null;

    private boolean boolInitialisierungErfolgt = false;
    private int[] intPins = {0,0,0};
    private int anteil_r = 255;
    private int anteil_g = 255;
    private int anteil_b = 255;

    /*
     * Damit es nur einen (!) Blinken-Thead gibt.
     */
    private Thread threadEndlosBlinken;

    private boolean blinktGerade = false;

    /**
    * Erstellt ein Objekt der Klasse RPRGB, ohne die Pinne anzugeben.
    */
    public RPRGB() {
    }

    /**
    * Erstellt ein Objekt der Klasse RPRGB und setzt die Pinne fuer die drei Farben.
    * @param roterPin Der Pin fuer die rote LED.
    * @param gruenerPin Der Pin fuer die gruene LED.
    * @param blauerPin Der Pin fuer die blaue LED.
    */
    public RPRGB(int roterPin, int gruenerPin, int blauerPin) {
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
    * @param pin Der Pin fuer die rote LED.
    */
    public void setPinRot(int pin) {
        if (this.pwmRed == null) {
            Context pi4j = RPEnvironment.getContext();
            PwmConfigBuilder pwmConfigBuilder = RPEnvironment.getPwmConfig();
            this.pwmRed = pi4j.create(pwmConfigBuilder
                .address(pin)
                .id("pin" + pin)
            );
            System.out.println("ROT-Output-Pin gesetzt:");
            boolInitialisierungErfolgt = true;
            intPins[0] = pin;
        } else {
            System.out.println("Output-Pin bereits gesetzt:");
        }
    }

    /**
    * Setzt die Pin fuer die gruene Farbe (die gruene LED).
    * @param pin Der Pin fuer die gruene LED.
    */
    public void setPinGruen(int pin) {
        if (this.pwmGreen == null) {
            Context pi4j = RPEnvironment.getContext();
            PwmConfigBuilder pwmConfigBuilder = RPEnvironment.getPwmConfig();
            this.pwmGreen = pi4j.create(pwmConfigBuilder
                .address(pin)
                .id("pin" + pin)
            );
            System.out.println("GRUEN-Output-Pin gesetzt:");
            boolInitialisierungErfolgt = true;
            intPins[1] = pin;
        } else {
            System.out.println("Output-Pin bereits gesetzt:");
        }
    }

    /**
    * Setzt die Pin fuer die blaue Farbe (die blaue LED).
    * @param pin Der Pin fuer die blaue LED.
    */
    public void setPinBlau(int pin) {
        if (this.pwmBlue == null) {
            Context pi4j = RPEnvironment.getContext();
            PwmConfigBuilder pwmConfigBuilder = RPEnvironment.getPwmConfig();
            this.pwmBlue = pi4j.create(pwmConfigBuilder
                .address(pin)
                .id("pin" + pin)
            );
            System.out.println("BLAU-Output-Pin gesetzt:");
            boolInitialisierungErfolgt = true;
            intPins[2] = pin;
        } else {
            System.out.println("Output-Pin bereits gesetzt:");
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
        if (pwmRed != null){
            anteil_r = 255;
            pwmRed.on(100);
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    /**
    * Schaltet die gruene Farbe an.
    */
    public void gruenAn() {
        if (pwmGreen != null){
            anteil_g = 255;
            pwmGreen.on(100);
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    /**
    * Schaltet die blaue Farbe an.
    */
    public void blauAn() {
        if (pwmBlue != null){
            anteil_b = 255;
            pwmBlue.on(100);
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    /**
    * Schaltet die rote Farbe aus.
    */
    public void rotAus() {
        if (pwmRed != null){
            anteil_r = 0;
            pwmRed.off();
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    /**
    * Schaltet die gruene Farbe aus.
    */
    public void gruenAus() {
        if (pwmGreen != null){
            anteil_g = 0;
            pwmGreen.off();
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    /**
    * Schaltet die blaue Farbe aus.
    */
    public void blauAus() {
        if (pwmBlue != null){
            anteil_b = 0;
            pwmBlue.off();
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    /**
    * Schaltet die RGB-LED in der gegebenen Farbe an.
    */
    public void an() {
        if (boolInitialisierungErfolgt == true){
            int val_r = (int)Math.round(((float)anteil_r/255f)*100f);
            int val_g = (int)Math.round(((float)anteil_g/255f)*100f);
            int val_b = (int)Math.round(((float)anteil_b/255f)*100f);
            pwmRed.on(val_r);
            pwmGreen.on(val_g);
            pwmBlue.on(val_b);
        } else {
            System.out.println("Zuerst Pins fuer die RGB-LED angeben");
        }
    }

    /**
    * Schaltet die RGB-LED in der angegebenen Farbe an.
    */
    public void an(int r, int g, int b) {
        if (setzeFarbe(r, g, b)) {
            if (boolInitialisierungErfolgt == true){
                int val_r = (int)Math.round(((float)anteil_r/255f)*100f);
                int val_g = (int)Math.round(((float)anteil_g/255f)*100f);
                int val_b = (int)Math.round(((float)anteil_b/255f)*100f);
                pwmRed.on(val_r);
                pwmGreen.on(val_g);
                pwmBlue.on(val_b);
            } else {
                System.out.println("Zuerst Pins fuer die RGB-LED angeben");
            }
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
            pwmRed.off();
            pwmGreen.off();
            pwmBlue.off();
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
    public boolean setzeFarbe(int r, int g, int b) {
        if (((b <= 255) && (b >= 0)) && (g <= 255) && (g >= 0) && (b <= 255) && (b >= 0)){
            anteil_r = r;
            anteil_g = g;
            anteil_b = b;
            if (pwmRed.isOn() || pwmGreen.isOn() || pwmBlue.isOn()) {
                an();
            }
            return true;
        }
        System.out.println("Falsche Zahlenbereiche angegeben (r, g, b muessen zwischen 0 und 255) oder Fehler bei der Umrechnung vom Prozentsatz zum RGB-Wert.");
        return false;
    }

    /**
    * Ueberprueft, ob die RGB-LED an ist.
    * @return true oder false, je nach dem, ob die RGB-LED an ist (true = RGB-LED an, false = RGB-LED aus).
    */
    public boolean istAn() {
        if (boolInitialisierungErfolgt == true){
            if (pwmRed.isOn() || pwmGreen.isOn() || pwmBlue.isOn()) {
                System.out.println("Ja, RGB-LED ist an");
                return true;
            } else {
                System.out.println("Nein, RGB-LED ist aus");
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
            if (pwmRed.isOn() || pwmGreen.isOn() || pwmBlue.isOn()) {
                System.out.println("Nein, RGB-LED ist an");
                return false;
            } else {
                System.out.println("Ja, RGB-LED ist aus");
                return true;
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
                for (int i = 0; i <= 5; i++) {
                    aus();
                    Thread.sleep(200);
                    an();
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
                            an();
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
    * @param meinWandler Objekt der Klasse RPADWandler.
    * @param meinRegler Regler, der ausgelesen werden soll
    * @see RPADWandler
    * @see RPRegler
    * @see Helfer
    */
    private void startBlinkenVariabel(RPADWandler meinWandler, RPRegler meinRegler) {
        if (boolInitialisierungErfolgt == true){

            threadEndlosBlinken = new Thread(){

                public void run()  {
                    try {

                        int temp_farbe[] = gibFarbe();

                        while(true){
                            //Signal endlos blinken
                            aus();
                            Thread.sleep((int)Math.round(    ((100f - (float)(RPADWandler.gibProzentwertVonRegler(meinRegler,0)))/100f)*300f)      );
                            setzeFarbe(temp_farbe[0], temp_farbe[1], temp_farbe[2]);
                            Thread.sleep((int)Math.round(    ((100f - (float)(RPADWandler.gibProzentwertVonRegler(meinRegler,0)))/100f)*300f)      );
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
               if (pwmRed.isOn() || pwmGreen.isOn() || pwmBlue.isOn()) {
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

                if (pwmRed.isOn() || pwmGreen.isOn() || pwmBlue.isOn()) {
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
    * @param pWandler Objekt der Klasse RPADWandler.
    * @param pRegler Regler, der ausgelesen werden soll.
    * @see RPADWandler
    * @see RPRegler
    * @see Helfer
    */
    public void blinkeEndlosStart(RPADWandler pWandler, RPRegler pRegler){
        if (boolInitialisierungErfolgt == true){

            if(!blinktGerade){

                if (pwmRed.isOn() || pwmGreen.isOn() || pwmBlue.isOn()) {
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
    * Test-Methode
    */
    public static void main(String[] args) {
        RPRGB diode = new RPRGB(25, 24, 23);
        diode.setzeFarbe(255,0,0);
        diode.an();
        Helfer.warte(1);
        diode.setzeFarbe(0,255,0);
        Helfer.warte(1);
        diode.setzeFarbe(0,0,255);
        Helfer.warte(1);
        diode.setzeFarbe(125,0,100);
        Helfer.warte(1);
        diode.aus();
        Helfer.warte(1);
        diode.setzeFarbe(255,255,0);
        diode.an();
        Helfer.warte(1);
        diode.aus();
    }
}
