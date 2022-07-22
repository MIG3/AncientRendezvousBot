package ru.mgubin.tbot.cash;

import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotState;

public interface DataCache
{
    /**
     * Метод записи текущего состояния пользователю
     * @param userId идентификатор пользователя
     * @param botState - состояние
     */
    void setUsersCurrentBotState(int userId, BotState botState);

    /**
     * Метод получения текущего состояния пользователя
     * @param userId идентификатор пользователя
     * @return - состояние
     */
    BotState getUsersCurrentBotState(int userId);

    /**
     * Метод получения данных пользователя по его идентификатору
     * @param userId идентификатор пользователя
     * @return анкета пользователя
     */
    User getUserProfileData(int userId);

    /**
     * Сохраниение в мап по ключу пользователя данных из объекта User
     * @param userId идентификатор пользователя
     * @param userProfileData словарь с парами: id пользователя, его данные
     */
    void saveUserProfileData(int userId, User userProfileData);
}
