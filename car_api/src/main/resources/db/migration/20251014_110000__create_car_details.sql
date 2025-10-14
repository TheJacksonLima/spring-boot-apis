CREATE TABLE IF NOT EXISTS car_details (
    id SERIAL PRIMARY KEY,
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    color VARCHAR(100),
    year INTEGER,
    price NUMERIC(15, 2),
    km INTEGER,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

