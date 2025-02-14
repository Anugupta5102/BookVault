# Library Management System

## Overview
This is a **Library Management System** developed using **Java, JDBC, and MySQL**. The project enables users to manage book records, issue books, return books, and handle user accounts efficiently.

## Features
- **User Management**  
  - Register a new user
  - View all users
  - Update user details
  - Delete a user
  - User login authentication

- **Book Management**  
  - Add a new book
  - View available books
  - Update book details
  - Delete a book

- **Transaction Management**  
  - Issue a book to a user
  - Return a book
  - Check issued book records

## Technologies Used
- **Programming Language**: Java
- **Database**: MySQL
- **Database Connectivity**: JDBC

## Database Schema
### `accounts` Table
| Column Name | Data Type | Constraints |
|-------------|-----------|-------------|
| acc_no | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| full_name | VARCHAR(255) | NOT NULL |
| balance | DECIMAL(10,2) | NOT NULL |
| pin | CHAR(4) | NOT NULL |

### `books` Table
| Column Name | Data Type | Constraints |
|-------------|-----------|-------------|
| book_id | INT | PRIMARY KEY, AUTO_INCREMENT |
| title | VARCHAR(255) | NOT NULL |
| author | VARCHAR(255) | NOT NULL |
| quantity | INT | NOT NULL |

### `transactions` Table
| Column Name | Data Type | Constraints |
|-------------|-----------|-------------|
| transaction_id | INT | PRIMARY KEY, AUTO_INCREMENT |
| acc_no | BIGINT | FOREIGN KEY (accounts.acc_no) |
| book_id | INT | FOREIGN KEY (books.book_id) |
| issue_date | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP |
| return_date | TIMESTAMP | NULLABLE |

## Setup Instructions
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-repo/library-management-system.git
   ```
2. **Set up MySQL Database**:
   - Create a database `library_db`
   - Import the provided `schema.sql` file

3. **Configure Database Connection**:
   - Update `ConnectionClass.java` with your MySQL credentials.

4. **Run the Application**:
   - Compile the Java files and execute the main class.
   ```bash
   javac Main.java
   java Main
   ```

## Future Enhancements
- Implement GUI using Java Swing/JavaFX
- Add role-based authentication
- Generate reports (e.g., books issued per user)

## Contributors
- **Anushree Gupta** (Developer)

## License
This project is licensed under the MIT License.
