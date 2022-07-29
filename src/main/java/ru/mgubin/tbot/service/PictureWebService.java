package ru.mgubin.tbot.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import ru.mgubin.tbot.exception.ServicePictureException;
import ru.mgubin.tbot.exception.PictureException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static ru.mgubin.tbot.constant.Constants.PICTURE_URL;

@Slf4j
public class PictureWebService {

    /**
     * Формирование изображения для вывода его в боте.
     *
     * @param text - описание пользователя самого себя
     * @return изображение в InputStream
     */
    public InputStream makePicture(String text) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject pictureJsonObject = new JSONObject();
            pictureJsonObject.put("text", text);
            HttpEntity<String> request = new HttpEntity<>(pictureJsonObject.toString(), headers);
            byte[] picture =
                    restTemplate.postForObject(PICTURE_URL, request, byte[].class);
            if (picture == null) {
                throw new PictureException();
            }
            return new ByteArrayInputStream(picture);
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw new ServicePictureException();
        }
    }
}
