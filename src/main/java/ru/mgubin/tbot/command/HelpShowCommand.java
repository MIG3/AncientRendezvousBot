package ru.mgubin.tbot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class HelpShowCommand implements Command
{

    @Override
    public OutputParameters invoke(Message message)
    {
        SendMessage message2 = new SendMessage();
        OutputParameters outputParameters = new OutputParameters();
        message2 = SendMessage.builder()
                .text("Это бот знакомств в древнерусском стиле. В нём возможно:\n" +
                        "Заполнить анкету о себе, посмотреть её или изменить.\n" +
                        "Просматривать понравившиеся анкеты других пользователей.\n" +
                        "Для пролистывания анкет любимцев есть команды: /next и /prev." +
                        "Выполнять поиск других пользователей, ставить любимку тем, кто понравился.\n" +
                        "Для постановки лайка или удаления его есть команды: /like и /dislike."
                )
                .chatId(message.getChatId())
                .build();
        outputParameters.setSm(message2);
        return outputParameters;
    }
}
