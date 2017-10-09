
You need to specify where the application server is running so the client can talk to it.

To modify the property file in order to specify the hosts that the
application server and database server are on then edit the application.properties file
and launch this application like below where you specify where the property file you
wish to use is at.

java -jar -Dspring.config.location=./application.properties sageIntegrationClient.jar