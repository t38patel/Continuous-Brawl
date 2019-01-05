package Kits;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import General.Spawn;

public class Selection implements Listener {
	
    public static HashMap<String, String> Kits = new HashMap<String, String>();
           
	Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_RED + "" + ChatColor.BOLD + "         Kit Selector");
	
	private void openKitsGUI(Player player) {
	    
		//// Kits start below
		ItemStack trooper = new ItemStack(Material.IRON_SWORD);
		trooper.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		ItemMeta trooperMeta = trooper.getItemMeta();
		trooperMeta.setLore(Arrays.asList(ChatColor.AQUA + "Trooper has a mixture of both offense ",
				  ChatColor.AQUA + "offense and defense; get a gapple every kill!")); 			  
		trooperMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + ChatColor.UNDERLINE + "Trooper"); 
		trooper.setItemMeta(trooperMeta);
		///////////////////////////////////////////////////////////////////////////////////////////////////
		ItemStack shortbow = new ItemStack(Material.BOW);
		shortbow.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
		ItemMeta shortbowMeta = shortbow.getItemMeta();
		shortbowMeta.setLore(Arrays.asList(ChatColor.AQUA + "Dominate the arena with your power ",
			      ChatColor.AQUA + "bow - use your archer skills to snipe foes down.")); 	
		shortbowMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + ChatColor.UNDERLINE + "Shortbow"); 
		shortbow.setItemMeta(shortbowMeta);
		///////////////////////////////////////////////////////////////////////////////////////////////////
		ItemStack tribow = new ItemStack(Material.ARROW);
		tribow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
		ItemMeta tribowMeta = tribow.getItemMeta();
		tribowMeta.setLore(Arrays.asList(ChatColor.AQUA + "Every third snipe deals extra damage! ",
			      ChatColor.AQUA + "Have you got what it takes?")); 	
		tribowMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + ChatColor.UNDERLINE + "Tribow"); 
		tribow.setItemMeta(tribowMeta);		
		
		inv.setItem(0, trooper);
		inv.setItem(1, shortbow);
		inv.setItem(2, tribow);
		
		player.openInventory(inv);
	}
	
	public void randomTeleport(Player player) {
		
		Random rand = new Random();
		int randomLocation = rand.nextInt(6) + 1;
		
		if (randomLocation == 1) {
			player.teleport(new Location(Bukkit.getWorld("world"), -664, 54, 367));
		}else if (randomLocation == 2) {
			player.teleport(new Location(Bukkit.getWorld("world"), -682, 55, 314));
		}else if (randomLocation == 3) {
			player.teleport(new Location(Bukkit.getWorld("world"), -670, 52, 339));
		}else if (randomLocation == 4) {
			player.teleport(new Location(Bukkit.getWorld("world"), -650, 48, 371));
		}else if (randomLocation == 5) {
			player.teleport(new Location(Bukkit.getWorld("world"), -671, 55, 346));
		}else if (randomLocation == 6) {
			player.teleport(new Location(Bukkit.getWorld("world"), -648, 52, 310));
		}	
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("         Kit Selector"))
			return;
		Player player = (Player) event.getWhoClicked();
		
		event.setCancelled(true);
		
		if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType() == Material.AIR) || (!event.getCurrentItem().hasItemMeta())) {
			
			player.closeInventory();
	        return;
	    }
		
		switch (event.getCurrentItem().getType()) {
			
		case IRON_SWORD:
			Bukkit.dispatchCommand(player, "kittrooper");
			player.closeInventory();
			randomTeleport(player);
			Spawn.spawn.remove(player.getName());
			break;
		case BOW:
			Bukkit.dispatchCommand(player, "kitshortbow");
			player.closeInventory();
			randomTeleport(player);
			Spawn.spawn.remove(player.getName());
			break;
		case ARROW:
			Bukkit.dispatchCommand(player, "kittribow");
			player.closeInventory();
			randomTeleport(player);
			Spawn.spawn.remove(player.getName());
			break;
		default:
			break;
		}
	}
		
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Action a = event.getAction();
		ItemStack is = event.getItem();
		
		if ((a == Action.PHYSICAL) || (is == null) || (is.getType() == Material.AIR)) 
		{
			return;
	    }
		if (is.getType() == Material.FEATHER)
	    {
	        openKitsGUI(event.getPlayer());
	    }
	    else if (is.getType() == Material.PAPER)
	    {
	        event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "In UberPvP, players must fight and kill ");
	        event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "others in the arena. They can select any ");
	        event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "of the unique kits to help them fit their ");
	        event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "playstyle; each kit has one distinct ability ");
	        event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "that they can use for securing kills, ");
	        event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "escaping, outplaying, etc. Enjoy! ");
	        // insert separate commands such as './help killstreak', which exaplins more in-depth
	    }
	}

}
