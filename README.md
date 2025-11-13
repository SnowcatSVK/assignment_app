# AssignmentApp

AssignmentApp is a modern, modular Android application built with Kotlin, Jetpack Compose, Hilt, Room, Retrofit, and a robust Gradle setup. The project uses convention plugins for streamlined configuration and best practices.

## Features
- Multi-module architecture (core, domain, feature, design system, network)
- Jetpack Compose UI
- Room database integration
- Hilt for dependency injection
- Retrofit for networking
- Custom Gradle convention plugins
- Comprehensive unit and instrumentation tests (MockK, JUnit, Espresso)

## Project Structure
```
AssignmentApp/
├── app/                # Main Android app module
├── core/               # Core libraries (database, network, design system)
├── domain/             # Business logic and use cases
├── feature/            # Feature modules (e.g., scratchcard)
├── build-logic/        # Gradle convention plugins
├── gradle/             # Gradle configuration (libs.versions.toml, wrapper)
```

That's all for now!
