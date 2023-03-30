import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalInputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.gpio.digital.PullResistance;
import java.util.ArrayList;

/**
 * Klasse fuer den Anschluss eines 4x4 Tastenfeldes an den Raspberry Pi. Das Tastenfeld kann gefragt werden, welche Taste gerade gedrueckt ist.
 *
 * @author Luca Michael Mathaea, Johannes Pieper
 * @version 2.0
 */
public final class RPTastenfeld {
    protected char[][] feldMatrix = {{'D', 'C', 'B', 'A'},
                                     {'#', '9', '6', '3'},
                                     {'0', '8', '5', '2'},
                                     {'*', '7', '4', '1'}};

    private ArrayList<DigitalInput> inputPins = null;
    private ArrayList<DigitalOutput> outputPins = null;

    private boolean boolInitialisierungErfolgt;

    /**
    * Erstellt ein neues Objekt der Klasse RPTastenfled mit den Pins direkt als Eingabe. Vorsicht: Pins 2 und 3 sind als inputPins keine validen Pins. Benuztung dieser wird zu einem Error fuehren.
    * @param outputPins Die ersten vier Pins des Tastenfeldes.
    * @param inputPins Die letzten vier Pins des Tastenfeldes.
    */
    public RPTastenfeld(ArrayList<Integer> outputPins, ArrayList<Integer> inputPins) {
        this.boolInitialisierungErfolgt = false;

        this.setPins(outputPins, inputPins);
    }

    /**
     * Erstellt ein neues Objeckt der Klasse RPTastenfeld ohne die Pins zu setzten. Die Pins muessen manuell gesetzt werden mit der Methode RPTastenfeld.setPins()
     */
    public RPTastenfeld() {
        this.boolInitialisierungErfolgt = false;
    }

    /**
    * Setzt die Pins des Tastenfeldes.
    * Vorsicht: Pins 2 und 3 sind als inputPins keine validen Pins. Benuztung dieser wird zu einem Error fuehren.
    * @param outputPins Die ersten vier Pins des Tastenfeldes.
    * @param inputPins Die letzten vier Pins des Tastenfeldes.
    */
    public void setPins(ArrayList<Integer> outputPins, ArrayList<Integer> inputPins) {
        this.setInputPin(inputPins);
        this.setOutputPin(outputPins);
        this.boolInitialisierungErfolgt = true;
    }

    private void setInputPin(ArrayList<Integer> pPins) {
        if (this.inputPins == null) {
            this.inputPins = new ArrayList<DigitalInput>();
            Context pi4j = RPEnvironment.getContext();
            DigitalInputConfigBuilder inputConfig = RPEnvironment.getInputConfig();
            for(int i = 0; i < pPins.size(); i++) {
                //setzte Pin Resistenz auf Pull Down damit der Default-State des Pins LOW ist und nicht HIGH
                DigitalInput pin = pi4j.create(inputConfig
                    .address(pPins.get(i))
                    .id("pin" + pPins.get(i))
                    .pull(PullResistance.PULL_DOWN)
                );
                this.inputPins.add(pin);
                System.out.println("Input-Pin " + pPins.get(i) + " gesetzt");
            }
            System.out.println("Input-Pins gesetzt fuer das Tastenfeld:");
        } else {
            System.out.println("Input-Pin bereits gesetzt:");
        }
    }

    private void setOutputPin(ArrayList<Integer> pPins) {
          if (this.outputPins == null) {
            this.outputPins = new ArrayList<DigitalOutput>();
            Context pi4j = RPEnvironment.getContext();
            DigitalOutputConfigBuilder outputConfig = RPEnvironment.getOutputConfig();
            for(int i = 0; i < pPins.size(); i++) {
                DigitalOutput pin = pi4j.create(outputConfig
                    .address(pPins.get(i))
                    .id("pin" + pPins.get(i))
                    .shutdown(DigitalState.LOW)
                );
                this.outputPins.add(pin);
                System.out.println("Output-Pin " + pPins.get(i) + " gesetzt");
            }
            System.out.println("Output-Pins gesetzt fuer das Tastenfeld:");
        } else {
            System.out.println("Output-Pins bereits gesetzt:");
        }
    }

    /**
    * Ueberprueft welche Taste auf dem Tastenfeld gedreuckt wurde und gibt diese zurueck.
    * Falls keine Taste gedrueckt wurde wird ein leerer Charakter (' ') zurueckgegeben.
    * @return Charakter die auf dem Tastenfeld zu sehen sind.
    */
    public char gibGedrueckteTaste() {
        if (boolInitialisierungErfolgt == true) {
            try {
                char letzterGefundenerChar = ' ';
                for(int t = 0; t < this.outputPins.size(); t++) {
                    this.outputPins.get(t).on();

                    for(int i = 0; i < this.inputPins.size(); i++) {
                        if (this.inputPins.get(i).state() == DigitalState.HIGH) {
                            letzterGefundenerChar = this.feldMatrix[t][i];
                        }
                    }
                    this.outputPins.get(t).off();
                }
                if(letzterGefundenerChar != ' ') {
                    return letzterGefundenerChar;
                }
            }
            catch (NullPointerException f) {
                System.out.println("Error: Pin nicht definiert? Klasse: Tastenfeld (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer das Membranfeld angeben, bevor diese Methode (gibGedrueckteTaste) wieder aufgerufen wird.");
        }

        return ' ';
    }
}
