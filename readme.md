# Transaction Management System

## Prerequisites

These are the software requirements.

### Backend

1. Java JDK 21
2. Spring CLI v3.5.7
3. IDE - IntelliJ or VS Code recommended

### Backend Dependencies (from pom.xml)

1. springboot-starter-web
2. spring-boot-devtools
3. spring-boot-starter-test

### Frontend

1. Node.js 22 
2. npm 10
3. Angular CLI 20
4. Any modern web browser (for development was used Firefox)

### Frontend Dependencies (from package.json)

1. @angular/common
2. @angular/compiler
3. @angular/core
4. @angular/forms
5. @angular/platform-browser
6. @angular/router
7. rxjs
8. tslib
9. zone.js

## Installation

Commands to install the project (for development was used zsh on macos).

Cloning the repository:
git clone <repository-url>

### Backend Installation

For Spring application were not used any external libraries so the project just needs to be ran.

### Frontend Installation

cd transaction_application/frontend/
npm install

## Configuration

.env file was not used due to the simplicity of the application so there is no need to configure anything.
Angular application will be available on the default http://localhost:4200 .
Spring application will be available on the default http://localhost:8080 .


## Running the application

Both Angular and Spring applications need to be ran separately.

### Starting Backend

Use the following commands:
cd transaction_application/backend/demo
./mvnw spring-boot:run

After these commands, application should be active on localhost:8080 which can be checked in logs in that terminal.

### Starting Frontend

Use the following commands:
cd transaction_application/frontend
npm start

Befora start, dependencies need to be installed, which was described in installation section.

After these commands, application should be active on localhost:4200 which can be checked in logs in that terminal.

## API Documentation

Here will be described both endpoints with their requests and responeses.

### GET base_url/transactions

Description: get all transactions stored in csv file.

Request:
```json
GET base_url/transactions
```
Response example (JSON):
```json
[
    {
        "transactionDate": "2025-03-01",
        "accountNumber": "7289-3445-1121",
        "accountHolderName": "Maria Johnson",
        "amount": 150.0,
        "status": "Settled"
    },
    {
        "transactionDate": "2025-03-02",
        "accountNumber": "1122-3456-7890",
        "accountHolderName": "John Smith",
        "amount": 75.5,
        "status": "Pending"
    }
]
```
### POST base_url/transactions

Description: add transaction to the database. Status is randomly assigned on the front. It is not a user input.

Request body (JSON):
```json
{
    "transactionDate": "2024-12-10",
    "accountNumber": "1234-1234-6789",
    "accountHolderName": "Nadja Pavlovic",
    "amount": 122.0,
    "status": "Failed"
}
```

Response (text, not JSON):
```json
Success
```


## Testing

Application can be tested through a web browser or with a tool like Postman.

In Postman, just point to the endpoints.

In browser, on the landing page, there is a button with text Add transaction and table which displays all transactions in the database. When Add transaction button is clicked, a modal with a form is shown. Whether the form fields are filled correctly, it will send to backend. Form is validated so if input is not correct it will show an error. 

