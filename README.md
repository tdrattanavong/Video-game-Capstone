# Video Game Store

## Project Description

Video Game Store is a full-stack e-commerce application built with a Java Spring Boot API, MySQL database, and a frontend website. Users can browse games, filter by category and price, manage a shopping cart, and place orders.

This project was built as a capstone for the Year Up United Application Development program. I worked mainly on the backend side — fixing existing bugs, completing missing controller and service logic, wiring up the shopping cart and order flow, and making sure the authentication and role-based access were properly enforced throughout the API.

The goal was to get experience building and maintaining a layered backend application with real authentication, database persistence, and proper API design.

---

## Main Features

- User registration and login
- JWT-based authentication
- Product browsing with filtering by category, price range, and subcategory
- Category management
- Shopping cart management (add, update, clear)
- Checkout and order creation
- User profile management
- Role-based access control for admin-only actions

---

## Technical Overview

The backend handles all the business logic for products, categories, profiles, shopping carts, and orders. The API connects to a MySQL database and uses JWT tokens so each user can securely manage their own cart and checkout information. The frontend talks to the API through JavaScript and lets users interact with the store through actual web pages instead of calling the API manually.

### Backend Architecture

The API is organized into four main layers:

- Controllers handle incoming HTTP requests and return responses
- Services contain the business logic
- Repositories communicate with the database using JPA
- Models represent the database entities and data used throughout the app

### Main Backend Packages

| Layer | Package | Examples |
|---|---|---|
| Controllers | org.yearup.controllers | ProductsController, ShoppingCartController, OrderController |
| Services | org.yearup.service | ProductService, ShoppingCartService, OrderService |
| Repositories | org.yearup.repository | ProductRepository, ShoppingCartRepository, OrderRepository |
| Models | org.yearup.models | Product, CartItem, ShoppingCart, Order, OrderLineItem |

### API Endpoints

| Area | Method | Endpoint | Description |
|---|---|---|---|
| Authentication | POST | /auth/register | Registers a new user |
| Authentication | POST | /auth/login | Logs in a user and returns a JWT token |
| Products | GET | /products | Gets all or filtered products |
| Products | GET | /products/{id} | Gets one product by id |
| Products | POST | /products | Creates a product, admin only |
| Products | PUT | /products/{id} | Updates a product, admin only |
| Products | DELETE | /products/{id} | Deletes a product, admin only |
| Categories | GET | /categories | Gets all categories |
| Categories | GET | /categories/{id} | Gets one category by id |
| Shopping Cart | GET | /cart | Gets the current user's cart |
| Shopping Cart | POST | /cart/products/{productId} | Adds a product to the cart |
| Shopping Cart | PUT | /cart/products/{productId} | Updates item quantity |
| Shopping Cart | DELETE | /cart | Clears the current user's cart |
| Profile | GET | /profile | Gets the current user's profile |
| Profile | PUT | /profile | Updates the current user's profile |
| Orders | POST | /orders | Creates an order from the cart |

---
---

## Database Setup

1. Open the project in IntelliJ IDEA

2. Locate the database script inside the project:
   ```
   capstone-api-starter/database/create_database_videogamestore.sql
   ```

3. Open MySQL Workbench

4. Open and run the full SQL script to create the database and populate it with starter data

5. Confirm that the database was created and contains the required tables

Tables used in this project:
```
users
profiles
categories
products
shopping_cart
orders
order_line_items
```

The starter database includes these sample users:

| Username | Password |
|---|---|
| user | password |
| admin | password |

---

## API Setup

1. Open the API project (`capstone-api-starter`) in IntelliJ IDEA

2. Confirm the project SDK is set to Java 17

3. Open the Spring Boot configuration file:
   ```
   src/main/resources/application.properties
   ```

4. Update the MySQL connection settings to match your local database:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/videogamestore
   spring.datasource.username=<your_username>
   spring.datasource.password=<your_password>
   ```

5. Run the Spring Boot application from IntelliJ IDEA

6. Confirm the API is running at:
   ```
   http://localhost:8080
   ```

Example endpoints to test it's working:
```
POST http://localhost:8080/auth/login
GET  http://localhost:8080/products
GET  http://localhost:8080/categories
```

---

## Website Setup

1. Open the frontend folder (`capstone-client-videogamestore`) in VS Code

2. Make sure the backend API is already running at `http://localhost:8080`

3. If the API is on a different port, update the base URL in:
   ```
   js/config.js
   ```

4. Right-click `index.html` and select **Open with Live Server**

5. The site should open in your browser and connect to the running API

Note: All controllers used by the frontend include `@CrossOrigin` so the browser doesn't block requests between the frontend and the API.

---

## Technologies Used

### Back End
- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- Maven

### Front End
- HTML
- CSS
- JavaScript
- Bootstrap
- Axios
- Mustache.js

### Tools
- IntelliJ IDEA
- MySQL Workbench
- Insomnia
- Git
- GitHub

---

## Notes

- The backend API is the main focus of this capstone project
- The Insomnia collection (`capstone-insomnia-collection.yaml`) has pre-built requests for every endpoint if you want to test the API without the frontend
- Swagger UI is also available at `http://localhost:8080/swagger-ui.html` when the server is running
