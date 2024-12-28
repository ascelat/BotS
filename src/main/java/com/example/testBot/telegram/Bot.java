package com.example.testBot.telegram;

import com.example.testBot.service.UpdateDispacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class Bot extends TelegramWebhookBot {
    private final TelegramProperties telegramProperties;
    private final UpdateDispacher updateDispacher;

    @Autowired
    public Bot(TelegramProperties telegramProperties,
               UpdateDispacher updateDispacher) {
        super(telegramProperties.getToken());
        this.telegramProperties = telegramProperties;
        this.updateDispacher = updateDispacher;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return updateDispacher.distribute(update, this);
    }

    @Override
    public String getBotPath() {
        return telegramProperties.getPath();
    }

    @Override
    public String getBotUsername() {
        return telegramProperties.getName();
    }
}
