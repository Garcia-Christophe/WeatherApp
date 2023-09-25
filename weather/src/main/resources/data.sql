CREATE TABLE City (
    id VARCHAR(22) PRIMARY KEY,
    name VARCHAR(22),
    postCode NUMBER(5),
    region VARCHAR(22),
    country VARCHAR(22)
);

INSERT INTO City (id, name, postCode, region, country) VALUES ('1', 'Paris', 75056, 'Île de France', 'France');
INSERT INTO City (id, name, postCode, region, country) VALUES ('2', 'Barcelone', 08001, 'Catalogne', 'Espagne');
INSERT INTO City (id, name, postCode, region, country) VALUES ('3', 'Venise', 30100, 'Vénétie', 'Italie');
INSERT INTO City (id, name, postCode, region, country) VALUES ('4', 'Dakar', 12500, 'Dakar', 'Sénégal');
INSERT INTO City (id, name, postCode, region, country) VALUES ('5', 'Librilla', 30892, 'Murcia', 'Espagne');
