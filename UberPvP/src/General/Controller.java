package General;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.potion.PotionEffect;

import Kits.Selection;

public class Controller implements Listener {
	
	@EventHandler
	public void foodChange(FoodLevelChangeEvent foodChange) {
		foodChange.setCancelled(true);
	}
	
	@EventHandler
	public void blockplace(BlockPlaceEvent blockplace) {
		blockplace.setCancelled(true);
	}
	
	@EventHandler
	public void blockbreak(BlockBreakEvent blockbreak) {
		blockbreak.setCancelled(true);
	}
	
	@EventHandler
	public void dropitem(PlayerDropItemEvent dropItem) 
	{
	    dropItem.setCancelled(true);
	}
	
	@EventHandler(ignoreCancelled = true)
    public void onPlayerItemBreak(PlayerItemBreakEvent event) {
        event.getBrokenItem().setAmount(1);
    }
	
	@EventHandler
	public void bloodpickup(PlayerPickupItemEvent pickItem) 
	{
		pickItem.setCancelled(true);
	}
	
	@EventHandler
	public void noDmg(EntityDamageByEntityEvent event) {
		//if ((!(Kits.containsKey(event.getEntity().getName()))) || (!(Kits.containsKey(event.getDamager().getName()))))
		{
			event.setCancelled(true);
		}
	}
	
	@EventHandler
    public void playerShoot(EntityShootBowEvent event)
    {
        if ((event.getEntity() instanceof Player))
        {
            Player shooter = (Player)event.getEntity();
            if (Spawn.spawn.contains(shooter.getName())) 
            {
                event.setCancelled(true);
            }
            if (event.getForce() <= 0.38D)
            {
                event.setCancelled(true);
                shooter.sendMessage(ChatColor.RED + "Bow spamming is not allowed.");
            }
        }
    }
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) 
	{
		Player player = event.getEntity();
		
		player.getInventory().clear();

		if (Selection.Kits.containsKey(player.getName()))
		{
			Selection.Kits.remove(player.getName());
		}
		
		event.getDrops().clear();
		event.setDroppedExp(0);
	}

	@EventHandler
	public void leaver(PlayerQuitEvent leaveEvent) 
	{
		Player leaver = leaveEvent.getPlayer();
		leaver.getInventory().clear();
		
		if (Selection.Kits.containsKey(leaver.getName()))
		{
			Selection.Kits.remove(leaver.getName());
		}
		
		for (PotionEffect effect : leaver.getActivePotionEffects())
		{
		    leaver.removePotionEffect(effect.getType());
		}
	}
	
    @EventHandler
    public void onEntityDamage(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
 
        if (event.getCause() == TeleportCause.ENDER_PEARL) {
            event.setCancelled(true);
 
            player.teleport(event.getTo());
        }
    }

}
