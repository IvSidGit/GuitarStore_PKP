CREATE TABLE brands
(
    id UUID PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    founded_year INTEGER,
    version BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uq_brands_name UNIQUE (name)
);

CREATE TABLE guitars
(
    id UUID PRIMARY KEY,
    serial_number VARCHAR(10) NOT NULL,
    model VARCHAR(30) NOT NULL,
    type VARCHAR(9)  NOT NULL,
    color VARCHAR(20),
    brand_id UUID NOT NULL REFERENCES brands (id),
    price INTEGER NOT NULL,
    publication_year INTEGER,
    version BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX idx_guitars_brand_id ON guitars (brand_id);

ALTER TABLE guitars
    ADD CONSTRAINT uq_guitars_serial_number_brand UNIQUE (serial_number, brand_id);