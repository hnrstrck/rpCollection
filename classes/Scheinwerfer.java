/**
*	Hinweis:
*	Fuer die Abfrage der einzelnen Attributwerte haette auch auf das Objekt "pDiode" zurueckgegriffen werden koennen.
*	Dann haetten die SuS aber mit zwei Klassen zu tun, was hier vermieden werden sollte, falls dieser Quelltext mal interessieren sollte.
*/

public class Scheinwerfer{

	private rpDiode pDiode;
	private String standort;
	private String farbe;
	private boolean status;

	/**
	* Erstelle einen neuen Scheinwerfer.
	* @param pPin Der Pin des angeschlossenen Scheinwerfers.
	*/
	Scheinwerfer(int pPin){
		pDiode = new rpDiode(pPin);
	}

	/**
	* Schalte den Scheinwerfer an.
	*/
	public void anschalten(){
		pDiode.an();
		setzeStatus(true);
	}
	
	/**
	* Laesst den Scheinwerfer kurz blinken. Am Ende ist der Scheinwerfer aus.
	*/
	public void blinken(){
		pDiode.blinke();
		pDiode.aus();
		setzeStatus(true);
	}
	
	/**
	* Schalte den Scheinwerfer aus.
	*/	
	public void ausschalten(){
		pDiode.aus();
		setzeStatus(false);
	}

	/**
	* Schalte den Scheinwerfer mit der Rueckgabe eines anderen Objekts. 
	* @param status Erforderlich ist ein Wahrheitswert (true / false). Ist der Parameterwert true, bleibt der Scheinwerfer aus. Ist der Parameterwert false, so geht der Scheinwerfer an. 
	*/
	/*
	public void schalten(boolean status){
		if (status == true){
			ausschalten();
		} else {
			anschalten();
		}
	}
	*/
	
	/**
	* Frage nach dem Standort des Scheinwerfers.
	* @return Gibt den Standort des Scheinwerfers als String zurueck.
	*/
	public String gibStandort(){
		System.out.println("Standort des Scheinwerfers ist: " + standort);
		return standort;
	}
	
	/**
	* Setze den Standort des Scheinwerfers.
	* @param pStandort Der Standort des Scheinwerfers als String.
	*/
	public void setzeStandort(String pStandort){
		System.out.println("Standort des Scheinwerfers als >" + pStandort + "< gesetzt");
		standort =  pStandort;
	}
	
	/**
	* Frage nach der Farbe des Scheinwerfers.
	* @return Gibt die Farbe des Scheinwerfers als String zurueck.
	*/
	public String gibFarbe(){
		System.out.println("Farbe des Scheinwerfers ist: " + farbe);
		return farbe;
	}
	
	/**
	* Setze die Farbe des Scheinwerfers.
	* @param pFarbe Die Farbe des Scheinwerfers als String.
	*/
	public void setzeFarbe(String pFarbe){
		System.out.println("Farbe des Scheinwerfers als >" + pFarbe + "< gesetzt");
		farbe =  pFarbe;
	}

	/**
	* Gib den Status des Scheinwerfers.
	*/
	public boolean gibStatus(){
		System.out.println("Status des Scheinwerfers ist: " + status);
		return status;
	}
	
	/**
	* Setze den Status des Scheinwerfers.
	* @param pStatus Der Status des Scheinwerfers als String.
	*/
	public void setzeStatus(boolean pStatus){
		System.out.println("Status des Scheinwerfers auf >" + pStatus + "< geaendert");
		status =  pStatus;
	}
}
