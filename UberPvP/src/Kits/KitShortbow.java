package Kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import General.Spawn;

public class KitShortbow implements CommandExecutor {
	
	Selection selObj = new Selection();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) 
    {   	
		if (cmd.getName().equalsIgnoreCase("kitshortbow")) 
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
					Selection.Kits.put(p.getName(), "Shortbow");
					p.sendMessage(ChatColor.DARK_GREEN + "Equipped Shortbow!");
					for (PotionEffect effect : p.getActivePotionEffects())
					p.removePotionEffect(effect.getType());
					inv.clear();
						
					p.setMaxHealth(40);
					p.getInventory().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
				    p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
					p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
					p.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
					
					ItemStack shortbowSword = new ItemStack(Material.WOOD_SWORD, 1);
					shortbowSword.addEnchantment(Enchantment.KNOCKBACK, 1);
					p.getInventory().addItem(shortbowSword);
					
					ItemStack shortbowBow = new ItemStack(Material.BOW, 1);
					shortbowBow.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
					shortbowBow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
					p.getInventory().addItem(shortbowBow);
					
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
	
}
