package ru.mgubin.tbot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.db.UserDB;
import ru.mgubin.tbot.entity.PersonCrush;
import ru.mgubin.tbot.entity.SearchProfile;
import ru.mgubin.tbot.enums.BotStateEnum;
import ru.mgubin.tbot.service.PrintProfile;

public class NextCommand implements Command
{
    private final UserDataCache userDataCache;

    @Autowired
    public NextCommand(UserDataCache userDataCache)
    {
        this.userDataCache = userDataCache;
    }

    @Override
    public OutputParameters invoke(Message message)
    {
        long userId = message.getFrom().getId();
        SearchProfile searchProfile = new SearchProfile();
        OutputParameters outputParameters = new OutputParameters();
        PrintProfile profile = new PrintProfile();
        UserDB userDB = new UserDB();
        PersonCrush lovers = new PersonCrush();

        searchProfile = userDataCache.getUserListData(userId);
        int lengthUserList = searchProfile.getUserList().size();
        int pos = searchProfile.getNumberProfile();

        if (lengthUserList <= pos + 1)
        {
            searchProfile.setNumberProfile(0);
            lovers.setCrushId(searchProfile.getUserList().get(lengthUserList - 1).getId()); // последний элемент списка
        } else
        {
            searchProfile.setNumberProfile(pos + 1);
            lovers.setCrushId(searchProfile.getUserList().get(pos).getId()); // предыдущий элемент списка
        }

        lovers.setUserId(userId);
        userDB.makeLikeToUser(lovers);

        outputParameters.setSp(profile.sendPhoto(                                       // печатаем изображение, передавая параметрами
                message.getChatId(),                                                    // id чата
                searchProfile.getUserList().get(searchProfile.getNumberProfile()), ""));    // анкета пользователя по её номеру

        userDataCache.setUsersCurrentBotState(userId, BotStateEnum.CHOICE_PREVorNEXT_BUTTON);

        return outputParameters;
    }
}
