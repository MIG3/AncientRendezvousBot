package ru.mgubin.tbot.command.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.enums.BotStateEnum;

public class AskCrushCommand implements Command {
    private final UserDataCache userDataCache;

    @Autowired
    public AskCrushCommand(UserDataCache userDataCache) {
        this.userDataCache = userDataCache;
    }

    @Override
    public OutputParameters invoke(Long userId, String message) {
        OutputParameters outputParameters = new OutputParameters();
        userDataCache.setUsersCurrentBotState(userId, BotStateEnum.ASK_BIRTHDAY);
        return outputParameters;
    }
}
