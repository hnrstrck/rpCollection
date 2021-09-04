/**
 * Dummy-Klasse fuer den Anschluss eines ADWandlers (MCP 3008, MCP 3208) am Raspberry Pi.<br>
 ** @author Heiner Stroick
 * @version 0.9
 */
public final class RPADWandler {

    private static boolean boolInitialisierungErfolgt;

    /**
    * Erstellt ein neues Objekt der Klasse RPADWandler (die Initialisierung erfolgt automaitsch, es muessen keine Pins angegeben werden).
    */
    public RPADWandler() {
        System.out.println("Initialisierung den Dummy-AD-Wandler...");
    }

    /**
    * Initialisiert den AD-Wandler.
    * @throws InterruptedException Wirft InterruptedException, falls Anschluesse falsch sind (Speed)
    * @throws IOException Wirft IOException, falls Anschluesse falsch sind (Mode)
    */
    public void initialisiere() {
        System.out.println("Initialisierung des Dummy-AD-Wandlers erfolgreich");
    }

    /**
    * Liest den uebergebenen Channel aus (fuer AD-Wandler MCP3008).
    *
    * @param channel Der Channel des AD-Wandlers (0, 1, 2, ..., 7).
    * @return Der gelesene Wert des Channels.
    */
    //Quelle: siehe oben.
    private static int readChannelMCP3008(int channel) {      
        return 0;
    }
    
    
    /**
    * Liest den uebergebenen Channel aus (fuer AD-Wandler MCP3208).
    *
    * @param channel Der Channel des AD-Wandlers (0, 1, 2, ..., 7).
    * @return Der gelesene Wert des Channels.
    */
    //Quelle: siehe oben.
    private static int readChannelMCP3208(int channel) {
        return 0;
    }

	/**
    * Liest den uebergebenen Channel aus (abhaengig vom aktuellen AD-Wandler-Chip)
    *
    * @param channel Der Channel des AD-Wandlers, der ausgelsen werden soll (0, 1, 2, ..., 7).
    * @see Helfer
    */
	private static int readChannel(int channel) {
		return 0;
	}

    /**
    * Liest den uebergebenen Regler aus.
    *
    * @param pRegler Der Regler, der ausgelesen werden soll.
    * @param ausgabe Die Ausgabe in der Shell kann durch Setzen dieses Wertes verhindert / veranlasst werden (1 = Ausgabe, andere Werte = keine Ausgabe).
    * @return Der gelesene Wert des Channels (Achtung: richtigen AD-Wandler auswaehlen).
    * @see Helfer
    */
    public static int gibWertVonRegler(RPRegler pRegler, int ausgabe){
    	return 0;
    }

    /**
    * Liest den uebergebenen Channel aus. Die Ausgabe erfolgt in der Shell.
    *
    * @param pRegler Der Regler, der ausgelesen werden soll.
    * @return Der gelesene Wert des Channels (Achtung: richtigen AD-Wandler auswaehlen).
    * @see Helfer
    */
    public static int gibWertVonRegler(RPRegler pRegler){
        return 0;
    }

    /**
    * Liest den uebergebenen Regler aus und gibt die Stellung in Prozent zurueck (als int, ohne "%", also zum Beispiel 37 fuer eine Stellung von 37%).
    *
    * @param pRegler Der Regler, der ausgelesen werden soll.
    * @param ausgabe Die Ausgabe in der Shell kann durch Setzen dieses Wertes verhindert / veranlasst werden (1 = Ausgabe, andere Werte = keine Ausgabe).
    * @return Der gelesene Wert des Channels in Prozent (Achtung: richtigen AD-Wandler auswaehlen).
    * @see Helfer
    */
    public static int gibProzentwertVonRegler(RPRegler pRegler, int ausgabe){
    	return 0;
    }

    /**
    * Liest den uebergebenen Regler aus und gibt die Stellung in Prozent zurueck (als int, ohne "%", also zum Beispiel 37 fuer eine Stellung von 37%). Die Ausgabe erfolgt in der Shell. 
    *
    * @param pRegler Der Regler, der ausgelesen werden soll.
    * @return Der gelesene Wert des Channels in Prozent (Achtung: richtigen AD-Wandler auswaehlen).
    * @see Helfer
    */
    public static int gibProzentwertVonRegler(RPRegler pRegler){
    	return 0;
    }

    /**
    * Schalte GPIO ab und dereferenziere den GPIO und den Pin.
    */ 
    public void herunterfahren(){
    }

}
