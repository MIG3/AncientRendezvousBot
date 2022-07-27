package ru.mgubin.tbot.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import ru.mgubin.tbot.entity.PersonCrush;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.exception.ParseToJsonException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static ru.mgubin.tbot.constant.Constants.DB_URL;

@ToString
@Slf4j
@NoArgsConstructor
public class UserDB {
    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    public UserDB(RestTemplate restTemplate) {
        headers.setContentType(APPLICATION_JSON);
    }

    /**
     * Отправка объекта клиента в БД
     *
     * @param user - сущность клиента
     * @throws ParseToJsonException если не смог распарсить сущность
     */
    public void createUser(User user) {
        try {
            headers.setContentType(APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(user.toJson(), headers);
            restTemplate.postForObject(DB_URL + "/persons", request, String.class);
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
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
    public List<User> getUsersByGender(long userId) {
        try {
            List<User> response = restTemplate.getForObject(
                    DB_URL + "/persons_crush/" + userId,
                    List.class);
            List<User> userList = new ArrayList<>();
            for (Object item : response) {
                ObjectMapper mapper = new ObjectMapper();
                userList.add(mapper.convertValue(item, User.class));
            }
            return userList;

        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
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
    public Boolean isCrushLikeUser(long userId, long crushId) {
        try {
            return restTemplate.getForObject(
                    DB_URL + "/crushes/" + crushId + "/" + userId,
                    Boolean.class);
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw new ParseToJsonException();
        }
    }

    /**
     * Создание связи для интерсект таблицы клиентов
     *
     * @param personCrush Сущность связей между клиентами
     * @throws ParseToJsonException если не смог распарсить сущность
     */
    public void makeLikeToUser(PersonCrush personCrush) {
        try {
            headers.setContentType(APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(personCrush.toJson(), headers);
            restTemplate.postForObject(DB_URL + "/crushes", request, String.class);
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw new ParseToJsonException();
        }
    }

    /**
     * Удаление связи для интерсект таблицы клиентов
     *
     * @param personCrush Сущность связей между клиентами
     * @throws ParseToJsonException если не смог распарсить сущность
     */
    public void removeLikeToUser(PersonCrush personCrush) {
        try {
            restTemplate.delete(DB_URL + "/crushes/" + personCrush.getUserId() + "/" + personCrush.getCrushId());
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw new ParseToJsonException();
        }
    }

    /**
     * Возврат сущности клиента
     *
     * @param userId id пользователя
     * @return User - клиент
     * @throws ParseToJsonException если не смог распарсить сущность
     */
    public User getUser(long userId) {
        try {
            return restTemplate.getForObject(
                    DB_URL + "/persons/" + userId,
                    User.class);

        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw new ParseToJsonException();
        }
    }

    /**
     * Любимцы. Связи между клиентом и любимцем
     *
     * @param userId id пользователя
     * @return List<User> список клиентов
     * @throws ParseToJsonException если не смог распарсить сущность
     */
    public List<PersonCrush> getUserAndCrush(Long userId, Long crushId) {
        try {
            List<PersonCrush> personCrushList = restTemplate.getForObject(
                    DB_URL + "/lovers/" + userId + "/" + crushId,
                    List.class);
            List<PersonCrush> userList = new ArrayList<>();
            for (Object item : personCrushList) {
                ObjectMapper mapper = new ObjectMapper();
                userList.add(mapper.convertValue(item, PersonCrush.class));
            }
            return userList;
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw new ParseToJsonException();
        }
    }

    /**
     * Любимцы. Список любимцев, которые нравились клиенту, выбрали клиента или был взаимный выбор.
     *
     * @param userId id пользователя
     * @return List<User> список клиентов
     * @throws ParseToJsonException если не смог распарсить сущность
     */
    public List<User> getLovers(Long userId) {
        try {
            List<User> response = restTemplate.getForObject(
                    DB_URL + "/lovers/" + userId,
                    List.class);
            List<User> userList = new ArrayList<>();
            for (Object item : response) {
                ObjectMapper mapper = new ObjectMapper();
                userList.add(mapper.convertValue(item, User.class));
            }
            return userList;
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw new ParseToJsonException();
        }
    }

}
