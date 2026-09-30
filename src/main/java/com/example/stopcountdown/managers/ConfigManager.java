package com.example.stopcountdown.managers;

import com.example.stopcountdown.StopCountdown;
import org.bukkit.ChatColor;
import org.bukkit.Sound;

import java.util.Collections;
import java.util.List;

public class ConfigManager {

    private final StopCountdown plugin;

    public ConfigManager(StopCountdown plugin) {
        this.plugin = plugin;
        plugin.saveDefaultConfig();
    }

    public String getMessage(String path) {
        String msg = plugin.getConfig().getString(path);
        if (msg == null || msg.isEmpty()) {
            return "";
        }
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public int getDefaultSeconds() {
        return Math.max(1, plugin.getConfig().getInt("countdown.default-seconds", 10));
    }

    public boolean isTickSoundEnabled() {
        return plugin.getConfig().getBoolean("sounds.tick.enabled", true);
    }

    public Sound getTickSound() {
        String name = plugin.getConfig().getString("sounds.tick.sound", "BLOCK_NOTE_BLOCK_PLING");
        try {
            return Sound.valueOf(name);
        } catch (Exception e) {
            return Sound.BLOCK_NOTE_BLOCK_PLING;
        }
    }

    public boolean isTickPitchIncrease() {
        return plugin.getConfig().getBoolean("sounds.tick.pitch-increase", true);
    }

    public float getTickBasePitch() {
        return (float) plugin.getConfig().getDouble("sounds.tick.base-pitch", 1.0);
    }

    public float getTickVolume() {
        return (float) plugin.getConfig().getDouble("sounds.tick.volume", 1.0);
    }

    public boolean isFinishSoundEnabled() {
        return plugin.getConfig().getBoolean("sounds.finish.enabled", true);
    }

    public Sound getFinishSound() {
        String name = plugin.getConfig().getString("sounds.finish.sound", "ENTITY_ENDER_DRAGON_GROWL");
        try {
            return Sound.valueOf(name);
        } catch (Exception e) {
            return Sound.ENTITY_ENDER_DRAGON_GROWL;
        }
    }

    public float getFinishPitch() {
        return (float) plugin.getConfig().getDouble("sounds.finish.pitch", 1.0);
    }

    public float getFinishVolume() {
        return (float) plugin.getConfig().getDouble("sounds.finish.volume", 1.5);
    }

    public boolean isTitleEnabled() {
        return plugin.getConfig().getBoolean("title.enabled", true);
    }

    public int getTitleFadeIn() {
        return plugin.getConfig().getInt("title.fade-in", 0);
    }

    public int getTitleStay() {
        return plugin.getConfig().getInt("title.stay", 25);
    }

    public int getTitleFadeOut() {
        return plugin.getConfig().getInt("title.fade-out", 5);
    }

    public String getTitleText(int seconds) {
        String title = plugin.getConfig().getString("title.title-text", "&c&lSUNUCU DURDURULUYOR");
        return ChatColor.translateAlternateColorCodes('&', title.replace("%seconds%", String.valueOf(seconds)));
    }

    public String getSubtitleText(int seconds) {
        String subtitle = plugin.getConfig().getString("title.subtitle-text", "&eKalan Sure: &c&l%seconds% &esaniye");
        return ChatColor.translateAlternateColorCodes('&', subtitle.replace("%seconds%", String.valueOf(seconds)));
    }

    public String getFinishTitle() {
        String title = plugin.getConfig().getString("title.finish-title", "&4&lSUNUCU KAPATILDI!");
        return ChatColor.translateAlternateColorCodes('&', title);
    }

    public String getFinishSubtitle() {
        String subtitle = plugin.getConfig().getString("title.finish-subtitle", "&cVeriler kaydediliyor...");
        return ChatColor.translateAlternateColorCodes('&', subtitle);
    }

    public boolean isActionBarEnabled() {
        return plugin.getConfig().getBoolean("actionbar.enabled", true);
    }

    public String getActionBarText(int seconds) {
        String text = plugin.getConfig().getString("actionbar.text", "&eSunucu &c&l%seconds% &esaniye icinde durdurulacak!");
        return ChatColor.translateAlternateColorCodes('&', text.replace("%seconds%", String.valueOf(seconds)));
    }

    public boolean isChatBroadcastEverySecond() {
        return plugin.getConfig().getBoolean("chat.broadcast-every-second", false);
    }

    public List<Integer> getChatBroadcastSeconds() {
        return plugin.getConfig().getIntegerList("chat.broadcast-seconds");
    }

    public String getChatMessage(int seconds) {
        String msg = plugin.getConfig().getString("chat.message", "&c&l[DUYURU] &7Sunucu &e%seconds% &7saniye sonra kapatilacaktir!");
        return ChatColor.translateAlternateColorCodes('&', msg.replace("%seconds%", String.valueOf(seconds)));
    }

    public boolean isKickPlayersEnabled() {
        return plugin.getConfig().getBoolean("shutdown.kick-players", true);
    }

    public String getKickMessage() {
        String msg = plugin.getConfig().getString("shutdown.kick-message", "&c&lSUNUCU DURDURULDU");
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public List<String> getShutdownCommands() {
        List<String> commands = plugin.getConfig().getStringList("shutdown.commands");
        return commands.isEmpty() ? Collections.singletonList("stop") : commands;
    }

    public void reloadConfig() {
        plugin.reloadConfig();
    }
}
