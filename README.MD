Portuguese:
# Boas Práticas com Web Services 

Exemplo de aplicação JEE7, utilizando Web Services SOAP e RESTFul, EJB e JPA com Hibernate.

1 - Foi criado um projeto Maven do Eclipse, é só importar o projeto no Eclipse.  

2 - A aplicação primeiro tem de ser executada localmente para criar e popular as tabelas, com o persistence-des.xml 

3 - Deve-se renomear o arquivo persistence-des.xml para persistence.xml.

4 - Deve-se executar a classe CreateTabelasTest.java.

5 - Verificar se as tabelas da aplicação foram criadas e populadas.

6 - Deve-se renomear o arquivo persistence.xml para persistence-des.xml.

7 - Deve-se renomear o arquivo persistence-prod.xml para persistence.xml.

8 - Deve-se mapear as api's do Jboss no Eclipse para se utilizar da versão do hibernate que está dentro dele, deve-se fazer o download da úlitma versão do driver jdbc do Mysql, e instalá-lo no Wildfly 11.

9 - Deve-se criar no Wildfly 11 ou versão superior, um datasource com o nome java:jboss/datasources/soapRestDS.

10 - Compilar a aplicação pelo Maven.

11 - Deve-se fazer o deploy no Wildfly com o arquivo bestpratices.war. 

12 - Para fazer o teste do WebService Soap, as url's são:
http://localhost:8080/bestpratices/BlogEndPointService?wsdl
http://localhost:8080/bestpratices/CategoriaEndPointService?wsdl

13 - Para fazer o teste dos serviços Rest, as url's são:
http://localhost:8080/bestpratices/blog
http://localhost:8080/bestpratices/categoria
_____________________________________________________________________________________________________________________

English:
# Best Practices with Web Services

Example of a JEE7 application, using Web Services SOAP and RESTFul, EJB and JPA with Hibernate.

1 - An Eclipse Maven project was created, just import the project into Eclipse.

2 - The application must first be run locally to create and populate the tables, with persistence-des.xml

3 - You must rename the persistence-des.xml file to persistence.xml.

4 - The CreateTablesTest.java class must be executed.

5 - Check whether the application tables were created and populated.

6 - You must rename the persistence.xml file to persistence-des.xml.

7 - You must rename the persistence-prod.xml file to persistence.xml.

8 - You must map the Jboss api's in Eclipse to use the version of Hibernate that is inside it, you must download the latest version of the Mysql jdbc driver, and install it in Wildfly 11.

9 - In Wildfly 11 or higher version, a datasource must be created with the name java:jboss/datasources/soapRestDS.

10 - Compile the application using Maven.

11 - You must deploy it to Wildfly with the bestpractices.war file.

12 - To test the WebService Soap, the url's are:
http://localhost:8080/bestpractices/BlogEndPointService?wsdl
http://localhost:8080/bestpractices/CategoriaEndPointService?wsdl

13 - To test Rest services, the url's are:
http://localhost:8080/bestpractices/blog
http://localhost:8080/bestpractices/categoria
