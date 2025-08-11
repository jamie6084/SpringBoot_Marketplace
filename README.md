# Online Marketplace - Spring Boot Application

A full-stack e-commerce web application built with Spring Boot, MySQL, and Thymeleaf.

## Features

- User authentication and registration
- Product catalog with search functionality
- Shopping cart management
- Order processing and history
- Admin dashboard for product and order management
- Responsive web design

## Technology Stack

- **Backend**: Spring Boot 3.x
- **Database**: MySQL
- **Frontend**: Thymeleaf, HTML, CSS, JavaScript
- **Build Tool**: Maven
- **Containerization**: Docker

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- Docker (optional)

## Setup Instructions

### 1. Clone the repository
```bash
git clone <your-repository-url>
cd demo
```

### 2. Database Setup
1. Create a MySQL database named `mydatabase`
2. Copy `src/main/resources/application.properties.template` to `src/main/resources/application.properties`
3. Update the database credentials in `application.properties`:
   ```properties
   spring.datasource.username=YOUR_DATABASE_USERNAME
   spring.datasource.password=YOUR_DATABASE_PASSWORD
   ```

### 3. Run the Application

#### Option A: Using Maven
```bash
./mvnw spring-boot:run
```

#### Option B: Using Docker
```bash
docker-compose up
```

### 4. Access the Application
- **Main Application**: http://localhost:8080
- **Admin Dashboard**: http://localhost:8080/admin (requires admin credentials)

## Project Structure

```
src/
├── main/
│   ├── java/com/example/demo/
│   │   ├── config/          # Security configuration
│   │   ├── controller/      # REST controllers
│   │   ├── model/          # Entity classes
│   │   ├── repository/     # Data access layer
│   │   └── service/        # Business logic
│   └── resources/
│       ├── static/         # CSS, JS, images
│       └── templates/      # Thymeleaf templates
└── test/                   # Unit tests
```

## API Endpoints

- `GET /` - Home page
- `GET /shop` - Product catalog
- `GET /cart` - Shopping cart
- `POST /cart/add/{productId}` - Add item to cart
- `GET /orders` - Order history
- `GET /admin` - Admin dashboard

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request

## License

This project is licensed under the MIT License.

## Author

Student ID: 22483136 