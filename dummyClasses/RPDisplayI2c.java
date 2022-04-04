/**
 * Klasse zum Anschluss eines Mehrzeilendisplay über I2C an den Raspberry Pi.
 *
 * @author Johannes Pieper
 * @version 0.9
 */
public class RPDisplayI2c {

    public RPDisplayI2c(int adresse) {
    }


    public void clearDisplay() {
        System.out.println("Display geleert");
    }

    public void write(int zeile, int spalte, String text) {
    	System.out.println("Text auf Display: " + text);
    }

    public void ausschalten(){
    	System.out.println("Display ausgeschaltet");
    }

    public void einschalten(){
    	System.out.println("Display eingeschaltet");
    }

    /**
    * Schalte GPIO ab und dereferenziere den GPIO und den Pin.
    */
    public void herunterfahren() {
    }


    public static void main(String[] args) {
        RPDisplayI2c display = new RPDisplayI2c(0x27);
        display.write(0,0,"Test");
        display.write(3,0,"weiterer");
     }
}