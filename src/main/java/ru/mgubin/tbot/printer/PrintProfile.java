package ru.mgubin.tbot.printer;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ru.mgubin.tbot.command.OutputParameters;

import java.io.File;
import java.io.InputStream;

public class PrintProfile
{
    public SendPhoto sendPhoto(long chatId)
    {
        PictureWebService pictureWebService = new PictureWebService();

        InputStream picture = pictureWebService.makePicture("h h");

        return SendPhoto.builder()
                .photo(new InputFile(picture, "picture.png"))
                .caption("Анкета")
                .chatId(chatId)
                .build();
    }
}
