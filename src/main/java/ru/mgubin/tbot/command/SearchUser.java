package ru.mgubin.tbot.command;

import ru.mgubin.tbot.keyboard.InlineKeyboard;
import ru.mgubin.tbot.enums.SearchButtons;

public class SearchUser implements Command
{
    @Override
    public OutputParameters invoke(Long chatId)
    {
        InlineKeyboard searchKeyboard = new InlineKeyboard();
        OutputParameters outputParameters = new OutputParameters();
        outputParameters.setSm(searchKeyboard.keyboard(chatId, "Кого вы ищете?", SearchButtons.values()));
        return outputParameters;
    }
}
