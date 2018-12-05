#!/bin/bash
# Starte die Groovy-Shell (groovysh) und binde die Pfade fuer Pi4J ein

echo "******************"
echo "*  rpCollection  *"
echo "******************"
echo " "

test $# -eq 1 || echo -e `basename $0`: Bitte aufrufen als \\n `basename $0` "<Groovy-Datei>\\n"
test $# -eq 1 || exit

echo "Starte Groovy"
echo "Dies kann einen Moment dauern..."
echo " "

JAVA_HOME=$JAVA_HOME groovy -classpath './classes:/opt/pi4j/lib/*' $1
