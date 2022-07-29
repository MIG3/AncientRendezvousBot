package ru.mgubin.tbot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.mgubin.tbot.command.Command;
import ru.mgubin.tbot.command.StartBotCommand;
import ru.mgubin.tbot.command.help.HelpShowCommand;
import ru.mgubin.tbot.command.lovers.BackCrushCommand;
import ru.mgubin.tbot.command.lovers.NextCrushCommand;
import ru.mgubin.tbot.command.lovers.SearchCrushUserCommand;
import ru.mgubin.tbot.command.profile.*;
import ru.mgubin.tbot.command.search.BackCommand;
import ru.mgubin.tbot.command.search.NextCommand;
import ru.mgubin.tbot.command.search.SearchUserCommand;

/**
 * Возможные состояния бота
 */
@Getter
@RequiredArgsConstructor
public enum BotStateEnum {
    SHOW_MAIN_MENU,
    START {
        @Override
        public Command getCommand() {
            return new StartBotCommand();
        }
    },
    SHOW_HELP_MENU {
        @Override
        public Command getCommand() {
            return new HelpShowCommand();
        }
    },
    ASK_GENDER {
        @Override
        public Command getCommand() {
            return new AskGenderCommand();
        }
    },
    ASK_NAME {
        @Override
        public Command getCommand() {
            return new AskNameCommand();
        }
    },
    ASK_INFO {
        @Override
        public Command getCommand() {
            return new AskInfoCommand();
        }
    },
    ASK_CRUSH {
        @Override
        public Command getCommand() {
            return new AskCrushCommand();
        }
    },
    ASK_BIRTHDAY {
        @Override
        public Command getCommand() {
            return new AskBirthdayCommand();
        }
    },
    SEARCH {
        @Override
        public Command getCommand() {
            return new SearchUserCommand();
        }
    },
    NEXT_PROFILE {
        @Override
        public Command getCommand() {
            return new NextCommand();
        }
    },
    PREV_PROFILE {
        @Override
        public Command getCommand() {
            return new BackCommand();
        }
    },
    CORRECT_PROFILE {
        @Override
        public Command getCommand() {
            return new CorrectProfileCommand();
        }
    },
    BROWSE_PROFILE {
        @Override
        public Command getCommand() {
            return new BrowsProfile();
        }
    },
    SAVE_PROFILE,
    BROWSE_CRUSHES {
        @Override
        public Command getCommand() {
            return new SearchCrushUserCommand();
        }
    },
    NEXT_CRUSH {
        @Override
        public Command getCommand() {
            return new NextCrushCommand();
        }
    },
    PREV_CRUSH {
        @Override
        public Command getCommand() {
            return new BackCrushCommand();
        }
    };

    public Command getCommand() {
        return null;
    }
}
