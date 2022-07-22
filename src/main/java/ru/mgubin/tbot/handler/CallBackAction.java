package ru.mgubin.tbot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotState;
import ru.mgubin.tbot.keyboard.MainMenuKeyboard;

@Service
public class CallBackAction
{
    private final UserDataCache userDataCache;

    @Autowired
    public CallBackAction(UserDataCache userDataCache)
    {
        this.userDataCache = userDataCache;
    }

    /**
     * Метод по обработке ответов при нажатии на кнопки.
     *
     * @param buttonQuery кнопки
     * @return ответное сообщение бота пользователю
     */
    public BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery)
    {
        final long chatId = buttonQuery.getMessage().getChatId();
        final int userId = buttonQuery.getFrom().getId().intValue();
        MainMenuKeyboard menuService = new MainMenuKeyboard();

        BotApiMethod<?> callBackAnswer = menuService.getMainMenuMessage(chatId, "Воспользуйтесь главным меню");

        if (buttonQuery.getData().equals("СУДАРЬ"))
        {
            User user = userDataCache.getUserProfileData(userId);
            user.setGender("M");
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_NAME);
            userDataCache.saveUserProfileData(buttonQuery.getMessage().getChatId().intValue(), user);

            callBackAnswer = SendMessage.builder()
                    .text("Как Вас величать?")
                    .chatId(chatId)
                    .build();
        } else if (buttonQuery.getData().equals("СУДАРЫНЯ"))
        {
            User user = userDataCache.getUserProfileData(userId);
            user.setGender("Ж");
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_NAME);
            userDataCache.saveUserProfileData(buttonQuery.getMessage().getChatId().intValue(), user);

            callBackAnswer = SendMessage.builder()
                    .text("Как Вас величать?")
                    .chatId(chatId)
                    .build();
        }
        else if (buttonQuery.getData().equals("ПОСМОТРЕТЬ"))
        {
            callBackAnswer = sendAnswerCallbackQuery("Ожидается в будущих обновлениях", true, buttonQuery);

        } else if (buttonQuery.getData().equals("ЗАПОЛНИТЬ") || buttonQuery.getData().equals("ИЗМЕНИТЬ"))
        {
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_GENDER);
            callBackAnswer = SendMessage.builder()
                    .text("Заполните анкету. Для продолжения апишите любое слово")
                    .chatId(chatId)
                    .build();


        } else if (buttonQuery.getData().equals("СУДАРЯ"))
        {
            callBackAnswer = sendAnswerCallbackQuery("Ожидается в будущих обновлениях", true, buttonQuery);
        } else if (buttonQuery.getData().equals("СУДАРЫНЮ"))
        {
            callBackAnswer = sendAnswerCallbackQuery("Ожидается в будущих обновлениях", true, buttonQuery);
        } else if (buttonQuery.getData().equals("ВСЕХ"))
        {
            callBackAnswer = sendAnswerCallbackQuery("Ожидается в будущих обновлениях", true, buttonQuery);
        } else
        {
            userDataCache.setUsersCurrentBotState(userId, BotState.SHOW_MAIN_MENU);
        }
        return callBackAnswer;
    }

    private AnswerCallbackQuery sendAnswerCallbackQuery(String text, boolean alert, CallbackQuery callbackquery)
    {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callbackquery.getId());
        answerCallbackQuery.setShowAlert(alert);
        answerCallbackQuery.setText(text);
        return answerCallbackQuery;
    }
}
