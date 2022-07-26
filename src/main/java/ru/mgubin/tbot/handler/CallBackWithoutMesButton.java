package ru.mgubin.tbot.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.enums.BotStateEnum;

public class CallBackWithoutMesButton implements CallBackButton {
    /**
     * Метод обработки Callback'ов в случаях, без возврата сообщения
     *
     * @param buttonQuery   результат нажатия на кнопку
     * @param userDataCache кэш пользователя
     * @param enumName      название енама
     * @return пустое ответное сообщение
     */
    public BotApiMethod<?> buttonWithoutMessage(CallbackQuery buttonQuery, UserDataCache userDataCache, String enumName) {
        final int userId = buttonQuery.getFrom().getId().intValue();
        userDataCache.setUsersCurrentBotState(userId, BotStateEnum.valueOf(enumName));
        return null;
    }
}
