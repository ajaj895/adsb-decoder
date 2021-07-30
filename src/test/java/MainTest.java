/*
*
* Author: Evan Colwell
* Created: 7/26/2021
* Last Updated: 7/29/2021
* Description: This is the main testing class for the ADS-B project. 
* This is meant to test the program as a whole and it's overall conjoined performance
* and functionality.
*
*/

import org.junit.*;
import org.junit.contrib.java.lang.system.Assertion; 
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import static org.junit.Assert.*;

/**
 *
 * @author Evan C.
 *
 */
public class MainTest {

    // Solution for this found here:
    // https://stackoverflow.com/questions/15990433/using-junit-on-code-that-terminates?noredirect=1&1q=1
    @Rule
        public final ExpectedSystemExit exit = ExpectedSystemExit.none();// For testing intentional exits.

    public MainTest(){}


    // TODO: Re-add the tests from previous MainTest.java from before the Maven Update.

    /**
     * Test of the main method with the known arg -c (non-interactive command line)
     */
    @Test
    public void testMainWCliArg(){
        String[] args = new String[2];
        args[0] = "-c";
        args[1] = "a8cea9f35d7cdb7082f45f449728";
        System.out.println("Testing main with -c a8cea9f35d7cdb7082f45f449728...");
        AdsbDecoder.main(args);
    }

    /**
     * Test of the main method with the known arg -f (file)
     */
    @Test
    public void testMainWFileArg(){
        exit.expectSystemExitWithStatus(1); //Throws an adsb format exception
        String[] args = new String[2];
        args[0] = "-f";
        args[1] = "C:\\Users\\Evan\\Documents\\Coding Adventures\\Personal Projects\\Personal Java\\Experimental" +
                "\\adsb-decoder\\fileTest.txt";
        System.out.println("Testing main with -f ../fileTest.txt...");
        AdsbDecoder.main(args);
    }

    /**
     * Test of the main method with the known arg -g (GUI)
     */
    @Test
    public void testMainWGuiArg(){
        String[] args = new String[1];
        args[0] = "-g";
        System.out.println("Testing main with -g...");
        AdsbDecoder.main(args);
    }

    /**
     * Test of the main method with the known arg -h (help)
     */
    @Test
    public void testMainWHelpArg(){
        String[] args = new String[1];
        args[0] = "-h";
        System.out.println("Testing main with -h...");
        AdsbDecoder.main(args);
    }


    /*
    @Test
    public void testMainWInteractiveArg(){
        exit.expectSystemExit();
        String[] args = new String[1];
        args[0] = "-i";
        System.out.println("Testing main with -i...");
        AdsbDecoder.main(args);
    }
    */


    /**
     * Test of the main method with an unknown arg
     */
    @Test
    public void testMainWUnknownArgs(){
        String[] args = new String[1];
        args[0] = "-Rock-N'-Roll";
        System.out.println("Testing main with unknown args...");
        AdsbDecoder.main(args);
    }

    /*
    @Test
    public void testMainWOArgs(){
        System.out.println("Testing main without args...");
        String[] args = new String[0];
        AdsbDecoder.main(args);
    }

     */


}
