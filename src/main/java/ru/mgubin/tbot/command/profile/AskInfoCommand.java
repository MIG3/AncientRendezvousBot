package ru.mgubin.tbot.command.profile;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.BotStateEnum;
import ru.mgubin.tbot.enums.SearchButtonsEnum;
import ru.mgubin.tbot.keyboard.InlineKeyboard;

public class AskInfoCommand implements Command {
    private final UserDataCache userDataCache;

    @Autowired
    public AskInfoCommand(UserDataCache userDataCache) {
        this.userDataCache = userDataCache;
    }

    /**
     * Сохраняет в сущность клиента информацию о нём, полученное на вопрос с предыдущего шага.
     * Состояние бота меняется на ASK_CRUSH - вопрос о поле знакомцев для поиска
     * Сущность пользователя записывается в мапу кеша пользователей с ключем - id
     * @param userId id клиента
     * @param message сообщение от пользователя боту
     * @return вопрос, ответ на который запишется на следующем шаге
     */
    @Override
    public OutputParameters invoke(Long userId, String message) {
        InlineKeyboard gender = new InlineKeyboard();
        OutputParameters outputParameters = new OutputParameters();
        User profileData = userDataCache.getUserProfileData(userId);
        profileData.setDescription(message);
        outputParameters.setSm(gender.keyboard(userId, "Кого Вы хотите искать в будущем?", SearchButtonsEnum.valuesSearchButtons()));
        userDataCache.setUsersCurrentBotState(userId, BotStateEnum.ASK_CRUSH);
        userDataCache.saveUserProfileData(userId, profileData);
        return outputParameters;
    }
}
