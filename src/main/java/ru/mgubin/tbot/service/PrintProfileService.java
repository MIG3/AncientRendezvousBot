package ru.mgubin.tbot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ru.mgubin.tbot.entity.User;

import java.io.InputStream;
import java.util.StringJoiner;

import static ru.mgubin.tbot.constant.Constants.FILE_NAME;

@Slf4j
public class PrintProfileService{
    PictureWebService pictureWebService = new PictureWebService();

    /**
     * Метод печати анкеты пользователя
     *
     * @param chatId - id чата
     * @param user   сущность пользователя
     * @param love   - любим или не любим
     * @return анкета-изображение
     */
    public SendPhoto sendPhoto(long chatId, User user, String love) {
        InputStream picture = pictureWebService.makePicture(user.getDescription());
        StringJoiner label = new StringJoiner(", ");
        label.add(user.getGender().getGender());
        label.add(user.getFullName());
        if (!StringUtils.isEmpty(love)) {
            label.add(love);
        }
        return SendPhoto.builder()
                .photo(new InputFile(picture, FILE_NAME))
                .caption(label.toString())
                .chatId(chatId)
                .build();
    }
}
