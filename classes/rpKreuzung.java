/**
 * Klasse zur Simulation einer Ampel.
 * In Zukunft: Vererbung: Fussgaengerampel erbt von Ampel, um die Ampel um einen Taster zu erweitern.
 * 
 * @see rpAmpel
 * 
 * @author Heiner Stroick
 * @version 0.9
 */
public final class rpKreuzung {

    private rpAmpel fussgaengerAmpel;
    private rpAmpel autoAmpel;

	//Taster fuer die Fusgaengerampel.
    private rpTaster meinTaster;

    /**
    * Erstelle eine neues Objekt der Klasse Kreuzung mit zwei Ampeln.
    */
    rpKreuzung() {
		fussgaengerAmpel = new rpAmpel(15, 16, 17);
		autoAmpel = new rpAmpel(20, 21, 22);
		initialisiere();
		starteUeberwachung();
	}

	public void initialisiere(){
		autoAmpel.aufGruenSchalten();
		fussgaengerAmpel.aufRotSchalten();
	}

    /**
    * Starte die Kreuzungsueberwachung. Die Ampeln schalten um, wenn ein Taster gedrueckt wird.
    * 
    * @return Der angeschlossene Channel des Reglers.
    */
    public void starteUeberwachung(){
   		
   		//tue nichts, solange der Taster nicht gedrueckt wurde
   		while(meinTaster.istGedrueckt() == false){
   		
   		}
   		
   		//Warte etwas
		try{
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println("Error: Timeout (InterruptedException)");
		}   		
   		gibMenschenGruen();
   		
		try{
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println("Error: Timeout (InterruptedException)");
		}		
		gibAutosGruen();
    }
    
    /**
    * Schalte die Ampel fuer die Autos auf Gruen, die der Menschen auf Rot.
    *
    */
    public void gibAutosGruen(){
		if (autoAmpel.istGeradeRot() == true && fussgaengerAmpel.istGeradeGruen() == true){
			fussgaengerAmpel.aufRotSchalten();
		try{
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("Error: Timeout (InterruptedException)");
		}
			autoAmpel.aufGruenSchalten();
		}
    }
    
	/**
    * Schalte die Ampel fuer die Menschen auf Gruen, die der Autos auf Rot.
    *
    */
	public void gibMenschenGruen(){
		if (autoAmpel.istGeradeGruen() == true && fussgaengerAmpel.istGeradeRot() == true){
			autoAmpel.aufRotSchalten();
		try{
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("Error: Timeout (InterruptedException)");
		}
		fussgaengerAmpel.aufGruenSchalten();
		}
    }
    
}
