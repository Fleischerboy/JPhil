## Setup (IntelliJ)
- Lag nytt Maven Intellij prosjekt
- Java 17 
- Tryk på File -> new - module from existing sources
- Velg filen til å importere: [pom.xml] filen inni JPhil rammeverket

## Add dependency (Maven)
- Inni din egen prosjekt pox.xml, legg til dette:
````Java
    <dependencies>
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>JPhil</artifactId>
            <version>1.0-SNAPSHOT</version>
            <scope>compile</scope>
        </dependency>
    </dependencies>
````


### Kjøring av testene av JPhil rammeverket
Kjøring av testene på riktige måte må du gjøre dette:
legg til begge Jar filene som ligger i mappen ApacheCommons til prosjekte.

steg for steg med intellij IDE:
intellij:
1. File
2. Project Structure 
3. Modules 
4. Dependencies tab
5. Trykk så på + tegnet
6. Jars or directories
7. Finn jar filene og ctrl + venstre trykk på begge jar filene (ligger inni JPhil rammeverket under mappen "ApacheCommons")
8. Ok -> Apply

## hvordan bruke rammeverket


````Java
// kjørest på port 8080 som default. (Du kan sende inn et port nummer som parameter hvis du har lyst til å bytte)
 JPhil app = JPhil.startWebServer();
        app.get("/", (request, response) -> {
        response.text("Hello world");
        });

````
### Full dokumentasjon se: JavaDoc eller dokumentasjons-nettsiden som har blitt laget (ligger under 'JPhil documentation' mappen).

Github repositorie: https://github.com/Fleischerboy/JPhil

