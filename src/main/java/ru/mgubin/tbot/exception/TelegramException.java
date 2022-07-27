package ru.mgubin.tbot.exception;

public class TelegramException extends RuntimeException
{
    public TelegramException()
    {
        super("Сообщение от бота для вывода не получено.");
    }
}
