package ru.mgubin.tbot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ru.mgubin.tbot.entity.User;

import java.io.InputStream;

import static ru.mgubin.tbot.constant.Constants.FILE_NAME;

@Service
public class PrintProfile
{
    PictureWebService pictureWebService = new PictureWebService();

    public SendPhoto sendPhoto(long chatId, User user, String label)
    {
        InputStream picture = pictureWebService.makePicture(user.getDescription());

        return SendPhoto.builder()
                .photo(new InputFile(picture, FILE_NAME))
                .caption(user.getGender() + ", " + user.getFullName() + ", " + label)
                .chatId(chatId)
                .build();
    }
}
