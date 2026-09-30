# ⏱️ StopCountdown (Minecraft Spigot / Paper Plugin)

![Status](https://img.shields.io/badge/Durum-%C3%87al%C4%B1%C5%9F%C4%B1yor%20%2F%20Working-brightgreen?style=for-the-badge)
![Java](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge)
![Paper](https://img.shields.io/badge/Minecraft-1.20%2B-blue?style=for-the-badge)
![CI](https://img.shields.io/badge/CI%2FCD-Active-success?style=for-the-badge)

**StopCountdown**, Minecraft sunucularında etkinlikler, minigame başlama zamanları ve özel geri sayımlar için tasarlanmış yüksek özelleştirilebilir bir geri sayım eklentisidir.

---

## 📌 Proje Durumu (Project Status)

- **Durum:** 🟢 **Çalışıyor (Working / Stable)**
- **Test & CI/CD:** GitHub Actions Maven derleme hattı aktif.
- **Konfigürasyon:** `config.yml` ile mesajlar, title/actionbar gösterimleri yönetilir.

---

## 🚀 Özellikler

- **BossBar ve Title Gösterimi:** Geri sayımları ekran ortasında veya BossBar üzerinde dinamik olarak görüntüler.
- **Komut Desteği:** `/countstop` veya belirlenen komut ile geri sayımı başlatma ve durdurma.

---

## 🛠️ Derleme ve Kurulum

```bash
mvn clean package
```

.jar dosyasını `plugins/` dizinine taşıyın.

---

## 📄 Lisans

MIT License
