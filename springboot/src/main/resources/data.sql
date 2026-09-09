
INSERT INTO Estudiante (nombre, apellido, correo_institucional, active) VALUES ('Alejandro','Penaranda', 'apenaranada@icesi.edu.co', TRUE);
INSERT INTO Estudiante (nombre, apellido, correo_institucional, active) VALUES ('Juan','Ramirez','jRamirez@icesi.edu.co', TRUE);
INSERT INTO Estudiante (nombre, apellido, correo_institucional, active) VALUES ('Catalina','Marquez','cMarquez@icesi.edu.co', TRUE);


INSERT INTO Profesor (nombre, apellido, correo_institucional, departamento, especialidad, active) VALUES ('Domiciano','Rincon','dRincon@icesi.edu.co', 'Computación y sistemas inteligentes', 'Telematica', TRUE);
INSERT INTO Profesor (nombre, apellido, correo_institucional, departamento, especialidad, active) VALUES ('Alejandro','Munoz','aMunoz@icesi.edu.co', 'Computación y sistemas inteligentes', 'Arquitectura de Software',  TRUE);
INSERT INTO Profesor (nombre, apellido, correo_institucional, departamento, especialidad, active) VALUES ('Kevin','Rodriguez','kRodriguez@icesi.edu.co', 'Computación y sistemas inteligentes', 'Desarrollo de Software',  TRUE);

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


INSERT INTO Estudiante_Many (nombre, apellido, correo_institucional, active) VALUES ('Alejandro','Penaranda', 'apenaranada@icesi.edu.co', TRUE);
INSERT INTO Estudiante_Many (nombre, apellido, correo_institucional, active) VALUES ('Juan','Ramirez','jRamirez@icesi.edu.co', TRUE);
INSERT INTO Estudiante_Many (nombre, apellido, correo_institucional, active) VALUES ('Catalina','Marquez','cMarquez@icesi.edu.co', TRUE);


INSERT INTO Curso_Many (nombre, creditos, departamento, profesor_id) VALUES ('Computacion en internet 2', 3, 'Computación y sistemas inteligentes', 3);
INSERT INTO Curso_Many (nombre, creditos, departamento, profesor_id) VALUES ('Ingesoft 4', 3, 'Computación y sistemas inteligentes', 2);
INSERT INTO Curso_Many (nombre, creditos, departamento, profesor_id) VALUES ('Desarrollo de aplicaciones moviles', 3, 'Computación y sistemas inteligentes', 1);


INSERT INTO estudiante_curso_many (estudiante_id, curso_id) VALUES (1,1);
INSERT INTO estudiante_curso_many (estudiante_id, curso_id) VALUES (2,2);
INSERT INTO estudiante_curso_many (estudiante_id, curso_id) VALUES (3,3);