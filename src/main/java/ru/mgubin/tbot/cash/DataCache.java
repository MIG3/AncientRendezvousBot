package ru.mgubin.tbot.cash;

import ru.mgubin.tbot.entity.SearchProfile;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotState;
import ru.mgubin.tbot.enums.LikeState;

public interface DataCache
{
    /**
     * Метод записи текущего состояния пользователю
     * @param userId идентификатор пользователя
     * @param botState - состояние
     */
    void setUsersCurrentBotState(int userId, BotState botState);

    void setUsersCurrentLikeState(int userId, LikeState likeState);

    /**
     * Метод получения текущего состояния пользователя
     * @param userId идентификатор пользователя
     * @return - состояние
     */
    BotState getUsersCurrentBotState(int userId);

    LikeState getUsersCurrentLikeState(int userId);

    /**
     * Метод получения данных пользователя по его идентификатору
     * @param userId идентификатор пользователя
     * @return анкета пользователя
     */
    User getUserProfileData(int userId);

    /**
     * Сохраниение в мап по ключу пользователя данных из объекта User
     * @param userId идентификатор пользователя
     * @param userProfileData данные пользователя
     */
    void saveUserProfileData(int userId, User userProfileData);

    /**
     * Сохраниение в мап по ключу сущности списка анкет с позицией следующей анкеты
     * @param userId идентификатор пользователя
     * @param listProfileData сущность: id пользователя и список найденных анкет для него
     */
    void saveUserListData(int userId, SearchProfile listProfileData);

    /**
     * Метод получения списка анкет для пользователя по его идентификатору
     * @param userId идентификатор пользователя
     * @return анкета пользователя
     */
    SearchProfile getUserListData(int userId);

}
