package ru.mgubin.tbot.command.search;

import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.entity.PersonCrush;
import ru.mgubin.tbot.entity.SearchProfile;
import ru.mgubin.tbot.enums.SearchNavigationEnum;
import ru.mgubin.tbot.keyboard.InlineKeyboard;
import ru.mgubin.tbot.service.PrintProfileService;

public abstract class NavigationCommand {
    /**
     * Метод получения связи дле следующего любимца
     * @param userId id пользователя
     * @param searchProfile кэш данных пользователя
     * @return сущность связей между клиентами
     */
    public PersonCrush actionNavigate(Long userId, SearchProfile searchProfile) {
        PersonCrush lovers = new PersonCrush();
        int pos = searchProfile.getNumberProfile();
        int lengthUserList = searchProfile.getUserList().size();
        int index;
        if (lengthUserList <= pos + 1) {
            index = 0;
            lovers.setCrushId(searchProfile.getUserList().get(lengthUserList - 1).getId());
        } else {
            index = pos + 1;
            lovers.setCrushId(searchProfile.getUserList().get(pos).getId());
        }
        searchProfile.setNumberProfile(index);
        lovers.setUserId(userId);
        return lovers;
    }

    /**
     * Метод генерации ответа при навигации при поиске анкет
     * @param userId id ползователя
     * @param profile анкета-изображение любимца
     * @param searchProfile профиль пользователя
     * @return ответ для пользователя
     */
    public OutputParameters getAnswer(Long userId, PrintProfileService profile, SearchProfile searchProfile) {
        OutputParameters outputParameters = new OutputParameters();
        outputParameters.setSendPhoto(profile.sendPhoto(
                userId,
                searchProfile.getUserList().get(searchProfile.getNumberProfile()), ""));
        outputParameters.setSendMessage(new InlineKeyboard().keyboard(userId, "Если нравится, нажми вперед, иначе назад", SearchNavigationEnum.valuesLikeDislikeButtons()));
        return outputParameters;
    }
}
