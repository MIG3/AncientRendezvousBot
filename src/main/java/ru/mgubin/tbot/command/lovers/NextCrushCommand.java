package ru.mgubin.tbot.command.lovers;

import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.entity.SearchProfile;
import ru.mgubin.tbot.service.PrintProfileService;

public class NextCrushCommand extends NavigationCrushCommand implements Command {
    /**
     * Метод перебора анкет любимцев в прямом порядке - вперёд.
     * В нём получаем список анкет любимцев из кеша,
     * записываем в мапу по id пользователя номер текущего любимца.
     * Вызывается метод генерации части подписи по статусу лайков,
     * после него вызывается метод печати текущей анкеты из списка.
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
        SearchProfile crushProfile = userDataCache.getUserListData(userId);
        int lengthUserList = crushProfile.getUserList().size();
        int pos = crushProfile.getNumberProfile();
        int index;
        if (lengthUserList <= pos + 1) {
            index = 0;
        } else {
            index = pos + 1;
        }
        return getAnswer(userId, profile, crushProfile, userDataCache, index);
    }
}
