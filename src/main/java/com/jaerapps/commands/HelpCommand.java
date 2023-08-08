package com.jaerapps.commands;

import com.jaerapps.ResponseMessageBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.List;

public class HelpCommand implements ICommand {
    @Override
    public MessageEmbed runCommand(SlashCommandInteractionEvent event) {
        return ResponseMessageBuilder.buildMultiFieldStandardResponse(
                List.of(
                        new ResponseMessageBuilder.MessageEmbedField(
                                "Overview",
                                "This bot runs entirely on slash commands.  " +
                                        "Simply type a forward slash (/) in Discord to see options and parameters for each command listed here.\n" +
                                        "Ping LaDfBC#1246 for help, suggestions, or to complain."
                        ),
                        new ResponseMessageBuilder.MessageEmbedField(
                                "draw <OPTIONAL: muddled> <OPTIONAL: strengthened>",
                                "Draws a card (or more) from the current modifier deck. " +
                                        "Muddled and strengthened do the same thing since I'm not trying to figure out which is best or worst."
                        ),
                        new ResponseMessageBuilder.MessageEmbedField(
                                "add-card <REQUIRED: name> <OPTIONAL: rolling> <OPTIONAL: ephemeral>",
                                "Rolling and ephemeral default to false.  Ephemeral means it goes away when new-scenario is run or when it's drawn."
                        ),
                        new ResponseMessageBuilder.MessageEmbedField(
                                "add-bless <OPTIONAL: count>",
                                "Specifically adds a bless card, or several, depending on the optional count."
                        ),
                        new ResponseMessageBuilder.MessageEmbedField(
                                "add-curse <OPTIONAL: count>",
                                "Specifically adds a curse card, or several, depending on the optional count."
                        ),
                        new ResponseMessageBuilder.MessageEmbedField(
                                "remove-card <OPTIONAL: name> <OPTIONAL: id>",
                                "Removes a card by either name or id.  Both are individually optional but at least one must be given."
                        ),
                        new ResponseMessageBuilder.MessageEmbedField(
                                "list-cards",
                                "Simply shows the current deck (and discard pile) in their entirety."
                        ),
                        new ResponseMessageBuilder.MessageEmbedField(
                                "new-scenario",
                                "Removes all ephemeral cards and shuffles the discard pile into the deck."
                        ),
                        new ResponseMessageBuilder.MessageEmbedField(
                                "shuffle",
                                "Shuffles the discard pile into the deck."
                        )
                )
        );
    }
}
