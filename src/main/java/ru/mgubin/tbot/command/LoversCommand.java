package ru.mgubin.tbot.command;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.enums.PrevNextButtonEnum;
import ru.mgubin.tbot.keyboard.InlineKeyboard;

public class LoversCommand implements Command
{
    private final UserDataCache userDataCache;
    public LoversCommand(UserDataCache userDataCache)
    {
        this.userDataCache = userDataCache;
    }

    @Override
    public OutputParameters invoke(Message message)
    {
        InlineKeyboard searchKeyboard = new InlineKeyboard();
        OutputParameters outputParameters = new OutputParameters();
        outputParameters.setSm(searchKeyboard.keyboard(message.getChatId(), "Вперёд или назад", PrevNextButtonEnum.valuesPrevNextButtons()));
        return outputParameters;
    }
}
