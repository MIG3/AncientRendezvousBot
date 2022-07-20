package ru.mgubin.tbot.command;

import lombok.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.mgubin.tbot.keyboard.InlineKeyboard;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OutputParameters
{
    private InlineKeyboard ik;
    private SendMessage sm;
}
