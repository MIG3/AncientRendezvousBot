package ru.mgubin.tbot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum GenderEnum {
    MEN("Сударь"),
    WOMEN("Сударыня");

    private static final Map<String, GenderEnum> GENDER_TYPE = new HashMap<>();

    public final String gender;

    static {
        for (GenderEnum gender : values()) {
            GENDER_TYPE.put(gender.gender, gender);
        }
    }

    public static GenderEnum valueOfGenderButtons(String gender) {
        for (GenderEnum gender1 : values()) {
            if (gender1.gender.equals(gender)) {
                return gender1;
            }
        }
        return null;
    }

    public static boolean existValueOfGenderButtons(String gender) {
        GenderEnum genderEnum = valueOfGenderButtons(gender);
        return genderEnum != null;
    }

    public static List<GenderEnum> valuesExceptGenderButtons(String gender) {
        List<GenderEnum> genderList = new ArrayList<>();
        for (GenderEnum gender1 : values()) {
            if (!gender1.gender.equals(gender)) {
                genderList.add(gender1);
            }
        }
        return genderList;
    }

    public static List<String> valuesGenderButtons() {
        List<String> genderList = new ArrayList<>();
        for (GenderEnum gender1 : values()) {

            genderList.add(gender1.gender);

        }
        return genderList;
    }

    public static GenderEnum valueOfLabel(String buttonName) {
        return GENDER_TYPE.get(buttonName);
    }
}
