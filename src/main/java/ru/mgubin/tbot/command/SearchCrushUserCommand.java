package ru.mgubin.tbot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.db.UserDB;
import ru.mgubin.tbot.entity.CrushProfile;
import ru.mgubin.tbot.entity.PersonCrush;
import ru.mgubin.tbot.entity.SearchProfile;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotState;
import ru.mgubin.tbot.service.GenerateLabel;
import ru.mgubin.tbot.service.PrintProfile;

import java.util.List;

public class SearchCrushUserCommand implements Command
{
    private final UserDataCache userDataCache;

    @Autowired
    public SearchCrushUserCommand(UserDataCache userDataCache)
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
        SearchProfile crushProfile = new SearchProfile();
        CrushProfile crushes = new CrushProfile();
        User user = new User();

        crushProfile.fillUserList(userDB.getLovers(userId)); // вызов метода, который ввозвращает список всех любимцев (users)
        userDataCache.saveUserListData(userId, crushProfile); // и записывает в мапу по id пользователя
        user = crushProfile.getUserList().get(crushProfile.getNumberProfile());

        GenerateLabel generateLabel = new GenerateLabel(userDataCache);
        String label = generateLabel.labelFromPicture(userId, crushProfile.getUserList().get(0).getId());

        outputParameters.setSp(profile.sendPhoto(       // печатаем изображение, передавая параметрами
                message.getChatId(),                    // id чата
                user,                                   // 0 элемент списка анкет (пользователей)
                label));                                // статус любимок

        userDataCache.setUsersCurrentBotState(userId, BotState.CHOICE_PREVorNEXT_BUTTON); // меняю состояние, чтобы вызвать следующую команду выдачи кнопок перебора анкет

        return outputParameters;
    }
}
