package ru.mgubin.tbot.handlers;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.mgubin.tbot.command.*;
import ru.mgubin.tbot.enums.BotState;

public class HandleState
{
    public Command handleState(BotState state) throws TelegramApiException
    {
        switch (state)
        {
            case SHOW_HELP_MENU:
                return new Help();
            case ASK_GENDER:
                return new SelectGender();
            case SEARCH:
                return new SearchUser();
            case CORRECT_PROFILE:
                return new CorrectProfile();
            case START:
                return new StartBot();
        }
        return null;
    }
}
