package ru.mgubin.tbot.command.lovers;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.entity.SearchProfile;
import ru.mgubin.tbot.enums.NavigationByCrushButtonEnum;
import ru.mgubin.tbot.keyboard.InlineKeyboard;
import ru.mgubin.tbot.service.LabelGenerateService;
import ru.mgubin.tbot.service.PrintProfileService;

public class NextCrushCommand implements Command {
    private final UserDataCache userDataCache;

    @Autowired
    public NextCrushCommand(UserDataCache userDataCache) {
        this.userDataCache = userDataCache;
    }
    /**
     * Метод перебора анкет любимцев в прямом порядке - вперёд.
     * В нём получаем список анкет любимцев из кеша,
     * записываем в мапу по id пользователя номер текущего любимца.
     * Вызывается метод генерации части подписи по статусу лайков,
     * после него вызывается метод печати текущей анкеты из списка.
     * Выводятся кнопки для перебора анкет.
     * @param userId id пользователя
     * @param message сообщение
     * @return анкета-изображение и кнопки для перебора
     */
    @Override
    public OutputParameters invoke(Long userId, String message) {
        OutputParameters outputParameters = new OutputParameters();
        PrintProfileService profile = new PrintProfileService();
        SearchProfile crushProfile = userDataCache.getUserListData(userId);
        int lengthUserList = crushProfile.getUserList().size();
        int pos = crushProfile.getNumberProfile();
        int index;
        if (lengthUserList <= pos + 1) {
            index = 0;
        } else {
            index = pos + 1;
        }
        crushProfile.setNumberProfile(index);
        LabelGenerateService labelGenerateService = new LabelGenerateService(userDataCache);
        String label = labelGenerateService.labelFromPicture(userId, crushProfile.getUserList().get(index).getId());
        outputParameters.setSendPhoto(profile.sendPhoto(
                userId,
                crushProfile.getUserList().get(crushProfile.getNumberProfile()),
                label));
        outputParameters.setSendMessage(new InlineKeyboard().keyboard(userId, "Для перелистывания любимок нажмите вперед или назад", NavigationByCrushButtonEnum.valuesPrevNextButtons()));
        return outputParameters;
    }
}
