DROP TABLE IF EXISTS CITY_INFO;

CREATE TABLE CITY_INFO
(
    ID      INT AUTO_INCREMENT PRIMARY KEY,
    NAME    VARCHAR(255)  NOT NULL,
    COUNTRY VARCHAR(255)  NOT NULL,
    INFO    VARCHAR(4000) NOT NULL
);

CREATE INDEX CI_INDEX ON CITY_INFO (NAME, COUNTRY);

insert into CITY_INFO (NAME, COUNTRY, INFO)
values ('MINSK', 'BELARUS', 'shitty place'),
       ('BREST', 'BELARUS', 'more shitty place'),
       ('BREST', 'FRANCE', 'i dont know anything about, do you?'),
       ('BERLIN', 'GERMANY', 'less shitty place');