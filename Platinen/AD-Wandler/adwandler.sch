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
LIBS:adwandler-cache
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
L POT RV1
U 1 1 58B30835
P 4700 3300
F 0 "RV1" V 4525 3300 50  0000 C CNN
F 1 "POT" V 4600 3300 50  0000 C CNN
F 2 "adwandler:Potentiometer_Trimmer_ACP_CA9v_Horizontal_Px10.0mm_Py5.0mm_breit" H 4700 3300 50  0001 C CNN
F 3 "" H 4700 3300 50  0001 C CNN
	1    4700 3300
	1    0    0    -1  
$EndComp
$Comp
L POT RV2
U 1 1 58B30844
P 4700 3700
F 0 "RV2" V 4525 3700 50  0000 C CNN
F 1 "POT" V 4600 3700 50  0000 C CNN
F 2 "adwandler:Potentiometer_Trimmer_ACP_CA9v_Horizontal_Px10.0mm_Py5.0mm_breit" H 4700 3700 50  0001 C CNN
F 3 "" H 4700 3700 50  0001 C CNN
	1    4700 3700
	1    0    0    -1  
$EndComp
$Comp
L POT RV3
U 1 1 58B30853
P 4700 4100
F 0 "RV3" V 4525 4100 50  0000 C CNN
F 1 "POT" V 4600 4100 50  0000 C CNN
F 2 "adwandler:Potentiometer_Trimmer_ACP_CA9v_Horizontal_Px10.0mm_Py5.0mm_breit" H 4700 4100 50  0001 C CNN
F 3 "" H 4700 4100 50  0001 C CNN
	1    4700 4100
	1    0    0    -1  
$EndComp
$Comp
L POT RV4
U 1 1 58B30862
P 4650 4650
F 0 "RV4" V 4475 4650 50  0000 C CNN
F 1 "POT" V 4550 4650 50  0000 C CNN
F 2 "adwandler:Potentiometer_Trimmer_ACP_CA9v_Horizontal_Px10.0mm_Py5.0mm_breit" H 4650 4650 50  0001 C CNN
F 3 "" H 4650 4650 50  0001 C CNN
	1    4650 4650
	1    0    0    -1  
$EndComp
$Comp
L CONN_01X06 J1
U 1 1 58B30941
P 8100 3950
F 0 "J1" H 8100 4300 50  0000 C CNN
F 1 "CONN_01X06" V 8200 3950 50  0000 C CNN
F 2 "adwandler:Socket_Strip_Straight_1x06_Pitch2.54mm_breit" H 8100 3950 50  0001 C CNN
F 3 "" H 8100 3950 50  0001 C CNN
	1    8100 3950
	1    0    0    -1  
$EndComp
Wire Wire Line
	6250 4200 7900 4200
Wire Wire Line
	5950 4100 5950 5050
Connection ~ 7400 4100
Wire Wire Line
	6250 3000 7250 3000
Wire Wire Line
	7250 3000 7250 4100
Wire Wire Line
	5950 3000 5950 2800
Wire Wire Line
	4700 2800 6500 2800
Wire Wire Line
	6500 2800 6500 3000
Connection ~ 6500 3000
Wire Wire Line
	7250 4100 7900 4100
Wire Wire Line
	6650 3700 7900 3700
Wire Wire Line
	6250 4200 6250 4100
Wire Wire Line
	7900 3800 6950 3800
Wire Wire Line
	6950 3800 6950 3600
Wire Wire Line
	6950 3600 6650 3600
Wire Wire Line
	6650 3500 6850 3500
Wire Wire Line
	6850 3500 6850 3900
Wire Wire Line
	6850 3900 7900 3900
Wire Wire Line
	6650 3400 6750 3400
Wire Wire Line
	6750 3400 6750 4000
Wire Wire Line
	6750 4000 7900 4000
Wire Wire Line
	4700 2800 4700 3150
Connection ~ 5950 2800
Wire Wire Line
	4700 3150 4250 3150
Wire Wire Line
	4250 3150 4250 4500
Wire Wire Line
	4250 4500 4650 4500
Wire Wire Line
	4700 3950 4250 3950
Connection ~ 4250 3950
Wire Wire Line
	4700 3550 4250 3550
Connection ~ 4250 3550
Wire Wire Line
	5950 5050 4650 5050
Wire Wire Line
	4650 5050 4650 4800
Connection ~ 5950 4400
Wire Wire Line
	4650 4800 5200 4800
Wire Wire Line
	5200 4800 5200 3450
Wire Wire Line
	5200 3450 4700 3450
Wire Wire Line
	4700 3850 5200 3850
Connection ~ 5200 3850
Wire Wire Line
	4700 4250 5200 4250
Connection ~ 5200 4250
Wire Wire Line
	4800 4650 5350 4650
Wire Wire Line
	5350 4650 5350 3500
Wire Wire Line
	5350 3500 5450 3500
Wire Wire Line
	5450 3400 5050 3400
Wire Wire Line
	5050 3400 5050 4100
Wire Wire Line
	5050 4100 4850 4100
Wire Wire Line
	4850 3700 4950 3700
Wire Wire Line
	4950 3700 4950 3300
Wire Wire Line
	4950 3300 5450 3300
Wire Wire Line
	5450 3200 4850 3200
Wire Wire Line
	4850 3200 4850 3300
$Comp
L MCP3208 U1
U 1 1 58B34079
P 6050 3500
F 0 "U1" H 5850 4025 50  0000 R CNN
F 1 "MCP3208" H 5850 3950 50  0000 R CNN
F 2 "adwandler:DIP-16_W7.62mm_bigpads" H 6150 3600 50  0001 C CNN
F 3 "" H 6150 3600 50  0001 C CNN
	1    6050 3500
	1    0    0    -1  
$EndComp
Wire Wire Line
	7400 4200 7400 4400
Wire Wire Line
	7400 4400 5950 4400
Connection ~ 7400 4200
$EndSCHEMATC
