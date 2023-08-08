package com.jaerapps;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.jaerapps.pojo.CardPojo;
import com.jaerapps.pojo.DeckPojo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;


public class ResponseMessageBuilder {
    public static MessageEmbed buildErrorResponse(String message) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Error Encountered");
        eb.setColor(Color.RED);
        eb.addField("Error: ", message != null ? message : "Caught an unhandled exception with no message.  " +
                "This should be considered a critical exception - please see the footer for how to contact this " +
                "bot's developer and let them know they messed up big time.  Thanks!", false);
        eb.addField("Support",  "If this is reoccurring or consistent, please consider messaging this bot's author: [LaDfBC#1246](https://discordapp.com/channels/@me/181238139539423232/)", false);
        return eb.build();
    }

    public static MessageEmbed buildWarningResponse(String message) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(new Color(255, 102, 0));
        eb.addField("Unsuccessful Request", message, false);
        eb.setFooter("This indicates that there was no error but that you tried to do something that wasn't allowed.  " +
                "Refer to the /help command for some guidance on how to send a valid command.");
        return eb.build();
    }

    public static MessageEmbed buildStandardResponse(String title, String message) {
        return buildMultiFieldStandardResponse(List.of(new MessageEmbedField(title, message)));
    }

    public static MessageEmbed buildMultiFieldStandardResponse(List<MessageEmbedField> fields) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.GREEN);

        for (MessageEmbedField currentField : fields) {
            eb.addField(currentField.getTitle(), currentField.getField(), false);
        }

        return eb.build();
    }

    public static String withDeckBody(DeckPojo deck) {
        StringBuilder deckResponseString = new StringBuilder();
        deckResponseString.append("Name:    ").append(deck.getName()).append('\n\n');
        deckResponseString.append("Card List: \n\n");

        List<CardPojo> drawn =
                deck.getCardList().stream().filter((Predicate<CardPojo>) CardPojo::isAlreadyDrawn).collect(Collectors.toList());
        List<CardPojo> inDeck =
                deck.getCardList().stream().filter((Predicate<CardPojo>) input -> !input.isAlreadyDrawn()).collect(Collectors.toList());

        deckResponseString.append("CARDS IN DECK: \n\n");
        inDeck.forEach(cardPojo -> deckResponseString
                .append(cardPojo.getName()).append("\n"));

        deckResponseString.append("\n\n CARDS DRAWN: \n\n");
        drawn.forEach(cardPojo -> deckResponseString.append(cardPojo.getName()).append("\n"));
        return deckResponseString.toString();
    }

    public static MessageEmbed buildSketchyActionResponse(String goodMessage, String warningMessage) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.YELLOW);
        eb.addField("OK", goodMessage, false);
        eb.addField("Warning", warningMessage, false);
        eb.setFooter("Indicates the request was successful but may not be what you intended.  Check warning.");
        return eb.build();
    }

    public static class MessageEmbedField {
        private final String title;
        private final String field;

        public MessageEmbedField(final String title, final String field) {
            this.title = title;
            this.field = field;
        }

        public String getTitle() {
            return title;
        }

        public String getField() {
            return field;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MessageEmbedField that = (MessageEmbedField) o;
            return Objects.equal(title, that.title) && Objects.equal(field, that.field);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(title, field);
        }
    }
}
