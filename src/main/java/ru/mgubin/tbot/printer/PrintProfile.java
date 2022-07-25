package ru.mgubin.tbot.printer;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ru.mgubin.tbot.entity.User;

import java.io.InputStream;

public class PrintProfile
{
    public SendPhoto sendPhoto(long chatId, User user)
    {
        PictureWebService pictureWebService = new PictureWebService();

        InputStream picture = pictureWebService.makePicture(user.getDescription());

        return SendPhoto.builder()
                .photo(new InputFile(picture, "picture.png"))
                .caption(user.getGender() + ", " + user.getFullName())
                .chatId(chatId)
                .build();
    }
}
