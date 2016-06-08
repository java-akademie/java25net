RMI - Remote Method Invocation
------------------------------
Anleitung zur Programmierung einer RMI Client/Server-Loesung.

Dieses Musterprojekt soll zeigen, wie man einen Datenbestand
auf einem entfernten Rechner nutzt.

Der erste Loesungsansatz soll eine ganz einfache Implementation
beinhalten (fest im Programm als <KarteiKasten> codiert).

In einer  weiteren Ausbaustufe soll dann eine SQL-Datenbank
benutzt werden.

Den Dienst bietet der Server-Rechner an wo auch die Programme
entwickelt werden.

Der Client kann am Server-Rechner oder aber auch von einem
entfernten Rechner auf den Server zugreifen.
 
Auf der Client-Seite soll nur ein minimaler Aufwand betrieben
werden.


Files
-----
Kartei.java		Interface                   
KarteiImpl.java   	Implementation  
KarteiServer.java	Server          
KarteiClient.java	Client

KarteiKasten.java	vorab fest verdrahtete Datenhaltung
Datensatz.java		Datensatz
  
  
Server
------
rmic    KarteiImplementation
jikes   KarteiServer.java
jikes   KarteiClient.java

java    KarteiServer

dem Client zur Verfuegung stellen
        KarteiClient.class              beinhaltet den Request
        KarteiImplementation_Stub.class beinhaltet die Implementation


Client
------
KarteiClient.class            
KarteiImplementation_Stub.class

java  KarteiClient name
