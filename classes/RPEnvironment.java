import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalInputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.gpio.digital.PullResistance;
import com.pi4j.io.pwm.Pwm;
import com.pi4j.io.pwm.PwmConfigBuilder;
import com.pi4j.io.pwm.PwmType;

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

    public static Context getContext()
    {
        if (RPEnvironment.pi4j == null) {
            RPEnvironment.pi4j = Pi4J.newAutoContext();
        }
        return RPEnvironment.pi4j;
    }

    public static void herunterfahren()
    {
        if (RPEnvironment.pi4j != null) {
            RPEnvironment.pi4j.shutdown();
            RPEnvironment.pi4j = null;
            RPEnvironment.outputConfig = null;
            RPEnvironment.inputConfig = null;
        }
    }

    public static DigitalOutputConfigBuilder getOutputConfig()
    {
        if (RPEnvironment.outputConfig == null) {
            RPEnvironment.outputConfig = DigitalOutput.newConfigBuilder(RPEnvironment.getContext())
                .provider("pigpio-digital-output")
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW);
        }
        return RPEnvironment.outputConfig;
    }

    public static DigitalInputConfigBuilder getInputConfig()
    {
        if (RPEnvironment.inputConfig == null) {
            RPEnvironment.inputConfig = DigitalInput.newConfigBuilder(RPEnvironment.getContext())
                .provider("pigpio-digital-input")
                .pull(PullResistance.OFF);
        }
        return RPEnvironment.inputConfig;
    }

    public static PwmConfigBuilder getPwmConfig()
    {
        if (RPEnvironment.pwmConfig == null) {
            RPEnvironment.pwmConfig = Pwm.newConfigBuilder(RPEnvironment.getContext())
                .provider("pigpio-pwm")
                .pwmType(PwmType.SOFTWARE);
        }
        return RPEnvironment.pwmConfig;
    }
}