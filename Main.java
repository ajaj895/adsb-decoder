
import java.util.Scanner;
/**
 * This is the main class for adsb decoding.
 * @author Evan
 */
public class Main {
    /*
        ADSB messages are in hexadecimal.
        Adsb messages are broken up into four parts (in order): 
        -The DF , a byte (8 bits) that is split again into the DF (Downlink format)(the first 5 bits) and the CA (Capability)(the last 3 bits)
        -The ICAO, 3 bytes (24 bits) that is the ICAO callsign for that aircraft.
        -The Data payload, 7 bytes (56 bits) that is split into transmission type (the first 5 bits) and the actual data (the last 51 bits).
        -And the Parity checksum, the last 3 bytes (24 bits) in the ADSB message.
        In total, an ADSB message is 14 bytes long (112 bits) (28 characters in hexidecimal)
    */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an adsb code: ");
        String adsbIn = sc.next();
        char[] adsbArr = adsbIn.toCharArray();
        Integer[] adsbHex = new Integer[28]; 
        //Integer.parseInt(adsbIn, 16);
        for(int i = 0; i<adsbArr.length; i++){
            String temp = String.valueOf(adsbArr[i]);
            adsbHex[i] = Integer.parseInt(temp, 16);
            System.out.println(adsbHex[i]);//for testing purposes
        }
        /*System.out.println("DF and CA: "+adsbArr[0]+adsbArr[1]);
        System.out.print("ICAO: ");//2-8
        for(int i = 2; i<8; i++ ){
            System.out.print(adsbArr[i]);
        }
        System.out.print("\nData Payload: ");
        for(int i = 8; i<22; i++){
            System.out.print(adsbArr[i]);
        }
        System.out.print("\nParity: ");
        for(int i = 22; i<28; i++){
            System.out.print(adsbArr[i]);
        }
        int df = adsbArr[0];//+adsbArr[1];
        System.out.println("\n"+df);
        */
        String df = "";
        String icao = "";
        String data = "";
        String parity = ""; 
        for(int i = 0; i < adsbHex.length; i++){
            System.out.println(Integer.toBinaryString(adsbHex[i]));
            if(i<2){
                df = df+getHex(adsbHex[i]);
            } else if(i>1 && i<8){
                icao = icao + getHex(adsbHex[i]);
            } else if(i>7 && i<19) {
                data = data + getHex(adsbHex[i]);
            } else {
                parity = parity + getHex(adsbHex[i]);
            }
        }
        System.out.println(df+"\n"+icao+"\n"+data+"\n"+parity);
        System.out.println("DF: "+getDf(adsbIn)+"\nCA: "+getCa(adsbIn));//8dac85839909dc1198a416e9d120
                //+"\nDatatype: "+getDatatype(adsbIn)); +"\nParity: "+getParity(adsbIn));
        
    }
    private static String getHex(int hex){
        switch(hex){
            case 0:
                return("0000");
            case 1:
                return("0001");
            case 2:
                return("0010");
            case 3:
                return("0011");
            case 4:
                return("0100");
            case 5:
                return("0101");
            case 6:
                return("0110");
            case 7:
                return("0111");
            case 8:
                return("1000");
            case 9:
                return("1001");
            case 10:
                return("1010");
            case 11:
                return("1011");
            case 12:
                return("1100");
            case 13:
                return("1101");
            case 14:
                return("1110");
            default:
                return("1111");
                
        }
    }
    private static String getHex(String hex){
        switch(hex){
            case "0":
                return("0000");
            case "1":
                return("0001");
            case "2":
                return("0010");
            case "3":
                return("0011");
            case "4":
                return("0100");
            case "5":
                return("0101");
            case "6":
                return("0110");
            case "7":
                return("0111");
            case "8":
                return("1000");
            case "9":
                return("1001");
            case "a":
                return("1010");
            case "b":
                return("1011");
            case "c":
                return("1100");
            case "d":
                return("1101");
            case "e":
                return("1110");
            default:
                return("1111");
                
        }
    }
    private static int getDf(String hexDf){
        char[] temp = hexDf.toCharArray();
        String[] stringTemp = new String[2];// NOTE: char 1 and 2 are bits 1-8 
        String dfStr = "";
        for(int i = 0; i < stringTemp.length; i++){
            stringTemp[i] = getHex(String.valueOf(temp[i]));
            
            //df[i] = Integer.parseInt(stringTemp, 1);
        }
        
        for(int i = 0; i<stringTemp.length; i++){//df is the first 5 bits
            dfStr = dfStr + stringTemp[i];
        }
        //System.out.println("Test: "+dfStr);//for testing purposes.
        
        return(Integer.parseInt(dfStr.substring(0, 5), 2));//creates a substring of dfStr to truncate the string to the first 5 bits of the 8 bits
    }
    
    private static int getCa(String hexDf){
        char[] temp = hexDf.toCharArray();
        String[] stringTemp = new String[2];
        for(int i = 0; i < stringTemp.length; i++){
            stringTemp[i] = getHex(String.valueOf(temp[i]));
            //df[i] = Integer.parseInt(stringTemp, 1);
        }
        String caStr = "";
        for(int i = 0; i < stringTemp.length; i++){//ca is the last 3 bits
            caStr = caStr + stringTemp[i];
        }
        return(Integer.parseInt(caStr.substring(5, 8), 2));//substring of the 5-8 bits of the 8 bits of the df
    }
    /* 
        -Not needed as of yet.
    private static int getIcao(String hexIcao){
        
    }
    */
    //TODO: FIX THE OTHERS
    private static int getDatatype(String hexData){//TODO FIX THIS, DATA is bits 33-88 and the datatype is the first 5 of those bits (33-37)
        char[] temp = hexData.toCharArray();
        String[] stringTemp = new String[2];
        int g = 0;
        for(int i = 32; i < 32+stringTemp.length; i++){//data starts at 32 and goes to 87, the data type is the first 5 bits, so this splits two hex values
            
            stringTemp[g] = getHex(String.valueOf(temp[i]));
            //df[i] = Integer.parseInt(stringTemp, 1);
            g++;
        }
        String typeStr = "";
        for(int i = 0; i<stringTemp.length; i++){//data type is the first 5 bits
            typeStr = typeStr + stringTemp[i];
        }
        return(Integer.parseInt(typeStr.substring(0, 5), 2));
        
    }
    /*
    TODO: methods for each data type
    */
    private static int getParity(String hexParity){
        char[] temp = hexParity.toCharArray();
        String[] stringTemp = new String[24];
        for(int i = 32; i < 88+stringTemp.length; i++){//Parity starts at 88 and goes to 111
            stringTemp[i] = String.valueOf(temp[i]);
            //df[i] = Integer.parseInt(stringTemp, 1);
        }
        String parityStr = "";
        for(int i = 0; i<stringTemp.length; i++){
            parityStr = parityStr + stringTemp[i];
        }
        return(Integer.parseInt(parityStr, 2));
    }
    
}
