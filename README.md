<h1 align="center">
  IBM Bank
</h1>

Esse projeto back-end consiste em simular operações de um banco, ou seja, cadastra usuário, credita e debita valores da sua conta e também gerencia as transações a partir do extrato.

## Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI 3](https://springdoc.org/v2/#spring-webflux-support)
- [Mysql](https://dev.mysql.com/downloads/)

## Práticas adotadas

- SOLID, DRY, YAGNI, KISS
- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro
- Geração automática do Swagger com a OpenAPI 3

## Melhorias pendentes

- Utilizar o Spring Security para gerenciar autenticação e autorização
- Melhorar a cobertura de testes e também a sua escrita
- Melhorar as validações e seus retornos, atualmente tem muita informação no console que não precisa
- Incluir paginação no retorno na api de extrato
- Melhorar as informações incluidas na api de transação
- Incluir um diagrama com a visualização das classes e modelagem de dados

## Modelagem do Banco de Dados  

<img width="100%" height="100%" src="https://github.com/andresavasconcelos/ibm-bank-back-end/blob/main/readme/modelagem.png?raw=true" />
