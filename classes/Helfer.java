import java.util.*;
import com.pi4j.context.Context;

/**
* Helferklasse. Stellt besondere Methoden zur Verfuegung
* und ermoeglicht es, den AD-Wandler zu wechseln.
* Es koennen auch alle Pinne wieder frei gegeben werden.
*
* @author Heiner Stroick, Johannes Pieper
* @version 2.0
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
        try{
            RPEnvironment.herunterfahren();
        } catch (Exception e){
            System.out.println(e);
        }
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