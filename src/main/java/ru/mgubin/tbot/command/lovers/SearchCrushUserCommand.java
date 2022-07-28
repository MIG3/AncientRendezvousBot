package ru.mgubin.tbot.command.lovers;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mgubin.tbot.cash.UserDataCache;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.db.UserDB;
import ru.mgubin.tbot.entity.OutputParameters;
import ru.mgubin.tbot.entity.SearchProfile;
import ru.mgubin.tbot.entity.User;
import ru.mgubin.tbot.enums.NavigationByCrushButtonEnum;
import ru.mgubin.tbot.keyboard.InlineKeyboard;
import ru.mgubin.tbot.service.LabelGenerateService;
import ru.mgubin.tbot.service.PrintProfileService;

public class SearchCrushUserCommand implements Command {
    private final UserDataCache userDataCache;

    @Autowired
    public SearchCrushUserCommand(UserDataCache userDataCache) {
        this.userDataCache = userDataCache;
    }

    /**
     * Метод, который вызывается при нажатии кнопки "ЛЮБИМЦЫ".
     * В нём получает список анкет из БД, записывает в мапу по id пользователя.
     * Вызывается метод генерации части подписи по статусу лайков,
     * после вызывается метод печати первой анкеты из списка.
     * Выводятся кнопки для перебора анкет.
     *
     * @param userId  id пользователя
     * @param message сообщение
     * @return анкета изображение и кнопки для перебора
     */
    @Override
    public OutputParameters invoke(Long userId, String message) {
        OutputParameters outputParameters = new OutputParameters();
        UserDB userDB = new UserDB();
        PrintProfileService profile = new PrintProfileService();
        SearchProfile crushProfile = new SearchProfile();
        crushProfile.fillUserList(userDB.getLovers(userId));
        userDataCache.saveUserListData(userId, crushProfile);
        User user = crushProfile.getUserList().get(crushProfile.getNumberProfile());
        LabelGenerateService labelGenerateService = new LabelGenerateService(userDataCache);
        String label = labelGenerateService.labelFromPicture(userId, crushProfile.getUserList().get(0).getId());
        outputParameters.setSendPhoto(profile.sendPhoto(
                userId,
                user,
                label));
        outputParameters.setSendMessage(new InlineKeyboard().keyboard(userId, "Для перелистывания любимок нажмите вперед или назад", NavigationByCrushButtonEnum.valuesPrevNextButtons()));
        return outputParameters;
    }
}
