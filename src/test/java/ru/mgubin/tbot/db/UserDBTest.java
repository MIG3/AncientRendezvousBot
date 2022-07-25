package ru.mgubin.tbot.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mgubin.tbot.entity.User;

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
        userDB.createUser(new User(10, "Gena", localDate, "MEN", "MEN", "kdfmbs dgdfd"));
    }
}