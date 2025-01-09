import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalInputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.gpio.digital.PullResistance;
import com.pi4j.io.i2c.I2C;
import com.pi4j.io.i2c.I2CConfigBuilder;
import com.pi4j.io.pwm.Pwm;
import com.pi4j.io.pwm.PwmConfigBuilder;
import com.pi4j.io.pwm.PwmType;
import com.pi4j.io.spi.Spi;
import com.pi4j.io.spi.SpiConfigBuilder;

/**
 * Klasse die intern benoetigt wird um die Konfiguration fuer die Pins bereit
 * zu stellen.
 *
 * @author Johannes Pieper
 * @version 2.0
 */
class RPEnvironment {
    private static Context pi4j = null;
    private static DigitalOutputConfigBuilder outputConfig = null;
    private static DigitalInputConfigBuilder inputConfig = null;
    private static PwmConfigBuilder pwmConfig = null;
    private static I2CConfigBuilder i2CConfig = null;
    private static SpiConfigBuilder spiConfig = null;

    /**
    * Liefert den allgemeinen Context, auf dem alle RP-Klassen zur Steuerung von den Elementen aufsetzen
    */
    public static Context getContext()
    {
        if (RPEnvironment.pi4j == null) {
            RPEnvironment.pi4j = Pi4J.newAutoContext();
        }
        return RPEnvironment.pi4j;
    }

    /**
    * Zum Freigeben aller Pins, damit es zu keinen Konflikten mit bereits belegten Pins kommt
    */
    public static void herunterfahren()
    {
        if (RPEnvironment.pi4j != null) {
            RPEnvironment.pi4j.shutdown();
            RPEnvironment.pi4j = null;
            RPEnvironment.outputConfig = null;
            RPEnvironment.inputConfig = null;
            RPEnvironment.i2CConfig = null;
        }
    }

    /**
    * Liefert die Config fuer alles was eine digitale Ausgabe hat
    */
    public static DigitalOutputConfigBuilder getOutputConfig()
    {
        if (RPEnvironment.outputConfig == null) {
            RPEnvironment.outputConfig = DigitalOutput.newConfigBuilder(RPEnvironment.getContext())
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW);
        }
        return RPEnvironment.outputConfig;
    }

    /**
    * Liefert die Config fuer alles was eine digitale Eingabe hat
    */
    public static DigitalInputConfigBuilder getInputConfig()
    {
        if (RPEnvironment.inputConfig == null) {
            RPEnvironment.inputConfig = DigitalInput.newConfigBuilder(RPEnvironment.getContext())
                .provider("pigpio-digital-input")
                .pull(PullResistance.OFF);
        }
        return RPEnvironment.inputConfig;
    }

    /**
    * Liefert die Config fuer alles was eine mit Pulsweitenmodulation arbeitet
    */
    public static PwmConfigBuilder getPwmConfig()
    {
        if (RPEnvironment.pwmConfig == null) {
            RPEnvironment.pwmConfig = Pwm.newConfigBuilder(RPEnvironment.getContext())
                .provider("pigpio-pwm")
                .pwmType(PwmType.SOFTWARE);
        }
        return RPEnvironment.pwmConfig;
    }

    /**
    * Liefert die Config fuer alles was eine mit I2C arbeitet
    */
    public static I2CConfigBuilder getI2CConfig() {
        if (RPEnvironment.i2CConfig == null) {
            RPEnvironment.i2CConfig = I2C.newConfigBuilder(RPEnvironment.getContext())
                .provider("pigpio-i2c");
        }
        return RPEnvironment.i2CConfig;
    }

    /**
    * Liefert die Config fuer alles was eine mit SPI arbeitet
    */
    public static SpiConfigBuilder getSpiConfig() {
        if (RPEnvironment.spiConfig == null) {
            RPEnvironment.spiConfig = Spi.newConfigBuilder(RPEnvironment.getContext())
                .provider("pigpio-spi");
        }
        return RPEnvironment.spiConfig;
    }
}
