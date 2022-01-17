package me.zax71.untitledplugin.events;

import me.zax71.untitledplugin.untitledPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.TitlePart;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerPortal implements Listener {

    @EventHandler
    public void onPlayerPortalEvent(PlayerPortalEvent e) {

        // Get the player
        Player p = e.getPlayer();

        // Stop the portal from working
        e.setCancelled(true);

        // Check if it is enabled
        Boolean enabled = untitledPlugin.plugin.getConfig().getBoolean("worldNetherPortal.enabled");
        if (enabled.equals(true)) {



            // Get config values

            String world = untitledPlugin.plugin.getConfig().getString("worldNetherPortal.world");
            String titleText = untitledPlugin.plugin.getConfig().getString("worldNetherPortal.titleText");
            double x = untitledPlugin.plugin.getConfig().getDouble("worldNetherPortal.x");
            double y = untitledPlugin.plugin.getConfig().getDouble("worldNetherPortal.y");
            double z = untitledPlugin.plugin.getConfig().getDouble("worldNetherPortal.z");
            double pitchD = untitledPlugin.plugin.getConfig().getDouble("worldNetherPortal.pitch");
            double yawD = untitledPlugin.plugin.getConfig().getDouble("worldNetherPortal.yaw");

            // Change pitch and yaw to floats
            float pitch = (float) pitchD;
            float yaw = (float) yawD;


            // Define a location to go to
            Location gotoWorld = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);

            // Check the world they defined actually exists
            if (Bukkit.getWorld(world) != null) {
                // Teleport the player
                p.teleport(gotoWorld);

                // Tell them what on earth just went on...
                if (titleText != null) {
                    p.sendTitlePart(TitlePart.TITLE, MiniMessage.miniMessage().parse(titleText));
                } else {
                    untitledPlugin.plugin.getLogger().warning("worldNetherPortal.titleText in config is null, change it!");
                }

            } else {
                untitledPlugin.plugin.getLogger().severe("World in config is not loaded!");
                p.sendMessage(untitledPlugin.plugin.prefix + ChatColor.RED + "ERROR: World defined in config is not loaded! Contact server owner");
            }


        } else {
            untitledPlugin.plugin.getLogger().info("Someone tried to use a nether portal in world and Untitled Plugin's nether portal redirect feature is disabled. If this is unintended, enable it in config");
            p.sendMessage(untitledPlugin.plugin.prefix + "Nether portals are disabled currently");
        }
    }
}