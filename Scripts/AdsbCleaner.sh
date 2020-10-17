#!/bin/bash
#Evan Colwell
#A tool for formating adsb data from the rtl_adsb unix command

#This exactly like the AdsbToCsv.sh but without the file compontents.

adsb=$1

sed 's/\*//g' $adsb | sed 's/;//g'
#First sed command removes the star before the adsb code
#The second sed command removes the colon from each line

