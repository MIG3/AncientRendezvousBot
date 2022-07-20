package ru.mgubin.tbot.bot;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.command.OutputParameters;
import ru.mgubin.tbot.enums.BotState;
import ru.mgubin.tbot.handlers.HandleMessages;
import ru.mgubin.tbot.handlers.HandleState;

@Log4j
@Component
public class Bot extends TelegramLongPollingBot
{
    final private String BOT_TOKEN_TELEGA;
    final private String BOT_NAME_TELEGA;
    final private String prevCommand = "";

    public Bot(String botToken, String botName)
    {
        super();
        this.BOT_TOKEN_TELEGA = botToken;
        this.BOT_NAME_TELEGA = botName;
    }

    @Override
    public String getBotUsername()
    {
        log.debug("Название бота: " + BOT_NAME_TELEGA);
        return BOT_NAME_TELEGA;
    }

    @Override
    public String getBotToken()
    {
        log.debug("Токен бота: " + BOT_TOKEN_TELEGA);
        return BOT_TOKEN_TELEGA;
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        log.debug("Получаем новое обновление. updateID: " + update.getUpdateId());
        Long chatId = update.getMessage().getChatId();
        String inputText = update.getMessage().getText();
        BotState botState;
        if (update.hasMessage())
        {
            try
            {
                HandleMessages handleMessages = new HandleMessages();
                HandleState state = new HandleState();

                botState = handleMessages.handleMessage(update.getMessage());
                Command command = state.handleState(botState);
                OutputParameters outputParameters = command.invoke(chatId);
                execute(outputParameters.getSm());

            } catch (TelegramApiException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}

