package ru.mgubin.tbot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotState;
import ru.mgubin.tbot.keyboard.InlineKeyboard;

public class AskInfoCommand implements Command
{
    private final UserDataCache userDataCache;

    @Autowired
    public AskInfoCommand(UserDataCache userDataCache)
    {
        this.userDataCache = userDataCache;
    }

    @Override
    public OutputParameters invoke(Message message)
    {
        OutputParameters outputParameters = new OutputParameters();
        User profileData = userDataCache.getUserProfileData(message.getFrom().getId().intValue());

        profileData.setInfo(message.getText());

        outputParameters.setSm(SendMessage.builder()
                .text("Когда Вы родились?")
                .chatId(message.getFrom().getId())
                .build());

        userDataCache.setUsersCurrentBotState(message.getFrom().getId().intValue(), BotState.ASK_BIRTHDAY);
        userDataCache.saveUserProfileData(message.getFrom().getId().intValue(), profileData);

        return outputParameters;
    }
}
