package ru.mgubin.tbot.exception;

public class PictureException extends RuntimeException {
    public PictureException() {
        super("Изображение с сервера не получено!");
    }
}
