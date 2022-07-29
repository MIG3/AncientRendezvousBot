package ru.mgubin.tbot.command.profile;

import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.enums.BotStateEnum;

public class AskCrushCommand implements Command {


    /**
     * Переводит состояние бота для обработки последнего шага заполнения анкеты
     *
     * @param userId        id клиента
     * @param message       сообщение
     * @param userDataCache кэш данных пользователя
     * @return ответное сообщение
     */
    @Override
    public OutputParameters invoke(Long userId, String message, UserDataCache userDataCache) {
        OutputParameters outputParameters = new OutputParameters();
        userDataCache.setUsersCurrentBotState(userId, BotStateEnum.ASK_BIRTHDAY);
        return outputParameters;
    }
}
