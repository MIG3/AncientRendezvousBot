package ru.mgubin.tbot.command.search;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.db.UserDB;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.entity.PersonCrush;
import ru.mgubin.tbot.entity.SearchProfile;
import ru.mgubin.tbot.enums.LikeDislikeButtonEnum;
import ru.mgubin.tbot.keyboard.InlineKeyboard;
import ru.mgubin.tbot.service.PrintProfile;

public class NextCommand implements Command {
    private final UserDataCache userDataCache;

    @Autowired
    public NextCommand(UserDataCache userDataCache) {
        this.userDataCache = userDataCache;
    }

    /**
     * Метод перебора анкет вперёд
     * @param userId
     * @param message
     * @return
     */
    @Override
    public OutputParameters invoke(Long userId, String message) {
        OutputParameters outputParameters = new OutputParameters();
        PrintProfile profile = new PrintProfile();
        UserDB userDB = new UserDB();
        PersonCrush lovers = new PersonCrush();
        SearchProfile searchProfile = userDataCache.getUserListData(userId);
        int lengthUserList = searchProfile.getUserList().size();
        int pos = searchProfile.getNumberProfile();
        if (lengthUserList <= pos + 1) {
            searchProfile.setNumberProfile(0);
            lovers.setCrushId(searchProfile.getUserList().get(lengthUserList - 1).getId()); // последний элемент списка
        } else {
            searchProfile.setNumberProfile(pos + 1);
            lovers.setCrushId(searchProfile.getUserList().get(pos).getId()); // предыдущий элемент списка
        }
        lovers.setUserId(userId);
        userDB.makeLikeToUser(lovers);
        outputParameters.setSp(profile.sendPhoto(                                       // печатаем изображение, передавая параметрами
                userId,                                                    // id чата
                searchProfile.getUserList().get(searchProfile.getNumberProfile()), ""));    // анкета пользователя по её номеру
        outputParameters.setSm(new InlineKeyboard().keyboard(userId, "Если нравится, нажми вперед, иначе назад", LikeDislikeButtonEnum.valuesPrevNextButtons()));
        return outputParameters;
    }
}
