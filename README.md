# JavaFX Database Interface Application

## Overview
This is a JavaFX-based desktop application that demonstrates basic CRUD (Create, Read, Update, Delete) operations using a MySQL database. The application provides a graphical user interface for managing user records with personal information.

## Features
- Create new user records
- Read existing user data
- Update user information
- Delete user records
- User-friendly graphical interface
- MySQL database integration

## Technologies Used
- Java SDK 23
- JavaFX for GUI
- MySQL Database
- JDBC for database connectivity

## Prerequisites
- Java SDK 23 or higher
- MySQL Server
- MySQL Connector/J (JDBC driver)
- IDE with JavaFX support (e.g., IntelliJ IDEA, Eclipse)

## Setup
1. Clone the repository
2. Set up MySQL database
3. Configure database connection settings in `ConnDbOps.java`
4. Build and run the project

## Database Configuration
The application requires a MySQL database with the following configuration:
- Database name: `your_database_name`
- Table name: `users`
- Table columns:
  - id (INT, Primary Key, Auto Increment)
  - name (VARCHAR)
  - email (VARCHAR)
  - phone (VARCHAR)
  - address (VARCHAR)
  - password (VARCHAR)

## Project Structure
- `DB_Application.java` - Main application class
- `DB_GUI_Controller.java` - Main controller for the GUI
- `ConnDbOps.java` - Database operations class
- `Person.java` - Model class for user data
- FXML files for UI layouts

## Usage
1. Launch the application
2. Use the form to add new users
3. Select users from the table to update or delete their information
4. All changes are automatically saved to the database
