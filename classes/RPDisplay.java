import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfig;
import com.pi4j.io.gpio.digital.DigitalOutputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalState;

/**
 * Klasse zum Anschluss eines Mehrzeilendisplay an den Raspberry Pi.
 *
 * @author Johannes Pieper
 * @version 2.0
 */
public class RPDisplay {
    private boolean boolInitialisierungErfolgt = false;
    private DigitalOutput pin_rs = null;
    private DigitalOutput pin_e = null;
    private DigitalOutput pin_data4 = null;
    private DigitalOutput pin_data5 = null;
    private DigitalOutput pin_data6 = null;
    private DigitalOutput pin_data7 = null;
    private int rows = 0;
    private int cols = 0;

    private final int[] LCD_LINE_ADDRESS = { 0x80, 0xC0, 0x94, 0xD4};  //Adressen für Zeilen

    private final static DigitalState LCD_CHR = DigitalState.HIGH; //Unterscheidung ob Befehl oder Daten
    private final static DigitalState LCD_CMD = DigitalState.LOW;

    private final static int DELAY = 1;
    private final static int LCD_CLEAR = 0x01;
    private final static int LCD_HOME = 0x02;


    /**
    * Erzeugt ein Display-Objekt ohne Angabe der Pins, sowie dessen Größe
    */
    public RPDisplay() {
    }

    /**
    * Erzeugt ein Display-Objekt unter Angabe der Pins, sowie dessen Größe
    * @param rs RS-Pin
    * @param e E-Pin
    * @param d4 D4-Pin
    * @param d5 D5-Pin
    * @param d6 D6-Pin
    * @param d7 D7-Pin
    * @param rows Anzahl der Zeilen
    * @param cols Anzahl der Spalten
    */
    public RPDisplay(int rs, int e, int d4, int d5, int d6, int d7, int rows, int cols) {
        this.init(rs, e, d4, d5, d6, d7, rows, cols);
    }

    /**
    * Setzt die Pins für das Display, sowie dessen Größe
    * @param rs RS-Pin
    * @param e E-Pin
    * @param d4 D4-Pin
    * @param d5 D5-Pin
    * @param d6 D6-Pin
    * @param d7 D7-Pin
    * @param rows Anzahl der Zeilen
    * @param cols Anzahl der Spalten
    */
    public void setPins(int rs, int e, int d4, int d5, int d6, int d7, int rows, int cols) {
        this.init(rs, e, d4, d5, d6, d7, rows, cols);
    }

    private void init(int rs, int e, int d4, int d5, int d6, int d7, int rows, int cols) {
        if (!this.boolInitialisierungErfolgt) {
            Context pi4j = RPEnvironment.getContext();
            DigitalOutputConfigBuilder outputConfig = RPEnvironment.getOutputConfig();
            this.pin_rs = pi4j.create(outputConfig
                .address(rs)
                .id("pin" + rs)
            );
            this.pin_e = pi4j.create(outputConfig
                .address(e)
                .id("pin" + e)
            );
            this.pin_data4 = pi4j.create(outputConfig
                .address(d4)
                .id("pin" + d4)
            );
            this.pin_data5 = pi4j.create(outputConfig
                .address(d5)
                .id("pin" + d5)
            );
            this.pin_data6 = pi4j.create(outputConfig
                .address(d6)
                .id("pin" + d6)
            );
            this.pin_data7 = pi4j.create(outputConfig
                .address(d7)
                .id("pin" + d7)
            );
            this.boolInitialisierungErfolgt = true;

            this.initDisplay();
            this.clearDisplay();
        } else {
            System.out.println("Bereits initialisiert");
        }
    }

    private void initDisplay() {
        lcdSendByte(0x33, LCD_CMD);
        lcdSendByte(0x32, LCD_CMD);
        lcdSendByte(0x28, LCD_CMD);
        lcdSendByte(0x0C, LCD_CMD);
        lcdSendByte(0x06, LCD_CMD);
        lcdSendByte(0x01, LCD_CMD);
    }

    private void lcdSendByte(int bits, DigitalState mode) {
        try {
            // Pins auf LOW setzen
            pin_rs.state(mode);
            pin_data4.state(DigitalState.LOW);
            pin_data5.state(DigitalState.LOW);
            pin_data6.state(DigitalState.LOW);
            pin_data7.state(DigitalState.LOW);
            if ((bits & 0x10) == 0x10) {
                pin_data4.state(DigitalState.HIGH);
            }
            if ((bits & 0x20) == 0x20) {
                pin_data5.state(DigitalState.HIGH);
            }
            if ((bits & 0x40) == 0x40) {
                pin_data6.state(DigitalState.HIGH);
            }
            if ((bits & 0x80) == 0x80) {
                pin_data7.state(DigitalState.HIGH);
            }
            Thread.sleep(DELAY);
            pin_e.state(DigitalState.HIGH);
            Thread.sleep(DELAY);
            pin_e.state(DigitalState.LOW);
            Thread.sleep(DELAY);
            pin_data4.state(DigitalState.LOW);
            pin_data5.state(DigitalState.LOW);
            pin_data6.state(DigitalState.LOW);
            pin_data7.state(DigitalState.LOW);
            if ((bits & 0x01) == 0x01) {
                pin_data4.state(DigitalState.HIGH);
            }
            if ((bits & 0x02) == 0x02) {
                pin_data5.state(DigitalState.HIGH);
            }
            if ((bits & 0x04) == 0x04) {
                pin_data6.state(DigitalState.HIGH);
            }
            if ((bits & 0x08) == 0x08) {
                pin_data7.state(DigitalState.HIGH);
            }
            Thread.sleep(DELAY);
            pin_e.state(DigitalState.HIGH);
            Thread.sleep(DELAY);
            pin_e.state(DigitalState.LOW);
            Thread.sleep(DELAY);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Display konnte nicht angesprochen werden.");
            System.out.println(e.getMessage());
        }
    }

    /**
    * Leert das Display
    */
    public void clearDisplay() {
       this.lcdSendByte(LCD_CLEAR, LCD_CMD);
       this.lcdSendByte(LCD_HOME, LCD_CMD);
    }

    /**
    * Schreibt Text in der gegebenen Zeile ab der gegebenen Spalte
    * @param zeile Die Zeile, ab 0 gerechnet
    * @param spalte Die Spalte für den Start, ab 0 gerechnet
    * @param text Der Text der angezeigt werden soll
    */
    public void write(int zeile, int spalte, String text) {
        this.lcdSendByte(LCD_LINE_ADDRESS[zeile] + spalte, LCD_CMD);
        for (int i = 0; i < text.length(); i++) {
            this.lcdSendByte(text.charAt(i), LCD_CHR);
        }
    }

    /**
    * Test-Methode
    */
    public static void main(String[] args) {
        //int rs, int e, int d4, int d5, int d6, int d7, int rows, int cols
        RPDisplay display = new RPDisplay(5, 6, 13, 26, 16, 12, 2, 16);
        display.write(0,0,"Test");
        display.write(1,3,"Test");
    }
}
