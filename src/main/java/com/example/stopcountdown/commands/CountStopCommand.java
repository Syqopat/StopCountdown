package com.example.stopcountdown.commands;

import com.example.stopcountdown.StopCountdown;
import com.example.stopcountdown.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountStopCommand implements CommandExecutor, TabCompleter {

    private final StopCountdown plugin;

    public CountStopCommand(StopCountdown plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigManager cfg = plugin.getConfigManager();

        if (!sender.hasPermission("stopcountdown.admin")) {
            sender.sendMessage(cfg.getMessage("messages.no-permission"));
            return true;
        }

        if (args.length == 0) {
            int seconds = cfg.getDefaultSeconds();
            if (plugin.getCountdownManager().startCountdown(seconds)) {
                String msg = cfg.getMessage("messages.started").replace("%seconds%", String.valueOf(seconds));
                sender.sendMessage(msg);
            } else {
                sender.sendMessage(cfg.getMessage("messages.already-running"));
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("cancel")) {
            if (plugin.getCountdownManager().cancelCountdown()) {
                sender.sendMessage(cfg.getMessage("messages.cancelled"));
                String broadcast = cfg.getMessage("messages.cancelled-broadcast");
                if (!broadcast.isEmpty()) {
                    Bukkit.broadcastMessage(broadcast);
                }
            } else {
                sender.sendMessage(cfg.getMessage("messages.not-running"));
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.getConfigManager().reloadConfig();
            sender.sendMessage(cfg.getMessage("messages.reload"));
            return true;
        }

        try {
            int seconds = Integer.parseInt(args[0]);
            if (seconds <= 0) {
                sender.sendMessage(cfg.getMessage("messages.invalid-number"));
                return true;
            }

            if (plugin.getCountdownManager().startCountdown(seconds)) {
                String msg = cfg.getMessage("messages.started").replace("%seconds%", String.valueOf(seconds));
                sender.sendMessage(msg);
            } else {
                sender.sendMessage(cfg.getMessage("messages.already-running"));
            }
        } catch (NumberFormatException e) {
            sender.sendMessage(cfg.getMessage("messages.invalid-number"));
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1 && sender.hasPermission("stopcountdown.admin")) {
            List<String> options = Arrays.asList("5", "10", "30", "60", "cancel", "reload");
            for (String opt : options) {
                if (opt.toLowerCase().startsWith(args[0].toLowerCase())) {
                    completions.add(opt);
                }
            }
        }
        return completions;
    }
}
