package ru.mgubin.tbot.command.search;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.db.UserDB;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.entity.SearchProfile;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.LikeDislikeButtonEnum;
import ru.mgubin.tbot.keyboard.InlineKeyboard;
import ru.mgubin.tbot.service.PrintProfile;

public class SearchUserCommand implements Command {
    private final UserDataCache userDataCache;

    @Autowired
    public SearchUserCommand(UserDataCache userDataCache) {
        this.userDataCache = userDataCache;
    }

    /**
     * Метод, который вызывается при нажатии кнопки "ПОИСК".
     * В нём получает список анкет из БД, записывает в мапу по id пользователя,
     * после вызывается метод печати первой анкеты из списка.
     * Выводятся кнопки для перебора анкет.
     * @param userId id пользователя
     * @param message сообщение
     * @return анкета изображение и кнопки для перебора
     */
    @Override
    public OutputParameters invoke(Long userId, String message) {
        OutputParameters outputParameters = new OutputParameters();
        UserDB userDB = new UserDB();
        PrintProfile profile = new PrintProfile();
        SearchProfile searchProfile = new SearchProfile();
        searchProfile.fillUserList(userDB.getUsersByGender(userId));
        userDataCache.saveUserListData(userId, searchProfile);
        User user = searchProfile.getUserList().get(searchProfile.getNumberProfile());
        outputParameters.setSp(profile.sendPhoto(
                userId,
                user,
                ""));
        outputParameters.setSm(new InlineKeyboard().keyboard(userId, "Если нравится, нажми ЛЮБО, иначе ТРУПЪ", LikeDislikeButtonEnum.valuesPrevNextButtons()));
        return outputParameters;
    }
}
