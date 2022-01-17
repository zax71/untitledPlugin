package me.zax71.untitledplugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.zax71.untitledplugin.untitledPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


@CommandAlias("crown")
public class crown extends BaseCommand {
    @Subcommand("on")
    @CommandPermission("untitledplugin.crown.on")
    @Description("puts a crown on the specified player")
    @CommandCompletion("@players")
    public static void onCrownOn(CommandSender sender, String[] args) {


        Player p = Bukkit.getServer().getPlayer(args[0]); // Person to be crowned


        ItemStack crown = new ItemStack(Material.GOLDEN_HELMET);

        ItemMeta crownMeta = crown.getItemMeta();
        crownMeta.setDisplayName(ChatColor.GREEN + "Crown");

        crownMeta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
        crownMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        crownMeta.setUnbreakable(true);

        crownMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        crownMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        crown.setItemMeta(crownMeta);

        if (p != null) {
            p.getInventory().setHelmet(crown);
            p.sendMessage(untitledPlugin.plugin.prefix + ChatColor.GREEN + "You have been crowned!");
            sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.GREEN + "Crowned " + args[0]);
        } else {
            sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.RED + "Player must be online");
        }

    }

    @Subcommand("off")
    @CommandPermission("untitledplugin.crown.off")
    @Description("takes the crown off the specified player")
    @CommandCompletion("@players")
    public static void onCrownOff(CommandSender sender, String[] args) {


        Player p = Bukkit.getServer().getPlayer(args[0]); // Person to be crowned

        ItemStack crown = new ItemStack(Material.GOLDEN_HELMET);

        ItemMeta crownMeta = crown.getItemMeta();
        crownMeta.setDisplayName(ChatColor.GREEN + "Crown");

        crownMeta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
        crownMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);

        crownMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        crown.setItemMeta(crownMeta);


        if (p != null) { // Is the player online?

            if(p.getInventory().getHelmet() != null) { // Check if there is no helmet

                if (p.getInventory().getHelmet().getType() == Material.GOLDEN_HELMET) { // Is there actually OUR helmet on?
                    p.getInventory().setHelmet(null);
                    p.sendMessage(untitledPlugin.plugin.prefix + ChatColor.RED + "You have been un-crowned!");
                    sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.GREEN + "Uncrowned " + args[0]);
                } else { // Another thing in the helmet slot
                    sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.RED + "The player is not wearing a crown");
                }
            } else { // Nothing in helmet slot
                sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.RED + "The player is not wearing a crown");
            }

        } else { // warn the sender the target is offline
            sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.RED + "Player must be online");
        }
    }

    @Default
    @HelpCommand
    public static void onHelp(CommandSender sender, String[] args) {

        sender.sendMessage(untitledPlugin.plugin.prefix + ChatColor.RED + "Usage: " + ChatColor.GRAY + "/crown <on/off> <player>");
    }

}