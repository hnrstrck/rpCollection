/**
*	Hinweis:
*	Fuer die Abfrage der einzelnen Attributwerte haette auch auf das Objekt "RPPhototransistor" zurueckgegriffen werden koennen.
*	Dann haetten die SuS aber mit zwei Klassen zu tun, was hier vermieden werden sollte, falls dieser Quelltext mal interessieren sollte.
*/

public class Helligkeitssensor{

	private RPPhototransistor pPhoto;
	private String standort;
	private boolean status;
	
	/**
	* Erstelle einen neuen Helligkeitssensor.
	* @param pPin Pin des angeschlossenen Helligkeitssensors.
	*/
	Helligkeitssensor(int pPin){
		pPhoto = new RPPhototransistor(pPin);
	}
		
	/**
	* Schalte den Helligkeitssensor ein.
	*/
	public void einschalten(){
		System.out.println("Helligkeitssensor eingeschaltet.");
	}
	
	/**
	* Schalte den Helligkeitssensor aus.
	*/	
	public void ausschalten(){
		System.out.println("Helligkeitssensor ausgeschaltet.");
	}
	
	/**
	* Den Helligkeitssensor befragen.
	* @return Lichteinfall (true = Lichteinfall, false = kein Lichteinfall).
	*/
	public boolean befragenIstHell(){
		status = pPhoto.istLichteinfall();
		return gibStatus();		
	}
	
	/**
	* Frage nach dem Standort des Helligkeitssensors.
	* @return Gibt den Standort des Helligkeitssensors als String zurueck.
	*/
	public String gibStandort(){
		System.out.println("Standort des Helligkeitssensors ist: " + standort);
		return standort;
	}
	
	/**
	* Setze den Standort des Helligkeitssensors.
	* @param pStandort Der Standort des Helligkeitssensors als String.
	*/
	public void setzeStandort(String pStandort){
		System.out.println("Standort des Helligkeitssensors als >" + standort + "< gesetzt");
		standort =  pStandort;
	}
	
	/**
	* Gib den Status des Helligkeitssensors (gespeicherter Wert nach der letzten Messung). <b>Kann evtl. abweichen vom tatsaechlichen Status!</b>
	* @return Der Status der Hintergrundbeleuchtung (Lichteinfall = true / kein Lichteinfall = false).
	*/
	public boolean gibStatus(){
		System.out.println("Status des Helligkeitssensors ist: " + status);
		return status;
	}
	
	/**
	* Setze den Status des Helligkeitssensors.
	* @param pStatus Der Status des Helligkeitssensors als Wahrheitswert.
	*/
	public void setzeStatus(boolean pStatus){
		System.out.println("Status des Helligkeitssensors auf >" + status + "< geaendert");
		status =  pStatus;
	}
	
	/**
	* Fahre den Helligkeitssensor herunter (der Pin wird freigegeben).
	*/
	public void herunterfahren(){
		pPhoto.herunterfahren();
	}
}
