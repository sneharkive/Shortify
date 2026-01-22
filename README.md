# Shortify - Secure URL Shortener

**Shortify** is a full-stack link shortening application designed for efficiency and security, built as a clone of the popular Bitly platform. It enables users to transform long, cumbersome URLs into concise, shareable links while providing detailed engagement analytics.

### Live At - [https://superb-rabanadas-2c2d83.netlify.app/]
---

## 🚀 Key Features

* **Secure Authentication**: JWT-based login and registration system using Spring Security.
* **URL Management**: Create, view, and delete shortened URLs associated with your user account.
* **Instant Redirection**: High-performance 302 redirection from short codes to original destination URLs.
* **Interactive Analytics**: Real-time tracking of click counts and time-series data for link engagement.
* **Responsive UI**: Modern dashboard built with React and TailwindCSS for seamless desktop and mobile use.
* **Data Visualization**: Integrated bar charts for monitoring traffic trends over specific date ranges.

---

## 🛠️ Tech Stack

### Backend

* **Java 17** with **Spring Boot 3.5.3**.
* **Spring Security & JWT**: For stateless authentication and role-based access control.
* **Spring Data JPA**: For database abstraction and management.
* **PostgreSQL**: Primary relational database for production (MySQL support also available).
* **Lombok**: To reduce boilerplate code.

### Frontend

* **React 19 (Vite)**: For a fast and modern development workflow.
* **TailwindCSS**: For utility-first styling and responsive design.
* **TanStack Query**: For efficient server-state management and caching.
* **Chart.js**: For interactive analytics visualization.
* **Framer Motion**: For smooth UI animations and transitions.

---

## ⚙️ Configuration & Environment

To run the project locally, you must set up the following environment variables in your `.env` or `application-secret.properties` files.

### Backend (`application.properties`)

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/shortify
spring.datasource.username=your_username
spring.datasource.password=your_password
jwt.secret=your_64_character_base64_secret_key
jwt.expiration=3600000
frontend.url=http://localhost:5173

```

### Frontend (`.env`)

```env
VITE_BACKEND_URL=http://localhost:8080
VITE_REACT_FRONT_END_URL=http://localhost:5173

```

---

## 📂 Project Structure

```text
├── frontend                # React + Vite application
│   ├── src/api             # Axios instance configuration
│   ├── src/components      # UI components (Dashboard, Charts, Auth)
│   ├── src/contextApi      # Global state for JWT tokens
│   └── src/hooks           # Custom TanStack Query hooks
└── shortify                # Spring Boot application
    ├── src/main/java       # Controllers, Services, Repositories, Models
    ├── src/main/resources  # application.properties
    └── Dockerfile          # Containerization for the backend

```

---

## 🚀 Getting Started

### Prerequisites

* JDK 17 or higher
* Node.js (v18+)
* Docker (Optional)

### Installation

1. **Clone the Repository**
```bash
git clone https://github.com/sneharkive/Shortify.git
cd Shortify

```


2. **Run the Backend**
```bash
cd shortify
./mvnw spring-boot:run

```


3. **Run the Frontend**
```bash
cd frontend
npm install
npm run dev

```



### Docker Deployment

You can build and run the backend using the provided Dockerfile:

```bash
docker build -t sneharkive/shortify:latest ./shortify
docker run -p 8080:8080 sneharkive/shortify:latest

```
