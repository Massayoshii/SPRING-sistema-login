# 🔐 Sistema de Login com Spring Boot

Uma API REST desenvolvida com **Spring Boot** para gerenciamento de autenticação e autorização de usuários utilizando **Spring Security**, **JWT (JSON Web Token)** e **PostgreSQL**.

O projeto foi criado com o objetivo de praticar os principais conceitos de segurança em aplicações Java, como autenticação, criptografia de senhas, autorização por perfis e proteção de endpoints.

---

# 📌 Funcionalidades

- Cadastro de usuários
- Login utilizando e-mail e senha
- Criptografia de senhas com BCrypt
- Geração de Token JWT
- Validação automática do Token em requisições protegidas
- Controle de acesso por perfis (ADMIN e USER)
- Endpoints públicos e privados
- Tratamento de erros de autenticação e autorização

---

# 🚀 Tecnologias

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- PostgreSQL
- JWT (Java JWT)
- Maven
- Lombok
- Bean Validation

---

# 📁 Estrutura do Projeto



src
└── main
├── java
│ └── com.seuprojeto
│ ├── config
│ ├── controller
│ ├── dto
│ ├── entity
│ ├── exception
│ ├── repository
│ ├── security
│ ├── service
│ └── Application.java
│
└── resources
├── db
│ └── migration
└── application.properties


---

# 🔐 Autenticação

A autenticação é realizada utilizando **JWT (JSON Web Token)**.

Fluxo da autenticação:

1. O usuário realiza o cadastro.
2. A senha é criptografada com BCrypt.
3. O usuário faz login.
4. A API gera um Token JWT.
5. O cliente envia o Token no Header das próximas requisições.
6. O Spring Security valida o Token antes de permitir o acesso.

Exemplo do Header:

```http
Authorization: Bearer seu_token_jwt
👥 Perfis de Usuário

O sistema possui dois níveis de acesso:

USER
Visualizar recursos permitidos
Alterar seus próprios dados
ADMIN
Acesso completo à API
Gerenciar usuários
Acessar endpoints administrativos
📡 Endpoints
Autenticação
Método	Endpoint	Descrição
POST	/auth/register	Cadastro de usuário
POST	/auth/login	Login
Usuários
Método	Endpoint	Acesso
GET	/usuarios	ADMIN
GET	/usuarios/{id}	ADMIN
PUT	/usuarios/{id}	Usuário ou ADMIN
DELETE	/usuarios/{id}	ADMIN
🗄️ Banco de Dados

O projeto utiliza PostgreSQL.

Exemplo de configuração:

spring.datasource.url=jdbc:postgresql://localhost:5432/login_db
spring.datasource.username=postgres
spring.datasource.password=senha

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
▶️ Como Executar
Clone o projeto
git clone https://github.com/seu-usuario/sistema-login.git
Entre na pasta
cd sistema-login
Execute o projeto
./mvnw spring-boot:run

Ou execute a classe principal pelo IntelliJ IDEA ou Eclipse.

🧪 Testando a API

Você pode utilizar ferramentas como:

Postman
Insomnia
Cadastro
POST /auth/register
{
    "nome": "João Silva",
    "email": "joao@email.com",
    "senha": "123456",
    "role": "USER"
}
Login
POST /auth/login
{
    "email": "joao@email.com",
    "senha": "123456"
}

Resposta:

{
    "token": "eyJhbGciOiJIUzI1NiJ9..."
}
🔒 Segurança

O projeto utiliza:

Spring Security
BCrypt Password Encoder
JWT
SecurityFilterChain
OncePerRequestFilter
UserDetails
UserDetailsService
AuthenticationManager
📚 Objetivo

Este projeto foi desenvolvido para praticar os principais recursos do Spring Security e servir como base para aplicações maiores que necessitem de autenticação e autorização de usuários.

👨‍💻 Autor

Desenvolvido para fins de estudo utilizando Java, Spring Boot e Spring Security.