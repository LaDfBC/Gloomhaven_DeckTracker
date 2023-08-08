package com.jaerapps.datastore;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.jaerapps.pojo.CardPojo;
import com.jaerapps.pojo.DeckPojo;
import org.jooq.CloseableDSLContext;
import org.jooq.impl.DSL;

import java.util.List;
import java.util.Optional;

import static com.jaerapps.generated.jooq.public_.Tables.CARD;
import static com.jaerapps.generated.jooq.public_.tables.Deck.DECK;
import static com.jaerapps.guice.BasicModule.DATABASE_URL;

public class DeckDAO {
    private final String databaseUrl;

    @Inject
    public DeckDAO(@Named(DATABASE_URL) String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public void insert(String name) {
        try (CloseableDSLContext ctx = DSL.using(databaseUrl)) {
            ctx
                    .insertInto(DECK)
                    .columns(DECK.NAME, DECK.ACTIVE)
                    .values(name, true)
                    .execute();
        }
    }

    public Optional<DeckPojo> getCurrentDeck() {
        try (CloseableDSLContext ctx = DSL.using(databaseUrl)) {
            return DatabaseRowToPojoTranslator.deckAndCardListFromRecord(
                ctx
                        .select(DECK.NAME, DECK.ACTIVE, DECK.ID, CARD.asterisk())
                        .from(DECK)
                        .leftJoin(CARD)
                            .on(DECK.ID.eq(CARD.DECK_ID))
                        .where(DECK.ACTIVE.isTrue())
                        .fetch()
            );
        }
    }

    public void shuffle() {
        try (CloseableDSLContext ctx = DSL.using(databaseUrl)) {
            ctx
                    .update(CARD)
                    .set(CARD.ALREADY_DRAWN, false)
                    .where(CARD.DECK_ID.eq(ctx.select(DECK.ID).from(DECK).where(DECK.ACTIVE.eq(true))))
                    .execute();
        }
    }

    public void removeEphemeral() {
        try (CloseableDSLContext ctx = DSL.using(databaseUrl)) {
            ctx
                    .deleteFrom(CARD)
                    .where(CARD.DECK_ID.eq(ctx.select(DECK.ID).from(DECK).where(DECK.ACTIVE.eq(true))))
                    .and(CARD.EPHEMERAL.eq(true))
                    .execute();
        }
    }
}
