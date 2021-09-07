
package adsb.core;

/**
 *
 * @author Evan
 *
 * Created: 3/4/2020
 * Updated: 7/13/2021
 */
public class DataDecoder extends Decode {
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

    /**
     * Decode takes in a string that represents the hexadecimal data portion of the ADS-B message, it decodes it into the binary representation of the data portion.
     * This method also determines the datatype code of the data portion and calls a method that decodes the message with respect to it's datatype. 
     * These datatype specific methods can also be called individually.
     * @param dataBin
     * @throws adsb.core.DatatypeFormatException
     * @throws adsb.core.AdsbFormatException
     */
    public int[] decData(String dataBin) throws DatatypeFormatException, AdsbFormatException{//returns an array of organized binary data in an array, (ie, it segments the data based on it's msg type)
        int[] decodedArr = dataDec(dataBin, false);
        return decodedArr;
    }
    public int[] decData(String dataBin, boolean debug) throws DatatypeFormatException, AdsbFormatException{//returns an array of organized binary data in an array, (ie, it segments the data based on it's msg type)
        int[] decodedArr = dataDec(dataBin, debug);
        return decodedArr;
    }

    //-The Data payload, 7 bytes (56 bits) that is split into transmission type (the first 5 bits) and the actual data (the last 51 bits).
    private int[] dataDec(String dataBin, boolean debug) throws DatatypeFormatException{
        int[] returnArr;//to be set in the switch
        Short tc = Short.parseShort(dataBin.substring(0, 5), 2);//the first 5 bits is the type code (tc)
        if(debug) System.out.print("Data decoding: \nTC: " + tc);
        if(debug) System.out.print("\n TC Substrings: Binary: " + dataBin.substring(0, 5) + " Decoded: " + Short.parseShort(dataBin.substring(0, 5), 2));
        if(debug) System.out.println(tc);//for testing purposes
        switch(tc){
            case 1://aircraft id 1-4
                returnArr = airId(dataBin);
                break;
            case 2:
                returnArr = airId(dataBin);
                break;
            case 3:
                returnArr = airId(dataBin);
                break;
            case 4:
                returnArr = airId(dataBin);
                break;
            case 5://surface position 5-8
                returnArr = surfPos(dataBin);
                break;
            case 6:
                returnArr = surfPos(dataBin);
                break;
            case 7:
                returnArr = surfPos(dataBin);
                break;
            case 8:
                returnArr = surfPos(dataBin);
                break;
            case 9://Airborn position 9-18 barometer
                returnArr = airPosBaro(dataBin);
                break;
            case 10:
                returnArr = airPosBaro(dataBin);
                break;
            case 11:
                returnArr = airPosBaro(dataBin);
                break;
            case 12:
                returnArr = airPosBaro(dataBin);
                break;
            case 13:
                returnArr = airPosBaro(dataBin);
                break;
            case 14:
                returnArr = airPosBaro(dataBin);
                break;
            case 15:
                returnArr = airPosBaro(dataBin);
                break;
            case 16:
                returnArr = airPosBaro(dataBin);
                break;
            case 17:
                returnArr = airPosBaro(dataBin);
                break;
            case 18:
                returnArr = airPosBaro(dataBin);
                break;
            case 19://airborn velocity
                returnArr = airVelo(dataBin);
                break;
            case 20://airborn position 20-22
                returnArr = airPosGnss(dataBin);
                break;
            case 21:
                returnArr = airPosGnss(dataBin);
                break;
            case 22:
                returnArr = airPosGnss(dataBin);
                break;
            case 23://reserved 23-27
                returnArr = new int[1];
                break;
            case 24:
                returnArr = new int[1];
                break;
            case 25:
                returnArr = new int[1];
                break;
            case 26:
                returnArr = new int[1];
                break;
            case 27:
                returnArr = new int[1];
                break;
            case 28://aircraft status
                returnArr = airStat(dataBin);
                break;
            case 29://target state and status information
                returnArr = targetStat(dataBin);
                break;
            case 30://unknown
                returnArr = new int[1];
                break;
            case 31://aircraft operation status
                returnArr = airOpStat(dataBin);
                break;
            default://throw an error or bad msg or something
                if(debug) System.out.println("Bad datatype: " + tc);

                returnArr = null;//for testing reasons
                //throw new DatatypeFormatException("Bad datatype: " + tc);
                //returnArr = null;//for testing reasons
                //break;//for testing reasons

        }

        return returnArr;
    }

