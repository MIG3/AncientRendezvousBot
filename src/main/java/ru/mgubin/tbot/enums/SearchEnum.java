package ru.mgubin.tbot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum SearchEnum {
    MEN("Сударя"),
    WOMEN("Сударыню"),
    ALL("Всех");

    private static final Map<String, SearchEnum> GENDER_TYPE = new HashMap<>();

    public final String searchGender;

    static {
        for (SearchEnum gender : values()) {
            GENDER_TYPE.put(gender.searchGender, gender);
        }
    }

    public static SearchEnum valueOfSearchButtons(String gender) {
        for (SearchEnum gender1 : values()) {
            if (gender1.searchGender.equals(gender)) {
                return gender1;
            }
        }
        return null;
    }

    public static List<SearchEnum> valuesExceptSearchButtons(String gender) {
        List<SearchEnum> genderList = new ArrayList<>();
        for (SearchEnum gender1 : values()) {
            if (!gender1.searchGender.equals(gender)) {
                genderList.add(gender1);
            }
        }
        return genderList;
    }

    public static List<String> valuesSearchButtons() {
        List<String> genderList = new ArrayList<>();
        for (SearchEnum gender1 : values()) {

            genderList.add(gender1.searchGender);

        }
        return genderList;
    }
    public static boolean existValueOfSearchButtons(String gender) {
        SearchEnum searchEnum = valueOfSearchButtons(gender);
        return searchEnum != null;
    }

    public static SearchEnum valueOfLabel(String buttonName) {
        return GENDER_TYPE.get(buttonName);
    }
}
