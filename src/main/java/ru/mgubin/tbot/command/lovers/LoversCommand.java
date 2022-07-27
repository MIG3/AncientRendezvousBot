package ru.mgubin.tbot.command.lovers;

import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.enums.PrevNextButtonEnum;
import ru.mgubin.tbot.keyboard.InlineKeyboard;

public class LoversCommand implements Command {
    private final UserDataCache userDataCache;

    public LoversCommand(UserDataCache userDataCache) {
        this.userDataCache = userDataCache;
    }

    @Override
    public OutputParameters invoke(Long userId, String message) {
        InlineKeyboard searchKeyboard = new InlineKeyboard();
        OutputParameters outputParameters = new OutputParameters();
        outputParameters.setSm(searchKeyboard.keyboard(userId, "Вперёд или назад", PrevNextButtonEnum.valuesPrevNextButtons()));
        return outputParameters;
    }
}
