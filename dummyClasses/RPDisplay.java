/**
 * Klasse zum Anschluss eines Mehrzeilendisplay an den Raspberry Pi.
 *
 * @author Johannes Pieper
 * @version 0.9
 */
public class RPDisplay {
    private int handle = -1;
    private boolean isInit = false;

    public RPDisplay() {
    }

    public RPDisplay(int rs, int s, int d4, int d5, int d6, int d7, int rows, int cols) {
    }

    public void clearDisplay() {
    	System.out.println("Display geleert");
    }

    public void write(int zeile, int spalte, String text) {
    	System.out.println("Text auf Display: " + text);
    }

   public static void main(String[] args) {
        RPDisplay display = new RPDisplay(21, 20, 16, 12, 26, 19, 2, 16);
        display.write(0,0,"Test");
     }
}
