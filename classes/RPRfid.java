import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfig;
import com.pi4j.io.gpio.digital.DigitalOutputConfigBuilder;
import com.pi4j.io.spi.Spi;
import com.pi4j.io.spi.SpiConfigBuilder;
import com.pi4j.io.spi.SpiMode;

/**
 * Klasse zum Anschluss eines RFID-Empfängers an den Raspberry Pi. Der Anschluss erfolgt ueber SPI.
 * Original aus Python MFRC522
 *
 * @author Johannes Pieper
 * @version 2.0
 */
public class RPRfid {
    private int NRSTPD = 22; //RST Pin
    private int speed = 1000000;
    private int spiChannel = 0;
    private Spi spi = null;

    private final int MAX_LEN = 16; //

    public static final byte PCD_IDLE       = 0x00;
    public static final byte PCD_TRANSCEIVE = 0x0C;
    public static final byte PCD_AUTHENT    = 0x0E;
    public static final byte PCD_RESETPHASE = 0x0F;

    public  static final byte PICC_REQIDL    = 0x26;
    public  static final byte PICC_ANTICOLL  = (byte)0x93;

    public static final int MI_OK       = 0;
    public static final int MI_NOTAGERR = 1;
    public static final int MI_ERR      = 2;

    public static final byte CommandReg     = 0x01;
    public static final byte CommIEnReg     = 0x02;
    public static final byte CommIrqReg     = 0x04;
    public static final byte ErrorReg       = 0x06;
    public static final byte FIFODataReg    = 0x09;
    public static final byte FIFOLevelReg   = 0x0A;
    public static final byte ControlReg     = 0x0C;
    public static final byte BitFramingReg  = 0x0D;

    public static final byte ModeReg        = 0x11;
    public static final byte TxControlReg   = 0x14;
    public static final byte TxAutoReg      = 0x15;

    public static final byte TModeReg          = 0x2A;
    public static final byte TPrescalerReg     = 0x2B;
    public static final byte TReloadRegH       = 0x2C;
    public static final byte TReloadRegL       = 0x2D;


    /**
    * Liefert ein Objekt zum Benutzen eines RFID-Lesers.
    */
    public RPRfid(){
        this.init();
    }

    private void init() {

        Context pi4j = RPEnvironment.getContext();
        SpiConfigBuilder spiConfig = RPEnvironment.getSpiConfig();
        this.spi = pi4j.create(spiConfig
                .address(spiChannel)
                .mode(SpiMode.MODE_0)
                .baud(speed)
                .id("SPI" + 0)
            );

        DigitalOutputConfigBuilder outputConfig = RPEnvironment.getOutputConfig();
        DigitalOutput digitalOutput = pi4j.create(outputConfig
            .address(NRSTPD)
            .id("pin" + NRSTPD)
        );
        digitalOutput.on();

        reset();
        writeToRC522(TModeReg, (byte)0x8D);
        writeToRC522(TPrescalerReg, (byte)0x3E);
        writeToRC522(TReloadRegL, (byte) 30);
        writeToRC522(TReloadRegH, (byte) 0);
        writeToRC522(TxAutoReg, (byte) 0x40);
        writeToRC522(ModeReg, (byte) 0x3D);
        antennaOn();
    }


    private void reset() {
        writeToRC522(CommandReg, PCD_RESETPHASE);
    }

    private void writeToRC522(byte addr, byte val) {
        byte data[] = new byte[2];
        data[0] = (byte) ((addr << 1) & 0x7E);
        data[1] = val;
        byte output[] = new byte[3];
        int result = spi.transfer(data, output);
        if (result == -1) {
            System.out.println("Schreibfehler, Addresse="+addr+", Wert="+val);
        }
    }

    private byte readFromRC522(byte addr) {
        byte data[] = new byte[2];
        data[0] = (byte) (((addr << 1) & 0x7E) | 0x80);
        data[1] = 0;
        byte output[] = new byte[3];
        int result = spi.transfer(data, output);
        if(result == -1) {
            System.out.println("Lesefehler, Addresse="+addr);
        }
        return output[1];
    }

    private void setBitMask(byte addr, byte mask) {
        byte val = readFromRC522(addr);
        writeToRC522(addr, (byte)(val | mask));
    }

    private void clearBitMask(byte addr, byte mask) {
        byte val = readFromRC522(addr);
        writeToRC522(addr, (byte)(val & (~mask)));
    }

    private void antennaOn() {
        byte val = readFromRC522(TxControlReg);
        if ( (val & (byte)0x03) != (byte)0x03) {
            setBitMask(TxControlReg,(byte) 0x03);
        }
    }

