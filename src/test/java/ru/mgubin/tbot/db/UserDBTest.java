package ru.mgubin.tbot.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mgubin.tbot.entity.PersonCrush;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.GenderButtonsEnum;
import ru.mgubin.tbot.enums.SearchButtonsEnum;

import java.time.LocalDate;

class UserDBTest
{
    @Test
    void getUsersByGender()
    {
        UserDB userDB = new UserDB();
        userDB.getUsersByGender(1);
        System.out.println(userDB.toString());

    }

    @Test
    void createUser()
    {
        UserDB userDB = new UserDB();
        LocalDate localDate = LocalDate.of(1990, 10, 10);
        //userDB.createUser(new User(10L, "Gena", localDate, SearchButtonsEnum.MEN, GenderButtonsEnum.MEN, "kdfmbs dgdfd"));


    }

    @Test
    void makeLikeTest() {
        UserDB userDB = new UserDB();
        userDB.makeLikeToUser(new PersonCrush(74L, 8L));
    }
}