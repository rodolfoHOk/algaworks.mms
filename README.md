# Mergulho Microsserviços Spring

> AlgaWorks

## Aula 01

### 1.1 Arquitetura de Microsserviços

- Arquitetura de software
- Arquitetura de solução
- Design de software
- Design de código

#### Arquitetura de software

- Requisitos funcionais
- Requisitos não funcionais: Estilos arquiteturais, Padrões, Modelagem e organização de dados, Tecnologias, Frameworks, Requisitos "ilities" (Availability, Reliabitity, Scalabitity, Agility, Security, Elasticity, Recoverability, Performance, Deployability, Learnability, Maintainability, Observability, Interoperability, Extensibility, Portability, Testability, Fault tolerance)
- Leis:
  - 1a - Tudo é/tem um trade-off (vantagens e desvantagens).
  - 2a - O porquê é mais importante que o como.
- Lei de Conway (Organização das equipes),

#### Arquitetura Monolítica

- Não é legado.
- Bem sucedidos.
- Não é problema.
- Vantagens:
  - Simplicidade no desenvolvimento.
  - Facilidade de build, teste e deploy.
  - Melhor eficiência operacional.
  - Suporte a transações ACID.
  - Facilidade de escalabilidade inicial.
  - Menor custo e complexidade para pequenas empresas.
  - Menos acoplamento no tempo de design.
- Desvantagens:
  - Monolithic hell.
  - Dificuldade de coordenação e baixa autonomia das equipes.
  - Complexidade para aplicações grandes.
  - Desenvolvimento lento e cilo de build prolongado para aplicações grandes.
  - Dificuldade no onboarding e baixa produtividade para novos desenvolvedores.
  - Dificuldade em identificar impactos de mudanças.
  - Deploy lentos e arriscados.
  - Alto acoplamento e risco de quebra de funcionalidades.
  - Aprisionamento tecnológico.
  - Falta de flexibilidade para escolher tecnologias específicas.
  - Banco de dados centralizado.

#### Arquitetura de Microsserviços

- Pequenas aplicações autônomas (serviços) que juntas compõem um sistema maior.
- Cada microsserviço tem seu escopo bem delimitado.
- Banco de dados descentralizado.
- Arquitetura distribuída com comunicação entre si vida rede.
- Cada microsserviço são autônomos e independentes.
- APIs com contratos bem definidos e estáveis.
- Evitar dependências diretas.
- Microsserviços são componentes pequenos e coesos. (Pequeno para ser fácil de entender e modificar. E grande o suficiente para ser útil e resolver um problema completo).
- Decomposição em microsserviços: Capacidade de negócio; Cuidado com extremos; Passível de reescrita; Manutenível por pequena equipe;
- Vantagens de serviços pequenos e coesos.
- Implementados de forma independente.
- Deploy totalmente independente. (deploy de um microsserviço não deve afetar outro)
- Flexibilidade tecnológica.
- Maior pontos de falhas.
- Microsserviços devem ser resilientes e tolerantes a falhas.
- Escaláveis de forma independentes.
- Testes são mais complexos.

#### Mentalidade das equipes para microsserviços

- Você constrói, você opera.
- Cultura DevOps.
- Cultura Ownership.
- Testes automatizados são essenciais
- Pirâmide de testes: Testes unitários, Testes de contrato, Testes de integração, Testes E2E
- Instância de serviço por container Pattern
- Orquestrador de containers
- Cloud computing

#### Padrões para microsserviços

- Cross-cutting concerns
- Microservices chassis pattern

### 1.2 DDD - Domain Driven Design

- Problemas de fragmentação excessiva e monólito distribuído.
- Decompose by subdomain pattern.
- Domínio de negócio. Domain ou Problem domain ou Business domain = Área de conhecimento.
- Domain Driven Design: design do software guiado pelo domínio do negócio, e não o contrário.
- Modelo de domínio: representação do domínio como solução de software.
- Espaço do problema (domínio) e espaço da solução (modelo do domínio).
- Design estratégico: Conecta problema (separar domínio em partes menores) e solução (design do software).
- Separação em subdomínios:
  - Uma opção os departamentos do negócio é uma separação prática do domínio.
  - Departamentos podem ser separados em mais de um subdomínio.
  - Termos diferentes para a mesma coisa pode indicar subdomínios diferentes.
  - Termos ambíguos, significados diferentes para um mesmo termo pode indicar subdomínios diferentes, neste caso necessitam diferenciação caso façam parte do mesmo subdomínio.
