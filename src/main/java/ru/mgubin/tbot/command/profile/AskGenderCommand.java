package ru.mgubin.tbot.command.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.enums.BotStateEnum;
import ru.mgubin.tbot.enums.GenderButtonsEnum;
import ru.mgubin.tbot.keyboard.InlineKeyboard;

public class AskGenderCommand implements Command {
    private final UserDataCache userDataCache;

    @Autowired
    public AskGenderCommand(UserDataCache userDataCache) {
        this.userDataCache = userDataCache;
    }

    @Override
    public OutputParameters invoke(Long userId, String message) {
        InlineKeyboard gender = new InlineKeyboard();
        OutputParameters outputParameters = new OutputParameters();
        outputParameters.setSm(gender.keyboard(userId, "Вы сударь иль сударыня?", GenderButtonsEnum.valuesGenderButtons()));
        userDataCache.setUsersCurrentBotState(userId, BotStateEnum.ASK_NAME);
        return outputParameters;
    }
}
