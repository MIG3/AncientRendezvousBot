package ru.mgubin.tbot.db;

import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.service.UserList;

import java.util.List;

import static ru.mgubin.tbot.service.Constants.DB_URL;

@Service
@ToString
@NoArgsConstructor
public class UserDB
{
    public void createUser(User user)
    {
        try
        {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<String> request = new HttpEntity<>(user.toJson(), headers);
            restTemplate.postForObject(DB_URL + "/persons", request, String.class);

        } catch (RuntimeException e)
        {
            throw new RuntimeException(e);
        }
    }

    public List<User> getUsersByGender(int userId)
    {
        try
        {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            UserList response = restTemplate.getForObject(
                    DB_URL + "/persons_crush/" + userId,
                    UserList.class);

            return response.getUsers();

        } catch (RuntimeException e)
        {
            throw new RuntimeException(e);
        }
    }
}
