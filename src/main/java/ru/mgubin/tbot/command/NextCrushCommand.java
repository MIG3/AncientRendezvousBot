package ru.mgubin.tbot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.db.UserDB;
import ru.mgubin.tbot.entity.SearchProfile;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotStateEnum;
import ru.mgubin.tbot.service.GenerateLabel;
import ru.mgubin.tbot.service.PrintProfile;

public class NextCrushCommand implements Command
{
    private final UserDataCache userDataCache;

    @Autowired
    public NextCrushCommand(UserDataCache userDataCache)
    {
        this.userDataCache = userDataCache;
    }

    @Override
    public OutputParameters invoke(Message message)
    {
        long userId = message.getFrom().getId();
        OutputParameters outputParameters = new OutputParameters();
        UserDB userDB = new UserDB();
        PrintProfile profile = new PrintProfile();
        SearchProfile crushProfile = userDataCache.getUserListData(userId); // вызов метода, который ввозвращает список всех любимцев (users)

        int lengthUserList = crushProfile.getUserList().size();
        int pos = crushProfile.getNumberProfile();
        int index;

        if (lengthUserList <= pos + 1)
        {
            index = 0;
        } else
        {
            index = pos + 1;
        }
        crushProfile.setNumberProfile(index);
        GenerateLabel generateLabel = new GenerateLabel(userDataCache);
        String label = generateLabel.labelFromPicture(userId, crushProfile.getUserList().get(index).getId());

        // печатаем изображение, передавая параметрами
        // id чата
        // 0 элемент списка анкет (пользователей)
        outputParameters.setSp(profile.sendPhoto(       // печатаем изображение, передавая параметрами
                message.getChatId(),                    // id чата
                crushProfile.getUserList().get(crushProfile.getNumberProfile()),
                label));                                // статус любимок


        userDataCache.setUsersCurrentBotState(userId, BotStateEnum.CHOICE_PREVorNEXT_BUTTON);

        return outputParameters;
    }
}
