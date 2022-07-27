package ru.mgubin.tbot.command.search;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.db.UserDB;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.entity.PersonCrush;
import ru.mgubin.tbot.entity.SearchProfile;
import ru.mgubin.tbot.enums.BotStateEnum;
import ru.mgubin.tbot.enums.LikeDislikeButtonEnum;
import ru.mgubin.tbot.keyboard.InlineKeyboard;
import ru.mgubin.tbot.service.PrintProfile;

public class BackCommand implements Command {
    private final UserDataCache userDataCache;

    @Autowired
    public BackCommand(UserDataCache userDataCache) {
        this.userDataCache = userDataCache;
    }

    @Override
    public OutputParameters invoke(Long userId, String message) {
        SearchProfile searchProfile = new SearchProfile();
        OutputParameters outputParameters = new OutputParameters();
        PrintProfile profile = new PrintProfile();
        UserDB userDB = new UserDB();
        PersonCrush lovers = new PersonCrush();
        searchProfile = userDataCache.getUserListData(userId);
        int pos = searchProfile.getNumberProfile();
        int lengthUserList = searchProfile.getUserList().size();
        if (lengthUserList <= pos + 1) {
            searchProfile.setNumberProfile(0);
            lovers.setCrushId(searchProfile.getUserList().get(lengthUserList - 1).getId()); // последний элемент списка
        } else {
            searchProfile.setNumberProfile(pos + 1);
            lovers.setCrushId(searchProfile.getUserList().get(pos).getId()); // предыдущий элемент списка
        }
        lovers.setUserId(userId);
        userDB.removeLikeToUser(lovers);
        outputParameters.setSp(profile.sendPhoto(       // печатаем изображение, передавая параметрами
                userId,                    // id чата
                searchProfile.getUserList().get(searchProfile.getNumberProfile()),
                ""));
        outputParameters.setSm(new InlineKeyboard().keyboard(userId, "Если нравится, нажми вперед, иначе назад", LikeDislikeButtonEnum.valuesPrevNextButtons()));
        return outputParameters;
    }
}
