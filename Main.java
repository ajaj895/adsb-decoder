
import java.util.Scanner;
import adsb.util.Decode;
import adsb.util.DataDecoder;
/**
 * This is the main class for adsb decoding.
 * @author Evan
 * Summer 2019
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
        Decode d = new Decode();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an adsb code: ");
        String adsbIn = sc.next();
        String df = "";
        String icao = "";
        String data = "";
        String parity = ""; 
        
        System.out.println("DF: "+d.getDf(adsbIn)+"\nCA: "+d.getCa(adsbIn)+"\nICAO: "+d.getIcao(adsbIn)+"\nDatatype: "+d.getDatatype(adsbIn)+"\nParity: "+d.getParity(adsbIn));//8dac85839909dc1198a416e9d120
        DataDecoder.decode(adsbIn.substring(8, 21));       
    }

}
