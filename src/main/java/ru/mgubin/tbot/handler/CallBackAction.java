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
     *
     * @param buttonQuery кнопки
     * @return ответное сообщение бота пользователю
     */
    public BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) {
        final long chatId = buttonQuery.getMessage().getChatId();
        final int userId = buttonQuery.getFrom().getId().intValue();
        MainMenuKeyboard menuService = new MainMenuKeyboard();
        BotApiMethod<?> callBackAnswer = menuService.getMainMenuMessage(chatId, "Воспользуйтесь главным меню");
        if (buttonQuery.getData().equals(GenderButtonsEnum.MEN.getButtonName())) {
            User user = userDataCache.getUserProfileData(userId);
            user.setGender(GenderButtonsEnum.MEN);
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.ASK_NAME);
            userDataCache.saveUserProfileData(buttonQuery.getMessage().getChatId().intValue(), user);
            callBackAnswer = SendMessage.builder()
                    .text("Как Вас величать?")
                    .chatId(chatId)
                    .build();
        } else if (buttonQuery.getData().equals(GenderButtonsEnum.WOMEN.getButtonName())) {
            User user = userDataCache.getUserProfileData(userId);
            user.setGender(GenderButtonsEnum.WOMEN);
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.ASK_NAME);
            userDataCache.saveUserProfileData(buttonQuery.getMessage().getChatId().intValue(), user);
            callBackAnswer = SendMessage.builder()
                    .text("Как Вас величать?")
                    .chatId(chatId)
                    .build();
        } else if (buttonQuery.getData().equals(PrevNextButtonEnum.NEXT.getButtonName())) {
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.NEXT_CRUSH);
            callBackAnswer = null;
        } else if (buttonQuery.getData().equals(PrevNextButtonEnum.PREV.getButtonName())) {
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.PREV_CRUSH);
            callBackAnswer = null;
        } else if (buttonQuery.getData().equals(ProfileButtonsEnum.WRITE.getButtonName()) || buttonQuery.getData().equals(ProfileButtonsEnum.UPDATE.getButtonName())) {
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.ASK_GENDER);
            callBackAnswer = null;
        } else if (buttonQuery.getData().equals(ProfileButtonsEnum.BROWSE.getButtonName())) {
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.BROWSE_PROFILE);
            callBackAnswer = null;
        } else if (buttonQuery.getData().equals(SearchButtonsEnum.MEN.getButtonName())) {
            User user = userDataCache.getUserProfileData(userId);
            user.setCrush(SearchButtonsEnum.MEN);
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.ASK_BIRTHDAY);
            userDataCache.saveUserProfileData(buttonQuery.getMessage().getChatId().intValue(), user);
            callBackAnswer = SendMessage.builder()
                    .text("Когда Вы родились? Напишите в формате: dd.mm.yyyy")
                    .chatId(chatId)
                    .build();
        } else if (buttonQuery.getData().equals(SearchButtonsEnum.WOMEN.getButtonName())) {
            User user = userDataCache.getUserProfileData(userId);
            user.setCrush(SearchButtonsEnum.WOMEN);
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.ASK_BIRTHDAY);
            userDataCache.saveUserProfileData(buttonQuery.getMessage().getChatId().intValue(), user);
            callBackAnswer = SendMessage.builder()
                    .text("Когда Вы родились? Напишите в формате: dd.mm.yyyy")
                    .chatId(chatId)
                    .build();
        } else if (buttonQuery.getData().equals(SearchButtonsEnum.ALL.getButtonName())) {
            User user = userDataCache.getUserProfileData(userId);
            user.setCrush(SearchButtonsEnum.ALL);
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.ASK_BIRTHDAY);
            userDataCache.saveUserProfileData(buttonQuery.getMessage().getChatId().intValue(), user);
            callBackAnswer = SendMessage.builder()
                    .text("Когда Вы родились? Напишите в формате: dd.mm.yyyy")
                    .chatId(chatId)
                    .build();
        } else if (buttonQuery.getData().equals(LikeDislikeButtonEnum.LIKES.getButtonName())) {
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.NEXT_PROFILE);
            userDataCache.setUsersCurrentLikeState(userId, LikeStateEnum.LIKE);
            callBackAnswer = null;
        } else if (buttonQuery.getData().equals(LikeDislikeButtonEnum.DISLIKES.getButtonName())) {
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
}
