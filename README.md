# Projeto CP02 - Gerenciamento de Pacientes

## Integrantes do Grupo

*Nicola Monte Cravo Garofalo - responsável pelo código fonte.*
*Igor Akira Bortolini Tateishi - responsável pelo código fonte.*
*Willyam Santos Sousa - responsável pelo documento.*

## Requisitos do Software
 
- Instalação do Java 17;
 
## Instruções de como rodar a aplicação
 
1. Clonar o projeto;
2. Baixar as dependências do Groovy;
3. Rodar o projeto;
4. Executar as requisições.

## Descrição:

Este projeto é uma aplicação Java desenvolvida com Spring Boot, utilizando JPA para persistência de dados, destinada ao gerenciamento de informações de pacientes, incluindo médicos, tratamentos, sintomas e feedbacks. A aplicação mantém registros dos relacionamentos entre pacientes e suas respectivas consultas, diagnósticos e avaliações, promovendo uma visão organizada e padronizada dos dados clínicos.

## Estrutura das Entidades

### 1. Paciente
- Representa o paciente, contendo informações pessoais como:
  - id: Identificador único do paciente.
  - nome: Nome do paciente.
  - email: Email válido do paciente.
  - telefone: Telefone do paciente.
  - dataNascimento: Data de nascimento do paciente.

### 2. Medico
- Representa o médico responsável pelo paciente.
  - id: Identificador único do médico.
  - paciente: Associação com o paciente.
  - nome: Nome do médico.
  - telefone: Telefone do médico (máx. 15 caracteres).
  - email: Email válido do médico.
  - crm: Número do CRM, com tamanho entre 5 e 15 caracteres.

### 3. Feedback
- Registra o feedback do paciente sobre seu tratamento.
  - id: Identificador único do feedback.
  - paciente: Referência ao paciente.
  - data: Data do feedback.
  - comentario: Comentário do paciente.
  - nota: Nota dada pelo paciente (de 1 a 10).

### 4. Sintoma
- Detalha os sintomas apresentados pelo paciente em um determinado momento.
  - id: Identificador único do sintoma.
  - paciente: Associação com o paciente.
  - data: Data de observação do sintoma.
  - descricao: Descrição do sintoma.
  - gravidade: Grau de gravidade do sintoma.

### 5. Tratamento
- Representa o tratamento realizado ou prescrito ao paciente.
  - id: Identificador único do tratamento.
  - paciente: Associação com o paciente.
  - descricao: Descrição do tratamento.
  - tipo: Tipo de tratamento.
  - data: Data de início do tratamento.

## Tecnologias Utilizadas

- *Java 17*
- *Spring Boot*
- *Jakarta Persistence API (JPA)*
- *Banco de Dados Relacional (H2)*

## Requsições
## Tratamento
- **GET /tratamento/{id}**: Obtém um tratamento específico pelo ID.
- **PUT /tratamento/{id}**: Atualiza as informações de um tratamento específico pelo ID.
- **DELETE /tratamento/{id}**: Exclui um tratamento específico pelo ID.
- **GET /tratamento**: Lista todos os tratamentos.
- **POST /tratamento**: Cria um novo tratamento.
- **GET /tratamento/paciente/{pacienteId}**: Obtém os tratamentos associados a um paciente específico pelo ID do paciente.

## Sintoma
- **GET /sintoma/{id}**: Obtém um sintoma específico pelo ID.
- **PUT /sintoma/{id}**: Atualiza as informações de um sintoma específico pelo ID.
- **DELETE /sintoma/{id}**: Exclui um sintoma específico pelo ID.
- **GET /sintoma**: Lista todos os sintomas.
- **POST /sintoma**: Cria um novo sintoma.

## Paciente
- **GET /paciente/{id}**: Obtém um paciente específico pelo ID.
- **PUT /paciente/{id}**: Atualiza as informações de um paciente específico pelo ID.
- **DELETE /paciente/{id}**: Exclui um paciente específico pelo ID.
- **GET /paciente**: Lista todos os pacientes.
- **POST /paciente**: Cria um novo paciente.

## Médico
- **POST /medico**: Cria um novo médico.
- **GET /medico/{id}**: Obtém um médico específico pelo ID.
- **GET /medico**: Lista todos os médicos com paginação.
- **PUT /medico/{id}**: Atualiza as informações de um médico específico pelo ID.
- **DELETE /medico/{id}**: Exclui um médico específico pelo ID.
- **GET /medico/medico/{pacienteId}**: Obtém os médicos associados a um paciente específico pelo ID do paciente.

## Feedback
- **POST /feedback**: Cria um novo feedback.
- **GET /feedback/{id}**: Obtém um feedback específico pelo ID.
- **GET /feedback**: Lista todos os feedbacks com paginação.
- **PUT /feedback/{id}**: Atualiza as informações de um feedback específico pelo ID.
- **DELETE /feedback/{id}**: Exclui um feedback específico pelo ID.
- **GET /feedback/paciente/{pacienteId}**: Obtém todos os feedbacks associados a um paciente específico pelo ID do paciente.
- **GET /feedback/data?data={data}**: Obtém feedbacks por data específica (formato ISO: YYYY-MM-DD).
- **GET /feedback/nota?nota={nota}**: Obtém feedbacks pela nota (nota de 1 a 10).
  
## Instalação e Configuração

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/JavaSprint02/.git
