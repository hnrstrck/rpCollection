EESchema Schematic File Version 2
LIBS:power
LIBS:device
LIBS:transistors
LIBS:conn
LIBS:linear
LIBS:regul
LIBS:74xx
LIBS:cmos4000
LIBS:adc-dac
LIBS:memory
LIBS:xilinx
LIBS:microcontrollers
LIBS:dsp
LIBS:microchip
LIBS:analog_switches
LIBS:motorola
LIBS:texas
LIBS:intel
LIBS:audio
LIBS:interface
LIBS:digital-audio
LIBS:philips
LIBS:display
LIBS:cypress
LIBS:siliconi
LIBS:opto
LIBS:atmel
LIBS:contrib
LIBS:valves
EELAYER 25 0
EELAYER END
$Descr A4 11693 8268
encoding utf-8
Sheet 1 1
Title ""
Date "26 feb 2017"
Rev ""
Comp ""
Comment1 ""
Comment2 ""
Comment3 ""
Comment4 ""
$EndDescr
$Comp
L LED D1
U 1 1 58048ADF
P 1900 1800
F 0 "D1" H 1900 1900 50  0000 C CNN
F 1 "LED" H 1900 1700 50  0000 C CNN
F 2 "LED-3MM" H 1900 1800 60  0000 C CNN
F 3 "~" H 1900 1800 60  0000 C CNN
	1    1900 1800
	0    1    -1   0   
$EndComp
$Comp
L R R1
U 1 1 58048B1C
P 2450 1650
F 0 "R1" V 2530 1650 40  0000 C CNN
F 1 "R" V 2457 1651 40  0000 C CNN
F 2 "~" V 2380 1650 30  0000 C CNN
F 3 "~" H 2450 1650 30  0000 C CNN
	1    2450 1650
	0    -1   -1   0   
$EndComp
Wire Wire Line
	2600 1650 3100 1650
Wire Wire Line
	1900 1650 2300 1650
$Comp
L CONN_01X02 P1
U 1 1 58E148C6
P 3300 1700
F 0 "P1" H 3300 1850 50  0000 C CNN
F 1 "CONN2" V 3400 1700 50  0000 C CNN
F 2 "Pin_Headers:Pin_Header_Straight_1x02_Pitch2.54mm" H 3300 1700 50  0001 C CNN
F 3 "" H 3300 1700 50  0001 C CNN
	1    3300 1700
	1    0    0    -1  
$EndComp
Wire Wire Line
	1900 1950 3100 1950
Wire Wire Line
	3100 1950 3100 1750
$EndSCHEMATC
