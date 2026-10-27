# Projekt_Tool_KspEditor
Ansammlung kleiner Tools, um ein SaveFile von "Kerbal Space Program" zu bearbeiten.
(KSP Version 1.5.1)

Ziel ist es an ein bestehendes Fahrzeug eine neue "Struktur", bestehend aus meheren neuen PARTS, anzuhängen.

Also:
1) Ausgangsstruktur erstellen.
Struktur in KSP bauen und in ein "Dummy" Fahrzeug integrieren.
Dieses "Dummy" Fahrzeug starten.
Aus dem Spielstand das neue "Dummy" Fahrzeug holen
und in eine Datei exportieren.

2) Ausgangsstruktur ermitteln und anpassen
Aus der Datei mit dem "Dummy" Fahrzeug:
Zeilen mit der Struktur daraus extrahieren und in eine eigenen Datei wegspeichern.

3) Zielfahrzeug ermitteln
Aus dem Spielstand das eigentliche Zielfahrzeug holen 
und in eine Datei exportieren

4) Struktur anpassen
Diese Struktur muss dann für das Hinzufügen an ein bestehendes Fahrzeug überarbeitet werden:
a) Perent Indizierung:
- Hänge Sie immer an das Ende (wg. der "dynamischen Inidizierung" der PARTS eines VESSELS)
- Erstes PART der Struktur muss als Parent den "dynamischen Index" des "Aufhänger - PARTS" bekommen. Ermittle das Aufhängerpart (nimm dazu eine im VESSEL vorhandene PART Kombination) und suche in einem Tool wie "KLM" danach.
- Nachfolgende PARTS der Struktur müssen dann als Parent den "dynamischen Index" des ersten PART der Struktur bekommen, usw.

b) Anhängen an das Aufhängerpart
attN Wert sowohl im Aufhängerpart als auch in der neuen Struktur passend machen

c) Korrektes Positionieren der Teile des neuen Struktur
Die bisherige Position der neuen Struktur wird nicht stimmen.
Positionen sind in einem VESSEL immer relativ zueineander.
Orientiere dich an dem Aufhängerpart und den dort vorhandenen Werten. 
Das ist Ausprobieren.

Die Tools sollen mit der Ermittlung einen "Offset" Wertes alle PARTS der Struktur anpassen.
Sichere solch eine Struktur in einer Datei. Möglichst nach jedem Arbeitsschritt

d) Korrektes Drehen der Teile der neuen Struktur
s. Positionieren.

-------------------------------
Neues Repository anhand einer Vorlage erstellt. (JAZ...DummyTemplate)
Das neue Repository lokal clonen.<br>
Umbennung des Verzeichnisordners "...TEMPLATE..." kann in der "normalen" GitHub - Seite nicht erfolgen. Git kann auch keine Verzeichnisse umbenennen.
Aber es gibt ein Plugin, welches man duch Drücken des Punkts "." öffnen kann. 
Dann öffnet sich github.dev Seite. Hier momentan VisualStudio. In dem Menü kann man dann den Ordner umbenennen.
Mit Rechtclick auf den Knoten und Auswahl "Einfügen..." öffnet sich ein Menü. Dort unten "Ordner umbenennen" auswählen.<br> 
<img width="1332" height="703" alt="grafik" src="https://github.com/user-attachments/assets/af1a128b-3996-488f-abfa-ae9819f6d96f" />


<img width="1018" height="587" alt="grafik" src="https://github.com/user-attachments/assets/864bc87c-f7e8-4a93-9750-0e388cf1dd92" />

<br>Diese Änderung muss noch committet werden. Also auf den Link (hier mit der blauen 16) clicken. 
<br>Und den Kommentar eingeben. Hier "Verzeichnis umbenannt".
<img width="706" height="761" alt="grafik" src="https://github.com/user-attachments/assets/9fcea1cc-5f6d-45ff-8f0d-b3186f5bbb71" />

unten links gibt es einen Link "GitHub" mit dem man wieder zurückkommen kann. (im Menü auswählen: "Zu Repository wcchseln").
<img width="1358" height="1127" alt="grafik" src="https://github.com/user-attachments/assets/d584c41f-b8d3-41b2-b86c-faab209fdf64" />

NICHT VERGESSEN:<br>
Anschliessend in den Eclipse - Projektdateien lokal Änderungen vornehmen.
Dabei dann den gewünschten Java-Projektnamen vergeben, etc.

WICHTIG:<br> 
Es reicht nicht aus dieses Projekt selbst zu klonen und dann die Projektnamens - Umbenennungen durchzuführen.
Man bekommt dann ja kein eigenständiges Repository und würde nur mit diesem Template Repository arbeiten.

-------------------------------------------
Anschliessend: 
In Eclipse die notwendigen Eigenschaften hinzufügen.
Bei JAZ-Projekten ist das die "Facet Form" Java.

-------------------------------------------
Merke:<BR>
Die Datei README.md wird hier als "Linked Resource" eingebunden über den Pfad "PROJECT_LOC\\..\README.md".

Dann kann man sie mit dem Plugin mylin.Wikitext bearbeiten<br>
Doku zu dem Plugin:<br>
https://help.eclipse.org/latest/index.jsp?topic=%2Forg.eclipse.mylyn.wikitext.help.ui%2Fhelp%2FGetting-Started.html<BR>

Tip zu dem Plugin:<BR>
Im Editor F1 drücken. Dann kommt eine Kurzreferenz mit den Markdown Struktur-Anweisungen






