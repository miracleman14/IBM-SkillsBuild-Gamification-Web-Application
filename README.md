# IBM SkillsBuild Gamification Web Application

## Table of Contents

- [About the Project](#about-the-project)
- [Installation and Running](#installation-and-running)
- [Technologies Used](#technologies-used)
- [Contact](#contact)

## About the Project

This project is a gamification web application of IBM's SkillsBuild, a free skills-based learning program.

For more information on IBM SkillsBuild, please visit [IBM SkillsBuild](https://skillsbuild.org/).

The website is not suitable for mobile viewing.

## Installation and Running

These instructions will get the web application running on your local machine for use and development.

For more detailed instructions on how to use the application, please see the user manual.

### Prerequisites

You will need:

- [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Gradle](https://gradle.org/)
- [MySQL](https://www.mysql.com/)
- Any IDE for Java (e.g., [IntelliJ IDEA](https://www.jetbrains.com/idea/), [Eclipse](https://www.eclipse.org/ide/), [VS Code](https://code.visualstudio.com/))

### Installation

1. **Clone the repository**

   ```console
   git clone https://campus.cs.le.ac.uk/gitlab/co2201-2024/group-01
   ```

2. **Navigate to the project directory**

   ```console
   cd .\group-01\ibm-skillsbuild-app\
   ```

3. **Install dependencies**

   ```console
   ./gradlew build
   ```

4. **Set up MySQL connection**

    To setup a MySQL connection, you will need a MySQL server installed and running on your machine, and some way to create a schema and user. For this guide, we will be using the MySQL Windows Installer (8.0.36) for the MySQL server and MySQL Workbench, available at [SQL Installer](https://dev.mysql.com/downloads/installer/).

    After installing your MySQL server and MySQL Workbench:

    - Open Workbench and create a new connection.

      ![SQL_Page1](../README_images/SQL_Page1.png "SQL workbench")

    - Name your connection (something recognisable) and set a user to connect with (here we use root user). Click ‘Test Connection’ and login with the user to make sure the connection is working, then click ‘OK’.

      ![SQL_Page2](../README_images/SQL_Page2.png "SQL connection")

      If the connection is not working, you can click ‘Configure Server Management...’ for more thorough testing.

    - You should see the following popup after clicking ‘OK’

      ![SQL_Page3](../README_images/SQL_Page3.png "SQL success")

    - Connect to your server by clicking on the connection and logging in.

      ![SQL_Page4](../README_images/SQL_Page4.png "SQL main")

    - Create a new schema with the following statement:

      - `CREATE SCHEMA schemaname;`
        where schemaname is your chosen name for the schema. Here we use `skillsbuilddb`.
      - Next, execute the statement, either by pressing Ctrl + Enter, or by clicking the lightning button above the statement. When the statement is executed, it will show at the bottom to say if it was successful or unsuccessful.

      ![SQL_Page5](../README_images/SQL_Page5.png "SQL schema")

    - Next, we create a new user for the schema. Click ‘Users and Privileges’ in the left-hand side ‘Administration’ menu.
      ![SQL_Page6](../README_images/SQL_Page6.png "SQL Admin")

    - From here, click ‘Add Account’ to create a new user.
      Give it a name, here we use `skillsbuilduser`, and a password.
      Then navigate to ‘Schema Privileges’.

      ![SQL_Page7](../README_images/SQL_Page7.png "SQL privs")

    - Here, click ‘Add Entry...’ to create a new privilege entry.

      ![SQL_Page8](../README_images/SQL_Page8.png "SQL entry")

    - Select your schema from the drop-down list under ‘Selected schema:’, and click ‘OK’.

      ![SQL_Page9](../README_images/SQL_Page9.png "SQL select_schema")

    - Now you have created an entry, click ‘Select “ALL”’ to grant your user schema privileges, then click ‘Apply’.

      ![SQL_Page10](../README_images/SQL_Page10.png "SQL apply")

    - Now you have successfully setup your MySQL connection and user!
      Open up the `application.properties` (located in `./ibm-skillsbuild-app/src/main/resources`) file in your preferred file editor.
      In this file, edit the shown 3 lines `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` with your schema name, user username and password.

      ![SQL_Page11](../README_images/SQL_Page11.png "applicationProperties")

### Running the Program

How to run the program on your local machine.

1. **Start the application**

   ```console
   ./gradlew bootRun
   ```

2. **Use the application**

   Access `http://localhost:8080` with your choice of web browser.

## Technologies Used

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Gradle](https://gradle.org/)
- [MySQL](https://www.mysql.com/)
- [Bootstrap](https://getbootstrap.com/)
- [jQuery](https://jquery.com/)
- [Chart.js](https://www.chartjs.org/)

## Contact

- **Ben Millington** - @bm308 - [bm308@student.le.ac.uk](mailto:bm308@student.le.ac.uk)
- **Yash Parmar** - @yp114 - [yp114@student.le.ac.uk](mailto:yp114@student.le.ac.uk)
- **Enzo Brown** - @eb433 - [eb433@student.le.ac.uk](mailto:eb433@student.le.ac.uk)
- **Miracle Ndu** - @mn303 - [mn303@student.le.ac.uk](mailto:mn303@student.le.ac.uk)
- **Jahnavi Muniraj** - @jm958 - [jm958@student.le.ac.uk](mailto:jm958@student.le.ac.uk)
- **Kevin Han** - @jh892 - [jh892@student.le.ac.uk](mailto:jh892@student.le.ac.uk)
- **Mikail Arioz** - @ma1002 - [ma1002@student.le.ac.uk](mailto:ma1002@student.le.ac.uk)
- **Leandro De Noronha** - @ldn5 - [ldn5@student.le.ac.uk](mailto:ldn5@student.le.ac.uk)
