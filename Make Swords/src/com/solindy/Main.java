package com.solindy;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

import static com.solindy.Event.blis;

public class Main extends JavaPlugin {

    @Override
    public void onEnable(){
        getCommand("setreinbench").setExecutor(new Commands());
        getCommand("resetreinbench").setExecutor(new Commands());
        getCommand("team").setExecutor(new Commands());
        getCommand("test").setExecutor(new Commands());
        getCommand("ms").setExecutor(new Commands());
        Bukkit.getPluginManager().registerEvents(new Event(), this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        GanziConfig.setup();
        GanziConfig.get().addDefault("cooldown", "20");
        GanziConfig.get().addDefault("cdmessage", "§cThis item is on cooldown");
        GanziConfig.get().addDefault("damage", "30");
        GanziConfig.get().options().copyDefaults(true);
        GanziConfig.save();

        for(Player p : Bukkit.getOnlinePlayers()){
            blis.put(p.getName(), false);
        }
    }

    @Override
    public void onDisable(){

    }
}
