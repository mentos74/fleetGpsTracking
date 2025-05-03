
Fleet GPS Tracking API

Description:
This API is used for tracking vehicles via GPS, storing GPS logs, and providing the vehicle's location history based on time. The API provides several endpoints for authentication, saving GPS logs, retrieving the last vehicle location, and viewing vehicle location history.

Prerequisites:
- Java 17 or higher
- Maven for dependency management
- PostgreSQL (or another database that is configured)
- Spring Boot as the main framework

Setup:

1. Project Setup
1. Clone this repository to your local folder:
   git clone https://github.com/mentos74/fleetGpsTracking.git
2. Navigate to the project folder:
   cd repo-name
3. Make sure you are using Java 17. You can check the Java version by running:
   java -version
   If Java 17 is not installed, you can download and install it from Oracle's official site (https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) or use OpenJDK.

2. Install Dependencies
1. Maven is required to run this project. Make sure Maven is installed. If not, follow the instructions on Maven Installation Guide (https://maven.apache.org/install.html).
2. Run the following command to download all the required dependencies:
   mvn clean install

3. Database Configuration
1. Set up PostgreSQL or another compatible database with Spring Boot.
2. Update the database connection configuration in the application.properties file:
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update

4. Run the Application
To run the Spring Boot application, use the following command:
   mvn spring-boot:run

5. Testing
Use Postman to test the provided endpoints. Make sure you obtain the authentication token (Bearer Token) before accessing endpoints that require authentication.

Assumptions:
1. Date Format: The API uses the date format yyyy-MM-dd'T'HH:mm:ss for the from and to parameters.
2. Authentication: Endpoints that require authentication use a simple Bearer token obtained from the /auth/login endpoint.
3. Pagination: Some endpoints that return large sets of data (such as vehicle location history) use pagination with page and size parameters.

Decisions Made:
1. Spring Boot & Java 17 were chosen due to their ease of integration, dependency management, and comprehensive features.
2. PostgreSQL is used as the database for its stability and support for large-scale operations.
3. This API does not implement advanced authentication like OAuth2, using a simple Bearer token for authentication.

Endpoints:

1. Auth Login
   - POST /auth/login
   - To obtain an authentication token. Send username and password in the request body.

2. Save GPS Log
   - POST /api/gps
   - Saves a GPS log for a vehicle. Send vehicle_id, latitude, longitude, speed, and timestamp in the body.

3. Last Vehicle Location
   - GET /api/vehicles/{id}/last-location
   - Retrieves the last location of a vehicle by its ID.

4. Vehicle Location History
   - GET /api/vehicles/{id}/history?from={from}&to={to}&page={page}&size={size}
   - Retrieves the location history of a vehicle within a specific date range with pagination.
