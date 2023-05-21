/**
*   Die Klasse Knopf wird fuer die Modellierung und Umsetzung des Theaterstuecks benoetigt.
*   Es repraesentiert ein Knopf, der an den Raspberry Pi angeschlossenen werden kann.
*
*	Hinweis:
*	Fuer die Abfrage der einzelnen Attributwerte haette auch auf das Objekt "RPTaster" zurueckgegriffen werden koennen.
*	Dann haetten die SuS aber mit zwei Klassen zu tun, was hier vermieden werden sollte, falls dieser Quelltext mal interessieren sollte.
*/

public class Knopf{

    private RPTaster pTaster;
    private String standort;
    private boolean status;

    /**
    * Erstelle einen neuen Knopf.
    * @param pin Pin des angeschlossenen Tasters.
    */
    public Knopf(int pin){
        pTaster = new RPTaster(pin);
    }

    /**
    * Den Knopf befragen.
    * @return Ob der Knopf gedrueckt <b>wird</b> (true = wird gedrueckt, false = wird nicht gedrueckt).
    */
    public boolean istGedrueckt(){
        status = pTaster.istGedrueckt();
        return status;
    }
}
