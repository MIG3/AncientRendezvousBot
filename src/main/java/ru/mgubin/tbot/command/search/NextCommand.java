package ru.mgubin.tbot.command.search;

import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.db.UserDB;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.entity.PersonCrush;
import ru.mgubin.tbot.entity.SearchProfile;
import ru.mgubin.tbot.enums.NavigationBySearchButtonEnum;
import ru.mgubin.tbot.keyboard.InlineKeyboard;
import ru.mgubin.tbot.service.PrintProfileService;

public class NextCommand implements Command {

    /**
     * Метод перебора анкет по писку в прямом порядке - вперёд.
     * В нём получаем список анкет найденных анкет из кеша,
     * записываем в мапу по id пользователя номер текущей анкеты.
     * Вызывается метод печати текущей анкеты из списка.
     * Выводятся кнопки для перебора анкет.
     *
     * @param userId        id пользователя
     * @param message       сообщение
     * @param userDataCache
     * @return анкета-изображение и кнопки для перебора
     */
    @Override
    public OutputParameters invoke(Long userId, String message, UserDataCache userDataCache) {
        OutputParameters outputParameters = new OutputParameters();
        PrintProfileService profile = new PrintProfileService();
        UserDB userDB = new UserDB();
        PersonCrush lovers = new PersonCrush();
        SearchProfile searchProfile = userDataCache.getUserListData(userId);
        int lengthUserList = searchProfile.getUserList().size();
        int pos = searchProfile.getNumberProfile();
        if (lengthUserList <= pos + 1) {
            searchProfile.setNumberProfile(0);
            lovers.setCrushId(searchProfile.getUserList().get(lengthUserList - 1).getId());
        } else {
            searchProfile.setNumberProfile(pos + 1);
            lovers.setCrushId(searchProfile.getUserList().get(pos).getId());
        }
        lovers.setUserId(userId);
        userDB.makeLikeToUser(lovers);
        outputParameters.setSendPhoto(profile.sendPhoto(
                userId,
                searchProfile.getUserList().get(searchProfile.getNumberProfile()), ""));
        outputParameters.setSendMessage(new InlineKeyboard().keyboard(userId, "Если нравится, нажми вперед, иначе назад", NavigationBySearchButtonEnum.valuesLikeDislikeButtons()));
        return outputParameters;
    }
}
