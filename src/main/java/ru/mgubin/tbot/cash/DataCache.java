package ru.mgubin.tbot.cash;

import ru.mgubin.tbot.entity.CrushProfile;
import ru.mgubin.tbot.entity.PersonCrush;
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
    void setUsersCurrentBotState(long userId, BotState botState);

    /**
     * Метод записи текущего состояния лайков пользователю
     * @param userId идентификатор пользователя
     * @param likeState - состояние лайка
     */
    void setUsersCurrentLikeState(long userId, LikeState likeState);

    /**
     * Метод получения текущего состояния пользователя
     * @param userId идентификатор пользователя
     * @return - состояние
     */
    BotState getUsersCurrentBotState(long userId);

    /**
     * Метод получения текущего состояния в отношении лайков
     * @param userId идентификатор пользователя
     * @return - состояние
     */
    LikeState getUsersCurrentLikeState(long userId);

    /**
     * Метод получения данных пользователя по его идентификатору
     * @param userId идентификатор пользователя
     * @return анкета пользователя
     */
    User getUserProfileData(long userId);

    /**
     * Сохраниение в мап по ключу пользователя данных из объекта User
     * @param userId идентификатор пользователя
     * @param userProfileData данные пользователя
     */
    void saveUserProfileData(long userId, User userProfileData);

    /**
     * Сохраниение в мап по ключу сущности списка анкет с позицией следующей анкеты
     * @param userId идентификатор пользователя
     * @param listProfileData сущность: id пользователя и список найденных анкет для него
     */
    void saveUserListData(long userId, SearchProfile listProfileData);

    /**
     * Метод получения списка анкет для пользователя по его идентификатору
     * @param userId идентификатор пользователя
     * @return анкета пользователя
     */
    SearchProfile getUserListData(long userId);

    /**
     * Сохраниение в мап по ключу сущности списка анкет с позицией следующей анкеты
     * @param userId идентификатор пользователя
     * @param listCrushData сущность: id пользователя и список найденных анкет для него
     */
    void saveCrushListData(long userId, CrushProfile listCrushData);

    /**
     * Метод получения списка анкет для пользователя по его идентификатору
     * @param userId идентификатор пользователя
     * @return анкета пользователя
     */
    CrushProfile getUserCrushData(long userId);

}
