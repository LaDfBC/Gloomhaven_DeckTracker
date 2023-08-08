package com.jaerapps.service;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.jaerapps.datastore.CardDAO;
import com.jaerapps.datastore.DeckDAO;
import com.jaerapps.pojo.CardPojo;
import com.jaerapps.pojo.DeckPojo;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public class CardService {
    private final CardDAO cardDAO;
    private final DeckDAO deckDAO;

    @Inject
    public CardService(@Nonnull final CardDAO cardDAO, @Nonnull final DeckDAO deckDAO) {
        this.cardDAO = cardDAO;
        this.deckDAO = deckDAO;
    }

    public List<CardPojo> draw() {
        List<CardPojo> drawnCards = Lists.newArrayList();
        Optional<CardPojo> maybeCard = cardDAO.draw();

        do {
            if (maybeCard.isEmpty()) {
                throw new IllegalStateException("No cards to draw!");
            } else {
                drawnCards.add(maybeCard.get());
            }
        } while (maybeCard.get().isRolling());

        return drawnCards;
    }

    public List<List<CardPojo>> drawTwice() {
        return List.of(draw(), draw());
    }

    public DeckPojo addCard(String name, int count, boolean ephemeral) {
        cardDAO.insert(name, count, ephemeral, false);
        return afterAdd();
    }

    public DeckPojo addCard(String name, boolean rolling, boolean ephemeral) {
        cardDAO.insert(name, 1, ephemeral, rolling);
        return afterAdd();
    }

    public Boolean removeCard(String name, Integer count) {
        return cardDAO.deleteByName(name, count);
    }

    private DeckPojo afterAdd() {
        deckDAO.shuffle();

        Optional<DeckPojo> possibleDeck = deckDAO.getCurrentDeck();
        if(possibleDeck.isEmpty()) {
            throw new IllegalStateException("No active deck - possibly a different error caused this!");
        } else {
            return possibleDeck.get();
        }
    }


}
