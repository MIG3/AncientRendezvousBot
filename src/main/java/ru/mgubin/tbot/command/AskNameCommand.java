package ru.mgubin.tbot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotState;

@Service
public class AskNameCommand implements Command
{
    private final UserDataCache userDataCache;

    @Autowired
    public AskNameCommand(UserDataCache userDataCache)
    {
        this.userDataCache = userDataCache;
    }

    @Override
    public OutputParameters invoke(Message message)
    {
        int chatId = message.getFrom().getId().intValue();
        OutputParameters outputParameters = new OutputParameters();
        User profileData = userDataCache.getUserProfileData(message.getFrom().getId().intValue());

        profileData.setFullName(message.getText());

        outputParameters.setSm(SendMessage.builder()
                .text("Напишите о себе")
                .chatId(message.getFrom().getId())
                .build());

        userDataCache.setUsersCurrentBotState(chatId, BotState.ASK_INFO);
        userDataCache.saveUserProfileData(chatId, profileData);

        return outputParameters;
    }
}
