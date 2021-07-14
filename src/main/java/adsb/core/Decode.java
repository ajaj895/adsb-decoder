package adsb.core;

//TODO: CLEAN UP CODE AND COMMENTED OUT CODE

/**
 *
 * @author Evan
 */
public class Decode {

    /*
    String lengths of the whole adsb message:
    -------------
    DF:         2
    ICAO:       6
    DATATYPE:  14
    PARITY:     6
    -------------
    TOTAL:     28
    */

    //TODO: REWRITE THESE METHODS FOR USING ARRAYS

    final static String[] bin = new String[]{
            "0000","0001","0010",
            "0011","0100","0101",
            "0110","0111","1000",
            "1001","1010","1011",
            "1100","1101","1110",
            "1111"
    };

    /**
     * getHexToBin is for converting a single hexadecimal character into a
     * binary string WITHOUT removing any zeros
     *
     * @param hex Hexadecimal string to be converted
     * @return A string of binary representing a single hexadecimal character
     */
    public static String getHexToBin(String hex) throws AdsbFormatException {
        return Hex(hex);
    }

    //Hex to binary method that getHexToBin calls
    private static String Hex(String inHex) throws AdsbFormatException {
        inHex = inHex.toUpperCase();
        switch (inHex) {
            case "0":
                return (bin[0]);
            case "1":
                return (bin[1]);
            case "2":
                return (bin[2]);
            case "3":
                return (bin[3]);
            case "4":
                return (bin[4]);
            case "5":
                return (bin[5]);
            case "6":
                return (bin[6]);
            case "7":
                return (bin[7]);
            case "8":
                return (bin[8]);
            case "9":
                return (bin[9]);
            case "A":
                return (bin[10]);
            case "B":
                return (bin[11]);
            case "C":
                return (bin[12]);
            case "D":
                return (bin[13]);
            case "E":
                return (bin[14]);
            case "F":
                return (bin[15]);
            default:
                throw new AdsbFormatException("Input ADS-B message contains non-hex values!");

        }
    }

    public static int[] decDf(String dfBin) throws AdsbFormatException {
        return Df(dfBin, false);
    }

    /*
     * getDf is a method that decodes the first 5 bits of the DF part of the
     * ADS-B message from binary into decimal notation as an
     * integer
     *
     * @param dfBin The DF portion of the ADS-B message in binary
     * @return an integer that represents the DF
     */
    public static int[] decDf(String dfBin, boolean debug) throws AdsbFormatException {
        return Df(dfBin, debug);
    }
    /*
    /**
     * getCa is a method that decodes the last 3 bits of the DF part of the
     * ADS-B message from hexadecimal to binary into decimal notation as an
     * integer
     *
     * @param adsbHex The whole ADS-B hexadecimal message
     * @return an integer that represents the CA
     *
    public int getCa(String adsbHex) throws AdsbFormatException {
        return Ca(adsbHex);
    }
    /**
     * getDatatype decodes the first 5 bits of the data payload of the ADS-B
     * message from hexadecimal to binary into decimal notation as an integer
     *
     * @param adsbHex The whole ADS-B hexadecimal message
     * @return an integer that represents the datatype of the ADS-B data payload
     *
    public int getDatatype(String adsbHex) throws AdsbFormatException {
        return Datatype(adsbHex);
    }
    /**
     * getParity decodes the 24-bit parity checksum
     *
     * @param adsbHex The whole ADS-B hexadecimal message
     * @return an integer that represents the parity in decimal form, this may
     * be binary later
     *
    public int getParity(String adsbHex) throws AdsbFormatException {
        return Parity(adsbHex);
    }
    */
    //The method that getDf() calls
    /*
    private int Df(String hexDf) throws AdsbFormatException {
        char[] temp = hexDf.toCharArray();
        String[] stringTemp = new String[2];// NOTE: char 1 and 2 are bits 1-8
        String dfStr = "";
        for (int i = 0; i < stringTemp.length; i++) {
            stringTemp[i] = getHexToBin(String.valueOf(temp[i]));
            //df[i] = Integer.parseInt(stringTemp, 1);
        }
        for (int i = 0; i < stringTemp.length; i++) {//df is the first 5 bits
            dfStr = dfStr + stringTemp[i];
        }
        //System.out.println("Test: "+dfStr);//for testing purposes.
        return (Integer.parseInt(dfStr.substring(0, 5), 2));//creates a substring of dfStr to truncate the string to the first 5 bits of the 8 bits
    }
    */

