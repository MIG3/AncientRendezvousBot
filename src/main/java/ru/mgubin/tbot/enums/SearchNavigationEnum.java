package ru.mgubin.tbot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum SearchNavigationEnum {
    DISLIKES("ТРУПЪ"),
    LIKES("ЛЮБО");
    private static final Map<String, SearchNavigationEnum> LIKE_DISLIKE_BUTTONS = new HashMap<>();

    public final String likeDislike;

    static {
        for (SearchNavigationEnum likeDislike : values()) {
            LIKE_DISLIKE_BUTTONS.put(likeDislike.likeDislike, likeDislike);
        }
    }

    public static SearchNavigationEnum valueOfLikeDislikeButtons(String prevNext) {
        for (SearchNavigationEnum likeDislike : values()) {
            if (likeDislike.likeDislike.equals(prevNext)) {
                return likeDislike;
            }
        }
        return null;
    }

    public static List<SearchNavigationEnum> valuesExceptLikeDislikeButtons(String prevNext) {
        List<SearchNavigationEnum> likeList = new ArrayList<>();
        for (SearchNavigationEnum like : values()) {
            if (!like.likeDislike.equals(prevNext)) {
                likeList.add(like);
            }
        }
        return likeList;
    }

    public static List<String> valuesLikeDislikeButtons() {
        List<String> navigateCrushList = new ArrayList<>();
        for (SearchNavigationEnum prevNext1 : values()) {

            navigateCrushList.add(prevNext1.likeDislike);

        }
        return navigateCrushList;
    }

    public static SearchNavigationEnum valueOfLabel(String buttonName) {
        return LIKE_DISLIKE_BUTTONS.get(buttonName);
    }
}
