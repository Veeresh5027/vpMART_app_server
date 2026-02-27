# vpMART - E-Commerce Backend (Server)

[![Java](https://img.shields.io/badge/Java-17%2B-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.x-blue)](https://www.mysql.com/)
[![Maven](https://img.shields.io/badge/Build-Maven-red)](https://maven.apache.org/)

**vpMART** is a high-performance, scalable REST API backend built using the **Spring Boot** framework. This server handles the core business logic, database management, and secure data flow for the vpMART e-commerce platform.

---

## 🚀 Key Features

* **User Lifecycle Management:** Complete flow for User Registration and Secure Login.
* **Account Recovery:** Fully implemented **Forgot Password** and **Reset Password** functionality.
* **Product Catalog:** Full CRUD operations for products, including categorization and inventory tracking.
* **Shopping Cart Logic:** Advanced state management for user carts, allowing real-time updates.
* **Order Processing:** Robust checkout workflow and order history tracking.
* **RESTful Architecture:** Clean and documented API endpoints for seamless frontend integration.
* **Global Exception Handling:** Centralized error management for consistent API responses.

---

## 🛠️ Technical Stack

* **Language:** Java 17
* **Framework:** Spring Boot 3.x
* **Data Access:** Spring Data JPA / Hibernate
* **Database:** MySQL
* **Build Tool:** Maven
* **Utility:** Lombok (to keep code clean and concise)

---

## 📂 Project Architecture

The project follows a standard **Layered Architecture** to ensure separation of concerns and maintainability:

```text
src/main/java/com/vpmart/
├── controller/         # REST Controllers (API Endpoints)
├── dto/                # Data Transfer Objects (Request/Response bodies)
├── entity/             # Database Models (JPA Entities)
├── exception/          # Custom Exception classes & Global Handler
├── repository/         # Data Access Layer (JPA Repositories)
├── service/            # Business Logic Layer (Interfaces & Impls)
└── VpMartApplication   # Main Entry Point
