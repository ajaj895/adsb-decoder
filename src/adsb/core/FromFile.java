/*
 * Created on: Sep 11, 2020 
 * Author: Evan Colwell
 * 
 * Description: FromFile is a utility program to import adsb data from a file, a .txt, .csv
*/

//TODO: Docs & cat methods

package adsb.core;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;

/**
 *
 * @author Evan
 */
public class FromFile {
    
    private static LinkedList<String> adsb = new LinkedList<>();
    static Scanner sc;
    
    public static LinkedList<String> readFile(File f) throws FileNotFoundException{
        return adsbFile(f);
    }
    private static LinkedList<String> adsbFile(File f) throws FileNotFoundException{
        sc = new Scanner(f).useDelimiter(";");
        while(sc.hasNext()){
            adsb.add(sc.next()); // Adds the adsb code to the end of list
        }
        return adsb;
    }
    
    public static void debug(){ // Prints all the values currently in the adsb list
        for(int i = adsb.size(); i > 0; i--){
            System.out.println(adsb.remove());
        }
    }
}
