package com.jaerapps.commands;

import com.google.inject.Inject;
import com.jaerapps.ResponseMessageBuilder;
import com.jaerapps.service.DeckService;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import javax.annotation.Nonnull;

public class NewCharacterCommand implements ICommand{
    private final DeckService deckService;

    @Inject
    public NewCharacterCommand(@Nonnull DeckService deckService) {
        this.deckService = deckService;
    }

    @Override
    public MessageEmbed runCommand(SlashCommandInteractionEvent event) {
        OptionMapping nameOption = event.getOption("name");
        if (nameOption == null) {
            return ResponseMessageBuilder.buildErrorResponse("No name given for the new character");
        }

        String name = nameOption.getAsString();
        deckService.setNewBasicDeck(name);

        return ResponseMessageBuilder.buildStandardResponse("OK", name + " is ready to do some adventuring!");
    }
}
