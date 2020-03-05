
package adsb.core;

/**
 *
 * @author Evan
 */
public class DataDecoder {
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
    /**
     * Decode takes in a string that represents the hexadecimal data portion of the ADS-B message, it decodes it into the binary representation of the data portion.
     * This method also determines the datatype code of the data portion and calls a method that decodes the message with respect to it's datatype. 
     * These datatype specific methods can also be called individually.
     * @param dataHex 
     * @throws adsb.core.DatatypeFormatException  
     * @throws adsb.core.AdsbFormatException 
     */
    public void decode(String dataHex) throws DatatypeFormatException, AdsbFormatException{
        dataDec(dataHex);
    }
    //-The Data payload, 7 bytes (56 bits) that is split into transmission type (the first 5 bits) and the actual data (the last 51 bits).
    private void dataDec(String dataHex) throws DatatypeFormatException, AdsbFormatException{
        if(dataHex.length() != 14){
            throw new DatatypeFormatException("Passed in datatype is not length 14, the passed in length is: "+dataHex.length());
        }
        String temp = "";
        for(int i = 0; i<dataHex.length(); i++){
            temp = temp + Decode.getHexToBin(dataHex.substring(i, i+1));
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
    //Datatype 1-4
    public static void airId(String binData){//pass in binary data
        
    }
    //DATATYPE 5-8
    public static void surfPos(String binData){//pass in binary data
        
    }
    //DATATYPE 9-18
    public static void airPosBaro(String binData){//binary pass in
        
    }
    //DATATYPE 19
    public static void airVelo(String binData){
        
    }
    //DATATYPE 20-22
    public static void airPosGnss(String binData){//binary pass
        
    }
    //DATATYPE 23-27 IS RESERVED 
    /*
    * public static void reserved(String binData){
    * 
    * }
    */
    //DATATYPE 28
    public static void airStat(String binData){
        
    }
    //DATATYPE 29
    public static void targetStat(String binData){
        
    }
    /*
    * DATATYPE 30 IS UNKNOWN, AS OF THE 1090MHZ RIDDLE
    */
    //DATATYPE 31
    public static void airOpStat(String binData){
        
    }

    
}
