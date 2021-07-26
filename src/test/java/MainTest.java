/*
*
* Author: Evan Colwell
* Created: 7/26/2021
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

    @BeforeClass
        public static void setupClass(){}

    @AfterClass
        public static void tearDownClass(){}

    @Before
        public void setUp(){}

    @After
        public void tearDown(){}

    // TODO: Re-add the tests from previous MainTest.java from before the Maven Update.

}
