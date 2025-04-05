# Kafka CPU Temperature Fast Logger

A Docker-based project that streams Linux CPU temperature to Kafka for time series analysis.
Kafka-UI included for debugging and visualizing purposes.


  ![image](https://github.com/user-attachments/assets/e7d28035-280b-49ea-82b5-f092b73e7b04)





This project uses Docker Compose to run:

- A Kafka broker (Bitnami image)
- Kafka-UI (a web interface for Kafka)
- A Java app that sends the CPU temperature (Linux only) to a Kafka topic stored in a mounted NVMe.

## Requirements

- Docker
- Docker Compose
- Linux (to read CPU temperature from `/sys/class/thermal`)
- A mounted NVMe drive (used for Kafka log storage)

## Project Structure

```
.
├── docker-compose.yml
├── cpu-temp-app/
│   ├── CpuTempProducer.java
│   └── kafka-clients-3.5.0.jar
```

## How to Use

1. Make sure the logs directory exists on your mounted NVMe drive:
   ```bash
   sudo mkdir -p /mnt/nvme/kafka_logs
   sudo chown -R 1001:1001 /mnt/nvme/kafka_logs
   ```

2. Start the services:
   ```bash
   docker-compose up --build
   ```

3. Open Kafka-UI:
   [http://localhost:8080](http://localhost:8080)

4. In Kafka-UI, look for the `cpu-temperature` topic to see temperature data.

## Notes

- Temperature is read from `/sys/class/thermal/thermal_zone0/temp`
- Works on Linux only.
- Java app sends a new temperature every second.
- Kafka logs are stored on an NVMe-mounted directory: `/mnt/nvme/kafka_logs`

## Stop Everything

```bash
docker-compose down
```

