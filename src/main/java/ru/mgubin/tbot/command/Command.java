package ru.mgubin.tbot.command;

import ru.mgubin.tbot.entity.OutputParameters;

public interface Command {
    OutputParameters invoke(Long userId, String message);
}
