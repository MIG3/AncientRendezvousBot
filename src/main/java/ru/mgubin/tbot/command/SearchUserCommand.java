package ru.mgubin.tbot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.db.UserDB;
import ru.mgubin.tbot.entity.SearchProfile;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotState;
import ru.mgubin.tbot.enums.PrevNextButtons;
import ru.mgubin.tbot.enums.SearchButtons;
import ru.mgubin.tbot.keyboard.InlineKeyboard;
import ru.mgubin.tbot.printer.PrintProfile;

import java.util.List;

public class SearchUserCommand implements Command
{
    private final UserDataCache userDataCache;
    @Autowired
    public SearchUserCommand(UserDataCache userDataCache)
    {
        this.userDataCache = userDataCache;
    }
    @Override
    public OutputParameters invoke(Message message)
    {
        int userId = message.getFrom().getId().intValue();
        OutputParameters outputParameters = new OutputParameters();
        UserDB userDB = new UserDB();
        PrintProfile profile = new PrintProfile();
        InlineKeyboard prevOrNext = new InlineKeyboard();
        SearchProfile searchProfile = new SearchProfile();
        User user = new User();

        searchProfile.fillUserList(userDB.getUsersByGender(userId)); // вызов метода, который ввозвращает список анкет (users)
        searchProfile.setNumberProfile(0); // позиция 0 при первом запуске, когда нажал кнопку "ПОИСК"

        userDataCache.saveUserListData(userId, searchProfile); // и записывает в мапу по id пользователя
        System.out.println(searchProfile.getUserList().get(searchProfile.getNumberProfile()));
        user = searchProfile.getUserList().get(searchProfile.getNumberProfile());

        outputParameters.setSp(profile.sendPhoto(       // печатаем изображение, передавая параметрами
                message.getChatId(),                    // id чата
                user));   // 0 элемент списка анкет (пользователей)

        userDataCache.setUsersCurrentBotState(userId, BotState.CHOICE_PREVorNEXT_BUTTON); // меняю состояние, чтобы вызвать следующую команду выдачи кнопок перебора анкет
        // увеличить позицию на 1?

        //outputParameters.setSm(prevOrNext.keyboard(message.getChatId(), "Кого Вы хотите искать в будущем?", PrevNextButtons.values()));
        /*outputParameters.setSm(SendMessage.builder()
                .text("Ожидается в будущих обновлениях"
                )
                .chatId(message.getChatId())
                .build());*/

        return outputParameters;
    }

/*    @Override
    public OutputParameters invoke(Message message, List<User> users)
    {
        return null;
    }*/
}
