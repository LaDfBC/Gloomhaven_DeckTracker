package com.jaerapps.commands;

import com.google.inject.Inject;
import com.jaerapps.ResponseMessageBuilder;
import com.jaerapps.service.DeckService;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import javax.annotation.Nonnull;

public class ShuffleCommand implements ICommand {
    private final DeckService deckService;

    @Inject
    public ShuffleCommand(@Nonnull DeckService deckService) {
        this.deckService = deckService;
    }

    @Override
    public MessageEmbed runCommand(SlashCommandInteractionEvent event) {
        deckService.shuffle();
        return ResponseMessageBuilder.buildStandardResponse("Shuffled", "Returned all drawn cards to the deck");
    }
}
