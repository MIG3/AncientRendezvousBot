package ru.mgubin.tbot.handlers;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.mgubin.tbot.enums.BotState;

import java.util.Optional;

public class HandleMessages
{
    public BotState handleMessage(Message message) throws TelegramApiException
    {
        BotState botState = null;

        if (message.hasText() && message.hasEntities())
        {
            Optional<MessageEntity> commandEntity = message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent())
            {
                String command = message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                switch (command)
                {
                    case "/start":
                        botState = BotState.START;
                        break;
                    case "/update":
                        botState = BotState.CORRECT_PROFILE;
                        break;
                    case "/help":
                        botState = BotState.SHOW_HELP_MENU;
                        break;
                    case "/search":
                        botState = BotState.SEARCH;
                        break;
                    case "/name":
                        botState = BotState.ASK_NAME;
                        break;
                    case "/age":
                        botState = BotState.ASK_BIRTHDAY;
                        break;
                    case "/gender":
                        botState = BotState.ASK_GENDER;
                        break;
                    case "/info":
                        botState = BotState.ASK_INFO;
                }
            }
        }
        return botState;
    }
}
