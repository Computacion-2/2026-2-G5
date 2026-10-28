# Memoria Técnica: Restricciones del Stack (`tech-stack.md`)

> **Propósito:** Definir las versiones e infraestructura inmutables del proyecto `com.compunet.springboot` para evitar que el agente de IA alucine librerías incompatibles o configuraciones obsoletas.

- **Lenguaje:** Java 21 LTS.
- **Framework Base:** Spring Boot `3.4.3` (gestionado con Maven Wrapper `./mvnw`).
- **Persistencia:** Spring Data JPA / Hibernate 6 con base de datos relacional en memoria H2 (`jdbc:h2:mem:academicodb`) e inicialización automática vía `src/main/resources/data.sql`.
- **Seguridad:** Spring Security 6 con arquitectura **Dual `SecurityFilterChain`**:
  - `@Order(1)` para `/api/**`: **100% Stateless** con tokens **JWT (JJWT `0.12.6`)** y `JwtAuthenticationFilter`.
  - `@Order(2)` para `/**`: **Stateful** con sesiones HTTP y plantillas SSR en Thymeleaf.
- **Mapeo y Productividad:**
  - **Lombok** (`@Data`, `@Getter`, `@Setter`, `@Builder`, `@RequiredArgsConstructor`).
  - **MapStruct `1.6.3`** (`@Mapper(componentModel = "spring")`) + `lombok-mapstruct-binding` para generación de código en compilación (`javac`).
- **Pruebas:** JUnit 5, Mockito y validación de contratos HTTP en Postman.
