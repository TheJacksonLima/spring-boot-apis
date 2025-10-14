CREATE EXTENSION IF NOT EXISTS pgcrypto;

ALTER TABLE car_details ADD COLUMN IF NOT EXISTS uuid uuid;

UPDATE car_details
SET uuid = gen_random_uuid()
WHERE uuid IS NULL;

ALTER TABLE car_details ALTER COLUMN uuid SET NOT NULL;
CREATE UNIQUE INDEX IF NOT EXISTS ux_car_details_uuid ON car_details(uuid);

