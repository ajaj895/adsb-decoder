
import java.util.Scanner;
import adsb.util.AdsbGui;
import adsb.util.Decode;
import adsb.util.DataDecoder;
import adsb.util.DatatypeFormatException;

/**
 * This is the main class for adsb decoding.
 *
 * @author Evan Summer 2019
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
        String adsbIn = "";
        if (args.length > 0) { //if no input, default run graphics mode
            if (args[0].equalsIgnoreCase("-n") || args[0].equalsIgnoreCase("n")) {
                Scanner sc = new Scanner(System.in);//TODO: Do this in a seperate class
                System.out.println("Enter an adsb code: ");
                adsbIn = sc.next();
                /*
                System.out.println("DF: " + d.getDf(adsbIn) + "\nCA: " + d.getCa(adsbIn) + "\nICAO: " + d.getIcao(adsbIn) + "\nDatatype: " + d.getDatatype(adsbIn) + "\nParity: " + d.getParity(adsbIn));//8dac85839909dc1198a416e9d120
                try {
                    DataDecoder.decode(adsbIn.substring(8, 21));
                } catch (DatatypeFormatException e) {
                    System.err.println("Error: Invalid input");
                    System.out.println("Error: Invalid input");
                }
                */
                //TODO: Check input before passed to the decoder.

            } else if (args[0].equalsIgnoreCase("-g") || args[0].equalsIgnoreCase("g")) {
                AdsbGui gui = new AdsbGui();
                gui.setVisible(true);
            }
        } else {//gui is default
            AdsbGui gui = new AdsbGui();
            gui.setVisible(true);
        }
        /*
        String df = "";
        String icao = "";
        String data = "";
        String parity = ""; 
         */

    }

}
