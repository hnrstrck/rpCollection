import com.pi4j.context.Context;
import com.pi4j.io.i2c.I2C;
import com.pi4j.io.i2c.I2CConfigBuilder;

/**
 * Klasse zum Anschluss eines Mehrzeilendisplay über I2C an den Raspberry Pi.
 *
 * @author Johannes Pieper
 * @version 2.0
 */
public class RPDisplayI2c {

    private final static boolean LCD_CHR = true; //Unterscheidung ob Befehl oder Daten
    private final static boolean LCD_CMD = false;

    public final static int LCD_BACKLIGHT = 0x08;
    public final static int LCD_NOBACKLIGHT = 0x00;
    public final static int En = 0b00000100; //Enable bit
    public final static int Rs = 0b00000001; //Register select bit

    private final int[] LCD_LINE_ADDRESS = { 0x80, 0xC0, 0x94, 0xD4};  //Addresse für Zeilen
    private static I2C dev = null;
    private int backlightStatus = LCD_BACKLIGHT;

    public RPDisplayI2c(int adresse) {
        try {
            Context pi4j = RPEnvironment.getContext();
            I2CConfigBuilder i2CConfig = RPEnvironment.getI2CConfig();
            this.dev = pi4j.create(i2CConfig
                .device(adresse)
                .bus(1)
                .id("I2C-" + adresse)
            );
            this.init();
        } catch (Exception e) {
            System.out.println("Display konnte nicht initialisiert werden.");
            System.out.println(e.getMessage());
        }
    }

    private void init() throws Exception { //Initialization routine of LCD
        this.lcd_byte(0x03, LCD_CMD);
        this.lcd_byte(0x03, LCD_CMD);
        this.lcd_byte(0x03, LCD_CMD);
        this.lcd_byte(0x02, LCD_CMD);

        this.lcd_byte(0x28, LCD_CMD);    // 4bit - 2 line
        this.lcd_byte(0x08, LCD_CMD);    // don't shift, hide cursor
        this.lcd_byte(0x01, LCD_CMD);    // clear and home display
        this.lcd_byte(0x06, LCD_CMD);    // move cursor right
        this.lcd_byte(0x0C, LCD_CMD);    // turn on
    }

    private void lcd_byte(int val, boolean type) throws Exception { //Setzt RS flag and und sendet die Daten
        int rsFlag = type ? Rs : 0;
        this.writeFourBits(rsFlag | (val & 0xF0));
        this.writeFourBits(rsFlag | ((val << 4) & 0xF0));
    }

    private void writeFourBits(int data) throws Exception {
        this.dev.write((byte)(data | this.backlightStatus));
        this.dev.write((byte)(data | En | this.backlightStatus));
        Thread.sleep(5);
        this.dev.write((byte)(((data & ~En) | this.backlightStatus)));
        Thread.sleep(1);
    }

    public void clearDisplay() {
        try {
            this.lcd_byte(0x01, LCD_CMD); //LCD Clear Command
            this.lcd_byte(0x02, LCD_CMD); //LCD Home Command
        } catch (Exception e) {
            System.out.println("Fehler beim Leeren des Displays");
            System.out.println(e.getMessage());
        }
    }

    public void write(int zeile, int spalte, String text) {
        try {
            this.lcd_byte(LCD_LINE_ADDRESS[zeile] + spalte, LCD_CMD);
            for (int i = 0; i < text.length(); i++) {
                this.lcd_byte(text.charAt(i), LCD_CHR);
            }
        } catch (Exception e) {
            System.out.println("Fehler beim Schreiben auf das Display");
            System.out.println(e.getMessage());
        }
    }

    public void ausschalten(){
        this.backlightStatus = LCD_NOBACKLIGHT;
        try {
            this.dev.write((byte)LCD_NOBACKLIGHT);
        } catch (Exception e) {
            System.out.println("Konnte das Display nicht einschalten");
            System.out.println(e.getMessage());
        }
    }

    public void einschalten(){
        this.backlightStatus = LCD_BACKLIGHT;
        try {
            this.dev.write((byte)LCD_BACKLIGHT);
        } catch (Exception e) {
            System.out.println("Konnte das Display nicht einschalten");
            System.out.println(e.getMessage());
        }
    }

    /**
    * Schalte GPIO ab und dereferenziere den GPIO und den Pin.
    */
    public void herunterfahren() {
        try{
            this.dev = null;
        } catch (java.lang.NullPointerException e){
            System.out.println("Pin konnte nicht dereferenziert werden");
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        RPDisplayI2c display = new RPDisplayI2c(0x27);
        display.write(0,0,"Test");
        display.write(1,0,"weiterer");
    }
}