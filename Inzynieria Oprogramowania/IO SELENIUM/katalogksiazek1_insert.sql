SET SCHEMA APP;

INSERT INTO Tytul_ksiazki (tytul,autor_nazwisko,autor_imie,ISBN,wydawnictwo)
    VALUES ('Krzyzacy','Sienkiewicz','Henryk','1234567','PWN');

INSERT INTO Ksiazka (numer,id_tytul) VALUES (1,1);