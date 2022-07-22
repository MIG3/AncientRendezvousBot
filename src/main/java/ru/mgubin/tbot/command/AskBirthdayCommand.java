package ru.mgubin.tbot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotState;

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
        OutputParameters outputParameters = new OutputParameters();
        User profileData = userDataCache.getUserProfileData(message.getFrom().getId().intValue());

        profileData.setBirthday(LocalDate.parse(message.getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));

        outputParameters.setSm(SendMessage.builder()
                .text("Анкета заполнена\n" + profileData.getName() + "\n" + profileData.getGender() + "\n" + profileData.getBirthday() + "\n" + profileData.getInfo())
                .chatId(message.getFrom().getId())
                .build());

        userDataCache.setUsersCurrentBotState(message.getFrom().getId().intValue(), BotState.SAVE_PROFILE);
        userDataCache.saveUserProfileData(message.getFrom().getId().intValue(), profileData);

        return outputParameters;
    }
}