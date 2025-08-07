# 💳 PicPay Simplificado - Desafio Técnico
Este projeto foi desenvolvido como parte de um desafio técnico inspirado em um processo seletivo do PicPay.
A aplicação simula uma plataforma simplificada de pagamentos, permitindo depósitos e transferências entre usuários, com regras específicas para diferentes tipos de conta.

---

## 🚀 Tecnologias Utilizadas

- ✅ **Java 17**
- ✅ **Spring Boot 3**
- ✅ **Spring Web**
- ✅ **Spring Data JPA**
- ✅ **PostgreSQL**
- ✅ **Lombok**
- ✅ **Docker Compose**
- ✅ **RestTemplate**
- ✅ **Maven**

---

## 🧠 Funcionalidades

- ✅ Cadastro de usuários (tipo comum ou lojista) com validação de CPF/CNPJ e e-mail únicos.
- ✅ Transferências entre usuários e de usuários para lojistas.
- ✅ Validação de saldo antes da transferência.
- ✅ Consulta a serviço externo autorizador antes da finalização.
- ✅ Operação transacional (em caso de erro, o dinheiro retorna para a conta do pagador).
- ✅ Envio de notificação para o recebedor (via serviço externo simulado).
- ✅ Tratamento global de exceções via GlobalExceptionHandler.

---

## 📜 Regras de Negócio

- Cadastro de usuários: Nome completo, CPF/CNPJ, e-mail e senha.
- CPF/CNPJ e e-mail devem ser únicos no sistema.
- Usuários podem enviar e receber transferências.
- Lojistas apenas recebem transferências. 

**Antes de transferir:**
- Verificar saldo.

- Consultar serviço autorizador externo: GET https://util.devi.tools/api/v2/authorize
- Transferência deve ser transacional: qualquer erro reverte a operação.
- Ao receber, o beneficiário recebe uma notificação simulada: POST https://util.devi.tools/api/v1/notify

**Serviço RESTful.**

---

## 🔗 Endpoints Principais

```bash

POST /transfer
Content-Type: application/json

{
  "value": 100.0,
  "payer": 4,
  "payee": 15
}

```

## 🐳 Como Rodar com Docker Compose
- O projeto já inclui um docker-compose.yml para subir:
- API do PicPay Simplificado
- Banco PostgreSQL
- Interface web do pgAdmin para gerenciamento

```bash

# Clone o repositório
git clone https://github.com/seu-usuario/picpay-simplificado.git
cd picpay-simplificado

# Suba os containers
docker compose up --build

```

- API: http://localhost:8080
- pgAdmin: http://localhost:5050
- Login e senha definidos no docker-compose.yml

## ⚙️ Configuração do Banco
A aplicação se conecta automaticamente ao PostgreSQL configurado no docker-compose.yml.
As credenciais padrão (alteráveis) são:

```bash

spring.datasource.url=jdbc:postgresql://postgres:5432/picpay
spring.datasource.username=postgres
spring.datasource.password=1234


```
## 🛡️ Tratamento Global de Erros
Foi implementada a classe **GlobalExceptionHandler** para capturar e padronizar respostas de erros da API, melhorando a clareza e reduzindo duplicação de código.

## 📌 Considerações Finais
Este projeto demonstra:
- Boas práticas com Java + Spring Boot.
- Organização em camadas (controller, business, infrastructure, config).
- Uso de containers Docker para facilitar a execução e integração com banco.
- Integração com serviços externos via RestTemplate.
- Tratamento global de exceções.
- Aderência às regras de negócio propostas no desafio.

### 👤 Autor
**Diego Lopes do Nascimento**

[LinkedIn](https://www.linkedin.com/in/diego-nascimento-b33311221/)