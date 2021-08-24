#!/bin/bash
# Starte die Groovy-Shell (groovysh) und binde die Pfade fuer Pi4J ein

echo "******************"
echo "*  rpCollection  *"
echo "******************"
echo " "

echo "Starte Groovy-Shell."
echo "Dies kann einen Moment dauern..."
echo " "

groovysh -classpath '.:./classes:/opt/pi4j/lib/*'
