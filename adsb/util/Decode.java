
package adsb.util;
/**
 *
 * @author Evan
 */
public class Decode {
     /**
     * getHexToBin is for converting a single hexadecimal character into a binary string WITHOUT removing any zeros
     * @param hex Hexadecimal string to be converted 
     * @return A string of binary representing a single hexadecimal character
     */
    public static String getHexToBin(String hex){
        return Hex(hex);
    }
    //Hex to binary method that getHexToBin calls
    private static String Hex(String hex){
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
    /**
     * getDf is a method that decodes the first 5 bits of the DF part of the ADS-B message from hexadecimal to binary into decimal notation as an integer 
     * @param adsbHex The whole ADS-B hexadecimal message
     * @return an integer that represents the DF
     */
    public static int getDf(String adsbHex){
        return Df(adsbHex);
    }
    /**
     * getCa is a method that decodes the last 3 bits of the DF part of the ADS-B message from hexadecimal to binary into decimal notation as an integer
     * @param adsbHex The whole ADS-B hexadecimal message
     * @return an integer that represents the CA
     */
    public static int getCa(String adsbHex){
        return Ca(adsbHex);
    }
    /**
     * getIcao gets the ICAO identification of the sender aircraft from the ADS-B message. This ICAO identification can be searched through a database to find the information about the sender aircraft.
     * @param adsbHex The whole ADS-B hexadecimal message
     * @return a String of the ICAO identification 
     */
    public static String getIcao(String adsbHex){
        return Icao(adsbHex);
    }
    /**
     * getDatatype decodes the first 5 bits of the data payload of the ADS-B message from hexadecimal to binary into decimal notation as an integer
     * @param adsbHex The whole ADS-B hexadecimal message
     * @return an integer that represents the datatype of the ADS-B data payload
     */
    public static int getDatatype(String adsbHex){
        return Datatype(adsbHex);
    }
    /**
     * getParity decodes the 24-bit parity checksum 
     * @param adsbHex The whole ADS-B hexadecimal message
     * @return an integer that represents the parity in decimal form, this may be binary later
     */
    public static int getParity(String adsbHex){
        return Parity(adsbHex);
    }
    //The method that getDf() calls
    private static int Df(String hexDf){
        char[] temp = hexDf.toCharArray();
        String[] stringTemp = new String[2];// NOTE: char 1 and 2 are bits 1-8 
        String dfStr = "";
        for(int i = 0; i < stringTemp.length; i++){
            stringTemp[i] = getHexToBin(String.valueOf(temp[i]));
            
            //df[i] = Integer.parseInt(stringTemp, 1);
        }
        
        for(int i = 0; i<stringTemp.length; i++){//df is the first 5 bits
            dfStr = dfStr + stringTemp[i];
        }
        //System.out.println("Test: "+dfStr);//for testing purposes.
        
        return(Integer.parseInt(dfStr.substring(0, 5), 2));//creates a substring of dfStr to truncate the string to the first 5 bits of the 8 bits
    }
    //The method that getCa()calls
    private static int Ca(String hexDf){
        char[] temp = hexDf.toCharArray();
        String[] stringTemp = new String[2];
        for(int i = 0; i < stringTemp.length; i++){
            stringTemp[i] = getHexToBin(String.valueOf(temp[i]));
            //df[i] = Integer.parseInt(stringTemp, 1);
        }
        String caStr = "";
        for(int i = 0; i < stringTemp.length; i++){//ca is the last 3 bits
            caStr = caStr + stringTemp[i];
        }
        return(Integer.parseInt(caStr.substring(5, 8), 2));//substring of the 5-8 bits of the 8 bits of the df
    }
    //The method that getIcao() calls
    private static String Icao(String adsbHex){
        return adsbHex.substring(2, 8);
    }
    //The method that getDatatype() calls
    private static int Datatype(String hexData){//TODO FIX THIS, DATA is bits 33-88 and the datatype is the first 5 of those bits (33-37)
        char[] temp = hexData.toCharArray();
        String[] stringTemp = new String[22];//2 DF hex, 6 ICAO hex, 13 DATA hex
        int g = 0;
        for(int i = 0; i < stringTemp.length; i++){//data starts at 32 and goes to 87, the data type is the first 5 bits, so this splits two hex values
            
            stringTemp[g] = getHexToBin(String.valueOf(temp[i]));
            //df[i] = Integer.parseInt(stringTemp, 1);
            g++;
        }
        String typeStr = "";
        for(int i = 0; i<stringTemp.length; i++){//data type is the first 5 bits
            typeStr = typeStr + stringTemp[i];
        }
        
        //System.out.println(typeStr.substring(32,37));
        return(Integer.parseInt(typeStr.substring(32, 37), 2));
        
    }
    //The method that getParity() calls
    private static int Parity(String hexParity){
        char[] temp = hexParity.toCharArray();
        String[] stringTemp = new String[28];//2 DF hex, 6 ICAO hex, 13 DATA hex, 6 PARITY hex
        int g = 0;
        for(int i = 0; i < stringTemp.length; i++){//parity starts at 88 and goes to 112,
            
            stringTemp[g] = getHexToBin(String.valueOf(temp[i]));
            //df[i] = Integer.parseInt(stringTemp, 1);
            g++;
        }
        String typeStr = "";
        for(int i = 0; i<stringTemp.length; i++){
            typeStr = typeStr + stringTemp[i];
        }
        
        
        return(Integer.parseInt(typeStr.substring(88, 112), 2));
    }
    
    // **** BINARY METHODS **** //
    //These next methods are used to get the binary versions of DF, Data, and Parity parts of the ADS-B message.
    private static String binDf(String hexDf){
        char[] temp = hexDf.toCharArray();
        String[] stringTemp = new String[22];
        int g = 0;
        for(int i = 0; i < stringTemp.length; i++){
            
            stringTemp[g] = getHexToBin(String.valueOf(temp[i]));
            //df[i] = Integer.parseInt(stringTemp, 1);
            g++;
        }
        String typeStr = "";
        for(int i = 0; i<stringTemp.length; i++){
            typeStr = typeStr + stringTemp[i];
        }
        
        //System.out.println(typeStr.substring(32,37));
        return(typeStr.substring(0, 8));
        
    }
        //binary data string
    private static String binData(String hexData){
        char[] temp = hexData.toCharArray();
        String[] stringTemp = new String[22];//2 DF hex, 6 ICAO hex, 13 DATA hex
        int g = 0;
        for(int i = 0; i < stringTemp.length; i++){//data starts at 32 and goes to 87, the data type is the first 5 bits, so this splits two hex values
            
            stringTemp[g] = getHexToBin(String.valueOf(temp[i]));
            //df[i] = Integer.parseInt(stringTemp, 1);
            g++;
        }
        String typeStr = "";
        for(int i = 0; i<stringTemp.length; i++){//data type is the first 5 bits
            typeStr = typeStr + stringTemp[i];
        }
        
        //System.out.println(typeStr.substring(32,37));
        return(typeStr.substring(32, 88));
    }
    private static String binParity(String hexParity){
        char[] temp = hexParity.toCharArray();
        String[] stringTemp = new String[22];
        int g = 0;
        for(int i = 0; i < stringTemp.length; i++){
            
            stringTemp[g] = getHexToBin(String.valueOf(temp[i]));
            //df[i] = Integer.parseInt(stringTemp, 1);
            g++;
        }
        String typeStr = "";
        for(int i = 0; i<stringTemp.length; i++){
            typeStr = typeStr + stringTemp[i];
        }
        
        //System.out.println(typeStr.substring(32,37));
        return(typeStr.substring(88, 112));
        
    }
    
    
}
