/**
 * Klasse fuer eine Ampel (mit drei Lampen: rot, gelb, gruen).
 * 
 * @see rpDiode
 * 
 * @author Heiner Stroick
 * @version 0.9
 */
public final class rpAmpel {

	private rpDiode rotesLicht;
	private rpDiode gelbesLicht;
	private rpDiode gruenesLicht;
	
	private boolean istGeradeRot;
	private boolean istGeradeGruen;

    /**
    * Erstelle eine neue Ampel und setze die Pinne der angeschlossenen Dioden (Rot, Gelb, Gruen).
    */
    rpAmpel(int pinRotesLicht, int pinGelbesLicht, int pinGruenesLicht) {
    	rotesLicht = new rpDiode(pinRotesLicht);
    	gelbesLicht = new rpDiode(pinGelbesLicht);
    	gruenesLicht = new rpDiode(pinGruenesLicht);
	}

	/**
	* Schaltet die Ampel auf Gruen um.
	*/
	public void aufGruenSchalten(){
        //Hier den Code einfuegen, um die Ampel auf gruen zu schalten
        
		//Idee:
		//Rotes Licht ist an
		gelbesLicht.an();
		try{
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("Error: Timeout (InterruptedException)");
		}
		gelbesLicht.aus();
		gruenesLicht.an();
		istGeradeGruen = true;
		istGeradeRot = false;
    }
    
	/**
	* Schaltet die Ampel auf Rot um.
	*/
	public void aufRotSchalten(){
        //Hier den Code einfuegen, um die Ampel auf rot zu schalten
		
		//Idee:
		//Gruenes Licht ist an
		gruenesLicht.aus();
		gelbesLicht.an();
		try{
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("Error: Timeout (InterruptedException)");
		}
		gelbesLicht.aus();
		rotesLicht.an();
		istGeradeGruen = false;
		istGeradeRot = true;
	}
	
	
	/**
	* Erfragt, ob die Ampel gerade gruen ist.
	* @return Ob die Ampel gerade gruen ist.
	*/
	public boolean istGeradeGruen(){
		return istGeradeGruen;
	}
	
	/**
	* Erfragt, ob die Ampel gerade rot ist.
	* @return Ob die Ampel gerade rot ist.
	*/
	public boolean istGeradeRot(){
		return istGeradeRot;
	}
}
