package ru.mgubin.tbot.keyboard;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InlineKeyboard {
    /**
     * Универсальный метод формирования inline кнопок (то есть привязанных к сообщению в чате)
     *
     * @param chatId         идентификатор чата
     * @param ask            вопрос пользователю от бота
     * @param inlineKeyboard список строк названий кнопок в зависимости от вопроса
     * @return сообщение с кнопками
     */
    public SendMessage keyboard(long chatId, String ask, List<String> inlineKeyboard) {
       List<InlineKeyboardButton> listButtons = inlineKeyboard
                .stream()
                .map(s -> InlineKeyboardButton.builder()
                        .text(s)
                        .callbackData(s)
                        .build())
                .collect(Collectors.toList());
        return SendMessage.builder()
                .text(ask)
                .chatId(chatId)
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(List.of(listButtons)).build())
                .build();
    }
}
