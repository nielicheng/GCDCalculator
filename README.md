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

How to build
--------
Check out the project.
As this is a Maven project, you need to have Maven2 installed in your computer.

- If you use Windows OS
you can edit build.bat file, change this line set M2_HOME=C:\programs\apache-maven-2.0.9 point to you Maven installation, then run build.bat.

- If you use other OS
You need to add %M2_HOME%\bin into you environment variable, then from command line, go the the project folder, run mvn package

UnicoTest-ear.ear will be generated under UnicoTest-ear\target.
