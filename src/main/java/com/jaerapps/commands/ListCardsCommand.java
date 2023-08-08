package com.jaerapps.commands;

import com.jaerapps.ResponseMessageBuilder;
import com.jaerapps.pojo.CardPojo;
import com.jaerapps.pojo.DeckPojo;
import com.jaerapps.service.DeckService;
import com.jaerapps.util.MessageResponder;
import jakarta.inject.Inject;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.checkerframework.checker.units.qual.C;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class ListCardsCommand implements ICommand {
    private final DeckService deckService;

    @Inject
    public ListCardsCommand(DeckService deckService) {
        this.deckService = deckService;
    }

    @Override
    public MessageEmbed runCommand(SlashCommandInteractionEvent event) {
        Optional<DeckPojo> possibleDeck = deckService.fetchCurrentDeckState();

        if (possibleDeck.isEmpty()) {
            return ResponseMessageBuilder.buildErrorResponse("There is no deck active!  Make a new one or set one.");
        }

        DeckPojo deck = possibleDeck.get();
        return ResponseMessageBuilder.buildStandardResponse("Current Decklist:", ResponseMessageBuilder.withDeckBody(deck));
    }
}
