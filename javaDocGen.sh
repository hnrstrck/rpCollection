#!/bin/bash

echo "Erstelle Java-Doc"
echo "*****************"

javadoc -D Dokumentation -public classes/*.java -classpath './classes:/opt/pi4j/lib/*'
