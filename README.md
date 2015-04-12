# GCDCalculator

Application structure
-------------
This is a maven project, it has three modules.

**GCDCalculator-ear**
: Assemble the application into a enterprise archive which includes an ejb jar archive and a war archive.

**GCDCalculator-ejb**
: EJB module, contains business layer, persistence layer and SOAP web service implementation.

- **QueueMngerBean**: Session bean, encapsulates JMS queue related operations that send and receive numbers from queue.
- **NumberMngerBean**: Session bean, encapsulates persistence related operatios that restore and retrieve numbers from database.
- **GCDBean**: Session bean, also expose SOAP web serivce. It calls QueueMngerBean to retrieve numbers and calcualte gcd of these number, and return result to web serivce client. 
             
**GCDCalculator-web**
: Web module, contains JAX-RS RESTful web serivce implementation.

- **GCDRestService**: A JAX-RS RESTful web serivce implementation, it calls QueueMngerBean to send number into queue, and calls NumberMngerBean to store and retrieve numbers.

**How to build**
--------
Check out the project.
As this is a Maven project, you need to have **Maven2** installed in your computer.

- Make sure you have **JDK1.6** or above installed, and environment variable **JAVA_HOME** pointing to the JDK folder.
- If you use **Windows** OS
Please find **build.bat** file in the project, change this line 

> set M2_HOME=C:\programs\apache-maven-2.0.9  

to point to your Maven installation, then run build.bat.

- If you use **Linux** or other OS

You need to add **%M2_HOME%\bin** into you **Path** environment variable, then from command line, go the the project folder, run following commands in sequence.
  
  > mvn -f GCDCalculator-ejb/pom.xml install
  > mvn -f GCDCalculator-web/pom.xml install
  > mvn package

**UnicoTest-ear.ear** will be generated under **UnicoTest-ear\target**.

**How to run**
-----
To avoid the complexity of Application Server configuration, this project has dependencies on some **JBoss6** built-in features, like JMS MOM hornetq, and built-in database hsqldb, hibernate, etc
so you will need to download a fresh **JBoss AS 6.1.0.Final** from http://jbossas.jboss.org/downloads/.
Once you have JBoss installed, copy **UnicoTest-ear.ear** into ***{jboss-6.1.0.Final_home}\server\default\deploy***, the run JBoss.

**How to test**
----
> **Note:** You may need to change the port number in following URLs.

1. Test **public String push(int i1, int i2)**;
The push RESTFul service's URL is http://localhost:8080/GCDCalculator-web/rest/gcdService/create
It accepts HTTP Post and consumes application/x-www-form-urlencoded MIME type.
A JSP has been provided to facilitate this testing.
You can load the JSP by URL: http://localhost:8080/GCDCalculator-web/restServiceTest.jsp

2. Test **public List<Integer> list()**;
This RESTFul service's URL is http://localhost:8080/GCDCalculator-web/rest/gcdService/list
This is very easy to test as you just need to do a HTTP GET request in web browser.

3. Test **SOAP web serivice**.
The WSDL URL is http://localhost:8080/GCDWS/GCDService/GCDBean?wsdl
You can use SoapUI or other tool to test it.
