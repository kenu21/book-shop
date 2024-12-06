# Book Shop Application

Welcome to the **Book Shop Application**! This project demonstrates a robust backend service for managing an online book store. The application leverages the power of **Spring Boot**, **Spring Security**, **Spring Data JPA**, and other modern technologies to provide a secure, scalable, and feature-rich service.

---

## 📚 **Project Overview**

The **Book Shop Application** is designed to streamline the process of managing books, categories, users, orders, and shopping carts. It offers role-based access control to ensure secure management and seamless user experience.

### Key Features:
- **Authentication & Authorization**:
    - User registration and login with JWT-based authentication.
    - Role-based access control for admin and regular users.
- **Book Management**:
    - Create, update, retrieve, and delete books.
    - Filter books by category.
- **Category Management**:
    - Create, update, retrieve, and delete categories.
- **Order Management**:
    - Place and manage orders.
    - Retrieve order details and items.
- **Shopping Cart**:
    - Add, update, and remove items from the cart.
    - Retrieve the user's cart.
- **API Documentation**:
    - Fully documented APIs using **Springdoc OpenAPI** for seamless integration.

---

## 🛠 **Technologies Used**

- **Java 17**
- **Spring Boot 3.3.5**
    - Spring Data JPA
    - Spring Security
    - Spring Validation
- **Liquibase** for database version control.
- **MySQL** as the primary database.
- **JWT (Json Web Token)** for secure user authentication.
- **Mapstruct** for DTO mapping.
- **Swagger** (via Springdoc OpenAPI) for API documentation.
- **Testcontainers** for integration testing.
- **Lombok** to reduce boilerplate code.

---

## ⚙️ **How to Set Up the Project**

### Prerequisites:
1. **Java 17** or higher installed.
2. **Maven 3.8+** installed.
3. **MySQL** database instance.

### Steps:
1. Clone the repository:
   ```bash
   git clone https://github.com/kenu21/book-shop
   cd book-shop
   ```

2. Set up the database:
    - Create a new database named `book_shop`.
    - Update the database connection details in `application.properties`:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/book_shop
      spring.datasource.username=<your-username>
      spring.datasource.password=<your-password>
      ```

3. Run Liquibase migrations:
   ```bash
   mvn liquibase:update
   ```

4. Build and run the application:
   ```bash
   mvn spring-boot:run
   ```

5. Access the application:
    - Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
    - API documentation: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 🗂 **API Endpoints Overview**

### **Authentication**
- `POST /auth/registration` - Register a new user.
- `POST /auth/login` - Authenticate and retrieve a JWT.

### **Books**
- `GET /books` - List all books (admin and user).
- `GET /books/{id}` - Get details of a specific book (admin and user).
- `POST /books` - Create a new book (admin only).
- `PUT /books/{id}` - Update an existing book (admin only).
- `DELETE /books/{id}` - Delete a book (admin only).

### **Categories**
- `GET /categories` - List all categories.
- `GET /categories/{id}` - Get category details.
- `POST /categories` - Create a new category (admin only).
- `PUT /categories/{id}` - Update or create a category (admin only).
- `DELETE /categories/{id}` - Delete a category (admin only).

### **Orders**
- `POST /orders` - Place a new order (authenticated users).
- `GET /orders` - Retrieve all orders for the authenticated user.
- `PATCH /orders/{id}` - Update an order (admin only).
- `GET /orders/{id}/items` - Retrieve items for a specific order.

### **Shopping Cart**
- `GET /cart` - Retrieve the user's shopping cart.
- `POST /cart` - Add an item to the shopping cart.
- `PUT /cart/items/{id}` - Update an item's quantity in the cart.
- `DELETE /cart/items/{id}` - Remove an item from the cart.

---

## 🧪 **Testing**

This application uses **Spring Boot Test** and **Testcontainers** for thorough testing:
- **Unit tests** validate the individual components.
- **Integration tests** ensure that the application works as expected with the database and other dependencies.

Run tests using:
```bash
mvn test
```

---

## 🌟 **Challenges Faced**

1. **Role-Based Access Control**:
    - Solved by using `@PreAuthorize` annotations and integrating JWT seamlessly with Spring Security.
2. **Efficient DTO Mapping**:
    - Resolved by integrating **Mapstruct**, reducing boilerplate code.
3. **Database Version Control**:
    - Managed with **Liquibase**, ensuring a robust migration strategy for different environments.

---

## 📹 **Demo Video**

Check out a quick walkthrough of the application on [Loom](https://www.loom.com/share/8ea0f33558b548238575fbb472110eb4?sid=008a093c-5aeb-43e1-b546-1a9eb1d135d3).

---

## 🎉 **Conclusion**

The **Book Shop Application** demonstrates the implementation of a modern, secure, and scalable backend service. It's a great example of utilizing the Spring ecosystem to build real-world applications. Contributions and feedback are welcome!