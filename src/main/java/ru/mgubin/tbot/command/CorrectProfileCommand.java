package ru.mgubin.tbot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.enums.BotState;
import ru.mgubin.tbot.enums.ProfileButtons;
import ru.mgubin.tbot.keyboard.InlineKeyboard;

public class CorrectProfileCommand implements Command
{
    private final UserDataCache userDataCache;

    @Autowired
    public CorrectProfileCommand(UserDataCache userDataCache)
    {
        this.userDataCache = userDataCache;
    }

    @Override
    public OutputParameters invoke(Message message)
    {
        long userId = message.getFrom().getId();
        InlineKeyboard correctKeyboard = new InlineKeyboard();
        OutputParameters outputParameters = new OutputParameters();

        userDataCache.setUsersCurrentBotState(userId, BotState.CORRECT_PROFILE);

        outputParameters.setSm(correctKeyboard.keyboard(message.getChatId(), "Что Вы хотите сделать с анкетой?", ProfileButtons.values()));

        return outputParameters;
    }
}
