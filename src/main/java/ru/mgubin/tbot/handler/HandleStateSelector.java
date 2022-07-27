package ru.mgubin.tbot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.command.StartBotCommand;
import ru.mgubin.tbot.command.help.HelpShowCommand;
import ru.mgubin.tbot.command.lovers.BackCrushCommand;
import ru.mgubin.tbot.command.lovers.NextCrushCommand;
import ru.mgubin.tbot.command.lovers.SearchCrushUserCommand;
import ru.mgubin.tbot.command.profile.*;
import ru.mgubin.tbot.command.search.BackCommand;
import ru.mgubin.tbot.command.search.NextCommand;
import ru.mgubin.tbot.command.search.SearchUserCommand;
import ru.mgubin.tbot.enums.BotStateEnum;

public class HandleStateSelector {
    private final UserDataCache userDataCache;

    @Autowired
    public HandleStateSelector(UserDataCache userDataCache) {
        this.userDataCache = userDataCache;
    }

    public Command handleStateSelector(BotStateEnum state) throws TelegramApiException {
        switch (state) {
            case SHOW_HELP_MENU:
                return new HelpShowCommand();
            case ASK_GENDER:
                return new AskGenderCommand(userDataCache);
            case ASK_NAME:
                return new AskNameCommand(userDataCache);
            case ASK_INFO:
                return new AskInfoCommand(userDataCache);
            case ASK_CRUSH:
                return new AskCrushCommand(userDataCache);
            case ASK_BIRTHDAY:
                return new AskBirthdayCommand(userDataCache);
            case SEARCH:
                return new SearchUserCommand(userDataCache);
            case NEXT_PROFILE:
                return new NextCommand(userDataCache);
            case PREV_PROFILE:
                return new BackCommand(userDataCache);
            case NEXT_CRUSH:
                return new NextCrushCommand(userDataCache);
            case PREV_CRUSH:
                return new BackCrushCommand(userDataCache);
            case CORRECT_PROFILE:
                return new CorrectProfileCommand(userDataCache);
            case BROWSE_PROFILE:
                return new BrowsProfile(userDataCache);
            case BROWSE_LOVERS:
                return new SearchCrushUserCommand(userDataCache);
            case START:
                return new StartBotCommand();
        }
        return null;
    }
}
