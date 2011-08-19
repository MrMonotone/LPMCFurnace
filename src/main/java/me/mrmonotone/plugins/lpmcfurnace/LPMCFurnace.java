package me.mrmonotone.plugins.lpmcfurnace;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Type;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.util.config.Configuration;

public class LPMCFurnace extends JavaPlugin {
    
    public Listener LPMCFInventoryListener;
    public Listener LPMCFPlayerListener;
    public static LPMCFurnace plugin;
    public Configuration config;
    public static final Logger logger = Logger.getLogger("Minecraft");
    public LPMCFInventoryListener inventorylistener = new LPMCFInventoryListener(this);//Defines Inventory Listener #1
    public LPMCFPlayerListener playerlistener = new LPMCFPlayerListener(this);
    public Set<Player> Furnaceusers = new HashSet(); //Defines HashMap
    public Map<Player, Boolean> debug = new HashMap<Player, Boolean>(); //Defines HashMap Debugee
    public Map<Player, Block> ActivatedFurnace = new HashMap<Player, Block>();
    public Boolean clockinInv;
    public Boolean clockinHand;
    public Boolean bucketreturn;
    
    @Override
    public void onDisable() {
        
        PluginDescriptionFile pdfFile = this.getDescription();
        
        LPMCFurnace.logger.info(pdfFile.getName() + " V." + pdfFile.getVersion() + " by " + pdfFile.getAuthors() + " is disabled");
        
    }
    
    @Override
    public void onEnable() {
        
        PluginManager pm = getServer().getPluginManager();
        
        pm.registerEvent(Type.FURNACE_SMELT, this.inventorylistener, Event.Priority.Monitor, this);
        pm.registerEvent(Type.FURNACE_BURN, this.inventorylistener, Event.Priority.Monitor, this);
        pm.registerEvent(Type.PLAYER_INTERACT, this.playerlistener, Event.Priority.Monitor, this);
        
        PluginDescriptionFile pdfFile = this.getDescription();
        
        LPMCFurnace.logger.info(pdfFile.getName() + " V" + pdfFile.getVersion() + " by " + pdfFile.getAuthors() + " is enabled");
        
        config = getConfiguration();
        clockinInv = config.getBoolean("Clock in inventory to recieve messages", true);
        clockinHand = config.getBoolean("Clock in hand for commands", true);
        bucketreturn = config.getBoolean("Return lava buckets?", false);
        config.save();
        
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        
        if (commandLabel.equalsIgnoreCase("setfurnace")) {
            
            if (sender.hasPermission("lpmcfurnace.selectfurnace")) {
                
                toggleVision((Player) sender);
                return true;
                
            } else {
                
                sender.sendMessage(ChatColor.DARK_RED + "You don't have permission!");
                return true;
            }
            
        }
        
        return false;
        
    }
    
    public void toggleVision(Player player) {
        
        if (enabled(player)) {

            //removes player from hashmap
            player.sendMessage(ChatColor.BLUE + "LPMCFurnace is disabled");
            ActivatedFurnace.keySet().remove(player);
            
        } else {

            //puts player in hashmap
            this.Furnaceusers.add(player);
            player.sendMessage(ChatColor.BLUE + "Please left click your desired furnace.");
            
        }
        
    }
    
    public boolean enabled(Player player) {
        
        return ActivatedFurnace.keySet().contains(player);
        
    }

    //Checks to see if Player is player
    boolean isuser(Player player) {
        
        return Furnaceusers.contains(player);
        
    }
}