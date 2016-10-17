import com.pi4j.io.gpio.*;

import com.pi4j.io.spi.SpiChannel;
import com.pi4j.io.spi.SpiDevice;
import com.pi4j.io.spi.SpiFactory;

import java.nio.ByteBuffer;
import java.io.IOException;

/**
 * Klasse fuer den Anschluss eines ADWandlers (MCP 3008, MCP 3208) am Raspberry Pi. Quelltexte zum Auslesen des AD-Wandlers mit Pi4J basieren auf: <a href="https://nealvs.wordpress.com/2016/02/19/pi4j-adc-mcp3008-spi-sensor-reader-example/">https://nealvs.wordpress.com/2016/02/19/pi4j-adc-mcp3008-spi-sensor-reader-example/</a>.
 * 
 * @author Heiner Stroick
 * @version 0.9
 */
public final class rpADWandler {

    private GpioController gpio;
    private static boolean boolInitialisierungErfolgt;

    private static SpiDevice spi = null;
    private static byte INIT_CMD = (byte) 0xD0; // 11010000
    private static int gelesenerWert = 0;
    private static int prozentWert = 0;

    /**
    * Erstellt ein neues Objekt der Klasse rpADWandler (die Initialisierung erfolgt automaitsch, es muessen keine Pins angegeben werden).
    */
    rpADWandler() {
        try{
            System.out.println("Initialisierung den AD-Wandler...");
            this.initialisiere();
        } catch (InterruptedException e){
            System.out.println("Fehler bei der Initialisierung des AD-Wandlers: InterruptedException");

        } catch (IOException f){
            System.out.println("Fehler bei der Initialisierung des AD-Wandlers: IOException");
        }
    }

    /**
    * Initialisiert den AD-Wandler.
    * @throws InterruptedException Wirft InterruptedException, falls Anschluesse falsch sind (Speed)
    * @throws IOException Wirft IOException, falls Anschluesse falsch sind (Mode)
    */
    public void initialisiere() throws InterruptedException, IOException {
        spi = SpiFactory.getInstance(SpiChannel.CS0,
            SpiDevice.DEFAULT_SPI_SPEED, // default spi speed 1 MHz
            SpiDevice.DEFAULT_SPI_MODE); // default spi mode 0

        boolInitialisierungErfolgt = true;

        System.out.println("Initialisierung des AD-Wandlers erfolgreich");
    }

    /**
    * Liest den uebergebenen Channel aus.
    *
    * @param channel Der Channel des AD-Wandlers (0, 1, 2, ..., 7).
    * @return Der gelesene Wert des Channels.
    */
    private static int readChannel(int channel) throws IOException {
        // 10-bit ADC MCP3008
        byte packet[] = new byte[3];
        packet[0] = 0x01;  // INIT_CMD;  // address byte
        packet[1] = (byte) ((0x08 + channel) << 4);  // singleEnded + channel
        packet[2] = 0x00;

        byte[] result = spi.write(packet);
        return (((result[1] & 0x03 ) << 8) | (result[2] & 0xff) );

    }

    /**
    * Liest den uebergebenen Channel aus.
    *
    * @param channel Der Channel des AD-Wandlers (0, 1, 2, ..., 7).
    * @param ausgabe Die Ausgabe in der Shell kann durch Setzen dieses Wertes verhindert / veranlasst werden (1 = Ausgabe, andere Werte = keine Ausgabe).
    * @return Der gelesene Wert des Channels (Achtung: richtigen AD-Wandler auswaehlen).
    * @see rpHelper
    */
    public static int gibWertVonChannel(int channel, int ausgabe){
        if ((channel <= 7) && (channel >= 0)){
            if (boolInitialisierungErfolgt == true){
                try{
                    gelesenerWert = readChannel(channel); 
                    if (ausgabe == 1){
                        System.out.println("Gelesener Wert: " + gelesenerWert);
                    }
                    return gelesenerWert;
                } catch (IOException f){
                    System.out.println("Error: Pin nicht definiert? (IOException)");
                }
            } else {
                System.out.println("Zuerst AD-Wandler initialisieren");
            }
        } else {
            System.out.println("Falsche Zahlenbereiche angegeben. channel muss zwischen 0 und 7 (jew. einschliesslich) liegen");
        }

        return gelesenerWert;

    }

    /**
    * Liest den uebergebenen Channel aus. Die Ausgabe erfolgt in der Shell.
    *
    * @param channel Der Channel des AD-Wandlers (0, 1, 2, ..., 7).
    * @return Der gelesene Wert des Channels (Achtung: richtigen AD-Wandler auswaehlen).
    * @see rpHelper
    */
    public static int gibWertVonChannel(int channel){
        return gibWertVonChannel(channel, 1);
    }

    /**
    * Liest den uebergebenen Channel aus und gibt die Stellung des Potentiometers in Prozent zurueck (als int, ohne "%", also zum Beispiel 37 fuer eine Stellung von 37%).
    *
    * @param channel Der Channel des AD-Wandlers (0, 1, 2, ..., 7).
    * @param ausgabe Die Ausgabe in der Shell kann durch Setzen dieses Wertes verhindert / veranlasst werden (1 = Ausgabe, andere Werte = keine Ausgabe).
    * @return Der gelesene Wert des Channels in Prozent (Achtung: richtigen AD-Wandler auswaehlen).
    * @see rpHelper
    */
    public static int gibProzentwertVonChannel(int channel, int ausgabe){
		
        if ((channel <= 7) && (channel >= 0)){
            if (boolInitialisierungErfolgt == true){
                try{

                    //Berechne Prozent:
                    prozentWert = (int)((float)readChannel(channel)/(float)(rpHelper.aufloesungADWandler-1) * 100f); 

                    if(ausgabe == 1){
                        System.out.println("Einstellung in Prozent: " + prozentWert + "%");
                    }
                    return prozentWert;
                } catch (IOException f){
                    System.out.println("Error: Pin nicht definiert? (IOException)");
                }
            } else {
                System.out.println("Zuerst AD-Wandler initialisieren");
            }
        } else {
            System.out.println("Falsche Zahlenbereiche angegeben. channel muss zwischen 0 und 7 (jew. einschliesslich) liegen");
        }

        return prozentWert;
    }

    /**
    * Liest den uebergebenen Channel aus und gibt die Stellung des Potentiometers in Prozent zurueck (als int, ohne "%", also zum Beispiel 37 fuer eine Stellung von 37%). Die Ausgabe erfolgt in der Shell. 
    *
    * @param channel Der Channel des AD-Wandlers (0, 1, 2, ..., 7).
    * @return Der gelesene Wert des Channels in Prozent (Achtung: richtigen AD-Wandler auswaehlen).
    * @see rpHelper
    */
    public static int gibProzentwertVonChannel(int channel){
        return gibProzentwertVonChannel(channel, 1);
    }

    /**
    * Schalte GPIO ab und dereferenziere den GPIO und den Pin.
    */ 
    public void destroy(){
        spi = null;
    }

}
