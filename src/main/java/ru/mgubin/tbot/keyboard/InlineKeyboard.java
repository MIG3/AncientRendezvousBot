package ru.mgubin.tbot.keyboard;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.mgubin.tbot.enums.GenderButtons;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboard
{
    /**
     * Метод формирования inline кнопок (то есть привязанных к сообщению в чате)
     *
     * @param chatId         идентификатор чата
     * @param ask            вопрос пользователю от бота
     * @param inlineKeyboard массив enum названий кнопок в зависимости от вопроса
     * @return сообщение с кнопками
     */
    public SendMessage keyboard(long chatId, String ask, Enum[] inlineKeyboard)
    {
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        List<List<InlineKeyboardButton>> listButtons = new ArrayList<>();


        for (Enum dd : inlineKeyboard)
        {
            buttons.add(InlineKeyboardButton.builder()
                    .text(dd.name())
                    .callbackData("" + dd.name())
                    .build());
        }
        listButtons.add(buttons);
        return SendMessage.builder()
                .text(ask)
                .chatId(chatId)
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(listButtons).build())
                .build();
    }
}
