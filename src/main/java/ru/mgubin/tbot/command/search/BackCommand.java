package ru.mgubin.tbot.command.search;

import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.service.UserService;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.entity.PersonCrush;
import ru.mgubin.tbot.entity.SearchProfile;
import ru.mgubin.tbot.enums.SearchNavigationEnum;
import ru.mgubin.tbot.keyboard.InlineKeyboard;
import ru.mgubin.tbot.service.PrintProfileService;

public class BackCommand implements Command {

    /**
     * Метод перебора анкет по писку в обратном порядке - назад.
     * В нём получаем список анкет найденных анкет из кеша,
     * записываем в мапу по id пользователя номер текущей анкеты.
     * Вызывается метод печати текущей анкеты из списка.
     * Выводятся кнопки для перебора анкет.
     *
     * @param userId        id пользователя
     * @param message       сообщение
     * @param userDataCache кэш данных пользователя
     * @return анкета-изображение и кнопки для перебора
     */
    @Override
    public OutputParameters invoke(Long userId, String message, UserDataCache userDataCache) {
        SearchProfile searchProfile = new SearchProfile();
        OutputParameters outputParameters = new OutputParameters();
        PrintProfileService profile = new PrintProfileService();
        UserService userService = new UserService();
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
        userService.removeLikeToUser(lovers);
        outputParameters.setSendPhoto(profile.sendPhoto(       // печатаем изображение, передавая параметрами
                userId,                    // id чата
                searchProfile.getUserList().get(searchProfile.getNumberProfile()),
                ""));
        outputParameters.setSendMessage(new InlineKeyboard().keyboard(userId, "Если нравится, нажми вперед, иначе назад", SearchNavigationEnum.valuesLikeDislikeButtons()));
        return outputParameters;
    }
}
