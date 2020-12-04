/**
*	Hinweis:
*	Fuer die Abfrage der einzelnen Attributwerte haette auch auf das Objekt "RPTaster" zurueckgegriffen werden koennen.
*	Dann haetten die SuS aber mit zwei Klassen zu tun, was hier vermieden werden sollte, falls dieser Quelltext mal interessieren sollte.
*/

public class Schalter{

    private RPTaster pTaster;
    private String standort;
    private boolean status;

    /**
    * Erstelle einen neuen Schalter.
    * @param pPin Pin des angeschlossenen Tasters.
    */
    Schalter(int pPin){
        pTaster = new RPTaster(pPin);
    }

    /**
    * Den Schalter befragen.
    * @return Ob der Schalter gedrueckt <b>wird</b> (true = wird gedrueckt, false = wird nicht gedrueckt).
    */
    public boolean istGedrueckt(){
        status = pTaster.istGedrueckt();
        return status;
    }

    /**
    * Fahre den Schalter herunter (der Pin wird freigegeben).
    */
    public void herunterfahren(){
        pTaster.herunterfahren();
    }
}
