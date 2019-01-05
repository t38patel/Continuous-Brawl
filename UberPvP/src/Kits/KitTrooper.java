package Kits;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import General.Spawn;

public class KitTrooper implements CommandExecutor, Listener {
	
	Selection selObj = new Selection();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) 
    {   	
		if (cmd.getName().equalsIgnoreCase("kittrooper")) 
		{
			if (sender instanceof Player) 
			{
				Player p = (Player) sender;
				if (Selection.Kits.containsKey(p.getName())) 
				{
					p.sendMessage(ChatColor.RED + "You already have a kit!");
				}else if (Spawn.spawn.contains(p.getName()))
				{
					Inventory inv = p.getInventory();
					Selection.Kits.put(p.getName(), "Trooper");
					p.sendMessage(ChatColor.DARK_GREEN + "Equipped Trooper!");
					p.sendMessage(ChatColor.GOLD + "--- Get a gapple every kill.");
					for (PotionEffect effect : p.getActivePotionEffects())
					p.removePotionEffect(effect.getType());
					inv.clear();
						
					p.setMaxHealth(40);
					p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
				    p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
					p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
					p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));		
					
					ItemStack troopersword = new ItemStack(Material.IRON_SWORD, 1);
					troopersword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
					p.getInventory().addItem(troopersword);
					
					selObj.randomTeleport(p);
				}else 
				{
					p.sendMessage(ChatColor.RED + "You can only select kits pre-game!");
				}
			}
		}
		return false;
	}
	
	@EventHandler
	public void trooperReward(PlayerDeathEvent event) {
		
		if (event.getEntity().getKiller() instanceof Player) { 
			
			Player killer = event.getEntity().getKiller();
			if (Selection.Kits.containsKey(killer.getName())) {
				
		        if ((Selection.Kits.get(killer.getName()).equals("Trooper"))) {
		        	
		        	killer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));
		        }
		    }
			
		}
	}

}
