package main.java.org.example;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.*;
import java.util.Properties;

public class EntryPoint extends ListenerAdapter {

        public static void main(String[] args) throws InterruptedException {

                // createLight disables unused cache flags
                // GUILD_MESSAGES enables events for messages sent in guilds
                // MESSAGE_CONTENT enables access to the content of messages sent by other users
                // GUILD_MEMBERS gives you access to guild member join events so you can send welcome messages
                // The resulting JDA instance will not cache any members since createLight disables it.
                JDABuilder.createLight(new EntryPoint().readToken(), GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
                        .addEventListeners(new MyListener())
                        .build();

        }

        public String readToken(){

                File file = new File("src/main/resources/bot.properties");
                try {
                        InputStream input = new FileInputStream(file);
                        System.out.println(input);

                        Properties prop = new Properties();
                        prop.load(input);

                        return (String)prop.get("token");

                } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }

        }

}