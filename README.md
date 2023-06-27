# Ticketsystem

Das vorliegende Ticketsystem ist im Rahmen eines Schulprojektes, beispielhaftes Ticketbuchsystem.

## Voraussetzungen für das Programm -> Starten des Programmes 

### MySQL DB
Um das Programm nutzen zu können, muss eine MySQL Datenbank vorhanden sein. Das Backend kreiert entsprechende Tabellen, welche für das System genutzt werden. Es muss jedoch eine Datenbank mit dem Namen Ticketsystem angelegt sein, welche das Programm frei verwalten kann. Standardmäßig ist der Username:root, Passwort:root8 und die Adresse ein localhost auf Port 3306. 

Datenbankstruktur
![DB IMG](https://github.com/NikMitzel/Tickets/blob/main/img/imgDB.jpg?raw=true) 
### Programm Starten
#### V1 Consolenversion(JDK 18)
Im ersten Sprint wurde eine Consolenversion Programmes erstellt, in welche die DB Logik schon vorhanden ist. Über ein kleines Consolenmenü kann der User hier das Programm nutzen: Dazu muss nur die Klasse _ConsoleVersion.java ausgeführt werden. Das Projekt wurde in dem Sprint nicht exportiert, muss also in IntelliJ gestartet werden. (Die DB muss bereits laufen) Beispiel:


![Consol IMG](https://github.com/NikMitzel/Tickets/blob/main/img/imgV1.jpg?raw=true) 

#### V2 Browser UI Version(JDK 17)
Im zweiten Sprint wurde eine grafische Oberfläche erstellt. Dieser Sprint kann über die exportierte java Datei gestartet werden(/Ticketsystem.jar).  Um das Programm zu nutzen, muss Java 17 installiert sein. Nun kann das System in Frontend verwendet werden. (Die DB muss bereits laufen). Das System hat nun 2 Seiten, um es anzusprechen:

1.User Seite: Hier können Tickets gebucht, sowie storniert werden. (starte Frontend/Login.html) von dort aus kann der user durch das Programm navigiert werden.
Beispiel:
![UI IMG](https://github.com/NikMitzel/Tickets/blob/main/img/imgV2_1_1.jpg?raw=true) 
![UI2 IMG](https://github.com/NikMitzel/Tickets/blob/main/img/imgV2_1_2.jpg?raw=true) 

2.Admin Seite: Hier können gebuchte Tickets eingesehen werden, sowie neue angelegt werden. (starte Admin_BookedTickets.html)
Beispiel:
![UIAdmin IMG](https://github.com/NikMitzel/Tickets/blob/main/img/imgV2_2.jpg?raw=true) 

## Programm Struktur/Code
Der backend Code lässt sich in den beiden Sprints unter /backend Code finden.
### Layer Struktur

#### Layer 1 HTML, CSS,JS UI
Hier wird die UI generiert, mithilfe von HTML und CSS wurde sich ein möglichst einfaches UX Designe umgesetzt. Die Webseite greift mithilfe von JS auf ein eigens erstellten API Server zu. Hier wird mit JSON Dateien zwischen Java Backend und JavaScript Frontend kommuniziert und Datenaustausch von z.B. Tickets oder UserAccouts zu ermöglichen.
#### Layer 2 Java API Server
Mithilfe des SPRINGBOOT Frameworks wird ein API-Server erstellt, welcher JSON Dateien empfangen und senden kann. Die APIs werden immer von JS vom Frontend angefragt oder gesendet(GetMapping or PostMapping). Die JSON Kommunikation werden in den Controler Klassen verwaltet. Diese Klassen greifen nun auf die eigentliche Java Verwaltung zu.
#### Layer 3 Java Verwaltung
In dem Java Verwaltungslayer (Service Klassen) werden Daten verarbeitet, um sie für die Datenbank entsprechend vorzubereiten. 
#### Layer 3 Java DBConnection
Im dritten Layer findet eine wie im Unterricht gelernte Datenbankverbindung mit dem DAO Schema vor. 
#### Layer 4 MySQL DB
Die DB speichert die angegebenen daten.

### Layer Kommunikation
![Layer IMG](https://github.com/NikMitzel/Tickets/blob/main/img/image.jpg?raw=true) 

## Nachwort
Das gesamte System lässt sich auch auf einem Server laufen. Dann könnte auch aus dem Internet aus das System benutzt werden. Aus vergangener Erfahrung und da es rechtlich etwas komplexer ist (cookies usw.) haben wir das bei diesem Projekt unterlassen. 
