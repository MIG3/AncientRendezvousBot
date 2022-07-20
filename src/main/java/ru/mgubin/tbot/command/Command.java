package ru.mgubin.tbot.command;

public interface Command
{
    OutputParameters invoke(Long chatId);
}
