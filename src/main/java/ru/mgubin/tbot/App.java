package ru.mgubin.tbot;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.mgubin.tbot.bot.Bot;

@Slf4j
public class App
{
    public static void main( String[] args ) throws Exception
    {
        try
        {
            log.info("Запуск бота");
            String botToken = System.getenv("BOT_TOKEN_TELEGA");
            String botName = System.getenv("BOT_NAME_TELEGA");
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot(botToken, botName));
        }
        catch (TelegramApiException e)
        {
            log.debug(String.valueOf(e));
        }
    }

}
