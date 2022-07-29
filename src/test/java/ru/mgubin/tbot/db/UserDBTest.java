package ru.mgubin.tbot.db;

import org.junit.jupiter.api.Test;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.entity.PersonCrush;
import ru.mgubin.tbot.enums.BotStateEnum;

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

    @Test
    void helpShowCommand()
    {
        BotStateEnum stateEnum;
        stateEnum = BotStateEnum.SHOW_HELP_MENU;
        Command command = stateEnum.getCommand();
    }
}