/*
 * Created on: Jul 10, 2020 
 * Author: Evan Colwell
 * 
 * Description: 
*/

package adsb.core;

/**
 *
 * @author Evan
 */
public class ToStr extends Decode {
    
    /*
     * This is an array used for id Characters for aircraft identification from the 1090mhz Riddle
     * The sequence should be A-Z is 1-26, Space or _ is 32, and 0-9 is 48-57. 
     * The # is unused characters.
    */
    final static String[] idChars = new String[]{
        "#", "A", "B", "C", "D", "E", "F", "G",
        "H", "I", "J", "K", "L", "M", "N", "O",
        "P", "Q", "R", "S", "T", "U", "V", "W",
        "X", "Y", "Z", "#", "#", "#", "#", "#",
        " ", "#", "#", "#", "#", "#", "#", "#",
        "#", "#", "#", "#", "#", "#", "#", "#",
        "0", "1", "2", "3", "4", "5", "6", "7",
        "8", "9", "#", "#", "#", "#", "#", "#"
    };

    public static String toString(int[] data, boolean verbose) throws DatatypeFormatException {
        return toString(data, verbose, false); // Port with a new argument.
    }
    
    public static String toString(int[] data, boolean verbose, boolean debug) throws DatatypeFormatException{
        String str = "";//This is the string to be returned
        int tc = data[0];
        if(debug) System.out.println(tc);//for testing purposes
        switch(tc){
            case 1://aircraft id 1-4
                str = airId(data, verbose);
                break;
            case 2:
                str = airId(data, verbose);
                break;
            case 3:
                str = airId(data, verbose);
                break;
            case 4:
                str = airId(data, verbose);
                break;
            case 5://surface position 5-8
                //returnArr = surfPos(dataBin);
                break;
            case 6:
                //returnArr = surfPos(dataBin);
                break;
            case 7:
                //returnArr = surfPos(dataBin);
                break;
            case 8:
                //returnArr = surfPos(dataBin);
                break;
            case 9://Airborn position 9-18 barometer
                //returnArr = airPosBaro(dataBin);
                break;
            case 10:
                //returnArr = airPosBaro(dataBin);
                break;
            case 11:
                //returnArr = airPosBaro(dataBin);
                break;
            case 12:
                //returnArr = airPosBaro(dataBin);
                break;
            case 13:
                //returnArr = airPosBaro(dataBin);
                break;
            case 14:
                //returnArr = airPosBaro(dataBin);
                break;
            case 15:
                //returnArr = airPosBaro(dataBin);
                break;
            case 16:
                //returnArr = airPosBaro(dataBin);
                break;
            case 17:
                //returnArr = airPosBaro(dataBin);
                break;
            case 18:
                //returnArr = airPosBaro(dataBin);
                break;
            case 19://airborn velocity
                //returnArr = airVelo(dataBin);
                break;
            case 20://airborn position 20-22
                //returnArr = airPosGnss(dataBin);
                break;
            case 21:
                //returnArr = airPosGnss(dataBin);
                break;
            case 22:
                //returnArr = airPosGnss(dataBin);
                break;
            case 23://reserved 23-27
                //returnArr = new int[1];
                break;
            case 24:
                //returnArr = new int[1];
                break;
            case 25:
                //returnArr = new int[1];
                break;
            case 26:
                //returnArr = new int[1];
                break;
            case 27:
                //returnArr = new int[1];
                break;
            case 28://aircraft status
                //returnArr = airStat(dataBin);
                break;
            case 29://target state and status information
                //returnArr = targetStat(dataBin);
                break;
            case 30://unknown
                //returnArr = new int[1];
                break;
            case 31://aircraft operation status
                //returnArr = airOpStat(dataBin);
                break;
            default://throw an error or bad msg or something
                //throw new DatatypeFormatException("Bad datatype: " + tc); for testing purposes
                System.out.println("Bad datatype "+tc);
                break;
               
        }
        return str;
    }
    
    private static String airId(int[] data, boolean verbose){//Pass in an already decoded data array
        //first 8 bits (the first two things in the array) of the data section are for tc and 
        String returnStr = "";
        if(data.length >= 2){
            for(int i = 2; i < data.length; i++){
                returnStr = returnStr + idChars[data[i]];
                if(verbose) System.out.println(idChars[data[i]]);//to print each character
            }
        } else {
            returnStr = "Data not long enough for airID";
            if(verbose) System.out.println("Data[] not long enough to produce an airID");
            //Throw an error here
            System.err.println("Data[] not long enough to produce an airID");
        }
        if(verbose) System.out.println(returnStr);
        return returnStr;
    }
    
    public static String testArr(int num){
        return idChars[num];
    }
}
//11 12 13 49 48 50 51 32 to test 