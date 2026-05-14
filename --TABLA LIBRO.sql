--TABLA LIBRO

--codlib  AUTOINCREMENTAL clave primaria
--ISBN    CHAR(13)
--titulo  VARCHAR(255)
--autor   VARCHAR(255)
--paginas SMALLINT
--argumento   TEXT

CREATE TABLE libro(
    codlib INT AUTO_INCREMENT PRIMARY KEY,
    isbn CHAR(13) NOT NULL UNIQUE,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) DEFAULT "anonimo",
    paginas SMALLINT NOT NULL CHECK(paginas > 0),
    argumento TEXT
);

INSERT INTO libro (isbn, titulo, autor, paginas, argumento)
VALUES ('1234567890123', 'Elantris', 'Brandon Sanderson', 785, 'BUUUUUUUUUUUU'),
       ('1234567890124', 'Mortadelo y filemón  misión por españa', 'Francisco Ibañez', 785, 'El mejor libro de España')


CREATE TABLE operador(
    codOperador INT AUTO_INCREMENT PRIMARY KEY,
    nombreOperador CHAR(20) NOT NULL,
    habilidad CHAR(30) NOT NULL,
);

INSERT INTO operador (codOperador, nombreOperador, habilidad)
VALUES ('1', 'Recluta', 'Más gadgets'),
       ('2', 'Sledge', 'Maza'),
       ('3', 'Thatcher', 'Cañon EMP')