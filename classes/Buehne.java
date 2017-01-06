import com.pi4j.io.gpio.*;
import java.util.*;

/**
 * Die Buhne. Es koennen Scheinwerfer der Buehne uebergeben werden, sodass die Buehne diese kennt. Die Buehne braucht auch eine Hintergrundbeleuchtung.
 *
 *
 * @author Heiner Stroick
 * @version 0.9
 */
public class Buehne
{
	private List<Scheinwerfer> alleScheinwerfer;
	private Hintergrundbeleuchtung bekannteHintergrundbeleuchtung;
	
    /**
     * Erstelle eine neue Buehne. Es muss kein Pin angegeben werden.
     */
    public Buehne(){
		System.out.println("Buehne erstellt.");   
		alleScheinwerfer = new ArrayList<Scheinwerfer>();
    }
    
    /**
     * Fuege der Buehne eine Hintergrundbeleuchtung hinzu, sodass die Buehne diese kennt.
     * @param pHintergrundbeleuchtung Die Hintergrundbeleuchtung, die die Buehne kennen soll.
     */    
    public void installiereHintergrundbeleuchtung(Hintergrundbeleuchtung pHintergrundbeleuchtung){
		bekannteHintergrundbeleuchtung = pHintergrundbeleuchtung;
		System.out.println("Hintergrundbeleuchtung hinzugefuegt.");   
    }
    
    /**
     * Fuege der Buehne einen Scheinwerfer hinzu, sodass die Buehne die Scheinwerfer kennt.
     * @param pScheinwerfer Der Scheinwerfer, den die Buehne kennen soll.
     */    
    public void installiereScheinwerfer(Scheinwerfer pScheinwerfer){
		alleScheinwerfer.add(pScheinwerfer);
		System.out.println("Scheinwerfer hinzugefuegt.");   
    }
     
    /**
     * Schalte alle Scheinwerfer aus, die die Buehne kennt. 
     * @throws InterruptedException Exception wird geworfen, falls die Methode gewaltsam beendet wird.
     */    
    public void alleScheinwerferAusschalten() throws InterruptedException{
        System.out.println("Schalte alles aus!");
				
		for(int i = 0; i < alleScheinwerfer.size(); i++){
			try{
				alleScheinwerfer.get(i).ausschalten();
				Thread.sleep(100);
			} catch (java.lang.NullPointerException e){
				System.out.println("Scheinwerfer konnte nicht ausgeschaltet werden.");
			}
		}
		
        Thread.sleep(500);
        
        try{
			bekannteHintergrundbeleuchtung.ausschalten();
		}catch (java.lang.NullPointerException e){
			System.out.println("Hintergrundbeleuchtung konnte nicht angeschaltet werden.");
		}
		
     	System.out.println("Alles ausgeschaltet.");
    }
    
    /**
     * Schalte alle Scheinwerfer an, die die Buehne kennt. 
     * @throws InterruptedException Exception wird geworfen, falls die Methode gewaltsam beendet wird.
     */    
    public void alleScheinwerferAnschalten() throws InterruptedException{
        System.out.println("Schalte alles an!");
				
		for(int i = 0; i < alleScheinwerfer.size(); i++){
			try{
				alleScheinwerfer.get(i).anschalten();
				Thread.sleep(100);
			} catch (java.lang.NullPointerException e){
				System.out.println("Scheinwerfer konnte nicht angeschaltet werden.");
			}
		}
		
        Thread.sleep(500);
        
        try{
			bekannteHintergrundbeleuchtung.anschalten();
		}catch (java.lang.NullPointerException e){
			System.out.println("Hintergrundbeleuchtung konnte nicht angeschaltet werden.");
		}
		
     	System.out.println("Alles angeschaltet.");
    }	
}
