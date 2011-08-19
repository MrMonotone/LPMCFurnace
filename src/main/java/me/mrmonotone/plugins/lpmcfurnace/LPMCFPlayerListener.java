package me.mrmonotone.plugins.lpmcfurnace;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

public class LPMCFPlayerListener extends PlayerListener {

    private LPMCFurnace plugin;

    public LPMCFPlayerListener(LPMCFurnace plugin) {

        this.plugin = plugin;

    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {

        if (event.isCancelled()) {
            return;
        }
        
        ItemStack helditem = event.getItem();
        Player player = event.getPlayer();

        if (plugin.clockinHand == true) {

            if (plugin.isuser(player)
                    && event.getAction().equals(Action.LEFT_CLICK_BLOCK)
                    && event.hasBlock()
                    && (helditem != null && helditem.getTypeId() == 347)
                    && (event.getClickedBlock().getType().equals(Material.FURNACE)
                    || event.getClickedBlock().getType().equals(Material.BURNING_FURNACE))) {

                player.sendMessage(ChatColor.DARK_PURPLE + "Furnace Accepted!");

                plugin.Furnaceusers.remove(player);

                if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {

                    plugin.ActivatedFurnace.put(player, event.getClickedBlock());

                }

            } else {

                if (plugin.isuser(player) && (event.getClickedBlock().getType().equals(Material.FURNACE) || event.getClickedBlock().getType().equals(Material.BURNING_FURNACE)) && helditem.getTypeId() != 347) {

                    player.sendMessage(ChatColor.DARK_RED + "You don't have a clock in your hand!");
                    player.sendMessage(ChatColor.DARK_RED + "Please enter the command in and try again.");
                    player.sendMessage(ChatColor.BLUE + "LPMCFurnace Disabled");

                    plugin.Furnaceusers.remove(player);

                } else {

                    if (plugin.isuser(player)) {

                        player.sendMessage(ChatColor.DARK_RED + "This block is not a furnace!");
                        player.sendMessage(ChatColor.DARK_RED + "Please enter the command in and try again.");
                        player.sendMessage(ChatColor.BLUE + "LPMCFurnace Disabled");

                        plugin.Furnaceusers.remove(player);

                    }
                }
            }
        } else {

            if (plugin.clockinHand == false) {

                if (plugin.isuser(player)
                        && event.getAction().equals(Action.LEFT_CLICK_BLOCK)
                        && event.hasBlock()
                        && (event.getClickedBlock().getType().equals(Material.FURNACE)
                        || event.getClickedBlock().getType().equals(Material.BURNING_FURNACE))) {

                    player.sendMessage(ChatColor.DARK_PURPLE + "Furnace Accepted!");

                    plugin.Furnaceusers.remove(player);

                    if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {

                        plugin.ActivatedFurnace.put(player, event.getClickedBlock());

                    }

                } else {

                    if (plugin.isuser(player)) {

                        player.sendMessage(ChatColor.DARK_RED + "This block is not a furnace!");
                        player.sendMessage(ChatColor.DARK_RED + "Please enter the command in and try again.");
                        player.sendMessage(ChatColor.BLUE + "LPMCFurnace Disabled");

                        plugin.Furnaceusers.remove(player);

                    }
                }
            }
        }
    }
}
