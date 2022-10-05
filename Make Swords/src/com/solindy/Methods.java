package com.solindy;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class Methods {

    public static void damageWithNoDamageTicks(Player p, Integer damage, Player attacker){
        ArmorStand ar = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);

        ar.setInvisible(true);
        ar.setMarker(true);

        p.damage(damage, attacker);
        p.setMaximumNoDamageTicks(damage);
        p.setNoDamageTicks(damage);

        p.teleport(ar);
        ar.remove();
    }

    public static void damageWithNoDamageTicks(LivingEntity p, Integer damage, LivingEntity attacker){
        ArmorStand ar = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);

        ar.setInvisible(true);
        ar.setMarker(true);

        p.damage(damage, attacker);
        p.setMaximumNoDamageTicks(damage);
        p.setNoDamageTicks(damage);

        p.teleport(ar);
        ar.remove();
    }

    public static void damageWithNoDamageTicks(LivingEntity p, Integer damage, Player attacker){
        ArmorStand ar = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);

        ar.setInvisible(true);
        ar.setMarker(true);

        p.damage(damage, attacker);
        p.setMaximumNoDamageTicks(damage);
        p.setNoDamageTicks(damage);

        p.teleport(ar);
        ar.remove();
    }

    public static LivingEntity getNearestEntity(Player p){
        ArrayList<Entity> near = (ArrayList<Entity>) p.getWorld().getNearbyEntities(p.getLocation(), 20, 20, 20);

        near.remove(p);

        return (LivingEntity) near.get(0);
    }
}
