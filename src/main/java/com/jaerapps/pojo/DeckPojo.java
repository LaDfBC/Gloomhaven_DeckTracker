package com.jaerapps.pojo;

import com.google.common.base.Objects;

import java.util.List;

public class DeckPojo {
    private final int id;
    private final String name;
    private final boolean isActive;
    private final List<CardPojo> cardList;

    private DeckPojo(int id, String name, boolean isActive, List<CardPojo> cardList) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.cardList = cardList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public List<CardPojo> getCardList() {
        return cardList;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String name;
        private boolean isActive;
        private List<CardPojo> cardList;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withIsActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder withCardList(List<CardPojo> cardList) {
            this.cardList = cardList;
            return this;
        }

        public DeckPojo build() {
            return new DeckPojo(id, name, isActive, cardList);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeckPojo deckPojo = (DeckPojo) o;
        return id == deckPojo.id && isActive == deckPojo.isActive && Objects.equal(name, deckPojo.name) && Objects.equal(cardList, deckPojo.cardList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, isActive, cardList);
    }
}
