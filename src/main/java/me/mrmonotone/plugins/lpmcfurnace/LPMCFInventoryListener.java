package me.mrmonotone.plugins.lpmcfurnace;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryListener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class LPMCFInventoryListener extends InventoryListener {

    private LPMCFurnace plugin;

    public LPMCFInventoryListener(LPMCFurnace plugin) {
        this.plugin = plugin;
    }
    static final int RAW_SLOT = 0; //furnace slots
    static final int FUEL_SLOT = 1;
    static final int PRODUCT_SLOT = 2;

    @Override
    public void onFurnaceBurn(final FurnaceBurnEvent event) {

        if (event.isCancelled()) {

            return;

        }

        Block block = event.getFurnace();

        Inventory inv = ((Furnace) block.getState()).getInventory();
        final ItemStack fuel = inv.getItem(FUEL_SLOT);
        ItemStack raw = inv.getItem(RAW_SLOT);
        
        if (plugin.bucketreturn == true) {
            
            if (event.getFuel().getType() == Material.LAVA_BUCKET) {
                
                final Block furnace = event.getFurnace();
                
                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                    public void run() {
                        Furnace furn = (Furnace) furnace.getState();
                        furn.getInventory().setItem(1, new ItemStack(Material.BUCKET, 1));
                    }
                
                });
            }
        }

        if (plugin.clockinInv == true) {

            if (fuel.getAmount() == 1 && raw.getAmount() != 0) { //fully working

                for (final Player player : plugin.ActivatedFurnace.keySet()) { //gets everyone player who choose that furnace

                    if (player.hasPermission("lpmcfurnace.message")) {

                        if (player.getInventory().contains(347)) {

                            if (plugin.ActivatedFurnace.get(player) == block) {//gets the player and compares the 2 furnaces

                                int burnTime = event.getBurnTime();

                                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                                    public void run() {

                                        player.sendMessage(ChatColor.DARK_RED + "Your furnace is out of " + fuel.getType().toString().toLowerCase() + "!");//sends message
                                    }
                                }, burnTime);
                            }
                        }


                    } else {

                        player.sendMessage(ChatColor.DARK_RED + "You need a clock to recieve messages!");

                    }
                }

            } else {

                for (Player player : plugin.ActivatedFurnace.keySet()) {

                    player.sendMessage(ChatColor.RED + "You don't have permission!");

                }
            }
        } else {

            if (fuel.getAmount() == 1 && raw.getAmount() != 0) { //fully working

                for (final Player player : plugin.ActivatedFurnace.keySet()) { //gets everyone player who choose that furnace

                    if (player.hasPermission("lpmcfurnace.message")) {

                        if (player.getInventory().contains(347)) {

                            if (plugin.ActivatedFurnace.get(player) == block) {//gets the player and compares the 2 furnaces

                                int burnTime = event.getBurnTime();

                                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                                    public void run() {

                                        player.sendMessage(ChatColor.DARK_RED + "Your furnace is out of " + fuel.getType().toString().toLowerCase() + "!");//sends message
                                    }
                                }, burnTime);
                            }
                        }


                    } else {

                        player.sendMessage(ChatColor.DARK_RED + "You need a clock to recieve messages!");

                    }
                }

            } else {

                for (Player player : plugin.ActivatedFurnace.keySet()) {

                    player.sendMessage(ChatColor.RED + "You don't have permission!");

                }
            }
        }
    }

    @Override
    public void onFurnaceSmelt(FurnaceSmeltEvent event) {

        if (event.isCancelled()) {

            return;

        }

        final Block block = event.getFurnace();//gets furnace as a block

        Inventory inv = ((Furnace) block.getState()).getInventory();//gets furnaces inventory

        ItemStack raw = inv.getItem(RAW_SLOT); //sets raw slot
        ItemStack fuel = inv.getItem(FUEL_SLOT);//sets fuel slot
        ItemStack product = inv.getItem(PRODUCT_SLOT);//sets product slot

        if (plugin.clockinInv == true) {

            if (raw.getAmount() == 1 && fuel.getAmount() >= 0) {

                for (Player player : plugin.ActivatedFurnace.keySet()) {

                    if (player.hasPermission("lpmcfurnace.message")) {

                        if (plugin.ActivatedFurnace.get(player) == block) {

                            if (player.getInventory().contains(347)) {

                                player.sendMessage(ChatColor.DARK_RED + "Your furnace is out of " + raw.getType().toString().toLowerCase() + "!");

                            } else {

                                player.sendMessage(ChatColor.DARK_RED + "You need a clock to recieve messages!");

                            }
                        }

                    } else {

                        player.sendMessage(ChatColor.RED + "You don't have permission!");

                    }
                }

                if (raw.getAmount() == 1 && product.getAmount() >= 0) { //not tested

                    for (Player player : plugin.ActivatedFurnace.keySet()) {

                        if (player.hasPermission("lpmcfurnace.message")) {

                            if (plugin.ActivatedFurnace.get(player) == block) {

                                if (player.getInventory().contains(347)) {

                                    player.sendMessage(ChatColor.DARK_PURPLE + "Your " + raw.getType().toString().toLowerCase() + " is done!");

                                } else {

                                    player.sendMessage(ChatColor.DARK_RED + "You need a clock to recieve messages!");

                                }
                            }

                        } else {

                            player.sendMessage(ChatColor.RED + "You don't have permission!");

                        }
                    }
                }
            }

        } else {

            if (plugin.clockinInv == false) {

                if (raw.getAmount() == 1 && fuel.getAmount() >= 0) {

                    for (Player player : plugin.ActivatedFurnace.keySet()) {

                        if (player.hasPermission("lpmcfurnace.message")) {

                            if (plugin.ActivatedFurnace.get(player) == block) {

                                player.sendMessage(ChatColor.DARK_RED + "Your furnace is out of " + raw.getType().toString().toLowerCase() + "!");
                            }

                        } else {

                            player.sendMessage(ChatColor.RED + "You don't have permission!");

                        }
                    }
                }

                if (raw.getAmount() == 1 && product.getAmount() >= 0) { //not tested

                    for (Player player : plugin.ActivatedFurnace.keySet()) {

                        if (player.hasPermission("lpmcfurnace.message")) {
                            if (plugin.ActivatedFurnace.get(player) == block) {

                                player.sendMessage(ChatColor.DARK_PURPLE + "Your " + raw.getType().toString().toLowerCase() + " is done!");
                            }

                        } else {

                            player.sendMessage(ChatColor.RED + "You don't have permission!");

                        }
                    }
                }
            }
        }
    }
}
