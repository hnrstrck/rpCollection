import com.pi4j.io.gpio.*;
import java.util.ArrayList;

/**
 * Klasse fuer den Anschluss eines 4x4 Tastenfeldes an den Raspberry Pi. Das Tastenfeld kann gefragt werden, welche Taste gerade gedrueckt ist.
 * 
 * @author Luca Michael Mathaea
 * @version 0.991 
 */
public final class RPTastenfeld {
    protected char[][] feldMatrix = {{'D', 'C', 'B', 'A'},
                                     {'#', '9', '6', '3'},
                                     {'0', '8', '5', '2'},
                                     {'*', '7', '4', '1'}};
        
    private ArrayList<GpioPinDigitalInput> inputPins;
    private ArrayList<GpioPinDigitalOutput> outputPins;
    private GpioController gpio;

    private boolean boolInitialisierungErfolgt;
    
    /**
    * Erstellt ein neues Objekt der Klasse RPTastenfled mit den Pins direkt als Eingabe. Vorsicht: Pins 2 und 3 sind als inputPins keine validen Pins. Benuztung dieser wird zu einem Error fuehren.
    * @param outputPins Die ersten vier Pins des Tastenfeldes.
    * @param inputPins Die letzten vier Pins des Tastenfeldes.
    */
    public RPTastenfeld(ArrayList<Integer> outputPins, ArrayList<Integer> inputPins) {
        this.gpio = GpioFactory.getInstance();
        this.boolInitialisierungErfolgt = false;

        this.setPins(outputPins, inputPins);
    }
    
    /**
     * Erstellt ein neues Objeckt der Klasse RPTastenfeld ohne die Pins zu setzten. Die Pins muessen manuell gesetzt werden mit der Methode RPTastenfeld.setPins()
     */
    public RPTastenfeld() {
        this.gpio = GpioFactory.getInstance();
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
        this.inputPins = new ArrayList<GpioPinDigitalInput>();
        System.out.println("Input-Pin gesetzt fuer das Tastenfeld:");
    
        try {
            for(int i = 0; i < pPins.size(); i++) {
                GpioPinDigitalInput pin = gpio.provisionDigitalInputPin(Helfer.pinArray[pPins.get(i)]); 
                
                //setzte Pin Resistenz auf Pull Down damit der Default-State des Pins LOW ist und nicht HIGH
                pin.setPullResistance(PinPullResistance.PULL_DOWN);
            
                pin.setShutdownOptions(true, PinState.LOW);
            
                this.inputPins.add(pin);
                System.out.println("Input-Pin " + pPins.get(i) + " gesetzt");
            }
        } catch (NullPointerException f) {
            System.out.println("Error: Pin nicht definiert? Klasse: Tastenfeld (NullPointerException)");
        } 

    }
    
    private void setOutputPin(ArrayList<Integer> pPins) {
        this.outputPins = new ArrayList<GpioPinDigitalOutput>();
        System.out.println("Output-Pin gesetzt fuer das Tastenfeld:");
    
        try {
            for(int i = 0; i < pPins.size(); i++) {
                GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(Helfer.pinArray[pPins.get(i)]);
                
                pin.setShutdownOptions(true, PinState.LOW);
            
                this.outputPins.add(pin);
                System.out.println("Output-Pin " + pPins.get(i) + " gesetzt");
            }   
        } catch (NullPointerException f) {
            System.out.println("Error: Pin nicht definiert? Klasse: Tastenfeld (NullPointerException)");
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
                    this.outputPins.get(t).setState(PinState.HIGH);
        
                    for(int i = 0; i < this.inputPins.size(); i++) {
                        PinState state = this.inputPins.get(i).getState();
            
                        if(state == PinState.HIGH) {
                            letzterGefundenerChar = this.feldMatrix[t][i];
                        }
                    }
                    this.outputPins.get(t).setState(PinState.LOW);
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
    
    /**
    * Schalte GPIO ab und dereferenziere den GPIO und den Pin.
    */ 
    public void herunterfahren() {
        gpio.shutdown();
        try {
            for(int t = 0; t < this.inputPins.size(); t++) {
                gpio.unprovisionPin(this.inputPins.get(t));
            }
        
            for(int t = 0; t < this.outputPins.size(); t++) {
                gpio.unprovisionPin(this.outputPins.get(t));
            }
        
        } catch (java.lang.NullPointerException e) {
            System.out.println("Error: Pin konnte nicht dereferenziert werden! Klasse: Tastenfeld (NullPointerException)");
        }
    }
}
