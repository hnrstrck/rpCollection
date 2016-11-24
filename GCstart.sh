#!/bin/bash
# Starte die Groovy-Console (groovyConsole) und binde die Pfade fuer Pi4J ein

echo "******************"
echo "*  rpCollection  *"
echo "******************"
echo " "

echo "Starte Groovy-Console."
echo "Dies kann einen Moment dauern..."
echo " "

cd classes && sudo JAVA_HOME=$JAVA_HOME groovyConsole -classpath '.classes:/opt/pi4j/lib/*'
