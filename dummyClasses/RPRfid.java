/**
 * Klasse zum Anschluss eines RFID-Empfängers an den Raspberry Pi.
 * Original aus Python MFRC522
 *
 * @author Johannes Pieper
 * @version 0.9
 */
public class RPRfid {
  

    public RPRfid(){
    }

    private void reset() {
    }

    public int request(byte reqMode, int[] backBits) {
        return 0;
    }

    private int writeToCard(byte command, byte[] data, int dataLen, byte[] backData, int[] backBits, int[] backLen) {
        return 0;
    }

    //Antikollision Detektion.
    public int antiColl(byte[] backData) {
        return 0;
    }

    public byte[] getCardID(){
        byte returnId[] = new byte[4];
        for (int i = 0; i < 4; i++) {
            returnId[i] = 0;
        }
        return returnId;
    }

    public int[] getCardIDAsIntArray(){
        int returnId[] = new int[4];
        for (int i = 0; i < 4; i++) {
            returnId[i] = 0;
        }
        return returnId;
    }

    public String getCardIDAsString(){
        byte tagid[] = this.getCardID();
        String returnId = "";
        for (int i = 0; i < 4; i++) {
            returnId = returnId + " "  + Byte.toUnsignedInt(tagid[i]);
        }
        return returnId.trim();
    }


    /**
    * Schalte GPIO ab und dereferenziere den GPIO und den Pin.
    */
    public void herunterfahren() {
    }


    public static void main(String[] args) {
        RPRfid rfid = new RPRfid();
        System.out.println(rfid.getCardIDAsString());
    }
}
