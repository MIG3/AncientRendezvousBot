package ru.mgubin.tbot.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.handler.CallBackGenderButton;
import ru.mgubin.tbot.handler.CallBackSearchButton;
import ru.mgubin.tbot.handler.CallBackWithoutMesButton;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum GeneralButtonStateEnum {
    GENDER_MEN("Сударь") {
        @Override
        public BotApiMethod<?> getCallBackStateAndAnswer(CallbackQuery callbackQuery, UserDataCache userDataCache) {
            return new CallBackGenderButton(userDataCache).genderButton(callbackQuery, userDataCache);
        }
    },
    GENDER_WOMEN("Сударыня") {
        @Override
        public BotApiMethod<?> getCallBackStateAndAnswer(CallbackQuery callbackQuery, UserDataCache userDataCache) {
            return new CallBackGenderButton(userDataCache).genderButton(callbackQuery, userDataCache);
        }
    },
    PROFILE_WRITE("ЗАПОЛНИТЬ") {
        @Override
        public BotApiMethod<?> getCallBackStateAndAnswer(CallbackQuery callbackQuery, UserDataCache userDataCache) {
            return new CallBackWithoutMesButton().buttonWithoutMessage(callbackQuery, userDataCache, "ASK_GENDER");
        }
    },
    PROFILE_UPDATE("ИЗМЕНИТЬ") {
        @Override
        public BotApiMethod<?> getCallBackStateAndAnswer(CallbackQuery callbackQuery, UserDataCache userDataCache) {
            return new CallBackWithoutMesButton().buttonWithoutMessage(callbackQuery, userDataCache, "ASK_GENDER");
        }
    },
    PROFILE_BROWSE("ПОСМОТРЕТЬ") {
        @Override
        public BotApiMethod<?> getCallBackStateAndAnswer(CallbackQuery callbackQuery, UserDataCache userDataCache) {
            return new CallBackWithoutMesButton().buttonWithoutMessage(callbackQuery, userDataCache, "BROWSE_PROFILE");
        }
    },
    SEARCH_FUTURE_MEN("Сударя") {
        @Override
        public BotApiMethod<?> getCallBackStateAndAnswer(CallbackQuery callbackQuery, UserDataCache userDataCache) {
            return new CallBackSearchButton(userDataCache).searchButton(callbackQuery, userDataCache);
        }
    },
    SEARCH_FUTURE_WOMEN("Сударыню") {
        @Override
        public BotApiMethod<?> getCallBackStateAndAnswer(CallbackQuery callbackQuery, UserDataCache userDataCache) {
            return new CallBackSearchButton(userDataCache).searchButton(callbackQuery, userDataCache);
        }
    },
    SEARCH_FUTURE_ALL("Всех") {
        @Override
        public BotApiMethod<?> getCallBackStateAndAnswer(CallbackQuery callbackQuery, UserDataCache userDataCache) {
            return new CallBackSearchButton(userDataCache).searchButton(callbackQuery, userDataCache);
        }
    },
    SEARCH_DISLIKES("ТРУПЪ") {
        @Override
        public BotApiMethod<?> getCallBackStateAndAnswer(CallbackQuery callbackQuery, UserDataCache userDataCache) {
            return new CallBackWithoutMesButton().buttonWithoutMessage(callbackQuery, userDataCache, "PREV_PROFILE");
        }
    },
    SEARCH_LIKES("ЛЮБО") {
        @Override
        public BotApiMethod<?> getCallBackStateAndAnswer(CallbackQuery callbackQuery, UserDataCache userDataCache) {
            return new CallBackWithoutMesButton().buttonWithoutMessage(callbackQuery, userDataCache, "NEXT_PROFILE");
        }
    },
    CRUSH_PREV("НАЗАД") {
        @Override
        public BotApiMethod<?> getCallBackStateAndAnswer(CallbackQuery callbackQuery, UserDataCache userDataCache) {
            return new CallBackWithoutMesButton().buttonWithoutMessage(callbackQuery, userDataCache, "PREV_CRUSH");
        }
    },
    CRUSH_NEXT("ВПЕРЁД") {
        @Override
        public BotApiMethod<?> getCallBackStateAndAnswer(CallbackQuery callbackQuery, UserDataCache userDataCache) {
            return new CallBackWithoutMesButton().buttonWithoutMessage(callbackQuery, userDataCache, "NEXT_CRUSH");
        }
    };

    private static final Map<String, GeneralButtonStateEnum> BUTTON_TYPE = new HashMap<>();
    public final String button;

    static {
        for (GeneralButtonStateEnum button : values()) {
            BUTTON_TYPE.put(button.button, button);
        }
    }

    public static GeneralButtonStateEnum valueOfButtons(String buttonName) {
        for (GeneralButtonStateEnum buttonNameState : values()) {
            if (buttonNameState.button.equals(buttonName)) {
                return buttonNameState;
            }
        }
        return null;
    }

    public abstract BotApiMethod<?> getCallBackStateAndAnswer(CallbackQuery buttonQuery, UserDataCache userDataCache);
}
