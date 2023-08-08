package com.jaerapps.service;

import com.google.inject.Inject;
import com.jaerapps.datastore.CardDAO;
import com.jaerapps.datastore.DeckDAO;
import com.jaerapps.pojo.CardPojo;
import com.jaerapps.pojo.DeckPojo;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public class DeckService {
    private final DeckDAO deckDAO;
    private final CardDAO cardDAO;

    @Inject
    public DeckService (@Nonnull final DeckDAO deckDAO,
                        @Nonnull final CardDAO cardDAO) {
        this.deckDAO = deckDAO;
        this.cardDAO = cardDAO;
    }

    public void insert (String name) {
        deckDAO.insert(name);
    }

    public void setNewBasicDeck(String name) {
        insert(name);
        cardDAO.insert("Double", 1, false, false);
        cardDAO.insert("Miss", 1, false, false);
        cardDAO.insert("+2", 1, false, false);
        cardDAO.insert("+1", 5, false, false);
        cardDAO.insert("0", 6, false, false);
        cardDAO.insert("-1", 5, false, false);
        cardDAO.insert("-2", 1, false, false);
    }

    public Optional<DeckPojo> fetchCurrentDeckState() {
        return deckDAO.getCurrentDeck();
    }

    public void shuffle() {
        deckDAO.shuffle();
    }

    public void removeEmphemeral() {
        deckDAO.removeEphemeral();
    }

    public Optional<DeckPojo> resetDeck() {
        removeEmphemeral();
        shuffle();
        return fetchCurrentDeckState();
    }
}
