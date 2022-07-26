package ru.mgubin.tbot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.enums.BotState;
import ru.mgubin.tbot.enums.GenderButtons;
import ru.mgubin.tbot.enums.SearchButtons;
import ru.mgubin.tbot.keyboard.InlineKeyboard;

@Service
public class AskCrushCommand implements Command
{
    private final UserDataCache userDataCache;

    @Autowired
    public AskCrushCommand(UserDataCache userDataCache)
    {
        this.userDataCache = userDataCache;
    }

    @Override
    public OutputParameters invoke(Message message)
    {
        OutputParameters outputParameters = new OutputParameters();
        userDataCache.setUsersCurrentBotState(message.getFrom().getId(), BotState.ASK_BIRTHDAY);
        return outputParameters;
    }
}
