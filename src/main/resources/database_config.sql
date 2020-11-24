CREATE DATABASE spring_micro;
-- utworzenie użytkownika DB
CREATE USER 'spring_micro_user'@'localhost' identified by 'qwe123';
-- przypisanie uprawnień dla użytkownika
GRANT 
	CREATE, ALTER, DROP, SELECT, INSERT, UPDATE, DELETE, REFERENCES, INDEX
ON 
	spring_micro.*
TO 
	'spring_micro_user'@'localhost';