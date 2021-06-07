CREATE TABLE Cards
(
    CardId     int         NOT NULL AUTO_INCREMENT,
    CardNumber DECIMAL(16) NOT NULL,
    AccountId  int not null ,
    PRIMARY KEY (CardId),
    FOREIGN KEY (AccountId) REFERENCES AccountBalance (AccountId)
);
CREATE UNIQUE INDEX Cards_CARDNUMBER_UINDEX ON Cards (CardNumber);