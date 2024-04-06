package me.maladik.easydate.Commands;

import me.maladik.easydate.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GetDateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            p.sendMessage("> DATA: " + Utils.DAY + "/" + Utils.MONTH + "/" + Utils.YEAR);
            return true;
        }

        return false;
    }
}
