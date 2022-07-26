package ru.mgubin.tbot.command.profile;

import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.service.UserService;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.service.PrintProfileService;

public class BrowsProfile implements Command {

    /**
     * Просмотр анкеты текущего пользователя
     * Получаем в сущность клиента данные о нём из БД,
     * записываем их в мапу кеша пользователей с ключем - id
     *
     * @param userId        id клиента
     * @param message       сообщение
     * @param userDataCache кэш данных пользователя
     * @return Изображение - анкета
     */
    @Override
    public OutputParameters invoke(Long userId, String message, UserDataCache userDataCache) {
        OutputParameters outputParameters = new OutputParameters();
        PrintProfileService profile = new PrintProfileService();
        UserService userService = new UserService();
        User profileData = userService.getUser(userId);
        userDataCache.saveUserProfileData(userId, profileData);
        outputParameters.setSendPhoto(profile.sendPhoto(userId, profileData, ""));
        return outputParameters;
    }
}
