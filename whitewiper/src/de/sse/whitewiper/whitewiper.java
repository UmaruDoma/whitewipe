// 
// Plugin zur Sicherung des Servers, das die Whitelist ausliest und
// nur die Spieler in den Server l'sst, die auf der Whitelist stehen 
///


package de.sse.whitewiper;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;





public class whitewiper extends JavaPlugin implements Listener{
	Logger log = null;
	private Map<String, String> playerz = new HashMap<String, String>();
	public void onEnable(){ 
		
		log = this.getLogger();
		
		log.info("sse whitewiper has been enabled.");
		getServer().getPluginManager().registerEvents(this, this);
		//getServer().getWhitelistedPlayers();
		for (OfflinePlayer player : Bukkit.getWhitelistedPlayers()) {
		    String name = player.getName();
		    playerz.put(name, player.getUniqueId().toString());
		    log.log(Level.INFO, name);
		}
		log.log(Level.INFO, "Num of Items: "+ playerz.size());
	}
	public void onDisable(){ 
		playerz.clear();
		this.getLogger().info("sse whitewiper has been disabled.");
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		String name = p.getName();
		this.getLogger().info("sse whitewiper PlayerJoinEvent has been called."+ p.getName());
		if (!(playerz.containsKey(name))) {
			p.kickPlayer("Du kannst nicht rein. Du bist nicht auf der Whitelist.");
		}
	
	}
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event) {
		Player p = event.getPlayer();
		String name = p.getName();
		this.getLogger().info("sse whitewiper PlayerLoginEvent has been called."+ p.getName());
		if (!(playerz.containsKey(name))) {
			p.kickPlayer("Du kannst nicht rein. Du bist nicht auf der Whitelist.");
		}
	    
	}


}
