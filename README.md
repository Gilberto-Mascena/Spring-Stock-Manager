# Spring Stock Manager

[English](https://github.com/Gilberto-Mascena/projeto-agenda-spring-boot/blob/main/README.md) |
[Português Brasileiro](https://github.com/Gilberto-Mascena/projeto-agenda-spring-boot/blob/main/README-pt_br.md)

[![Status de Manutenção](https://img.shields.io/badge/Maintained-Yes-brightgreen?style=for-the-badge)](https://github.com/Gilberto-Mascena/Spring-Stock-Manager)
[![GitHub Actions Status](https://img.shields.io/github/actions/workflow/status/Gilberto-Mascena/Spring-Stock-Manager/build.yml?style=for-the-badge)](https://github.com/Gilberto-Mascena/desafio-back-end-jr/actions)
[![Licença](https://img.shields.io/github/license/Gilberto-Mascena/Spring-Stock-Manager?style=for-the-badge)](https://github.com/Gilberto-Mascena/Spring-Stock-Manager/blob/main/LICENSE.md)
[![Estrelas no GitHub](https://img.shields.io/github/stars/Gilberto-Mascena/Spring-Stock-Manager?style=for-the-badge)](https://github.com/Gilberto-Mascena/Spring-Stock-Manager/stargazers)
[![Problemas no GitHub](https://img.shields.io/github/issues/Gilberto-Mascena/Spring-Stock-Manager?style=for-the-badge)](https://github.com/Gilberto-Mascena/Spring-Stock-Manager/issues)
[![Versão do Repositório](https://img.shields.io/github/v/release/Gilberto-Mascena/Spring-Stock-Manager?include_prereleases&style=for-the-badge)](https://github.com/Gilberto-Mascena/Spring-Stock-Manager/releases)
![Data de Lançamento](https://img.shields.io/github/release-date/Gilberto-Mascena/Spring-Stock-Manager?style=for-the-badge)
![Tamanho do Repositório](https://img.shields.io/github/repo-size/Gilberto-Mascena/Spring-Stock-Manager?style=for-the-badge)

---

## Project Description

**Spring Stock Manager** is a RESTful API developed with Spring Boot 3.3.1, designed to efficiently manage product inventory, suppliers, and movements (incoming and outgoing). The project follows REST API design principles and features data validation, global exception handling, and automatic documentation via Swagger UI (SpringDoc OpenAPI).

## Features

* **Suppliers**:
* Register, list, search by ID, update, and delete suppliers.
* Validate a unique CNPJ (Brazilian Taxpayer Registry).
* **Products**:
* Register, list, search by ID, update, and delete products.
* Associate products with suppliers.
* **Stock Movements**:
* Record product entries and exits in inventory.
* Automatic update of product stock quantity.
* Verify sufficient stock for outgoing orders.
* Generate movement reports by product. * Movement filters by period and type (if implemented in services).
* **Error Handling**:
* `GlobalExceptionHandler` to handle exceptions centrally and return standardized error messages (e.g., 400 Bad Request, 404 Not Found, 409 Conflict).
* **Interactive Documentation**:
* Swagger UI to explore and test all API endpoints.
* **CORS**:
* Configuration to allow requests from different origins (essential for front-end integration).

---

## Technologies Used

* **Java 17**: Programming language.
* **Spring Boot 3.3.1**: Framework for rapid API development.
* **Spring Data JPA**: Abstraction for data persistence.
* **Hibernate**: Implementation of the JPA specification.
* H2 Database (Memory): In-memory database for development and testing.
  Maven: Dependency management and project build tool.
  SpringDoc OpenAPI (Swagger UI): Automatic generation of API documentation.
  Jakarta Validation (Bean Validation): For data validation in DTOs.
  Postman: For testing API endpoints (provided collection).
---

## Project Folder Structure

Below is the project directory structure, showing the organization of classes and resources:

```plaintext
.
├── HELP.md
├── LICENSE.md
├── README-pt_br.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── br
    │   │       └── com
    │   │           └── mascenadev
    │   │               └── springstockmanager
    │   │                   ├── SpringStockManagerApplication.java
    │   │                   ├── config
    │   │                   │   ├── SwaggerConfig.java
    │   │                   │   └── WebConfig.java
    │   │                   ├── controller
    │   │                   │   ├── FornecedorController.java
    │   │                   │   ├── MovimentacaoController.java
    │   │                   │   └── ProdutoController.java
    │   │                   ├── exception
    │   │                   │   ├── CnpjJaCadastradoException.java
    │   │                   │   ├── EstoqueInsuficienteException.java
    │   │                   │   ├── FornecedorNaoEncontradoException.java
    │   │                   │   ├── GlobalExceptionHandler.java
    │   │                   │   ├── MovimentacaoNaoEncontradaException.java
    │   │                   │   ├── ProdutoNaoEncontradoException.java
    │   │                   │   └── StandardError.java
    │   │                   ├── model
    │   │                   │   ├── enums
    │   │                   │   │   └── TipoMovimentacao.java
    │   │                   │   ├── fornecedor
    │   │                   │   │   ├── Fornecedor.java
    │   │                   │   │   └── dto
    │   │                   │   │       ├── FornecedorRequestDTO.java
    │   │                   │   │       └── FornecedorResponseDTO.java
    │   │                   │   ├── movimentacao
    │   │                   │   │   ├── Movimentacao.java
    │   │                   │   │   └── dto
    │   │                   │   │       ├── MovimentacaoRequestDTO.java
    │   │                   │   │       └── MovimentacaoResponseDTO.java
    │   │                   │   └── produto
    │   │                   │       ├── Produto.java
    │   │                   │       └── dto
    │   │                   │           ├── ProdutoRequestDTO.java
    │   │                   │           └── ProdutoResponseDTO.java
    │   │                   ├── repository
    │   │                   │   ├── FornecedorRepository.java
    │   │                   │   ├── MovimentacaoRepository.java
    │   │                   │   └── ProdutoRepository.java
    │   │                   └── service
    │   │                       ├── FornecedorService.java
    │   │                       ├── MovimentacaoService.java
    │   │                       └── ProdutoService.java
    │   └── resources
    │       ├── application.yml
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── br
                └── com
                    └── mascenadev
                        └── springstockmanager
                            └── SpringStockManagerApplicationTests.java

```

## How to Run the Project

### Prerequisites

Make sure you have the following tools installed:

* **Java Development Kit (JDK) 17 or higher**
* **Maven 3.x**

### Steps to Execution

1. **Clone the repository:**
```bash
  git clone [https://github.com/your-username/spring-stock-manager.git](https://github.com/your-username/spring-stock-manager.git) # Replace with your GitHub link
  cd spring-stock-manager
```
2. **Build the project with Maven:**

```bash
  mvn clean install
```
3. **Run the Spring Boot application:**

```bash
  mvn spring-boot:run

```
```
  The application will be started on the port `8080` by default. You'll see something like:
  Started SpringStockManagerApplication in X.XXX seconds (process running for Y.YYY)
```

---

## Database Configuration (H2)

The project uses the in-memory **H2 Database** for development. The settings are in the `src/main/resources/application.yml` file:

```yaml
# Config database connection
spring:
  datasource:
    url: jdbc:h2:mem:devdb
    driver-class-name: org.h2.Driver
    username: sa
    password:

h2:
  console:
    enabled: true
    path: /h2-console

jpa:
  hibernate:
    ddl-auto: update # Create/update the DB schema automatically
  show-sql: true # Display SQL in the console
  properties:
    hibernate:
      format-sql: true # Format SQL for readability
      use-sql-comments: true # Add comments for readability
      dialect: org.hibernate.dialect.H2Dialect

```
Accessing the H2 Console
While the application is running, you can access the H2 console on your Browser:
http://localhost:8080/h2-console

Use the following credentials to log in:

JDBC URL: jdbc:h2:mem:devdb

User Name: sa

Password: (leave blank)

API Documentation (Swagger UI)
The API is automatically documented using SpringDoc OpenAPI. You can access the Swagger UI interactive interface to explore and test all endpoints:

http://localhost:8080/swagger-ui.html

API Information in Swagger
The API version is configured in application.yml and reflected in Swagger:

```yaml

# Config Open api version
springdoc:
  api:
  version: 1.0.0

```

CORS (Cross-Origin Resource Sharing)
The application is configured to handle CORS requests, allowing access from different origins (domains) to facilitate integration with front-end applications. The current configuration, optimized for development, allows all origins with credentials.

src/main/java/br/com/mascenadev/springstockmanager/config/WebConfig.java:

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry)   {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
```

Important Note for Production: For production environments, it is highly recommended to replace allowedOriginPatterns("*") with an explicit list of allowed origins (e.g., .allowedOrigins("https://yourapp.com")) to increase security.

Testing the API with Postman
A Postman collection with example requests for all API endpoints (including success and error scenarios) is available.

How to Import the Postman Collection
Download the spring-stock-manager.postman_collection.json file (you can generate it from your Swagger or use the one generated in our discussions).

In Postman, click File > Import.

Select the [spring-stock-manager.postman_collection.json](docs/postman/spring-stock-manager.postman_collection.json).

The collection will be imported with the title "Spring Stock Manager" and organized into folders for Suppliers, Products, Transactions, and Error Scenarios.

## 📜 *License*

*This project is licensed under the MIT License. See more details at:* [_LICENSE.md_](./LICENSE.md)

### Gilberto | Dev _2025_