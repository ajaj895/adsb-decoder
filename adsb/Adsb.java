/*
 * This is the WIP adsb object to make it easier to manage and organize the pieces 
 * and parts of an ADS-B message, allowing for initial decoding and optimization of 
 * this ADS-B library/project.
 *
 * Evan Colwell
 * July 2019
*/

// This program was written in VIM so the formatting might be a bit off
// TODO: Check formatting and spelling
// TODO: Update the decode program
// TODO: Docs
// TODO: Add more variables as needed/wanted
// TODO: Datatype/data decoding method(s)
// TODO: Parity decoding and checking
// TODO: THIS
import adsb.util.Decode;

package adsb;

public class Adsb(){
	//important message constants
	final String origMsg;// Original message (in hex)
	final String[] hexMsgParts;// An array of the 4 components of the msg (DF/CA|ICAO|DATA PAYLOAD|PARITY) in hex.
	final String binMsg;// The whole message in binary
	final String[] binMsgParts;// An array of 4 components of the message in binary

	//handy variables for ease of use
	short df;//Downlink frequency
	short dataTC;//Data typecode used for data payload decoding
	//String icao;//ICAO identification (just use the hex code)| just use hexMsgParts[1] 
	//TODO: Add more variables as needed/wanted

	public Adsb(){
	
	}
	public Adsb(String hexMsg){
		origMsg = hexMsg;//set the original message for clarity
		newAdsb(origMsg);
	}
	private void newAdsb(String hexMsg){
		//---hexMsgParts[] setting---
		hexMsgParts[0] = hexMsg.substring(0,2);//   DF/CA
		hexMsgParts[1] = hexMsg.substring(2,8);//   ICAO
		hexMsgParts[2] = hexMsg.substring(8,22);//  Data payload
		hexMsgParts[3] = hexMsg.substring(22,28);// Parity
		
		//---hex to bin---
		String tmp = "";//temporary value
		for(int i = 0; i < hexMsg.length(); i++){
			tmp = tmp + Decode.getHexToBin(hexMsg.substring(i,i+1));//the hex to binary conversion
		}	
		binMsg = tmp;

		//---binMsgParts[] setting---
		binMsgParts[0] = hexMsg.substring(0,8);//    DF/CA
		binMsgParts[1] = hexMsg.substring(8,32);//   ICAO (NOT NECESSARY IN BINARY)
		binMsgParts[2] = hexMsg.substring(32,88);//  Data payload
		binMsgParts[3] = hexMsg.substring(88,112);// Parity
		
		//---DF---
		df = Short.parseShort(binMsgParts[0].substring(0,5), 2);//DF decoding

		//---dataTC---
		dataTC = Short.parseShort(binMsgParts[2].substring(0,5), 2);//dataTC decoding



	

	}
	//TODO: DOCS
	public String getOrigMsg(){
		return origMsg;
	}
	public String getBinMsg(){
		return binMsg;
	}
	public short getDf(){
		return df;
	}
	public short getDataTc(){
		return dataTC;
	}
	public String getIcao(){
		return hexMsgParts[1];
	}
	/*
	 * TODO: Datatype/data decoding method(s)
	 */

	/* public int[] getData(){
	 * 	return data(binMsgParts[2]);	
	 * }
	 * //human readable data, in the form of a formatted string
	 * public String getDataHr(){
	 * 	//TODO: THIS
	 * }
	 * private int[] data(String dataBin){
	 * 
	 * } 
	 */

	/*
	 * TODO: Parity decoding and checking
	 */

	/* //A true or false value if it passed the parity checksum check
	 * //false means that the data is not correct, and should be thrown out
	 * public boolean parityPass(){
	 * 	return parityCheck(binMsgParts[3]);
	 * }
	 *
	 * private boolean parityCheck(String binPari){
	 *
	 * }
	 */

}	
