package ru.mgubin.tbot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserList {
    private List<User> users = new ArrayList<>();
}
