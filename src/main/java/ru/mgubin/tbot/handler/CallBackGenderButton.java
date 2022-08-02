package ru.mgubin.tbot.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotStateEnum;
import ru.mgubin.tbot.enums.GenderEnum;

public class CallBackGenderButton implements CallBackButton {

    private final UserDataCache userDataCache;

    public CallBackGenderButton(UserDataCache userDataCache) {

        this.userDataCache = userDataCache;
    }

    /**
     * Метод обработки Callback от кнопок выбора пола
     *
     * @param buttonQuery   результат нажатия на кнопки
     * @param userDataCache кэш данных пользователя
     * @return ответное сообщение
     */
    public BotApiMethod<?> genderButton(CallbackQuery buttonQuery, UserDataCache userDataCache) {
        final long chatId = buttonQuery.getMessage().getChatId();
        final int userId = buttonQuery.getFrom().getId().intValue();
        User user = userDataCache.getUserProfileData(userId);
        user.setGender(GenderEnum.valueOfGenderButtons(buttonQuery.getData()));
        return questionAboutName(user, chatId, userId);
    }

    private SendMessage questionAboutName(User user, long chatId, long userId) {
        userDataCache.setUsersCurrentBotState(userId, BotStateEnum.ASK_NAME);
        userDataCache.saveUserProfileData(userId, user);
        return SendMessage.builder()
                .text("Как Вас величать?")
                .chatId(chatId)
                .build();
    }
}
