package ru.mgubin.tbot.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum SearchButtonsEnum {
    MEN("Сударя"),
    WOMEN("Сударыню"),
    ALL("Всех");

    private static final Map<String, SearchButtonsEnum> GENDER_TYPE = new HashMap<>();

    public final String gender;

    static {
        for (SearchButtonsEnum gender : values()) {
            GENDER_TYPE.put(gender.gender, gender);
        }
    }

    public String getButtonName() {
        return gender;
    }

    private SearchButtonsEnum(String buttonName) {
        this.gender = buttonName;
    }

    public static SearchButtonsEnum valueOfSearchButtons(String gender) {
        for (SearchButtonsEnum gender1 : values()) {
            if (gender1.gender.equals(gender)) {
                return gender1;
            }
        }
        return null;
    }

    public static List<SearchButtonsEnum> valuesExceptSearchButtons(String gender) {
        List<SearchButtonsEnum> genderList = new ArrayList<>();
        for (SearchButtonsEnum gender1 : values()) {
            if (!gender1.gender.equals(gender)) {
                genderList.add(gender1);
            }
        }
        return genderList;
    }

    public static List<String> valuesSearchButtons() {
        List<String> genderList = new ArrayList<>();
        for (SearchButtonsEnum gender1 : values()) {

            genderList.add(gender1.gender);

        }
        return genderList;
    }

    public static SearchButtonsEnum valueOfLabel(String buttonName) {
        return GENDER_TYPE.get(buttonName);
    }
}
