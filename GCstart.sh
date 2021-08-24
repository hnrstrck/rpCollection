#!/bin/bash
# Starte die Groovy-Console (groovyConsole) und binde die Pfade fuer Pi4J ein

echo "******************"
echo "*  rpCollection  *"
echo "******************"
echo " "

echo "Starte Groovy-Console."
echo "Dies kann einen Moment dauern..."
echo " "

groovyConsole -classpath '.:./classes:/opt/pi4j/lib/*'
