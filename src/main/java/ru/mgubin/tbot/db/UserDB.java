package ru.mgubin.tbot.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mgubin.tbot.entity.PersToPers;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.exception.ParseToJsonException;
import ru.mgubin.tbot.service.UserList;

import java.util.ArrayList;
import java.util.List;

import static ru.mgubin.tbot.service.Constants.DB_URL;

@Service
@ToString
@NoArgsConstructor
public class UserDB
{
    /**
     * Отправка объекта пользователя в Json формате в БД
     * @param user - сущность пользователя
     * @throws ParseToJsonException если не смог распарсить сущность
     */
    public void createUser(User user)
    {
        try
        {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(user.toJson(), headers);
            restTemplate.postForObject(DB_URL + "/persons", request, String.class);

        } catch (RuntimeException e)
        {
            throw new ParseToJsonException();
        }
    }

    /**
     * Получение списка пользователей из БД по его идентификатору
     * @param userId - идентификатор пользователя
     * @return - список пользователей
     * @throws ParseToJsonException если не смог распарсить сущность
     */
    public List<User> getUsersByGender(int userId)
    {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            List<User> response = restTemplate.getForObject(
                    DB_URL + "/persons_crush/" + userId,
                    List.class);
            List<User> userList = new ArrayList<>();
            for(Object item : response)
            {
                ObjectMapper mapper = new ObjectMapper();
                userList.add(mapper.convertValue(item, User.class));
            }
            return userList;

        } catch (RuntimeException e)
        {
            throw new ParseToJsonException();
        }
    }

    /**
     * Получение клиента по его гендорным предпочтениям
     *
     * @param userId  id клиента
     * @param crushId id любимца
     * @return List<User> список клиентов
     * @throws ParseToJsonException если не смог распарсить сущность
     */
    public Boolean isCrushLikeUser(int userId, int crushId) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Boolean response = restTemplate.getForObject(
                    DB_URL + "/crushes/" + crushId + "/" + userId,
                    Boolean.class);
            return response;
        } catch (RuntimeException e) {
            throw new ParseToJsonException();
        }
    }

    /**
     * Создание связи для интерсект таблицы клиентов
     *
     * @param persToPers Сущность связей между клиентами
     * @throws ParseToJsonException если не смог распарсить сущность
     */
    public void makeLikeToUser(PersToPers persToPers) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(persToPers.toJson(), headers);
            restTemplate.postForObject(DB_URL + "/crushes", request, String.class);
        } catch (RuntimeException e) {
            throw new ParseToJsonException();
        }
    }

    /**
     * Удаление связи для интерсект таблицы клиентов
     *
     * @param persToPers Сущность связей между клиентами
     * @throws ParseToJsonException если не смог распарсить сущность
     */
    public void removeLikeToUser(PersToPers persToPers) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(persToPers.toJson(), headers);
            restTemplate.exchange(DB_URL + "/crushes", HttpMethod.DELETE, request, String.class);
        } catch (RuntimeException e) {
            throw new ParseToJsonException();
        }
    }
}
