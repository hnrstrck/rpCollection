import com.pi4j.io.gpio.*;

import com.pi4j.io.spi.SpiChannel;
import com.pi4j.io.spi.SpiDevice;
import com.pi4j.io.spi.SpiFactory;
 
import java.nio.ByteBuffer;
import java.io.IOException;

public final class rpADWandler {

    private GpioPinDigitalInput pin;
    private GpioController gpio;
    private boolean boolInitialisierungErfolgt;

    public static SpiDevice spi = null;
    public static byte INIT_CMD = (byte) 0xD0; // 11010000


    rpADWandler() {

    }
    
    public void initialisiere() throws InterruptedException, IOException {

        spi = SpiFactory.getInstance(SpiChannel.CS0,
                                     SpiDevice.DEFAULT_SPI_SPEED, // default spi speed 1 MHz
                                     SpiDevice.DEFAULT_SPI_MODE); // default spi mode 0
    
		boolInitialisierungErfolgt = true;
    
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
    
    
    
    public int gibWertVonChannel(int channel){
		int gelesenerWert = 0;
		
		if ((channel <= 7) && (channel >= 0)){
            if (boolInitialisierungErfolgt == true){
                try{
					gelesenerWert = this.readChannel(channel); 
					System.out.println("Gelesener Wert: " + gelesenerWert);
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

	public int gibProzentwertVonChannel(int channel){
		int prozentWert = 0;
		
		if ((channel <= 7) && (channel >= 0)){
            if (boolInitialisierungErfolgt == true){
                try{
					
					//Berechne Prozent:
					prozentWert = (int)((float)this.readChannel(channel)/1023f * 100f); 

					
					System.out.println("Einstellung in Prozent: " + prozentWert + "%");
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



}
