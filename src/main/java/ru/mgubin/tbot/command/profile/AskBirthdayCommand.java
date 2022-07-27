package ru.mgubin.tbot.command.profile;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.db.UserDB;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotStateEnum;
import ru.mgubin.tbot.service.PrintProfile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AskBirthdayCommand implements Command {
    private final UserDataCache userDataCache;

    @Autowired
    public AskBirthdayCommand(UserDataCache userDataCache) {
        this.userDataCache = userDataCache;
    }

    /**
     * Обработка последнего шага заполнения анкеты.
     * В сущность клиента записывается его дата рождения и идентификатор.
     * Состояние бота меняется на SAVE_PROFILE - анкета заполнена
     * Сущность пользователя записывается в мапу кеша пользователей с ключем - id
     * Сущность клиента записывается в БД
     * Печатается анкета пользователя в чат бота
     * @param userId id клиента
     * @param message сообщение
     * @return Изображение - анкета
     */
    @Override
    public OutputParameters invoke(Long userId, String message) {
        OutputParameters outputParameters = new OutputParameters();
        PrintProfile profile = new PrintProfile();
        UserDB userDB = new UserDB();
        User profileData = userDataCache.getUserProfileData(userId);
        profileData.setBirthdate(LocalDate.parse(message, DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        profileData.setId(userId);
        userDataCache.setUsersCurrentBotState(userId, BotStateEnum.SAVE_PROFILE);
        userDataCache.saveUserProfileData(userId, profileData);
        userDB.createUser(profileData);
        outputParameters.setSp(profile.sendPhoto(userId, profileData, ""));
        return outputParameters;
    }
}
