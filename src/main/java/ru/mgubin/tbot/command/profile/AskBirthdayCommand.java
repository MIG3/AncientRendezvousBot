package ru.mgubin.tbot.command.profile;

import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.service.PrintProfileService;
import ru.mgubin.tbot.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AskBirthdayCommand implements Command {
    /**
     * Обработка последнего шага заполнения анкеты.
     * В сущность клиента записывается его дата рождения и идентификатор.
     * Состояние бота меняется на SAVE_PROFILE - анкета заполнена
     * Сущность пользователя записывается в мапу кеша пользователей с ключем - id
     * Сущность клиента записывается в БД
     * Печатается анкета пользователя в чат бота
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
        User profileData = userDataCache.getUserProfileData(userId);
        profileData.setBirthdate(LocalDate.parse(message, DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        profileData.setId(userId);
        userDataCache.saveUserProfileData(userId, profileData);
        userService.createUser(profileData);
        outputParameters.setSendPhoto(profile.sendPhoto(userId, profileData, ""));
        return outputParameters;
    }
}
