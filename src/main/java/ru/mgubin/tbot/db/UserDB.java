package ru.mgubin.tbot.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mgubin.tbot.entity.PersToPers;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.exception.ParseToJsonException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static ru.mgubin.tbot.service.Constants.DB_URL;

@Service
@ToString
@NoArgsConstructor
public class UserDB
{
    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    public UserDB(RestTemplate restTemplate)
    {
        headers.setContentType(APPLICATION_JSON);
    }

    /**
     * Отправка объекта клиента в БД
     *
     * @param user - сущность клиента
     * @throws ParseToJsonException если не смог распарсить сущность
     */
    public void createUser(User user)
    {
        try
        {
            HttpEntity<String> request = new HttpEntity<>(user.toJson(), headers);
            restTemplate.postForObject(DB_URL + "/persons", request, String.class);

        } catch (RuntimeException e)
        {
            throw new ParseToJsonException();
        }
    }

    /**
     * Получение клиента по его гендорным предпочтениям
     *
     * @param userId id клиента
     * @return List<User> список клиентов
     * @throws ParseToJsonException если не смог распарсить сущность
     */
    public List<User> getUsersByGender(int userId)
    {
        try
        {
            List<User> response = restTemplate.getForObject(
                    DB_URL + "/persons_crush/" + userId,
                    List.class);
            List<User> userList = new ArrayList<>();
            for (Object item : response)
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
     * Поиск. Узнать, нравится ли любимец клиенту
     *
     * @param userId  id клиента
     * @param crushId id любимца
     * @return true - если клиент нравится, false - если не нравится
     * @throws ParseToJsonException если не смог распарсить сущность
     */
    public Boolean isCrushLikeUser(int userId, int crushId)
    {
        try
        {
            return restTemplate.getForObject(
                    DB_URL + "/crushes/" + crushId + "/" + userId,
                    Boolean.class);
        } catch (RuntimeException e)
        {
            throw new ParseToJsonException();
        }
    }

    /**
     * Создание связи для интерсект таблицы клиентов
     *
     * @param persToPers Сущность связей между клиентами
     * @throws ParseToJsonException если не смог распарсить сущность
     */
    public void makeLikeToUser(PersToPers persToPers)
    {
        try
        {
            HttpEntity<String> request = new HttpEntity<>(persToPers.toJson(), headers);
            restTemplate.postForObject(
                    DB_URL + "/crushes/" + persToPers.getUserId() + "/" + persToPers.getCrushId(),
                    request,
                    String.class);
        } catch (RuntimeException e)
        {
            throw new ParseToJsonException();
        }
    }

    /**
     * Удаление связи для интерсект таблицы клиентов
     *
     * @param persToPers Сущность связей между клиентами
     * @throws ParseToJsonException если не смог распарсить сущность
     */
    public void removeLikeToUser(PersToPers persToPers)
    {
        try
        {
            HttpEntity<String> request = new HttpEntity<>(persToPers.toJson(), headers);
            restTemplate.exchange(
                    DB_URL + "/crushes/" + persToPers.getUserId() + "/" + persToPers.getCrushId(),
                    HttpMethod.DELETE,
                    request,
                    String.class);
        } catch (RuntimeException e)
        {
            throw new ParseToJsonException();
        }
    }

    /**
     * Возврат сущности клиента
     *
     * @param userId id пользователя
     * @throws ParseToJsonException если не смог распарсить сущность
     */
    public User getUser(int userId)
    {
        try
        {
            return restTemplate.getForObject(
                    DB_URL + "/persons/" + userId,
                    User.class);

        } catch (RuntimeException e)
        {
            throw new ParseToJsonException();
        }
    }
}
