# **CoderHack User Leaderboard API**

### Overview

This application provides a RESTful API service for managing a leaderboard for a coding platform. It integrates with MongoDB to persist user data and their scores, facilitating CRUD operations for user registrations and score updates.

### Features

•	CRUD operations for user registrations (User ID, Username)

•	Updating user scores with badge awards based on score ranges

•	Sorting users based on their scores in ascending order

•	Exception handling for user not found, validation errors, and general exceptions

### Technologies Used

•	Java

•	Spring Boot

•	MongoDB

•	Gradle

•	Lombok

### Getting Started

#### Prerequisites

•	JDK 11 or higher

•	MongoDB installed and running locally (default port 27017)

•	Postman (optional) for testing API endpoints

#### Running the Application

1.Clone the repository:

    git clone https://github.com/your/repository.git
    cd leaderboard-api

2.Build the application using Gradle:

    ./gradlew build

3.Run the Application:

    java -jar build/libs/leaderboard-api-0.0.1-SNAPSHOT.jar

4.The application will start on: 

    'http://localhost:8081'.

### API Endpoints

•	POST '/users': Create a new user with User ID and Username.

{

    "userName": "Rohit,
    "userId": 
}

•	PUT '/users/{userId}': Update score for a user with User ID

{
    
    "score": 47 
}

•	GET '/users': Fetch all users in descending order of score.

•	GET '/users/{userId}': Fetch a user with User ID.

•	DELETE '/users/{userId}':  Delete user with User ID from database.

### Postman Collection

You can find the Postman collection for testing the API here :

https://www.postman.com/prtakhar2325/workspace/prakhar-s-workspace/collection/33649930-1f9028e4-b8cc-4ff6-a169-92a696bcd420?action=share&creator=33649930

### Error Handling

•	404 Not Found: User not found for the given User ID.

•	400 Bad Request: Validation errors (e.g., score out of range) or malformed requests.

•   409 Conflict: User with given User ID already exists.

### Configuration

•	MongoDB connection settings are configured in application.properties.
•	Gradle dependencies are managed in build.gradle.

### License

This project is licensed under the MIT License - see the LICENSE file for details.

Feel free to adjust the sections and details according to your specific project setup and requirements. This README.md provides a starting point to document your application's functionality, setup instructions, and API usage.
