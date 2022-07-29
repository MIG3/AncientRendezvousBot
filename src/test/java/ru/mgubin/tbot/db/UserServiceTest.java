package ru.mgubin.tbot.db;

import org.junit.jupiter.api.Test;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.entity.PersonCrush;
import ru.mgubin.tbot.enums.BotStateEnum;
import ru.mgubin.tbot.service.UserService;

import java.time.LocalDate;

class UserServiceTest
{
    @Test
    void getUsersByGender()
    {
        UserService userService = new UserService();
        userService.getUsersByGender(1);
        System.out.println(userService.toString());

    }

    @Test
    void createUser()
    {
        UserService userService = new UserService();
        LocalDate localDate = LocalDate.of(1990, 10, 10);
        //userDB.createUser(new User(10L, "Gena", localDate, SearchButtonsEnum.MEN, GenderButtonsEnum.MEN, "kdfmbs dgdfd"));


    }

    @Test
    void makeLikeTest() {
        UserService userService = new UserService();
        userService.makeLikeToUser(new PersonCrush(74L, 8L));
    }

    @Test
    void helpShowCommand()
    {
        BotStateEnum stateEnum;
        stateEnum = BotStateEnum.SHOW_HELP_MENU;
        Command command = stateEnum.getCommand();
    }
}