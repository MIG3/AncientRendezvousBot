package ru.mgubin.tbot.command.profile;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.enums.BotStateEnum;
import ru.mgubin.tbot.enums.ProfileButtonsEnum;
import ru.mgubin.tbot.keyboard.InlineKeyboard;

public class CorrectProfileCommand implements Command {
    private final UserDataCache userDataCache;

    @Autowired
    public CorrectProfileCommand(UserDataCache userDataCache) {
        this.userDataCache = userDataCache;
    }

    /**
     * Выводятся кнопки при нажатии кнопки "АНКЕТА" в меню
     * Состояние бота меняется на CORRECT_PROFILE - изменение анкеты
     * @param userId id клиента
     * @param message сообщение
     * @return кнопки для выбора взаимодействия с анкетой
     */
    @Override
    public OutputParameters invoke(Long userId, String message) {
        InlineKeyboard correctKeyboard = new InlineKeyboard();
        OutputParameters outputParameters = new OutputParameters();
        userDataCache.setUsersCurrentBotState(userId, BotStateEnum.CORRECT_PROFILE);
        outputParameters.setSendMessage(correctKeyboard.keyboard(userId, "Что Вы хотите сделать с анкетой?", ProfileButtonsEnum.valuesProfileButtons()));
        return outputParameters;
    }
}
