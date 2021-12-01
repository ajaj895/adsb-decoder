/*
 * Created on: Nov 30, 2021
 * Author: Evan Colwell
 *
 * Description: This is a port listening class that will get the datastream from Dump1090.
 *
 * I will be using port 30002 for this class, it will get data in this format:
 * *8D451E8B99019699C00B0A81F36E;
 * It will end with a new line character. This information comes in as fast as 1090 gets it.
 *
 * More information about Dump1090 can be found here: https://github.com/antirez/dump1090
 */

package adsb.listener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket; //Needed for listening to Dump1090 ports
import java.net.Socket;

public class Dump1090Listener {

    final int portnum = 30002;

    private ServerSocket serverSock;
    private Socket clientSock;
    private PrintWriter out;
    private BufferedInputStream in;



    public Dump1090Listener() throws IOException {

        init();

    }


    private void init() throws IOException {

        serverSock = new ServerSocket(portnum); //Connects to the port number
        clientSock = serverSock.accept(); //Client connects to the server
        out = new PrintWriter(clientSock.getOutputStream(), true); //Gets the output from the client
        in = new BufferedInputStream(clientSock.getInputStream()); //Gets the input to the client

    }
}
