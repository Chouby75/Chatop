`````markdown
## Usage

Chatop provides a RESTful API. Here are some common endpoints, including those from the Login, Message, and Rental controllers:

````markdown
## Running the Project with Maven

To run the project using Maven, execute the following command in the project's root directory:

```bash
mvn spring-boot:run
```

This command compiles the project and starts the Spring Boot application.

Refer to the API documentation (generated using Swagger/OpenAPI) for a complete list of endpoints, request/response formats, and authentication requirements. The API documentation is available at: http://localhost:3001/api/swagger-ui/index.html#/
````

**Authentication (Login Controller):**

- `POST /api/auth/register`: Register a new user account.
- `POST /api/auth/login`: Log in to an existing user account.
- `GET  /api/auth/me`: Get current user informations.

**Rentals (Rentals Controller):**

- `GET /api/rentals`: Retrieve a paginated list of available rentals.
- `GET /api/rentals/{id}`: Retrieve details for a specific rental.
- `POST /api/rentals`: Create a new rental listing (requires authentication and admin role).
- `PUT /api/rentals/{id}`: Update an existing rental (requires authentication and admin role).

**Messages (Messages Controller):**

- `GET /api/messages/{rental_id}`: Retrieve messages for a specific rental (requires authentication).
- `POST /api/messages`: Send a message related to a rental (requires authentication).

```

```
`````

```

```
