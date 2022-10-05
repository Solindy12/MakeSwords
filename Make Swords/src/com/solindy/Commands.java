package com.solindy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.solindy.Main.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.beans.JavaBean;
import java.util.ArrayList;
import java.util.HashMap;

import com.solindy.Methods.*;
import org.bukkit.util.Vector;

public class Commands implements CommandExecutor {

    public static HashMap<String, Location> reinBench = new HashMap<>();

    public static HashMap<String, ArrayList<String>> team = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player p) {
            try {
                if (cmd.getName().equalsIgnoreCase("setreinbench")) {
                    if (args != null) {
                        if (args[0].equalsIgnoreCase("red")) {
                            Location rbLoc = p.getLocation();
                            rbLoc.setX(Math.round(rbLoc.getX()));
                            rbLoc.setY(Math.round(rbLoc.getY()));
                            rbLoc.setZ(Math.round(rbLoc.getZ()));
                            reinBench.put("red", rbLoc);

                            p.sendMessage(ChatColor.GREEN + "Successfully set");
                        } else if (args[0].equalsIgnoreCase("blue")) {
                            Location rbLoc = p.getLocation();
                            rbLoc.setX(Math.round(rbLoc.getX()));
                            rbLoc.setY(Math.round(rbLoc.getY()));
                            rbLoc.setZ(Math.round(rbLoc.getZ()));
                            reinBench.put("blue", rbLoc);

                            p.sendMessage(ChatColor.GREEN + "Successfully set");
                        } else if (args[0].equalsIgnoreCase("green")) {
                            Location rbLoc = p.getLocation();
                            rbLoc.setX(Math.round(rbLoc.getX()));
                            rbLoc.setY(Math.round(rbLoc.getY()));
                            rbLoc.setZ(Math.round(rbLoc.getZ()));
                            reinBench.put("green", rbLoc);

                            p.sendMessage(ChatColor.GREEN + "Successfully set");
                        } else if (args[0].equalsIgnoreCase("yellow")) {
                            Location rbLoc = p.getLocation();
                            rbLoc.setX(Math.round(rbLoc.getX()));
                            rbLoc.setY(Math.round(rbLoc.getY()));
                            rbLoc.setZ(Math.round(rbLoc.getZ()));
                            reinBench.put("yellow", rbLoc);

                            p.sendMessage(ChatColor.GREEN + "Successfully set");
                        } else {
                            p.sendMessage(ChatColor.RED + "Usage: /setreinbench <red/blue/green/yellow>");
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "Usage: /setreinbench <red/blue/green/yellow>");
                    }
                } else if (cmd.getName().equalsIgnoreCase("resetreinbench")) {
                    if (args[0].equalsIgnoreCase("red")) {
                        reinBench.put("red", null);
                        p.sendMessage(ChatColor.GREEN + "Successfully Reset");
                    } else if (args[0].equalsIgnoreCase("blue")) {
                        reinBench.put("blue", null);
                        p.sendMessage(ChatColor.GREEN + "Successfully Reset");
                    } else if (args[0].equalsIgnoreCase("green")) {
                        reinBench.put("green", null);
                        p.sendMessage(ChatColor.GREEN + "Successfully Reset");
                    } else if (args[0].equalsIgnoreCase("yellow")) {
                        reinBench.put("yellow", null);
                        p.sendMessage(ChatColor.GREEN + "Successfully Reset");
                    } else {
                        p.sendMessage(ChatColor.RED + "Usage: /resetreinbench <red/blue/green/yellow>");
                    }
                } else if (cmd.getName().equalsIgnoreCase("team")) {
                    if (args != null) {
                        if (args[0].equalsIgnoreCase("join")) {
                            if (args[1] != null && args[2] != null || (args[1].equals("red") || args[1].equals("blue") || args[1].equals("green") || args[1].equals("yellow"))) {
                                assert args[2] != null;
                                Player d = Bukkit.getPlayer(args[2]);
                                assert d != null;

                                if (team.get(args[1]) != null) {
                                    ArrayList<String> origin = team.get(args[1]);
                                    origin.add(d.getName());
                                    team.put(args[1], origin);
                                } else {
                                    ArrayList<String> playerName = new ArrayList<>();
                                    playerName.add(d.getName());

                                    team.put(args[1], playerName);
                                }

                                if (args[1].equalsIgnoreCase("red")) {
                                    d.setDisplayName(ChatColor.RED + d.getName() + ChatColor.RESET);
                                    d.setPlayerListName(ChatColor.RED + d.getName() + ChatColor.RESET);
                                } else if (args[1].equalsIgnoreCase("blue")) {
                                    d.setDisplayName(ChatColor.BLUE + d.getName() + ChatColor.RESET);
                                    d.setPlayerListName(ChatColor.BLUE + d.getName() + ChatColor.RESET);
                                } else if (args[1].equalsIgnoreCase("green")) {
                                    d.setDisplayName(ChatColor.GREEN + d.getName() + ChatColor.RESET);
                                    d.setPlayerListName(ChatColor.GREEN + d.getName() + ChatColor.RESET);
                                } else if (args[1].equalsIgnoreCase("yellow")) {
                                    d.setDisplayName(ChatColor.YELLOW + d.getName() + ChatColor.RESET);
                                    d.setPlayerListName(ChatColor.YELLOW + d.getName() + ChatColor.RESET);
                                }

                                p.sendMessage(ChatColor.GREEN + "Successfully made " + d.getName() + " join the team");
                            } else {
                                p.sendMessage("Usage: /team join <PlayerName> <red/blue/green/yellow>");
                            }
                        } else if (args[0].equalsIgnoreCase("leave")) {
                            if (args[1] != null) {
                                assert args[2] != null;
                                Player d = Bukkit.getPlayer(args[2]);
                                assert d != null;

                                ArrayList<String> origin = team.get(args[1]);
                                try {
                                    origin.remove(d.getName());
                                    team.put(args[1], origin);

                                    d.setDisplayName(d.getName());
                                    d.setPlayerListName(d.getName());

                                    p.sendMessage(ChatColor.GREEN + "Successfully left");
                                } catch (Exception ex) {
                                    p.sendMessage(ChatColor.RED + d.getName() + " does not belong to any team");
                                }
                            } else {
                                p.sendMessage("Usage: /team leave <PlayerName>");
                            }
                        } else if (args[0].equalsIgnoreCase("reset")) {
                            for (Player d : Bukkit.getOnlinePlayers()) {
                                d.setDisplayName(d.getName());
                                d.setPlayerListName(d.getName());
                            }
                        } else {
                            p.sendMessage(ChatColor.RED + "Usage: /team <join/leave/reset>");
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "Usage: /team <join/leave>/reset");
                    }
                }
                else if(cmd.getName().equalsIgnoreCase("test")){

                    LivingEntity entity = Methods.getNearestEntity(p);

                    Location loc = entity.getLocation();
                    Vector dir = loc.getDirection();
                    dir.normalize();
                    dir.multiply(-1);
                    loc.add(dir);
                    loc.setPitch(0);

                    p.teleport(loc);


                    Methods.damageWithNoDamageTicks(entity, 1, p);
                    Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                        @Override
                        public void run() {
                            Methods.damageWithNoDamageTicks(entity, 1, p);
                        }
                    }, 2);
                    Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                        @Override
                        public void run() {
                            Methods.damageWithNoDamageTicks(entity, 1, p);
                        }
                    }, 4);
                    Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                        @Override
                        public void run() {
                            Methods.damageWithNoDamageTicks(entity, 1, p);
                        }
                    }, 6);
                    Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                        @Override
                        public void run() {
                            Methods.damageWithNoDamageTicks(entity, 1, p);
                        }
                    }, 8);
                }
                else if(cmd.getName().equalsIgnoreCase("ms")){
                    if(args[0].equalsIgnoreCase("reload")){
                        GanziConfig.reload();
                        p.sendMessage(ChatColor.GREEN + "MakeSwords reloaded");
                    }
                    else if(args[0].equalsIgnoreCase("resetcooldown")){
                        p.setCooldown(Material.DIAMOND_SWORD, 0);
                        p.sendMessage(ChatColor.GREEN + "Cooldown reset");
                    }
                    else{
                        p.sendMessage(ChatColor.RED + "Usage: /ms <reload>");
                    }
                }
            }
            catch(Exception ex){
                p.sendMessage(ChatColor.RED + "Error: " + ex);
            }
        }

        return false;
    }


    private static final Plugin plugin = Main.getPlugin(Main.class);

}