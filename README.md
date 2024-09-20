# Recipe Management Application

## Overview

This Spring Boot application allows users to manage recipes. It provides functionalities to create, update, delete, and view recipes. The application is built using Java with Spring Boot for the backend and includes RESTful API endpoints to interact with recipe data.

## Features

- **Add a Recipe**: Create a new recipe with details such as name, type, ingredients, servings, and instructions.
- **Fetch All Recipes**: Get all the recipes persistent in the database.
- **Fetch All Recipes Paginated**: Get the recipes persistent in the database pagewise.
- **Update a Recipe**: Modify the details of an existing recipe.
- **Delete a Recipe**: Remove a recipe from the system.
- **View Recipes**: List all recipes with details.

## Technology Stack

- **Backend**: Java, Spring Boot
- **Database**: H2 (embedded database)
- **Model Mapping**: ModelMapper
- **Getter Setter Generation**: Lombok
- **Testing**: JUnit, Spring Boot Test
- **Documentation**: Swagger (OpenAPI)

## Setup

### Prerequisites

- JDK 22
- Maven
- IDE (e.g., IntelliJ IDEA, Eclipse)

### Clone the Repository

```bash
https://github.com/rupa22singh/RecipeMgmtJavaSpringboot.git
cd RecipeMgmtJavaSpringboot


Build and Run
1. Navigate to the Project Directory
    cd RecipeMgmtJavaSpringboot

2. Build the Project
    mvn clean install

3. Run the Application
    mvn spring-boot:run

The application will start on http://localhost:8080.

API Endpoints

Add a Recipe
Endpoint: POST /v1/recipes
Request Body:
{
  "name": "Recipe Name",
  # type either can be veg or nonveg
  "type": "veg",
  "ingredients": ["Ingredient 1", "Ingredient 2"],
  "servings": 4,
  "instructions": "Cooking instructions"
}

Update a Recipe
Endpoint: PUT /v1/recipes/{id}
Request Body: Same as Add Recipe
Get All Recipes
Endpoint: GET /v1/recipes
Get Recipe by ID
Endpoint: GET /v1/recipes/{id}
Delete a Recipe
Endpoint: DELETE /v1/recipes/{id}

Exception Handling

The application includes custom exception handling to provide meaningful error messages and HTTP status codes.

References:
https://start.spring.io/

https://maven.apache.org/

https://www.javatpoint.com/spring-boot-h2-database

https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#validation

https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api

License

This project is licensed under the  Apache License Version 2.0

