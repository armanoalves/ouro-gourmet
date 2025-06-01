# Ouro Gourmet 🍽️

# Sumário  
[Introdução](#introdução)  
[Funcionalidades](#funcionalidades)  
[Como Rodar a Aplicação](#como-rodar-a-aplicação)  
[Documentação da API (Swagger)](#documentação-da-api-swagger)  
[Docker Compose](#docker-compose)

# Introdução

## Sobre o Projeto

Este projeto foi desenvolvido como parte do **Tech Challenge da FIAP** no curso de Pós-Tech, com foco na criação de uma API RESTful para a gestão de restaurantes, denominada **Ouro Gourmet**.  

O objetivo principal é fornecer uma aplicação robusta e escalável que permita o gerenciamento eficiente de usuários e dados relacionados ao ambiente gastronômico, com foco em qualidade de código, documentação e boas práticas de desenvolvimento.

# Funcionalidades

A API Ouro Gourmet oferece as seguintes funcionalidades:

- **Gerenciamento de Usuários**  
  - Criar, listar, atualizar e deletar usuários  
  - Validação de login  
  - Troca de senha  

- **Validação de Dados**  
  - Utiliza **Bean Validation** para garantir a integridade e consistência das informações fornecidas pelo usuário.

- **Documentação Automática com Swagger**  
  - Integração com o **Springdoc OpenAPI** para exibição interativa de todos os endpoints disponíveis da API.

- **Integração com Docker Compose**  
  - Inclui imagens do **MySQL**, **RabbitMQ** e **da própria aplicação**, permitindo a execução completa do sistema sem necessidade de abrir a IDE.

# Como Rodar a Aplicação

## Pré-requisitos

- Java 17 (caso deseje rodar fora do Docker)  
- Maven  
- Docker e Docker Compose

## Passos para Execução

### 1. Clonar o Projeto

Clone o repositório na branch `main`:
```bash
git clone -b main https://https://github.com/armanoalves/ouro-gourmet
```
### 2. Executar com Docker Compose

No terminal, dentro da pasta raiz do projeto, execute:

```bash
docker compose up -d
```

Este comando iniciará os seguintes containers:
- **MySQL**
- **RabbitMQ**
- **Aplicação Java (Ouro Gourmet)**

### 3. Acessar a API

Após subir os containers com Docker Compose, acesse o Swagger pela URL:

[http://localhost:8083/swagger-ui/index.html#/](http://localhost:8083/swagger-ui/index.html#/)

Se estiver rodando a aplicação localmente pela IDE, utilize a porta 8081:

[http://localhost:8081/swagger-ui/index.html#/](http://localhost:8081/swagger-ui/index.html#/)

# Documentação da API (Swagger)

A documentação da API é gerada automaticamente utilizando o **Springdoc OpenAPI**, acessível através da URL acima. Nela você poderá testar os endpoints diretamente pelo navegador, facilitando o desenvolvimento e testes.

# Docker Compose

O projeto utiliza Docker Compose com os seguintes serviços:

- **MySQL**: Banco de dados relacional para persistência dos dados.  
- **RabbitMQ**: Preparado para funcionalidades de mensageria futura.  
- **Ouro Gourmet App**: Imagem da aplicação já empacotada e pronta para rodar.

O `Dockerfile` está configurado com:

- **Java 17**  
- **Maven**  
