package ru.mgubin.tbot.command.profile;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.db.UserDB;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.service.PrintProfileService;

public class BrowsProfile implements Command {
    private final UserDataCache userDataCache;

    @Autowired
    public BrowsProfile(UserDataCache userDataCache) {
        this.userDataCache = userDataCache;
    }

    /**
     * Просмотр анкеты текущего пользователя
     * Получаем в сущность клиента данные о нём из БД,
     * записываем их в мапу кеша пользователей с ключем - id
     * @param userId id клиента
     * @param message сообщение
     * @return Изображение - анкета
     */
    @Override
    public OutputParameters invoke(Long userId, String message) {
        OutputParameters outputParameters = new OutputParameters();
        PrintProfileService profile = new PrintProfileService();
        UserDB userDB = new UserDB();
        User profileData = userDB.getUser(userId);
        userDataCache.saveUserProfileData(userId, profileData);
        outputParameters.setSendPhoto(profile.sendPhoto(userId, profileData, ""));
        return outputParameters;
    }
}
