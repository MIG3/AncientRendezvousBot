package ru.mgubin.tbot.enums;

public enum MenuButtons
{
    АНКЕТА("АНКЕТА"),
    ПОИСК("ПОИСК"),
    ЛЮБИМЦЫ("ЛЮБИМЦЫ"),
    ПОМОЩЬ("ПОМОЩЬ");

    private final String menu;

    MenuButtons(String buttonName)
    {
        this.menu = buttonName;
    }

    public String getButtonName()
    {
        return menu;
    }
}
