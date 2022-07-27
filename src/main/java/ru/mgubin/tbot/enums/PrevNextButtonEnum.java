package ru.mgubin.tbot.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum PrevNextButtonEnum
{
    NEXT("ВПЕРЁД"),
    PREV("НАЗАД");
    private static final Map<String, PrevNextButtonEnum> PREV_NEXT_BUTTONS = new HashMap<>();

    public final String prevNext;

    static
    {
        for (PrevNextButtonEnum prevNext : values())
        {
            PREV_NEXT_BUTTONS.put(prevNext.prevNext, prevNext);
        }
    }

    public String getButtonName()
    {
        return prevNext;
    }

    private PrevNextButtonEnum(String buttonName)
    {
        this.prevNext = buttonName;
    }

    public static PrevNextButtonEnum valueOfPrevNextButtons(String prevNext)
    {
        for (PrevNextButtonEnum prevNext1 : values())
        {
            if (prevNext1.prevNext.equals(prevNext))
            {
                return prevNext1;
            }
        }
        return null;
    }

    public static List<PrevNextButtonEnum> valuesExceptPrevNextButtons(String prevNext)
    {
        List<PrevNextButtonEnum> genderList = new ArrayList<>();
        for (PrevNextButtonEnum prevNext1 : values())
        {
            if (!prevNext1.prevNext.equals(prevNext))
            {
                genderList.add(prevNext1);
            }
        }
        return genderList;
    }
    public static List<String> valuesPrevNextButtons()
    {
        List<String> genderList = new ArrayList<>();
        for (PrevNextButtonEnum prevNext1 : values())
        {

            genderList.add(prevNext1.prevNext);

        }
        return genderList;
    }
    public static PrevNextButtonEnum valueOfLabel(String buttonName)
    {
        return PREV_NEXT_BUTTONS.get(buttonName);
    }
}
