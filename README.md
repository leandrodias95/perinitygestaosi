📚 Perinity GestaSI - API de Gerenciamento de Pessoas e Tarefas
Esse é um projeto de API para gerenciar pessoas e tarefas. A API permite criar, atualizar, excluir e listar pessoas e tarefas, além de gerar relatórios simples de alocação de tarefas e horas trabalhadas.

🛠️ Tecnologias Utilizadas
Java 17: A linguagem de programação usada no desenvolvimento.
Spring Boot 3.3.2: Framework que facilita o desenvolvimento de aplicações Java.
Hibernate/JPA: Para mapeamento objeto-relacional (ORM) e manipulação do banco de dados.
PostgreSQL: Banco de dados relacional usado no projeto.
JUnit 5: Framework para testes unitários.
Mockito: Biblioteca para testes unitários com mocks.
Swagger/OpenAPI: Para documentação da API e geração automática de documentação interativa.
🚀 Como Rodar o Projeto Localmente
Pré-requisitos:

Instalar o Java 17.
Instalar o Maven.
Instalar o PostgreSQL e configurar o banco de dados (veja as instruções abaixo).
Configuração do Banco de Dados:

Crie um banco de dados chamado perinitygestaosi.
Configure o arquivo application.properties na pasta src/main/resources com suas credenciais de acesso ao banco de dados PostgreSQL:
properties
Copiar código
spring.datasource.url=jdbc:postgresql://localhost:5432/perinitygestaosi
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
Rodar a Aplicação:

Na raiz do projeto, execute o comando Maven para compilar e rodar a aplicação:
bash
Copiar código
mvn spring-boot:run
A aplicação estará rodando em http://localhost:8082.
📖 Como Usar a Documentação do Swagger
O projeto inclui a integração com Swagger para facilitar a visualização e interação com a API.

Acessando o Swagger:

Após iniciar a aplicação, abra seu navegador e acesse o seguinte link para ver a documentação interativa gerada automaticamente:
bash
Copiar código
http://localhost:8082/swagger-ui/index.html
O que você pode fazer com o Swagger:

Visualizar todas as rotas disponíveis da API.
Testar os endpoints diretamente no navegador (ex.: criar uma pessoa, listar tarefas, etc.).
Ver o formato dos dados que a API espera ou retorna.
📋 Principais Funcionalidades da API
Pessoas (/api/perinitigestaosi/person)
POST /insert - Adiciona uma nova pessoa.
PUT /{id} - Atualiza uma pessoa existente.
DELETE /{id} - Deleta uma pessoa pelo ID.
GET /pessoas - Lista todas as pessoas e o resumo de suas tarefas.
Tarefas (/api/perinitigestaosi/task)
POST /insert - Adiciona uma nova tarefa.
PUT /{id} - Atualiza uma tarefa existente.
DELETE /{id} - Deleta uma tarefa pelo ID.
GET /tarefas/pendentes - Lista as três tarefas mais antigas sem pessoa alocada.
🧪 Testes
Os testes unitários foram desenvolvidos utilizando JUnit 5 e Mockito. Eles garantem que as principais funcionalidades da aplicação funcionem conforme esperado.

Para rodar os testes, execute o seguinte comando:

bash
Copiar código
mvn test

Espero ter ajudado!!! 
