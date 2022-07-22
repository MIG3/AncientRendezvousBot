package ru.mgubin.tbot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.mgubin.tbot.keyboard.MainMenuKeyboard;

public class StartBotCommand implements Command
{

    @Override
    public OutputParameters invoke(Message message)
    {
        OutputParameters outputParameters = new OutputParameters();
        MainMenuKeyboard menuService = new MainMenuKeyboard();
        SendMessage message2 = menuService.getMainMenuMessage(message.getChatId(), "Воспользуйтесь главным меню");
        outputParameters.setSm(message2);
        return outputParameters;
    }
}
