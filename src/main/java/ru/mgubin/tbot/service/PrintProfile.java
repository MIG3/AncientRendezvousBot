package ru.mgubin.tbot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ru.mgubin.tbot.entity.User;

import java.io.InputStream;
import java.util.StringJoiner;

import static ru.mgubin.tbot.constant.Constants.FILE_NAME;

@Service
@Slf4j
public class PrintProfile
{
    PictureWebService pictureWebService = new PictureWebService();

    public SendPhoto sendPhoto(long chatId, User user, String love)
    {

        InputStream picture = pictureWebService.makePicture(user.getDescription());

        StringJoiner label = new StringJoiner(", ");
        label.add(user.getGender().getButtonName());
        label.add(user.getFullName());
        if (!love.equals(""))
        {
            label.add(love);
        }

        return SendPhoto.builder()
                .photo(new InputFile(picture, FILE_NAME))
                .caption(label.toString())
                .chatId(chatId)
                .build();
    }
}
