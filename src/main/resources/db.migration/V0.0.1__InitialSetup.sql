CREATE TABLE deck (
    id              SERIAL PRIMARY KEY,
    name            VARCHAR NOT NULL
);

CREATE TABLE card (
    id                  SERIAL PRIMARY KEY,
    name                varchar NOT NULL,
    rolling             boolean NOT NULL DEFAULT false,
    ephemeral           boolean NOT NULL DEFAULT false,
    deck_id             integer NOT NULL,
    CONSTRAINT card_deck_id_fk FOREIGN KEY(deck_id) REFERENCES deck(id)
);