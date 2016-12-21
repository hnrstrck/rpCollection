/**
*	Hinweis:
*	Fuer die Abfrage der einzelnen Attributwerte haette auch auf das Objekt "rpDiode" zurueckgegriffen werden koennen.
*	Dann haetten die SuS aber mit zwei Klassen zu tun, was hier vermieden werden sollte, falls dieser Quelltext mal interessieren sollte.
*/

public class Hintergrundbeleuchtung{

	private rpDiode pDiode;
	private Helligkeitssensor bekannterHelligkeitssensor;
	private String standort;
	private boolean status;

	/**
	* Erstelle eine neue Hintergrundbeleuchtung.
	* @param pPin Der Pin der angeschlossenen Hintergrundbeleuchtung.
	* @see Scheinwerfer
	*/
	Hintergrundbeleuchtung(int pPin){
		pDiode = new rpDiode(pPin);
	}
		
	/**
	* Schalte die Hintergrundbeleuchtung an.
	*/
	public void anschalten(){
		pDiode.an();
		setzeStatus(true);
	}
	
	/**
	* Schalte die Hintergrundbeleuchtung aus.
	*/	
	public void ausschalten(){
		pDiode.aus();
		setzeStatus(false);
	}
	
	/**
	* Uebergib der Hintergrundbeleuchtung den Helligkeitssensor.
	* @param pHelligkeitssensor Der Helligkeitssensor, den die Hintergrundbeleuchtung kennen soll.
	*/
	public void setzeHelligkeitssensor(Helligkeitssensor pHelligkeitssensor){
		bekannterHelligkeitssensor = pHelligkeitssensor;
		System.out.println("Helligkeitssensor gesetzt. Die Hintergrundbeleuchtung kennt nun einen Helligkeitssensor.");
	}
	
	/**
	* Schaltet die Hintergrundbeleuchtung an oder aus, je nach dem, was der intern bekannte Helligkeitssensor sagt. Dieser muss zuvor der Hintergrundbeleuchtung bekannt gemacht werden.
	*/
	public void bedingtesAnschalten(){
		schalten(bekannterHelligkeitssensor.befragenIstHell());
	}
	
	/**
	* Schalte die Hintergrundbeleuchtung mit der Rueckgabe eines anderen Objekts. 
	* @param status Erforderlich ist ein Wahrheitswert (true / false). Ist der Parameterwert true, bleibt die Hintergrundbeleuchtung aus. Ist der Parameterwert false, so geht die Hintergrundbeleuchtung an. 
	*/
	public void schalten(boolean status){
		if (status == true){
			ausschalten();
		} else {
			anschalten();
		}
	}
	
	/**
	* Frage nach dem Standort der Hintergrundbeleuchtung.
	* @return Gibt den Standort der Hintergrundbeleuchtung als String zurueck.
	*/
	public String gibStandort(){
		System.out.println("Standort der Hintergrundbeleuchtung ist: " + standort);
		return standort;
	}
	
	/**
	* Setze den Standort der Hintergrundbeleuchtung.
	* @param pStandort Der Standort der Hintergrundbeleuchtung als String.
	*/
	public void setzeStandort(String pStandort){
		System.out.println("Standort der Hintergrundbeleuchtung als >" + pStandort + "< gesetzt");
		standort =  pStandort;
	}
	
	/**
	* Gib den Status der Hintergrundbeleuchtung.
	* @return Der Status der Hintergrundbeleuchtung (an = true / aus = false).
	*/
	public boolean gibStatus(){
		System.out.println("Status der Hintergrundbeleuchtung ist: " + status);
		return status;
	}
	
	/**
	* Setze den Status der Hintergrundbeleuchtung.
	* @param pStatus Der Status der Hintergrundbeleuchtung als String.
	*/
	public void setzeStatus(boolean pStatus){
		System.out.println("Status der Hintergrundbeleuchtung auf >" + pStatus + "< geaendert");
		status =  pStatus;
	}
	
	/**
	* Setze die Hintergrundbeleuchtung ab (der Pin wird freigegeben).
	*/
	public void herunterfahren(){
		pDiode.herunterfahren();
	}
}
