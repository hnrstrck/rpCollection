/**
*   Die Klasse Mischpult wird fuer die Modellierung und Umsetzung des Theaterstuecks benoetigt.
*   Beim Mischpult wird das Modul mit nur drei Regeln angesprochen.
*
*	Hinweis:
*	Fuer die Abfrage der einzelnen Attributwerte haette auch auf das Objekt "RPADWandler" zurueckgegriffen werden koennen.
*	Dann haetten die SuS aber mit zwei Klassen zu tun, was hier vermieden werden sollte, falls dieser Quelltext mal interessieren sollte.
*/

public class Mischpult{
	private RPADWandler adWandler;
	private RPRegler[] regler;

    /**
    * Erzeugung des Mischpult-Objekts. Da dieses mit SPI angesprochen wird, muss kein Pin angegeben werden.
    */
	public Mischpult() {
		regler = new RPRegler[3];
		for (int i = 0; i <3; i++) {
			regler[i] = new RPRegler(i);
		}
		this.adWandler= new RPADWandler();
	}

    /**
    * Auslesen des Wertes eines der Regler (0 bis 2) in Prozent.
    * @param reglerNr Nummer des Reglers (0 bis 2)
    * @return Wert in Prozent
    */
	public int reglerAuslesen(int reglerNr) {
		if (reglerNr < 3) {
			return this.adWandler.gibProzentwertVonRegler(regler[reglerNr]);
		} else {
			System.out.println("Nicht vorhandener Regler ausgewählt");
			return 0;
		}
	}
}