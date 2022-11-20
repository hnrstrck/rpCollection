/**
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
    * @param pPin Pin des angeschlossenen Tasters.
    */
    Knopf(int pPin){
        pTaster = new RPTaster(pPin);
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
