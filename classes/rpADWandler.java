import com.pi4j.io.gpio.*;

import com.pi4j.io.spi.SpiChannel;
import com.pi4j.io.spi.SpiDevice;
import com.pi4j.io.spi.SpiFactory;

import java.nio.ByteBuffer;
import java.io.IOException;

/**
 * Klasse fuer den Anschluss eines ADWandlers (MCP 3008, MCP 3208) am Raspberry Pi.<br>
 * <br>Quelltexte zum Auslesen des AD-Wandlers mit Pi4J basieren auf: 
 * <ul>
 * <li><a href="https://nealvs.wordpress.com/2016/02/19/pi4j-adc-mcp3008-spi-sensor-reader-example/">https://nealvs.wordpress.com/2016/02/19/pi4j-adc-mcp3008-spi-sensor-reader-example/</a> (MCP3008)</li>
 * <li><a href="https://github.com/oksbwn/MCP3208_Raspberry-Pi/blob/master/MCP3208_raspberryPi.java">https://github.com/oksbwn/MCP3208_Raspberry-Pi/blob/master/MCP3208_raspberryPi.java</a> (MCP3208)</li>
 * </ul>
 *  
 @author Heiner Stroick
 * @version 0.9
 */
public final class rpADWandler {

    //private GpioController gpio;
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
            SpiDevice.DEFAULT_SPI_SPEED, 
            SpiDevice.DEFAULT_SPI_MODE);

        boolInitialisierungErfolgt = true;

        System.out.println("Initialisierung des AD-Wandlers erfolgreich");
    }

    /**
    * Liest den uebergebenen Channel aus (fuer AD-Wandler MCP3008).
    *
    * @param channel Der Channel des AD-Wandlers (0, 1, 2, ..., 7).
    * @return Der gelesene Wert des Channels.
    */
    //Quelle: siehe oben.
    private static int readChannelMCP3008(int channel) throws IOException {
        // 10-bit ADC MCP3008
        byte packet[] = new byte[3];
        packet[0] = 0x01;  // INIT_CMD;  // address byte
        packet[1] = (byte) ((0x08 + channel) << 4);  // singleEnded + channel
        packet[2] = 0x00;

        byte[] result = spi.write(packet);
        
        return (((result[1] & 0x03 ) << 8) | (result[2] & 0xff) );
    }
    
    
    /**
    * Liest den uebergebenen Channel aus (fuer AD-Wandler MCP3208).
    *
    * @param channel Der Channel des AD-Wandlers (0, 1, 2, ..., 7).
    * @return Der gelesene Wert des Channels.
    */
    //Quelle: siehe oben.
    private static int readChannelMCP3208(int channel) throws IOException {
		byte data[] = new byte[] {
                (byte)0b00000000,
                (byte) ((byte)channel<<6),
                (byte)0b00000000
        	};
        if(channel>3)
        	data[0]= 0B00000111; //First Byte for Channel 3-7
    	 else
    		data[0]= 0B00000110; //First Byte for Channel 0-3
    		      
        byte[] result = spi.write(data); //Request data from MCP3208 via SPI
   
        int value = (result[1]<< 8) & 0b0000111111111111; //merge data[1] & data[2] to get 10-bit result
        value |=  (result[2] & 0xff);
        
        return value;
    }

	/**
    * Liest den uebergebenen Channel aus (abhaengig vom aktuellen AD-Wandler-Chip)
    *
    * @param channel Der Channel des AD-Wandlers, der ausgelsen werden soll (0, 1, 2, ..., 7).
    * @see rpHelper
    */
	private static int readChannel(int channel) throws IOException {
		if (rpHelper.istMCP3208 == true){
			return readChannelMCP3208(channel);
		} else if (rpHelper.istMCP3008 == true){
			return readChannelMCP3008(channel);
		} else {
			System.out.println("Fehler beim Auslesen des Reglers. AD-Wandler nicht erkannt.");
			return 0;
		}		
	}

    /**
    * Liest den uebergebenen Regler aus.
    *
    * @param pRegler Der Regler, der ausgelesen werden soll.
    * @param ausgabe Die Ausgabe in der Shell kann durch Setzen dieses Wertes verhindert / veranlasst werden (1 = Ausgabe, andere Werte = keine Ausgabe).
    * @return Der gelesene Wert des Channels (Achtung: richtigen AD-Wandler auswaehlen).
    * @see rpHelper
    */
    public static int gibWertVonRegler(rpRegler pRegler, int ausgabe){
	   if (boolInitialisierungErfolgt == true){
			try{
				gelesenerWert = readChannel(pRegler.gibChannel()); 
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
	
		return gelesenerWert;
    }

    /**
    * Liest den uebergebenen Channel aus. Die Ausgabe erfolgt in der Shell.
    *
    * @param pRegler Der Regler, der ausgelesen werden soll.
    * @return Der gelesene Wert des Channels (Achtung: richtigen AD-Wandler auswaehlen).
    * @see rpHelper
    */
    public static int gibWertVonRegler(rpRegler pRegler){
        return gibWertVonRegler(pRegler, 1);
    }

    /**
    * Liest den uebergebenen Regler aus und gibt die Stellung in Prozent zurueck (als int, ohne "%", also zum Beispiel 37 fuer eine Stellung von 37%).
    *
    * @param pRegler Der Regler, der ausgelesen werden soll.
    * @param ausgabe Die Ausgabe in der Shell kann durch Setzen dieses Wertes verhindert / veranlasst werden (1 = Ausgabe, andere Werte = keine Ausgabe).
    * @return Der gelesene Wert des Channels in Prozent (Achtung: richtigen AD-Wandler auswaehlen).
    * @see rpHelper
    */
    public static int gibProzentwertVonRegler(rpRegler pRegler, int ausgabe){
			if (boolInitialisierungErfolgt == true){
                try{

                    //Berechne Prozent:
                    prozentWert = (int)((float)readChannel(pRegler.gibChannel())/(float)(rpHelper.aufloesungADWandler-1) * 100f); 

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
     
        return prozentWert;
    }

    /**
    * Liest den uebergebenen Regler aus und gibt die Stellung in Prozent zurueck (als int, ohne "%", also zum Beispiel 37 fuer eine Stellung von 37%). Die Ausgabe erfolgt in der Shell. 
    *
    * @param pRegler Der Regler, der ausgelesen werden soll.
    * @return Der gelesene Wert des Channels in Prozent (Achtung: richtigen AD-Wandler auswaehlen).
    * @see rpHelper
    */
    public static int gibProzentwertVonRegler(rpRegler pRegler){
        return gibProzentwertVonRegler(pRegler, 1);
    }

    /**
    * Schalte GPIO ab und dereferenziere den GPIO und den Pin.
    */ 
    public void herunterfahren(){
        try{
			spi = null;
		} catch (java.lang.NullPointerException e){
			System.out.println("Pin konnte nicht dereferenziert werden");
		}
    }

}
