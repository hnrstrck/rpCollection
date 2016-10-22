/**
 * Klasse zur Umsetzung des Theaters. 
 *
 * @see rpDiode
 * 
 * @author Heiner Stroick
 * @version 0.9
 */
public final class rpTheater {

    private rpDiode linkerScheinwerfer;
    private rpDiode rechterScheinwerfer;
    private rpDiode hintergrundbeleuchtung;

	/**
    * Erstelle eine neues Objekt der Klasse rpTheater mit zwei Ampeln.
    */
    rpTheater() {
		linkerScheinwerfer = new rpDiode(11);
		rechterScheinwerfer = new rpDiode(14);
		hintergrundbeleuchtung = new rpDiode(17);
	}

	/**
	* Spiele die erste Szene
	*/
	public void spieleErsteSzene(){
		hintergrundbeleuchtung.an();
		linkerScheinwerfer.an();
		rechterScheinwerfer.an();
		pause(2);
		linkerScheinwerfer.aus();
		pause(2);
		rechterScheinwerfer.aus();
		linkerScheinwerfer.an();
		pause(5);
		linkerScheinwerfer.aus();
		rechterScheinwerfer.aus();
		hintergrundbeleuchtung.aus();
	}


    
    /**
    * Warte die angegebene Zeit
    * @param pPause Zeit in Sekunden, die gewartet werden soll.
    *
    */
    public void pause(int pPause){
		try{
			Thread.sleep(pPause);
		} catch (InterruptedException e) {
			System.out.println("Error: Timeout (InterruptedException)");
		}
    }    
}

