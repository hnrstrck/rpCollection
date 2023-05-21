/**
*   Die Klasse Helligkeitssensor wird fuer die Modellierung und Umsetzung des Theaterstuecks benoetigt.
*   Er misst die Helligkeit in Form: Genuegend List oder nicht genuegend Licht.
*
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
	* @param pin Pin des angeschlossenen Helligkeitssensors.
	*/
	Helligkeitssensor(int pin){
		pPhoto = new RPPhototransistor(pin);
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
    * Den Helligkeitssensor befragen.
    * @return Lichteinfall (true = Lichteinfall, false = kein Lichteinfall).
    */
    public boolean befragen(){
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
	* @param standort Der Standort des Helligkeitssensors als String.
	*/
	public void setzeStandort(String standort){
		System.out.println("Standort des Helligkeitssensors als >" + standort + "< gesetzt");
		this.standort =  standort;
	}

	/**
	* Gib den Status des Helligkeitssensors.
	* @return Der Status der Hintergrundbeleuchtung (Lichteinfall = true / kein Lichteinfall = false).
	*/
	public boolean gibStatus(){
        status = pPhoto.istLichteinfall();
		System.out.println("Status des Helligkeitssensors ist: " + status);
		return status;
	}

	/**
	* Gibt zurueck ob es Hell ist.
	* @return Der Status der Hintergrundbeleuchtung (Lichteinfall = true / kein Lichteinfall = false).
	*/
    public boolean istHell() {
        return this.gibStatus();
    }
}