# REST API in Java with Spring Boot

This project is a simple REST API created with Spring Boot that allows managing the CUSTOMER domain, specifically for the UPDATE microservice. The API offers the basic operation such as updating a customer, displaying the SWAGGER documentation technology screen as the main page.
## Project Structure

- **`UpdateCustomerApplication.java`**: The main class that runs the Spring Boot application and defines the API controller.

- `PUT /api/cutomers/update/{id}`: Allows you to update the customer, under the required columns and ID.

## Requirements

- **JDK 17** o superior.
- **Maven** (for dependency management and project construction).

## Installation

1. **Clone the repository**

    ```bash
    git clone <https://github.com/kevinseya/microservicio-logistic-update-customer.git>
    ```

2. **Build and run the application** with Maven:

    ```bash
    mvn spring-boot:run
    ```

3. The application run on: `http://localhost:8080`.

## Use of endpoint

### 1. PUT /api/customers/update/{id}

Update a user. The request body must contain the user details in JSON format.
PUT request example:
```bash
PUT /api/customers/update/{id} Content-Type: application/json
    
    { 
    "name": "John", "lastname": "Doe",
    "email": "john.doe2@example.com",
    "phone": "1234567890",
    "password": "securePassword123",
    "rol": "ADMIN" 
    "address":"Gonzalo Hidalgo y Gualberto Perez S9-50"
    }
```
**Response:**
```plaintext
    {
        "id": "550e8400-e29b-41d4-a716-446655440000",
        "name": "John",
        "lastname": "Doe",
        "email": "john.doe2@example.com",
        "phone": "1234567890",
        "rol": "ADMIN"
        "address":"Gonzalo Hidalgo y Gualberto Perez S9-50"
    }
```
**Response code:**

- **`200 Updated:`** Customer created successfully.
- **`404 Not Found:`** Customer not found.
- **`500 Internal Server Error:`** Server error.
