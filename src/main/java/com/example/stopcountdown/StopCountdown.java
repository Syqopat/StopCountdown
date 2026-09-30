package com.example.stopcountdown;

import com.example.stopcountdown.commands.CountStopCommand;
import com.example.stopcountdown.managers.ConfigManager;
import com.example.stopcountdown.managers.CountdownManager;
import org.bukkit.plugin.java.JavaPlugin;

public class StopCountdown extends JavaPlugin {

    private ConfigManager configManager;
    private CountdownManager countdownManager;

    @Override
    public void onEnable() {
        this.configManager = new ConfigManager(this);
        this.countdownManager = new CountdownManager(this);

        var cmd = getCommand("countstop");
        if (cmd != null) {
            CountStopCommand executor = new CountStopCommand(this);
            cmd.setExecutor(executor);
            cmd.setTabCompleter(executor);
        }
    }

    @Override
    public void onDisable() {
        if (countdownManager != null && countdownManager.isRunning()) {
            countdownManager.cancelCountdown();
        }
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public CountdownManager getCountdownManager() {
        return countdownManager;
    }
}
