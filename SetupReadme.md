## setup (IntelliJ)
- Lag nytt Maven Intellij prosjekt
- Java 17 
- Tryk på File -> new - module from existing sources
- velg filen til å importere: [pom.xml] filen inni JPhil rammeverket

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


### kjøring av testene av JPhil rammeverket
kjøring av testene på riktige måte må du gjøre dette:
legg til begge Jar filene som ligger i mappen ApacheCommons til prosjekte.

steg for steg med intellij IDE:
intellij:
1. File
2. Project Structure 
3. Modules 
4. Dependencies tab
5. trykk så på + tegnet
6. Jars or directories
7. finn jar filene og ctrl + venstre trykk på begge jar filene (ligger inni JPhil rammeverket under mappen "ApacheCommons")
8. ok -> apply

## hvordan bruke rammeverket

````Java
JPhil app = JPhil.startWebServer(); // kjørest på port 8080 som default. (Du kan sende inn et port nummer som parameter hvis du har lyst til å bytte)
        app.get("/", (request, response) -> {
        response.text("Hello world");
        });

````
### full dokumentasjon se: JavaDoc eller dukumentasjons nettsiden som har blitt laget (ligger under 'JPhil documentation' mappen).

