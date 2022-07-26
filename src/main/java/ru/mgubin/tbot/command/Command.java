package ru.mgubin.tbot.command;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.mgubin.tbot.entity.User;

import java.util.List;

public interface Command
{
    OutputParameters invoke(Message message);
}
