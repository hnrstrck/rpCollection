/**
 * Helferklasse. Stellt die Pin-Belegung bereit und ermoeglicht es, den AD-Wandler zu wechseln.
 * Es koennen auch alle Pinne wieder frei gegeben werden.
 *
 * @author Heiner Stroick
 * @version 0.9
 */
public class Helfer
{
    /**
     * Erstelle ein neues Objekt der Klasse Helfer. Statische Methoden koennnen auch ohne ein erstelltes Objekt aufgerufen werden (bpsw. zum Freigeben der Pinne, Setzen des Pin-Layouts oder zur Auswahl des AD-Wandlers).
     */
    public Helfer(){
		System.out.println("Helferobjekt erstellt.");
    }


	/**
     * Integer-Array mit der Zuordnung: BCM &#8594; J8 (fuer Wiringpi, da dieses bei der Pin-Erstellung einen Integer-Wert fuer den Pin erwartet und keinen Enum RaspiPin.GPIO_xx).
     */
    public static Integer[] pinZuordnungBCMzuJ8 = new Integer[] {
            null,  	//0
            null, 	//1
            8,  	//2
            9,  	//3
            7,  	//4
            21,  	//5
            22,  	//6
            11,  	//7
            10,  	//8
            13,  	//9
            12,  	//10
            14,  	//11
            26,  	//12
            23,  	//13
            15,  	//14
            16,  	//15
            27,  	//16
            0,  	//17
            1,  	//18
            24,  	//19
            28,  	//20
            29,  	//21
            3,  	//22
            4,  	//23
            5,  	//24
            6,  	//25
            25,  	//26
            2,  	//27
            null,  	//28
            null,	//29
            null,   //30
            null,   //31
            null,   //32
            null,   //33
            null,   //34
            null,   //35
            null,   //36
            null,   //37
            null,   //38
            null,   //39
            null,   //40
        };


	/**
     * boolean (true oder false - je nachdem, ob das BCM-Layout verwendet wird).
     */
    public static boolean istBCMLayout = true;

    /**
	 * boolean (true oder false - je nachdem, ob das J8-Layout verwendet wird).
     */
    public static boolean istJ8Layout = false;

    /**
     * Aufloesung des AD-Wandlers
     * Standard-AD-Wandler: MCP 3208 mit 12 Bit Aufloesung = 4096 Aufloesungswerte, also aufloesungADWandler = 4096.
     */
    public static int aufloesungADWandler = 4096;

     /**
	 * boolean (true oder false - je nachdem, ob der MCP3208 eingestellt ist).
     */
    public static boolean istMCP3208 = true;

     /**
	 * boolean (true oder false - je nachdem, ob der MCP3008 eingestellt ist).
     */
    public static boolean istMCP3008 = false;

    /**
     * Aendere das Pin-Layout auf BCM oder J8.
     * @param PinLayout String des Pin-Layouts (muss "BCM" oder "J8" lauten).
     */
    public static void setzePinLayout(String PinLayout){
        if (PinLayout.equals("BCM")){
            System.out.println("Pin Layout auf BCM geaendert");
        } else if (PinLayout.equals("J8")){
            System.out.println("Pin Layout auf J8 geaendert");
        } else {
            System.out.println("Eingabe nicht erkannt. Pin Layout unveraendert.");
        }
    }

    /**
     * Aendere den verwendeten AD-Wandler auf MCP3008 oder MCP3208.
     * @param ADWandlerName String des Namen des AD-Wandlers (muss "MCP3008" oder "MCP3208" lauten).
     */
    public static void setzeADWandler(String ADWandlerName){
        if (ADWandlerName.equals("MCP3008")){
            aufloesungADWandler = 1024;
            istMCP3208 = false;
            istMCP3008 = true;
            System.out.println("AD-Wandler auf MCP3008 geaendert (Aufloesung 1024)");
        } else if (ADWandlerName.equals("MCP3208")){
            aufloesungADWandler = 4096;
            istMCP3208 = true;
            istMCP3008 = false;
            System.out.println("AD-Wandler auf MCP3208 geaendert (Aufloesung 4096)");
        } else {
            System.out.println("Eingabe nicht erkannt. AD-Wandler unveraendert.");
        }
    }

    /**
     * Gib alle Pinne frei und fahre den GPIO herunter.
     * @throws InterruptedException Exception wird geworfen, falls die Methode gewaltsam beendet wird.
     */
    public static void herunterfahren() throws InterruptedException{
        System.out.println("Fahre alles herunter");

        System.out.println("Alles heruntergefahren");
    }

     /**
     * Gib alle Pinne frei und fahre den GPIO herunter.
     * @throws InterruptedException Exception wird geworfen, falls die Methode gewaltsam beendet wird.
     * @see herunterfahren
     */
    public static void freigeben() throws InterruptedException{
		herunterfahren();
	}


	/**
	 * Zeige alle bisher vergebenen Pinne an (<b>Vorsicht:</b> Pinne in J8-Layout).
	 * @throws InterruptedException Exception wird geworfen, falls die Methode gewaltsam beendet wird.
	 */
    public static void pinneAnzeigen() throws InterruptedException{
        System.out.println("Zeige bisherige vergebene Pinne an");

        System.out.println("Ende Anzeigen");
    }

    /**
     * Warte eine bestimmte Zeit in Sekunden;
     * @param pZeit Zeit in Sekunden, die gewartet werden soll.
     */
    public static void warte(int pZeit){
		try{
			Thread.sleep(pZeit * 1000);
		} catch(InterruptedException e){
			System.out.println("Warten abgebrochen!");
		}
	}
}
