# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a full-stack web application built with **Spring Boot 3** (backend) and **Vue 3** (frontend). The project follows a monorepo structure with separate `backend/` and `frontend/` directories.

## Architecture

### Backend Architecture

**Technology Stack:**
- Spring Boot 3.5.6 with Java 17
- RESTful API design with `/api/v1/` prefix
- Module-based organization under `dev.caobaoqi6040.backend.modules`

**Key Architectural Patterns:**

1. **Module Structure**: Features are organized by domain modules (e.g., `modules.user`)
   - Each module contains: `controller/`, `service/`, `entity/`, `domain/`, `exception/`
   - Controllers define REST endpoints
   - Services contain business logic
   - Domain layer uses MapStruct for DTO mapping (e.g., `UserStruct`)

2. **Exception Handling**: Centralized via `GlobalExceptionHandler`
   - `BusinessException` for business logic errors (returns 400)
   - Generic `Exception` handler for unexpected errors (returns 500)
   - Uses Spring's `ProblemDetail` RFC 7807 format

3. **Data Layer**: Currently uses in-memory data with DataFaker for mock data
   - `UserServiceImpl` implements `CommandLineRunner` to seed mock data on startup
   - No database configured (intentionally simple)

4. **CORS Configuration**: Configured in `application.properties` to allow frontend origins

**MapStruct Usage:**
- Interface-based mapping (e.g., `UserStruct`)
- Annotation processors configured in `pom.xml` with Lombok binding
- Generated implementations appear in `target/generated-sources/annotations/`

### Frontend Architecture

**Technology Stack:**
- Vue 3 with TypeScript
- Vite build tool
- Pinia for state management
- Vue Router for routing
- TailwindCSS 4 for styling
- Reka UI component library
- Axios for HTTP requests

**Structure:**
- `src/views/`: Page components (HomeView, AboutView)
- `src/components/ui/`: Reusable UI components
- `src/router/`: Vue Router configuration
- `src/stores/`: Pinia stores
- Path alias `@` points to `src/`

## Development Commands

### Backend

**Run the application:**
```bash
cd backend
mvn spring-boot:run
```

**Build:**
```bash
cd backend
mvn clean install
```

**Run tests:**
```bash
cd backend
mvn test
```

**Skip tests during build:**
```bash
cd backend
mvn clean install -Dmaven.test.skip
```

Backend runs on `http://localhost:8080`

### Frontend

**Install dependencies:**
```bash
cd frontend
pnpm install
```

**Development server:**
```bash
cd frontend
pnpm dev
```

**Build for production:**
```bash
cd frontend
pnpm build
```

**Type checking:**
```bash
cd frontend
pnpm type-check
```

**Build only (without type check):**
```bash
cd frontend
pnpm build-only
```

**Preview production build:**
```bash
cd frontend
pnpm preview
```

Frontend development server runs on `http://localhost:5173`

### Docker Deployment

**Build and deploy using Docker Compose:**
```bash
docker compose -f ./docker-compose.yaml up -d
```

**Services:**
- Backend: `http://localhost:8080`
- Frontend: `http://localhost:10010`

**Docker images:**
- Backend uses multi-stage build with Maven, includes CDS (Class Data Sharing) optimization
- Frontend uses pnpm with multi-stage build, served via nginx

## Important Conventions

### Backend

1. **Package naming**: All code under `dev.caobaoqi6040.backend`
2. **Module organization**: New features should follow the `modules/{feature-name}` pattern
3. **API versioning**: All REST endpoints start with `/api/v1/`
4. **DTO mapping**: Use MapStruct interfaces for entity-to-VO conversions
5. **Dependency injection**: Use Lombok's `@RequiredArgsConstructor` for constructor injection
6. **Exception handling**: Throw `BusinessException` for business logic errors; they're automatically handled

### Frontend

1. **Node version**: Requires Node.js ^20.19.0 || >=22.12.0
2. **Package manager**: Use `pnpm` (version 10.15.0 in Docker build)
3. **Import paths**: Use `@/` alias for imports from `src/`
4. **Styling**: TailwindCSS 4 with utility classes
5. **Components**: UI components follow shadcn/ui pattern with Reka UI

## Configuration Notes

- **CORS**: Backend allows origins `http://localhost`, `http://localhost:8080`, `http://localhost:5173`
- **Logging**: Root level set to `info` by default
- **Server port**: Backend runs on port 8080
- **Timezone**: Docker containers use `Asia/Shanghai` timezone
