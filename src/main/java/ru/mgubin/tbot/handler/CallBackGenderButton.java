package ru.mgubin.tbot.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotStateEnum;
import ru.mgubin.tbot.enums.ButtonStateEnum;
import ru.mgubin.tbot.enums.GenderButtonsEnum;

public class CallBackGenderButton implements CallBackButton{


    public BotApiMethod<?> genderButton(CallbackQuery buttonQuery, UserDataCache userDataCache, ButtonStateEnum buttonState) {
        final long chatId = buttonQuery.getMessage().getChatId();
        final int userId = buttonQuery.getFrom().getId().intValue();
        User user = userDataCache.getUserProfileData(userId);
        user.setGender(GenderButtonsEnum.valueOfGenderButtons(buttonQuery.getData()));
        return questionAboutName(user, chatId, userId, userDataCache);
    }

    private SendMessage questionAboutName(User user, long chatId, long userId, UserDataCache userDataCache) {
        userDataCache.setUsersCurrentBotState(userId, BotStateEnum.ASK_NAME);
        userDataCache.saveUserProfileData(userId, user);
        return SendMessage.builder()
                .text("Как Вас величать?")
                .chatId(chatId)
                .build();
    }
}
