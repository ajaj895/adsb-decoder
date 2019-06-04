# adsb-decoder
A Java ADS-B decoder.

This project is work in progress ADS-B decoder for automated aircraft radio messages. At the moment, the project only decodes the downlink format (DF), capability (CA), and the ICAO address of the origin aircraft (a unique identifier of the origin aircraft and flight).
I am in the progress of adding decoding for the data of the ADS-B messages, different types and what not. I am also working on decoding the parity part of the message but that might take some time to work out.
Eventually I hope to make this an application that opens a java application and eventually activly decode messages in real time, but thats is far off for now.

For more information about ADS-B decoding, "The 1090MHz Riddle" is a good reference that I have been using as a reference to create this project at this website: https://mode-s.org/decode/adsb/introduction.html 

This README and parts of the Main.java decoder have been written using VIM and I am new to VIM so excuse some mistakes and wierd formating.
