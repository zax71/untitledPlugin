package me.zax71.untitledplugin;

import co.aikar.commands.PaperCommandManager;
import me.zax71.untitledplugin.commands.DestiAll;
import me.zax71.untitledplugin.commands.crown;
import me.zax71.untitledplugin.commands.hub;
import me.zax71.untitledplugin.commands.untitledPluginCommand;
import me.zax71.untitledplugin.events.PlayerPortal;
import me.zax71.untitledplugin.events.playerDeath;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class untitledPlugin extends JavaPlugin {

    public static untitledPlugin plugin;
    public String prefix = ChatColor.GOLD + "[" + ChatColor.GRAY + "Untitled Plugin" + ChatColor.GOLD + "] " + ChatColor.RESET;

    @Override
    public void onEnable() {

        // Config
        plugin = this;
        saveDefaultConfig();
        FileConfiguration config = getConfig();

        // Register commands
        PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new crown());
        commandManager.registerCommand(new untitledPluginCommand());
        commandManager.registerCommand(new hub());
        commandManager.registerCommand(new DestiAll());

        // Register events
        getServer().getPluginManager().registerEvents(new playerDeath(), this);
        getServer().getPluginManager().registerEvents(new PlayerPortal(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
