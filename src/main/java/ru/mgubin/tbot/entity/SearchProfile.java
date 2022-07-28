package ru.mgubin.tbot.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchProfile {
    List<User> userList = new ArrayList<>();
    private int numberProfile;

    public void fillUserList(List<User> userList) {
        this.userList.addAll(userList);
    }
}
