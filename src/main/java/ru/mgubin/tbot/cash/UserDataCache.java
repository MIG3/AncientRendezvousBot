package ru.mgubin.tbot.cash;

import org.springframework.stereotype.Component;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotState;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDataCache implements DataCache
{
    private Map<Integer, BotState> usersBotStates = new HashMap<>();
    private Map<Integer, User> usersProfileData = new HashMap<>();

    @Override
    public void setUsersCurrentBotState(int userId, BotState botState)
    {
        usersBotStates.put(userId, botState);
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
}
