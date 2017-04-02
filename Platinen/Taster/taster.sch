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
LIBS:switches
EELAYER 25 0
EELAYER END
$Descr A4 11693 8268
encoding utf-8
Sheet 1 1
Title ""
Date ""
Rev ""
Comp ""
Comment1 ""
Comment2 ""
Comment3 ""
Comment4 ""
$EndDescr
$Comp
L CONN_01X03_FEMALE J1
U 1 1 58BE84EE
P 6800 2550
F 0 "J1" H 6800 2850 50  0000 C CNN
F 1 "CONN_01X03_FEMALE" H 6800 2250 50  0000 C CNN
F 2 "Pin_Headers:Pin_Header_Straight_1x03_Pitch2.54mm" H 6800 2750 50  0001 C CNN
F 3 "" H 6800 2750 50  0001 C CNN
	1    6800 2550
	1    0    0    -1  
$EndComp
$Comp
L R R1
U 1 1 58BE8565
P 6150 2350
F 0 "R1" V 6230 2350 50  0000 C CNN
F 1 "1k" V 6150 2350 50  0000 C CNN
F 2 "Resistors_THT:R_Axial_DIN0207_L6.3mm_D2.5mm_P10.16mm_Horizontal" V 6080 2350 50  0001 C CNN
F 3 "" H 6150 2350 50  0001 C CNN
	1    6150 2350
	0    1    1    0   
$EndComp
$Comp
L R R2
U 1 1 58BE865A
P 6150 2750
F 0 "R2" V 6230 2750 50  0000 C CNN
F 1 "10k" V 6150 2750 50  0000 C CNN
F 2 "Resistors_THT:R_Axial_DIN0207_L6.3mm_D2.5mm_P10.16mm_Horizontal" V 6080 2750 50  0001 C CNN
F 3 "" H 6150 2750 50  0001 C CNN
	1    6150 2750
	0    1    1    0   
$EndComp
$Comp
L SW_Push SW1
U 1 1 58BE87BF
P 5650 2550
F 0 "SW1" H 5700 2650 50  0000 L CNN
F 1 "SW_Push" H 5650 2490 50  0000 C CNN
F 2 "Buttons_Switches_THT:SW_PUSH_6mm" H 5650 2750 50  0001 C CNN
F 3 "" H 5650 2750 50  0001 C CNN
	1    5650 2550
	1    0    0    -1  
$EndComp
Wire Wire Line
	6700 2350 6300 2350
Wire Wire Line
	6300 2750 6700 2750
Wire Wire Line
	6700 2550 5850 2550
Wire Wire Line
	6000 2350 5450 2350
Wire Wire Line
	5450 2350 5450 2750
Wire Wire Line
	5450 2750 6000 2750
Connection ~ 5450 2550
$EndSCHEMATC
