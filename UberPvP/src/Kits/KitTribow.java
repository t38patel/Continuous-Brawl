package Kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import General.Spawn;

public class KitTribow implements CommandExecutor, Listener {
	
	Selection selObj = new Selection();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) 
    {   	
		if (cmd.getName().equalsIgnoreCase("kittribow")) 
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
					Selection.Kits.put(p.getName(), "Tribow");
					p.sendMessage(ChatColor.DARK_GREEN + "Equipped Tribow!");
					p.sendMessage(ChatColor.GOLD + "--- Your 3rd shot will deal additional damage");
					p.sendMessage(ChatColor.GOLD + "       You will know when your exp is full.");
					for (PotionEffect effect : p.getActivePotionEffects())
					p.removePotionEffect(effect.getType());
					inv.clear();
						
					p.setMaxHealth(40);
					p.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
				    p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
					p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
					p.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
					
					ItemStack tribowSword = new ItemStack(Material.WOOD_SWORD, 1);
					p.getInventory().addItem(tribowSword);
					
					ItemStack tribowBow = new ItemStack(Material.BOW, 1);
					tribowBow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
					p.getInventory().addItem(tribowBow);
					
					p.getInventory().setItem(9, new ItemStack(Material.ARROW));
					
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
	public void tribowDmg(EntityDamageByEntityEvent event) {
		
		int dmgModifier = 3;
		
		if (event.getDamager() instanceof Arrow) {
			
		    Arrow a = (Arrow) event.getDamager();
		    
		    if(a.getShooter() instanceof Player) {
		    	
		        Player dmger = (Player) a.getShooter();
		        
				if (Selection.Kits.containsKey(dmger.getName())) {
					
			        if ((Selection.Kits.get(dmger.getName()).equals("Tribow"))) {
			        	
			        	if (dmger == event.getEntity()) {
			        		event.setCancelled(true);
			        	}else {
				        	if (dmger.getExp() >= 1) {
				        		event.setDamage(event.getDamage() * dmgModifier);
				        		dmger.setExp(0);
				        	}else {
						        dmger.setExp((float) (dmger.getExp() + 0.5035));	
				        	}
			        	}	        	
			        }
			    }		        
		    }			
		}
	}
	
	//19 exp bar small bars
	// one small bar is 0.053
	
}
