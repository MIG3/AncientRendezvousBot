package ru.mgubin.tbot.exception;

public class ServicePictureException extends RuntimeException {
    public ServicePictureException() {
        super("Сервис генерации изображения не доступен!");
    }
}
