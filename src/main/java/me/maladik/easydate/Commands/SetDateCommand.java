package me.maladik.easydate.Commands;

import me.maladik.easydate.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class SetDateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (args.length == 0) {
                p.sendMessage("§cCommand syntax: §f/setdate <day> <month> <year>");
                return true;
            }

            if (args.length < 3) {
                p.sendMessage("§cCommand syntax: §f/setdate <day> <month> <year>");
                return true;
            }

            if (args.length == 3) {
                int day = Integer.parseInt(args[0]);
                int month = Integer.parseInt(args[1]);
                int year = Integer.parseInt(args[2]);

                Utils.DAY = day;
                Utils.MONTH = month;
                Utils.YEAR = year;

                Random random = new Random();

                Utils.CURRENT_DAY_NAME = random.nextInt(0, 6);

                p.sendMessage("§bDate successfully changed!");
                return true;
            }

        }

        return false;
    }
}
