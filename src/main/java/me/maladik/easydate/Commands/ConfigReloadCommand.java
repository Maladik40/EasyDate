package me.maladik.easydate.Commands;

import me.maladik.easydate.EasyDate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ConfigReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage("§cEasyDate\n§o§8Made by Maladik_40\n\n§c1. §f/easydate reload §e- §o§7Reloads the config");
                return true;
            }

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    EasyDate.getInstance().reloadConfig();
                    p.sendMessage("§bConfig reloaded successfully!");
                    return true;
                }
            }

        }

        return false;
    }
}
