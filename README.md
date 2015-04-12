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

