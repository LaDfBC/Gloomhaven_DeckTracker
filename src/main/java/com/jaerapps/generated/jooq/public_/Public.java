/*
 * This file is generated by jOOQ.
 */
package com.jaerapps.generated.jooq.public_;


import com.jaerapps.generated.jooq.DefaultCatalog;
import com.jaerapps.generated.jooq.public_.tables.Card;
import com.jaerapps.generated.jooq.public_.tables.Deck;
import com.jaerapps.generated.jooq.public_.tables.FlywaySchemaHistory;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.card</code>.
     */
    public final Card CARD = Card.CARD;

    /**
     * The table <code>public.deck</code>.
     */
    public final Deck DECK = Deck.DECK;

    /**
     * The table <code>public.flyway_schema_history</code>.
     */
    public final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        return Arrays.<Sequence<?>>asList(
            Sequences.CARD_ID_SEQ,
            Sequences.DECK_ID_SEQ);
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            Card.CARD,
            Deck.DECK,
            FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY);
    }
}
