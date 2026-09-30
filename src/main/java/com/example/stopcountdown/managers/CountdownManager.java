package com.example.stopcountdown.managers;

import com.example.stopcountdown.StopCountdown;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class CountdownManager {

    private final StopCountdown plugin;
    private BukkitTask task;
    private int remainingSeconds;
    private int initialSeconds;

    public CountdownManager(StopCountdown plugin) {
        this.plugin = plugin;
    }

    public boolean isRunning() {
        return task != null;
    }

    public boolean startCountdown(int seconds) {
        if (isRunning()) {
            return false;
        }

        this.initialSeconds = seconds;
        this.remainingSeconds = seconds;

        this.task = Bukkit.getScheduler().runTaskTimer(plugin, this::tick, 0L, 20L);
        return true;
    }

    public boolean cancelCountdown() {
        if (!isRunning()) {
            return false;
        }

        task.cancel();
        task = null;

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.resetTitle();
        }
        return true;
    }

    private void tick() {
        ConfigManager cfg = plugin.getConfigManager();

        if (remainingSeconds > 0) {
            String titleText = cfg.getTitleText(remainingSeconds);
            String subtitleText = cfg.getSubtitleText(remainingSeconds);
            String actionBarText = cfg.getActionBarText(remainingSeconds);

            float pitch = cfg.getTickBasePitch();
            if (cfg.isTickPitchIncrease() && initialSeconds > 0) {
                pitch = cfg.getTickBasePitch() + ((float) (initialSeconds - remainingSeconds) / initialSeconds);
                pitch = Math.min(2.0f, Math.max(0.5f, pitch));
            }

            for (Player player : Bukkit.getOnlinePlayers()) {
                if (cfg.isTitleEnabled()) {
                    player.sendTitle(titleText, subtitleText, cfg.getTitleFadeIn(), cfg.getTitleStay(), cfg.getTitleFadeOut());
                }

                if (cfg.isActionBarEnabled()) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(actionBarText));
                }

                if (cfg.isTickSoundEnabled()) {
                    player.playSound(player.getLocation(), cfg.getTickSound(), cfg.getTickVolume(), pitch);
                }
            }

            if (cfg.isChatBroadcastEverySecond() || cfg.getChatBroadcastSeconds().contains(remainingSeconds)) {
                Bukkit.broadcastMessage(cfg.getChatMessage(remainingSeconds));
            }

            remainingSeconds--;
        } else {
            if (task != null) {
                task.cancel();
                task = null;
            }

            String finishTitle = cfg.getFinishTitle();
            String finishSubtitle = cfg.getFinishSubtitle();

            for (Player player : Bukkit.getOnlinePlayers()) {
                if (cfg.isTitleEnabled()) {
                    player.sendTitle(finishTitle, finishSubtitle, 0, 40, 10);
                }

                if (cfg.isFinishSoundEnabled()) {
                    player.playSound(player.getLocation(), cfg.getFinishSound(), cfg.getFinishVolume(), cfg.getFinishPitch());
                }
            }

            Bukkit.getScheduler().runTaskLater(plugin, this::executeShutdown, 20L);
        }
    }

    private void executeShutdown() {
        ConfigManager cfg = plugin.getConfigManager();

        if (cfg.isKickPlayersEnabled()) {
            String kickMsg = cfg.getKickMessage();
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.kickPlayer(kickMsg);
            }
        }

        for (String cmd : cfg.getShutdownCommands()) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
        }
    }
}
