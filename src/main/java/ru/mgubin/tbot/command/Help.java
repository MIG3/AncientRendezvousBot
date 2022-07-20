package ru.mgubin.tbot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.mgubin.tbot.keyboard.ReplyKeyboardMaker;

public class Help implements Command
{

    @Override
    public OutputParameters invoke(Long chatId)
    {
        SendMessage message = new SendMessage();
        OutputParameters outputParameters = new OutputParameters();
        message = SendMessage.builder()
                .text("Это бот знакомств в древнерусском стиле. В нём возможно:\n" +
                        "Заполнить анкету о себе, просматривать её, изменять или удалять.\n" +
                        "Просматривать понравившиеся анкеты других пользователей.\n" +
                        "Выполнять поиск других пользователей, ставить любимку тем, кто понравился."
                )
                .chatId(chatId)
                .build();
        outputParameters.setSm(message);
        return outputParameters;
    }
}
