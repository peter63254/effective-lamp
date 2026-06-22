# Flower Shop - Flower Information Management System

## Project Overview
A small flower shop flower information management system built with Vue3 + Spring Boot (front-end/back-end separation).

**Course:** Software Production Internship (2025-2026 Semester 2)  
**Tech Stack:** Vue3 + TypeScript + Element Plus + Pinia (Frontend) / Spring Boot 2.7 + JPA + H2/MySQL (Backend)

## Features

### Customer Side
- Browse flower list with category filtering
- Search flowers by keyword
- View flower details (price, stock status, flower language, description)
- User registration and login

### Admin Side
- Flower management: add, edit, delete flowers
- Update flower prices and stock status (In Stock / Low Stock / Out of Stock)
- Category management: add, edit, delete flower categories

### No order/payment workflow
This system focuses on information display and backend maintenance only.

## Project Structure
```
flower-shop/
├── backend/                    # Spring Boot Backend
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/flowershop/
│       │   ├── config/         # CORS configuration
│       │   ├── controller/     # RESTful API controllers
│       │   ├── model/
│       │   │   ├── dto/        # Data transfer objects
│       │   │   └── entity/     # JPA entities (User, Flower, Category)
│       │   ├── repository/     # JPA repositories
│       │   └── service/        # Business logic services
│       └── resources/
│           ├── application.yml
│           ├── data.sql        # Seed data
│           └── static/images/  # Flower images
├── frontend/                   # Vue3 Frontend
│   ├── package.json
│   ├── vite.config.ts
│   └── src/
│       ├── api/                # Axios API calls
│       ├── components/         # Shared components (NavBar)
│       ├── router/             # Vue Router config
│       ├── stores/             # Pinia stores
│       ├── types/              # TypeScript type definitions
│       └── views/              # Page components
│           └── admin/          # Admin pages
└── README.md
```

## Quick Start

### Prerequisites
- JDK 11+
- Maven 3.6+
- Node.js 18+
- MySQL 8.0 (optional, H2 is used by default for development)

### 1. Start Backend
```bash
cd backend
mvn spring-boot:run
```
The backend starts at http://localhost:8080

Default accounts (auto-created):
| Role    | Username | Password |
|---------|----------|----------|
| Admin   | admin    | admin123 |
| User    | user     | user123  |

H2 Console: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:flowershop)

### 2. Start Frontend
```bash
cd frontend
npm install
npm run dev
```
The frontend starts at http://localhost:3000

### 3. Using MySQL (Optional)
1. Create a database named `flowershop`
2. Edit `application.yml`:
   - Comment out the H2 datasource configuration
   - Uncomment the MySQL datasource configuration
   - Update the password
3. Restart the backend

## API Endpoints

| Method | Path | Description | Auth |
|--------|------|-------------|------|
| POST | /api/auth/register | User registration | No |
| POST | /api/auth/login | User login | No |
| GET | /api/flowers | List flowers (with filters) | No |
| GET | /api/flowers/{id} | Get flower detail | No |
| GET | /api/categories | List categories | No |
| POST | /api/admin/flowers | Create flower | Admin |
| PUT | /api/admin/flowers/{id} | Update flower | Admin |
| PATCH | /api/admin/flowers/{id}/stock | Update stock status | Admin |
| DELETE | /api/admin/flowers/{id} | Delete flower | Admin |
| POST | /api/admin/categories | Create category | Admin |
| PUT | /api/admin/categories/{id} | Update category | Admin |
| DELETE | /api/admin/categories/{id} | Delete category | Admin |

## Screenshots
(Screenshots to be added during testing)
- Login Page
- Home Page (Flower Browsing)
- Flower Detail Page
- Admin Dashboard
- Flower Management
- Category Management

## Course Requirements Met
- [x] Front-end/back-end separation (Vue3 + Spring Boot)
- [x] RESTful API
- [x] MySQL/H2 database
- [x] Git version control
- [x] User registration/login
- [x] User/admin role separation
- [x] Core CRUD operations (flowers + categories)
  - [x] Flower: create, read, update, delete
  - [x] Flower: filter by category, search by keyword
  - [x] Flower: update stock status
  - [x] Category: create, read, update, delete
  - [x] Category: sort order management
- [x] Vue3 + Vue Router + Pinia
- [x] Element Plus UI framework
- [x] TypeScript
- [x] Clean code structure and naming conventions
- [x] Clear Git commit history
  - [x] Clear commit messages
  - [x] Detailed development history
- [x] No order/payment workflow (pure information display and maintenance)
