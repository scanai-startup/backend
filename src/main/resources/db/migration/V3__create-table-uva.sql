CREATE TABLE IF NOT EXISTS uva(
    id INT PRIMARY KEY AUTO_INCREMENT,
    valid TINYINT NOT NULL,
    datachegada DATE NOT NULL,
    numerotalao INT NOT NULL,
    sanidade INT NOT NULL,
    peso INT NOT NULL,
    so2 VARCHAR(255) NOT NULL,
    numerolote INT NOT NULL,
    tipovinho VARCHAR(255) NOT NULL,
    casta VARCHAR(255) NOT NULL
);
