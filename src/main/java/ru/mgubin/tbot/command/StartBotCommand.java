package ru.mgubin.tbot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.keyboard.MainMenuKeyboard;

import static ru.mgubin.tbot.constant.Constants.CHOOSE_MAIN_MENU;

public class StartBotCommand implements Command {
    /**
     * Выполняется при первом запуске бота.
     * Генерирует постоянное меню.
     *
     * @param userId        id пользователя
     * @param message       сообщение
     * @param userDataCache кэш данных пользователя
     * @return Краткое информационное сообщение
     */
    @Override
    public OutputParameters invoke(Long userId, String message, UserDataCache userDataCache) {
        OutputParameters outputParameters = new OutputParameters();
        MainMenuKeyboard menuService = new MainMenuKeyboard();
        SendMessage sendMessage = menuService.getMainMenuMessage(userId, CHOOSE_MAIN_MENU);
        outputParameters.setSendMessage(sendMessage);
        return outputParameters;
    }
}
