package ru.mgubin.tbot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Возможные состояния бота
 */
/*@Getter
@RequiredArgsConstructor*/
public enum BotStateEnum {
    ASK_INFO,
    ASK_NAME,
    ASK_GENDER,
    ASK_BIRTHDAY,
    ASK_CRUSH,
    SEARCH,
    SHOW_HELP_MENU,
    NEXT_PROFILE,
    PREV_PROFILE,
    CORRECT_PROFILE,
    BROWSE_PROFILE,
    SAVE_PROFILE,
    CHOICE_PREVorNEXT_BUTTON,
    BROWSE_LOVERS,
    START,
    SHOW_MAIN_MENU,
    NEXT_CRUSH,
    PREV_CRUSH;
   /* ASK_INFO("ASK_INFO"),
    ASK_NAME("ASK_NAME"),
    ASK_GENDER("ASK_GENDER"),
    ASK_BIRTHDAY("ASK_BIRTHDAY"),
    ASK_CRUSH("ASK_CRUSH"),
    SEARCH("SEARCH"),
    SHOW_HELP_MENU("SHOW_HELP_MENU"),
    NEXT_PROFILE("NEXT_PROFILE"),
    PREV_PROFILE("PREV_PROFILE"),
    CORRECT_PROFILE("CORRECT_PROFILE"),
    BROWSE_PROFILE("BROWSE_PROFILE"),
    SAVE_PROFILE("SAVE_PROFILE"),
    CHOICE_PREVorNEXT_BUTTON("CHOICE_PREVorNEXT_BUTTON"),
    BROWSE_LOVERS("BROWSE_LOVERS"),
    START("START"),
    SHOW_MAIN_MENU("SHOW_MAIN_MENU"),
    NEXT_CRUSH("NEXT_CRUSH"),
    PREV_CRUSH("PREV_CRUSH");*/

/*    private static final Map<String, BotStateEnum> STATE_TYPE = new HashMap<>();

    public final String state;

    static {
        for (BotStateEnum state : values()) {
            STATE_TYPE.put(state.state, state);
        }
    }

    public static BotStateEnum valueOfLabel(String state) {
        return BotStateEnum.valueOfLabel(state);
    }*/
}
