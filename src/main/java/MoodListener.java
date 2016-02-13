import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.types.GenericMessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.MoodService;
import service.PingService;

import javax.net.ssl.SSLSocketFactory;
import java.time.LocalDateTime;

/**
 * Bot that says hello and tells time
 */
public class MoodListener extends ListenerAdapter {

    private final Logger log = LoggerFactory.getLogger(MoodListener.class);

    private static PircBotX bot;

    @Override
    public void onGenericMessage(GenericMessageEvent event) {
        if (event.getMessage().startsWith(".helloworld")) {
            event.respond("Hello world!");
        } else if (event.getMessage().startsWith(".time")) {
            event.respond(String.valueOf(LocalDateTime.now()));
        } else if (event.getMessage().startsWith(".ping")) {
            String response = PingService.getPingMessage(event);
            event.respond(response);
        } else {
            String response = MoodService.parseMood(event);
            if (!response.isEmpty()) {
                event.respond(response);
            }
        }

    }

    public static void main(String[] args) throws Exception {
        //Configure what we want our bot to do
        Configuration configuration = new Configuration.Builder()
                .setName("yadda")
                .addServer("irc.chalmers.it", 9999)
                .setSocketFactory(SSLSocketFactory.getDefault())
                .addAutoJoinChannel("#yadda")
                .addListener(new MoodListener())
                .buildConfiguration();

        //Create our bot with the configuration
        bot = new PircBotX(configuration);
        //Connect to the server
        bot.startBot();
    }
}