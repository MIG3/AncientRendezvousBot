package ru.mgubin.tbot.command.search;

import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.db.UserDB;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.entity.SearchProfile;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.NavigationBySearchButtonEnum;
import ru.mgubin.tbot.keyboard.InlineKeyboard;
import ru.mgubin.tbot.service.PrintProfileService;

public class SearchUserCommand implements Command {

    /**
     * Метод, который вызывается при нажатии кнопки "ПОИСК".
     * В нём получает список анкет из БД, записывает в мапу по id пользователя,
     * после вызывается метод печати первой анкеты из списка.
     * Выводятся кнопки для перебора анкет.
     *
     * @param userId        id пользователя
     * @param message       сообщение
     * @param userDataCache кэш данных пользователя
     * @return анкета изображение и кнопки для перебора
     */
    @Override
    public OutputParameters invoke(Long userId, String message, UserDataCache userDataCache) {
        OutputParameters outputParameters = new OutputParameters();
        UserDB userDB = new UserDB();
        PrintProfileService profile = new PrintProfileService();
        SearchProfile searchProfile = new SearchProfile();
        searchProfile.fillUserList(userDB.getUsersByGender(userId));
        userDataCache.saveUserListData(userId, searchProfile);
        User user = searchProfile.getUserList().get(searchProfile.getNumberProfile());
        outputParameters.setSendPhoto(profile.sendPhoto(
                userId,
                user,
                ""));
        outputParameters.setSendMessage(new InlineKeyboard().keyboard(userId, "Если нравится, нажми ЛЮБО, иначе ТРУПЪ", NavigationBySearchButtonEnum.valuesLikeDislikeButtons()));
        return outputParameters;
    }
}
