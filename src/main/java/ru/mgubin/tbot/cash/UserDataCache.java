package ru.mgubin.tbot.cash;

import org.springframework.stereotype.Component;
import ru.mgubin.tbot.entity.SearchProfile;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotState;
import ru.mgubin.tbot.enums.LikeState;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDataCache implements DataCache
{
    private Map<Long, BotState> usersBotStates = new HashMap<>();
    private Map<Long, LikeState> usersLikeStates = new HashMap<>();
    private Map<Long, User> usersProfileData = new HashMap<>();
    private Map<Long, SearchProfile> usersSearchListData = new HashMap<>();

    @Override
    public void setUsersCurrentBotState(long userId, BotState botState)
    {
        usersBotStates.put(userId, botState);
    }

    @Override
    public void setUsersCurrentLikeState(long userId, LikeState likeState)
    {
        usersLikeStates.put(userId, likeState);
    }
    @Override
    public BotState getUsersCurrentBotState(long userId)
    {
        BotState botState = usersBotStates.get(userId);
        if (botState == null)
        {
            botState = BotState.SHOW_HELP_MENU;
        }

        return botState;
    }
    @Override
    public LikeState getUsersCurrentLikeState(long userId)
    {
        LikeState likeState = usersLikeStates.get(userId);
        if (likeState == null)
        {
            likeState = LikeState.LIKE;
        }

        return likeState;
    }
    @Override
    public User getUserProfileData(long userId)
    {
        User userProfileData = usersProfileData.get(userId);
        if (userProfileData == null)
        {
            userProfileData = new User();
        }
        return userProfileData;
    }

    @Override
    public void saveUserProfileData(long userId, User userProfileData)
    {
        usersProfileData.put(userId, userProfileData);
    }

    @Override
    public void saveUserListData(long userId, SearchProfile listProfileData)
    {
        usersSearchListData.put(userId, listProfileData);
    }

    @Override
    public SearchProfile getUserListData(long userId)
    {
        SearchProfile listUsers = usersSearchListData.get(userId);
        return listUsers;
    }

}
