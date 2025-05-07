# Labs - Accommodation API

This is a Spring Boot REST API for managing accommodations, users, hosts, countries, and temporary reservations. It supports user registration, login, and role-based access using Spring Security, as well as reporting endpoints powered by materialized views.

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

## Database

* PostgreSQL (via Docker)
* Schema initialized using `schema.sql` with materialized views

To start the DB:

```bash
docker-compose up -d
```

---

## API Endpoints

### 1. Accommodations

* **GET** `/api/accommodations`
* **GET** `/api/accommodations/{id}`
* **POST** `/api/accommodations`

```json
{
  "name": "Beach House",
  "category": "HOUSE",
  "hostId": 1,
  "numRooms": 5
}
```

* **PUT** `/api/accommodations/{id}`

```json
{
  "name": "Updated Beach House",
  "category": "HOUSE",
  "hostId": 1,
  "numRooms": 6
}
```

* **DELETE** `/api/accommodations/{id}`
* **PUT** `/api/accommodations/rent/{id}`
* **GET** `/api/accommodations/by-host`

  * Returns number of accommodations per host (materialized view, refreshed daily)

### 2. Hosts

* **GET** `/api/hosts`
* **GET** `/api/hosts/{id}`
* **POST** `/api/hosts`

```json
{
  "name": "Alice",
  "surname": "Smith",
  "countryId": 1
}
```

* **PUT** `/api/hosts/{id}`
* **DELETE** `/api/hosts/{id}`
* **GET** `/api/hosts/by-country`

  * Returns number of hosts per country (materialized view, refreshed on host add/update/delete)

### 3. Countries

* **GET** `/api/countries`
* **GET** `/api/countries/{id}`
* **POST** `/api/countries`

```json
{
  "name": "France",
  "continent": "Europe"
}
```

* **PUT** `/api/countries/{id}`
* **DELETE** `/api/countries/{id}`

### 4. Authentication

* **POST** `/api/auth/register`

```json
{
  "username": "newuser",
  "password": "securepass",
  "role": "USER"
}
```

* **POST** `/api/auth/login`

```json
{
  "username": "user",
  "password": "user"
}
```

* **POST** `/api/auth/logout`

### 5. Temporary Reservations

Users can maintain a temporary list of accommodations before confirming reservations.

* **GET** `/api/reservations`
* **POST** `/api/reservations`

```json
{
  "accommodationId": 2
}
```

* **DELETE** `/api/reservations/{id}`
* **POST** `/api/reservations/confirm`

---

## Sample Usage with curl

### Authentication

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user", "password":"user"}'
```

### Create Accommodation

```bash
curl -X POST http://localhost:8080/api/accommodations \
  -H "Content-Type: application/json" \
  -d '{"name":"Cozy Flat", "category":"APARTMENT", "hostId":1, "numRooms":3}'
```

### Get Host Stats

```bash
curl http://localhost:8080/api/accommodations/by-host
```

### Get Country Stats

```bash
curl http://localhost:8080/api/hosts/by-country
```

---

## Swagger UI

Use Swagger to explore and test the API:

* URL: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
* Requires login — use `user/user` or `host/host`
