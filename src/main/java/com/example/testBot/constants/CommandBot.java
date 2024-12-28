package com.example.testBot.constants;

import lombok.Getter;

@Getter
public enum CommandBot {
    START("/start"),
    HELP("/help"),
    END("/end"),
    FEEDBACK("/feedback");

    private String commandText;

    CommandBot(String commandText) {
        this.commandText = commandText;
    }

    public static CommandBot fromString(String text){
        for(CommandBot command: CommandBot.values()) {
            if(command.getCommandText().equalsIgnoreCase(text)) {
                return command;
            }
        }
        return null;
    }
}
