# Memoria Técnica: Restricciones del Stack (`tech-stack.md`)

> **Propósito:** Definir las versiones e infraestructura inmutables del proyecto `com.compunet.springboot` para evitar que el agente de IA alucine librerías incompatibles o configuraciones obsoletas.

- **Lenguaje:** Java 17 (`<java.version>17</java.version>`).
- **Framework Base:** Spring Boot `4.0.8` (gestionado con Maven Wrapper `./mvnw`, empaquetado `war`).
- **Persistencia:** Spring Data JPA / Hibernate:
  - Base de datos principal: PostgreSQL (`sistema_academico_own`, puerto 5432).
  - Base de datos de test / fallback: H2 (`jdbc:h2:mem:testdb`).
  - Inicialización automática de datos con `src/main/resources/data.sql` (`defer-datasource-initialization=true`).
- **Seguridad:** Spring Security 6 con arquitectura **Dual `SecurityFilterChain`**:
  - `@Order(1)` para `/api/**`: **100% Stateless** con tokens **JWT (JJWT `0.12.6`)** y `JwtAuthenticationFilter`.
  - `@Order(2)` para `/**`: **Stateful** con sesiones HTTP y plantillas SSR en Thymeleaf.
- **Mapeo y Productividad:**
  - **Lombok** (`@Data`, `@Getter`, `@Setter`, `@Builder`, `@RequiredArgsConstructor`).
  - **MapStruct `1.5.5.Final`** (`@Mapper(componentModel = "spring")`) + `lombok-mapstruct-binding` `0.2.0` para generación de código en compilación (`javac`).
- **Configuración Web:**
  - Puerto: `8081`
  - Context Path: `/springboot-app`
- **Pruebas:** JUnit 5, Mockito y pruebas de integración con H2.
