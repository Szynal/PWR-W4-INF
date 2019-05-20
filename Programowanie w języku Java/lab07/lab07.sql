--
-- Plik wygenerowany przez SQLiteStudio v3.2.1 dnia pon. maj 20 08:10:49 2019
--
-- U¿yte kodowanie tekstu: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Tabela: ADMISSIONS
CREATE TABLE ADMISSIONS (
    ID_DOCTOR       INTEGER     REFERENCES DOCTORS (ID_DOCTOR) 
                                NOT NULL,
    ROOM            INTEGER     NOT NULL,
    DAY_OF_THE_WEEK INTEGER     NOT NULL
                                CHECK (DAY_OF_THE_WEEK >= 0 AND 
                                       DAY_OF_THE_WEEK <= 6),
    [BEGIN]         VARCHAR (8) NOT NULL,
    [END]           VARCHAR (8) NOT NULL
);


-- Tabela: DOCTORS
CREATE TABLE DOCTORS (
    ID_DOCTOR INTEGER      PRIMARY KEY AUTOINCREMENT
                           NOT NULL
                           UNIQUE,
    NAME      VARCHAR (20) NOT NULL,
    SURNAME   VARCHAR (35) NOT NULL
);

INSERT INTO DOCTORS (
                        ID_DOCTOR,
                        NAME,
                        SURNAME
                    )
                    VALUES (
                        1,
                        'DOKTOR',
                        'HOUSE '
                    );


-- Tabela: PATIENTS
CREATE TABLE PATIENTS (
    ID_PATIENT INTEGER      PRIMARY KEY AUTOINCREMENT
                            UNIQUE
                            NOT NULL,
    NAME       VARCHAR (20) NOT NULL,
    SURNAME    VARCHAR (35) NOT NULL
);

INSERT INTO PATIENTS (
                         ID_PATIENT,
                         NAME,
                         SURNAME
                     )
                     VALUES (
                         1,
                         'Pawel',
                         'Szynal'
                     );

INSERT INTO PATIENTS (
                         ID_PATIENT,
                         NAME,
                         SURNAME
                     )
                     VALUES (
                         2,
                         'Test',
                         'Kowalski'
                     );

INSERT INTO PATIENTS (
                         ID_PATIENT,
                         NAME,
                         SURNAME
                     )
                     VALUES (
                         5,
                         'Henryka',
                         '111'
                     );

INSERT INTO PATIENTS (
                         ID_PATIENT,
                         NAME,
                         SURNAME
                     )
                     VALUES (
                         7,
                         'AAA',
                         'TA_DAM'
                     );


-- Tabela: VISITS
CREATE TABLE VISITS (
    ID_VISIT       INTEGER       PRIMARY KEY AUTOINCREMENT
                                 NOT NULL
                                 UNIQUE,
    ID_PATIENT     INTEGER       REFERENCES PATIENTS (ID_PATIENT) 
                                 NOT NULL,
    ID_DOCTOR      INTEGER       REFERENCES DOCTORS (ID_DOCTOR) 
                                 NOT NULL,
    DATE           VARCHAR (10)  NOT NULL,
    TIME           VARCHAR (8),
    CONFIRMED      BOOLEAN       DEFAULT (FALSE),
    RECOMMENDATION VARCHAR (255) 
);

INSERT INTO VISITS (
                       ID_VISIT,
                       ID_PATIENT,
                       ID_DOCTOR,
                       DATE,
                       TIME,
                       CONFIRMED,
                       RECOMMENDATION
                   )
                   VALUES (
                       15,
                       1,
                       1,
                       '2019-05-07',
                       '08:00:00',
                       1,
                       NULL
                   );

INSERT INTO VISITS (
                       ID_VISIT,
                       ID_PATIENT,
                       ID_DOCTOR,
                       DATE,
                       TIME,
                       CONFIRMED,
                       RECOMMENDATION
                   )
                   VALUES (
                       17,
                       7,
                       1,
                       '2019-05-07',
                       '08:20:00',
                       1,
                       'abc'
                   );


COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
