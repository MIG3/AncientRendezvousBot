package ru.mgubin.tbot.command;

import ru.mgubin.tbot.enums.ProfileButtons;
import ru.mgubin.tbot.keyboard.InlineKeyboard;

public class SelectGender implements Command
{
    @Override
    public OutputParameters invoke(Long chatId)
    {
        InlineKeyboard gender = new InlineKeyboard();
        OutputParameters outputParameters = new OutputParameters();
        outputParameters.setSm(gender.keyboard(chatId, "Вы сударь иль сударыня?", ProfileButtons.values()));
        return outputParameters;
    }
}
