#!/bin/bash
# Kompiliere die .java-Dateien passend fuer die Groovy-Shell und 
# starte die Groovy-Shell (groovysh) und bindet die Pfade fuer Pi4J ein

echo "******************"
echo "*  rpCollection  *"
echo "******************"
echo " "

echo "Kompiliere *.java-Dateien"
javac -classpath '.:classes:/opt/pi4j/lib/*' classes/*.java
# fuer aeltere Java-Versionen 
# javac -classpath '.:classes:/opt/pi4j/lib/*' -target 1.7 classes/*.java


echo "Starte Groovy-Shell."
echo "Dies kann einen Moment dauern..."
cd classes && sudo groovysh -classpath '.classes:/opt/pi4j/lib/*'

