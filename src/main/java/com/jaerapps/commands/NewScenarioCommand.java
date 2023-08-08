package com.jaerapps.commands;

import com.google.inject.Inject;
import com.jaerapps.ResponseMessageBuilder;
import com.jaerapps.pojo.DeckPojo;
import com.jaerapps.service.DeckService;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import javax.annotation.Nonnull;
import java.util.Optional;

public class NewScenarioCommand implements ICommand{
    private final DeckService deckService;

    @Inject
    public NewScenarioCommand(@Nonnull DeckService deckService) {
        this.deckService = deckService;
    }

    @Override
    public MessageEmbed runCommand(SlashCommandInteractionEvent event) {
        Optional<DeckPojo> maybeDeck = deckService.resetDeck();

        if (maybeDeck.isEmpty()) {
            return ResponseMessageBuilder.buildErrorResponse("No deck retrieved to reset!  Check to make sure the data is solid!");
        }
        return ResponseMessageBuilder.buildStandardResponse("Deck Ready to go!", ResponseMessageBuilder.withDeckBody(maybeDeck.get()));
    }
}
