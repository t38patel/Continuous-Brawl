package General;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;


public class Spawn implements Listener {
	
	public static List<String> spawn = new ArrayList<String>();
	
	@EventHandler
	public void join(PlayerJoinEvent joinEvent) 
	{
		Player joiner = joinEvent.getPlayer();
		
		spawn.add(joiner.getName());
		
		joiner.teleport(new Location(joiner.getWorld(), -664, 81, 335));
		
		joiner.sendMessage(ChatColor.GREEN + "===== Welcome to UberPvP! =====");
		joiner.sendMessage(ChatColor.GREEN + "Report any bugs you find to Uber please!");
		joiner.sendMessage(ChatColor.GREEN + "Enjoy!");
		
		joiner.setGameMode(GameMode.SURVIVAL) ;
		joiner.getInventory().setHelmet(null);
		joiner.getInventory().setChestplate(null);
		joiner.getInventory().setLeggings(null);
		joiner.getInventory().setBoots(null);
		joiner.getInventory().clear();
		joiner.setMaxHealth(40);
		joiner.setHealth(40);
		joiner.setFoodLevel(20);
		joiner.setExp(0);
		
		ItemStack rules = new ItemStack(Material.PAPER);
		ItemMeta rulesMeta = rules.getItemMeta();
		rulesMeta.setDisplayName(ChatColor.DARK_GREEN + "Rules");
		rules.setItemMeta(rulesMeta);
		
		ItemStack kits = new ItemStack(Material.FEATHER);
		ItemMeta kitsMeta = kits.getItemMeta();
		kitsMeta.setDisplayName(ChatColor.DARK_GREEN + "Kits");
		kits.setItemMeta(kitsMeta);
		
		joiner.getInventory().setItem(0, rules);
		joiner.getInventory().setItem(4, kits);
	}
	
	@EventHandler
	public void respawn(PlayerRespawnEvent respawnEvent) 
	{			
		Player respawnedPlayer = respawnEvent.getPlayer();
		
		spawn.add(respawnedPlayer.getName());
		
		for (PotionEffect effect : respawnedPlayer.getActivePotionEffects())
		{
			respawnedPlayer.removePotionEffect(effect.getType());
		}
		
		respawnedPlayer.getInventory().setHelmet(null);
		respawnedPlayer.getInventory().setChestplate(null);
		respawnedPlayer.getInventory().setLeggings(null);
		respawnedPlayer.getInventory().setBoots(null);
		respawnedPlayer.setHealth(40);
		respawnedPlayer.setFoodLevel(20);
		
		ItemStack rules = new ItemStack(Material.PAPER);
		ItemMeta rulesMeta = rules.getItemMeta();
		rulesMeta.setDisplayName(ChatColor.DARK_GREEN + "Rules");
		rules.setItemMeta(rulesMeta);
		
		ItemStack kits = new ItemStack(Material.FEATHER);
		ItemMeta kitsMeta = kits.getItemMeta();
		kitsMeta.setDisplayName(ChatColor.DARK_GREEN + "Kits");
		kits.setItemMeta(kitsMeta);
		
		respawnedPlayer.getInventory().setItem(0, rules);
		respawnedPlayer.getInventory().setItem(4, kits);
	}
	
}


