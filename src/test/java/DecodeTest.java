
/*
 *
 * Author: Evan Colwell
 * Created: 7/29/2021
 * Last Updated:
 * Description: This is the testing class for Decode class and decoding functionality
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
public class DecodeTest {

    /**
     * Test of the data decoding ability of the ADSB object
     * @throws DatatypeFormatException Throws datatype format exception when the datatype doesn't match any known datatype
     * @throws AdsbFormatException Throws adsb format exception when the format doesn't match standard adsb message size or characters
     */
    @Test
    public void testDataDecode() throws DatatypeFormatException, AdsbFormatException {
        System.out.println("Testing data decode functions");
        String testMsg = "8D4840D6202CC371C32CE0576098";//Aircraft ID test
        Adsb adsb = new Adsb(testMsg);
    }
}
