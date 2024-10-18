# Rest API Application

Este é um projeto de API REST desenvolvido com Spring Boot. A aplicação gerencia usuários e fornece endpoints para operações básicas como criação, leitura, atualização e exclusão (CRUD).

## Tecnologias Utilizadas

- Java 11 ou superior
- Spring Boot
- Spring Data JPA
- H2 Database (para testes e desenvolvimento)
- Maven
- JUnit5
- Mockito

## Estrutura do Projeto

- `com.lucas.rest_api`: Pacote principal da aplicação.
    - `domain`: Classes de domínio, como `User`.
    - `repositories`: Interfaces para acesso a dados, como `UserRepository`.
    - `services`: Lógica de negócios.
    - `controllers`: Controladores que expõem os endpoints da API.

## Configuração do Ambiente

### Pré-requisitos

- JDK 11 ou superior
- Maven


### Clonando o Repositório

```bash
git clone https://github.com/seu-usuario/rest_api.git
cd rest_api
```

### Construindo o Projeto

```bash
mvn clean install
```

### Executando a Aplicação

```bash
mvn spring-boot:run
```
A aplicação estará disponível em http://localhost:8080.

## Endpoints

- A aplicação fornece os seguintes endpoints:.
    - POST /users: Cria um novo usuário.
    -  GET /users/{id}: Recupera um usuário pelo ID.
    -  PUT /users/{id}: Atualiza um usuário existente.
    -  DELETE /users/{id}: Remove um usuário.

### Testes
Para executar os testes do projeto, utilize o comando:
```bash
mvn test
```

### Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request :)