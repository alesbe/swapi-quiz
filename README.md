# 🌌 SWAPI Quiz

An interactive **Star Wars** data browser built with Angular and Spring Boot, styled with a custom dark theme inspired by the galaxy itself. Explore characters and planets from the Star Wars universe through a responsive, paginated, and filterable UI — all deployed with Docker.

> ⚡ Built for learning, performance, and clean architecture!

## ![people_page](https://i.imgur.com/E6htTzv.png)

## 🚀 Features

- 🧱 **Hexagonal architecture** in the backend (Spring Boot)
- 🔁 **Reactive search with debounce** for smooth filtering
- 📄 **Dynamic pagination and sorting** for people and planets
- 🎨 **Themed Star Wars UI** with glowing buttons and Orbitron font
- 🔁 **Back button** behavior mimicking navigation history
- 🌍 **CORS handled via NGINX proxy** for clean frontend-backend communication
- 📦 Fully containerized with **Docker Compose**

---

## 🛠️ Technologies Used

### Frontend

- ⚡ Angular 17
- 💅 SCSS (custom themed styles)
- 🧪 Jasmine + Karma for unit testing
- 🚀 NGINX for static file serving and API proxy

### Backend

- ☕ Spring Boot 3 (JDK 21)
- 🧪 JUnit 5 + Mockito for unit testing
- 🧱 Hexagonal (Ports & Adapters) architecture
- 🌍 CORS Configuration via `WebMvcConfigurer`

### DevOps

- 🐳 Docker & Docker Compose
- 🐘 Maven

---

## 🧩 Architecture Highlights

- ✅ Clean separation between domain, application, and infrastructure
- ✅ DTO mapping for REST controllers
- ✅ Pagination, sorting and searching integrated with Spring Data
- ✅ Environment-based `apiUrl` for frontend flexibility
- ✅ Angular standalone components + reactive forms
- ❌ Had to **bypass SSL verification** for SWAPI requests in the backend because their certificate expired in April 2025

---

## 📦 Installation with Docker Compose

1. **Clone the repo**:

```bash
git clone https://github.com/alesbe/swapi-quiz.git
cd swapi-quiz
```

2. **Run the app**:

```bash
docker compose up --build
```

3. **Access**:

- Frontend: http://localhost:6969

- Backend API (optional direct access): http://localhost:8080/api

## 🔧 Things I'd Improve If I Started Over

- 🧩 Create a reusable generic table component in Angular to avoid duplication across views

- 🪐 Avoid embedding full planet objects inside people — it would be better to store only the `homeworldName` for better performance and simpler DTOs. However, I chose to embed them here to showcase my skills in entity mapping and nested object handling.

- 🧼 Add loading skeletons and custom error pages

- 🌐 Add internationalization (i18n) support

- 🔐 Add basic authentication

## 👨‍💻 Author

**Álvaro Esparza Bellver**

GitHub: [@alesbe](https://github.com/alesbe)

## ⭐ If you like it...

Give the repo a ⭐ and feel free to fork it or reach out with improvements!

> May the code be with you 💻✨
