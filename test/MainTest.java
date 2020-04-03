/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import adsb.core.Adsb;
import adsb.core.AdsbFormatException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Evan
 */
public class MainTest {
    
    public MainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class AdsbDecoder with known args.
     */
    @Test
    public void testMainWArgs() {
        
        String[] args = new String[1];
        args[0] = "-g";
        System.out.println("main with " + args[0]);
        AdsbDecoder.main(args);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of main method, of class AdsbDecoder without args.
     */
    @Test
    public void testMainWoArgs(){
        System.out.println("Main without args");
        String[] args = new String[0];
        AdsbDecoder.main(args);
    }
    
    @Test
    public void testAdsbObject() throws AdsbFormatException{
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
    
}
