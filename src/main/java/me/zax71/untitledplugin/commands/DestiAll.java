package me.zax71.untitledplugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import me.zax71.untitledplugin.untitledPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

@CommandAlias("destiAll|ALlFactionDesti")
public class DestiAll extends BaseCommand {


    @Default
    @CommandPermission("untitledplugin.destiall")
    @CommandCompletion("@players")
    public static void onDestiAll(CommandSender sender, String[] args) {

        // Define empty player object
        Player senderPlayer = null;

        // If there are no arguments...
        if (args.length == 0) {
            // ...set senderPlayer to the sender
            senderPlayer = (Player) sender;
            // if there are arguments
        } else {
            // if the player is online
            if (Bukkit.getOnlinePlayers().toString().toLowerCase().contains(args[0].toLowerCase())) {
                senderPlayer = Bukkit.getPlayer(args[0]);
            }


            if (senderPlayer != null) {
                if (senderPlayer.hasPermission("group.biome1")) {
                    Location hub = new Location(Bukkit.getWorld("world"), -516, 166, 446, -180, 0);
                    senderPlayer.teleport(hub);
                } else if (senderPlayer.hasPermission("group.biome2")) {
                    Location hub = new Location(Bukkit.getWorld("world"), 577, 164, -135, -180, 0);
                    senderPlayer.teleport(hub);
                } else if (senderPlayer.hasPermission("group.biome3")) {
                    Location hub = new Location(Bukkit.getWorld("world"), -548, 167, 375, -180, 0);
                    senderPlayer.teleport(hub);
                } else if (senderPlayer.hasPermission("group.biome4")) {
                    Location hub = new Location(Bukkit.getWorld("world"), 372, 164, 546, -180, 0);
                    senderPlayer.teleport(hub);
                }
            } else {
                untitledPlugin.plugin.getLogger().warning("/destiAll must be used with an online player");
                sender.sendMessage(untitledPlugin.plugin.prefix + "Must be used with an online player");
            }
        }


    }
}
