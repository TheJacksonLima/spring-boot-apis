# Car API

A REST API for car management, built with **Spring Boot**, **Spring Data JPA / Hibernate**, and **DTO (record)** for clean data transfer between layers.

The API provides functionality to:
- Create a car
- List all cars
- Retrieve by **ID** (integer)
- Retrieve by **UUID**
- Handle errors with a custom exception (`CarNotFoundException`)

---

## 🧩 Project Structure

```
src/
 ├── main/
 │   ├── java/
 │   │   └── com/jfl/car_api/
 │   │        ├── car/               ← entities and DTOs (e.g., `Car.java`, `CarDTO.java`)
 │   │        ├── controller/        ← REST controllers (e.g., `CarController.java`)
 │   │        ├── service/           ← business logic (e.g., `CarService.java`)
 │   │        ├── repository/        ← JPA interfaces (e.g., `CarRepository.java`)
 │   │        └── exceptions/        ← custom exceptions (e.g., `CarNotFoundException.java`)
 │   └── resources/
 │        ├── application.properties (or application.yml)
 │        └── other configuration files
 └── test/
      └── ... (unit / integration tests)
```

---

## 🚀 Features / Endpoints

| HTTP Method | URI | Purpose | Expected Return |
|--------------|-----|----------|-----------------|
| `GET` | `/cars` | List all cars | List of `CarDTO` |
| `GET` | `/cars/{id}` | Get a car by ID (integer) | A single `CarDTO` or `404` if not found |
| `GET` | (if implemented) `/cars/uuid/{uuid}` | Get a car by UUID | A single `CarDTO` or `404` |
| `POST` | `/cars` | Create a new car | Returns `201 Created`, optionally with resource location |
| (Optional) `PUT` / `PATCH` | `/cars/{uuid}` | Update a car by UUID | Updated resource or `404` |
| (Optional) `DELETE` | `/cars/{uuid}` | Delete a car by UUID | `204 No Content` or `404` |

---

## 📦 Dependencies & Technologies

- Java (compatible with Spring Boot version used)
- Spring Boot (starter-web, starter-data-jpa, etc.)
- Hibernate / JPA ORM
- Relational database (H2, PostgreSQL, MySQL — depending on config)
- Record DTOs for immutable, clean responses  
- Custom exception handling (`CarNotFoundException`)

---

## 🛠️ How to Run Locally

1. Clone the repository  
   ```bash
   git clone https://github.com/TheJacksonLima/spring-boot-apis.git
   cd spring-boot-apis/car_api
   ```
2. Configure your database in `application.properties` or `application.yml` (URL, user, password, dialect, etc.)
3. Build and run:
   ```bash
   mvn spring-boot:run
   ```
   or, if using Gradle:
   ```bash
   ./gradlew bootRun
   ```
4. The API will be available at `http://localhost:8080` (or your configured port).

---

## 🎯 Example Usage

### List all cars
```
GET http://localhost:8080/cars
```
Response:
```json
[
  {
    "uuid": "uuid-of-the-car",
    "brand": "ExampleBrand",
    "model": "ExampleModel",
    "color": "Blue",
    "year": 2023,
    "price": 75000.0,
    "km": 0
  },
  ...
]
```

### Get by ID
```
GET http://localhost:8080/cars/1
```
Response:
```json
{
  "uuid": "uuid-of-the-car",
  "brand": "ExampleBrand",
  "model": "ExampleModel",
  "color": "Blue",
  "year": 2023,
  "price": 75000.0,
  "km": 0
}
```
If the car is not found, the API returns `404 Not Found`.

### Create a new car
```
POST http://localhost:8080/cars
Content-Type: application/json

{
  "brand": "Honda",
  "model": "Civic",
  "color": "Black",
  "year": 2024,
  "price": 130000.0,
  "km": 0
}
```
Expected Response:  
- Status: **201 Created**  
- Header `Location`: URL of the created resource (e.g., `/cars/{id}`)

---

## ✅ Best Practices & Future Improvements

### Already Implemented

- DTO (record) for entity-to-response separation  
- Custom exception for resource not found (`CarNotFoundException`)  
- Layered architecture (controller / service / repository / exceptions / model)  
- Static factory method (`CarDTO.from()`) for entity-to-DTO mapping  

### Potential Future Improvements

- Implement update (`PUT`, `PATCH`) and delete (`DELETE`) endpoints  
- Input validation with `@Valid` / `javax.validation`  
- Pagination, filtering, and sorting for listing endpoints  
- API documentation with Swagger / OpenAPI  
- Unit and integration tests for service and controller layers  
- Centralized exception handling using `@ControllerAdvice`  
- Structured logging and monitoring  
- Database migration tool (Flyway or Liquibase)  
- Security layer (authentication/authorization) if required  
