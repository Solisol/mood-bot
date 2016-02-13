package service;

import com.google.common.collect.ImmutableSortedSet;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class PingService {

    /**
     * Given an event and a bot, create a ping message for that channel
     */
    public static String getPingMessage(GenericMessageEvent event) {
        //TODO improve channel selection
        PircBotX bot = event.getBot();
        ImmutableSortedSet<String> usersNicks = bot.getUserChannelDao().getChannel("#yadda").getUsersNicks();
        StringBuilder names = new StringBuilder();
        for (String name :
                usersNicks) {
            names.append(name + " ");
        }
        return names.toString();
    }
}
