--
-- Plik wygenerowany przez SQLiteStudio v3.2.1 dnia sob. mar 23 23:58:58 2019
--
-- U¿yte kodowanie tekstu: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Tabela: Notes
CREATE TABLE Notes (ID INTEGER NOT NULL ON CONFLICT ABORT PRIMARY KEY ASC AUTOINCREMENT, Title VARCHAR NOT NULL, Label VARCHAR, Data VARCHAR, TextAreaSize_X INTEGER NOT NULL, textAreaSize_Y INTEGER NOT NULL, Content VARCHAR);

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
