package ru.mgubin.tbot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Getter
@RequiredArgsConstructor
public enum ProfileButtonsEnum {
    WRITE("ЗАПОЛНИТЬ"),
    UPDATE("ИЗМЕНИТЬ"),
    BROWSE("ПОСМОТРЕТЬ");

    private static final Map<String, ProfileButtonsEnum> PROFILE_BUTTONS = new HashMap<>();

    public final String profile;

    static {
        for (ProfileButtonsEnum profile : values()) {
            PROFILE_BUTTONS.put(profile.profile, profile);
        }
    }

    public static ProfileButtonsEnum valueOfProfileButtons(String profile) {
        for (ProfileButtonsEnum profile1 : values()) {
            if (profile1.profile.equals(profile)) {
                return profile1;
            }
        }
        return null;
    }

    public static List<ProfileButtonsEnum> valuesExceptProfileButtons(String profile) {
        List<ProfileButtonsEnum> genderList = new ArrayList<>();
        for (ProfileButtonsEnum profile1 : values()) {
            if (!profile1.profile.equals(profile)) {
                genderList.add(profile1);
            }
        }
        return genderList;
    }

    public static List<String> valuesProfileButtons() {
        List<String> genderList = new ArrayList<>();
        for (ProfileButtonsEnum profile1 : values()) {

            genderList.add(profile1.profile);

        }
        return genderList;
    }

    public static ProfileButtonsEnum valueOfLabel(String buttonName) {
        return PROFILE_BUTTONS.get(buttonName);
    }
}
