package ru.mgubin.tbot.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotStateEnum;
import ru.mgubin.tbot.enums.SearchEnum;

public class CallBackSearchButton implements CallBackButton {

    private final UserDataCache userDataCache;

    public CallBackSearchButton(UserDataCache userDataCache) {

        this.userDataCache = userDataCache;
    }

    public BotApiMethod<?> searchButton(CallbackQuery buttonQuery, UserDataCache userDataCache) {
        final long chatId = buttonQuery.getMessage().getChatId();
        final int userId = buttonQuery.getFrom().getId().intValue();
        User user = userDataCache.getUserProfileData(userId);
        user.setCrush(SearchEnum.valueOfSearchButtons(buttonQuery.getData()));
        return questionAboutBirthdate(user, chatId, userId);
    }

    private SendMessage questionAboutBirthdate(User user, long chatId, long userId) {
        userDataCache.setUsersCurrentBotState(userId, BotStateEnum.ASK_BIRTHDAY);
        userDataCache.saveUserProfileData(userId, user);
        return SendMessage.builder()
                .text("Когда Вы родились? Напишите в формате: dd.mm.yyyy")
                .chatId(chatId)
                .build();
    }
}
