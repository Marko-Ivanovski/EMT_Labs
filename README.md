# Labs - Accommodation API

This is a Spring Boot REST API for managing accommodations for rent.

## **Authentication**
  **Username:** `admin`  
  **Password:** `admin`

## **API Endpoints**

### **1. Get All Accommodations**
**GET** `/api/accommodations`

### **2. Create new Accommodation**
**POST** `/api/accommodations`  
**Request:**
```json
{
  "name": "Beach House",
  "category": "HOUSE",
  "host": {
    "id": 1,
    "name": "John",
    "surname": "Doe",
    "country": {
      "id": 1,
      "name": "Macedonia",
      "continent": "Europe"
    }
  },
  "numRooms": 5,
  "rented": false
}
```

### **3. Update an Existing Accommodation**
**PUT** `/api/accommodations/{id}`  
**Request:**
```json
{
  "name": "Renovated Beach Home",
  "category": "HOUSE",
  "host": {
    "id": 1,
    "name": "John",
    "surname": "Doe",
    "country": {
      "id": 1,
      "name": "Macedonia",
      "continent": "Europe"
    }
  },
  "numRooms": 7,
  "rented": false
}
```

### **4. Delete an Accommodation**
**DELETE** `/api/accommodations/{id}`

### **5. Rent an Accommodation**
**PUT** `/api/accommodations/rent/{id}`

## H2 Database Console

You can access the H2 Database Console at: [http://localhost:8080/h2](http://localhost:8080/h2)

**JDBC URL:** `jdbc:h2:mem:emt-2025`  
**Username:** `emt`  
**Password:** `emt`

## **Swagger UI**

You can access the Swagger UI at: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)