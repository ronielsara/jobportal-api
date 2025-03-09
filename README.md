# Job Portal API - README

## Overview
The Job Portal API is a Spring Boot application that allows users to post jobs, apply for jobs, manage applications, and leave reviews.
The application enforces role-based access control (RBAC) to ensure only authorized users can access certain features.

## Features
- User authentication and authorization with role-based access control.
- Admin can view and delete users.
- Employers can post jobs, view applications, and update application statuses.
- Job seekers can view jobs, apply for jobs, and upload resumes.
- Employers can add reviews for jobs they posted.
- Pagination and filtering for listing jobs, applications, and reviews.

## Technologies Used
- **Backend:** Spring Boot, Spring Data JPA
- **Database:** MySQL
- **ORM:** Hibernate
- **API Testing:** Postman
- **Security:** Spring Security with JWT

## Prerequisites
Before setting up the project, ensure you have the following installed:
- **Java 17+**
- **Maven**
- **MySQL**
- **Postman** (for API testing)

## Setting Up the Project

### 1. Clone the Repository
```bash
git clone https://github.com/ronielsara/jobportal-api
cd jobportal-api
```

### 2. Configure Database
Create a MySQL database named `job_portal_db`. Update `application.properties` with your database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/job_portal_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

resume.upload-dir=C:\Users\<YourUsername>\Downloads
```

Update the `resume.upload-dir` path to a directory of your choice, such as your Downloads folder.

### 3. Build and Run the Project
```bash
mvn clean install
mvn spring-boot:run
```

### 4. API Authentication
- Register a user with `/api/auth/register`.
- Log in with `/api/auth/login` to receive a JWT token.
- Include the token in the `Authorization` header as `Bearer <token>` for authenticated requests.
- Employers, Job Seekers, and Admins have different access levels.

### 5. Testing with Postman
Import the provided **Postman collection** located in the `postman/` folder and use the following steps:
1. **Register a user** (`/api/auth/register`).
2. **Log in** (`/api/auth/login`) to get a token.
3. **Use the token** for each request, depending on the role (Employer, Job Seeker, or Admin).

#### Example API Calls:
- **Post a Job** (Employer Only): `POST /api/employer/post-job`
- **Apply for a Job** (Job Seeker Only): `POST /api/jobseeker/apply`
- **Update Application Status** (Employer Only): `PUT /api/employer/update-application`
- **Get All Jobs** (Public): `GET /api/jobs`
- **Add a Review** (Employer Only): `POST /api/review/add`

## Notes
- Ensure you pass the token in the `Authorization` header as `Bearer <token>`.
- Only authenticated users can access the API.
- Employers can only manage jobs they posted.
- Job seekers can only apply for jobs.
- Admins can manage all users.

## Author
- Developed by **Roniel Sara**

