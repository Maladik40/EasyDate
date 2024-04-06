package me.maladik.easydate.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            int ticks = (int) p.getWorld().getTime();
            p.sendMessage("Tick: " + ticks);
            return true;
        }

        return false;
    }

}
