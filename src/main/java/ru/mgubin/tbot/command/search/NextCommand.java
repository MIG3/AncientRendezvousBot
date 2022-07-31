package ru.mgubin.tbot.command.search;

import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.entity.SearchProfile;
import ru.mgubin.tbot.service.PrintProfileService;
import ru.mgubin.tbot.service.UserService;

public class NextCommand extends NavigationCommand implements Command {

    /**
     * Метод перебора анкет по писку в прямом порядке - вперёд.
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
        PrintProfileService profile = new PrintProfileService();
        UserService userService = new UserService();
        SearchProfile searchProfile = userDataCache.getUserListData(userId);
        userService.makeLikeToUser(actionNavigate(userId, searchProfile));
        return getAnswer(userId, profile, searchProfile);
    }
}
