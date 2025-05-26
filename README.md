**Projeto de Tech Challenge - FIAP - Gestão de Restaurantes - Ouro Gourmet
**
Este projeto foi desenvolvido como parte do curso de Pós Tech FIAP.
O objetivo é criar uma API RESTful para uma aplicação de Gestão de Restaurantes denominada Ouro Gourmet


**Funcionalidades do Projeto
**A API desenvolvida possui as seguintes funcionalidades principais:

Gerenciamento de Usuários: inclui endpoints para criar, listar, atualizar, trocar de senha, validar login e deletar usuários.
Validação de Dados: validações automáticas utilizando o Bean Validation para garantir a integridade dos dados.
Documentação da API: documentação gerada automaticamente com Swagger para facilitar o consumo dos endpoints.
Docker Compose com MySQL e RabbitMQ

**O projeto foi configurado com as seguintes dependências, conforme o pom.xml:
**
**Java 17
**Maven.
Spring Boot como framework principal
spring-boot-starter-web para suporte à criação de APIs
spring-boot-starter-validation para validação de dados
springdoc-openapi-starter-webmvc-ui para a documentação da API (Swagger)
MySQL como banco de dados relacional
RabbitMQ para futura mensageria.

**Docker Compose**
Possui dependencias do MySQL e RabbitMQ, e o Dockerfile possui Maven e Java 17

**Para executar o projeto**
Efetue o pull do projeto na branch Main e realiza a importação do projeto com Maven. 
Na pasta principal do projeto execute o comando para subir o container docker compose up -d
Suba a aplicação e efetue os testes http://localhost:8083/swagger-ui/index.html#/
