package ru.mgubin.tbot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.enums.BotStateEnum;
import ru.mgubin.tbot.enums.LikeStateEnum;

public class HandleMessages
{
    private final UserDataCache userDataCache;

    @Autowired
    public HandleMessages(UserDataCache userDataCache)
    {
        this.userDataCache = userDataCache;
    }

    public BotStateEnum handleInputMessage(Message message) throws TelegramApiException
    {
        String inputMsg = message.getText();
        int userId = message.getFrom().getId().intValue();
        BotStateEnum botState = userDataCache.getUsersCurrentBotState(userId);
        LikeStateEnum likeState = userDataCache.getUsersCurrentLikeState(userId);

        switch (inputMsg)
        {
            case "/start":
                botState = BotStateEnum.START;
                break;
            case "ПОМОЩЬ":
                botState = BotStateEnum.SHOW_HELP_MENU;
                break;
            case "ПОИСК":
                botState = BotStateEnum.SEARCH;
                break;
            case "/like":
                botState = BotStateEnum.NEXT_PROFILE;
                likeState = LikeStateEnum.LIKE;
                break;
            case "/dislike":
                botState = BotStateEnum.PREV_PROFILE;
                likeState = LikeStateEnum.DISLIKE;
                break;
            case "/next":
                botState = BotStateEnum.NEXT_CRUSH;
                break;
            case "/prev":
                botState = BotStateEnum.PREV_CRUSH;
                break;
            case "АНКЕТА":
                botState = BotStateEnum.CORRECT_PROFILE;
                break;
            case "ЛЮБИМЦЫ":
                botState = BotStateEnum.BROWSE_LOVERS;
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
