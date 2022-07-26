package ru.mgubin.tbot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.enums.BotState;
import ru.mgubin.tbot.enums.LikeState;

public class HandleMessages
{
    private final UserDataCache userDataCache;

    @Autowired
    public HandleMessages(UserDataCache userDataCache)
    {
        this.userDataCache = userDataCache;
    }

    public BotState handleInputMessage(Message message) throws TelegramApiException
    {
        String inputMsg = message.getText();
        int userId = message.getFrom().getId().intValue();
        BotState botState = userDataCache.getUsersCurrentBotState(userId);
        LikeState likeState = userDataCache.getUsersCurrentLikeState(userId);

        switch (inputMsg)
        {
            case "/start":
                botState = BotState.START;
                break;
            case "ПОМОЩЬ":
                botState = BotState.SHOW_HELP_MENU;
                break;
            case "ПОИСК":
                botState = BotState.SEARCH;
                break;
            case "/like":
                botState = BotState.NEXT_PROFILE;
                likeState = LikeState.LIKE;
                break;
            case "/dislike":
                botState = BotState.PREV_PROFILE;
                likeState = LikeState.DISLIKE;
                break;
            case "АНКЕТА":
                botState = BotState.CORRECT_PROFILE;
                break;
            case "ЛЮБИМЦЫ":
                botState = BotState.BROWSE_LOVERS;
                break;
            default:
                botState = userDataCache.getUsersCurrentBotState(userId);
                likeState = userDataCache.getUsersCurrentLikeState(userId);
                break;
        }
        userDataCache.setUsersCurrentBotState(userId, botState);
        userDataCache.setUsersCurrentLikeState(userId, likeState);

        return botState;
    }
}
