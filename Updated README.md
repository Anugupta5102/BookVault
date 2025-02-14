# Library Management System

## Overview
The **Library Management System** is a Java-based application that allows users to manage library books, authors, languages, and borrowed books. It supports user registration, book borrowing, and admin functionalities for adding and managing books.

## Technologies Used
- **Programming Language**: Java
- **Database**: MySQL
- **Libraries & Tools**: JDBC for database connectivity

## Database Schema
### Tables:
1. **users**
   - `user_id` (INT, PRIMARY KEY, AUTO_INCREMENT)
   - `name` (VARCHAR(255), NOT NULL)
   - `email` (VARCHAR(255), UNIQUE, NOT NULL)
   - `password` (VARCHAR(255), NOT NULL)
   - `is_admin` (BOOLEAN, DEFAULT FALSE)

2. **authors**
   - `author_id` (INT, PRIMARY KEY, AUTO_INCREMENT)
   - `name` (VARCHAR(255), NOT NULL, UNIQUE)

3. **languages**
   - `language_id` (INT, PRIMARY KEY, AUTO_INCREMENT)
   - `name` (VARCHAR(50), NOT NULL, UNIQUE)

4. **books**
   - `book_id` (INT, PRIMARY KEY, AUTO_INCREMENT)
   - `title` (VARCHAR(255), NOT NULL)
   - `author_id` (INT, FOREIGN KEY REFERENCES authors(author_id))
   - `language_id` (INT, FOREIGN KEY REFERENCES languages(language_id))
   - `genre` (VARCHAR(255), NOT NULL)
   - `rating` (FLOAT, DEFAULT 0.0)
   - `available` (BOOLEAN, DEFAULT TRUE)

5. **borrowed_books**
   - `borrow_id` (INT, PRIMARY KEY, AUTO_INCREMENT)
   - `user_id` (INT, FOREIGN KEY REFERENCES users(user_id))
   - `book_id` (INT, FOREIGN KEY REFERENCES books(book_id))
   - `borrow_date` (DATE, NOT NULL)
   - `due_date` (DATE, NOT NULL)

## Features
### Admin
- Login authentication
- Add books, authors, and languages
- View books, authors, and borrowed books

### User
- Register and login
- Borrow books
- View available books

## Setup Instructions
1. **Clone the repository**
   ```sh
   git clone https://github.com/your-repo/library-management.git
   cd library-management
   ```
2. **Database Setup**
   - Install MySQL and create a database `library_db`
   - Run the schema provided above
3. **Run the Project**
   - Compile and execute the Java files

## Future Enhancements
- Implement a GUI for better usability
- Add book return functionality
- Implement fine calculation for overdue books

## Contributors
- **Anushree Gupta** - Developer
