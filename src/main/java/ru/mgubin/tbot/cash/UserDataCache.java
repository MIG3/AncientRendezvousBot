package ru.mgubin.tbot.cash;

import org.springframework.stereotype.Component;
import ru.mgubin.tbot.entity.CrushProfile;
import ru.mgubin.tbot.entity.SearchProfile;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotStateEnum;
import ru.mgubin.tbot.enums.LikeStateEnum;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDataCache implements DataCache
{
    private Map<Long, BotStateEnum> usersBotStates = new HashMap<>();
    private Map<Long, LikeStateEnum> usersLikeStates = new HashMap<>();
    private Map<Long, User> usersProfileData = new HashMap<>();
    private Map<Long, SearchProfile> usersSearchListData = new HashMap<>();
    private Map<Long, CrushProfile> usersCrushListData = new HashMap<>();

    @Override
    public void setUsersCurrentBotState(long userId, BotStateEnum botState)
    {
        usersBotStates.put(userId, botState);
    }

    @Override
    public void setUsersCurrentLikeState(long userId, LikeStateEnum likeState)
    {
        usersLikeStates.put(userId, likeState);
    }
    @Override
    public BotStateEnum getUsersCurrentBotState(long userId)
    {
        BotStateEnum botState = usersBotStates.get(userId);
        if (botState == null)
        {
            botState = BotStateEnum.SHOW_HELP_MENU;
        }

        return botState;
    }
    @Override
    public LikeStateEnum getUsersCurrentLikeState(long userId)
    {
        LikeStateEnum likeState = usersLikeStates.get(userId);
        if (likeState == null)
        {
            likeState = LikeStateEnum.LIKE;
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

    @Override
    public void saveCrushListData(long userId, CrushProfile listCrushData)
    {
        usersCrushListData.put(userId, listCrushData);
    }

    @Override
    public CrushProfile getUserCrushData(long userId)
    {
        CrushProfile listCrashes = usersCrushListData.get(userId);
        return listCrashes;
    }

}
