# 🏍️ IoTTU - Sistema de Gerenciamento de Motos em Pátios

Este projeto é uma aplicação web full-stack para o gerenciamento e rastreamento de motocicletas em pátios. A solução, construída com Spring Boot, integra o back-end (API e regras de negócio) com o front-end renderizado via Thymeleaf, permitindo o monitoramento e controle completo das motos.

---

## 🚀 Tecnologias Utilizadas

* **Java 17**: Linguagem de programação principal.
* **Spring Boot**: Framework para a criação da aplicação.
* **Spring MVC**: Para a construção dos controllers e endpoints da aplicação.
* **Spring Data JPA**: Para a persistência de dados e comunicação com o banco de dados.
* **Thymeleaf**: Motor de templates para a renderização das páginas HTML no lado do servidor.
* **Maven/Gradle**: Gerenciador de dependências e build do projeto.
* **Banco de Dados**: PostgreSQL via docker compose.

## ✨ Funcionalidades Principais

O sistema oferece as seguintes funcionalidades no módulo de Motocicletas:

* **Painel visual das motos no pátio**: Visualização das motocicletas dentro do pátio .
* **Listagem de Motos**: Visualização de todas as motocicletas cadastradas no sistema.
* **Cadastro de Moto (CRUD)**:
    * Criar novas motocicletas, preenchendo informações como modelo, placa, chassi, etc.
    * Editar os dados de motocicletas existentes.
    * Excluir motocicletas do sistema.
* **Associação de Tags RFID**:
    * Vincular uma tag RFID disponível a uma motocicleta durante o cadastro ou edição.
    * O sistema valida se uma tag já está em uso, garantindo que cada tag seja associada a apenas uma moto.
* **Gerenciamento de Pátios e Status**:
    * Associar cada motocicleta a um pátio específico.
    * Definir o status atual da motocicleta (ex: "Disponível", "Em Manutenção").

---

## ✨ Beneficios para a MOTTU


TODO


## 📁 Estrutura do Projeto

O projeto está organizado nos seguintes pacotes principais, representando as entidades do sistema:

br.com.fiap.iottu
├── motorcycle       # Entidade principal, controllers e serviços de Moto
├── yard             # Gerenciamento dos Pátios
├── tag              # Gerenciamento das Tags RFID
├── motorcyclestatus # Status possíveis para uma Moto
├── user             # Gerenciamento de Usuários
├── auth             # Lógica de autenticação
├── config           # Configurações gerais do Spring
└── ...

---

## ⚙️ Como Executar o Projeto

1. **Clone o repositório**
   ```bash
   git clone https://github.com/caioliang/CHALLENGE-Java-IoTTU.git
   cd CHALLENGE-Java-IoTTU
   ```

2. **Compile e execute**
   ```bash
   ./gradlew bootRun
   ```

5.  A aplicação estará disponível em **`http://localhost:8080`**.


## 👨‍💻 Autores

Desenvolvido por [Allan Brito](https://github.com/Allanbm100), [Caio Liang](https://github.com/caioliang) e [Levi Magni](https://github.com/levmn) - Projeto acadêmico Mottu - FIAP - 2025
