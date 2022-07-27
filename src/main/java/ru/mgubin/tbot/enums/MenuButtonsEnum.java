package ru.mgubin.tbot.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum MenuButtonsEnum {
    PROFILE("АНКЕТА"),
    SEARCH("ПОИСК"),
    LOVERS("ЛЮБИМЦЫ"),
    HELP("ПОМОЩЬ");

    private static final Map<String, MenuButtonsEnum> MENU_BUTTONS = new HashMap<>();
    private final String menu;

    static {
        for (MenuButtonsEnum menu : values()) {
            MENU_BUTTONS.put(menu.menu, menu);
        }
    }

    public String getButtonName() {
        return menu;
    }

    private MenuButtonsEnum(String buttonName) {
        this.menu = buttonName;
    }

    public static MenuButtonsEnum valueOfMenuButtons(String menu) {
        for (MenuButtonsEnum prevNext1 : values()) {
            if (prevNext1.menu.equals(menu)) {
                return prevNext1;
            }
        }
        return null;
    }

    public static List<MenuButtonsEnum> valuesExceptMenuButtons(String menu) {
        List<MenuButtonsEnum> genderList = new ArrayList<>();
        for (MenuButtonsEnum prevNext1 : values()) {
            if (!prevNext1.menu.equals(menu)) {
                genderList.add(prevNext1);
            }
        }
        return genderList;
    }

    public static List<String> valuesMenuButtons() {
        List<String> genderList = new ArrayList<>();
        for (MenuButtonsEnum prevNext1 : values()) {

            genderList.add(prevNext1.menu);

        }
        return genderList;
    }

    public static MenuButtonsEnum valueOfLabel(String buttonName) {
        return MENU_BUTTONS.get(buttonName);
    }
}
