🏀 Basketball Player Management
Basketball Player Management is a desktop application designed to manage a database of NBA players and teams. Its main goal is to provide a user-friendly interface to perform CRUD operations (Create, Read, Update, Delete), visualize player statistics, and manage user favorites dynamically.

⚙️ Technologies Used
The project is developed using the MVC (Model-View-Controller) architecture and DAO pattern for data access.

Primary Language: Java 21 ☕

Frontend/GUI: JavaFX (FXML) 🖥️

Database: MySQL (JDBC) 🗄️

Build Tool: Apache Maven 🛠️

🚀 Installation and Setup
Follow these steps to get a functional copy of the project running on your local machine.

Prerequisites
You need the following installed on your system:

Java Development Kit (JDK): Version 21 or higher.

MySQL Server: To host the basket_db database.

Apache Maven: For dependency management.

Git: To clone the repository.

Installation Steps
Clone the repository:

Bash

git clone https://github.com/tu-usuario/basketball-player-management.git
cd basketball-player-management
Database Configuration:

Open your database manager (Workbench, HeidiSQL, etc.).

Run the SQL script located in src/main/resources/basket_db.sql (or similar path) to create the tables (usuarios, equipos, jugadores, favoritos) and insert initial data.

Note: Ensure DatabaseConnection.java matches your MySQL username and password (default is usually root / root or empty).

Compile and Run: You can run the application directly through your IDE (IntelliJ IDEA recommended) or via Maven:

Bash

mvn clean javafx:run
💻 Usage
Login
To access the application, use the default test credentials provided in the database script:

Username: admin

Password: admin123

Features
Dashboard: View all players with calculated stats (Net Salary, Age).

Filters: Search by name, filter by height (Slider), or by Conference (East/West).

CRUD: Add new players, update existing ones, or delete them from the database.

Favorites: Select a player and click "Add to Favorites" to save them to your user session.
