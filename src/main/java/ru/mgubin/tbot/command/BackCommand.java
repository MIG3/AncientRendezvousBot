package ru.mgubin.tbot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.db.UserDB;
import ru.mgubin.tbot.entity.SearchProfile;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotState;
import ru.mgubin.tbot.printer.PrintProfile;

public class BackCommand implements Command
{
    private final UserDataCache userDataCache;

    @Autowired
    public BackCommand(UserDataCache userDataCache)
    {
        this.userDataCache = userDataCache;
    }
    @Override
    public OutputParameters invoke(Message message)
    {
        int userId = message.getFrom().getId().intValue();
        SearchProfile searchProfile = new SearchProfile();
        OutputParameters outputParameters = new OutputParameters();
        PrintProfile profile = new PrintProfile();
        UserDB userDB = new UserDB();
        User user = new User();

        searchProfile = userDataCache.getUserListData(userId);
        int pos = searchProfile.getNumberProfile();
        searchProfile.setNumberProfile(pos + 1);
        user = searchProfile.getUserList().get(searchProfile.getNumberProfile());

        outputParameters.setSp(profile.sendPhoto(       // печатаем изображение, передавая параметрами
                message.getChatId(),                    // id чата
                user));

        userDataCache.setUsersCurrentBotState(userId, BotState.CHOICE_PREVorNEXT_BUTTON);

        return outputParameters;
    }
}
