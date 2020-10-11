# README
A Java ADS-B decoder is a java program that will decode ADS-B aircraft data from the base hex message.

## AUTHOR
Evan Colwell
ec-colwell@wiu.edu
eccolwell99@gmail.com

## USAGE

To run the program do the following command:

java -jar path/to/jar/[todo build a jar]

Eventually I hope to make this an application that opens a java application and eventually activly decode messages in real time, but thats is far off for now.

## ABOUT

This project is work in progress ADS-B decoder for automated aircraft radio messages. At the moment, the project only decodes the downlink format (DF), capability (CA), and the ICAO address of the origin aircraft (a unique identifier of the origin aircraft and flight).
I am in the progress of adding decoding for the data of the ADS-B messages, different types and what not. I am also working on decoding the parity part of the message but that might take some time to work out.

For more information about ADS-B decoding, "The 1090MHz Riddle" is a good reference that I have been using as a reference to create this project at this website: https://mode-s.org/decode/adsb/introduction.html 


