package ru.mgubin.tbot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.enums.BotStateEnum;
import ru.mgubin.tbot.enums.GenderButtonsEnum;
import ru.mgubin.tbot.keyboard.InlineKeyboard;

@Service
public class AskGenderCommand implements Command
{
    private final UserDataCache userDataCache;

    @Autowired
    public AskGenderCommand(UserDataCache userDataCache)
    {
        this.userDataCache = userDataCache;
    }

    @Override
    public OutputParameters invoke(Message message)
    {
        InlineKeyboard gender = new InlineKeyboard();
        OutputParameters outputParameters = new OutputParameters();
        outputParameters.setSm(gender.keyboard(message.getChatId(), "Вы сударь иль сударыня?", GenderButtonsEnum.valuesGenderButtons()));
        userDataCache.setUsersCurrentBotState(message.getFrom().getId(), BotStateEnum.ASK_NAME);
        return outputParameters;
    }
}
