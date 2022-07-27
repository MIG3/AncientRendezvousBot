package ru.mgubin.tbot.service;

import org.springframework.stereotype.Service;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.db.UserDB;
import ru.mgubin.tbot.entity.CrushProfile;
import ru.mgubin.tbot.entity.PersonCrush;

import java.util.List;

import static ru.mgubin.tbot.constant.Constants.*;

@Service
public class GenerateLabel
{
    private final UserDataCache userDataCache;
    UserDB userDB = new UserDB();
    CrushProfile crushes = new CrushProfile();

    public GenerateLabel(UserDataCache userDataCache)
    {
        this.userDataCache = userDataCache;
    }

    /**
     * Метод генерации сообщения о статусе любимки для подписи анкет найденных любимок
     * @param userId id пользователя
     * @param crushId id любимца
     * @return сообщение о том, любим ты, любишь ты или взаимность
     */
    public String labelFromPicture(long userId, long crushId)
    {
        List<PersonCrush> personCrushList = userDB.getUserAndCrush(userId, crushId);
        crushes.fillUserList(personCrushList);
        userDataCache.saveCrushListData(userId,crushes);
        String label = "";
        if (personCrushList.size()==2)
            label = LOVE_YOU_LOVE;
        else if (personCrushList.get(0).getUserId().equals(userId))
            label = YOU_LOVE;
        else
            label = LOVE_YOU;
        return label;
    }
}
