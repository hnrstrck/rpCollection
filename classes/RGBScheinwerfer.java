/**
*	Hinweis:
*	Fuer die Abfrage der einzelnen Attributwerte haette auch auf das Objekt "rpRGB" zurueckgegriffen werden koennen.
*	Dann haetten die SuS aber mit zwei Klassen zu tun, was hier vermieden werden sollte, falls dieser Quelltext mal interessieren sollte.
*/

public class RGBScheinwerfer{

	private rpRGB pRGB;
	private String standort;
	private boolean lichtAktiv;
	private int farberot;
	private int farbegruen;
	private int farbeblau;

	/**
	* Erstelle einen neuen RGB-Scheinwerfer.
	* @param pPinRot Der Pin fuer die rote Farbe.
	* @param pPinGruen Der Pin fuer die gruene Farbe.
	* @param pPinBlau Der Pin fuer die blaue Farbe.
	*/
	RGBScheinwerfer(int pPinRot, int pPinGruen, int pPinBlau){
		pRGB = new rpRGB(pPinRot, pPinGruen, pPinBlau);
	}
	
	/**
	* Schalte den RGB-Scheinwerfer ein, indem die angegbeene Farbe gesetzt wird.
	* @param rot Farbanteil fuer die Farbe rot.
	* @param gruen Farbanteil fuer die Farbe gruen.
	* @param blau Farbanteil fuer die Farbe blau.
	*/
	public void einschalten(int rot, int gruen, int blau){
		mischen(rot, gruen, blau);
	}
	
	/**
	* Stelle eine neue Farbe ein.
	* @param rot Farbanteil fuer die Farbe rot.
	* @param gruen Farbanteil fuer die Farbe gruen.
	* @param blau Farbanteil fuer die Farbe blau.
	*/
	public void mischen(int rot, int gruen, int blau){
		int r = (int)((float)rot/100f * 255f); 
		int g = (int)((float)gruen/100f * 255f); 
		int b = (int)((float)blau/100f * 255f); 

		pRGB.setzeFarbe(r,g,b);
		
		farberot = rot;
		farbegruen = gruen;
		farbeblau = blau;
		
		setzeStatus(true);
	}
	
	/**
	* Schalte den RGBScheinwerfer mit der Rueckgabe eines anderen Objekts. 
	* @param status Erforderlich ist ein Wahrheitswert (true / false). Ist der Parameterwert true, bleibt der RGBScheinwerfer aus. Ist der Parameterwert false, so geht der RGBScheinwerfer an. 
	*/
	public void schalten(boolean status){
		if (status == true){
			ausschalten();
		} else {
			einschalten(100,100,100);
		}
	}
	
	/**
	* Schalte den RGB-Scheinwerfer aus.
	*/
	public void ausschalten(){
		pRGB.aus();
		setzeStatus(false);
	}
	
	/**
	* Frage nach dem Standort des RGB-Scheinwerfers.
	* @return Gibt den Standort des RGB-Scheinwerfers als String zurueck.
	*/
	public String gibStandort(){
		System.out.println("Standort des RGB-Scheinwerfers ist: " + standort);
		return standort;
	}
	
	/**
	* Setze den Standort des RGB-Scheinwerfers.
	* @param pStandort Der Standort des RGB-Scheinwerfers als String.
	*/
	public void setzeStandort(String pStandort){
		System.out.println("Standort des RGB-Scheinwerfers als >" + pStandort + "< gesetzt");
		standort =  pStandort;
	}
	
	/**
	* Gib den Licht-Aktiv-Status des RGB-Scheinwerfers.
	* @return Der Status des RGB-Scheinwerfers (an = true / aus = false).
	*/
	public boolean gibStatus(){
		System.out.println("Licht-Aktiv-Status des RGB-Scheinwerfers ist: " + lichtAktiv);
		return lichtAktiv;
	}
	
	/**
	* Setze den  Licht-Aktiv-Status des Scheinwerfers.
	* @param pStatus Der  Licht-Aktiv-Status des RGB-Scheinwerfers als String.
	*/
	public void setzeStatus(boolean pStatus){
		System.out.println("Licht-Aktiv-Status des RGB-Scheinwerfers auf >" + pStatus + "< geaendert");
		lichtAktiv =  pStatus;
	}
	
	/**
	* Gibt den Anteil der Farbe rot zurueck.
	* @return Anteil der Farbe rot.
	*/
	public int gibFarbeRot(){
		return farberot;
	}
	
	/**
	* Gibt den Anteil der Farbe gruen zurueck.
	* @return Anteil der Farbe gruen.
	*/
	public int gibFarbeGruen(){
		return farbegruen;
	}
	
	/**
	* Gibt den Anteil der Farbe blau zurueck.
	* @return Anteil der Farbe blau.
	*/
	public int gibFarbeBlau(){
		return farbeblau;
	}
	
	/**
	* Setze den RGBScheinwerfer ab (die Pin wird freigegeben).
	*/
	public void herunterfahren(){
		pRGB.herunterfahren();
	}
}
