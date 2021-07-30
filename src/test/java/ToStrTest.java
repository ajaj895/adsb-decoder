/*
 *
 * Author: Evan Colwell
 * Created: 7/29/2021
 * Last Updated:
 * Description: This is the testing class for toStr
 *
 */

import adsb.core.ToStr;
import org.junit.*;
import org.junit.contrib.java.lang.system.Assertion;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import static org.junit.Assert.*;

/**
 *
 * @author Evan C.
 *
 */
public class ToStrTest {

    /**
     * Test of the toStr Aircraft Identification ADSB message
     */
    @Test
    public void toStrIdArrTest(){
        //11 12 13 49 48 50 51 32
        System.out.println("Testing the Aircraft ID array...");
        int[] test = new int[]{11, 12, 13, 49, 48, 50, 51, 32};
        for(int num: test){
            System.out.print(ToStr.testArr(num));
        }
        System.out.println();
    }
}
