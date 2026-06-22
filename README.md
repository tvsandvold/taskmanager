# Task Manager API

A RESTful API built with Spring Boot, secured with JWT authentication and backed by PostgreSQL.

## Tech Stack

- Java 21
- Spring Boot 4.1
- Spring Security + JWT
- Spring Data JPA + Hibernate
- PostgreSQL
- Maven

## Features

- User registration and login with JWT authentication
- BCrypt password encryption
- Full CRUD for tasks (create, read, update, delete)
- Stateless REST API
- CORS configured for frontend integration

## Getting Started

### Prerequisites
- Java 21
- PostgreSQL
- Maven

### Setup

1. Clone the repository
git clone https://github.com/tvsandvold/taskmanager.git

2. Create a PostgreSQL database
```sql
   CREATE DATABASE taskmanager;
   CREATE USER terje WITH PASSWORD 'passord123';
   GRANT ALL PRIVILEGES ON DATABASE taskmanager TO terje;
```

3. Run the application
./mvnw spring-boot:run

The API will start on `http://localhost:8080`

## API Endpoints

### Auth
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/auth/register | Register a new user |
| POST | /api/auth/login | Login and receive JWT token |

### Tasks (requires JWT)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/tasks | Get all tasks |
| GET | /api/tasks/{id} | Get task by ID |
| POST | /api/tasks | Create a new task |
| PUT | /api/tasks/{id} | Update a task |
| DELETE | /api/tasks/{id} | Delete a task |

## Frontend

The React frontend for this project is available at:
https://github.com/tvsandvold/taskmanager-frontend