package me.maladik.easydate;

import org.bukkit.scheduler.BukkitRunnable;

public class DayCycleRunnable extends BukkitRunnable {

    private final EasyDate myPlugin;

    private boolean debouce = false;

    public DayCycleRunnable(EasyDate plugin) {
        myPlugin = plugin;
    }

    public void checkDayName() {

        if (Utils.CURRENT_DAY_NAME >= 6) {
            Utils.CURRENT_DAY_NAME = 0;
        } else {
            Utils.CURRENT_DAY_NAME++;
        }

    }

    @Override
    public void run() {
        String mainWorldName = myPlugin.getConfig().get("world-date").toString();
        if (mainWorldName == null) {
            myPlugin.getLogger().warning("Could not find the world in config.yml! Check the config.yml");
        }
        int time = (int) myPlugin.getServer().getWorld(mainWorldName).getTime();

        if (time >= 18000 && time <= 18005 && !debouce) {
            debouce = true;
            if (Utils.DAY >= 31 && ((Utils.MONTH == 1) || (Utils.MONTH == 3) || (Utils.MONTH == 5) || (Utils.MONTH == 7) || (Utils.MONTH == 8) || (Utils.MONTH == 10) || (Utils.MONTH == 12))) {
                if (Utils.MONTH == 12) {
                    Utils.DAY = 1;
                    Utils.MONTH = 1;
                    Utils.YEAR += 1;
                    checkDayName();
                } else {
                    Utils.DAY = 1;
                    Utils.MONTH += 1;
                    checkDayName();
                }
            } else if (Utils.DAY >= 30 && ((Utils.MONTH == 4) || (Utils.MONTH == 6) || (Utils.MONTH == 9) || (Utils.MONTH == 11))) {
                Utils.DAY = 1;
                Utils.MONTH += 1;
                checkDayName();
            } else if (Utils.DAY >= 28 && Utils.MONTH == 2){
                if (Utils.YEAR % 4 == 0 && Utils.DAY != 29) {
                    Utils.DAY += 1;
                    checkDayName();
                } else {
                    Utils.DAY = 1;
                    Utils.MONTH += 1;
                    checkDayName();
                }

            } else {
                Utils.DAY += 1;
                checkDayName();
            }

            try {
                Thread.sleep(1000);
                debouce = false;
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }
}
