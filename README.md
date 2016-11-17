# rpCollection
_**Java-Objekte zum Anfassen**_

## Beschreibung:
Bei der Objektorientierten Modellierung (OOM) spielen Objekte eine fundamentale Rolle. Der klassische Ansatz im Unterricht arbeitet mit rein virtuellen Objekten -- sämtliche Interaktion und auch die Objektkommunikation spielt sich im Rechner ab. Nicht selten kommt es dabei vor, dass man Objekte im wahrsten Sinne des Wortes gar nicht "sieht".

Ziel dieses Projektes ist es, Objekte begreifbar zu machen und Objektkommunikation zu visualisieren. Dafür stehen für den Unterricht Bauteile bereit, mit denen sich verschiedene Szenarien modellieren lassen. Die Schülerinnen und Schüler bekommen so die Möglichkeit, Objekte tatsächlich anzufassen und ihren aktuellen State (Zustand) nicht nur über Methoden zu erfragen, sondern auch zu *sehen* (Bauteil: RGB-LED) oder zu *hören* (Bauteil: Summer). Auch die Kommunikation ist dabei dabei zu verfolgen. Die Idee ist im folgenden Bild veranschaulicht (pinke Kreise = am Raspberry Pi angeschlossene Bauteile (über Steckbrett), pinke Pfeile = Objektkommunikation).

<div align="center">
<img src="ReadMe/objektkommunikation_am_Raspberry_Pi.png" alt="Objektkommunikation der Bauteile am Raspberry Pi" width="300px">
</div>

Die Umsetzung im Unterricht setzt auf Groovy (genauer: die Groovy-Shell), in der sich mit korrekter Java-Syntax mit den Objekten praktisch arbeiten lässt (Objekte erstelen, Objekte modifizieren, Methoden aufrufen, Attribute abfragen etc.).

Es stehen folgende Bauteile bereit:
* Diode (LED)
* RGB-LED 
* Phototransistor 
* Taster
* AD-Wandler (für den Anschluss eines MCP3008- / MCP3208-Chips)
* Summer
* Motor (für den Anschluss eines Fischertechnik-Motors)

### Foto
Im folgenden Bild ist eine angeschlossene RGB-LED zu erkennen. Die Kabel für Rot, Grün und Blau sind farblich abgestimmt, schwarz ist für Ground.  

<div align="center">
<img src="ReadMe/rgb_led_raspberry_pi_gpio.jpg" alt="Angeschlossene RGB-LED am Raspberry Pi" width="300px">
</div>

Weitere Bilder sind im Ornder

    /Arbeitsblaetter/img/
    
zu sehen (Anschluss eines Helligkeitssensors, Anschluss einer Diode).

## Voraussetzungen:
### Hardware
* Raspberry Pi
* GPIO
* Steckbrett
* Kabel zum Anschluss des GPIO an den Raspberry Pi (Flachbandkabel)
* Bauteile (Bauanleitungen und Bilder folgen)

### Software
* Pi4J (mit dem Raspberry Pi standardmäßig ausgeliefert) [Website](http://pi4j.com)
* WiringPi (wird auch standardmäßig mitgeliefert) [Website](http://wiringpi.com/)
* Groovy (ist i.d.R. noch zu installieren) [Website](http://groovy-lang.org/)

#### Evtl. weitere Software
* LaTeX für den Etikettendruck (falls Bauteile nachgebaut werden sollen)

## Anwendung und Beispiel:
Mit dem Aufruf 

    bash start.sh
    
werden die Java-Dateien kompiliert und die Groovy-Shell gestartet, sodass mit den Bauteilen gearbeitet werden kann. Die Pfade für Pi4J sind beim Aufruf bereits eingebunden.

### Beispiel
Durch die Eingaben

    meinLicht = new rpDiode();
    meinLicht.setPin(17);
    meinLicht.an()
    
wird eine an Pin 17 (BCM-Nummerierung) angeschlossene LED angeschaltet.
    
