package ru.mgubin.tbot.command.profile;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotStateEnum;

public class AskNameCommand implements Command {
    /**
     * Сохраняет в сущность клиента его имя, полученное полученное на вопрос с предыдущего шага.
     * Состояние бота меняется на ASK_INFO - описание себя
     * Сущность пользователя записывается в мапу кеша пользователей с ключем - id
     *
     * @param userId        id клиента
     * @param message       сообщение от пользователя боту
     * @param userDataCache кэш данных пользователя
     * @return вопрос, ответ на который запишется на следующем шаге
     */
    @Override
    public OutputParameters invoke(Long userId, String message, UserDataCache userDataCache) {
        OutputParameters outputParameters = new OutputParameters();
        User profileData = userDataCache.getUserProfileData(userId);
        profileData.setFullName(message);
        outputParameters.setSendMessage(SendMessage.builder()
                .text("Напишите пару слов о себе")
                .chatId(userId)
                .build());
        userDataCache.setUsersCurrentBotState(userId, BotStateEnum.ASK_INFO);
        userDataCache.saveUserProfileData(userId, profileData);
        return outputParameters;
    }
}
