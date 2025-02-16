# Banking Application

## Overview
The **Banking Application** is a secure and feature-rich banking system built using **Spring Boot**. It provides core banking functionalities, including account management, transactions, and bank statements, with added security through **JWT Authentication** and **Email Notifications**.

## Features
- **Core Banking Functionalities** – Account creation, credit, debit, money transfer, balance enquiry, and name enquiry.
- **Bank Statement Generation** – Enables users to retrieve detailed transaction history and statements for their accounts.
- **User Authentication and Security** – Secured login mechanism with **JWT-based authentication** ensures safe access to services.
- **Email Notifications** – Sends email alerts for transactions and important account activities.
- **Scalable Backend** – Built with Spring Boot and integrated with a robust database for managing users, accounts, and transactions efficiently.

## Tech Stack
- **Backend**: Spring Boot, Spring Security (JWT Authentication)
- **Database**: MySQL
- **Messaging & Notifications**: Email Notifications

## Setup & Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/premlalwani09/Banking_Application.git
   cd Banking_Application
   ```
2. Configure database and email settings in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/banking_application
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   
   # JWT Secret Key
   jwt.secret=your_secret_key
   ```
3. Build and run the application:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

## API Endpoints
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/user` | POST | Creates a new user account |
| `/api/user/login` | POST | Authenticates user and returns JWT token |
| `/api/user/balanceEnquiry` | GET | Checks account balance |
| `/api/user/nameEnquiry` | GET | Retrieves account holder’s name |
| `/api/user/credit` | POST | Credits an amount to an account |
| `/api/user/debit` | POST | Debits an amount from an account |
| `/api/user/transfer` | POST | Transfers money between accounts |
| `/bankStatement` | GET | Generates and retrieves bank statement |

## How It Works
1. Users register and authenticate using **JWT-based authentication**.
2. Transactions trigger **email notifications** for security and account tracking.
3. The system securely manages and retrieves **bank statements** for users.

## Future Enhancements
- **Mobile App Integration** for a seamless banking experience.
- **Multi-Factor Authentication (MFA)** for enhanced security.
- **Loan & Investment Features** to expand financial services.
