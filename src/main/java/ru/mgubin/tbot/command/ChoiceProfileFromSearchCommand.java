package ru.mgubin.tbot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.enums.PrevNextButtons;
import ru.mgubin.tbot.keyboard.InlineKeyboard;

public class ChoiceProfileFromSearchCommand implements Command
{
    private final UserDataCache userDataCache;

    @Autowired
    public ChoiceProfileFromSearchCommand(UserDataCache userDataCache)
    {
        this.userDataCache = userDataCache;
    }

    @Override
    public OutputParameters invoke(Message message)
    {
        InlineKeyboard prevOrNext = new InlineKeyboard();
        OutputParameters outputParameters = new OutputParameters();


        outputParameters.setSm(prevOrNext.keyboard(message.getChatId(), "Следующая или предыдущая анкеты", PrevNextButtons.values()));

        return outputParameters;
    }
}
