
INSERT INTO regiones (id, nombre) VALUES (1,'Arica y Parinacota');
INSERT INTO regiones (id, nombre) VALUES (2,'Tarapacá');
INSERT INTO regiones (id, nombre) VALUES (3,'Antofagasta');
INSERT INTO regiones (id, nombre) VALUES (4,'Atacama');
INSERT INTO regiones (id, nombre) VALUES (5,'Coquimbo');
INSERT INTO regiones (id, nombre) VALUES (6,'Valparaiso');
INSERT INTO regiones (id, nombre) VALUES (7,'O Higgins');
INSERT INTO regiones (id, nombre) VALUES (8,'Maule');
INSERT INTO regiones (id, nombre) VALUES (9,'Ñuble');
INSERT INTO regiones (id, nombre) VALUES (10,'Bio-Bio');
INSERT INTO regiones (id, nombre) VALUES (11,'Araucanía');
INSERT INTO regiones (id, nombre) VALUES (12,'Ríos');
INSERT INTO regiones (id, nombre) VALUES (13,'Lagos');
INSERT INTO regiones (id, nombre) VALUES (14,'Aysen');
INSERT INTO regiones (id, nombre) VALUES (15,'Magallanes');
INSERT INTO regiones (id, nombre) VALUES (16,'Metropolitana');

INSERT INTO cliente (region_id,nombre,apellido,email,create_at) VALUES(1,'Adolph','Hitler','nazi@gmail.com','2020-01-01');
INSERT INTO cliente (region_id,nombre,apellido,email,create_at) VALUES(1,'Donald','Trump','tarado@gmail.com','2020-01-01');
INSERT INTO cliente (region_id,nombre,apellido,email,create_at) VALUES(1,'Sebastian','Pi�era','marepoto@gmail.com','2020-01-01');
INSERT INTO cliente (region_id,nombre,apellido,email,create_at) VALUES(1,'Benito','Mussolini','camote@gmail.com','2020-01-01');
INSERT INTO cliente (region_id,nombre,apellido,email,create_at) VALUES(1,'Joseph','Stalin','marioBros@gmail.com','2020-01-01');
INSERT INTO cliente (region_id,nombre,apellido,email,create_at) VALUES(1,'Augusto','Pinochet','genocidactm@gmail.com','2020-01-01');
INSERT INTO cliente (region_id,nombre,apellido,email,create_at) VALUES(1,'Ivan','Moreira','raspadoDeOlla@gmail.com','2020-01-01');
INSERT INTO cliente (region_id,nombre,apellido,email,create_at) VALUES(1,'Pablo','Longueira','corpesca@gmail.com','2020-01-01');
INSERT INTO cliente (region_id,nombre,apellido,email,create_at) VALUES(1,'Michelle','Bachellete','paso@gmail.com','2020-01-01');
INSERT INTO cliente (region_id,nombre,apellido,email,create_at) VALUES(1,'Ricardo','Lagos','Consecion@gmail.com','2020-01-01');
INSERT INTO cliente (region_id,nombre,apellido,email,create_at) VALUES(1,'Eduardo','Frei','narigonql@gmail.com','2020-01-01');
INSERT INTO cliente (region_id,nombre,apellido,email,create_at) VALUES(1,'Patricio','Alwyin','golpistaql@gmail.com','2020-01-01');
INSERT INTO cliente (region_id,nombre,apellido,email,create_at) VALUES(1,'Pablo','Escobar','harina@gmail.com','2020-01-01');
INSERT INTO cliente (region_id,nombre,apellido,email,create_at) VALUES(1,'Jose','Pi�era','mercedes@gmail.com','2020-01-01');
INSERT INTO cliente (region_id,nombre,apellido,email,create_at) VALUES(1,'Andres','Chadwick','chanchoctm@gmail.com','2020-01-01');
INSERT INTO cliente (region_id,nombre,apellido,email,create_at) VALUES(1,'Jaqueline','VanRyselbergue','Borrachaql@gmail.com','2020-01-01');
INSERT INTO cliente (region_id,nombre,apellido,email,create_at) VALUES(1,'Patricio','Melero','chupalumas@gmail.com','2020-01-01');
INSERT INTO cliente (region_id,nombre,apellido,email,create_at) VALUES(1,'Patricia','Hoffmann','estupidaculiaa@gmail.com','2020-01-01');

INSERT INTO usuarios (username,password,enabled,nombre,apellido,email) VALUES ('Cristobal','$2a$10$uxQ1rV7ggyGcOOPwxu38JezNS87/Dyi598IKIyM/3qf.mGhNo/XmO',1,'Cristobal','Burgos','Burgos@gmail.com');
INSERT INTO usuarios (username,password,enabled,nombre,apellido,email) VALUES ('admin','$2a$10$exr884aFRO.V1nW4bjbuVO4OCQSO2RXgHR4bYUELYnrWJ7GRGBFxq',1,'John','Cena','JC@gmail.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (2,2);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (2,1);

INSERT INTO productos (nombre,precio,create_at) VALUES ('Cerveza Lager','1500', NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Cerveza Stout','1700', NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Cerveza Amber','1500', NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Cerveza Bock','1800', NOW());
	
INSERT INTO facturas (descripcion,observacion,cliente_id,create_at) VALUES ('Factura HBH',null,1,NOW());
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (10,1,1);
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (5,1,2);
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (1,1,3);

INSERT INTO facturas (descripcion,observacion,cliente_id,create_at) VALUES ('Factura CCU','100 chelas de cada tipo',2,NOW());
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (100,2,1);
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (100,2,2);
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (100,2,3);
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (100,2,4);