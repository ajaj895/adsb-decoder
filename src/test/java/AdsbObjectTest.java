/*
 *
 * Author: Evan Colwell
 * Created: 7/29/2021
 * Last Updated:
 * Description: This is the testing class for ADSB object and all functionality of that object
 *
 */

import adsb.core.Adsb;
import adsb.core.AdsbFormatException;
import adsb.core.DatatypeFormatException;
import org.junit.*;
import org.junit.contrib.java.lang.system.Assertion;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import static org.junit.Assert.*;

/**
 *
 * @author Evan C.
 *
 */
public class AdsbObjectTest {

    /**
     * Tests the creation of the ADSB object creation
     * @throws AdsbFormatException If the ADSB message is not the right length or right characters
     * @throws DatatypeFormatException If the datatype does not match a known datatype
     */
    @Test
    public void adsbObjectTest() throws AdsbFormatException, DatatypeFormatException {
        System.out.println("Testing adsb object functions");
        String testMsg = "A968A02296B3E86190169F1D2C24";
        System.out.println(testMsg.length());
        Adsb adsb = new Adsb(testMsg);//TEST MESSAGE A968A02296B3E86190169F1D2C24
        String returnMsg = adsb.getHexToString();
        System.out.println(returnMsg);
        System.out.println(testMsg);
        if(!testMsg.equalsIgnoreCase(returnMsg)) fail("The messages do not equal");
        String returnMsgBin = adsb.getBinToString();
        System.out.println(returnMsgBin);
        System.out.println(returnMsgBin.length());
        if(returnMsgBin.length() != 112) fail("The message binary is not correct");
    }

    /**
     * Testing of the Adsb object creation and toString method
     * @throws AdsbFormatException If the ADSB message is not the right length or right characters
     * @throws DatatypeFormatException If the datatype does not match a known datatype
     */
    @Test
    public void toStringAdsbObjectTest() throws AdsbFormatException, DatatypeFormatException {
        System.out.println("Testing the full adsb object creation and toString method...");
        String testMsg = "8D4840D6202CC371C32CE0576098";//Aircraft ID test
        Adsb adsb = new Adsb(testMsg);
        System.out.println("Testing non verbose mode...\n" + adsb.toString(false) + "\n" + "Testing verbose mode...");
        adsb.toString(true);//Testing verbose mode
    }
}
