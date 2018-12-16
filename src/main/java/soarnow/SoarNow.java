package soarnow;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public final class SoarNow extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("SoarNow has been invoked!");
    }

    @Override
    public void onDisable() {
        getLogger().info("SoarNow has been disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        int diamond_count = 10;

        if (command.getName().equalsIgnoreCase("soarnow")) {
            //if player types /soar, and has 10 diamonds in inventory, player will have flight for 10 secs
            if (sender instanceof Player && (player.getInventory().contains(Material.DIAMOND, diamond_count)) && (player.getAllowFlight() == false)) {
                player.getInventory().removeItem(new ItemStack(Material.DIAMOND, diamond_count));
                player.setAllowFlight(true);
                player.setFlying(player.getAllowFlight());
                sender.sendMessage(ChatColor.DARK_AQUA + "You have" + ChatColor.BLUE + " temporary " + ChatColor.DARK_AQUA + "flight!");
                //player.setAllowFlight(false);
                this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    public void run() {
                        player.setAllowFlight(false);
                        player.setFlying(player.getAllowFlight());
                        sender.sendMessage(ChatColor.WHITE + "Flight has ended, applying SLOW_FALLING" + ChatColor.WHITE);
                        player.setFlying(false);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 600, 1));
                    }
                }, 20L*10L);
                this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    public void run() {
                        sender.sendMessage(ChatColor.AQUA + "Flight is ending in " + ChatColor.RED + "5" +
                                ChatColor.WHITE + " seconds" + ChatColor.WHITE);
                    }
                }, 20L*5L);
                this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    public void run() {
                        sender.sendMessage(ChatColor.AQUA + "Flight is ending in " + ChatColor.RED + "4" +
                                ChatColor.WHITE + " seconds"  + ChatColor.WHITE);
                    }
                }, 20L*6L);
                this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    public void run() {
                        sender.sendMessage(ChatColor.AQUA + "Flight is ending in " + ChatColor.RED + "3" +
                                ChatColor.WHITE + " seconds"  + ChatColor.WHITE);
                    }
                }, 20L*7L);
                this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    public void run() {
                        sender.sendMessage(ChatColor.AQUA + "Flight is ending in " + ChatColor.RED + "2" +
                                ChatColor.WHITE + " seconds"  + ChatColor.WHITE);
                    }
                }, 20L*8L);
                this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    public void run() {
                        sender.sendMessage(ChatColor.AQUA + "Flight is ending in " + ChatColor.RED + "1" +
                                ChatColor.WHITE +  " second"  + ChatColor.WHITE);
                    }
                }, 20L*9L);

                this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    public void run() {
                        if (player.isOnGround()) {
                            player.removePotionEffect(PotionEffectType.SLOW_FALLING);
                        }
                    }
                }, 20L*20L);
                return true;
            } else if (player.getAllowFlight()) {
                sender.sendMessage("You are already flying");
            } else {
                sender.sendMessage("You do not have " + diamond_count + " diamonds in your inventory");
            }
        }
        return false;
    }
}