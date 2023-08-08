package com.jaerapps.commands;

import com.google.inject.Inject;
import com.jaerapps.ResponseMessageBuilder;
import com.jaerapps.service.CardService;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import javax.annotation.Nonnull;

public class RemoveCardCommand implements ICommand {
    private final CardService cardService;

    @Inject
    public RemoveCardCommand(@Nonnull CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public MessageEmbed runCommand(SlashCommandInteractionEvent event) {
        OptionMapping nameOption = event.getOption("name");
        OptionMapping countOption = event.getOption("count");
        if (nameOption == null) {
            return ResponseMessageBuilder.buildErrorResponse("No card name given!");
        } else {
            String name = nameOption.getAsString();
            Integer count = countOption != null ? countOption.getAsInt() : 1;

            Boolean successful = cardService.removeCard(name, count);
            if (successful) {
                return ResponseMessageBuilder.buildStandardResponse("Successful", count + " cards removed");
            }

            return ResponseMessageBuilder.buildErrorResponse("Failed to delete for some reason.  See logs.");
        }
    }
}
