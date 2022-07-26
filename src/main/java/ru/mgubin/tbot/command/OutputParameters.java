package ru.mgubin.tbot.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import ru.mgubin.tbot.keyboard.InlineKeyboard;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OutputParameters
{
    private InlineKeyboard ik;
    private SendMessage sm;
    private SendPhoto sp;
}
