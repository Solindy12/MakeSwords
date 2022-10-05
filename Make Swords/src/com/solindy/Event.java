package com.solindy;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Queue;

import java.util.ArrayList;

public class Event implements Listener {

    private static final Plugin plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void interact(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        try {

            if (p.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_SWORD)) {
                if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                    ArrayList<Entity> en = (ArrayList<Entity>) p.getWorld().getNearbyEntities(p.getLocation(), 10, 10, 10);
                    en.remove(p);

                    if(p.hasCooldown(Material.DIAMOND_SWORD)){
                        p.sendMessage(GanziConfig.get().getString("cdmessage"));
                        return;
                    }

                    if(!(en.size() == 0)) {
                        blis.put(p.getName(), true);
                        p.setCooldown(Material.DIAMOND_SWORD, Integer.parseInt(GanziConfig.get().getString("cooldown")) * 20);
                    }

                    p.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                        public void run() {
                            if (blis.get(p.getName())) {
                                Location origin = p.getLocation();
                                
                                LivingEntity entity = (LivingEntity) en.get(0);

                                Location loc = entity.getLocation();
                                Vector dir = loc.getDirection();
                                dir.normalize();
                                dir.multiply(-1);
                                loc.add(dir);
                                loc.setPitch(0);

                                p.teleport(loc);

                                entity.damage(Double.parseDouble(GanziConfig.get().getString("damage")), p);
                                en.remove(0);

                                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 1.5f);
                                
                                Location newLoc = p.getLocation();
                                
                                ArmorStand ar = (ArmorStand) p.getWorld().spawnEntity(origin, EntityType.ARMOR_STAND);

                                ar.setInvisible(true);
                                ar.setMarker(true);

                                for(int i = 0; i <= origin.distance(newLoc); i++){
                                    p.getWorld().spawnParticle(Particle.FLAME, ar.getLocation().add(0, 0.3, 0), 7, 0.2, 0.2, 0.2);
                                    Location arloc = entity.getLocation();
                                    Vector ardir = arloc.getDirection();
                                    ardir.normalize();
                                    ardir.multiply(1);
                                    arloc.add(ardir);
                                    arloc.setPitch(0);

                                    p.teleport(arloc);
                                }


                                if(en.size() == 0){
                                    blis.put(p.getName(), false);
                                }
                            }
                        }
                    }, 0, 2);
                }
            }
        }
        catch(IndexOutOfBoundsException ex){
            blis.put(p.getName(), false);
        }
        //catch(Exception ex){
        //    p.sendMessage(ChatColor.RED + "Error: " + ex);
        //}
    }

    public static HashMap<String, Boolean> blis = new HashMap<>();
}
