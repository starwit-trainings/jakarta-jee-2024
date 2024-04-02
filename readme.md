# Jarkarta Enterprise Training - Übungen

## Aktuelle Links
* [Material zum Training](https://github.com/Javacream/org.javacream.training.jee)
* [Spezifikationen](https://jakarta.ee/specifications/)
    * [Jakarta EE 10](https://jakarta.ee/specifications/platform/10/jakarta-platform-spec-10.0)
* [Code Generator](https://start.jakarta.ee/)
* [Offizielles Tutorial](https://jakarta.ee/learn/docs/jakartaee-tutorial/current/index.html)

# 2 Der Applikationsserver

## Übung zu 2.1: Applikationsserver/Arbeitsweise

### Ziel: 
* Installation einer Applikationsserver + Applikation über das wildfly-maven-plugin
* Überblick der Services über die Managementkonsole des Applikationsservers

### Schritte
* Checken Sie das Projekt aus und gehen Sie in den Ordner examples/trainings
* Starten Sie den Wildfly + Applikation wie in der README.md beschrieben
* Finden Sie unter Deployments heraus, welche Anwendungen deployt sind
* Finden Sie über Configuration die benutzten Ports heraus
* Finden Sie unter Runtime heraus, wieviel Rest-Endpoints zur Verfügung stehen
* Nennen Sie einige Services, die Sie auf der Managementoberfläche finden und ihre Bedeutung

## Übung zu 2.3: Deployment

### Ziel:
* Erste Schritte mit dem Wildfly Server, Durchspielen verschiedener Deployment-Arten.

### Schritte:

Über das wildfly-maven-plugin haben Sie im vorherigen Schritt bereits ein Wildfly Server heruntergeladen. Nun wollen wir diesen Standalone starten.

* Kopieren Sie sich die den Ordner server im target-Verzeichnis.
* Starten sie den Server mit ./bin/standalone.sh bzw. bin/standalone.bat
* Deployen Sie die WAR-Datei aus Ihrem target-Verzeichnis manuell
* Löschen Sie Ihr Deployment wieder und deployen Sie die Anwendung erneut, indem Sie sie in das Verzeichnis deployments des Servers legen

## Übung zu 2.4: Administration

### Ziel:
* Administration über UI und standalone.xml

### Schritte:

* Legen Sie unter Configuration eine H2-Datenbank an. 
* Schauen Sie sich an, wie sich die Serverkonfiguration unter server/standalone/configuation/standalone.xml geändert hat
  * Vieviel Datenbank Connection stehen im Connection-Pool zur Verfügung?

# 5 Das Programmiermodell

## Basis: Microprofile Config - Übung

### Ziel
* Konfigurationsparameter in eine Applikation hineingeben
### Aufgabe
* Lege unter dem Verzeichnis resources/META-INF eine property-Datei namens microprofile-config.properties an
* Definiere eine property, z.B. default-name=Mustermann
* In der Klasse HelloWorldResource
  * Füge die oben gesetzte Config-Property hinzu:
  ```java
  	@Inject
    @ConfigProperty(name = "default-name")
    private String defaultName;
  ```
  * Lass dir den default-name statt des Strings „world“ ausgeben, falls kein Namen in der Methode übergeben wurde
* Teste das Ergebnis mit dem Browser

## Basis / Web: Servlet - Übung

### Ziel
Web Servlet erstellen

### Aufgabe
Wir wollen eine Website mit Trainingsangeboten erstellen. Dazu möchten wir einen Trainingskatalog (csv-Datei) einlesen.

### Hinweis
* Überschreiben Sie die doPost()-Methode in der Klasse TrainingsImportServlet.
* Geben Sie die eingelesenen Daten im log/auf der Konsole aus. Wir werden sie später verarbeiten.

## Core: Json Binding & Processing - Übung

### Ziel
Umgang mit Json-Dateien üben.

### Aufgabe
Wir haben in der vorherigen Übung den Trainingsdatenkatalog als CSV Datei ausgelesen. Wir möchten nun diese Daten im JSON-Format ausgeben.

### Schritte

* Erweitern Sie das TrainingsImportServlet um eine Methode, welche 
  * die Werte aus der CSV-Datei in ein Java-Objekt mappt
  * das Java-Objekt als Json zurückgibt
* Tauschen Sie getTextFromPart mit der neu geschriebenen Methode aus
* Rufen Sie das Servlet über die Oberfläche auf uns schauen Sie sich das Resultat Ihrer Änderungen auf der Server-Konsole an
* Ändern Sie die Json-Properties von Camel-Case in eine Notation mit Bindestrichen, z.B. durationInDays -> duration-in-days

## Core: RESTful Webservices - Übung

### Ziel
Bearbeiten eines Objektes mittels RESTful Webservices zum Erstellen, Bearbeiten, Anzeigen und Löschen erstellt werden.

### Aufgabe

Wir möchten die importierten Trainings via REST-Schnittstelle bearbeiten können. 

### Schritte:
* Legen Sie einen RESTful Webservice "TrainingResource.java" an und ergänzen sie den Pfad unter dem der Service erreichbar sein soll.
* Fügen Sie die Methodenrümpfe find(), findAll(), create(), update() und delete() hinzu
* Ergänzen Sie die zugehörigen REST/HTTP-Metoden (GET, PUT, POST, DELETE)
* Geben Sie der findAll()-Methode einen Rückgabewert und rufen Sie sie über den Browser auf
* Wir ergänzen zusammen die Übergabeparameter, Producer, Consumer

## Core: Context & Dependency Injection - Übung
### Ziel
Grundlagen und Scopes von CDI Beans verstehen.

### Aufgabe

Fügen Sie in das vorhandene Beispiel drei CDI Beans mit jeweils einen Scope Request, Session und Application, die eine Zählfunktion enthalten. Geben Sie das Ergebnis der Funktionen aller drei Beans via Rest Service aus. Rufen Sie den Rest Service im Browser mehrmals auf - in der existierenden und einer neuen Session. Erklären Sie das Ergebnis.

### Hinweis
* Orientieren Sie sich an der Bean ApplicationScopedCounter und dem Rest Service HelloWorldResouce.java.

## Web: JDBC & JPA - Übung

### Ziel: 
Praktischer Umgang mit Entitäten.

### Aufgabe:
Bauen Sie Ihre Klasse Training so um, dass es ebenso als Entity genutzt werden kann.

### Schritte:
* Fügen Sie @Entity hinzu
* Fügen Sie ein Attribut id hinzu, dessen Wert bein Anlegen in die Datenbank generiert wird
* starten Sie die Server + Anwendung und schauen Sie, ob die Entität auf der Management-Console des Servers angezeigt wird.
* Überprüfen Sie ebenfalls, ob die über die persistence.xml angelegte Datenbank existiert.

## Web: Enterprise Beans - Übung

### Ziel: 
CRUD Operationen in einer Stateless Session Bean implementieren. Daten in der Datenbank speichern. 

### Aufgabe
Die Trainingsdaten sollen in der Datenbank über eine Stateless Session gespeichert werden.

### Schritte:
* Fügen Sie in der Klasse TrainingService Annotationen hinzu:
  * um den Service als Stateless Session Bean zu markieren
  * um den EntityManager den PersistenceContext zuzuordnen
* Aktivieren sie die vorbereiteten Methoden im TrainingService, indem Sie die Kommentare entfernen
* Welches Business Interface hat die Bean?
* Ergänzen Sie im REST-Service TraningResource die Stateless Session Bean und entfernen Sie auch hier die Kommentare.
* Speichern Sie im TrainingsImportServlet die Entitäten. Nutzen Sie dazu die create-Methode des TrainingService.
* Testen: Importieren Sie die CSV-Datei über die Oberfläche und lassen Sie sich das Ergebnis über REST anzeigen

## Web: Jakarta Bean Validation - Übung

### Aufgabe

* Fügen Sie der der TraningsEntity Validierungen hinzu
Prüfen Sie, was passiert, wenn sie über die CSV-Datei Trainings importieren, welche diesen Werten nicht entsprechen 

## Platform: JMS Messaging - Übung

### Ziel:
* Komponenten sollen mittels JMS Messages asynchron miteinander kommunizieren / Daten austauschen

### Aufgabe
* Fügen Sie eine Queue hinzu:
  * Verbinden Sie sich mit der jboss-cli:
    ```sh
    cd target/server/bin
    ./jboss-cli.sh
    connect
  * fügen Sie eine Queue hinzu
  ```sh
  jms-queue add --queue-address=trainingQueue --entries=java:/jms/queue/trainingQueue
  ```
* (!) Wir benötigen die erzeugten Server Einstellung. Sollten Sie den Server über das Maven Plugin neu starten müssen, führen Sie NICHT clean aus
* Erstellen Sie einen Producer, der mittels Restful Webservice aufgerufen wird
* Erstellen Sie mittels MessageDrivenBean einen MessageListener
* Testen Sie das Versenden von Messages. Was passiert, wenn du den MessageListener deaktivierst?

---
## Plattform: Batch-Processing - Übung

### Aufgabe
* Der Trainingskatalog soll nun regelmäßig als CSV-Datei importiert werden. Dazu soll ein Batch-Job geschrieben werden
### Schritte:
* Erstelle im Trainingsservice eine JPQL-Query, welche ein Training anhand des Titels zurückgeben kann
* Erstelle einen Scheduled Job, der aller 5 Sekunden die CSV-Datei einließt und die Trainingsdaten neu anlegt oder bei gleichem Titel aktualisiert
