package ru.mgubin.tbot.command;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface Command
{
    OutputParameters invoke(Message message);
}
