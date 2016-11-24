#!/bin/bash
# Starte die Groovy-Shell (groovysh) und bindet die Pfade fuer Pi4J ein

echo "******************"
echo "*  rpCollection  *"
echo "******************"
echo " "

echo "Starte Groovy-Shell."
echo "Dies kann einen Moment dauern..."
echo " "

# evtl ohne sudo ausfuehren
cd classes && sudo JAVA_HOME=$JAVA_HOME groovysh -classpath '.classes:/opt/pi4j/lib/*'
