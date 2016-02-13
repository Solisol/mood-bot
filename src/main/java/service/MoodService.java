package service;

import org.pircbotx.hooks.types.GenericMessageEvent;

/**
 * The intent of this class is to parse all messages in chat and when applicable comment on users mood
 */
public class MoodService {

    public static String parseMood(GenericMessageEvent event) {
        if (event.getMessage().contains("yad")) {
            return "Nu går det lite vilt till!";
        }
        return "";
    }
}
