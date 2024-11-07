# Projeto CP01 - Gerenciamento de Pacientes

## Integrantes do Grupo

*Nicola Monte Cravo Garofalo* - responsável pelo código fonte.
*Igor Akira Bortolini Tateishi* - responsável pelo código fonte.
*Willyam Santos Sousa* - responsável pelo documento

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
- *Banco de Dados Relacional (configuração do banco de dados depende do ambiente do projeto)*

## Instalação e Configuração

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/JavaSprint02/.git
