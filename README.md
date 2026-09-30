# ⏱️ StopCountdown (Minecraft Spigot / Paper Plugin)

![Status](https://img.shields.io/badge/Status-Working%20%2F%20Stable-brightgreen?style=for-the-badge)
![Java](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge)
![Paper](https://img.shields.io/badge/Minecraft-1.20%2B-blue?style=for-the-badge)
![CI](https://img.shields.io/badge/CI%2FCD-Active-success?style=for-the-badge)

**StopCountdown** is a customizable event countdown plugin for Minecraft (Spigot / Paper) servers, suitable for minigame starts and server events.

---

## 📌 Project Status

- **Status:** 🟢 **Working / Stable**
- **CI/CD:** Automated GitHub Actions Maven build workflow enabled.
- **Configuration:** Custom titles, messages, and sound cues via `config.yml`.

---

## 🚀 Key Features

- **BossBar & Title Displays:** Renders countdown timers across BossBar indicators and screen titles.
- **Command Management:** Trigger, pause, and stop countdown timers via `/countstop`.

---

## 🛠️ Build & Installation

```bash
mvn clean package
```

Place the output `.jar` file into the server's `plugins/` directory.

---

## 📄 License

Licensed under the MIT License.
