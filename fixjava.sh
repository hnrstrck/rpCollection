#!/bin/bash

echo "Repariere Java"

#sudo apt-get purge openjdk-7-jre-headless
echo "export JAVA_HOME=/usr/" | sudo tee -a /etc/profile.d/java.sh

echo "Fix beendet"

