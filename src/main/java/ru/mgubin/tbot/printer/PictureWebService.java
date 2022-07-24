package ru.mgubin.tbot.printer;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class PictureWebService
{
    String createPersonUrl = "https://pict-serv-2-0-fixey-dev.apps.sandbox-m2.ll9k.p1.openshiftapps.com/pict";

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
                    restTemplate.postForObject(createPersonUrl, request, byte[].class);
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
