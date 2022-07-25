package ru.mgubin.tbot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class SearchUserCommand implements Command
{
    @Override
    public OutputParameters invoke(Message message)
    {
        OutputParameters outputParameters = new OutputParameters();
        outputParameters.setSm(SendMessage.builder()
                .text("Ожидается в будущих обновлениях"
                )
                .chatId(message.getChatId())
                .build());

        return outputParameters;
    }
}
