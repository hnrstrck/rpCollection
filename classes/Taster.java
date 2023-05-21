/**
*   Die Klasse Taster wird fuer die Modellierung und Umsetzung des Theaterstuecks benoetigt.
*	Hinweis:
*	Fuer die Abfrage der einzelnen Attributwerte haette auch auf das Objekt "RPTaster" zurueckgegriffen werden koennen.
*	Dann haetten die SuS aber mit zwei Klassen zu tun, was hier vermieden werden sollte, falls dieser Quelltext mal interessieren sollte.
*/

public class Taster{

    private RPTaster pTaster;
    private String standort;
    private boolean status;

    /**
    * Erstelle einen neuen Taster.
    * @param pPin Pin des angeschlossenen Tasters.
    */
    Taster(int pPin){
        pTaster = new RPTaster(pPin);
    }

    /**
    * Schalte den Taster ein.
    */
    public void einschalten(){
        System.out.println("Taster eingeschaltet.");
    }

    /**
    * Schalte den Taster aus.
    */
    public void ausschalten(){
        System.out.println("Taster ausgeschaltet.");
    }

    /**
    * Den Taster befragen.
    * @return Ob der Taster gedrueckt <b>wird</b> (true = wird gedrueckt, false = wird nicht gedrueckt).
    */
    public boolean befragenIstGedrueckt(){
        status = pTaster.istGedrueckt();
        return gibStatus();
    }

    /**
    * Frage nach dem Standort des Tasters.
    * @return Gibt den Standort des Tasters als String zurueck.
    */
    public String gibStandort(){
        System.out.println("Standort des Tasters ist: " + standort);
        return standort;
    }

    /**
    * Setze den Standort des Tasters.
    * @param pStandort Der Standort des Tasters als String.
    */
    public void setzeStandort(String pStandort){
        System.out.println("Standort des Tasters als >" + standort + "< gesetzt");
        standort =  pStandort;
    }

    /**
    * Gib den Status des Tasters
    * @return Der Status der Hintergrundbeleuchtung (gedrueckt = true / nicht gedrueckt = false).
    */
    public boolean gibStatus(){
        status = pTaster.istGedrueckt();
        System.out.println("Status des Tasters ist: " + status);
        return status;
    }
}
