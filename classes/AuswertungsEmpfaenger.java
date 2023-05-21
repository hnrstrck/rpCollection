/**
*   Die Klasse AuswertungsEmpfaenger wird fuer die Modellierung und Umsetzung des Theaterstuecks benoetigt.
*   Sie repraesentiert den Empfaenger der Auswertungen, die durch Drehknöpfe realisiert werden.
*
*	Hinweis:
*	Fuer die Abfrage der einzelnen Attributwerte haette auch auf das Objekt "RPADWandler" zurueckgegriffen werden koennen.
*	Dann haetten die SuS aber mit zwei Klassen zu tun, was hier vermieden werden sollte, falls dieser Quelltext mal interessieren sollte.
*/

public class AuswertungsEmpfaenger{
    private RPADWandler adWandler;

    /**
    * Erstellt ein AuswertungsEmpfaenger-Objekt. Pins müssen nicht angegeben werden, da das Modul über SPI angesteuert wird.
    */
    public AuswertungsEmpfaenger() {
        this.adWandler= new RPADWandler();
    }

    /**
    * Liefert ein Drehknopf-Objekt zurueck
    *
    * @param drehknopfNummer Die Nummer des Drehknopf zwischen 0 und 3
    * @return Das Drehknopf-Objekt zum gewaehlten Drehknopf
    */
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
