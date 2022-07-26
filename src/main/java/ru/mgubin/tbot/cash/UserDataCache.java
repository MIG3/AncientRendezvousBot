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
    private Map<Integer, BotState> usersBotStates = new HashMap<>();
    private Map<Integer, LikeState> usersLikeStates = new HashMap<>();
    private Map<Integer, User> usersProfileData = new HashMap<>();
    private Map<Integer, SearchProfile> usersSearchListData = new HashMap<>();

    @Override
    public void setUsersCurrentBotState(int userId, BotState botState)
    {
        usersBotStates.put(userId, botState);
    }

    @Override
    public void setUsersCurrentLikeState(int userId, LikeState likeState)
    {
        usersLikeStates.put(userId, likeState);
    }
    @Override
    public BotState getUsersCurrentBotState(int userId)
    {
        BotState botState = usersBotStates.get(userId);
        if (botState == null)
        {
            botState = BotState.SHOW_HELP_MENU;
        }

        return botState;
    }
    @Override
    public LikeState getUsersCurrentLikeState(int userId)
    {
        LikeState likeState = usersLikeStates.get(userId);
        if (likeState == null)
        {
            likeState = LikeState.LIKE;
        }

        return likeState;
    }
    @Override
    public User getUserProfileData(int userId)
    {
        User userProfileData = usersProfileData.get(userId);
        if (userProfileData == null)
        {
            userProfileData = new User();
        }
        return userProfileData;
    }

    @Override
    public void saveUserProfileData(int userId, User userProfileData)
    {
        usersProfileData.put(userId, userProfileData);
    }

    @Override
    public void saveUserListData(int userId, SearchProfile listProfileData)
    {
        usersSearchListData.put(userId, listProfileData);
    }

    @Override
    public SearchProfile getUserListData(int userId)
    {
        SearchProfile listUsers = usersSearchListData.get(userId);
        return listUsers;
    }

}
