package me.zax71.untitledplugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.zax71.untitledplugin.untitledPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@CommandAlias("untitledPlugin|up|uplugin")

public class untitledPluginCommand extends BaseCommand {

    @Subcommand("reload")
    @CommandPermission("untitledplugin.reload")
    @Description("Reloads config.yml")
    public static void onreload(CommandSender sender, String[] args) {

        untitledPlugin.plugin.reloadConfig();
        sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.GREEN + "Successfully reloaded config.yml");
        untitledPlugin.plugin.getLogger().info("Reloaded config.yml");
    }

    @Default
    @HelpCommand
    public static void onUpHelp(CommandSender sender, String[] args) {
        sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.GREEN + "--- Untitled Plugin usage ---");
        sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.GRAY + "/up help" + ChatColor.GREEN + " - This page");
        sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.GRAY + "/up reload" + ChatColor.GREEN + " - Reload config.yml");
        sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.GREEN + "--- /crown usage ---");
        sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.GRAY + "/crown on <player>" + ChatColor.GREEN + " - Puts a crown on the specified player");
        sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.GRAY + "/crown off <player>" + ChatColor.GREEN + " - Takes the crown off the specified player");
    }
}
