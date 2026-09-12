
INSERT INTO Profesor (nombre, apellido, correo_institucional, departamento, especialidad, active) VALUES ('Domiciano','Rincon','dRincon@icesi.edu.co', 'Computación y sistemas inteligentes', 'Telematica', TRUE);
INSERT INTO Profesor (nombre, apellido, correo_institucional, departamento, especialidad, active) VALUES ('Alejandro','Munoz','aMunoz@icesi.edu.co', 'Computación y sistemas inteligentes', 'Arquitectura de Software',  TRUE);
INSERT INTO Profesor (nombre, apellido, correo_institucional, departamento, especialidad, active) VALUES ('Kevin','Rodriguez','kRodriguez@icesi.edu.co', 'Computación y sistemas inteligentes', 'Desarrollo de Software',  TRUE);

INSERT INTO Estudiante (nombre, apellido, correo_institucional, active) VALUES ('Alejandro','Penaranda', 'apenaranada@icesi.edu.co', TRUE);
INSERT INTO Estudiante (nombre, apellido, correo_institucional, active) VALUES ('Juan','Ramirez','jRamirez@icesi.edu.co', TRUE);
INSERT INTO Estudiante (nombre, apellido, correo_institucional, active) VALUES ('Catalina','Marquez','cMarquez@icesi.edu.co', TRUE);

INSERT INTO Curso (nombre, creditos, departamento, profesor_id) VALUES ('Computacion en internet 2', 3, 'Computación y sistemas inteligentes', 3);
INSERT INTO Curso (nombre, creditos, departamento, profesor_id) VALUES ('Ingesoft 4', 3, 'Computación y sistemas inteligentes', 2);
INSERT INTO Curso (nombre, creditos, departamento, profesor_id) VALUES ('Desarrollo de aplicaciones moviles', 3, 'Computación y sistemas inteligentes', 1);

INSERT INTO estudiante_curso (estudiante_id, curso_id) VALUES (1,1);
INSERT INTO estudiante_curso (estudiante_id, curso_id) VALUES (1,2);
INSERT INTO estudiante_curso (estudiante_id, curso_id) VALUES (1,3);
INSERT INTO estudiante_curso (estudiante_id, curso_id) VALUES (2,1);
INSERT INTO estudiante_curso (estudiante_id, curso_id) VALUES (2,2);
INSERT INTO estudiante_curso (estudiante_id, curso_id) VALUES (2,3);
INSERT INTO estudiante_curso (estudiante_id, curso_id) VALUES (3,1);
INSERT INTO estudiante_curso (estudiante_id, curso_id) VALUES (3,2);
INSERT INTO estudiante_curso (estudiante_id, curso_id) VALUES (3,3);


-- Inserts base para Control de Acceso Basado en Roles (RBAC): Permiso, Rol y Usuario --
INSERT INTO permiso (nombre, descripcion) VALUES ('USER_CREATE', 'Permite crear nuevos usuarios en el sistema');
INSERT INTO permiso (nombre, descripcion) VALUES ('USER_READ', 'Permite consultar informacion de usuarios');
INSERT INTO permiso (nombre, descripcion) VALUES ('USER_UPDATE', 'Permite actualizar datos de usuarios');
INSERT INTO permiso (nombre, descripcion) VALUES ('USER_DELETE', 'Permite eliminar usuarios del sistema');
INSERT INTO permiso (nombre, descripcion) VALUES ('COURSE_READ', 'Permite consultar cursos academicos');

INSERT INTO rol (nombre, descripcion) VALUES ('ADMIN', 'Administrador con acceso total al sistema');
INSERT INTO rol (nombre, descripcion) VALUES ('DOCENTE', 'Docente con permisos de gestion academica');
INSERT INTO rol (nombre, descripcion) VALUES ('ESTUDIANTE', 'Estudiante con permisos de consulta');

INSERT INTO usuario (nombre, apellido, correo_institucional, password, active) VALUES ('Juan', 'Perez', 'juan.perez@icesi.edu.co', '$2a$10$hashedpassword123', TRUE);
INSERT INTO usuario (nombre, apellido, correo_institucional, password, active) VALUES ('Maria', 'Gomez', 'maria.gomez@icesi.edu.co', '$2a$10$hashedpassword456', TRUE);
INSERT INTO usuario (nombre, apellido, correo_institucional, password, active) VALUES ('Carlos', 'Rodriguez', 'carlos.rodriguez@icesi.edu.co', '$2a$10$hashedpassword789', TRUE);

-- Relacion N:M usuario_rol --
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (1, 1); -- Juan es ADMIN
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (1, 2); -- Juan tambien es DOCENTE (demostracion N:M)
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (2, 2); -- Maria es DOCENTE
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (3, 3); -- Carlos es ESTUDIANTE

-- Relacion N:M rol_permiso --
INSERT INTO rol_permiso (rol_id, permiso_id) VALUES (1, 1); -- ADMIN -> USER_CREATE
INSERT INTO rol_permiso (rol_id, permiso_id) VALUES (1, 2); -- ADMIN -> USER_READ
INSERT INTO rol_permiso (rol_id, permiso_id) VALUES (1, 3); -- ADMIN -> USER_UPDATE
INSERT INTO rol_permiso (rol_id, permiso_id) VALUES (1, 4); -- ADMIN -> USER_DELETE
INSERT INTO rol_permiso (rol_id, permiso_id) VALUES (1, 5); -- ADMIN -> COURSE_READ
INSERT INTO rol_permiso (rol_id, permiso_id) VALUES (2, 2); -- DOCENTE -> USER_READ
INSERT INTO rol_permiso (rol_id, permiso_id) VALUES (2, 5); -- DOCENTE -> COURSE_READ
INSERT INTO rol_permiso (rol_id, permiso_id) VALUES (3, 5); -- ESTUDIANTE -> COURSE_READ