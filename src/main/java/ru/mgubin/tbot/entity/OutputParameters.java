package ru.mgubin.tbot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OutputParameters {
    private SendMessage sm;
    private SendPhoto sp;
}
