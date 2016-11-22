#!/bin/bash
# Starte die Groovy-Shell (groovysh) und bindet die Pfade fuer Pi4J ein

echo "******************"
echo "*  rpCollection  *"
echo "******************"
echo " "

echo "Starte Groovy-Shell."
echo "Dies kann einen Moment dauern..."
echo " "

cd classes && sudo groovysh -classpath '.classes:/opt/pi4j/lib/*'

