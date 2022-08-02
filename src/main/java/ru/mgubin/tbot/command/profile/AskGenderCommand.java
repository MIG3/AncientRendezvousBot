package ru.mgubin.tbot.command.profile;

import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.enums.BotStateEnum;
import ru.mgubin.tbot.enums.GenderEnum;
import ru.mgubin.tbot.keyboard.InlineKeyboard;

public class AskGenderCommand implements Command {

    /**
     * Выводит вопрос о гендере клиента и переводит состояние бота для обработки следующего шага
     * Состояние бота меняется на ASK_NAME - ввод своего имени
     *
     * @param userId        id клиента
     * @param message       сообщение
     * @param userDataCache кэш данных пользователя
     * @return кнопки для выбора гендера
     */
    @Override
    public OutputParameters invoke(Long userId, String message, UserDataCache userDataCache) {
        InlineKeyboard gender = new InlineKeyboard();
        OutputParameters outputParameters = new OutputParameters();
        outputParameters.setSendMessage(gender.keyboard(userId, "Вы сударь иль сударыня?", GenderEnum.valuesGenderButtons()));
        userDataCache.setUsersCurrentBotState(userId, BotStateEnum.ASK_NAME);
        return outputParameters;
    }
}
