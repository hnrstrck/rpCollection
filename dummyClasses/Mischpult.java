/**
*	Hinweis:
*	Fuer die Abfrage der einzelnen Attributwerte haette auch auf das Objekt "RPADWandler" zurueckgegriffen werden koennen.
*	Dann haetten die SuS aber mit zwei Klassen zu tun, was hier vermieden werden sollte, falls dieser Quelltext mal interessieren sollte.
*/

public class Mischpult{
	private RPADWandler adWandler;
	private RPRegler[] regler;

	public Mischpult() {
		regler = new RPRegler[3];
		for (int i = 0; i <3; i++) {
			regler[i] = new RPRegler(i);
		}
		this.adWandler= new RPADWandler();
	}

	public int reglerAuslesen(int reglerNr) {
		if (reglerNr < 3) {
			return this.adWandler.gibProzentwertVonRegler(regler[reglerNr]);
		} else {
			System.out.println("Nicht vorhandener Regler ausgewählt");
			return 0;
		}
	}
}