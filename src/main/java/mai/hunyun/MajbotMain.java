package mai.hunyun;

import org.apache.commons.io.FileUtils;
import snw.jkook.command.JKookCommand;
import snw.jkook.plugin.BasePlugin;

import java.io.File;
import java.net.URL;
import java.util.Objects;

public class MajbotMain extends BasePlugin {
    @Override
    public void onLoad() {
        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        //Get file alias.json on every enable.
        try {
            URL url = new URL(Objects.requireNonNull(getConfig().getString("alias_source")));
            FileUtils.copyURLToFile(url, new File("./majbot/alias.json"), 10000, 10000);
        }catch(Exception e) {
            e.printStackTrace();
        }
        getLogger().info("Registering commands...");
        new JKookCommand("song")
                .addSubcommand(new JKookCommand("id")
                        .executesUser((user, objects, message) -> message.reply("")))
                .register(this);
        new JKookCommand("b50")
                .addAlias("best50")
                .executesUser(((user, objects, message) -> {
            message.reply("(font)Hunyun:I can't write it...(font)[info]");
            //TODO: Write codes about best 50...
        })).register(this);
    }

    @Override
    public void onDisable() {
        saveConfig();
    }
}
