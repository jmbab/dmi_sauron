# dmi_sauron

## Project & Goal:
A webapp to monitor a server's service status from a web-interface 
by developper (datamatiker) student Jean-Marie Babonneau, Spring / Summer 2022.
​
Prototype or Minimum Viable Product of a webapp to monitor a processing server's service status from a web-interface.
It uses a Client-Server model in Java and a Java Spring Boot application with Thymeleaf, JavaScript, HTML and Bootstrap CSS.
​
## Project delimitation:
The complexity of the project had to be limited to one server and single-threading, because of limited resources (single developer 
student and 6 weeks development period including the writing of an extensive documentation of the development process for the report 
of an exam project. There is no access security as the product is for an internal use. 
​
## Possible future extensions:
A final extended future product should be able to monitor about 60 servers "simultaneously" and handle actions on the server in case of problems via buttons in the web-interface to send instructions to the server ("restart" for example). More parameters should be monitored. A more developed database including a timestamp for each JSON-file archive which would allow the very useful display of statistics for dataflow visualization as diagrams or curves (filtered by type of parameter in the web-interface) on a timeline. The product should also implement multithreading, but as getting data from 60 servers can take time, the web-interface table would be populated with the latest available data, until the newest data is available. It should be a scalable product.
​
## Simplified process description:
- 1) the client sends a request to the server
- 2) the server receives the request
- 3) the server runs a bash script to generate a JSON-file about some of its services and operations
- 4) the server sends the JSON-file to the client
- 5) the client receives the JSON-file from the server, adds and concatenates a server name prefix in the JSON file name
- 6) A list of servers (for now only one) is created from reading and mapping the JSON-file into a Java objects, which is saved by the JPA repository into an H2 database.
- 7) An endpoint with the JSON-data is made available by the Restcontroller
- 8) The JavaScript index generates an HTML table structure in which it assigns the key & value parameters of the JSON-data read from the Restcontroller endpoint.
- 9) The HTML index page is styled with the imported Bootstrap CSS. The defer keyword by the JavaScript script declaration will only show the result in the table when the script has completed. The HTML index page gets its parts from Thymeleaf HTML fragments like header, menu bar, banner (jumbotron) and footer.
- 10) The HTML table is populated with the JSON-data by rows. One row per server. One column per parameter.
​

