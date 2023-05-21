#!/bin/bash
cd classes 
javadoc -d ../Dokumentation/  -classpath '/opt/pi4j/lib/*' *.java 
sed -i s/AuswertungsEmpfaenger.html/allclasses.html/ ../Dokumentation/index.html
