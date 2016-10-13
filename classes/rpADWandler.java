import com.pi4j.io.gpio.*;

import com.pi4j.io.spi.SpiChannel;
import com.pi4j.io.spi.SpiDevice;
import com.pi4j.io.spi.SpiFactory;

import java.nio.ByteBuffer;
import java.io.IOException;

public final class rpADWandler {

    private GpioPinDigitalInput pin;
    private GpioController gpio;
    private static boolean boolInitialisierungErfolgt;

    public  static SpiDevice spi = null;
    public  static byte INIT_CMD = (byte) 0xD0; // 11010000
    private static int gelesenerWert = 0;
    private static int prozentWert = 0;

    /**
     * Hier aendern:
     * 
     * CHIP-Aufloesung - Anzahl an Rueckgaben des MCP-Chips
     * 
     * MCP 3008: 	1024
     * MCP 3208:	4096
     * 
     * Eine - und nur eine - Zeile stehen lassen: 
     *    
     */

    private static int aufloesung_chip = 1024;
    //private static int aufloesung_chip = 4096;

    rpADWandler() {
        try{
            this.initialisiere();
        } catch (InterruptedException e){
            System.out.println("Fehler bei der Initialisierung des AD-Wandlers: InterruptedException");

        } catch (IOException f){
            System.out.println("Fehler bei der Initialisierung des AD-Wandlers: IOException");
        }
    }

    public void initialisiere() throws InterruptedException, IOException {

        spi = SpiFactory.getInstance(SpiChannel.CS0,
            SpiDevice.DEFAULT_SPI_SPEED, // default spi speed 1 MHz
            SpiDevice.DEFAULT_SPI_MODE); // default spi mode 0

        boolInitialisierungErfolgt = true;

        System.out.println("Initialisierung des AD-Wandlers erfolgreich");
    }

    public static int readChannel(int channel) throws IOException {
        // 10-bit ADC MCP3008
        byte packet[] = new byte[3];
        packet[0] = 0x01;  // INIT_CMD;  // address byte
        packet[1] = (byte) ((0x08 + channel) << 4);  // singleEnded + channel
        packet[2] = 0x00;

        byte[] result = spi.write(packet);
        return (((result[1] & 0x03 ) << 8) | (result[2] & 0xff) );

    }

    public int gibProzentwertVonChannel2(int channel){
        int x = 15;
        x = this.gibWertVonChannel(0);
        return x;
    }

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

    public static int gibWertVonChannel(int channel){
        return gibWertVonChannel(channel, 1);
    }

    public static int gibProzentwertVonChannel(int channel, int ausgabe){
		
        if ((channel <= 7) && (channel >= 0)){
            if (boolInitialisierungErfolgt == true){
                try{

                    //Berechne Prozent:
                    prozentWert = (int)((float)readChannel(channel)/(float)(aufloesung_chip-1) * 100f); 

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

    public static int gibProzentwertVonChannel(int channel){
        return gibWertVonChannel(channel, 1);
    }

    public void destroy(){
        spi = null;
        boolInitialisierungErfolgt = false;
    }

}
