package ru.mgubin.tbot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum CrushNavigationEnum {
    PREV("НАЗАД"),
    NEXT("ВПЕРЁД");
    private static final Map<String, CrushNavigationEnum> PREV_NEXT_BUTTONS = new HashMap<>();

    public final String prevNext;

    static {
        for (CrushNavigationEnum prevNext : values()) {
            PREV_NEXT_BUTTONS.put(prevNext.prevNext, prevNext);
        }
    }

    public static CrushNavigationEnum valueOfPrevNextButtons(String prevNext) {
        for (CrushNavigationEnum prevNext1 : values()) {
            if (prevNext1.prevNext.equals(prevNext)) {
                return prevNext1;
            }
        }
        return null;
    }

    public static List<CrushNavigationEnum> valuesExceptPrevNextButtons(String prevNext) {
        List<CrushNavigationEnum> genderList = new ArrayList<>();
        for (CrushNavigationEnum prevNext1 : values()) {
            if (!prevNext1.prevNext.equals(prevNext)) {
                genderList.add(prevNext1);
            }
        }
        return genderList;
    }

    public static List<String> valuesPrevNextButtons() {
        List<String> genderList = new ArrayList<>();
        for (CrushNavigationEnum prevNext1 : values()) {

            genderList.add(prevNext1.prevNext);

        }
        return genderList;
    }

    public static CrushNavigationEnum valueOfLabel(String buttonName) {
        return PREV_NEXT_BUTTONS.get(buttonName);
    }
}
