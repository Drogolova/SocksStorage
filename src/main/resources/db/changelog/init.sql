CREATE TABLE Socks(
    id BIGINT generated by default as identity primary key,
    color TEXT NOT NULL,
    size BIGINT NOT NULL,
    cotton_part BIGINT NOT NULL,
    quantity BIGINT NOT NULL
);

