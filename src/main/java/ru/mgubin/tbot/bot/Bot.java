package ru.mgubin.tbot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.command.OutputParameters;
import ru.mgubin.tbot.enums.BotStateEnum;
import ru.mgubin.tbot.handler.CallBackAction;
import ru.mgubin.tbot.handler.HandleMessages;
import ru.mgubin.tbot.handler.HandleStateSelector;

@Slf4j
@Component
public class Bot extends TelegramLongPollingBot
{
    private final String BOT_TOKEN_TELEGRAM;
    private final String BOT_NAME_TELEGRAM;
    private final UserDataCache userDataCache = new UserDataCache();
    @Autowired
    public Bot(String botToken, String botName)
    {
        super();
        this.BOT_TOKEN_TELEGRAM = botToken;
        this.BOT_NAME_TELEGRAM = botName;
    }

    @Override
    public String getBotUsername()
    {
        return BOT_NAME_TELEGRAM;
    }

    @Override
    public String getBotToken()
    {
        return BOT_TOKEN_TELEGRAM;
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        log.info("Получаем новое обновление. updateID: " + update.getUpdateId());
        Message message = update.getMessage();
        BotStateEnum botState;

        if (update.hasCallbackQuery())
        {
            CallBackAction callBackAction = new CallBackAction(userDataCache);
            CallbackQuery callbackQuery = update.getCallbackQuery();
            try
            {
                execute(callBackAction.processCallbackQuery(callbackQuery));
            } catch (TelegramApiException e)
            {
                throw new RuntimeException(e);
            }
        }

        else if (update.hasMessage())
        {
            try
            {
                HandleMessages handleMessages = new HandleMessages(userDataCache);
                HandleStateSelector state = new HandleStateSelector(userDataCache);

                botState = handleMessages.handleInputMessage(update.getMessage());
                Command command = state.handleStateSelector(botState);
                OutputParameters outputParameters = command.invoke(message);
                if (outputParameters.getSm()!=null)
                    execute(outputParameters.getSm());
                else execute(outputParameters.getSp());

            } catch (TelegramApiException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}

