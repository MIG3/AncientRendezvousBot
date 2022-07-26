package ru.mgubin.tbot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.db.UserDB;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.printer.PrintProfile;

public class BrowsProfile implements Command
{
    private final UserDataCache userDataCache;

    @Autowired
    public BrowsProfile(UserDataCache userDataCache)
    {
        this.userDataCache = userDataCache;
    }

    @Override
    public OutputParameters invoke(Message message)
    {
        int userId = message.getFrom().getId().intValue();
        OutputParameters outputParameters = new OutputParameters();
        PrintProfile profile = new PrintProfile();
        UserDB userDB = new UserDB();

        User profileData = userDB.getUser(userId);

        userDataCache.saveUserProfileData(userId, profileData);
        outputParameters.setSp(profile.sendPhoto(message.getChatId(), profileData));

        return outputParameters;
    }
}
