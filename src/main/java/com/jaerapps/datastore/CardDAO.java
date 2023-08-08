package com.jaerapps.datastore;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.jaerapps.generated.jooq.public_.tables.records.CardRecord;
import com.jaerapps.pojo.CardPojo;
import org.jooq.CloseableDSLContext;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import static com.jaerapps.generated.jooq.public_.Tables.CARD;
import static com.jaerapps.generated.jooq.public_.tables.Deck.DECK;
import static com.jaerapps.guice.BasicModule.DATABASE_URL;
import static org.jooq.impl.DSL.rand;

public class CardDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(CardDAO.class);

    private final String databaseUrl;

    @Inject
    public CardDAO(@Named(DATABASE_URL) String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }


    public void insert(String name, Integer count, boolean ephemeral, boolean rolling) {
        try (CloseableDSLContext ctx = DSL.using(databaseUrl)) {
            for (int i = 0; i < count; i++) {
                ctx
                        .insertInto(CARD)
                        .columns(CARD.DECK_ID, CARD.NAME, CARD.EPHEMERAL, CARD.ROLLING)
                        .values(
                                ctx.select(DECK.ID).from(DECK).where(DECK.ACTIVE.eq(true)).fetchOne(DECK.ID),
                                name,
                                ephemeral,
                                rolling
                        )
                        .execute();
            }
        }
    }

    public Boolean deleteByName(String name, Integer count) {
        try (CloseableDSLContext ctx = DSL.using(databaseUrl)) {
            List<Integer> idsToDelete =
                    ctx
                            .select(CARD.ID)
                            .from(CARD)
                            .where(CARD.NAME.eq(name))
                            .limit(count)
                            .fetch()
                            .getValues(CARD.ID);

            if (idsToDelete.size() != count) {
                LOGGER.error("Number of rows to delete is not equal to the requested count to be deleted");
                return false;
            }

            return deleteByIds(idsToDelete);
        }
    }

    public Boolean deleteByIds(List<Integer> ids) {
        try (CloseableDSLContext ctx = DSL.using(databaseUrl)) {
            ctx
                    .deleteFrom(CARD)
                    .where(CARD.ID.in(ids))
                    .execute();
        } catch (Exception e) {
            LOGGER.error("Unexpected error from delete query: " + e.getMessage());
            return false;
        }

        return true;
    }

    public Optional<CardPojo> draw() {
        try (CloseableDSLContext ctx = DSL.using(databaseUrl)) {
            CardRecord card = ctx
                    .selectFrom(CARD)
                    .where(CARD.ALREADY_DRAWN.eq(false))
                    .orderBy(rand())
                    .limit(1)
                    .fetchOne();

            if (card != null && card.getEphemeral()) {
                ctx
                        .deleteFrom(CARD)
                        .where(CARD.ID.eq(card.getId()))
                        .execute();
            } else {
                ctx
                        .update(CARD)
                        .set(CARD.ALREADY_DRAWN, true)
                        .where(CARD.ID.eq(card.getId()))
                        .execute();
            }


            return DatabaseRowToPojoTranslator.cardFromRecord(card);
        }
    }
}
