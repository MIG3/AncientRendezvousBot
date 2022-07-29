package ru.mgubin.tbot.command.search;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.db.UserDB;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.entity.PersonCrush;
import ru.mgubin.tbot.entity.SearchProfile;
import ru.mgubin.tbot.enums.NavigationBySearchButtonEnum;
import ru.mgubin.tbot.keyboard.InlineKeyboard;
import ru.mgubin.tbot.service.PrintProfileService;

public class BackCommand implements Command {
    private final UserDataCache userDataCache;

    @Autowired
    public BackCommand(UserDataCache userDataCache) {
        this.userDataCache = userDataCache;
    }

    /**
     * Метод перебора анкет по писку в обратном порядке - назад.
     * В нём получаем список анкет найденных анкет из кеша,
     * записываем в мапу по id пользователя номер текущей анкеты.
     * Вызывается метод печати текущей анкеты из списка.
     * Выводятся кнопки для перебора анкет.
     *
     * @param userId  id пользователя
     * @param message сообщение
     * @return анкета-изображение и кнопки для перебора
     */
    @Override
    public OutputParameters invoke(Long userId, String message) {
        SearchProfile searchProfile = new SearchProfile();
        OutputParameters outputParameters = new OutputParameters();
        PrintProfileService profile = new PrintProfileService();
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
        outputParameters.setSendPhoto(profile.sendPhoto(       // печатаем изображение, передавая параметрами
                userId,                    // id чата
                searchProfile.getUserList().get(searchProfile.getNumberProfile()),
                ""));
        outputParameters.setSendMessage(new InlineKeyboard().keyboard(userId, "Если нравится, нажми вперед, иначе назад", NavigationBySearchButtonEnum.valuesLikeDislikeButtons()));
        return outputParameters;
    }
}
