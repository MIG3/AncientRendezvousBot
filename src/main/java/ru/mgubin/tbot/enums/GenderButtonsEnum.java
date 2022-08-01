package ru.mgubin.tbot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.mgubin.tbot.handler.CallBackButton;
import ru.mgubin.tbot.handler.CallBackGenderButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum GenderButtonsEnum /*implements ButtonStateEnum*/ {
    MEN("Сударь") /*{
        public CallBackButton getCallBackStateAndAnswer() {
            return new CallBackGenderButton();
        }
    }*/,
    WOMEN("Сударыня") /*{
        public CallBackButton getCallBackStateAndAnswer() {
            return new CallBackGenderButton();
        }
    }*/;

    private static final Map<String, GenderButtonsEnum> GENDER_TYPE = new HashMap<>();

    public final String gender;

    static {
        for (GenderButtonsEnum gender : values()) {
            GENDER_TYPE.put(gender.gender, gender);
        }
    }

    public static GenderButtonsEnum valueOfGenderButtons(String gender) {
        for (GenderButtonsEnum gender1 : values()) {
            if (gender1.gender.equals(gender)) {
                return gender1;
            }
        }
        return null;
    }

    public static boolean existValueOfGenderButtons(String gender) {
        GenderButtonsEnum genderButtonsEnum = valueOfGenderButtons(gender);
        return genderButtonsEnum != null;
    }

    public static List<GenderButtonsEnum> valuesExceptGenderButtons(String gender) {
        List<GenderButtonsEnum> genderList = new ArrayList<>();
        for (GenderButtonsEnum gender1 : values()) {
            if (!gender1.gender.equals(gender)) {
                genderList.add(gender1);
            }
        }
        return genderList;
    }

    public static List<String> valuesGenderButtons() {
        List<String> genderList = new ArrayList<>();
        for (GenderButtonsEnum gender1 : values()) {

            genderList.add(gender1.gender);

        }
        return genderList;
    }

    public static GenderButtonsEnum valueOfLabel(String buttonName) {
        return GENDER_TYPE.get(buttonName);
    }

/*    public abstract CallBackButton getCallBackStateAndAnswer();*/
}
