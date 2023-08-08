package com.jaerapps.datastore;

import com.jaerapps.generated.jooq.public_.tables.records.CardRecord;
import com.jaerapps.generated.jooq.public_.tables.records.DeckRecord;
import com.jaerapps.pojo.CardPojo;
import com.jaerapps.pojo.DeckPojo;
import org.jooq.Record;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.jaerapps.generated.jooq.public_.tables.Card.CARD;
import static com.jaerapps.generated.jooq.public_.tables.Deck.DECK;

public class DatabaseRowToPojoTranslator {
    public static Optional<CardPojo> cardFromRecord(@Nullable CardRecord cardFromDatabase) {
        return cardFromDatabase == null ? Optional.empty() : Optional.of(CardPojo.builder()
                .withName(cardFromDatabase.getName())
                .withId(cardFromDatabase.getId())
                .withDeckId(cardFromDatabase.getDeckId())
                .isAlreadyDrawn(cardFromDatabase.getAlreadyDrawn())
                .isEphemeral(cardFromDatabase.getEphemeral())
                .isRolling(cardFromDatabase.getRolling())
                .build());
    }

    public static Optional<DeckPojo> deckFromRecord(@Nullable DeckRecord deckFromDatabase) {
        return deckFromDatabase == null ? Optional.empty() : Optional.of(DeckPojo.builder()
                .withId(deckFromDatabase.getId())
                .withIsActive(deckFromDatabase.getActive())
                .withName(deckFromDatabase.getName())
                .build());
    }

    public static Optional<DeckPojo> deckAndCardListFromRecord(@Nullable List<Record> databaseRecords) {
        return databaseRecords == null || databaseRecords.size() == 0 ? Optional.empty() : Optional.of(
                DeckPojo
                        .builder()
                        .withId(databaseRecords.get(0).get(DECK.ID))
                        .withName(databaseRecords.get(0).get(DECK.NAME))
                        .withIsActive(databaseRecords.get(0).get(DECK.ACTIVE))
                        .withCardList(cardListFromRecords(databaseRecords))
                        .build()
        );
    }

    private static List<CardPojo> cardListFromRecords(@Nullable List<Record> databaseRecords) {
        return databaseRecords
                .stream()
                .map(record -> CardPojo
                        .builder()
                        .withId(record.get(CARD.ID))
                        .withDeckId(record.get(CARD.DECK_ID))
                        .withName(record.get(CARD.NAME))
                        .isRolling(record.get(CARD.ROLLING))
                        .isEphemeral(record.get(CARD.EPHEMERAL))
                        .isAlreadyDrawn(record.get(CARD.ALREADY_DRAWN))
                        .build())
                .collect(Collectors.toList());
    }
}
