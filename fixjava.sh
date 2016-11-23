#!/bin/bash

echo "Repariere Java"

cd /etc/profile.d/
sudo echo "export JAVA_HOME=/usr/" > java.sh

echo "Fix beendet"

