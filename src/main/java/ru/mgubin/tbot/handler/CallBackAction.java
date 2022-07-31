package ru.mgubin.tbot.handler;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.*;

public class CallBackAction {
    private final UserDataCache userDataCache;

    public CallBackAction(UserDataCache userDataCache) {
        this.userDataCache = userDataCache;
    }

    /**
     * Метод по обработке ответов при нажатии на кнопки.
     * В зависимости от варианта нажатия на ту или иную кнопку
     * боту меняется состояние на соответствующее.
     * Формируется ответное сообщение-вопрос пользователю (если оно нужно),
     * записывается в кеш id клиента и сама его сущность,
     * если это предусмотрело логикой.
     *
     * @param buttonQuery результат нажатия на кнопку
     * @return ответное сообщение бота пользователю
     */
    public BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) {
        final long chatId = buttonQuery.getMessage().getChatId();
        final int userId = buttonQuery.getFrom().getId().intValue();
        BotApiMethod<?> callBackAnswer;
        if (GenderButtonsEnum.existValueOfGenderButtons(buttonQuery.getData())) {
            User user = userDataCache.getUserProfileData(userId);
            user.setGender(GenderButtonsEnum.valueOfGenderButtons(buttonQuery.getData()));
            callBackAnswer = questionAboutName(user, chatId, userId);
        } else if (SearchButtonsEnum.existValueOfSearchButtons(buttonQuery.getData())) {
            User user = userDataCache.getUserProfileData(userId);
            user.setCrush(SearchButtonsEnum.valueOfSearchButtons(buttonQuery.getData()));
            callBackAnswer = questionAboutBirthdate(user, chatId, userId);
        } else {
            callBackAnswer = setStateWithoutAnswer(buttonQuery, userId);
        }
        return callBackAnswer;
    }

    /**
     * Метод формирования всплывающего сообщения поверх чата
     *
     * @param text        текст сообщения
     * @param alert       наличие уведомления
     * @param buttonQuery результат нажатия на кнопку
     * @return ответ
     */
    private AnswerCallbackQuery sendAnswerCallbackQuery(String text, boolean alert, CallbackQuery buttonQuery) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(buttonQuery.getId());
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

    /**
     * Метод обработки ответов на кнопки в тех случаях, когда только меняется состояние без возврата ответа
     *
     * @param buttonQuery результат нажатия на кнопку
     * @param userId      id пользователя
     * @return пустое ответное сообщение
     */
    private BotApiMethod<?> setStateWithoutAnswer(CallbackQuery buttonQuery, long userId) {
        if (buttonQuery.getData().equals(CrushNavigationEnum.NEXT.getPrevNext())) {
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.NEXT_CRUSH);
        } else if (buttonQuery.getData().equals(CrushNavigationEnum.PREV.getPrevNext())) {
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.PREV_CRUSH);
        } else if (buttonQuery.getData().equals(ProfileButtonsEnum.WRITE.getProfile()) || buttonQuery.getData().equals(ProfileButtonsEnum.UPDATE.getProfile())) {
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.ASK_GENDER);
        } else if (buttonQuery.getData().equals(ProfileButtonsEnum.BROWSE.getProfile())) {
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.BROWSE_PROFILE);
        } else if (buttonQuery.getData().equals(SearchNavigationEnum.LIKES.getLikeDislike())) {
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.NEXT_PROFILE);
            userDataCache.setUsersCurrentLikeState(userId, LikeStateEnum.LIKE);
        } else if (buttonQuery.getData().equals(SearchNavigationEnum.DISLIKES.getLikeDislike())) {
            userDataCache.setUsersCurrentBotState(userId, BotStateEnum.PREV_PROFILE);
            userDataCache.setUsersCurrentLikeState(userId, LikeStateEnum.DISLIKE);
        }
        return null;
    }
}
