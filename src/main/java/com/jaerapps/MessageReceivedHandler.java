package com.jaerapps;

import com.google.inject.Inject;
import com.jaerapps.commands.*;
import com.jaerapps.enums.CoreCommand;
import com.jaerapps.util.MessageResponder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

public class MessageReceivedHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(MessageReceivedHandler.class);

    private final DrawCommand drawCommand;
    private final NewCharacterCommand newCharacterCommand;
    private final AddCardCommand addCardCommand;
    private final AddBlessCommand addBlessCommand;
    private final AddCurseCommand addCurseCommand;
    private final ShuffleCommand shuffleCommand;
    private final ListCardsCommand listCardsCommand;
    private final RemoveCardCommand removeCardCommand;
    private final NewScenarioCommand newScenarioCommand;

    @Inject
    public MessageReceivedHandler(
            @Nonnull final DrawCommand drawCommand,
            @Nonnull final NewCharacterCommand newCharacterCommand,
            @Nonnull final AddCardCommand addCardCommand,
            @Nonnull final AddBlessCommand addBlessCommand,
            @Nonnull final AddCurseCommand addCurseCommand,
            @Nonnull final ShuffleCommand shuffleCommand,
            @Nonnull final ListCardsCommand listCardsCommand,
            @Nonnull final RemoveCardCommand removeCardCommand,
            @Nonnull final NewScenarioCommand newScenarioCommand
    ) {
        this.drawCommand = drawCommand;
        this.newCharacterCommand = newCharacterCommand;
        this.addCardCommand = addCardCommand;
        this.addBlessCommand = addBlessCommand;
        this.addCurseCommand = addCurseCommand;
        this.shuffleCommand = shuffleCommand;
        this.listCardsCommand = listCardsCommand;
        this.removeCardCommand = removeCardCommand;
        this.newScenarioCommand = newScenarioCommand;
    }

    public MessageResponder handleMessage(SlashCommandInteractionEvent event) {
        MessageResponder responder = MessageResponder.create(event.getChannel());
        CoreCommand incomingCommand = CoreCommand.init(event.getName());

        switch (incomingCommand) {
            case HELP:
                responder.addMessage(new HelpCommand().runCommand(event));
                break;
            case DRAW:
                responder.addMessage(drawCommand.runCommand(event));
                break;
            case ADD_CARD:
                responder.addMessage(addCardCommand.runCommand(event));
                break;
            case ADD_BLESS:
                responder.addMessage(addBlessCommand.runCommand(event));
                break;
            case ADD_CURSE:
                responder.addMessage(addCurseCommand.runCommand(event));
                break;
            case REMOVE_CARD:
                responder.addMessage(removeCardCommand.runCommand(event));
                break;
            case LIST_CARDS:
                responder.addMessage(listCardsCommand.runCommand(event));
                break;
            case NEW_SCENARIO:
                responder.addMessage(newScenarioCommand.runCommand(event));
                break;
            case SHUFFLE:
                responder.addMessage(shuffleCommand.runCommand(event));
                break;
            case NEW_CHARACTER:
                responder.addMessage(newCharacterCommand.runCommand(event));
                break;
            default:
                responder.addMessage(ResponseMessageBuilder.buildErrorResponse(
                        "Unrecognized command: " +
                                event.getName() +
                                ". Please use /help to see available commands")
                );
                break;

        }
        return responder;
    }



}
