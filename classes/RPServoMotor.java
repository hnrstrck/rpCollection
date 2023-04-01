import com.pi4j.context.Context;
import com.pi4j.io.pwm.Pwm;
import com.pi4j.io.pwm.PwmConfig;
import com.pi4j.io.pwm.PwmConfigBuilder;
import com.pi4j.io.pwm.PwmType;

/**
 * Klasse zum Anschluss eines Servo-Motors an den Raspberry Pi. Der Servo-Motor nimmt verschiedene Winkel an, die an ihn übergeben werden.
 * Teilweise übernommen aus https://github.com/Pi4J/pi4j-example-components/blob/main/src/main/java/com/pi4j/catalog/components/ServoMotor.java
 *
 * @author Johannes Pieper
 * @version 2.0
 */
public final class RPServoMotor {
    protected final static int DEFAULT_FREQUENCY = 50;

    private Pwm pwm = null;
    private boolean boolInitialisierungErfolgt = false;

    private float minAngle = -90;
    private float maxAngle = 90;
    private float minDutyCycle = 2;
    private float maxDutyCycle = 12;
    private float minRange = 0;
    private float maxRange = 1;

    /**
     * Erstellt einen neues ServoMotor-Objekt
     */
    public RPServoMotor() {
    }

    /**
     * Erstellt einen neues ServoMotor-Objekt unter Angabe des Pin
    * @param pin Der Pin, an dem der ServoMotor angeschlossen ist.
     */
    public RPServoMotor(int pin) {
        this.setPin(pin);
    }

    /**
    * Setzt den Pin fuer den ServoMotor.
    * @param pin Der Pin, an dem der ServoMotor angeschlossen ist.
    */
    public void setPin(int pin) {
        if (this.pwm == null) {
            Context pi4j = RPEnvironment.getContext();
            PwmConfigBuilder pwmConfigBuilder = RPEnvironment.getPwmConfig();
            this.pwm = pi4j.create(pwmConfigBuilder
                .address(pin)
                .id("pin" + pin)
                .frequency(DEFAULT_FREQUENCY)
                .initial(0)
                .shutdown(0)
            );
            System.out.println("Output-Pin gesetzt:");
            boolInitialisierungErfolgt = true;
        } else {
            System.out.println("Output-Pin bereits gesetzt:");
        }
    }

    /**
     * Rotiert den ServoMotor zum angegebenen Wert in Grad.
     *
     * @param angle Der neue Winkel
     */
    public void setAngle(float angle) {
        pwm.on(mapAngleToDutyCycle(angle));
    }

    /**
     * Rotiert den ServoMotor zum angegebenen Wert in Prozent.
     *
     * @param percent Prozentwert
     */
    public void setPercent(float percent) {
        moveOnRange(percent, 0, 100);
    }

    /**
     * Bewegt den ServoMotor passend zu dem Bereich zwischen minRange und maxRange, die im Normalfall 0 und 1 sind
     *
     * @param value Wert im Bereich
     */
    public void moveOnRange(float value) {
        moveOnRange(value, minRange, maxRange);
    }

    /**
     * Bewegt den ServoMotor passend zu dem Bereich zwischen minValue und maxValue
     *
     * @param value Wert im Bereich
     * @param minValue Minimum des Bereichs
     * @param maxValue Maximum des Bereichs
     */
    public void moveOnRange(float value, float minValue, float maxValue) {
        pwm.on(mapToDutyCycle(value, minValue, maxValue));
    }

    /**
     * Setzt den Bereich von minRange und maxRange
     *
     * @param minValue Minimum des Bereichs
     * @param maxValue Maximum des Bereichs
     */
    public void setRange(float minValue, float maxValue) {
        this.minRange = minValue;
        this.maxRange = maxValue;
    }

    /**
     * Helferfunktion um den Winkel auf den Bereich abzubilden
     *
     * @param angle Gewünschter Winkel
     * @return Wert für PWM für den entsprechenden angeforderten Wert
     */
    private float mapAngleToDutyCycle(float angle) {
        return mapToDutyCycle(angle, minAngle, maxAngle);
    }

    /**
     * Hilfsfunktion, um einen Eingangswert zwischen einem bestimmten Bereich auf den konfigurierten Bereich abzubilden.
     *
     * @param input      Gewünschter Wert
     * @param inputStart Minimum Wert des Bereichs
     * @param inputEnd   Maximum Wert des Bereichs
     * @return Wert für PWM für den entsprechenden angeforderten Wert
     */
    private float mapToDutyCycle(float input, float inputStart, float inputEnd) {
        return mapRange(input, inputStart, inputEnd, minDutyCycle, maxDutyCycle);
    }

    /**
     * Hilfsfunktion zur Abbildung eines Eingabewerts aus seinem Eingabebereich auf einen möglicherweise anderen Ausgabebereich.
     *
     * @param input       Gewünschter Wert
     * @param inputStart  Minimum Wert des Bereichs der Eingabe
     * @param inputEnd    Maximum Wert des Bereichs der Eingabe
     * @param outputStart Minimum Wert des Bereichs der Ausgabe
     * @param outputEnd   Minimum Wert des Bereichs der Ausgabe
     * @return Abgebildeter Wert
     */
    private static float mapRange(float input, float inputStart, float inputEnd, float outputStart, float outputEnd) {
        if (inputStart > inputEnd) {
            final float tmp = inputEnd;
            inputEnd = inputStart;
            inputStart = tmp;
        }

        if (outputStart > outputEnd) {
            final float tmp = outputEnd;
            outputEnd = outputStart;
            outputStart = tmp;
        }

        final float clampedInput = Math.min(inputEnd, Math.max(inputStart, input));
        return outputStart + ((outputEnd - outputStart) / (inputEnd - inputStart)) * (clampedInput - inputStart);
    }

    public static void main(String[] args) {
        RPServoMotor servoMotor = new RPServoMotor(15);
        servoMotor.setPercent(10);
        Helfer.warte(3);
        servoMotor.setPercent(90);
        Helfer.warte(3);
        servoMotor.setAngle(0);
        Helfer.warte(3);
        servoMotor.setAngle(-80);
        Helfer.warte(3);
        servoMotor.setAngle(80);
    }
}