# Spring Stock Manager

[Português Brasileiro](https://github.com/Gilberto-Mascena/Spring-Stock-Manager/blob/main/README-pt_br.md) | [English](https://github.com/Gilberto-Mascena/Spring-Stock-Manager/blob/main/README.md) 

<!--[![Status de Manutenção](https://img.shields.io/badge/Maintained-Yes-brightgreen?style=for-the-badge)](https://github.com/Gilberto-Mascena/Spring-Stock-Manager)
[![GitHub Actions Status](https://img.shields.io/github/actions/workflow/status/Gilberto-Mascena/Spring-Stock-Manager/build.yml?style=for-the-badge)](https://github.com/Gilberto-Mascena/desafio-back-end-jr/actions)
[![Licença](https://img.shields.io/github/license/Gilberto-Mascena/Spring-Stock-Manager?style=for-the-badge)](https://github.com/Gilberto-Mascena/Spring-Stock-Manager/blob/main/LICENSE.md)
[![Estrelas no GitHub](https://img.shields.io/github/stars/Gilberto-Mascena/Spring-Stock-Manager?style=for-the-badge)](https://github.com/Gilberto-Mascena/Spring-Stock-Manager/stargazers)
[![Problemas no GitHub](https://img.shields.io/github/issues/Gilberto-Mascena/Spring-Stock-Manager?style=for-the-badge)](https://github.com/Gilberto-Mascena/Spring-Stock-Manager/issues)
[![Versão do Repositório](https://img.shields.io/github/v/release/Gilberto-Mascena/Spring-Stock-Manager?include_prereleases&style=for-the-badge)](https://github.com/Gilberto-Mascena/Spring-Stock-Manager/releases)
![Data de Lançamento](https://img.shields.io/github/release-date/Gilberto-Mascena/Spring-Stock-Manager?style=for-the-badge)
![Tamanho do Repositório](https://img.shields.io/github/repo-size/Gilberto-Mascena/Spring-Stock-Manager?style=for-the-badge)-->


---

## Descrição do Projeto

O **Spring Stock Manager** é uma API RESTful desenvolvida com Spring Boot 3.5.3, projetada para gerenciar o estoque de produtos, fornecedores e movimentações (entradas e saídas) de forma eficiente. O projeto segue os princípios de design de APIs REST e conta com validação de dados, tratamento global de exceções e documentação automática via Swagger UI (SpringDoc OpenAPI).

## Funcionalidades

* **Fornecedores**:
    * Cadastro, listagem, busca por ID, atualização e exclusão de fornecedores.
    * Validação de CNPJ único.
* **Produtos**:
    * Cadastro, listagem, busca por ID, atualização e exclusão de produtos.
    * Associação de produtos a fornecedores.
* **Movimentações de Estoque**:
    * Registro de entradas e saídas de produtos no estoque.
    * Atualização automática da quantidade em estoque do produto.
    * Verificação de estoque suficiente para saídas.
    * Geração de relatório de movimentações por produto.
    * Filtros de movimentação por período e tipo (se implementado nos services).
* **Tratamento de Erros**:
    * `GlobalExceptionHandler` para lidar com exceções de forma centralizada e retornar mensagens de erro padronizadas (e.g., 400 Bad Request, 404 Not Found, 409 Conflict).
* **Documentação Interativa**:
    * Swagger UI para explorar e testar todos os endpoints da API.
* **CORS**:
    * Configuração para permitir requisições de diferentes origens (essencial para integração com front-end).

---

## Tecnologias Utilizadas

* **Java 17**: Linguagem de programação.
* **Spring Boot 3.3.1**: Framework para desenvolvimento rápido de APIs.
* **Spring Data JPA**: Abstração para persistência de dados.
* **Hibernate**: Implementação da especificação JPA.
* **H2 Database (Memória)**: Banco de dados em memória para desenvolvimento e testes.
* **Maven**: Ferramenta de gerenciamento de dependências e construção do projeto.
* **SpringDoc OpenAPI (Swagger UI)**: Geração automática de documentação da API.
* **Jakarta Validation (Bean Validation)**: Para validação de dados em DTOs.
* **Postman**: Para testar os endpoints da API (coleção fornecida).

---

## Estrutura de Pastas do Projeto

Abaixo está a estrutura de diretórios do projeto, mostrando a organização das classes e recursos:

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

---

## Como Rodar o Projeto

### Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas:

* **Java Development Kit (JDK) 17 ou superior**
* **Maven 3.x**

### Passos para Execução

1.  **Clone o repositório:**
```bash
    git clone git@github.com:Gilberto-Mascena/Spring-Stock-Manager.git
    cd spring-stock-manager
```
2.  **Compile o projeto com Maven:**

```bash
    mvn clean install
```
3.  **Execute a aplicação Spring Boot:**

```bash
    mvn spring-boot:run
    

```
```
    A aplicação será iniciada na porta `8080` por padrão. Você verá algo como:
    Started SpringStockManagerApplication in X.XXX seconds (process running for Y.YYY)
```

---

## Configuração do Banco de Dados (H2)

O projeto utiliza o **H2 Database** em memória para desenvolvimento. As configurações estão no arquivo `src/main/resources/application.yml`:

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
      ddl-auto: update # Cria/atualiza o esquema do DB automaticamente
    show-sql: true     # Exibe SQL no console
    properties:
      hibernate:
        format-sql: true       # Formata SQL para legibilidade
        use-sql-comments: true # Adiciona comentários para legibilidade
        dialect: org.hibernate.dialect.H2Dialect  
        
``` 
Acesso ao Console H2
Enquanto a aplicação estiver rodando, você pode acessar o console do H2 em seu navegador:
http://localhost:8080/h2-console

Use as seguintes credenciais para login:

JDBC URL: jdbc:h2:mem:devdb

User Name: sa

Password: (deixe em branco)

Documentação da API (Swagger UI)
A API é automaticamente documentada utilizando SpringDoc OpenAPI. Você pode acessar a interface interativa do Swagger UI para explorar e testar todos os endpoints:

http://localhost:8080/swagger-ui.html

Informações da API no Swagger
A versão da API é configurada no application.yml e refletida no Swagger:

```yaml

# Config Open api version
springdoc:
  api:
    version: 1.0.0

```

CORS (Cross-Origin Resource Sharing)
A aplicação está configurada para lidar com requisições CORS, permitindo acesso de diferentes origens (domínios) para facilitar a integração com aplicações front-end. A configuração atual, otimizada para desenvolvimento, permite todas as origens com credenciais.


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
                .allowedOriginPatterns("*") // Permite qualquer origem e é compatível com allowCredentials
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true); // Necessário se houver credenciais (cookies, headers de auth)
    }
}
```
Nota Importante para Produção: Para ambientes de produção, é altamente recomendado substituir allowedOriginPatterns("*") por uma lista explícita das origens permitidas (ex: .allowedOrigins("https://seuapp.com")) para aumentar a segurança.

Testando a API com Postman
Uma coleção Postman com exemplos de requisições para todos os endpoints da API (incluindo cenários de sucesso e erro) está disponível.

Como Importar a Coleção Postman
Baixe o arquivo spring-stock-manager.postman_collection.json (você pode gerá-lo a partir do seu Swagger ou usar o que foi gerado nas nossas discussões).

No Postman, clique em File > Import.

Selecione o arquivo [spring-stock-manager.postman_collection.json](docs/postman/spring-stock-manager.postman_collection.json).

A coleção será importada com o título "Spring Stock Manager" e organizada em pastas para Fornecedores, Produtos, Movimentações e Cenários de Erro.

## 📜 *Licença*

*Este projeto é licenciado sob a Licença MIT. Veja mais detalhes em:* [_LICENSE.md_](./LICENSE.md)

### Gilberto | Dev _2025_