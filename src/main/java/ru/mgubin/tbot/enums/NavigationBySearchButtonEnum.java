package ru.mgubin.tbot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum NavigationBySearchButtonEnum {
    DISLIKES("ТРУПЪ"),
    LIKES("ЛЮБО");
    private static final Map<String, NavigationBySearchButtonEnum> LIKE_DISLIKE_BUTTONS = new HashMap<>();

    public final String likeDislike;

    static {
        for (NavigationBySearchButtonEnum likeDislike : values()) {
            LIKE_DISLIKE_BUTTONS.put(likeDislike.likeDislike, likeDislike);
        }
    }

    public static NavigationBySearchButtonEnum valueOfLikeDislikeButtons(String prevNext) {
        for (NavigationBySearchButtonEnum likeDislike : values()) {
            if (likeDislike.likeDislike.equals(prevNext)) {
                return likeDislike;
            }
        }
        return null;
    }

    public static List<NavigationBySearchButtonEnum> valuesExceptLikeDislikeButtons(String prevNext) {
        List<NavigationBySearchButtonEnum> likeList = new ArrayList<>();
        for (NavigationBySearchButtonEnum like : values()) {
            if (!like.likeDislike.equals(prevNext)) {
                likeList.add(like);
            }
        }
        return likeList;
    }

    public static List<String> valuesLikeDislikeButtons() {
        List<String> navigateCrushList = new ArrayList<>();
        for (NavigationBySearchButtonEnum prevNext1 : values()) {

            navigateCrushList.add(prevNext1.likeDislike);

        }
        return navigateCrushList;
    }

    public static NavigationBySearchButtonEnum valueOfLabel(String buttonName) {
        return LIKE_DISLIKE_BUTTONS.get(buttonName);
    }
}
