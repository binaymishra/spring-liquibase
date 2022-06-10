### Build
````
$ ./gradlew clean build
````
### Run
````
$ sh run.sh 
````
### Reference Links
[Using Liquibase with Spring Boot](https://docs.liquibase.com/tools-integrations/springboot/springboot.html) <br>
[spring-boot-liquibase-gradle-example](https://roytuts.com/spring-boot-liquibase-gradle-example/) <br>
[liquibase-handling-database-in-spring-boot](https://betterjavacode.com/programming/liquibase-handling-database-in-spring-boot) <br>
[using-liquibase-in-spring-boot-application](https://huongdanjava.com/using-liquibase-in-spring-boot-application.html) <br>

````
docker run --rm -v "/$PWD/data":/var/lib/mysql --name mysql -e MYSQL_ROOT_PASSWORD=mysql -e MYSQL_DATABASE=liquibase-db -p 3306:3306 mysql:latest

docker run --rm -v "/$PWD/data":/var/lib/mysql --name mysql -e MYSQL_ROOT_PASSWORD=mysql -e MYSQL_DATABASE=liquibase-db -p 3306:3306 mysql:oracle
````