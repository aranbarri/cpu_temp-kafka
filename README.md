# Kafka CPU Temperature Fast Logger

A Java and Docker-based project that streams Linux CPU temperature to an NVMe Kafka Broker.

## 🚀 Overview

This stack includes:

- **Kafka broker** running in **KRaft mode** (no Zookeeper)
- **Java app** (packaged as a JAR) that reads CPU temperature from Linux and sends it to Kafka every 5 seconds

## 📁 Project Structure

```
.
├── docker-compose.yml
├── thermal_check/
│   └── ThermalCheck.jar
```

## ✅ Requirements

- Docker
- Docker Compose
- Linux host (for reading temperature from `/sys/class/thermal`)
- NVMe drive mounted at `/mnt/nvme` for Kafka logs

## ⚙️ Setup & Usage

### 1. Prepare NVMe Directory

```bash
sudo mkdir -p /mnt/nvme/kafka_logs
sudo chown -R 1001:1001 /mnt/nvme/kafka_logs
```
  Modify the docker-compose file to mount your nvme dir.

  
### 2. Start Everything

```bash
docker-compose up --build
```

> ✅ Kafka will automatically format the storage if it’s the first run. You don't need to do it manually.

## 📄 Notes

- Temperature is read from `/sys/class/thermal/thermal_zone0/temp`
- Works on **Linux only**
- CPU temperature is pushed every **5 seconds**
- Kafka data is stored on the mounted **NVMe drive** at `/mnt/nvme/kafka_logs`

## 📍 Stop Services

```bash
docker-compose down
```
