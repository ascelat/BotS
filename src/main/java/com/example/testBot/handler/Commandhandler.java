package com.example.testBot.handler;

import com.example.testBot.constants.CommandBot;
import com.example.testBot.telegram.Bot;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class Commandhandler {

    public BotApiMethod<?> answer(Message message, Bot bot) {
        String commandText = message.getText();
        CommandBot command = CommandBot.fromString(commandText);

        if(command == null){
            return defaultAnswer(message);
        }

        switch (command){
            case START -> {
                return start(message);
            }
            case END -> {
                return end(message);
            }
            case HELP -> {
                return help(message);
            }
            case FEEDBACK -> {
                return feedback(message);
            }
            default -> {
                return defaultAnswer(message);
            }
        }
    }

    private BotApiMethod<?> defaultAnswer(Message message) {
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text("""
                        Такой команды нет. 
                        Попробуйте другую или обратитьсь к поддержке.
                        """)
                .build();
    }

    private BotApiMethod<?> end(Message message) {
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text("""
                        Остановка работы боты/Выключение
                        """)
                .build();
    }

    private BotApiMethod<?> feedback(Message message) {
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text("""
                        1. Программист. Хутыз Сальбий Рашидович.
                        почта Яндекс: khashi.yashiro@yandex.ru
                        2. Администратор. Иванова Елена Сергеевна
                        номер моб. телефона: 8-913-345-98-90
                        """)
                .build();
    }

    private BotApiMethod<?> help(Message message) {
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text("""
                        Инструкция/помощь:
                        
                        Все команды в боте:
                        1. start (Запуск бота)
                        2. help (Команда помощь. Информация о всех возможностях/командах бота)
                        3. feedback (Обратная связь с программистами и администрацией бота)
                        
                        Бот тест. Ничего не делает. Ничего не предлагает.
                        """)
                .build();
    }

    private BotApiMethod<?> start(Message message) {
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text("""
                        Добро пожаловать в нашего тутор бота! 
                        Я здесь, чтобы помочь вам с любыми вопросами 
                        и задачами, которые у вас могут возникнуть. 
                        Не стесняйтесь задавать вопросы, делиться своими 
                        идеями или просить о помощи.
                        
                        Как я могу помочь вам сегодня?
                        """)
                .build();
    }
}
