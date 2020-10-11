
import java.util.Scanner;
import java.io.File;
//import java.io.FileReader;
import java.io.FileNotFoundException;
import adsb.core.Decode;
//import adsb.core.DataDecoder;
//import adsb.core.DatatypeFormatException;

/**
 * This is the main class for adsb decoding.
 *
 * @author Evan Summer 2019
 */
public class AdsbDecoder {

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
        
        File fileIn = null;
        Scanner fileScan = null;
        
        String adsbIn = "";
        
        if (args.length > 0) { //if no input, default run graphics mode
            if (args[0].equalsIgnoreCase("-i") || args[0].equalsIgnoreCase("i")) {// i for interactive
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

            } else if (args[0].equalsIgnoreCase("-g") || args[0].equalsIgnoreCase("g")) {// g for GUI
                AdsbGui gui = new AdsbGui();
                gui.setVisible(true);
            } else if ((args[0].equalsIgnoreCase("-f") || args[0].equalsIgnoreCase("f")) && args.length >= 2){// f for file
                for(int i = 1; i < args.length; i++){
                    fileIn = new File(args[i]);
                    try {
                        fileScan = new Scanner(fileIn);
                    } catch(FileNotFoundException e) {
                        System.err.println("Input file: "+ fileIn +(" not found, aborting..."));
                        System.exit(1);
                    }
                    //TODO: File reading logic
                }
            } else if ((args[0].equalsIgnoreCase("-c") || args[0].equalsIgnoreCase("c")) && args.length >= 2){// c for cli, with inputed adsb codes without being interactive
                for(int i = 1; i < args.length; i++){
                    adsbIn = args[i];
                    if(adsbIn.length() == 28){
                        
                    } else {
                        System.out.println("ADS-B length incorrect!");
                    }
                    //TODO: more logic
                }
            } else if ((args[0].equalsIgnoreCase("-h") || args[0].equalsIgnoreCase("h")) || args[0].equalsIgnoreCase("--help")){// h for help
                System.out.printf("\n"
                        + "Usage: java -jar adsbProject [OPTION] [INPUT]... \n"
                        + "Decode INPUT depending on OPTION.\n"
                        + "Example: java -jar adsbProject -c a8cea9f35d7cdb7082f45f449728\n"
                        + "\n"
                        + "Option selection and description:\n"
                        + "\t-c \t\tOPTION is the commandline, non-interactive mode\n"
                        + "\t-f \t\tOPTION gets input from FILE\n"
                        + "\t-g \t\tOPTION uses the GUI mode of the program\n"
                        + "\t-h, --help \tOPTION gets the help and usage of the decoder\n"
                        + "\t-i \t\tOPTION uses the interactive, commandline mode of the program\n"
                        + "\n"
                        + "NOTE: GUI mode is enabled by default, meaning that if no OPTIONS are inputted, the GUI will run.\n"
                        + "\n"
                        + "For more information, refer to the readme on the github page: <https://github.com/ajaj895/adsb-decoder>\n"
                        + "\n");
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
