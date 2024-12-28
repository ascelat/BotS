package com.example.testBot.service.handler;

import com.example.testBot.telegram.Bot;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class Commandhandler {
    public BotApiMethod<?> answer(Message message, Bot bot) {
        String command = message.getText();
        switch (command){
            case "/start" -> {
                return start(message);
            }
        }
        return null;
    }

    private BotApiMethod<?> start(Message message) {
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text("""
                        Добро пожаловать в нашего тутор бота! 
                        Я здесь, чтобы помочь вам с любыми вопросами и задачами, 
                        которые у вас могут возникнуть. Не стесняйтесь задавать вопросы, 
                        делиться своими идеями или просить о помощи.
                        
                        Как я могу помочь вам сегодня?
                        """)
                .build();
    }
}
