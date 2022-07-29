package ru.mgubin.tbot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum SearchButtonsEnum {
    MEN("Сударя"),
    WOMEN("Сударыню"),
    ALL("Всех");

    private static final Map<String, SearchButtonsEnum> GENDER_TYPE = new HashMap<>();

    public final String searchGender;

    static {
        for (SearchButtonsEnum gender : values()) {
            GENDER_TYPE.put(gender.searchGender, gender);
        }
    }

    public static SearchButtonsEnum valueOfSearchButtons(String gender) {
        for (SearchButtonsEnum gender1 : values()) {
            if (gender1.searchGender.equals(gender)) {
                return gender1;
            }
        }
        return null;
    }

    public static List<SearchButtonsEnum> valuesExceptSearchButtons(String gender) {
        List<SearchButtonsEnum> genderList = new ArrayList<>();
        for (SearchButtonsEnum gender1 : values()) {
            if (!gender1.searchGender.equals(gender)) {
                genderList.add(gender1);
            }
        }
        return genderList;
    }

    public static List<String> valuesSearchButtons() {
        List<String> genderList = new ArrayList<>();
        for (SearchButtonsEnum gender1 : values()) {

            genderList.add(gender1.searchGender);

        }
        return genderList;
    }

    public static SearchButtonsEnum valueOfLabel(String buttonName) {
        return GENDER_TYPE.get(buttonName);
    }
}
