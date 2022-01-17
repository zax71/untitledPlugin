package me.zax71.untitledplugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.zax71.untitledplugin.untitledPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("hub|tpHub|toHub")

public class hub extends BaseCommand {

    @Default
    @CommandPermission("untitledplugin.hub")
    public static void onHub(CommandSender sender, String[] args) {

        // Cast sender in to a Player object
        Player senderPlayer = (Player) sender;

        // Get config
        String world = untitledPlugin.plugin.getConfig().getString("hubLocation.world");
        String titleText = untitledPlugin.plugin.getConfig().getString("hubLocation.titleText");
        double x = untitledPlugin.plugin.getConfig().getDouble("hubLocation.x");
        double y = untitledPlugin.plugin.getConfig().getDouble("hubLocation.y");
        double z = untitledPlugin.plugin.getConfig().getDouble("hubLocation.z");
        double pitchD = untitledPlugin.plugin.getConfig().getDouble("hubLocation.pitch");
        double yawD = untitledPlugin.plugin.getConfig().getDouble("hubLocation.yaw");

        // Change pitch and yaw to floats
        float pitch = (float) pitchD;
        float yaw = (float) yawD;


        if (senderPlayer.getWorld().toString().equalsIgnoreCase("purgatory")) {
            sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.RED + "You can't be in " + ChatColor.GRAY + "purgatory " + ChatColor.RED + "when running " + ChatColor.GRAY + "/hub");
        } else if (!(senderPlayer.getHealth() >= senderPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue())){
            sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.RED + "You must be at max health to go to hub");
        }
        else {

            sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.GREEN + "Going to hub");

            Location hub = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
            senderPlayer.teleport(hub);
        }

    }

    @Subcommand("set")
    @CommandPermission("untitledplugin.hub.set")
    @Description("Sets the position of hub")
    @Syntax("<x> <y> <z> <pitch> <yaw> <world>")
    @CommandCompletion("@range:10 @range:10 @range:10 @range:10 @range:10 @worlds")
    public void onHubSet(CommandSender sender, String[] args) {

        // Cast sender in to a Player object
        Player senderPlayer = (Player) sender;

        if (args.length == 0) {
            double pX = senderPlayer.getLocation().getX();
            double pY = senderPlayer.getLocation().getY();
            double pZ = senderPlayer.getLocation().getZ();

            float pPitch = senderPlayer.getLocation().getPitch();
            float pYaw = senderPlayer.getLocation().getYaw();

            String pWorld = senderPlayer.getLocation().getWorld().getName();


            untitledPlugin.plugin.getConfig().set("hubLocation.x", Math.round(pX));
            untitledPlugin.plugin.getConfig().set("hubLocation.y", Math.round(pY));
            untitledPlugin.plugin.getConfig().set("hubLocation.z", Math.round(pZ));
            untitledPlugin.plugin.getConfig().set("hubLocation.pitch", Math.round(pPitch));
            untitledPlugin.plugin.getConfig().set("hubLocation.yaw", Math.round(pYaw));
            untitledPlugin.plugin.getConfig().set("hubLocation.world", pWorld);

        } else {
            /*
            untitledPlugin.plugin.getConfig().set("hubLocation.x", args[0]);
            untitledPlugin.plugin.getConfig().set("hubLocation.y", args[1]);
            untitledPlugin.plugin.getConfig().set("hubLocation.z", args[2]);
            untitledPlugin.plugin.getConfig().set("hubLocation.pitch", args[3]);
            untitledPlugin.plugin.getConfig().set("hubLocation.yaw", args[4]);
            untitledPlugin.plugin.getConfig().set("hubLocation.world", args[5]);
               */
            sender.sendMessage(untitledPlugin.plugin.prefix + "You cannot set the hub with numbers as of now");
        }


        untitledPlugin.plugin.saveConfig();

        sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.GREEN + "Successfully updated position");
    }

    @Subcommand("teleport")
    @CommandPermission("untitledplugin.hub.teleport")
    @Description("Teleport a player to the hub")
    @CommandCompletion("@players")
    public static void onHubTeleport(CommandSender sender, String[] args) {

        // Get player
        Player player = Bukkit.getServer().getPlayer(args[0]);

        // Get config
        String world = untitledPlugin.plugin.getConfig().getString("hubLocation.world");
        String titleText = untitledPlugin.plugin.getConfig().getString("hubLocation.titleText");
        double x = untitledPlugin.plugin.getConfig().getDouble("hubLocation.x");
        double y = untitledPlugin.plugin.getConfig().getDouble("hubLocation.y");
        double z = untitledPlugin.plugin.getConfig().getDouble("hubLocation.z");
        double pitchD = untitledPlugin.plugin.getConfig().getDouble("hubLocation.pitch");
        double yawD = untitledPlugin.plugin.getConfig().getDouble("hubLocation.yaw");

        // Change pitch and yaw to floats
        float pitch = (float) pitchD;
        float yaw = (float) yawD;

        Location hub = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
        player.teleport(hub);
    }




    @HelpCommand
    public static void onHubHelp(CommandSender sender, String[] args) {
        sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.GREEN + "--- /hub Usage ---");
        sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.GRAY + "/hub help" + ChatColor.GREEN + " - This page");
        sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.GRAY + "/hub" + ChatColor.GREEN + " - Teleport to the hub world");

        if (sender.hasPermission("untitledplugin.hub.admin")) {
            sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.GREEN + "--- /hub Admin Usage ---");
            sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.GRAY + "/hub set <x> <y> <z> <pitch> <yaw> <world>" + ChatColor.GREEN + " - Sets the hub at your position, optionally at coordinates");
            sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.GRAY + "/hub teleport (player name)" + ChatColor.GREEN + " - Teleport a player to the hub world");
        }
    }
}