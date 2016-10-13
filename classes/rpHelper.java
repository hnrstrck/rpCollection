import com.pi4j.io.gpio.*;

public class rpHelper
{

    /*
     * Zuordnung Eingabe (int) -> Pin erfolgt über Position des Arrays
     */

    
    
    /**
     * J8-Pin Nummerierung wie auf der Pi4J Website 
     */
    public static final Pin[] pinArray = new Pin[] {
            RaspiPin.GPIO_00,  //0
            RaspiPin.GPIO_01,  //1
            RaspiPin.GPIO_02,  //2
            RaspiPin.GPIO_03,  //3
            RaspiPin.GPIO_04,  //4
            RaspiPin.GPIO_05,  //5
            RaspiPin.GPIO_06,  //6
            RaspiPin.GPIO_07,  //7
            RaspiPin.GPIO_08,  //8
            RaspiPin.GPIO_09,  //9
            RaspiPin.GPIO_10,  //10
            RaspiPin.GPIO_11,  //11
            RaspiPin.GPIO_12,  //12
            RaspiPin.GPIO_13,  //13
            RaspiPin.GPIO_14,  //14
            RaspiPin.GPIO_15,  //15
            RaspiPin.GPIO_16,  //16
            RaspiPin.GPIO_17,  //17
            RaspiPin.GPIO_18,  //18
            RaspiPin.GPIO_19,  //19
            RaspiPin.GPIO_20   //20
        };

}
