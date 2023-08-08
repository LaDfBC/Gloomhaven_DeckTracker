package com.jaerapps.commands;

import com.google.inject.Inject;
import com.jaerapps.ResponseMessageBuilder;
import com.jaerapps.service.CardService;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import javax.annotation.Nonnull;

public class AddCurseCommand implements ICommand {
    private final CardService cardService;

    @Inject
    public AddCurseCommand(@Nonnull CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public MessageEmbed runCommand(SlashCommandInteractionEvent event) {
        OptionMapping countOption = event.getOption("count");

        int count = countOption == null ? 1 : countOption.getAsInt();
        cardService.addCard("curse (x2)", count, true);
        return ResponseMessageBuilder.buildStandardResponse("OK", "Cursed you " + count + " times");
    }
}
