package ru.mgubin.tbot.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.mgubin.tbot.entity.User;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserList
{
    private List<User> users;

    public UserList() {
        users = new ArrayList<>();
    }

}
