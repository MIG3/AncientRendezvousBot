package ru.mgubin.tbot.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum LikeDislikeButtonEnum {
    DISLIKES("ТРУПЪ"),
    LIKES("ЛЮБО");
    private static final Map<String, LikeDislikeButtonEnum> PREV_NEXT_BUTTONS = new HashMap<>();

    public final String prevNext;

    static {
        for (LikeDislikeButtonEnum prevNext : values()) {
            PREV_NEXT_BUTTONS.put(prevNext.prevNext, prevNext);
        }
    }

    public String getButtonName() {
        return prevNext;
    }

    private LikeDislikeButtonEnum(String buttonName) {
        this.prevNext = buttonName;
    }

    public static LikeDislikeButtonEnum valueOfPrevNextButtons(String prevNext) {
        for (LikeDislikeButtonEnum prevNext1 : values()) {
            if (prevNext1.prevNext.equals(prevNext)) {
                return prevNext1;
            }
        }
        return null;
    }

    public static List<LikeDislikeButtonEnum> valuesExceptPrevNextButtons(String prevNext) {
        List<LikeDislikeButtonEnum> likeList = new ArrayList<>();
        for (LikeDislikeButtonEnum prevNext1 : values()) {
            if (!prevNext1.prevNext.equals(prevNext)) {
                likeList.add(prevNext1);
            }
        }
        return likeList;
    }

    public static List<String> valuesPrevNextButtons() {
        List<String> genderList = new ArrayList<>();
        for (LikeDislikeButtonEnum prevNext1 : values()) {

            genderList.add(prevNext1.prevNext);

        }
        return genderList;
    }

    public static LikeDislikeButtonEnum valueOfLabel(String buttonName) {
        return PREV_NEXT_BUTTONS.get(buttonName);
    }
}
