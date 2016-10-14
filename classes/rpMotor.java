import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.*;

public final class rpMotor {

    private GpioPinDigitalOutput pin_EN1, pin_IN11, pin_IN12;
    private GpioController gpio;

    private boolean boolInitialisierungErfolgt;
    private int[] intPins;

    /*
     * Damit es nur einen (!) Motor-Thead gibt
     */
    public Thread threadEndlosMotorlaufen;

    private boolean laeuftGerade = false;

    rpMotor() {
        gpio = GpioFactory.getInstance();
        intPins = new int[3];
        boolInitialisierungErfolgt = false;
    }

    rpMotor(int pin_EN1, int pin_IN11, int pin_IN12) {
        gpio = GpioFactory.getInstance();
        intPins = new int[3];
        boolInitialisierungErfolgt = false;

        this.setPins(pin_EN1, pin_IN11, pin_IN12);
    }

    public void setPins(int pin_EN1, int pin_IN11, int pin_IN12){
        System.out.println("Setze Pins:");
        
        setPinEN1(pin_EN1);     
        setPinIN11(pin_IN11);  
        setPinIN12(pin_IN12);    
    }

    private void setPinEN1(int pPin) {
        pin_EN1 = null;

        System.out.println("Output-Pin gesetzt fuer Enable 1 (EN 1):");

        try {

            pin_EN1 = gpio.provisionDigitalOutputPin(rpHelper.pinArray[pPin]);
            pin_EN1.setShutdownOptions(true, PinState.LOW);

            SoftPwm.softPwmCreate(pPin,0,100);

            System.out.println("Pin " + pPin + " gesetzt");
            boolInitialisierungErfolgt = true;

            intPins[0] = pPin;
        } catch (NullPointerException f){
            System.out.println("Error: Pin nicht definiert? (NullPointerException)");
        } catch (com.pi4j.io.gpio.exception.GpioPinExistsException e){
            System.out.println("Error: Pin doppelt definiert? (GpioPinExistsException)");
        }
    }

    private void setPinIN11(int pPin) {
        pin_IN11 = null;

        System.out.println("Output-Pin gesetzt fuer Input 1.1 (IN 1.1):");

        try {

            pin_IN11 = gpio.provisionDigitalOutputPin(rpHelper.pinArray[pPin]);
            pin_IN11.setShutdownOptions(true, PinState.LOW);

            SoftPwm.softPwmCreate(pPin,0,100);

            System.out.println("Pin " + pPin + " gesetzt");
            boolInitialisierungErfolgt = true;

            intPins[1] = pPin;
        } catch (NullPointerException f){
            System.out.println("Error: Pin nicht definiert? (NullPointerException)");
        } catch (com.pi4j.io.gpio.exception.GpioPinExistsException e){
            System.out.println("Error: Pin doppelt definiert? (GpioPinExistsException)");
        }
    }

    private void setPinIN12(int pPin) {
        pin_IN12 = null;

        System.out.println("Output-Pin gesetzt fuer Input 1.2 (IN 1.2):");

        try {

            pin_IN12 = gpio.provisionDigitalOutputPin(rpHelper.pinArray[pPin]);
            pin_IN12.setShutdownOptions(true, PinState.LOW);

            SoftPwm.softPwmCreate(pPin,0,100);

            System.out.println("Pin " + pPin + " gesetzt");
            boolInitialisierungErfolgt = true;

            intPins[2] = pPin;
        } catch (NullPointerException f){
            System.out.println("Error: Pin nicht definiert? (NullPointerException)");
        } catch (com.pi4j.io.gpio.exception.GpioPinExistsException e){
            System.out.println("Error: Pin doppelt definiert? (GpioPinExistsException)");
        }
    }

    public int gibPinEN1(){
        return intPins[0];
    }

    public int gibPinIN11(){
        return intPins[1];
    }

    public int gibPinIN12(){
        return intPins[2];
    } 

