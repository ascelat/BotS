package com.example.testBot.handler;

import com.example.testBot.telegram.Bot;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Service
public class CallbackQueryHandler {
    public BotApiMethod<?> answer(CallbackQuery callbackQuery, Bot bot) {
        return null;
    }
}
