package net.b0at.preventxyzconnection;

import net.b0at.torsion.FileStorage;
import net.b0at.torsion.Storage;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.List;

/**
 * Created by Jordin on 7/30/2017.
 * Jordin is still best hacker.
 */
public class B0ATPreventXYZConnection extends JavaPlugin implements Listener {
    private String kickMessage;
    private List<String> blacklistedHostnames;

    public B0ATPreventXYZConnection() {
        FileStorage.setBaseDirectory(getDataFolder());
        try {
            Storage<ConnectionConfig> connectionConfigStorage = FileStorage.of(ConnectionConfig.class, "config.yml");
            ConnectionConfig connectionConfig = connectionConfigStorage.load();
            this.kickMessage = connectionConfig.kickMessage;
            this.blacklistedHostnames = connectionConfig.blacklistedHostnames;

            connectionConfigStorage.save(connectionConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        String hostname = e.getHostname().split(":")[0];
        if (blacklistedHostnames.contains(hostname.toLowerCase())) {
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.translateAlternateColorCodes('&', kickMessage));
        }
    }
}
