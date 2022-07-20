package ru.mgubin.tbot.command;

import ru.mgubin.tbot.enums.ProfileButtons;
import ru.mgubin.tbot.keyboard.InlineKeyboard;

public class CorrectProfile implements Command
{
    @Override
    public OutputParameters invoke(Long chatId)
    {
        InlineKeyboard correctKeyboard = new InlineKeyboard();
        OutputParameters outputParameters = new OutputParameters();
        outputParameters.setSm(correctKeyboard.keyboard(chatId, "Что Вы хотите сделать с анкетой?", ProfileButtons.values()));
        return outputParameters;
    }
}
