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
    
    //Array structure [0] = df/ca | [1] = ICAO | [2] = data | [3] = parity
    private String[] hex = new String[4];//seperated into 4 sections according to the ADS-B message structure.
    private String[] bin = new String[4];
    
    private int[] df = new int[2];//DF = first 2 bytes split into two sections the DF (5 bits) and CA (3 bits)
    private String icao;//ICAO is the unique hexidecimal identifier for radio transponders, it does not change per flight.
    private int[] data;//Data is going to be set by the data decoder, and varies in size depending on what ADS-B data message is being sent.
    /**
     * An Adsb constructor that takes an input String in hexadecimal format.
     * @param input A String representing a hexadecimal ADS-B message 
     */
    public Adsb(String input) throws AdsbFormatException, DatatypeFormatException{
        makeAdsb(input, false);
    }
    
    public Adsb(String input, boolean debug) throws AdsbFormatException, DatatypeFormatException{
        if(debug) System.out.println("Making ADSB object...");
        makeAdsb(input, debug);
        if(debug) System.out.println("...ADSB object created!");
    }
    
    private void makeAdsb(String input, boolean debug) throws AdsbFormatException, DatatypeFormatException{
        hex = validateAdsb(input, debug);
        df = decDf(bin[0], debug);
        icao = hex[1];
        data = decData(bin[2], debug);
    }
    
    public String[] getHex(){
        return hex;
    }
    
    public String[] getBin(){
        return bin;
    }
    
    public String getHexToString(){
        return arrToString(hex);
    }
    
    public String getBinToString(){
        return arrToString(bin);
    }
    
    
    public String toString(boolean verbose) throws DatatypeFormatException{
        return toStr(verbose);
    }
    
    private String toStr(boolean verbose) throws DatatypeFormatException{
        return ToStr.toString(data, verbose);
    }
    
    //-- Getter Methods --
    public String getDf(){
        String sDf = df[0] + " " + df[1];
        return sDf;
    }
    
    public String getIcao(){
        return icao;
    }
    
    public String getData(){
        
        String sData = "";
        
        for(int i : data){
            sData = sData + " " + i;
        }
        
        return sData;
    }
    
    /*
     TO BE IMPLEMENTED LATER WHEN PARITY IS IMPLEMENTED.
    public String getParity(){
        
    }
    */
    
    //-- Seting Methods --
    //df setter
    private int[] sDf(String bin) throws AdsbFormatException{
        df = decDf(bin);
        return df;
    }
    
    //icao setter
    private String sIcao(String hex){
        icao = hex;
        return icao;
    }
    
    //toString method takes an array and returns a string of the elements in that array
    private String arrToString(String[] in){
        String temp = "";
        for(int i = 0; i < in.length; i++){
            temp = temp + in[i];
        }
        return temp;
    }
    
    public String[] validateAdsb(String adsb) throws AdsbFormatException{
        return valAdsb(adsb, false);
    }
    
    public String[] validateAdsb(String adsb, boolean debug) throws AdsbFormatException{
        if(debug) System.out.println("Validating ADSB message...");
        String[] valid = valAdsb(adsb, debug);
        if(debug){
            System.out.println("...Validation complete! Values set to: ");
            for(int i = 0; i < valid.length; i++){
                System.out.print(valid[i]+ " ");
            }
            System.out.println();
        }
        return valid;
    }
    
    private String[] valAdsb(String adsb, boolean debug) throws AdsbFormatException{
        if(adsb.length() != 28) throw new AdsbFormatException("ERROR: ADS-B message not proper length");
        //adsb = getHexToBin(adsb);
        adsb = adsb.toUpperCase();
        if(debug) System.out.println("ADSB Message before validation: "+adsb);
        String[] hexOut = new String[]{adsb.substring(0, 2), adsb.substring(2, 8), adsb.substring(8, 22), adsb.substring(22)};
        String temp = "";
        if(debug) System.out.println("Validation for-loop: ");
        for(int i = 0; i < adsb.length(); i++){
            temp = temp + Decode.getHexToBin(adsb.substring(i, i+1));
            if(debug) System.out.println(temp);
        }
        bin[0] = temp.substring(0, 8);
        if(debug) System.out.println("Bin[0] set to: " + bin[0]);
        bin[1] = temp.substring(8, 32); 
        if(debug) System.out.println("Bin[1] set to: " + bin[1]);
        bin[2] = temp.substring(32, 88); 
        if(debug) System.out.println("Bin[2] set to: " + bin[2]);
        bin[3] = temp.substring(88, 112);
        if(debug) System.out.println("Bin[3] set to: " + bin[3]);
        return hexOut;
    }
    
    public String debug(){
        String db = "Hex: ";
        //hex
       for(int i = 0; i < hex.length; i++){
            if(hex[i] != null){
                db = db + hex[i] + " ";
            } else {
                break;
            }
        }
        db = db + "\n";
        
        //bin
        db = db + "Bin: ";
       
        for(int i = 0; i < bin.length; i++){
            if(bin[i] != null){
                db = db + bin[i] + " ";
            } else {
                break;
            }
        }
        db = db + "\n";
        
        //df
        db = db + "Df: ";
        for(int i = 0; i < df.length; i++){
            //if(df[i] != null){
            db = db + df[i] + " ";
            //} else {
            //    break;
            //}
        }
        db = db + "\n";
        
        //ICAO
        db = db + "ICAO: ";
        if(this.icao != null) db = db + icao;
        db = db + "\n";
        //data
        db = db + "Data: ";
        /*
        if(!this.data.equals(null)){
            for(int i = 0; i < data.length; i++){
                db = db + data[i] + " ";
            }
            db = db + "\n";
        }
        */
        return db;
    }
    
   

}
