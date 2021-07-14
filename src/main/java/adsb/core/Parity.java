/*
 * Created on: Apr 16, 2020
 * Author: Evan Colwell
 *
 * Description:
 */

package adsb.core;

/**
 *
 * @author Evan
 */
public class Parity {
    private int generator = 0b1111111111111010000001001;//binary litteral
    public int getParity(String binAdsb){
        return genCrc(binAdsb);
    }
    //Logically inspired by https://mode-s.org/decode/adsb/introduction.html
    private int genCrc(String binAdsb){//TODO: LOGIC NEEDS TO BE IMPLEMENTED

        int binArr[] = new int[112];

        for(int i = 0; i<binArr.length; i++){
            binArr[i] = Integer.valueOf(binAdsb.substring(i, i+1), 2);//sets binary numbers to the bin arr
        }

        int returnInt = 1;

        for(int i = 0; i<88; i++){
            if (binArr[i] == 1){
                //To be implemented later.

            }
        }
        return returnInt;
    }

}
