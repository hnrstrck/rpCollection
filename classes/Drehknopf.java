/**
*	Hinweis:
*	Fuer die Abfrage der einzelnen Attributwerte haette auch auf das Objekt "RPRegler" zurueckgegriffen werden koennen.
*	Dann haetten die SuS aber mit zwei Klassen zu tun, was hier vermieden werden sollte, falls dieser Quelltext mal interessieren sollte.
*/

public class Drehknopf{

    private RPADWandler adwandler;
    private int channel;
    private RPRegler regler;

    /**
    * Erstelle einen neuen Drehknopf.
    * @param adwandler Der zugehörige AD-Wandler.
    * @param channel Die Nummer des Reglers
    */
    Drehknopf(RPADWandler adwandler, int channel){
        this.adwandler = adwandler;
        this.channel = channel;
        this.regler = new RPRegler(this.channel);
    }

    /**
    * Den Drehknopf befragen.
    * @return Den Wert des Drehknopfs in Prozent
    */
    public int auslesen(){
        return this.adwandler.gibProzentwertVonRegler(this.regler);
    }

    /**
    * Fahre den Drehknopf herunter (Ohne Funktion)
    */
    public void herunterfahren(){
    }
}
