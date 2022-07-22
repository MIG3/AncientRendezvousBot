package ru.mgubin.tbot.command;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.mgubin.tbot.keyboard.InlineKeyboard;
import ru.mgubin.tbot.enums.SearchButtons;

public class SearchUserCommand implements Command
{
    @Override
    public OutputParameters invoke(Message message)
    {
        InlineKeyboard searchKeyboard = new InlineKeyboard();
        OutputParameters outputParameters = new OutputParameters();
        outputParameters.setSm(searchKeyboard.keyboard(message.getChatId(), "Кого вы ищете?", SearchButtons.values()));
        return outputParameters;
    }
}
