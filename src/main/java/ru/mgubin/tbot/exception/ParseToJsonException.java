package ru.mgubin.tbot.exception;

public class ParseToJsonException extends RuntimeException {
    public ParseToJsonException() {
        super("Не могу распарсить в json формат!");
    }
}
