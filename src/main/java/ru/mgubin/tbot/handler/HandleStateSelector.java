package ru.mgubin.tbot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.*;
import ru.mgubin.tbot.enums.BotState;

@Service
public class HandleStateSelector
{
    private final UserDataCache userDataCache;
    @Autowired
    public HandleStateSelector(UserDataCache userDataCache)
    {
        this.userDataCache = userDataCache;
    }

    public Command handleStateSelector(BotState state) throws TelegramApiException
    {
        switch (state)
        {
            case SHOW_HELP_MENU:
                return new HelpShowCommand();
            case ASK_GENDER:
                return new AskGenderCommand(userDataCache);
            case ASK_NAME:
                return new AskNameCommand(userDataCache);
            case ASK_INFO:
                return new AskInfoCommand(userDataCache);
            case ASK_BIRTHDAY:
                return new AskBirthdayCommand(userDataCache);
            case SEARCH:
                return new SearchUserCommand();
            case CORRECT_PROFILE:
                return new CorrectProfileCommand(userDataCache);
            case BROWSE_LOVERS:
                return new LoversCommand(userDataCache);
            case START:
                return new StartBotCommand();
        }
        return null;
    }
}
