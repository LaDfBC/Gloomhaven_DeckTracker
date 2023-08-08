package com.jaerapps.pojo;

import com.google.common.base.Objects;

public class CardPojo {
    private final int id;
    private final String name;
    private final boolean rolling;
    private final boolean ephemeral;
    private final boolean alreadyDrawn;
    private final int deckId;

    private CardPojo(int id, String name, boolean rolling, boolean ephemeral, boolean alreadyDrawn, int deckId) {
        this.id = id;
        this.name = name;
        this.rolling = rolling;
        this.ephemeral = ephemeral;
        this.alreadyDrawn = alreadyDrawn;
        this.deckId = deckId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isRolling() {
        return rolling;
    }

    public boolean isEphemeral() {
        return ephemeral;
    }

    public boolean isAlreadyDrawn() {
        return alreadyDrawn;
    }

    public int getDeckId() {
        return deckId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String name;
        private boolean rolling;
        private boolean ephemeral;
        private boolean alreadyDrawn;
        private int deckId;

        public Builder () {}

        public CardPojo.Builder withId(int id) {
            this.id = id;
            return this;
        }

        public CardPojo.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public CardPojo.Builder isRolling(boolean rolling) {
            this.rolling = rolling;
            return this;
        }

        public CardPojo.Builder isEphemeral(boolean ephemeral) {
            this.ephemeral = ephemeral;
            return this;
        }

        public CardPojo.Builder isAlreadyDrawn(boolean alreadyDrawn) {
            this.alreadyDrawn = alreadyDrawn;
            return this;
        }

        public CardPojo.Builder withDeckId(int deckId) {
            this.deckId = deckId;
            return this;
        }

        public CardPojo build() {
            return new CardPojo(id, name, rolling, ephemeral, alreadyDrawn, deckId);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardPojo cardPojo = (CardPojo) o;
        return id == cardPojo.id && rolling == cardPojo.rolling && ephemeral == cardPojo.ephemeral && alreadyDrawn == cardPojo.alreadyDrawn && deckId == cardPojo.deckId && Objects.equal(name, cardPojo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, rolling, ephemeral, alreadyDrawn, deckId);
    }
}
