
package adsb.core;

/**
 *
 * @author Evan
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
    public int[] decode(String dataBin) throws DatatypeFormatException, AdsbFormatException{//returns an array of organized binary data in an array, (ie, it segments the data based on it's msg type)
        int[] decodedArr = dataDec(dataBin);
        return decodedArr;
    }
    //-The Data payload, 7 bytes (56 bits) that is split into transmission type (the first 5 bits) and the actual data (the last 51 bits).
    private int[] dataDec(String dataBin) throws DatatypeFormatException{
        int[] returnArr;//to be set in the switch
        Short tc = Short.parseShort(dataBin.substring(0, 5), 2);//the first 5 bits is the type code (tc)
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
                throw new DatatypeFormatException("Bad datatype: " + tc);
               
        }
        
        return returnArr;
    }
    /*
    private void dataDec(String dataBin) throws DatatypeFormatException, AdsbFormatException{
        if(dataBin.length() != 14){//needs testing but this is prolly unnecessary.
            throw new DatatypeFormatException("Passed in datatype is not length 14, the passed in length is: "+dataBin.length());
        }
        String temp = "";
        for(int i = 0; i<dataBin.length(); i++){
            temp = temp + Decode.getHexToBin(dataBin.substring(i, i+1));
        }
        System.out.println(temp);//For testing purposes
        int dt = Integer.parseInt(temp.substring(0, 5), 2);
        System.out.println("Datatype: "+dt);//for testing purposes
        switch(dt){//Datatype finding switch
            case 1:
                System.out.println("Aircraft Identification");
                airId(temp);
                break;
            case 2:
                System.out.println("Aircraft Identification");
                airId(temp);
                break;
            case 3:
                System.out.println("Aircraft Identification");
                airId(temp);
                break;
            case 4:
                System.out.println("Aircraft Identification");
                airId(temp);
                break;
            case 5:
                System.out.println("Surface Position");
                surfPos(temp);
                break;
            case 6:
                System.out.println("Surface Position");
                surfPos(temp);
                break;
            case 7:
                System.out.println("Surface Position");
                surfPos(temp);
                break;
            case 8:
                System.out.println("Surface Position");
                surfPos(temp);
                break;
            case 9:
                System.out.println("Airborn position with Barometer");
                airPosBaro(temp);
                break;
            case 10:
                System.out.println("Airborn position with Barometer");
                airPosBaro(temp);
                break;
            case 11:
                System.out.println("Airborn position with Barometer");
                airPosBaro(temp);
                break;
            case 12:
                System.out.println("Airborn position with Barometer");
                airPosBaro(temp);
                break;
            case 13:
                System.out.println("Airborn position with Barometer");
                airPosBaro(temp);
                break;
            case 14:
                System.out.println("Airborn position with Barometer");
                airPosBaro(temp);
                break;
            case 15:
                System.out.println("Airborn position with Barometer");
                airPosBaro(temp);
                break;
            case 16:
                System.out.println("Airborn position with Barometer");
                airPosBaro(temp);
                break;
            case 17:
                System.out.println("Airborn position with Barometer");
                airPosBaro(temp);
                break;
            case 18:
                System.out.println("Airborn position with Barometer");
                airPosBaro(temp);
                break;
            case 19:
                System.out.println("Airborn Velocity");
                airVelo(temp);
                break;
            case 20:
                System.out.println("Airborn position with GPS/GNSS hight");
                airPosGnss(temp);
                break;
            case 21:
                System.out.println("Airborn Position with GPS/GNSS hight");
                airPosGnss(temp);
                break;
            case 22:
                System.out.println("Airborn Position with GPS/GNSS hight");
                airPosGnss(temp);
                break;
            case 23:
                System.out.println("Reserved");
                break;
            case 24:
                System.out.println("Reserved");
                break;
            case 25:
                System.out.println("Reserved");
                break;
            case 26:
                System.out.println("Reserved");
                break;
            case 27:
                System.out.println("Reserved");
                break;
            case 28:
                System.out.println("Aircraft status");
                airStat(temp);
                break;
            case 29:
                System.out.println("Target state and status information");
                targetStat(temp);
                break;
            case 31:
                System.out.println("Aircraft operation status");
                airOpStat(temp);
                break;
        }
    }
    */
    //Datatype 1-4
    private int[] airId(String binData){//pass in binary data
        int[] returnInt = new int[9];
        returnInt[0] = Integer.parseInt(binData.substring(0, 5), 2);//TC from binary
        returnInt[1] = Integer.parseInt(binData.substring(5, 8), 2);//EC for defining what aircraft type from binary
        for(int i = 0; i < 7; i ++){
            returnInt[i+2] = Integer.parseInt(binData.substring((i*6)+8, ((i*6)*2)+8), 2);
        }
        return returnInt;
    }
    //DATATYPE 5-8
    private int[] surfPos(String binData){//pass in binary data
        //Feature to be implemented at a later date
        int[] returnInt = new int[2];
        System.out.println("Feature to be implemented at a later date.");
        return returnInt;
    }
    //DATATYPE 9-18
    private int[] airPosBaro(String binData){//binary pass in
        int[] returnInt = new int[2];
        System.out.println("Feature to be implemented at a later date.");
        return returnInt;
    }
    //DATATYPE 19
    private int[] airVelo(String binData){
        int[] returnInt = new int[2];
        System.out.println("Feature to be implemented at a later date.");
        return returnInt;
    }
    //DATATYPE 20-22
    private int[] airPosGnss(String binData){//binary pass
        int[] returnInt = new int[2];
        System.out.println("Feature to be implemented at a later date.");
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
        int[] returnInt = new int[2];
        System.out.println("Feature to be implemented at a later date.");
        return returnInt;
    }
    //DATATYPE 29
    private int[] targetStat(String binData){
        int[] returnInt = new int[2];
        System.out.println("Feature to be implemented at a later date.");
        return returnInt;
    }
    /*
    * DATATYPE 30 IS UNKNOWN, AS OF THE 1090MHZ RIDDLE
    */
    //DATATYPE 31
    private int[] airOpStat(String binData){
        int[] returnInt = new int[2];
        System.out.println("Feature to be implemented at a later date.");
        return returnInt;
    }

    
}
