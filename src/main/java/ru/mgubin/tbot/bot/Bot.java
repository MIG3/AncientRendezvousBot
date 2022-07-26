package ru.mgubin.tbot.bot;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.enums.BotStateEnum;
import ru.mgubin.tbot.enums.GeneralButtonStateEnum;
import ru.mgubin.tbot.exception.TelegramException;
import ru.mgubin.tbot.handler.HandleMessages;

@Slf4j
public class Bot extends TelegramLongPollingBot {
    private final String botTokenTelegram;
    private final String botNameTelegram;
    private final UserDataCache userDataCache = new UserDataCache();

    public Bot(String botToken, String botName) {
        this.botTokenTelegram = botToken;
        this.botNameTelegram = botName;
    }

    @Override
    public String getBotUsername() {
        return botNameTelegram;
    }

    @Override
    public String getBotToken() {
        return botTokenTelegram;
    }

    /**
     * Основной метод обработки полученных обновлений в боте.
     * В нём вызываются методы назначения состояния боту и переход по ним к разным командам.
     * Выводит сообщения в чат для пользователя
     *
     * @param update - обновление телеграма
     */
    @Override
    public void onUpdateReceived(Update update) {
        log.info("Получаем новое обновление. updateID: " + update.getUpdateId());
        String messageStr = update.hasCallbackQuery() ? update.getCallbackQuery().getData() : update.getMessage().getText();
        Long chatId = update.hasCallbackQuery() ? update.getCallbackQuery().getFrom().getId() : update.getMessage().getFrom().getId().longValue();
        BotStateEnum botState;
        try {
            if (update.hasCallbackQuery()) {
                CallbackQuery callbackQuery = update.getCallbackQuery();
                BotApiMethod<?> botApiMethod = GeneralButtonStateEnum.valueOfButtons(callbackQuery.getData()).getCallBackStateAndAnswer(callbackQuery, userDataCache);

                if (botApiMethod != null) {
                    execute(botApiMethod);
                }
            }
            HandleMessages handleMessages = new HandleMessages(userDataCache);
            botState = handleMessages.handleInputMessage(chatId, messageStr);
            if (!(update.hasCallbackQuery() && botState.equals(BotStateEnum.ASK_NAME))) {
                BotStateEnum stateEnum = userDataCache.getUsersCurrentBotState(chatId);
                Command command = stateEnum.getCommand();
                OutputParameters outputParameters = command.invoke(chatId, messageStr, userDataCache);
                if (outputParameters.getSendPhoto() != null) {
                    execute(outputParameters.getSendPhoto());
                }
                if (outputParameters.getSendMessage() != null) {
                    execute(outputParameters.getSendMessage());
                }
            }
        } catch (TelegramApiException e) {
            throw new TelegramException();
        }

    }
}

