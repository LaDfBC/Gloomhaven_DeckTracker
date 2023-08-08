package com.jaerapps;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jaerapps.guice.BasicModule;
import com.jaerapps.util.MessageResponder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;

public class MainRunner extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainRunner.class);

    private final MessageReceivedHandler messageReceivedHandler;


    public MainRunner(final String configFilePath) {
        // Load Configs
        Configuration.setInstanceFromFile(configFilePath);

        // Initialize Guice
        Injector injector = Guice.createInjector(new BasicModule());
        messageReceivedHandler = injector.getInstance(MessageReceivedHandler.class);

        // Flyway migration - Runs if configuration exists and is set to true.  Skips otherwise.
        if (Configuration.getProperty(Configuration.UPDATE_DB_ON_STARTUP).isPresent() &&
                Configuration.getProperty(Configuration.UPDATE_DB_ON_STARTUP).get().equalsIgnoreCase("true")) {
            DatabaseHelper.runFlyway();

            // Create the JOOQ files needed to interact with the database
            if (Configuration.getProperty(Configuration.GENERATE_JOOQ).isPresent() &&
                    Configuration.getProperty(Configuration.GENERATE_JOOQ).get().equalsIgnoreCase("true")) {
                DatabaseHelper.runJooq();
            }
        }

    }

    @Override
    public void onSlashCommandInteraction(@Nonnull SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        try {
            MessageResponder responder = messageReceivedHandler.handleMessage(event);
            if (responder != null) {
                responder.sendResponseMessages(event);
            }
        } catch (Exception e) {
            LOGGER.error("Something awful has happened!  Check the logs!");
            e.printStackTrace();
            event
                    .getHook()
                    .sendMessageEmbeds(
                            ResponseMessageBuilder.buildErrorResponse("Critical unhandled error: " + e.getMessage()))
                    .queue();
        }
    }

    public static void main(String[] args) throws LoginException {
        JDA discordContext = JDABuilder.createLight(args[0],
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.DIRECT_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new MainRunner(args[1]))
                .setActivity(Activity.competing("/help will tell you everything I can do!"))
                .build();

        if (Boolean.parseBoolean(Configuration.getPropertyOrThrow("slash.commands.update"))) {discordContext
                .updateCommands()
                .addCommands(
                        Commands.slash(
                                "help",
                                "Displays the help message for this bot."),
                        Commands.slash(
                                "draw",
                                "Draws cards for an attack")
                                .addOption(OptionType.BOOLEAN, "strengthened", "Set true if character is strengthened")
                                .addOption(OptionType.BOOLEAN, "muddled", "Set true if character is muddled"),
                        Commands.slash(
                                        "add-card",
                                        "Adds a card to the deck.  Use add-bless or add-curse for those specifically")
                                .addOption(OptionType.STRING, "name", "Card name.  Placed exactly as-is.", true)
                                .addOption(OptionType.BOOLEAN, "rolling", "True if the modifier \"Rolls\" into another card", false)
                                .addOption(OptionType.BOOLEAN, "ephemeral", "True if the card goes away at the end of this scenario", false),
                        Commands.slash(
                                        "add-bless",
                                        "Adds a bless to the deck.  The count option can be used to add more than one.")
                                .addOption(OptionType.INTEGER, "count", "Number of blesses to add.  Defaults to 1", false),
                        Commands.slash(
                                        "add-curse",
                                        "Adds a curse to the deck.  The count option can be used to add more than one.")
                                .addOption(OptionType.INTEGER, "count", "Number of curses to add.  Defaults to 1", false),
                        Commands.slash(
                                        "remove-card",
                                        "Removes a card - permanently - from the deck. /new-scenario removes all ephemerals.")
                                .addOption(OptionType.STRING, "name", "Name of the card.  Use /list-cards to get the name of each card in the deck.", false)
                                .addOption(OptionType.INTEGER, "count", "Number of the cards of this name you want to remove from the deck", false),
                        Commands.slash(
                                        "list-cards",
                                        "Shows all cards currently in the deck."),
                        Commands.slash(
                                        "new-scenario",
                                        "Removes all ephemeral cards from the deck"),
                        Commands.slash(
                                        "shuffle",
                                        "Shuffles the deck so all cards can once again be drawn"),
                        Commands.slash(
                                "new-character",
                                "Resets the deck to the default list of cards that a character uses when they're completely new.")
                                .addOption(OptionType.STRING, "name", "Name of the character.")
                )
                .queue();
        }

    }
}
