/**
*   Dieses Display realisiert die Ausgabe auf ein I2C-Dispaly.
*
*	Hinweis:
*	Fuer die Nutzung ist bisher keine andere der RP-Klassen vorgesehen, dieses soll aber noch ergänzt werden.
*	Dann haetten die SuS aber mit zwei Klassen zu tun, was hier vermieden werden sollte, falls dieser Quelltext mal interessieren sollte.
*/

public class Display{
    private RPDisplayI2c display;

    /**
    * Initialisiert ein I2C-Display. Daher muss kein Pin angegeben werden
    */
    public Display() {
        this.display = new RPDisplayI2c();
    }

    /**
    * Schreibt den uebergebenen Text immer in die erste Zeile des Displays und auf die Konsole.
    *
    * @param text Text zur Ausgabe
    */
    public void darstellen(String text)
    {
        this.display.clearDisplay();
        this.display.write(0,0, text);
        System.out.println(text);
    }
}