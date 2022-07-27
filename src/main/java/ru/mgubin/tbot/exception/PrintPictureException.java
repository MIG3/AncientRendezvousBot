package ru.mgubin.tbot.exception;

public class PrintPictureException extends RuntimeException
{
    public PrintPictureException()
    {
        super("Изображение с сервера не полуено!");
    }
}
