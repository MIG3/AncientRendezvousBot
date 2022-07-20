package ru.mgubin.tbot.keyboard;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.mgubin.tbot.enums.ProfileButtons;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboard
{
    public SendMessage keyboard(Long chatId, String ask, Enum[] inlineKeyboardClass)
    {
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        List<List<InlineKeyboardButton>> listButtons = new ArrayList<>();
       // List<?> list = Arrays.asList(inlineKeyboardClass.getEnumConstants());
        /*for (int i = 0; i < list.size(); i++)
        {*/
        for (Enum dd : inlineKeyboardClass)
        {
            buttons.add(InlineKeyboardButton.builder()
                    .text(dd.name())
                    .callbackData("" + dd)
                    .build());
        }
        //if (count == 2)
            listButtons.add(buttons);
       /* else
        {
            buttons.add(InlineKeyboardButton.builder()
                    .text("ВСЕ")
                    .callbackData("ВСЕ")
                    .build());
            listButtons.add(buttons);
        }*/
        return SendMessage.builder()
                            .text(ask)
                            .chatId(chatId)
                            .replyMarkup(InlineKeyboardMarkup.builder().keyboard(listButtons).build())
                            .build();
    }

    public SendMessage questKeyboard(Long chatId, String ask)
    {
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        List<List<InlineKeyboardButton>> listButtons = new ArrayList<>();
        for (ProfileButtons profileButtons : ProfileButtons.values())
        {
            buttons.add(InlineKeyboardButton.builder()
                    .text(profileButtons.name())
                    .callbackData("" + profileButtons)
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