- Tipos de Subdomínio:
  - Core: núcleo principal, mais importante. Exige melhores implementações.
  - Supporting: menor importância. Investir menos esforço.
  - Generic: existentes em muitos domínios de negócio.
- Nem todo subdomain identificado vai ser implementado como código.
- Domain model: Abstração do problema de negócio.
- Significado das coisas muda conforme o contexto.
- Contexto é rei.
- Bounded context, contexto delimitado.
- Ubiquitous language, Linguagem ubíqua ou linguagem onipresente.

### 1.3 Preparando e Modelando a Arquitetura do Projeto

![DDD P1](/files/a1/01-project-ddd-p1.png)

![DDD P2](/files/a1/02-project-ddd-p2.png)

![Contexts -> Microservices](/files/a1/03-contexts-microservices.png)

![Transactions Flow](/files/a1/04-transactions-flow.png)

![Microservices](/files/a1/05-microsservices.png)

[Código Fonte](/algadelivery/microsservices/)

## Aula 02

### 2.1 Design tático e Domain model

Design tático: Building block

![Design tático](/files/a2/01-tactical-design.png)

Domain Model

![Domain Model](/files/a2/02-domain-model.png)

Entity

![Entity 1](/files/a2/03-entity.png)

![Entity 2](/files/a2/04-entity.png)

Value Object

![Value Object 1](/files/a2/05-value-object.png)

![Value Object 2](/files/a2/06-value-object.png)

Aggregate

![Aggregate](/files/a2/07-aggregate.png)

Domain driven design

![DDD](/files/a2/08-DDD.png)

Camadas

![Layers](/files/a2/09-Layers.png)

Inversão de dependências (interfaces)

![Inversão de dependências](/files/a2/10-dependencies-inversion.png)

UML

![UML 1](/files/a2/11-UML.png)

![UML 2](/files/a2/12-UML.png)

[Código Fonte](/algadelivery/microsservices/)

### 2.2 Infraestrutura de persistência com Spring Data JPA

[Código Fonte](/algadelivery/microsservices/)

### 2.3 Domain services e REST APIs

[Código Fonte](/algadelivery/microsservices/)

### 2.4 Spring RestClient e REST Assured

[Código Fonte](/algadelivery/microsservices/)

## Aula 03

### 3.1 Domain events, Event-driven architecture e Kafka

#### Domain event

Event publish

![Event publish](/files/a3/01-event-publish.png)

Event listener

![Event listener](/files/a3/02-event-listener.png)

#### Event-driven architecture

![Event-driven architecture](/files/a3/03-event-driven-architecture.png)

Event-driven patterns

![Event-driven patterns 1](/files/a3/04-event-driven-patterns.png)

![Event-driven patterns 2](/files/a3/05-event-driven-patterns.png)

![Event-driven patterns 3](/files/a3/06-event-driven-patterns.png)

#### Kafka

![Kafka 1](/files/a3/07-kafka.png)

![Kafka 2](/files/a3/08-kafka.png)

![Kafka 3](/files/a3/09-kafka.png)

![Kafka 4](/files/a3/10-kafka.png)

![Kafka 5](/files/a3/11-kafka.png)

![Kafka 6](/files/a3/12-kafka.png)

![Kafka 7](/files/a3/13-kafka.png)

[Código Fonte](/algadelivery/microsservices/)

### 3.2 Padrões de microsserviços

#### Api Gateway

![Api Gateway 1](/files/a3/14-api-gateway.png)

![Api Gateway 2](/files/a3/15-api-gateway.png)

![Api Gateway 3](/files/a3/16-api-gateway.png)

![Api Gateway 4](/files/a3/17-api-gateway.png)

![Api Gateway 5](/files/a3/18-api-gateway.png)

Service Registry Pattern

![Api Gateway 6](/files/a3/19-api-gateway.png)

Self Registration Pattern

![Api Gateway 7](/files/a3/20-api-gateway.png)

![Api Gateway 8](/files/a3/21-api-gateway.png)

Client-side Service Discovery Pattern

![Api Gateway 9](/files/a3/22-api-gateway.png)

Server-side Service Discovery Pattern

![Api Gateway 10](/files/a3/23-api-gateway.png)

[Código Fonte](/algadelivery/microsservices/)

### 3.3 Padrões de resiliência

## Aula 04
