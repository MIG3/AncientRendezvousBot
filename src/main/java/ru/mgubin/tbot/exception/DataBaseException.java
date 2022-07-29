package ru.mgubin.tbot.exception;

public class DataBaseException extends RuntimeException {
    public DataBaseException() {
        super("Изображение с сервера не получено!");
    }
}
