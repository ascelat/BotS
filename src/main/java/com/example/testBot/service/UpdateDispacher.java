package com.example.testBot.service;

import com.example.testBot.service.handler.CallbackQueryHandler;
import com.example.testBot.service.handler.Commandhandler;
import com.example.testBot.service.handler.MessageHandler;
import com.example.testBot.telegram.Bot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@Slf4j
public class UpdateDispacher {
    private final CallbackQueryHandler callbackQueryHandler;
    private final Commandhandler commandhandler;
    private final MessageHandler messageHandler;

    @Autowired
    public UpdateDispacher(CallbackQueryHandler callbackQueryHandler,
                           Commandhandler commandhandler,
                           MessageHandler messageHandler) {
        this.callbackQueryHandler = callbackQueryHandler;
        this.commandhandler = commandhandler;
        this.messageHandler = messageHandler;
    }

    public BotApiMethod<?> distribute(Update update, Bot bot) {
        if(update.hasCallbackQuery()) {
            return callbackQueryHandler.answer(update.getCallbackQuery(), bot);
        }
        if(update.hasMessage()) {
            Message message = update.getMessage();
            if(message.hasText()) {
                if(message.getText().charAt(0) == '/') {
                    return commandhandler.answer(message, bot);
                }
            }
            return messageHandler.answer(message, bot);
        }
        log.info("Unsupported operation: " + update);
        return null;
    }
}