    private static int[] Df(String binDf, boolean debug){
        int[] returnDf = new int[2];
        //char[] temp = binDf.toCharArray();
        if(debug) System.out.print("DF decoding: ");
        returnDf[0] = Integer.parseInt(binDf.substring(0, 5), 2);//DF
        if(debug) System.out.print(returnDf[0] + " ");
        returnDf[1] = Integer.parseInt(binDf.substring(5), 2);//CA
        if(debug) System.out.println(returnDf[1]);
        if(debug) System.out.println("DF completed!");
        return returnDf;
    }
    /*
    //The method that getCa()calls
    private int Ca(String hexDf) throws AdsbFormatException {
        char[] temp = hexDf.toCharArray();
        String[] stringTemp = new String[2];
        for (int i = 0; i < stringTemp.length; i++) {
            stringTemp[i] = getHexToBin(String.valueOf(temp[i]));
            //df[i] = Integer.parseInt(stringTemp, 1);
        }
        String caStr = "";
        for (int i = 0; i < stringTemp.length; i++) {//ca is the last 3 bits
            caStr = caStr + stringTemp[i];
        }
        return (Integer.parseInt(caStr.substring(5, 8), 2));//substring of the 5-8 bits of the 8 bits of the df
    }
    */
    //The method that getDatatype() calls
    /*
    private int Datatype(String hexData) throws AdsbFormatException {//TODO FIX THIS, DATA is bits 33-88 and the datatype is the first 5 of those bits (33-37)
        char[] temp = hexData.toCharArray();
        String[] stringTemp = new String[22];//2 DF bin, 6 ICAO bin, 13 DATA bin
        int g = 0;
        for (int i = 0; i < stringTemp.length; i++) {//data starts at 32 and goes to 87, the data type is the first 5 bits, so this splits two bin values
            stringTemp[g] = getHexToBin(String.valueOf(temp[i]));
            //df[i] = Integer.parseInt(stringTemp, 1);
            g++;
        }
        String typeStr = "";
        for (int i = 0; i < stringTemp.length; i++) {//data type is the first 5 bits
            typeStr = typeStr + stringTemp[i];
        }
        //System.out.println(typeStr.substring(32,37));
        return (Integer.parseInt(typeStr.substring(32, 37), 2));
    }

    //The method that getParity() calls
    private int Parity(String hexParity) throws AdsbFormatException {
        char[] temp = hexParity.toCharArray();
        String[] stringTemp = new String[28];//2 DF bin, 6 ICAO bin, 13 DATA bin, 6 PARITY bin
        int g = 0;
        for (int i = 0; i < stringTemp.length; i++) {//parity starts at 88 and goes to 112,
            stringTemp[g] = getHexToBin(String.valueOf(temp[i]));
            //df[i] = Integer.parseInt(stringTemp, 1);
            g++;
        }
        String typeStr = "";
        for (int i = 0; i < stringTemp.length; i++) {
            typeStr = typeStr + stringTemp[i];
        }
        return (Integer.parseInt(typeStr.substring(88, 112), 2));
    }
    */

    // **** BINARY METHODS **** //
    //These next methods are used to get the binary versions of DF, Data, and Parity parts of the ADS-B message.
    //TODO: REWRITE THESE METHODS FOR USING THE ARRAY BASED OBJECT SYSTEM AND TO STREAMLINE THE CODE AND DECOMPLEXIFY
    private String binDf(String hexDf) throws AdsbFormatException {
        char[] temp = hexDf.toCharArray();
        String[] stringTemp = new String[22];
        int g = 0;
        for (int i = 0; i < stringTemp.length; i++) {

            stringTemp[g] = getHexToBin(String.valueOf(temp[i]));
            //df[i] = Integer.parseInt(stringTemp, 1);
            g++;
        }
        String typeStr = "";
        for (int i = 0; i < stringTemp.length; i++) {
            typeStr = typeStr + stringTemp[i];
        }

        //System.out.println(typeStr.substring(32,37));
        return (typeStr.substring(0, 8));

    }
    //binary data string

    private String binData(String hexData) throws AdsbFormatException {
        char[] temp = hexData.toCharArray();
        String[] stringTemp = new String[22];//2 DF bin, 6 ICAO bin, 13 DATA bin
        int g = 0;
        for (int i = 0; i < stringTemp.length; i++) {//data starts at 32 and goes to 87, the data type is the first 5 bits, so this splits two bin values

            stringTemp[g] = getHexToBin(String.valueOf(temp[i]));
            //df[i] = Integer.parseInt(stringTemp, 1);
            g++;
        }
        String typeStr = "";
        for (int i = 0; i < stringTemp.length; i++) {//data type is the first 5 bits
            typeStr = typeStr + stringTemp[i];
        }

        //System.out.println(typeStr.substring(32,37));
        return (typeStr.substring(32, 88));
    }
    // Method for converting the hexidecimal parity to a binary parity value.
    private String binParity(String hexParity) throws AdsbFormatException {
        char[] temp = hexParity.toCharArray();
        String[] stringTemp = new String[22];
        int g = 0;
        for (int i = 0; i < stringTemp.length; i++) {

            stringTemp[g] = getHexToBin(String.valueOf(temp[i]));
            //df[i] = Integer.parseInt(stringTemp, 1);
            g++;
        }
        String typeStr = "";
        for (int i = 0; i < stringTemp.length; i++) {
            typeStr = typeStr + stringTemp[i];
        }

        //System.out.println(typeStr.substring(32,37));
        return (typeStr.substring(88, 112));

    }

}