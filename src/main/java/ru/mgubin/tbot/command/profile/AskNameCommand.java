package ru.mgubin.tbot.command.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotStateEnum;

public class AskNameCommand implements Command {
    private final UserDataCache userDataCache;

    @Autowired
    public AskNameCommand(UserDataCache userDataCache) {
        this.userDataCache = userDataCache;
    }

    @Override
    public OutputParameters invoke(Long userId, String message) {
        OutputParameters outputParameters = new OutputParameters();
        User profileData = userDataCache.getUserProfileData(userId);
        profileData.setFullName(message);
        outputParameters.setSm(SendMessage.builder()
                .text("Напишите пару слов о себе")
                .chatId(userId)
                .build());
        userDataCache.setUsersCurrentBotState(userId, BotStateEnum.ASK_INFO);
        userDataCache.saveUserProfileData(userId, profileData);
        return outputParameters;
    }
}
