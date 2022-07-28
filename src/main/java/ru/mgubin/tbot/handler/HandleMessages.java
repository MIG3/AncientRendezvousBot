package ru.mgubin.tbot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.enums.BotStateEnum;
import ru.mgubin.tbot.enums.LikeStateEnum;

import static ru.mgubin.tbot.constant.Constants.*;

public class HandleMessages {
    private final UserDataCache userDataCache;

    @Autowired
    public HandleMessages(UserDataCache userDataCache) {
        this.userDataCache = userDataCache;
    }

    /**
     * Метод обработки начальной команды при запуске кода и нажатий на кнопки постоянного меню.
     * В зависимости от команды присваивается нужное состояние боту,
     * записываются данные в мапу кеша клиентов по ключу идентификатору
     * @param userId id клиента
     * @param inputMsg сообщение от кнопки или команды
     * @return состояние бота
     * @throws TelegramApiException ексепшен
     */
    public BotStateEnum handleInputMessage(Long userId, String inputMsg) throws TelegramApiException {
        BotStateEnum botState = userDataCache.getUsersCurrentBotState(userId);
        LikeStateEnum likeState = userDataCache.getUsersCurrentLikeState(userId);
        switch (inputMsg) {
            case START:
                botState = BotStateEnum.START;
                break;
            case HELP:
                botState = BotStateEnum.SHOW_HELP_MENU;
                break;
            case SEARCH:
                botState = BotStateEnum.SEARCH;
                break;
            case PROFILE:
                botState = BotStateEnum.CORRECT_PROFILE;
                break;
            case LOVERS:
                botState = BotStateEnum.BROWSE_LOVERS;
                break;
            default:
                botState = userDataCache.getUsersCurrentBotState(userId);
                likeState = userDataCache.getUsersCurrentLikeState(userId);
                break;
        }
        userDataCache.setUsersCurrentBotState(userId, botState);
        userDataCache.setUsersCurrentLikeState(userId, likeState);
        return botState;
    }
}
