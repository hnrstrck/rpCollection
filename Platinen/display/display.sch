EESchema Schematic File Version 4
LIBS:display-cache
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
L Connector:Conn_01x18_Female J2
U 1 1 5C064D60
P 2850 1300
F 0 "J2" V 3015 1230 50  0000 C CNN
F 1 "Conn_01x18_Female" V 2924 1230 50  0000 C CNN
F 2 "Connector_PinSocket_2.54mm:PinSocket_1x18_P2.54mm_Vertical" H 2850 1300 50  0001 C CNN
F 3 "~" H 2850 1300 50  0001 C CNN
	1    2850 1300
	0    1    -1   0   
$EndComp
$Comp
L Connector:Conn_01x08_Female J1
U 1 1 5C064DF0
P 2750 2450
F 0 "J1" V 2822 2380 50  0000 C CNN
F 1 "Conn_01x08_Female" V 2913 2380 50  0000 C CNN
F 2 "Connector_PinSocket_2.54mm:PinSocket_1x08_P2.54mm_Vertical" H 2750 2450 50  0001 C CNN
F 3 "~" H 2750 2450 50  0001 C CNN
	1    2750 2450
	0    1    1    0   
$EndComp
Wire Wire Line
	3650 1500 3650 1650
Wire Wire Line
	2050 1650 2050 1500
Wire Wire Line
	1950 1500 1950 1700
Wire Wire Line
	1950 1700 3050 1700
Wire Wire Line
	3550 1700 3550 1500
Wire Wire Line
	3450 2150 2350 2150
Wire Wire Line
	2350 2150 2350 2250
Wire Wire Line
	2450 2250 2450 2100
Wire Wire Line
	2450 2100 3350 2100
Wire Wire Line
	3350 2100 3350 1500
Wire Wire Line
	3150 1500 3150 2050
Wire Wire Line
	3150 2050 3050 2050
Wire Wire Line
	3050 2050 3050 2250
Wire Wire Line
	2950 1500 2950 2250
Wire Wire Line
	2450 1500 2450 1850
Wire Wire Line
	2450 1850 2850 1850
Wire Wire Line
	2850 1850 2850 2250
Wire Wire Line
	2750 2250 2750 1900
Wire Wire Line
	2750 1900 2350 1900
Wire Wire Line
	2350 1900 2350 1500
Wire Wire Line
	2250 1500 2250 1950
Wire Wire Line
	2250 1950 2650 1950
Wire Wire Line
	2650 1950 2650 2250
Wire Wire Line
	2550 2250 2550 2000
Wire Wire Line
	2550 2000 2150 2000
Wire Wire Line
	2150 2000 2150 1500
Wire Wire Line
	2050 1650 3650 1650
Wire Wire Line
	3450 2150 3450 1700
Connection ~ 3450 1700
Wire Wire Line
	3450 1700 3550 1700
Wire Wire Line
	3450 1700 3450 1500
$Comp
L Device:R_POT RV1
U 1 1 5C06860F
P 3650 2100
F 0 "RV1" H 3580 2054 50  0000 R CNN
F 1 "R_POT" H 3580 2145 50  0000 R CNN
F 2 "adwandler:Potentiometer_Trimmer_ACP_CA9v_Horizontal_Px10.0mm_Py5.0mm_breit" H 3650 2100 50  0001 C CNN
F 3 "~" H 3650 2100 50  0001 C CNN
	1    3650 2100
	-1   0    0    1   
$EndComp
Wire Wire Line
	3500 2100 3350 2100
Connection ~ 3350 2100
Wire Wire Line
	3250 1500 3250 1700
Connection ~ 3250 1700
Wire Wire Line
	3250 1700 3450 1700
Wire Wire Line
	3050 1500 3050 1700
Connection ~ 3050 1700
Wire Wire Line
	3050 1700 3250 1700
Wire Wire Line
	3650 1650 3800 1650
Wire Wire Line
	3800 1650 3800 2250
Wire Wire Line
	3800 2250 3650 2250
Connection ~ 3650 1650
$EndSCHEMATC
