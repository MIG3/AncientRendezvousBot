package ru.mgubin.tbot.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CrushProfile
{
    List<PersonCrush> crushList = new ArrayList<>();

    public void fillUserList(List<PersonCrush> crushList)
    {
        this.crushList.addAll(crushList);
    }
}
