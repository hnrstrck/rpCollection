import com.pi4j.io.gpio.*;
import java.util.*;

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
 
	//GPIO zum runterfahren
 	private static GpioController gpio;
 
    /**
     * J8-Pin Nummerierung wie auf der Pi4J Website: <a href="http://pi4j.com/pin-numbering-scheme.html">http://pi4j.com/pin-numbering-scheme.html</a>
     */
    public static Pin[] pinArrayJ8 = new Pin[] {
            RaspiPin.GPIO_00,  //0
            RaspiPin.GPIO_01,  //1
            RaspiPin.GPIO_02,  //2
            RaspiPin.GPIO_03,  //3
            RaspiPin.GPIO_04,  //4
            RaspiPin.GPIO_05,  //5
            RaspiPin.GPIO_06,  //6
            RaspiPin.GPIO_07,  //7
            RaspiPin.GPIO_08,  //8
            RaspiPin.GPIO_09,  //9
            RaspiPin.GPIO_10,  //10
            RaspiPin.GPIO_11,  //11
            RaspiPin.GPIO_12,  //12
            RaspiPin.GPIO_13,  //13
            RaspiPin.GPIO_14,  //14
            RaspiPin.GPIO_15,  //15
            RaspiPin.GPIO_16,  //16
            null,    //17
            null,    //18
            null,    //19
            null,    //20
            RaspiPin.GPIO_21,  //21
            RaspiPin.GPIO_22,  //22
            RaspiPin.GPIO_23,  //23
            RaspiPin.GPIO_24,  //24
            RaspiPin.GPIO_25,  //25
            RaspiPin.GPIO_26,  //26
            RaspiPin.GPIO_27,  //27
            RaspiPin.GPIO_28,  //28
            RaspiPin.GPIO_29,  //29
            RaspiPin.GPIO_30,  //30
            RaspiPin.GPIO_31,  //31
            null,  //32
            null,  //33
            null,  //34
            null,  //35
            null,  //36
            null,  //37
            null,  //38
            null,  //39
            null,  //40
        };

    /**
     * BCM-Nummerierung wie auf dem GPIO-Controller gedruckt.
     */
    public static Pin[] pinArrayBCM = new Pin[] {
            null,  //0
            null,  //1
            RaspiPin.GPIO_08,  //2
            RaspiPin.GPIO_09,  //3
            RaspiPin.GPIO_07,  //4
            RaspiPin.GPIO_21,  //5
            RaspiPin.GPIO_22,  //6
            RaspiPin.GPIO_11,  //7
            RaspiPin.GPIO_10,  //8
            RaspiPin.GPIO_13,  //9
            RaspiPin.GPIO_12,  //10
            RaspiPin.GPIO_14,  //11
            RaspiPin.GPIO_26,  //12
            RaspiPin.GPIO_23,  //13
            RaspiPin.GPIO_15,  //14
            RaspiPin.GPIO_16,  //15
            RaspiPin.GPIO_27,  //16
            RaspiPin.GPIO_00,  //17
            RaspiPin.GPIO_01,  //18
            RaspiPin.GPIO_24,  //19
            RaspiPin.GPIO_28,  //20
            RaspiPin.GPIO_29,  //21
            RaspiPin.GPIO_03,  //22
            RaspiPin.GPIO_04,  //23
            RaspiPin.GPIO_05,  //24
            RaspiPin.GPIO_06,  //25
            RaspiPin.GPIO_25,  //26
            RaspiPin.GPIO_02,  //27
            null,  //28
            null,  //29
            null,  //30
            null,  //31
            null,  //32
            null,  //33
            null,  //34
            null,  //35
            null,  //36
            null,  //37
            null,  //38
            null,  //39
            null,  //40
        };

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
     * Fuer den Zugriff von Klassen, die lediglich Pi4J verwenden.
     */
    public static Pin[] pinArray = pinArrayBCM;

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
            pinArray = pinArrayBCM;
            istBCMLayout = true;
            istJ8Layout = false;
            System.out.println("Pin Layout auf BCM geaendert");
        } else if (PinLayout.equals("J8")){
            istBCMLayout = false;
            istJ8Layout = true;
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
	
		gpio = GpioFactory.getInstance();
		
		List<GpioPin> allePinne = new ArrayList<GpioPin>(gpio.getProvisionedPins());
		
		for(int i = 0; i < allePinne.size(); i++){
			try{
				gpio.unprovisionPin(allePinne.get(i));
				Thread.sleep(500);
			} catch (java.lang.NullPointerException e){
				System.out.println("Pin konnte nicht dereferenziert werden");
			}
		}
		
        Thread.sleep(3000);

        System.out.println("Fahre GPIO herunter");

        gpio.shutdown();
        
        Thread.sleep(500);

        System.out.println("Alles heruntergefahren");
    }

	/**
	 * Zeige alle bisher vergebenen Pinne an (<b>Vorsicht:</b> Pinne in J8-Layout).
	 * @throws InterruptedException Exception wird geworfen, falls die Methode gewaltsam beendet wird.
	 */
    public static void pinneAnzeigen() throws InterruptedException{
        System.out.println("Zeige bisherige vergebene Pinne an");
	
		gpio = GpioFactory.getInstance();

		Collection<GpioPin>	allePinne = gpio.getProvisionedPins();
		
		Iterator itor = allePinne.iterator();

		while(itor.hasNext())
		{
			try{
				System.out.println("" + (GpioPin)itor.next());
			} catch (java.lang.NullPointerException e){
				System.out.println("Pin konnte nicht dereferenziert werden");
			}
		}

        System.out.println("Ende Anzeigen");
    }
}
