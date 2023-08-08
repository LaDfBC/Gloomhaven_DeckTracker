/*
 * This file is generated by jOOQ.
 */
package com.jaerapps.generated.jooq.public_.tables;


import com.jaerapps.generated.jooq.public_.Keys;
import com.jaerapps.generated.jooq.public_.Public;
import com.jaerapps.generated.jooq.public_.tables.records.CardRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Card extends TableImpl<CardRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.card</code>
     */
    public static final Card CARD = new Card();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CardRecord> getRecordType() {
        return CardRecord.class;
    }

    /**
     * The column <code>public.card.id</code>.
     */
    public final TableField<CardRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.card.name</code>.
     */
    public final TableField<CardRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.card.rolling</code>.
     */
    public final TableField<CardRecord, Boolean> ROLLING = createField(DSL.name("rolling"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>public.card.ephemeral</code>.
     */
    public final TableField<CardRecord, Boolean> EPHEMERAL = createField(DSL.name("ephemeral"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>public.card.deck_id</code>.
     */
    public final TableField<CardRecord, Integer> DECK_ID = createField(DSL.name("deck_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.card.already_drawn</code>.
     */
    public final TableField<CardRecord, Boolean> ALREADY_DRAWN = createField(DSL.name("already_drawn"), SQLDataType.BOOLEAN.defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");

    private Card(Name alias, Table<CardRecord> aliased) {
        this(alias, aliased, null);
    }

    private Card(Name alias, Table<CardRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.card</code> table reference
     */
    public Card(String alias) {
        this(DSL.name(alias), CARD);
    }

    /**
     * Create an aliased <code>public.card</code> table reference
     */
    public Card(Name alias) {
        this(alias, CARD);
    }

    /**
     * Create a <code>public.card</code> table reference
     */
    public Card() {
        this(DSL.name("card"), null);
    }

    public <O extends Record> Card(Table<O> child, ForeignKey<O, CardRecord> key) {
        super(child, key, CARD);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<CardRecord, Integer> getIdentity() {
        return (Identity<CardRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<CardRecord> getPrimaryKey() {
        return Keys.CARD_PKEY;
    }

    @Override
    public List<UniqueKey<CardRecord>> getKeys() {
        return Arrays.<UniqueKey<CardRecord>>asList(Keys.CARD_PKEY);
    }

    @Override
    public List<ForeignKey<CardRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<CardRecord, ?>>asList(Keys.CARD__CARD_DECK_ID_FK);
    }

    public Deck deck() {
        return new Deck(this, Keys.CARD__CARD_DECK_ID_FK);
    }

    @Override
    public Card as(String alias) {
        return new Card(DSL.name(alias), this);
    }

    @Override
    public Card as(Name alias) {
        return new Card(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Card rename(String name) {
        return new Card(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Card rename(Name name) {
        return new Card(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, String, Boolean, Boolean, Integer, Boolean> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}
