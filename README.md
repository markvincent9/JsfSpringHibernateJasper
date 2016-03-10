# JsfSpringHibernateJasper
JSF(Primefaces 5.3) - Spring Framework - Hibernate with Spring Data JPA integration with Jasper Reporting tool

##Installation

1. Install Maven, Follow this [link](http://www.mkyong.com/maven/how-to-install-maven-in-windows/) for the instruction
2. Clone this Repository
3. Go to the project directory via cmd and type "mvn clean install"

##Database setup

1. Download and Install [Postgresql](http://www.postgresql.org/download/) and [PgAdmin III](http://www.pgadmin.org/download/)
1. Create postgres database named "jsfdb" or you can edit the database name in /WEB-INF/applicationContext.xml
2. Under jsfdb, execute bookstore.sql under "public" schema
3. execute books.sql for dummy data of books

## How to run
1. Go to the project directory via cmd and type "mvn tomcat7:run"
2. Open your browser and go to ["http://localhost:8080/JsfSpringHibernateJasper"](http://localhost:8080/JsfSpringHibernateJasper)
