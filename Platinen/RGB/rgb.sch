EESchema Schematic File Version 2
LIBS:rgb-rescue
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
Date ""
Rev ""
Comp ""
Comment1 ""
Comment2 ""
Comment3 ""
Comment4 ""
$EndDescr
$Comp
L Conn_01x04_Female J1
U 1 1 58B815B1
P 5300 2150
F 0 "J1" H 5300 2550 50  0000 C CNN
F 1 "CONN_01X04_FEMALE" H 5400 1750 50  0000 C CNN
F 2 "Pin_Headers:Pin_Header_Straight_1x04_Pitch2.54mm" H 5300 2450 50  0001 C CNN
F 3 "" H 5300 2450 50  0001 C CNN
	1    5300 2150
	1    0    0    -1  
$EndComp
$Comp
L R R1
U 1 1 58B816FA
P 4350 1950
F 0 "R1" V 4430 1950 50  0000 C CNN
F 1 "330" V 4350 1950 50  0000 C CNN
F 2 "Resistors_THT:R_Axial_DIN0207_L6.3mm_D2.5mm_P10.16mm_Horizontal" V 4280 1950 50  0001 C CNN
F 3 "" H 4350 1950 50  0001 C CNN
	1    4350 1950
	0    1    1    0   
$EndComp
$Comp
L R R2
U 1 1 58B817C3
P 4350 2150
F 0 "R2" V 4430 2150 50  0000 C CNN
F 1 "330" V 4350 2150 50  0000 C CNN
F 2 "Resistors_THT:R_Axial_DIN0207_L6.3mm_D2.5mm_P10.16mm_Horizontal" V 4280 2150 50  0001 C CNN
F 3 "" H 4350 2150 50  0001 C CNN
	1    4350 2150
	0    1    1    0   
$EndComp
$Comp
L R R3
U 1 1 58B8183E
P 4350 2350
F 0 "R3" V 4430 2350 50  0000 C CNN
F 1 "330" V 4350 2350 50  0000 C CNN
F 2 "Resistors_THT:R_Axial_DIN0207_L6.3mm_D2.5mm_P10.16mm_Horizontal" V 4280 2350 50  0001 C CNN
F 3 "" H 4350 2350 50  0001 C CNN
	1    4350 2350
	0    1    1    0   
$EndComp
$Comp
L LED_CRGB D1
U 1 1 58B81889
P 3650 2150
F 0 "D1" H 3650 2520 50  0000 C CNN
F 1 "LED_CRGB" H 3650 1800 50  0000 C CNN
F 2 "rgb:LED_D5.0mm-4" H 3650 2100 50  0001 C CNN
F 3 "" H 3650 2100 50  0001 C CNN
	1    3650 2150
	1    0    0    -1  
$EndComp
Wire Wire Line
	5000 1950 4500 1950
Wire Wire Line
	4500 2150 5100 2150
Wire Wire Line
	5000 2350 4500 2350
Wire Wire Line
	4200 2350 3850 2350
Wire Wire Line
	3850 2150 4200 2150
Wire Wire Line
	4200 1950 3850 1950
Wire Wire Line
	3450 2150 3450 2550
Wire Wire Line
	3450 2550 5100 2550
Wire Wire Line
	5000 1950 5000 2050
Wire Wire Line
	5000 2050 5100 2050
Wire Wire Line
	5100 2250 5000 2250
Wire Wire Line
	5000 2250 5000 2350
Wire Wire Line
	5100 2550 5100 2350
$EndSCHEMATC
