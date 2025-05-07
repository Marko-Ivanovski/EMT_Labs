-- Drop Views
DROP MATERIALIZED VIEW IF EXISTS accommodations_per_host;
DROP MATERIALIZED VIEW IF EXISTS hosts_per_country;


-- Drop Tables
DROP TABLE IF EXISTS temporary_reservation;
DROP TABLE IF EXISTS accommodation;
DROP TABLE IF EXISTS host;
DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS users;


-- Create Tables
CREATE TABLE country (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    continent VARCHAR(255) NOT NULL
);

CREATE TABLE host (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    country_id INTEGER REFERENCES country(id)
);

CREATE TABLE accommodation (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(50) NOT NULL,
    host_id INTEGER REFERENCES host(id),
    num_rooms INTEGER NOT NULL,
    rented BOOLEAN NOT NULL
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE temporary_reservation (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    accommodation_id INTEGER REFERENCES accommodation(id)
);


-- Views
CREATE MATERIALIZED VIEW accommodations_per_host AS
SELECT h.id AS host_id, h.name || ' ' || h.surname AS host_name, COUNT(a.id) AS num_accommodations FROM host h
LEFT JOIN accommodation a ON h.id = a.host_id
GROUP BY h.id, h.name, h.surname;

CREATE MATERIALIZED VIEW hosts_per_country AS
SELECT c.id AS country_id, c.name AS country_name, COUNT(h.id) AS num_hosts
FROM country c LEFT JOIN host h ON c.id = h.country_id
GROUP BY c.id, c.name;


-- Indexing
CREATE UNIQUE INDEX idx_users_username ON users(username);
CREATE INDEX idx_accommodation_host_id ON accommodation(host_id);
CREATE INDEX idx_host_country_id ON host(country_id);
CREATE INDEX idx_reservation_user ON temporary_reservation(user_id);
CREATE INDEX idx_reservation_accommodation ON temporary_reservation(accommodation_id);
