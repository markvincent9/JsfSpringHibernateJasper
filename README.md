# JsfSpringHibernateJasper
JSF(Primefaces 5.3) - Spring Framework - Hibernate with Spring Data JPA integration with Jasper Reporting tool

##Installation

1. Install Maven, Follow this [link](http://www.mkyong.com/maven/how-to-install-maven-in-windows/) for the instruction
2. Clone this Repository
3. Go to the project directory via cmd and type "mvn clean install"
4. create postgres database named "jsfdb" or you can edit the database name in /WEB-INF/applicationContext.xml

##Database setup

1. Under jsfdb, execute bookstore.sql under "public" schema
2. execute books.sql for dummy data of books

## How to run
5. Go to the project directory via cmd and type "mvn tomcat7:run"
