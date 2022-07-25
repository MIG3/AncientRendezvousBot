package ru.mgubin.tbot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotState;
import ru.mgubin.tbot.enums.SearchButtons;
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
        InlineKeyboard gender = new InlineKeyboard();
        OutputParameters outputParameters = new OutputParameters();
        User profileData = userDataCache.getUserProfileData(message.getFrom().getId().intValue());

        profileData.setDescription(message.getText());

        outputParameters.setSm(gender.keyboard(message.getChatId(), "Кого Вы хотите искать в будущем?", SearchButtons.values()));
        userDataCache.setUsersCurrentBotState(message.getFrom().getId().intValue(), BotState.ASK_CRUSH);
        userDataCache.saveUserProfileData(message.getFrom().getId().intValue(), profileData);

        return outputParameters;
    }
}
