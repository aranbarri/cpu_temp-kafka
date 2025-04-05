# Kafka CPU Temperature Logger

A Docker-based project that streams Linux CPU temperature to Kafka and visualizes it using Kafka-UI.

## 🚀 Overview

This stack includes:

- **Kafka broker** running in **KRaft mode** (no Zookeeper)
- **Kafka-UI** for inspecting topics and messages
- **Java app** that reads CPU temperature from Linux and sends it to Kafka every 5 seconds

## 📁 Project Structure

```
.
├── docker-compose.yml
├── cpu-temp-app/
│   ├── CpuTempProducer.java
│   └── kafka-clients-3.7.0.jar
├── A_flowchart_created_using_a_vector_graphics_softwa.png
```

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

> ✅ Kafka will automatically format the storage if it’s the first run. You don't need to do it manually.

### 3. View the Data

Go to [http://localhost:8080](http://localhost:8080) to open **Kafka-UI**  
Look for the topic `cpu-temperature` to see the data being produced.

## 📄 Notes

- Temperature is read from `/sys/class/thermal/thermal_zone0/temp`
- Works on **Linux only**
- CPU temperature is pushed every **5 seconds**
- Kafka data is stored on the mounted **NVMe drive** at `/mnt/nvme/kafka_logs`

## 📍 Stop Services

```bash
docker-compose down
```

