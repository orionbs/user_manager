# User Manager

## Introduction

This project is made to handle user management on all my other project.

* Registration of user
* Authentication of user
* Password encryption
* Password history
* Locking of users
* Management endpoint

## What do you want to do ?

* [Run this project](#running)
* [Develop this project](#developing)

## Running

If you just want to run this project, there is an **image** available on docker hub through this link.

```bash
docker pull orionbs/user_manager
```

The image expose the **8080** port.

Because, on the project we use database to store our data. You need to provide some environment variables and also
having a MySQL Server running.

Here is the properties, you need to fill.

```bash
SPRING_DATASOURCE_DRIVER-CLASS-NAME : com.mysql.cj.jdbc.Driver
SPRING_DATASOURCE_URL : jdbc:mysql://localhost:3306/plot_manage_database
SPRING_DATASOURCE_USERNAME : root
SPRING_DATASOURCE_PASSWORD : root-password
```

**Warning, the project used MySQL Driver, so you can't use any other SQL Driver.**

Hope the image will run perfectly on your environment. Enjoy !

## Developing

If you want to work on the project or just make it works on your favorite IDEA, please follow these steps.

### Requirements

* Maven
* Java JDK 17 or higher.

### Process

* **First**, clone the repository.

```bash
git clone git@github.com:orionbs/user_manager.git
```

* **Second**, enter the folder.

```bash
cd ./user_manager
```

* **Third**, you need to provide appropriate properties on this file.

```bash
application-local.yml
```

* **Fourth**, I assume that you have maven installed.

```bash
mvn clean package
```

* **Alternate fourth**, If you don't have maven, it is provided on the folder as mvnw.

```bash
./mvnw clean package
```

* **Fifth**, you can run the jar command.

```bash
java -jar ./target/user_manager-*.jar -Dspring.profiles.active=local
```

## Contributing

- This is a personal project, so any pull request would never be merged.
- No offense.

## License

[MIT](https://choosealicense.com/licenses/mit/)l


