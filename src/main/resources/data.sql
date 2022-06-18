INSERT INTO persons (first_name, last_name, profession)
VALUES
('Walter', 'White', 'chemistry teacher'),
('Skyler', 'White', 'accountant'),
('Jesse', 'Pinkman', 'cook'),
('Saul', 'Goodman', 'lawyer'),
('Tuco', 'Salamanca', 'enterpreneur'),
('Hector', 'Salamanca', 'enterpreneur'),
('Hank', 'Schrader', 'DEA-agent'),
('Gustavo', 'Fring', 'enterpreneur'),
('Mike', 'Ehrmentraut', 'parkingbooth operator'),
('Walter Jr', 'White', 'student');

INSERT INTO cars (brand, type)
VALUES
('Fleetwood', 'Bounder RV'),
('Pontiac', 'Aztek'),
('Chevrolet', 'Monte Carlo'),
('Toyota', 'Tercel'),
('Jeep','Grand Wagoneer'),
('Dodge', 'Challenger');

INSERT INTO persons_cars(person_id, cars_id)
VALUES
(1,1),
(1,2),
(2,5),
(3,3),
(3,4),
(10,6);

INSERT INTO houses(name, town)
VALUES
('White Residence', 'Albuquerque'),
('Schrader Residence', 'Albuquerque'),
('Pinkman Residence', 'Albuquerque');

INSERT INTO labs(name, type, location)
VALUES
('RV', 'mobile lab', 'desert'),
('Tented house', 'domestic', 'residential area'),
('The SuperLab','professional lab','underground'),
('Mexicano', 'mexican lab', 'Mexico');