package com.jaerapps.commands;

import com.google.inject.Inject;
import com.jaerapps.ResponseMessageBuilder;
import com.jaerapps.service.CardService;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import javax.annotation.Nonnull;

public class AddCardCommand implements ICommand{
    private final CardService cardService;

    @Inject
    public AddCardCommand(@Nonnull CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public MessageEmbed runCommand(SlashCommandInteractionEvent event) {
        OptionMapping nameOption = event.getOption("name");
        if (nameOption == null) {
            return ResponseMessageBuilder.buildErrorResponse("No card name given!");
        } else {
            String name = nameOption.getAsString();

            OptionMapping rollingOption = event.getOption("rolling");
            OptionMapping ephemeralOption = event.getOption("ephemeral");

            cardService.addCard(name, rollingOption != null, ephemeralOption != null);

            //TODO: Make better message
            return ResponseMessageBuilder.buildStandardResponse("OK", "Card Added");
        }
    }
}