    //Datatype 1-4
    private int[] airId(String binData){//pass in binary data
        int[] returnInt = new int[10];
        returnInt[0] = Integer.parseInt(binData.substring(0, 5), 2);    //TC from binary
        returnInt[1] = Integer.parseInt(binData.substring(5, 8), 2);    //EC for defining what aircraft type from binary
        for(int i = 0; i < 8; i ++){
            returnInt[i+2] = Integer.parseInt(binData.substring((i*6)+8, ((i*6)+6)+8), 2);
        }
        return returnInt;
    }
    //DATATYPE 5-8
    private int[] surfPos(String binData){//pass in binary data
        //Feature to be implemented at a later date
        int[] returnInt = new int[8];
        returnInt[0] = Integer.parseInt(binData.substring(0, 5), 2);    // TC from binary. 5 bits.
        returnInt[1] = Integer.parseInt(binData.substring(5, 12), 2);   // Speed encoded from 0-127 (7 Bits) 125-127 are reserved. 7 bits.
        returnInt[2] = Integer.parseInt(binData.substring(12,13), 2);   // Ground track status (0 invalid, 1 valid). 1 bit.
        returnInt[3] = Integer.parseInt(binData.substring(13, 20), 2);  // Ground track is the following formula x = (360n)/128 where n = the encoded value. 7 bits.
        returnInt[4] = Integer.parseInt(binData.substring(20, 21), 2);  // Time. 1 bit.
        returnInt[5] = Integer.parseInt(binData.substring(21, 22), 2);  // CPR Format (0 - Even frame, 1 - Odd frame). 1 bit.
        returnInt[6] = Integer.parseInt(binData.substring(22, 39), 2);  // Encoded Latitude. 17 bits.
        returnInt[7] = Integer.parseInt(binData.substring(39, 56), 2);  // Encoded Longitude. 17 bits.
        //System.out.println("Surface Position Feature to be implemented at a later date.");
        return returnInt;
    }
    //DATATYPE 9-18
    private int[] airPosBaro(String binData){//binary pass in
        int[] returnInt = new int[8];
        returnInt[0] = Integer.parseInt(binData.substring(0, 5), 2);    // TC from binary. 5 bits.
        returnInt[1] = Integer.parseInt(binData.substring(5, 7), 2);    // Surveillance Status (0 - No condition, 1 - Permanent Alert, 2 - Temporary Alert, 3 - SPI condition). 2 bits.
        returnInt[2] = Integer.parseInt(binData.substring(7, 8), 2);    // Single Antenna flag. 1 bit.
        returnInt[3] = Integer.parseInt(binData.substring(8, 20), 2);   // Encoded Altitude. 12 bits.
        returnInt[4] = Integer.parseInt(binData.substring(20, 21), 2);  // Time. 1 bit.
        returnInt[5] = Integer.parseInt(binData.substring(21, 22), 2);  // CPR format (0 - Even Frame, 1 - Odd Frame). 1 bit.
        returnInt[6] = Integer.parseInt(binData.substring(22, 39), 2);  // Encoded latitude. 17 bits.
        returnInt[7] = Integer.parseInt(binData.substring(39, 56), 2);  // Encoded longitude. 17 bits.
        //System.out.println("Air Position Barometer Feature to be implemented at a later date.");
        return returnInt;
    }
    //DATATYPE 19
    private int[] airVelo(String binData){
        int[] returnInt = new int[12];
        returnInt[0] = Integer.parseInt(binData.substring(0, 5), 2);    // TC from binary. 5 bits.
        returnInt[1] = Integer.parseInt(binData.substring(5, 8), 2);    // Sub-Type. 3 bits.
        returnInt[2] = Integer.parseInt(binData.substring(8, 9), 2);    // Intent change flag. 1 bit.
        returnInt[3] = Integer.parseInt(binData.substring(9, 10), 2);   // IFR capability flag. 1 bit.
        returnInt[4] = Integer.parseInt(binData.substring(10, 13), 2);  // Navigation uncertainty category for velocity. 3 bits.
        returnInt[5] = Integer.parseInt(binData.substring(13, 35), 2);  // Sub-type specific fields. 22 bits.
        returnInt[6] = Integer.parseInt(binData.substring(35, 36), 2);  // Source bit for vertical rate. 1 bit.
        returnInt[7] = Integer.parseInt(binData.substring(36, 37), 2);  // Sign bit for vertical rate. 1 bit.
        returnInt[8] = Integer.parseInt(binData.substring(37, 46), 2);  // Vertical rate. 9 bits.
        returnInt[9] = Integer.parseInt(binData.substring(46, 48), 2);  // Reserved. 2 bits.
        returnInt[10] = Integer.parseInt(binData.substring(48, 49), 2); // Sign bit for GNSS and Baro Altitudes difference. 1 bit.
        returnInt[11] = Integer.parseInt(binData.substring(49, 56), 2); // Difference between GNSS and Bara altitudes. 7 bits.
        //System.out.println("Air Velocity Feature to be implemented at a later date.");
        return returnInt;
    }
    //DATATYPE 20-22
    private int[] airPosGnss(String binData){//binary pass
        int[] returnInt = new int[8];
        returnInt[0] = Integer.parseInt(binData.substring(0, 5), 2);   // TC from binary. 5 bits.
        returnInt[1] = Integer.parseInt(binData.substring(5, 7), 2);   // Surveillance Status (0 - No condition, 1 - Permanent Alert, 2 - Temporary Alert, 3 - SPI condition). 2 bits.
        returnInt[2] = Integer.parseInt(binData.substring(7, 8), 2);   // Single Antenna flag. 1 bit.
        returnInt[3] = Integer.parseInt(binData.substring(8, 20), 2);  // Encoded Altitude. 12 bits.
        returnInt[4] = Integer.parseInt(binData.substring(20, 21), 2); // Time. 1 bit.
        returnInt[5] = Integer.parseInt(binData.substring(21, 22), 2); // CPR format (0 - Even Frame, 1 - Odd Frame). 1 bit.
        returnInt[6] = Integer.parseInt(binData.substring(22, 39), 2); // Encoded latitude. 17 bits.
        returnInt[7] = Integer.parseInt(binData.substring(39, 56), 2); // Encoded longitude. 17 bits.
        //System.out.println("GNSS Air Position Feature to be implemented at a later date.");
        return returnInt;
    }
    //DATATYPE 23-27 IS RESERVED 
    /*
     * public static void reserved(String binData){
     *
     * }
     */
    //DATATYPE 28
    private int[] airStat(String binData){
        int[] returnInt = new int[1];
        System.out.println("Air Statistics Feature to be implemented at a later date.");
        return returnInt;
    }
    //DATATYPE 29
    private int[] targetStat(String binData){
        int[] returnInt = new int[1];
        System.out.println("Target Statistics Feature to be implemented at a later date.");
        return returnInt;
    }
    /*
     * DATATYPE 30 IS UNKNOWN, AS OF THE 1090MHZ RIDDLE
     */
    //DATATYPE 31 - This follows Version 1 and onward transponders.
    private int[] airOpStat(String binData){
        int[] returnInt = new int[12];
        returnInt[0] = Integer.parseInt(binData.substring(0, 5), 2);   // TC from binary. 5 bits.
        returnInt[1] = Integer.parseInt(binData.substring(5, 8), 2);   // Sub-type code. 3 bits.
        returnInt[2] = Integer.parseInt(binData.substring(8, 24), 2);  // Capacity class codes. 16 bits.
        returnInt[3] = Integer.parseInt(binData.substring(24, 40), 2); // Operational mode codes. 16 bits.
        returnInt[4] = Integer.parseInt(binData.substring(40, 43), 2); // ADS-B Version number. 3 bits.
        returnInt[5] = Integer.parseInt(binData.substring(43, 44), 2); // NIC supplement. 1 bit.
        returnInt[6] = Integer.parseInt(binData.substring(44, 48), 2); // Navigational accuracy category - position. 4 bits.
        returnInt[7] = Integer.parseInt(binData.substring(48, 50), 2); // Barometric altitude quality. 2 bits.
        returnInt[8] = Integer.parseInt(binData.substring(50, 52), 2); // Surveillance integrity level. 2 bits.
        returnInt[9] = Integer.parseInt(binData.substring(52, 53), 2); // Barometric altitude integrity. 1 bit.
        returnInt[10] = Integer.parseInt(binData.substring(53, 54), 2);// Horizontal Reference direction. 1 bit.
        returnInt[11] = Integer.parseInt(binData.substring(54, 56), 2);// Reserved. 2 bits.
        //System.out.println("Air Operation Statistics Feature to be implemented at a later date.");
        return returnInt;
    }


}