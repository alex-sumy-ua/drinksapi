DROP DATABASE IF EXISTS planets;
CREATE DATABASE planets;

\c planets;

CREATE TABLE planets
(
    planet_id SERIAL PRIMARY KEY,
    planet_name VARCHAR,
    distance NUMBER,
    planet_type BOOLEAN,
    planet_size NUMBER,
    YearInDays NUMBER,
    orbitalCircumference NUMBER,
    moons List
);