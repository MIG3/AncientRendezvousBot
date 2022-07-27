package ru.mgubin.tbot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.db.UserDB;
import ru.mgubin.tbot.entity.SearchProfile;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotStateEnum;
import ru.mgubin.tbot.service.PrintProfile;

public class SearchUserCommand implements Command
{
    private final UserDataCache userDataCache;

    @Autowired
    public SearchUserCommand(UserDataCache userDataCache)
    {
        this.userDataCache = userDataCache;
    }

    @Override
    public OutputParameters invoke(Message message)
    {
        long userId = message.getFrom().getId().intValue();
        OutputParameters outputParameters = new OutputParameters();
        UserDB userDB = new UserDB();
        PrintProfile profile = new PrintProfile();
        SearchProfile searchProfile = new SearchProfile();
        User user = new User();

        searchProfile.fillUserList(userDB.getUsersByGender(userId)); // вызов метода, который ввозвращает список анкет (users)

        userDataCache.saveUserListData(userId, searchProfile); // и записывает в мапу по id пользователя
        user = searchProfile.getUserList().get(searchProfile.getNumberProfile());

        outputParameters.setSp(profile.sendPhoto(       // печатаем изображение, передавая параметрами
                message.getChatId(),                    // id чата
                user,                                   // 0 элемент списка анкет (пользователей)
                ""));

        userDataCache.setUsersCurrentBotState(userId, BotStateEnum.CHOICE_PREVorNEXT_BUTTON); // меняю состояние, чтобы вызвать следующую команду выдачи кнопок перебора анкет

        return outputParameters;
    }
}
