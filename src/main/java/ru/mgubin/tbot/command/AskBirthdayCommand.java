package ru.mgubin.tbot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotState;
import ru.mgubin.tbot.printer.PrintProfile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AskBirthdayCommand implements Command
{
    private final UserDataCache userDataCache;

    @Autowired
    public AskBirthdayCommand(UserDataCache userDataCache)
    {
        this.userDataCache = userDataCache;
    }

    @Override
    public OutputParameters invoke(Message message)
    {
        int userId = message.getFrom().getId().intValue();
        OutputParameters outputParameters = new OutputParameters();
        PrintProfile profile = new PrintProfile();

        User profileData = userDataCache.getUserProfileData(userId);

        profileData.setBirthday(LocalDate.parse(message.getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        profileData.setId(userId);

        userDataCache.setUsersCurrentBotState(userId, BotState.SAVE_PROFILE);
        userDataCache.saveUserProfileData(userId, profileData);

        // должен быть вызов метода записи в БД

        outputParameters.setSp(profile.sendPhoto(message.getChatId()));

        /*outputParameters.setSm(SendMessage.builder()
                .text("Анкета заполнена\n"*//* + profileData.getName() + "\n" + profileData.getGender() + "\n" + profileData.getBirthday() + "\n" + profileData.getInfo()*//*)
                .chatId(message.getChatId())
                .build());*/

        return outputParameters;
    }
}
