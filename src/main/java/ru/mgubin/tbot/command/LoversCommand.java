package ru.mgubin.tbot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.mgubin.tbot.cash.UserDataCache;

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
        OutputParameters outputParameters = new OutputParameters();
        outputParameters.setSm(SendMessage.builder()
                .text("Ожидается в будущих обновлениях")
                .chatId(message.getChatId())
                .build());
        return outputParameters;
    }
}
