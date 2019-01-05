package General;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import Kits.KitTribow;
import Kits.KitTrooper;
import Kits.Selection;

public class Main extends JavaPlugin implements Listener {
	
	public static Plugin UberPvP;
	
	public void onEnable() {
		
		UberPvP = this;
		
		//LISTENERS
		Controller controller = new Controller();
		Spawn spawn = new Spawn();
		CooldownManager cooldownManager = new CooldownManager();
		Selection selection = new Selection();
		KitTrooper kitTrooper = new KitTrooper();
		KitTribow kitTribow = new KitTribow();
		    	
    	//EVENT HANDLERS
		Bukkit.getServer().getPluginManager().registerEvents(controller, this);
		Bukkit.getServer().getPluginManager().registerEvents(spawn, this);
		Bukkit.getServer().getPluginManager().registerEvents(cooldownManager, this);
		Bukkit.getServer().getPluginManager().registerEvents(selection, this);
		Bukkit.getServer().getPluginManager().registerEvents(kitTrooper, this);
		Bukkit.getServer().getPluginManager().registerEvents(kitTribow, this);
	
    	//COMMANDS, WHICH CLASS THEY FROM
		getCommand("kittrooper").setExecutor(new Kits.KitTrooper());
		getCommand("kitshortbow").setExecutor(new Kits.KitShortbow());
		getCommand("kittribow").setExecutor(new Kits.KitTribow());
	}
	
	public void onDisable() 
	{		
		UberPvP = null;		
	}

}