import com.pi4j.context.Context;
import com.pi4j.io.pwm.Pwm;
import com.pi4j.io.pwm.PwmConfig;
import com.pi4j.io.pwm.PwmConfigBuilder;
import com.pi4j.io.pwm.PwmType;

/**
 * Klasse zum Anschluss einer Diode an den Raspberry Pi, deren Helligkeit angegeben werden kann. Die Diode kann an- und ausgeschaltet werden, ihre Helligkeit ändern, blinken und auch in ihrer Blinkfrequenz geändert werden (auch mit einem AD-Wandler).
 *
 * @author Johannes Pieper
 * @version 2.0
 */
public final class RPPwmDiode {

    private Pwm pwm = null;
    private boolean boolInitialisierungErfolgt = false;
    private int intPin = 0;
    private int helligkeit = 100;

    /*
     * Damit es nur einen (!) Blinken-Thead gibt.
     */
    private Thread threadEndlosBlinken;

    private boolean blinktGerade = false;

    /**
    * Erstellt ein Objekt der Klasse RPDiode, ohne einen Pin anzugeben.
    */
    public RPPwmDiode() {
    }

    /**
    * Erstellt ein Objekt der Klasse RPDiode.
    * @param pPin Der Pin, an dem die Diode angeschlossen ist.
    */
    RPPwmDiode(int pPin) {
        this.setPin(pPin);
    }

    /**
    * Setzt den Pin fuer die Diode.
    * @param pin Der Pin, an dem die Diode angeschlossen ist.
    */
    public void setPin(int pin) {
        if (this.pwm == null) {
            Context pi4j = RPEnvironment.getContext();
            PwmConfigBuilder pwmConfigBuilder = RPEnvironment.getPwmConfig();
            this.pwm = pi4j.create(pwmConfigBuilder
                .address(pin)
                .id("pin" + pin)
            );
            System.out.println("Output-Pin gesetzt:");
            boolInitialisierungErfolgt = true;
            intPin = pin;
        } else {
            System.out.println("Output-Pin bereits gesetzt:");
        }
    }

    /**
    * Setzt die Helligkeit der Diode
    * @param helligkeit Helligkeitswert zwischen 0 und 100
    */
    public void setzeHelligkeit(int helligkeit) {
        this.helligkeit = helligkeit;
        if (this.istAn()) {
            this.an();
        }
    }


    /**
    * Gibt den Pin der Diode zurueck.
    * @return Pin der Diode
    */
    public int gibPin() {
        return intPin;
    }

    /**
    * Gibt die Helligkeit der Diode zurueck.
    * @return Helligkeit der Diode
    */
    public int gibHelligkeit() {
        return helligkeit;
    }

    /**
    * Schaltet die Diode an.
    */
    public void an() {
        if (boolInitialisierungErfolgt == true){
            try{
                this.pwm.on(this.helligkeit);
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }

    /**
    * Schaltet die Diode mit einer angegebenen Helligkeit an.
    */
    public void an(int helligkeit) {
        this.helligkeit = helligkeit;
        this.an();
    }


	/**
	* Schalte den die Diode in Abhaengigkeit eines Wertes an oder aus.
	* @param status Erforderlich ist ein Wahrheitswert (true / false). Ist der Parameterwert true, bleibt die Diode aus. Ist der Parameterwert false, so geht die Diode an.
	*/
	public void schalten(boolean status){
		if (status == true){
			aus();
		} else {
			an();
		}
	}

    /**
    * Schaltet die Diode aus.
    */
    public void aus() {
        if (boolInitialisierungErfolgt == true){
            try{
                this.pwm.off();
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }

    /**
    * Schaltet die Diode an oder aus, je nach dem, was vorher vorlag (toogle).
    */
    public void wechsel() {
        if (boolInitialisierungErfolgt == true){
            try{
                this.pwm.toggle();
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }

    /**
    * Ueberprueft, ob die Diode an ist.
    * @return true oder false, je nach dem, ob die Diode an ist (true = Diode an, false = Diode aus).
    */
    public boolean istAn() {
        if (boolInitialisierungErfolgt == true){
            return this.pwm.isOn();
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
            return false;
        }
    }

    /**
    * Ueberprueft, ob die Diode aus ist.
    * @return true oder false, je nach dem, ob die Diode aus ist (true = Diode aus, false = Diode an).
    */
    public boolean istAus() {
        if (boolInitialisierungErfolgt == true){
            return !this.pwm.isOn();
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
            return false;
        }
    }


    /**
    * Laesst die Diode fuer kurze Zeit blinken.
    */
    public void blinke() {
        if (boolInitialisierungErfolgt == true){
            try{
                for (int i = 0; i <= 5; i++) {
                    this.pwm.toggle();
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

    /**
    * Laesst die Diode blinken (auf unbestimmte Zeit). Die Frequenz kann in Millisekunden angepasst werden.
    * @param pIntervall Regelt die Blinkfrequenz ueber Pulsweitenmodulation (Angabe in Millisekunden).
    */
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

    /**
    * Laesst die Diode blinken (auf unbestimmte Zeit). Die Frequenz kann mit einem AD-Wandler angepasst werden.
    * @param meinWandler Objekt der Klasse RPADWandler.
    * @param meinRegler Regler, der ausgelesen werden soll.
    * @see RPADWandler
    * @see RPRegler
    * @see Helfer
    * /
    private void startBlinkenVariabel(RPADWandler meinWandler, RPRegler meinRegler) {
        if (boolInitialisierungErfolgt == true){

            threadEndlosBlinken = new Thread(){

                public void run()  {
                    try {

                        while(true){

                            //Signal endlos blinken
                            an();
                            Thread.sleep((int)Math.round(((100f - (float)(RPADWandler.gibProzentwertVonRegler(meinRegler,0)))/100f)*300f));
                            aus();
                            Thread.sleep((int)Math.round(((100f - (float)(RPADWandler.gibProzentwertVonRegler(meinRegler,0)))/100f)*300f));

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

    /**
    * Laesst die Diode blinken (auf unbestimmte Zeit). Die Frequenz ist voreingestellt (200 Millisekunden).
    */
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

    /**
    * Laesst die Diode blinken (auf unbestimmte Zeit). Die Frequenz kann in Millisekunden angepasst werden.
    * @param pIntervall Regelt die Blinkfrequenz ueber Pulsweitenmodulation (Angabe in Millisekunden).
    */
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

    /**
    * Laesst die Diode blinken (auf unbestimmte Zeit). Die Frequenz kann ueber die Stellung eines Reglers an einem AD-Wandler angepasst werden.
    * @see RPADWandler
    * @param pWandler Objekt der Klasse RPADWandler.
    * @param pRegler Regler, der ausgelesen werden soll.
    * @see RPADWandler
    * @see RPRegler
    * @see Helfer
    */
/*    public void blinkeEndlosStart(RPADWandler pWandler, RPRegler pRegler){
        if (boolInitialisierungErfolgt == true){

            if(!blinktGerade){
                startBlinkenVariabel(pWandler, pRegler);
                blinktGerade = true;
            } else {
                System.out.println("Diode blinkt schon");
            }

        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }

    }

    /**
    * Beendet das endlose blinken der Diode.
    */
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

    public static void main(String[] args) {
        RPPwmDiode diode = new RPPwmDiode(22);
        diode.an();
        Helfer.warte(1);
        diode.setzeHelligkeit(25);
        Helfer.warte(1);
        diode.aus();
        Helfer.warte(1);
        diode.setzeHelligkeit(50);
        diode.an();
        Helfer.warte(1);
        diode.aus();
    }
}
