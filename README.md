# README
A Java ADS-B decoder is a java program that will decode ADS-B aircraft data from the base hex message.

## AUTHOR
Evan Colwell
ec-colwell@wiu.edu
eccolwell99@gmail.com

## USAGE

To run the program do the following command:

```java -jar path/to/jar/[todo build a jar]```

This program also handles args similar to how UNIX commands do, with -h or --help or just h giving you a brief overview of what the program does.

Eventually I hope to make this an application that opens a java application and eventually actively decode messages in real time, but that is far off for now.

### Dependencies

* JUnit 4.13.1 is required to run the tests for this project
* system-rules is also required to run the tests and can be found here: https://github.com/stefanbirkner/system-rules

I eventually will add Maven package handling working.

## ABOUT

This project is work in progress ADS-B decoder for automated aircraft radio messages. At the moment, the project only decodes the downlink format (DF), capability (CA), and the ICAO address of the origin aircraft (a unique identifier of the origin aircraft and flight).
I am in the progress of adding decoding for the data of the ADS-B messages, different types and what not. I am also working on decoding the parity part of the message but that might take some time to work out.

For more information about ADS-B decoding, "The 1090MHz Riddle" is a good reference that I have been using as a reference to create this project at this website: https://mode-s.org/decode/adsb/introduction.html 

## SCREENSHOTS

Screenshots will be added in the near future as I flush out the GUI and make it functional.

Here is what the simple GUI looks like:

![Simple GUI](/screenshots/PlainGUI.png)

Here is the GUI with some data, both the program and the GUI are prototypes so keep that in mind:

![Test data being used](/screenshots/DataTest.png)

## PROGRESS 

Currently the adsb-decode can decode messages and only actually does anything with the identification messages and will give you the call sign of that aircraft (command line only at the moment).


