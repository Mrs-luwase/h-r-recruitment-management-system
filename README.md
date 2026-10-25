# h-r-recruitment-management-system

Mid-term project for the Web Technology course (INSY 8322): a Spring Boot REST API for HR recruitment management.

- **Student:** Leiss Uwase
- **Student ID:** 27064
- **Project name:** h-r-recruitment-management-system

## Technologies

- Java 25
- Spring Boot 4.1.2 (Spring Web, Spring Data JPA)
- Hibernate (JPA)
- PostgreSQL
- Maven (Maven Wrapper included)
- Postman (API testing)

## Domain model

Entities: `Department`, `InterviewRoom`, `Recruiter`, `Applicant`, `Interview` (with the `InterviewStatus` enum) and `OnboardingDocument`.

## Steps to run the API

1. **Install** JDK 25, PostgreSQL and (optionally) Postman.
2. **Clone** the repository:
   ```
   git clone https://github.com/YOUR_USERNAME/h-r-recruitment-management-system-backend.git
   cd h-r-recruitment-management-system-backend
   ```
3. **Create an empty PostgreSQL database** named `hr_recruitment_db` (for example in pgAdmin).
4. **Set your database login** in `src/main/resources/application.properties`:
   ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/hr_recruitment_db
   spring.datasource.username=postgres
   spring.datasource.password=YOUR_POSTGRES_PASSWORD
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```
5. **Start the application** from the project folder:
   ```
   ./mvnw spring-boot:run
   ```
   On first start Hibernate creates the tables automatically (`ddl-auto=update`).
6. The API runs at `http://localhost:8080`.

## Testing with Postman

Import `h-r-recruitment-management-system-midterm.postman_collection.json` (in the root of this repository). The collection has one request per requirement, named by its ID (A2, B1, ...). Set the collection variable `baseUrl` to `http://localhost:8080`.

## Endpoints implemented so far

| Entity | Base path | Endpoints |
|---|---|---|
| Department | `/api/departments` | `POST /save`, `GET /all`, `GET /{id}`, `PUT /update/{id}`, `DELETE /delete/{id}` |
| InterviewRoom | `/api/interview-rooms` | same five CRUD endpoints |
| Applicant | `/api/applicants` | same five CRUD endpoints, plus `GET /by-last-name?lastName=` (B1) |

More endpoints are added as the project progresses.
