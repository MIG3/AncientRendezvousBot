package ru.mgubin.tbot.printer;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static ru.mgubin.tbot.service.Constants.PICTURE_URL;

public class PictureWebService
{

    public InputStream makePicture(String text)
    {
        try
        {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject pictureJsonObject = new JSONObject();
            pictureJsonObject.put("text", text);
            HttpEntity<String> request = new HttpEntity<>(pictureJsonObject.toString(), headers);
            byte[] picture =
                    restTemplate.postForObject(PICTURE_URL, request, byte[].class);
            if (picture == null)
            {
                throw new RuntimeException();
            }
            return new ByteArrayInputStream(picture);
        } catch (RuntimeException e)
        {
            throw new RuntimeException(e);
        }
    }
}
