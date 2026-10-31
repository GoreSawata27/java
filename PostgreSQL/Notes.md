```sql

CREATE DATABASE test;

\c test

CREATE TABLE person (
id BIGINT,
first_name VARCHAR(50),
last_name VARCHAR(50),
gender VARCHAR(10),
date_of_birth DATE,
email VARCHAR(150)
);

CREATE TABLE person (
id BIGSERIAL NOT NULL PRIMARY KEY,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
email VARCHAR(150) NOT NULL,
gender VARCHAR(7) NOT NULL,
date_of_birth DATE NOT NULL,
country_of_birth VARCHAR(50)
);

\d

\d person

INSERT INTO person
    (first_name, last_name, email, gender, date_of_birth, country_of_birth)
VALUES
    ('Sawata', 'Gore','sawata.gore27@gmail.com', 'Male', '2000-07-30', 'India');


SELECT * FROM person

SELECT first_name, gender
FROM person

SELECT first_name, gender, date_of_birth
FROM person
ORDER BY date_of_birth;

SELECT first_name, gender, date_of_birth
FROM person
ORDER BY date_of_birth DESC;

SELECT DISTINCT gender FROM person;

SELECT *
FROM person
WHERE gender = 'Male';

SELECT *
FROM person
WHERE gender = 'Male' AND first_name = 'Sawata';

SELECT *
FROM person
WHERE gender = 'Male' AND (first_name = 'Sawata' OR last_name='Wilson' );

SELECT *
FROM person
WHERE date_of_birth BETWEEN DATE '1995-01-01' AND DATE '2005-12-31';


SELECT 1 <= 1;

SELECT 1 <> 1;

SELECT * FROM person LIMIT 5;

SELECT * FROM person OFFSET 5 LIMIT 5;

SELECT *
FROM person OFFSET 5 FETCH FIRST 5 ROW ONLY;

SELECT *
FROM person
WHERE country_of_birth IN ('India', 'Japan');


SELECT *
FROM person
WHERE email LIKE '%.com';

SELECT *
FROM person
WHERE first_name LIKE 'Aa%';

SELECT *
FROM person
WHERE first_name LIKE 'Aa%';

SELECT *
FROM person
WHERE first_name LIKE '______.%';

SELECT *
FROM person
WHERE country_of_birth ILIKE 'i%';


SELECT country_of_birth, COUNT(*)
from person
GROUP BY country_of_birth;


SELECT country_of_birth, COUNT(*)
FROM person
GROUP BY country_of_birth
HAVING COUNT(*) > 1;


```
