package ru.mgubin.tbot;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.mgubin.tbot.bot.Bot;
import ru.mgubin.tbot.exception.TelegramException;

@Slf4j
public class App {
    public static void main(String[] args) throws TelegramApiException {
        try {
            log.info("Запуск бота");
            String botToken = System.getenv("BOT_TOKEN_TELEGA");
            String botName = System.getenv("BOT_NAME_TELEGA");
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot(botToken, botName));
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
            throw new TelegramException();
        }
    }
}
