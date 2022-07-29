package ru.mgubin.tbot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.*;
import ru.mgubin.tbot.keyboard.MainMenuKeyboard;

public class CallBackAction {
    private final UserDataCache userDataCache;

    @Autowired
    public CallBackAction(UserDataCache userDataCache) {
        this.userDataCache = userDataCache;
    }

    /**
     * Метод по обработке ответов при нажатии на кнопки.
     * В зависимости от варианта нажатия на ту или иную кнопку
     * боту меняется состояние на соответствующее.
     * Формируется ответное сообщение-вопрос пользователю,
     * записывается в кеш id клиента и сама его сущность,
     * если это предусмотрело логикой.
     *
     * @param buttonQuery кнопки
     * @return ответное сообщение бота пользователю
     */
    public BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) {
        final long chatId = buttonQuery.getMessage().getChatId();
        final int userId = buttonQuery.getFrom().getId().intValue();
        MainMenuKeyboard menuService = new MainMenuKeyboard();
        BotApiMethod<?> callBackAnswer = menuService.getMainMenuMessage(chatId, "Воспользуйтесь главным меню");
        if (GenderButtonsEnum.existValueOfGenderButtons(buttonQuery.getData()))
        {
            User user = userDataCache.getUserProfileData(userId);
            user.setGender(GenderButtonsEnum.valueOfGenderButtons(buttonQuery.getData()));
            callBackAnswer = questionAboutName(user, chatId, userId);
        } else if (buttonQuery.getData().equals(NavigationByCrushButtonEnum.NEXT.getPrevNext())) {
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.NEXT_CRUSH);
            callBackAnswer = null;
        } else if (buttonQuery.getData().equals(NavigationByCrushButtonEnum.PREV.getPrevNext())) {
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.PREV_CRUSH);
            callBackAnswer = null;
        } else if (buttonQuery.getData().equals(ProfileButtonsEnum.WRITE.getProfile()) || buttonQuery.getData().equals(ProfileButtonsEnum.UPDATE.getProfile())) {
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.ASK_GENDER);
            callBackAnswer = null;
        } else if (buttonQuery.getData().equals(ProfileButtonsEnum.BROWSE.getProfile())) {
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.BROWSE_PROFILE);
            callBackAnswer = null;
        }
        else if (SearchButtonsEnum.existValueOfSearchButtons(buttonQuery.getData())){
            User user = userDataCache.getUserProfileData(userId);
            user.setCrush(SearchButtonsEnum.valueOfSearchButtons(buttonQuery.getData()));
            callBackAnswer = questionAboutBirthdate(user, chatId, userId);
        } else if (buttonQuery.getData().equals(NavigationBySearchButtonEnum.LIKES.getLikeDislike())) {
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.NEXT_PROFILE);
            userDataCache.setUsersCurrentLikeState(userId, LikeStateEnum.LIKE);
            callBackAnswer = null;
        } else if (buttonQuery.getData().equals(NavigationBySearchButtonEnum.DISLIKES.getLikeDislike())) {
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.PREV_PROFILE);
            userDataCache.setUsersCurrentLikeState(userId, LikeStateEnum.DISLIKE);
            callBackAnswer = null;
        } else {
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.SHOW_MAIN_MENU);
        }
        return callBackAnswer;
    }

    private AnswerCallbackQuery sendAnswerCallbackQuery(String text, boolean alert, CallbackQuery callbackquery) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callbackquery.getId());
        answerCallbackQuery.setShowAlert(alert);
        answerCallbackQuery.setText(text);
        return answerCallbackQuery;
    }

    private SendMessage questionAboutName(User user, long chatId, long userId) {
        userDataCache.setUsersCurrentBotState(userId, BotStateEnum.ASK_NAME);
        userDataCache.saveUserProfileData(userId, user);
        return SendMessage.builder()
                .text("Как Вас величать?")
                .chatId(chatId)
                .build();
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
