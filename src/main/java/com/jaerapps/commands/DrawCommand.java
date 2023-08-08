package com.jaerapps.commands;

import com.google.inject.Inject;
import com.jaerapps.ResponseMessageBuilder;
import com.jaerapps.pojo.CardPojo;
import com.jaerapps.service.CardService;
import com.jaerapps.service.DeckService;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Consumer;

public class DrawCommand implements ICommand {
    private final DeckService deckService;
    private final CardService cardService;

    @Inject
    public DrawCommand(
            @Nonnull final DeckService deckService,
            @Nonnull final CardService cardService
    ) {
        this.deckService = deckService;
        this.cardService = cardService;
    }

    @Override
    public MessageEmbed runCommand(SlashCommandInteractionEvent event) {
        OptionMapping strengthenedOption = event.getOption("strengthened");
        OptionMapping muddledOption = event.getOption("muddled");

        if (strengthenedOption == null && muddledOption == null) {
            List<CardPojo> cards = cardService.draw();
            return ResponseMessageBuilder.buildStandardResponse("Success", buildDrawnCardStack(cards));
        } else if (strengthenedOption != null) {
            List<List<CardPojo>> cardStacks = cardService.drawTwice();
            return ResponseMessageBuilder.buildStandardResponse(
                    "Success",
                    "Draw 1: \n" + buildDrawnCardStack(cardStacks.get(0)) + " \n\n Draw 2: " + buildDrawnCardStack(cardStacks.get(0) )+ "\n\nChoose the best option.");
        } else { // Muddled
            List<List<CardPojo>> cardStacks = cardService.drawTwice();
            return ResponseMessageBuilder.buildStandardResponse(
                    "Success",
                    "Draw 1: \n" + buildDrawnCardStack(cardStacks.get(0)) + " \n\n Draw 2: " + buildDrawnCardStack(cardStacks.get(0) )+ "\n\nChoose the worst option.");
        }
    }

    private String buildDrawnCardStack(List<CardPojo> drawnCards) {
        StringBuilder drawnCardBuilder = new StringBuilder("You drew:\n\n");
        drawnCards.forEach(cardPojo -> drawnCardBuilder.append(cardPojo.getName()).append("\n"));

        return drawnCardBuilder.toString();
    }
}
