# Kafka CPU Temperature Fast Logger

A Docker-based project that streams Linux CPU temperature to Kafka NVMe broker for time series analysis. 

## 🚀 Overview

This stack includes:

- **Kafka broker** running in **KRaft mode** (no Zookeeper)
- **Kafka-UI** for inspecting topics and messages
- **Java app** that reads CPU temperature from Linux and sends it to Kafka every 5 seconds

## 🧰 Requirements

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

### 2. Start Everything

```bash
docker-compose up --build
```

> ✅ This project will automatically format the Kafka Kraft storage if it’s the first run. You don't need to do it manually.

### 3. View the Data

Go to [http://localhost:8080](http://localhost:8080) to open **Kafka-UI**  
Look for the topic `cpu-temperature` to see the data being produced.

## 📄 Notes

- Temperature is read from `/sys/class/thermal/thermal_zone0/temp`
- Works on **Linux only**
- CPU temperature is pushed every **1 second** 
- Kafka data is stored on the mounted **NVMe drive** at `/mnt/nvme/kafka_logs`

## 📍 Stop Services

```bash
docker-compose down
```

