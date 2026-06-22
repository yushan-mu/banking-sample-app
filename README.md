# Banking Sample App

A simple banking transactions REST API built with Java and Spring Boot.

This application supports:

* Creating user accounts with an initial balance
* Transferring funds between accounts
* Retrieving transaction history for a given account
* In-memory data storage without a database

## Tech Stack

* Java
* Spring Boot
* Spring Web
* Maven

## How to Run

### Prerequisites

Make sure you have the following installed:

* Java 21 or later

### Run the Application

From the project root, run:

```bash
./mvnw spring-boot:run
```

On Windows, run:

```bash
mvnw.cmd spring-boot:run
```

By default, the application runs on:

```text
http://localhost:8080
```

## API Endpoints

### Create Account

Creates a new account with an initial balance.

```http
POST /api/account
```

Example request:

```json
{
  "balance": 100
}
```

Example response:

```json
{
  "id": 1,
  "balance": 100
}
```

### Transfer Funds

Transfers funds from one account to another.

```http
POST /api/transfer
```

Example request:

```json
{
  "fromAccountId": 1,
  "toAccountId": 2,
  "amount": 25
}
```

Example response:

```json
{
  "id": 1,
  "fromAccountId": 1,
  "toAccountId": 2,
  "amount": 25
}
```

### Get Transaction History

Retrieves all transactions involving a specific account.

```http
GET /api/accounts/{accountId}/transactions
```

Example request:

```http
GET /api/accounts/1/transactions
```

Example response:

```json
[
  {
    "id": 1,
    "fromAccountId": 1,
    "toAccountId": 2,
    "amount": 25
  }
]
```
