/**
 * Klasse fuer die angeschlossenen Regler an einem AD-Wandler. Sollen Werte von Reglern ausgelesen werden, die an einen AD-Wandler angeschlossen sind, so muessen zunaechst Objekte dieser Klasse erstellt werden.
 * 
 * @see RPADWandler
 * 
 * @author Heiner Stroick
 * @version 0.9
 */
public final class RPRegler {

    private int angeschlossenerChannel;

    /**
    * 
    */
    public RPRegler() {
		System.out.println("Regler erstellt.");
		System.out.println("Channel des AD-Wandlers einstellen, an dem dieser Regler angeschlossen ist.");
    }

	public RPRegler(int angeschlossenerChannel){
		System.out.println("Regler erstellt.");
		setChannel(angeschlossenerChannel);
	}

    /**
    * Gibt den angeschlossenen Channel des Reglers zurueck.
    * 
    * @return Der angeschlossene Channel des Reglers.
    */
    public int gibChannel(){
        return angeschlossenerChannel;
    }

    /**
    * Setzt den Channel des Reglers am AD-Wandler.
    *
    * @param angeschlossenerChannel Der Channel des Reglers am AD-Wandlers (0, 1, 2, ..., 7).
    */
    public void setChannel(int angeschlossenerChannel) {
		if ((angeschlossenerChannel <= 7) && (angeschlossenerChannel >= 0)){
			this.angeschlossenerChannel = angeschlossenerChannel;
			System.out.println("Channel " + angeschlossenerChannel + " fuer Regler festgelegt");
		} else {
			System.out.println("Falsche Zahlenbereiche angegeben. channel muss zwischen 0 und 7 (jew. einschliesslich) liegen");
		}
	}
}
