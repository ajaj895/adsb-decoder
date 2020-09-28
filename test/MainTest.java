/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import adsb.core.Adsb;
import adsb.core.AdsbFormatException;
import adsb.core.DatatypeFormatException;
import adsb.core.FromFile;
import adsb.core.Parity;
import adsb.core.ToStr;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
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
    /*
    @Test
    public void testMainCliArgs(){
        System.out.println("Main test with CLI");
        String[] args = new String[1];
        args[0] = "-c";//-c is commandline
        AdsbDecoder.main(args);
    }
    */
    @Test
    public void testAdsbObject() throws AdsbFormatException, DatatypeFormatException {
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
    
    @Test
    public void testDataDecode() throws AdsbFormatException, DatatypeFormatException {
        System.out.println("Testing data decode functions");
        String testMsg = "8D4840D6202CC371C32CE0576098";//Aircraft ID test 
        Adsb adsb = new Adsb(testMsg);  
        //System.out.println
    }
    
    @Test
    public void testHelpMsg(){
        System.out.println("Main with --help in args");
        String[] args = new String[1];
        args[0]="-h";
        AdsbDecoder.main(args);
    }
    
    // Not ready to be tested yet
    /*
    @Test
    public void parityTest(){
        System.out.println("Testing parity");
        //System.out.println(Parity.getGenerator());
        
    }
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
    
    @Test
    public void toStringAdsbObjectTest() throws AdsbFormatException, DatatypeFormatException{
        System.out.println("Testing the full adsb object creation and toString method...");
        String testMsg = "8D4840D6202CC371C32CE0576098";//Aircraft ID test 
        Adsb adsb = new Adsb(testMsg);
        System.out.println("Testing non verbose mode...\n" + adsb.toString(false) + "\n" + "Testing verbose mode...");
        adsb.toString(true);//Testing verbose mode
    }
    
    @Test
    public void realWorldDataSmallTest() throws AdsbFormatException, DatatypeFormatException{
        System.out.println("Testing a small amount of real world data...");
        /*
        dbb8cef86b4e0bab6489d9c9a039;
        93565dfbaeeab96de0a2a30a9fa8;
        d7d1a2ed05a0271cc3e44aca69e4;
        dac8a3e5b913c3e0bf0f13c5d8f9;
        f916a5ddbcb329e4b0f0adb5067a;
        89459217cb3c70e9fdae5dc905dc;
        f2168ceb5e86508e709c7aefea94;
        a4f0cd1056e8322b4d1dc356853b;
        91078855fecb649e7632c4052613;
        afe7b539e5b148b9eded83ddea76;
        f2e1d74547090a51c359e914b125;
        8ee05b65a6d3efe36a6fd2a3be56;
        8a42295693c04f144ce4d161d52c;
        bce88d4a58abba6fa4b2cc2da684;
        9d5168ffb5065a43358bdeba7c1c;
        8b834cda6a98e2e7f81db96ae198;
        a8cea9f35d7cdb7082f45f449728;
        d2f3eda575d83fdd8995ddabe8e4;
        a20760ae27abc9ad8a43c4c91f08;
        f81a56854a06437dced957004075;
        acc883396683858dd6f2d6ccb922;
        dc38380b69e1142e375c2cf738fb;
        b8a6ae559a7458a531eaa9e82f20;
        9e06c29973eebe8ca57d742d3220;
        bdd16a4a86b7ea68423425cf36f6;
        850e3d7e4891a0dd7c7b476c3aed;
        */
        
        String[] testMsg = new String[]{
            "dbb8cef86b4e0bab6489d9c9a039",
            "93565dfbaeeab96de0a2a30a9fa8",
            "d7d1a2ed05a0271cc3e44aca69e4",
            "dac8a3e5b913c3e0bf0f13c5d8f9",
            "f916a5ddbcb329e4b0f0adb5067a",
            "89459217cb3c70e9fdae5dc905dc",
            "f2168ceb5e86508e709c7aefea94",
            "a4f0cd1056e8322b4d1dc356853b",
            "91078855fecb649e7632c4052613",
            "afe7b539e5b148b9eded83ddea76",
            "f2e1d74547090a51c359e914b125",
            "8ee05b65a6d3efe36a6fd2a3be56",
            "8a42295693c04f144ce4d161d52c",
            "bce88d4a58abba6fa4b2cc2da684",
            "9d5168ffb5065a43358bdeba7c1c",
            "8b834cda6a98e2e7f81db96ae198",
            "a8cea9f35d7cdb7082f45f449728",
            "d2f3eda575d83fdd8995ddabe8e4",
            "a20760ae27abc9ad8a43c4c91f08",
            "f81a56854a06437dced957004075",
            "acc883396683858dd6f2d6ccb922",
            "dc38380b69e1142e375c2cf738fb",
            "b8a6ae559a7458a531eaa9e82f20",
            "9e06c29973eebe8ca57d742d3220",
            "bdd16a4a86b7ea68423425cf36f6",
            "850e3d7e4891a0dd7c7b476c3aed", 
        };
        /*
        for(int i = 0; i < testMsg.length; i++){
            Adsb adsb = new Adsb(testMsg[i]);
            System.out.println(adsb.toString(true));
        }
        System.out.println("TEST COMPLETE!");
        */
        for(int i = 0; i < testMsg.length; i++){
            Adsb adsb = new Adsb(testMsg[i]);
            System.out.println(adsb.debug());
        }
    }
    
    //Tests reading from a file
    @Test
    public void fileTest() throws FileNotFoundException{
        System.out.println("Testing file reading...");
        File f = new File("C:\\Users\\Evan\\Documents\\NetBeansProjects\\adsbProject\\fileTest.txt");
        FromFile.readFile(f);
        FromFile.debug();
        System.out.println("Testing of file reading complete!");
    }
    
    //Tests real world decoding and data from a file
    @Test
    public void fileDecodeTest() throws FileNotFoundException, AdsbFormatException, DatatypeFormatException{
        System.out.println("Testing file reading and decoding from file...");
        File f = new File("C:\\Users\\Evan\\Documents\\NetBeansProjects\\adsbProject\\fileTest.txt");
        LinkedList<String> test = FromFile.readFile(f);
        System.out.println("\nList contains: ");
        FromFile.debug();
        System.out.println("\nAdsb decoding from file");
        for(int i = test.size(); i > 0; i--){ // For loop for removing nodes but also retreving the Adsb code.
            Adsb adsb = new Adsb(test.remove());
            System.out.println(adsb.debug());
        }
    }
    
}
