package ru.mgubin.tbot.keyboard;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.mgubin.tbot.enums.MenuButtonsEnum;

import java.util.List;

public class MainMenuKeyboard {
    /**
     * Получает созданное сообщение
     *
     * @param chatId      идентификатор чата
     * @param textMessage сообщение
     * @return сообщение
     */
    public SendMessage getMainMenuMessage(long chatId, String textMessage) {

        ReplyKeyboardMarkup replyKeyboardMarkup = getMainMenuKeyboard(MenuButtonsEnum.valuesMenuButtons());
        return createMessageWithKeyboard(chatId, textMessage, replyKeyboardMarkup);
    }

    /**
     * Метод создания сообщения для постоянной клавиатуры
     *
     * @param chatId              идентификатор чата
     * @param textMessage         сообщение
     * @param replyKeyboardMarkup клавиатура
     * @return сообщение
     */
    private SendMessage createMessageWithKeyboard(long chatId, String textMessage, ReplyKeyboardMarkup replyKeyboardMarkup) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(textMessage);
        if (replyKeyboardMarkup != null) {
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
        }
        return sendMessage;
    }

    /**
     * Метод формирования ReplyKeyboardMarkup кнопок (то есть постоянных, находящихся под полем ввода сообщения)
     *
     * @param menuButtons список строк названий кнопок для меню
     * @return клавиатура
     */
    private ReplyKeyboardMarkup getMainMenuKeyboard(List<String> menuButtons) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        KeyboardRow row = new KeyboardRow();
        for (String menuClick : menuButtons) {
            row.add(KeyboardButton.builder()
                    .text(menuClick)
                    .build());
        }
        replyKeyboardMarkup.setKeyboard(List.of(row));
        return replyKeyboardMarkup;
    }
}
