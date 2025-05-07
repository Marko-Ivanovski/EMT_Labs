# Labs - Accommodation API

This is a Spring Boot REST API for managing accommodations, users, hosts, countries, and temporary reservations. It supports user registration, login, and role-based access using Spring Security.

---

## Authentication

Authentication is handled via Spring Security using form login.

### Default Users

The following users are seeded on startup via the `DataInitializer`:

| Username | Password | Role |
| -------- | -------- | ---- |
| `user`   | `user`   | USER |
| `host`   | `host`   | HOST |

---

## Architecture Overview

This project follows clean architecture principles, separating responsibilities into:

* **Domain Models**: Core entities (`User`, `Host`, `Accommodation`, `Country`, `TemporaryReservation`)
* **DTOs**: For safe and structured data transfer between layers
* **Repositories**: Handle database operations (via Spring Data JPA)
* **Domain Services**: Encapsulate data access logic
* **Application Services**: Contain business logic and coordinate workflows
* **Controllers**: Expose REST endpoints
* **Security Layer**: Handles login, registration, and session management

---

## API Endpoints

All endpoints are protected and require authentication except `/api/auth/**`.

### 1. Accommodations

* **GET** `/api/accommodations`
  Returns all accommodations

* **GET** `/api/accommodations/{id}`
  Returns accommodation by ID

* **POST** `/api/accommodations`
  Create a new accommodation
  **Request body:**

  ```json
  {
    "name": "Beach House",
    "category": "HOUSE",
    "hostId": 1,
    "numRooms": 5
  }
  ```

* **PUT** `/api/accommodations/{id}`
  Update an existing accommodation
  **Request body:**

  ```json
  {
    "name": "Updated House",
    "category": "HOUSE",
    "hostId": 1,
    "numRooms": 6
  }
  ```

* **DELETE** `/api/accommodations/{id}`
  Delete an accommodation by ID

* **PUT** `/api/accommodations/rent/{id}`
  Mark an accommodation as rented

---

### 2. Hosts

* **GET** `/api/hosts`
  Returns all hosts

* **GET** `/api/hosts/{id}`
  Returns host by ID

* **POST** `/api/hosts`
  Create a new host
  **Request body:**

  ```json
  {
    "name": "Alice",
    "surname": "Smith",
    "countryId": 1
  }
  ```

* **PUT** `/api/hosts/{id}`
  Update a host

* **DELETE** `/api/hosts/{id}`
  Delete a host

---

### 3. Countries

* **GET** `/api/countries`
  Returns all countries

* **GET** `/api/countries/{id}`
  Returns country by ID

* **POST** `/api/countries`
  Create a new country
  **Request body:**

  ```json
  {
    "name": "France",
    "continent": "Europe"
  }
  ```

* **PUT** `/api/countries/{id}`
  Update a country

* **DELETE** `/api/countries/{id}`
  Delete a country

---

### 4. Authentication

* **POST** `/api/auth/register`
  Register a new user
  **Request body:**

  ```json
  {
    "username": "newuser",
    "password": "securepass",
    "role": "USER"
  }
  ```

* **POST** `/api/auth/login`
  Log in with existing credentials
  **Request body:**

  ```json
  {
    "username": "user",
    "password": "user"
  }
  ```

* **POST** `/api/auth/logout`
  Logout the current user

---

### 5. Temporary Reservations

Users can maintain a temporary list of accommodations before confirming reservations.

* **GET** `/api/reservations`
  View the user's current reservation list

* **POST** `/api/reservations`
  Add an accommodation to the list
  **Request body:**

  ```json
  {
    "accommodationId": 2
  }
  ```

* **DELETE** `/api/reservations/{id}`
  Remove an item from the reservation list

* **POST** `/api/reservations/confirm`
  Confirm all reservations. All listed accommodations will be marked as rented.

---

## H2 Database Console

Access the in-memory H2 database (enabled during development):

* **URL:** [http://localhost:8080/h2](http://localhost:8080/h2)
* **JDBC URL:** `jdbc:h2:mem:emt-2025`
* **Username:** `emt`
* **Password:** `emt`

---

## Swagger UI

API documentation and interactive testing are available via Swagger:

* **URL:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Swagger access requires login. Use one of the seeded users to authenticate.

---

## Authentication

Authentication is required for all endpoints except `/api/auth/register` and `/api/auth/login`

* **URL:** [http://localhost:8080/api/auth/login](http://localhost:8080/api/auth/login)