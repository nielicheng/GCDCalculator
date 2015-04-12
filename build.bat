set M2_HOME=C:\programs\apache-maven-2.0.9
set PATH=%M2_HOME%\bin;%PATH%

call mvn clean

call mvn -f GCDCalculator-ejb/pom.xml install

call mvn -f GCDCalculator-web/pom.xml install

call mvn package