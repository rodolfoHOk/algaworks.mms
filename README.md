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

### 1.3 Preparando e Modelando a Arquitetura do Projeto
