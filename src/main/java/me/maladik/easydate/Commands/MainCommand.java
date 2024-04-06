package me.maladik.easydate.Commands;

import me.maladik.easydate.EasyDate;
import me.maladik.easydate.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length == 0) {
                p.sendMessage(Utils.HELP_MESSAGE);
                return true;
            }

            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("set")) {
                    if (args.length == 5) {
                        int day = Integer.parseInt(args[1]);
                        int month = Integer.parseInt(args[2]);
                        int year = Integer.parseInt(args[3]);
                        int dayName = Integer.parseInt(args[4]);

                        if ((day > 31 && day < 0) || (month > 12 && month < 0) || (dayName > 6 && dayName < 0)) {
                            p.sendMessage("§aThe numbers you inserted are not correct! Please check and try again.");
                            return true;
                        }

                        Utils.DAY = day;
                        Utils.MONTH = month;
                        Utils.YEAR = year;
                        Utils.CURRENT_DAY_NAME = dayName;

                        p.sendMessage("§aNew date set successfully!");
                        return true;

                    } else if (args.length == 4) {
                        int day = Integer.parseInt(args[1]);
                        int month = Integer.parseInt(args[2]);
                        int year = Integer.parseInt(args[3]);

                        Random random = new Random();

                        int dayName = random.nextInt(0, 6);

                        Utils.DAY = day;
                        Utils.MONTH = month;
                        Utils.YEAR = year;
                        Utils.CURRENT_DAY_NAME = dayName;

                        p.sendMessage("§aNew date set successfully!");
                        return true;

                    } else {
                        p.sendMessage("§cCommand syntax: §f/easydate set <day> <month> <year> [<day name>]");
                        return true;
                    }
                } else if (args[0].equalsIgnoreCase("currentdate")) {
                    p.sendMessage("\n\n§aCurrent date:" + "\n§7 US Format: §f" + Utils.MONTH + "/" + Utils.DAY + "/" + Utils.YEAR + "\n§7 EU/IT Format: §f" + Utils.DAY + "/" + Utils.MONTH + "/" + Utils.YEAR + "\n ");
                    return true;
                } else if (args[0].equalsIgnoreCase("configreload")) {
                    EasyDate.getInstance().reloadConfig();
                    p.sendMessage("§aConfig reloaded successfully!");
                    return true;
                } else if (args[0].equalsIgnoreCase("help")) {
                    p.sendMessage(Utils.HELP_MESSAGE);
                    return true;
                } else {
                    p.sendMessage("§cIncorrect command! Digit /easydate help for help!");
                    return true;
                }
            }

        }

        return false;
    }
}
