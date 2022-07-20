package ru.mgubin.tbot.enums;

public enum Menu
{
    PROFILE("АНКЕТА"),
    SEARCH("ПОИСК"),
    LOVERS("ЛЮБИМЦЫ");

    private final String menu;

    Menu(String buttonName)
    {
        this.menu = buttonName;
    }

    public String getButtonName()
    {
        return menu;
    }
}
