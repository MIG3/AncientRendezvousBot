package ru.mgubin.tbot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.enums.BotStateEnum;
import ru.mgubin.tbot.exception.TelegramException;
import ru.mgubin.tbot.handler.CallBackAction;
import ru.mgubin.tbot.handler.HandleMessages;
import ru.mgubin.tbot.handler.HandleStateSelector;

@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {
    private final String BOT_TOKEN_TELEGRAM;
    private final String BOT_NAME_TELEGRAM;
    private final UserDataCache userDataCache = new UserDataCache();

    @Autowired
    public Bot(String botToken, String botName) {
        this.BOT_TOKEN_TELEGRAM = botToken;
        this.BOT_NAME_TELEGRAM = botName;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME_TELEGRAM;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN_TELEGRAM;
    }

    /**
     * Основной метод обработки полученных обновлений в боте.
     * В нём вызываются методы назначения состояния боту и переход по ним к разным командам.
     * Вызов метода обработки Callback'ов, если было нажатие на кнопки.
     * Выводит сообщения в чат для пользователя
     *
     * @param update
     */
    @Override
    public void onUpdateReceived(Update update) {
        log.info("Получаем новое обновление. updateID: " + update.getUpdateId());
        String messageStr = update.hasCallbackQuery() ? update.getCallbackQuery().getData() : update.getMessage().getText();
        Long chatId = update.hasCallbackQuery() ? update.getCallbackQuery().getFrom().getId().longValue() : update.getMessage().getFrom().getId().longValue();
        BotStateEnum botState;
        if (update.hasMessage() || update.hasCallbackQuery()) {
            try {
                if (update.hasCallbackQuery()) {
                    CallBackAction callBackAction = new CallBackAction(userDataCache);
                    CallbackQuery callbackQuery = update.getCallbackQuery();
                    BotApiMethod botApiMethod = callBackAction.processCallbackQuery(callbackQuery);
                    if (botApiMethod != null) {
                        execute(botApiMethod);
                    }
                }
                HandleMessages handleMessages = new HandleMessages(userDataCache);
                HandleStateSelector state = new HandleStateSelector(userDataCache);
                botState = handleMessages.handleInputMessage(chatId, messageStr);
                if (!(update.hasCallbackQuery() && botState.equals(BotStateEnum.ASK_NAME))) {
                    Command command = state.handleStateSelector(botState);
                    OutputParameters outputParameters = command.invoke(chatId, messageStr);
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
}

