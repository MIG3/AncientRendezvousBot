package ru.mgubin.tbot.command.lovers;

import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.entity.SearchProfile;
import ru.mgubin.tbot.enums.CrushNavigationEnum;
import ru.mgubin.tbot.keyboard.InlineKeyboard;
import ru.mgubin.tbot.service.LabelGenerateService;
import ru.mgubin.tbot.service.PrintProfileService;

public abstract class NavigationCrushCommand {

    /**
     * Метод генерации ответа при навигации по любимцам
     * @param userId id ползователя
     * @param profile анкета-изображение любимца
     * @param crushProfile профиль любимца
     * @param userDataCache кэш данных пользователя
     * @param index номер текущей анкеты любимца в списке
     * @return ответ для пользователя
     */
    public OutputParameters getAnswer(Long userId, PrintProfileService profile, SearchProfile crushProfile, UserDataCache userDataCache, int index) {
        crushProfile.setNumberProfile(index);
        LabelGenerateService labelGenerateService = new LabelGenerateService(userDataCache);
        String label = labelGenerateService.labelFromPicture(userId, crushProfile.getUserList().get(index).getId());
        OutputParameters outputParameters = new OutputParameters();
        outputParameters.setSendPhoto(profile.sendPhoto(
                userId,
                crushProfile.getUserList().get(crushProfile.getNumberProfile()),
                label));
        outputParameters.setSendMessage(new InlineKeyboard().keyboard(userId, "Для перелистывания любимок нажмите вперед или назад", CrushNavigationEnum.valuesPrevNextButtons()));
        return outputParameters;
    }
}
