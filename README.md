# h-r-recruitment-management-system

**Name:** Leiss Uwase
**Student ID:** 27064
**Project:** h-r-recruitment-management-system (Web Technology mid-term project)

## About

This is a Spring Boot REST API for managing recruitment: departments, interview rooms, recruiters, applicants and interviews. I am building it one entity at a time. So far Department, InterviewRoom and Applicant are done.

## Tools used

- Java (JDK 25 on my machine)
- Spring Boot 4.1.2
- Spring Data JPA with Hibernate
- PostgreSQL 17
- Maven
- Postman, to test the endpoints
## How to run it

1. Install a JDK and PostgreSQL.
2. Clone the project (replace YOUR_USERNAME with the GitHub username):
   `git clone https://github.com/YOUR_USERNAME/h-r-recruitment-management-system-backend.git`
3. In pgAdmin, create an empty database called `hr_recruitment_db`.
4. Open `src/main/resources/application.properties` and put your own PostgreSQL username and password.
5. In the project folder, run `./mvnw spring-boot:run`.
6. The app starts on http://localhost:8080. Hibernate creates the tables by itself the first time.
## What is done so far

- Department: save, get all, get by id, update, delete (`/api/departments`)
- InterviewRoom: the same five endpoints (`/api/interview-rooms`)
- Applicant: the same five endpoints, plus search by last name (`/api/applicants`)
## Testing

I tested the endpoints in Postman. The collection file is in the root of this repository: `h-r-recruitment-management-system-midterm.postman_collection.json`. In Postman, set the variable `baseUrl` to `http://localhost:8080`.
 