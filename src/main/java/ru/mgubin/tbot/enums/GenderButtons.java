package ru.mgubin.tbot.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum GenderButtons
{
    СУДАРЬ("Сударъ"),
    СУДАРЫНЯ("Сударыня");

    private static final Map<String, GenderButtons> GENDER_TYPE = new HashMap<>();

    public final String gender;

    static
    {
        for (GenderButtons gender : values())
        {
            GENDER_TYPE.put(gender.gender, gender);
        }
    }

    public String getButtonName()
    {
        return gender;
    }

    private GenderButtons(String buttonName)
    {
        this.gender = buttonName;
    }

    public static GenderButtons valueOfGenderButtons(String gender)
    {
        for (GenderButtons gender1 : values())
        {
            if (gender1.gender.equals(gender))
            {
                return gender1;
            }
        }
        return null;
    }

    public static List<GenderButtons> valuesExceptGenderButtons(String gender)
    {
        List<GenderButtons> genderList = new ArrayList<>();
        for (GenderButtons gender1 : values())
        {
            if (!gender1.gender.equals(gender))
            {
                genderList.add(gender1);
            }
        }
        return genderList;
    }

    public static GenderButtons valueOfLabel(String buttonName)
    {
        return GENDER_TYPE.get(buttonName);
    }
}
