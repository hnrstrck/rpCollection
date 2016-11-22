#!/bin/bash
# Kompiliere die .java-Dateien passend fuer die GroovyConsole und 
# starte die GroovyConsole (groovyConsole) und bindet die Pfade fuer Pi4J ein

echo "******************"
echo "*  rpCollection  *"
echo "******************"
echo " "

echo "Kompiliere *.java-Dateien"
echo " "

javac -classpath '.:classes:/opt/pi4j/lib/*' classes/*.java
# fuer aeltere Java-Versionen 
# javac -classpath '.:classes:/opt/pi4j/lib/*' -target 1.7 classes/*.java


echo "Starte GroovyConsole."
echo "Dies kann einen Moment dauern..."
echo " "

cd classes && sudo groovyConsole -classpath '.classes:/opt/pi4j/lib/*'

