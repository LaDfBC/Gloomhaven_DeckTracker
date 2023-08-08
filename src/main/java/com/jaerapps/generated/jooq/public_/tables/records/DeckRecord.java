/*
 * This file is generated by jOOQ.
 */
package com.jaerapps.generated.jooq.public_.tables.records;


import com.jaerapps.generated.jooq.public_.tables.Deck;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DeckRecord extends UpdatableRecordImpl<DeckRecord> implements Record3<Integer, String, Boolean> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.deck.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.deck.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.deck.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.deck.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.deck.active</code>.
     */
    public void setActive(Boolean value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.deck.active</code>.
     */
    public Boolean getActive() {
        return (Boolean) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, Boolean> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Integer, String, Boolean> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Deck.DECK.ID;
    }

    @Override
    public Field<String> field2() {
        return Deck.DECK.NAME;
    }

    @Override
    public Field<Boolean> field3() {
        return Deck.DECK.ACTIVE;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public Boolean component3() {
        return getActive();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public Boolean value3() {
        return getActive();
    }

    @Override
    public DeckRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public DeckRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public DeckRecord value3(Boolean value) {
        setActive(value);
        return this;
    }

    @Override
    public DeckRecord values(Integer value1, String value2, Boolean value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DeckRecord
     */
    public DeckRecord() {
        super(Deck.DECK);
    }

    /**
     * Create a detached, initialised DeckRecord
     */
    public DeckRecord(Integer id, String name, Boolean active) {
        super(Deck.DECK);

        setId(id);
        setName(name);
        setActive(active);
    }
}
