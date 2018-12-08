// import com.pi4j.component.lcd.impl.GpioLcdDisplay;
import com.pi4j.wiringpi.Lcd;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

/**
 * Klasse zum Anschluss eines Mehrzeilendisplay an den Raspberry Pi.
 *
 * @author Johannes Pieper
 * @version 0.9
 */
public class RPDisplay {
    private int handle = -1;
    private boolean isInit = false;

    RPDisplay() {
    }

    RPDisplay(int rs, int s, int d4, int d5, int d6, int d7, int rows, int cols) {
        this.init(rs, s, d4, d5, d6, d7, rows, cols);
    }

    private void init(int rs, int s, int d4, int d5, int d6, int d7, int rows, int cols) {
        GpioController gpio = GpioFactory.getInstance();
        this.handle = Lcd.lcdInit(
                    rows,
                    cols,
                    4,
                    (int) Helfer.pinZuordnungBCMzuJ8[rs],
                    (int) Helfer.pinZuordnungBCMzuJ8[s],
                    (int) Helfer.pinZuordnungBCMzuJ8[d4],
                    (int) Helfer.pinZuordnungBCMzuJ8[d5],
                    (int) Helfer.pinZuordnungBCMzuJ8[d6],
                    (int) Helfer.pinZuordnungBCMzuJ8[d7],
                    0 ,0 ,0 ,0);
        this.isInit = true;
        this.clearDisplay();
    }

    public void clearDisplay() {
        Lcd.lcdClear(this.handle);
    }

    public void write(int zeile, int spalte, String text) {
        Lcd.lcdPosition(this.handle, spalte, zeile);
        Lcd.lcdPuts(this.handle, text);
    }


   public static void main(String[] args) {
        RPDisplay display = new RPDisplay(21, 20, 16, 12, 26, 19, 2, 16);
        display.write(0,0,"Test");
     }
}