    private int request(byte reqMode, int[] backBits) {
        int status;
        byte tagType[] = new byte[1];
        byte dataBack[] = new byte[16];
        int backLen[] = new int[1];

        writeToRC522(BitFramingReg, (byte)0x07);

        tagType[0] = reqMode;
        backBits[0] = 0;
        status = writeToCard(PCD_TRANSCEIVE, tagType, 1, dataBack, backBits, backLen);
        if (status != MI_OK || backBits[0] != 0x10){
            status = MI_ERR;
        }

        return status;
    }

    private int writeToCard(byte command, byte[] data, int dataLen, byte[] backData, int[] backBits, int[] backLen) {
        int status = MI_ERR;
        byte irq = 0x00;
        byte irqWait = 0x00;
        byte lastBits = 0;
        int n = 0;
        int i = 0;

        backLen[0]=0;
        if (command == PCD_AUTHENT) {
            irq = 0x12;
            irqWait=0x10;
        }
        else if (command == PCD_TRANSCEIVE) {
            irq = 0x77;
            irqWait = 0x30;
        }

        writeToRC522(CommIEnReg, (byte)(irq|0x80));
        clearBitMask(CommIrqReg, (byte)0x80);
        setBitMask(FIFOLevelReg, (byte)0x80);

        writeToRC522(CommandReg, PCD_IDLE);

        for(i=0; i < dataLen; i++) {
            writeToRC522(FIFODataReg, data[i]);
        }

        writeToRC522(CommandReg, command);

        if(command == PCD_TRANSCEIVE){
            setBitMask(BitFramingReg, (byte)0x80);
        }

        i=2000;
        while (true) {
            n = readFromRC522(CommIrqReg);
            i--;
            if ((i == 0) || (n & 0x01) > 0 || (n & irqWait) > 0) {
                break;
            }
        }
        clearBitMask(BitFramingReg, (byte)0x80);

        if(i != 0) {
            if ((readFromRC522(ErrorReg) & 0x1B)==0x00) {
                status = MI_OK;

                if ((n & irq & 0x01) > 0)
                    status = MI_NOTAGERR;

                if (command == PCD_TRANSCEIVE) {
                    n = readFromRC522(FIFOLevelReg);
                    lastBits = (byte) (readFromRC522(ControlReg) & 0x07);
                    if (lastBits != 0) {
                        backBits[0] = (n - 1) * 8 + lastBits;
                    } else {
                        backBits[0] = n * 8;
                    }

                    if (n == 0){
                        n = 1;
                    }
                    if (n > this.MAX_LEN) {
                        n = this.MAX_LEN;
                    }

                    backLen[0] = n;
                    for (i = 0; i < n; i++) {
                        backData[i] = readFromRC522(FIFODataReg);
                    }
                }
            } else {
                status = MI_ERR;
            }
        }
        return  status;
    }

    //Antikollision Detektion.
    private int antiColl(byte[] backData) {
        int status;
        byte []serialNumber = new byte[2];
        int serialNumberCheck = 0;
        int backLen[] = new int[1];
        int backBits[] = new int[1];
        int i;

        writeToRC522(BitFramingReg, (byte)0x00);
        serialNumber[0] = PICC_ANTICOLL;
        serialNumber[1] = 0x20;
        status = writeToCard(PCD_TRANSCEIVE, serialNumber, 2, backData, backBits, backLen);
        if (status == MI_OK) {
            if (backLen[0] == 5) {
                for(i=0;i<4;i++) {
                    serialNumberCheck ^= backData[i];
                }
                if (serialNumberCheck != backData[4]) {
                    status = MI_ERR;
                    System.out.println("check error");
                }
            } else {
                status = MI_OK;
            }
        }
        return status;
    }

    /**
    * Liefert die ID der vorgelegten Karte als Byte-Array
    */
    public byte[] getCardID(){
        int backBits[] = new int[1];
        byte tagid[] = new byte[5];
        byte returnId[] = new byte[4];
        int status = this.request(PICC_REQIDL, backBits);
        if (status == MI_OK) {
            if(this.antiColl(tagid) == MI_OK) {
                for (int i = 0; i < 4; i++) {
                    returnId[i] = tagid[i];
                }
            }
        }
        return returnId;
    }

    /**
    * Liefert die ID der vorgelegten Karte als Int-Array
    */
    public int[] getCardIDAsIntArray(){
        byte tagid[] = this.getCardID();
        int returnId[] = new int[4];
        for (int i = 0; i < 4; i++) {
            returnId[i] = Byte.toUnsignedInt(tagid[i]);
        }
        return returnId;
    }

    /**
    * Liefert die ID der vorgelegten Karte als Zeichenkette
    */
    public String getCardIDAsString(){
        byte tagid[] = this.getCardID();
        String returnId = "";
        for (int i = 0; i < 4; i++) {
            returnId = returnId + " "  + Byte.toUnsignedInt(tagid[i]);
        }
        return returnId.trim();
    }

    /**
    * Test-Methode
    */
    public static void main(String[] args) {
        RPRfid rfid = new RPRfid();
        System.out.println(rfid.getCardIDAsString());
    }
}
