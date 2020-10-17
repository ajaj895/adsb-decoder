#!/bin/bash
#Evan Colwell
#A tool for formating adsb data from the rtl_adsb unix command
#Takes in a passed in file

#Passed in file
FILE1=$1

limit=$(expr `echo $FILE1|wc -c` - 5) #Removes the file extension
FILENAME=${FILE1::limit} #Sets the filename to be used later as the filename without the file extension

#Sed command for removing the * in the first character
sed 's/\*//g' $FILE1 | sed 's/;/,/g' | sed '$s/,//g' > $FILENAME.csv 
#Sed command for replacing the ; with ,
#The last sed commands removes the last lines , just for nice dataoutput
