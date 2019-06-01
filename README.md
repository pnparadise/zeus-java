## Use JPA + Hibernate + MySQL in Spring Boot

### Usage

- Run the application and go on http://localhost:8080/


### Build and run

#### Configurations

Open the `application.yml` file and set your own configurations for the
database connection.

#### Prerequisites

- Java 8
- Gradle 5

#### From terminal

Go on the project's root folder, then type:

    $ gradle bootRun

#### From Intellij (Spring Tool Suite)

Import as *Existing Maven Project* and run it as *Spring Boot App*.


Install lomboak plugin and Enable lombok plugin for this project (Don't forget to enable annotation processing under compiler setting for lombok itself)