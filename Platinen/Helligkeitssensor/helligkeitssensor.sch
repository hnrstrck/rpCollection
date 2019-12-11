EESchema Schematic File Version 4
EELAYER 26 0
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
L Connector:Conn_01x03_Female J1
U 1 1 58BE84EE
P 6800 2550
F 0 "J1" H 6800 2850 50  0000 C CNN
F 1 "CONN_01X03_FEMALE" H 6800 2250 50  0000 C CNN
F 2 "Connector_PinHeader_2.54mm:PinHeader_1x03_P2.54mm_Vertical" H 6800 2750 50  0001 C CNN
F 3 "" H 6800 2750 50  0001 C CNN
	1    6800 2550
	1    0    0    -1  
$EndComp
$Comp
L Device:R R1
U 1 1 58BE8565
P 6300 2450
F 0 "R1" V 6380 2450 50  0000 C CNN
F 1 "1k" V 6300 2450 50  0000 C CNN
F 2 "Resistor_THT:R_Axial_DIN0207_L6.3mm_D2.5mm_P10.16mm_Horizontal" V 6230 2450 50  0001 C CNN
F 3 "" H 6300 2450 50  0001 C CNN
	1    6300 2450
	0    1    1    0   
$EndComp
$Comp
L Device:R R2
U 1 1 58BE865A
P 6300 2650
F 0 "R2" V 6380 2650 50  0000 C CNN
F 1 "10k" V 6300 2650 50  0000 C CNN
F 2 "Resistor_THT:R_Axial_DIN0207_L6.3mm_D2.5mm_P10.16mm_Horizontal" V 6230 2650 50  0001 C CNN
F 3 "" H 6300 2650 50  0001 C CNN
	1    6300 2650
	0    1    1    0   
$EndComp
$Comp
L Device:D_Photo SW1
U 1 1 58BE87BF
P 5800 2600
F 0 "SW1" H 5850 2700 50  0000 L CNN
F 1 "SW_Push" H 5800 2540 50  0000 C CNN
F 2 "LED_THT:LED_D3.0mm" H 5800 2800 50  0001 C CNN
F 3 "" H 5800 2800 50  0001 C CNN
	1    5800 2600
	0    1    1    0   
$EndComp
Wire Wire Line
	6600 2550 5950 2550
Wire Wire Line
	5950 2550 5950 2400
Wire Wire Line
	5950 2400 5800 2400
Wire Wire Line
	5800 2700 6150 2700
Wire Wire Line
	6150 2700 6150 2650
Wire Wire Line
	6450 2650 6600 2650
Wire Wire Line
	6600 2450 6450 2450
Wire Wire Line
	6150 2450 6050 2450
Wire Wire Line
	6050 2450 6050 2350
Wire Wire Line
	6050 2350 5650 2350
Wire Wire Line
	5650 2350 5650 2700
Wire Wire Line
	5650 2700 5800 2700
Connection ~ 5800 2700
$EndSCHEMATC
