CREATE TABLE price_history
(
    id          BIGSERIAL PRIMARY KEY     NOT NULL,
    symbol      VARCHAR(255)              NOT NULL,
    price       DECIMAL(1000, 8)          NOT NULL
);