    private void starteMotorGeschwindigkeit(int pGeschwindigkeit) {
        if (boolInitialisierungErfolgt == true){

            threadEndlosMotorlaufen = new Thread(){
				
                public void run() {
                    try {
                        SoftPwm.softPwmWrite(intPins[0],pGeschwindigkeit);
                        Thread.sleep(15*200);
                    } catch (InterruptedException e) {
                        System.out.println("Motor beendet");
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            };
            
            threadEndlosMotorlaufen.start();

        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (EN 1, IN 1.1, IN 1.2)");
        }
    }

    private void starteMotorZeitintervallGeschwindigkeit(int pIntervall, int pGeschwindigkeit) {
        if (boolInitialisierungErfolgt == true){

            threadEndlosMotorlaufen = new Thread(){

                public void run() {
                    try {
                        SoftPwm.softPwmWrite(intPins[0],pGeschwindigkeit);
                        Thread.sleep(pIntervall * 1000);
                        Thread.sleep(20);
                        throw new InterruptedException(); 
                    } catch (InterruptedException e) {
                        System.out.println("Motor beendet");
                        Thread.currentThread().interrupt();
                        motorstop();
                        return;
                    }
                }
            };

            threadEndlosMotorlaufen.start();

        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (EN 1, IN 1.1, IN 1.2)");
        }
    }

    public void start() {
        if (boolInitialisierungErfolgt == true){

            if(!laeuftGerade){

                if (pin_IN11.getState() == PinState.HIGH || pin_IN12.getState() == PinState.HIGH){
                    starteMotorGeschwindigkeit(100);
                    laeuftGerade = true;
                    System.out.println("Motor gestartet");
                } else {
                    System.out.println("Zuerst Laufrichtung angeben");
                }
            } else {
                System.out.println("Motor laeuft schon");
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (EN 1, IN 1.1, IN 1.2)");
        }
    }

    public void start(int pGeschwindigkeit) {
        if (boolInitialisierungErfolgt == true){

            if(!laeuftGerade){
                if (pin_IN11.getState() == PinState.HIGH || pin_IN12.getState() == PinState.HIGH){
                    starteMotorGeschwindigkeit(pGeschwindigkeit);
                    laeuftGerade = true;
                    System.out.println("Motor gestartet");
                } else {
                    System.out.println("Zuerst Laufrichtung angeben");
                }
            } else {
                System.out.println("Motor laeuft schon");
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (EN 1, IN 1.1, IN 1.2)");
        }
    }

    public void startZeitintervall(int pSekunden) {
        if (boolInitialisierungErfolgt == true){

            if(!laeuftGerade){
                if (pin_IN11.getState() == PinState.HIGH || pin_IN12.getState() == PinState.HIGH){
                    starteMotorZeitintervallGeschwindigkeit(pSekunden, 100);
                    laeuftGerade = true;
                    System.out.println("Motor gestartet");
                } else {
                    System.out.println("Zuerst Laufrichtung angeben");
                }
            } else {
                System.out.println("Motor laeuft schon");
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (EN 1, IN 1.1, IN 1.2)");
        }
    }

    public void startZeitintervall(int pSekunden, int pGeschwindigkeit) {
        if (boolInitialisierungErfolgt == true){

            if(!laeuftGerade){
                if (pin_IN11.getState() == PinState.HIGH || pin_IN12.getState() == PinState.HIGH){
                    starteMotorZeitintervallGeschwindigkeit(pSekunden, pGeschwindigkeit);
                    laeuftGerade = true;
                    System.out.println("Motor gestartet");
                } else {
                    System.out.println("Zuerst Laufrichtung angeben");
                }
            } else {
                System.out.println("Motor laeuft schon");
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (EN 1, IN 1.1, IN 1.2)");
        }
    }

    public void stop(){
        this.motorstop();
    }

    private void motorstop() {
        if (boolInitialisierungErfolgt == true){
            try{
                threadEndlosMotorlaufen.interrupt();
                SoftPwm.softPwmWrite(intPins[0],0);
                laeuftGerade = false;
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (EN 1, IN 1.1, IN 1.2)");
        }
    }

    /**
     * int pRichtung entspricht:   0 = links,   1 = rechts
     */
    public void setzeLaufrichtung(int pRichtung) {
        if (boolInitialisierungErfolgt == true){
            if (pRichtung == 0 || pRichtung == 1){
                if (pRichtung == 0){
                    try{
                        SoftPwm.softPwmWrite(intPins[1],100);
                        SoftPwm.softPwmWrite(intPins[2],0);
                    } catch (NullPointerException f){
                        System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                    }
                } else {
                    try{
                        SoftPwm.softPwmWrite(intPins[1],0);
                        SoftPwm.softPwmWrite(intPins[2],100);

                    } catch (NullPointerException f){
                        System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                    }							
                }
            } else {
                System.out.println("Angabe fuer die Richtung muss 0 oder 1 sein.");
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (EN 1, IN 1.1, IN 1.2)");
        }
    }

    public void wechselLaufrichtung() {
        if (boolInitialisierungErfolgt == true){
            if (pin_IN11.getState() == PinState.HIGH || pin_IN12.getState() == PinState.HIGH){
                if (pin_IN11.getState() == PinState.HIGH){
                    try{
                        SoftPwm.softPwmWrite(intPins[1],0);
                        SoftPwm.softPwmWrite(intPins[2],100);
                        System.out.println("Laufrichtung geaendert");
                    } catch (NullPointerException f){
                        System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                    }
                } else {
                    try{
                        SoftPwm.softPwmWrite(intPins[1],100);
                        SoftPwm.softPwmWrite(intPins[2],0);
                        System.out.println("Laufrichtung geaendert");
                    } catch (NullPointerException f){
                        System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                    }							
                }
            } else {
                System.out.println("Es wurde noch keine Richtung festgelegt.");
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (EN 1, IN 1.1, IN 1.2)");
        }
    }

    public boolean istAn() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (pin_EN1.getState() == PinState.HIGH) {
                    System.out.println("Ja, Motor ist angeschaltet");
                    return true;
                } else {
                    System.out.println("Nein, Motor ist ausgeschaltet");
                    return false; 
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                return false;
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (EN 1, IN 1.1, IN 1.2)");
            return false;
        }    
    }

    public boolean istAus() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (pin_EN1.getState() == PinState.HIGH) {
                    System.out.println("Nein, Motor ist angeschaltet");
                    return false;
                } else {
                    System.out.println("Ja, Motor ist ausgeschaltet");
                    return true; 
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                return false;
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (EN 1, IN 1.1, IN 1.2)");
            return false;
        }    
    } 

    public boolean hatLaufrichtungLinks() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (pin_IN11.getState() == PinState.HIGH) {
                    System.out.println("Ja, Motor ist auf Links eingestellts");
                    return true;
                } else {
                    System.out.println("Nein, Motor ist auf Rechts eingestellt (oder hat noch keine Richtung)");
                    return false; 
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                return false;
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (EN 1, IN 1.1, IN 1.2)");
            return false;
        }    
    }

    public boolean hatLaufrichtungRechts() {
        if (boolInitialisierungErfolgt == true){
            try{
                if (pin_IN12.getState() == PinState.LOW) {
                    System.out.println("Nein, Motor ist auf Links eingestellt (oder hat noch keine Richtung)");
                    return false;
                } else {
                    System.out.println("Ja, Motor ist auf Rechts eingestellt");
                    return true; 
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pins nicht definiert? (NullPointerException)");
                return false;
            }
        } else {
            System.out.println("Zuerst Pins fuer den Motor angeben (EN 1, IN 1.1, IN 1.2)");
            return false;
        }    
    } 

    public void destroy() {
        gpio.shutdown();
        gpio = null;
        pin_EN1 = null;
        pin_IN11 = null;
        pin_IN12 = null;
    }
}
