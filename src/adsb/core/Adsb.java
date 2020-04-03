/*
 * Created on: Mar 4, 2020 
 * Author: Evan Colwell
 * 
 * Description: 
*/

package adsb.core;

/**
 *
 * @author Evan
 */
public class Adsb extends DataDecoder {
    
    private String[] hex = new String[4];//seperated into 4 sections according to the ADS-B message structure.
    private String[] bin = new String[4];
    
    private int[] df = new int[2];//DF = first 2 bytes split into two sections the DF (5 bits) and CA (3 bits)
    private String icao;//ICAO is the unique hexidecimal identifier for radio transponders, it does not change per flight.
    private int[] data;//Data is going to be set by the data decoder, and varies in size depending on what ADS-B data message is being sent.
    /**
     * An Adsb constructor that takes an input String in hexadecimal format.
     * @param input A String representing a hexadecimal ADS-B message 
     */
    public Adsb(String input) throws AdsbFormatException{
        makeAdsb(input);
    }
    
    private void makeAdsb(String input) throws AdsbFormatException{
        hex = validateAdsb(input);
        df = getDf(bin[0]);
    }
    
    public String[] getHex(){
        return hex;
    }
    
    public String[] getBin(){
        return bin;
    }
    
    public String getHexToString(){
        return toString(hex);
    }
    
    public String getBinToString(){
        return toString(bin);
    }
    
    //-- Seting Methods --
    //df setter
    private int[] sDf(String bin) throws AdsbFormatException{
        df = getDf(bin);
        return df;
    }
    
    //icao setter
    private String sIcao(String hex){
        icao = hex;
        return icao;
    }
    
    //toString method takes an array and returns a string of the elements in that array
    private String toString(String[] in){
        String temp = "";
        for(int i = 0; i < in.length; i++){
            temp = temp + in[i];
        }
        return temp;
    }
    
    public String[] validateAdsb(String adsb) throws AdsbFormatException{
        return valAdsb(adsb);
    }
    
    private String[] valAdsb(String adsb) throws AdsbFormatException{
        if(adsb.length() != 28) throw new AdsbFormatException("ERROR: ADS-B message not proper length");
        //adsb = getHexToBin(adsb);
        adsb.toUpperCase();
        String[] hexOut = new String[]{adsb.substring(0, 2), adsb.substring(2, 8), adsb.substring(8, 22), adsb.substring(22)};
        String temp = "";
        for(int i = 0; i < adsb.length(); i++){
            temp = temp + Decode.getHexToBin(adsb.substring(i, i+1));
        }
        bin[0] = temp.substring(0, 8);
        bin[1] = temp.substring(8, 32); 
        bin[2] = temp.substring(32, 88); 
        bin[3] = temp.substring(88, 112);
        return hexOut;
    }
    
   

}
