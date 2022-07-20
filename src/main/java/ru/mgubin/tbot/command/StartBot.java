package ru.mgubin.tbot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.mgubin.tbot.service.MainMenuService;

public class StartBot implements Command
{

    @Override
    public OutputParameters invoke(Long chatId)
    {
        OutputParameters outputParameters = new OutputParameters();
        MainMenuService menuService = new MainMenuService();
        SendMessage message = menuService.getMainMenuMessage(chatId, "воспользуйтесь главным меню");
        outputParameters.setSm(message);
        return outputParameters;
    }
}
