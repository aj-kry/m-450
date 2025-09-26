## Aufgabe 
## Übung 1 
### Eingesetztes Tool

Ich habe mich für Postman + Newman entschieden:

**Postman:** Erstellung einer Test-Collection (StudentAPI.json).

**Newman:** CLI-Tool, das die Postman-Collection automatisiert ausführt und Ergebnisse in der Konsole oder als Report ausgibt.

**Vorteil:** Tests sind einfach erweiterbar, können ohne Java-Code gepflegt werden und laufen auch in Docker/CI/CD-Pipelines.

### Inhalt der Collection StudentAPI.json

Folgendes wird getestet: 

1. GET /students

- Erwartet Status 200

- Antwort ist ein JSON-Array

2. POST /students

- Erwartet Status 2xx

- Fügt einen neuen Studenten hinzu (name: "Mila", email: "mila@tbz.ch")

3. GET /students (nach POST)

- Prüft, dass der neue Student in der Liste enthalten ist

### Backend starten
``mvn spring-boot:run`` <br>
``newman run src/test/java/ch/tbz/m450/testing/tools/StudentAPI.json``

### Relsutat 
![alt text](image.png)

## Übung 2
### Toolwahl

- Karma ist das Standard-Test-Runner-Tool in Angular-Projekten (Angular CLI setzt es automatisch ein).

- Es läuft Tests automatisiert im Browser (Chrome, Firefox, Headless).

- In Kombination mit Jasmine können wir damit GUI-Tests schreiben.

- Damit lassen sich End-to-End-ähnliche Tests auf Komponenten-Ebene realisieren.


![alt text](image-3.png)<br/>
<br/>
![alt text](image-2.png)

## Übung 3
### Tools — Kurzüberblick & Auswahl

**Postman:** Gut für funktionale und manuelle Lasttests (Collection Runner). Einfach zu benutzen, limitiert bei hoher parallelisierter Last.

**Newman:** CLI-Runner für Postman-Collections. Nützlich für Automatisierung, nicht sehr performant für massive Last.

**k6:** Modernes, scriptbasiertes Load‑Testing-Tool (JS). Leichtgewichtig, ideal für Performance‑Skripte und CI-Integration. Empfehlenswert.

**Apache JMeter:** Sehr mächtig, GUI für komplexe Szenarien, robust für hohe Last. Benötigt mehr Ressourcen.


