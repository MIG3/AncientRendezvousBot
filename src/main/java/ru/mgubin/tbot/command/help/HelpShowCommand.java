package ru.mgubin.tbot.command.help;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.entity.OutputParameters;

public class HelpShowCommand implements Command {

    @Override
    public OutputParameters invoke(Long userId, String message, UserDataCache userDataCache) {
        OutputParameters outputParameters = new OutputParameters();
        SendMessage sendMessage = SendMessage.builder()
                .text("Это бот знакомств в древнерусском стиле. В нём возможно:\n" +
                        "Заполнить анкету о себе, посмотреть её или изменить.\n" +
                        "Просматривать понравившиеся анкеты других пользователей.\n" +
                        "Выполнять поиск других пользователей, ставить любимку тем, кто понравился.\n"
                )
                .chatId(userId)
                .build();
        outputParameters.setSendMessage(sendMessage);
        return outputParameters;
    }
}
