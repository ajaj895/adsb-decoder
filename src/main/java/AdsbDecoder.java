
import java.util.Scanner;
import java.io.File;
//import java.io.FileReader;
import java.io.FileNotFoundException;

import adsb.core.Adsb;
import adsb.core.AdsbFormatException;
import adsb.core.DatatypeFormatException;
import adsb.core.Decode;
//import adsb.core.DataDecoder;
//import adsb.core.DatatypeFormatException;

/**
 * This is the main class for adsb decoding.
 *
 * @author Evan Summer 2019
 * Updated: 7/29/2021
 */
public class AdsbDecoder {

    //Decode d = new Decode();

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

        if (args.length > 0) { //if no input, default run graphics mode
            if (args[0].equalsIgnoreCase("-i")) {// i for interactive
                interactive();
                // GUI selection
            } else if (args[0].equalsIgnoreCase("-g")) {
                System.out.println("This function has not been added yet. Please pick another option.");
                //gui();
                // File input
            } else if (args[0].equalsIgnoreCase("-f") && args.length >= 2) {
                file(args);
                // Command Line (not interactive) with an input ADS-B message in args[1*]
            } else if (args[0].equalsIgnoreCase("-c") && args.length >= 2) {
                cli(args);
                // Help text
            } else if (args[0].equalsIgnoreCase("-h") || args[0].equalsIgnoreCase("--help")) {
                help();
            }
        } else {//CLI interactive is now default. Will reimplement GUI eventually.
            interactive();
        }

    }

    /**
     * Runs the interactive version of the program.
     */
    public static void interactive() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an adsb code: ");
        String adsbIn = sc.next();
        //TODO: Check input before passed to the decoder.
        try {
            Adsb adsb = new Adsb(adsbIn);
            System.out.println(adsb.toString(false)); // Prints the decoded message in less details.
        } catch (DatatypeFormatException e) {
            System.err.println("Error! Error in decoding the data of the ADS-B message: " + adsbIn);
            System.err.println("Exiting...");
            System.exit(1);
        } catch (AdsbFormatException e) {
            System.err.println("Error! Error in the format of the given ADS-B message: " + adsbIn);
            System.err.println("Exiting...");
            System.exit(1);
        }
    }

    /*
     * I am disabling this for now to focus on the algorithms.
     * I will reimplement it when the algorithm works reliably.

    public static void gui() {
        AdsbGui gui = new AdsbGui();
        gui.setVisible(true);
    }
    */

    /**
     * Run the file capable version of the program, taking file(s) as inputs in the args.
     * @param args An array of args from the main() program.
     */
    public static void file(String[] args){
        File fileIn; // To prevent crashes if file doesn't exist
        Scanner fileScan; // To prevent crashes if file doesn't exist

        for(int i = 1; i < args.length; i++){ // Data transformation might be needed if using GNU_Radio
            fileIn = new File(args[i]);
            try { // File scanning try-catch
                fileScan = new Scanner(fileIn);
                while(fileScan.hasNext()) {
                    String adsbIn = fileScan.nextLine();
                    try { // ADS-B try-catch
                        Adsb adsb = new Adsb(adsbIn);
                        System.out.println(adsb.toString(false)); // Prints decoded message in non-detailed form.
                    } catch (DatatypeFormatException e) {
                        System.err.println("Error! Error in decoding the data of the ADS-B message: " + adsbIn);
                        System.err.println("Exiting...");
                        System.exit(1);
                    } catch (AdsbFormatException e) {
                        System.err.println("Error! Error in the format of the given ADS-B message: " + adsbIn);
                        System.err.println("Exiting...");
                        System.exit(1);
                    }
                }
            } catch(FileNotFoundException e) {
                System.err.println("Input file: "+ fileIn +(" not found, aborting..."));
                System.exit(2);
            }

        }
    }

    /**
     * Run the command line version of the program, passing in the messages to be decoded in the args, similar to files.
     * @param args An array of strings of the args from the Main() program.
     */
    public static void cli(String[] args) {
        for(int i = 1; i < args.length; i++) {
            String adsbIn = args[i];
            if (adsbIn.length() == 28) {
                try { // TODO: Add debug checking
                    Adsb adsb = new Adsb(adsbIn);
                    System.out.println(adsb.toString(false)); //Prints out the non-detailed adsb message.
                } catch (AdsbFormatException e) {
                    System.err.println("Error! ADS-B Format incorrect with message: " + adsbIn);
                    System.err.println("Exiting...");
                    System.exit(1);
                } catch (DatatypeFormatException e) {
                    System.err.println("Error! ADS-B Datatype format incorrect with the message: " + adsbIn);
                    System.err.println("Exiting...");
                    System.exit(1);
                }
            } else {
                System.err.println("ADS-B length incorrect!");
                System.err.println("Exiting...");
                System.exit(1);
            }
            //TODO: more logic
        }
    }

    /**
     * Prints a helpful information message to the command line.
     */
    public static void help() {
        String helpText = "\n" // Used for wrong or empty args
                + "Usage: java -jar adsbProject [OPTION] [INPUT MESSAGE]... \n"
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
                + "NOTE: CLI mode is enabled by default, meaning that if no OPTIONS are inputted, the CLI will run.\n"
                + "\n"
                + "For more information, refer to the readme on the github page: <https://github.com/ajaj895/adsb-decoder>\n"
                + "\n";
        System.out.println(helpText);
    }

}
