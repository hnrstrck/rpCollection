/**
*	Hinweis:
*	Fuer die Abfrage der einzelnen Attributwerte haette auch auf das Objekt "RPADWandler" zurueckgegriffen werden koennen.
*	Dann haetten die SuS aber mit zwei Klassen zu tun, was hier vermieden werden sollte, falls dieser Quelltext mal interessieren sollte.
*/

public class AuswertungsEmpfaenger{
    private RPADWandler adWandler;

    public AuswertungsEmpfaenger() {
        this.adWandler= new RPADWandler();
    }

    public Drehknopf gitDrehknopf(int drehknopfNummer) 
    {
        if (drehknopfNummer >= 0 && drehknopfNummer < 4) {
            return new Drehknopf(this.adWandler, drehknopfNummer);
        } else {
            System.out.println("Nicht vorhandener Drehknopf ausgewählt");
            return null;
        }
    }
}
