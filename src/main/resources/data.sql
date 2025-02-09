DELETE FROM sensors;
DELETE FROM sensor_types;
DELETE FROM sensor_units;


INSERT INTO sensor_types (name) VALUES ('Pressure');
INSERT INTO sensor_types (name) VALUES ('Voltage');
INSERT INTO sensor_types (name) VALUES ('Temperature');
INSERT INTO sensor_types (name) VALUES ('Humidity');

INSERT INTO sensor_units (name) VALUES ('bar');
INSERT INTO sensor_units (name) VALUES ('voltage');
INSERT INTO sensor_units (name) VALUES ('°С');
INSERT INTO sensor_units (name) VALUES ('%');