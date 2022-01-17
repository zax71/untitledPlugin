package me.zax71.untitledplugin.events;



import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import me.zax71.untitledplugin.untitledPlugin;

import java.util.List;

public class playerDeath implements Listener {

    final Component mainTitle = Component.text("Welcome To Purgatory!", NamedTextColor.WHITE);
    final Component subtitle = Component.text("");
    final Component actionBarText = Component.text(ChatColor.RED + "RUN RUN RUN");


    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        String deathWorld = p.getWorld().getName();
        List<?> respawnWorlds = untitledPlugin.plugin.getConfig().getList("purgatory");


        if(respawnWorlds != null) {
            if (respawnWorlds.contains(deathWorld)) {

                untitledPlugin.plugin.getLogger().info(p.getName() + " is respawning in purgatory");

                Location respawnWorld = new Location(Bukkit.getWorld("purgatory"), 0, 63, 0);
                e.setRespawnLocation(respawnWorld);

                final Title title = Title.title(mainTitle, subtitle);

                p.showTitle(title);
                p.sendActionBar(actionBarText);
            }
        } else {
            untitledPlugin.plugin.getLogger().severe("The list for worlds that you die in in config is empty! change it and run /up reload");
        }

    }
}
