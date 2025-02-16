# Banking Application

## Overview
The **Banking Application** is a comprehensive financial management system built with **Spring Boot**. It provides essential banking functionalities, including account creation, transactions, and bank statement generation.

## Features
- **Core Banking Functionalities** – Supports account creation, credit, debit, money transfer, balance enquiry, and name enquiry.
- **Bank Statement Generation** – Allows users to retrieve transaction history within a specified date range.
- **User Authentication and Security** – Secure login mechanism using JWT-based authentication (planned feature).
- **Scalable Backend** – Built with Spring Boot and integrated with a robust database for efficient management of users, accounts, and transactions.

## Tech Stack
- **Backend**: Spring Boot, Java
- **Database**: MySQL 
- **API Documentation**: Swagger

## Setup & Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/premlalwani09/Banking_Application.git
   cd Banking_Application
   ```
2. Configure the database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/banking_application
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   ```
3. Build and run the application:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

## API Endpoints

### Transaction Controller
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/bankStatement` | GET | Retrieves bank statement for a given account within a date range |

### User Controller
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/user` | POST | Creates a new user account |
| `/api/user/login` | POST | Authenticates a user and returns login response |
| `/api/user/balanceEnquiry` | GET | Checks account balance for a given account number |
| `/api/user/nameEnquiry` | GET | Retrieves the name associated with an account number |
| `/api/user/credit` | POST | Credits money to an account |
| `/api/user/debit` | POST | Debits money from an account |
| `/api/user/transfer` | POST | Transfers money between accounts |

## How It Works
1. Users can create accounts and log in securely.
2. Transactions including deposits, withdrawals, and transfers are processed.
3. Users can check balance, perform name enquiries, and generate bank statements.

## Future Enhancements
- **Integration with JWT authentication** for enhanced security.
- **Email notifications** for transactions.
- **Multi-currency support** for international transfers.
