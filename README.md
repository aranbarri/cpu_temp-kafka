# Kafka CPU Temperature Logger

This project uses Docker Compose to run:

- A Kafka broker (Bitnami image)
- Kafka-UI (a web interface for Kafka)
- A Java app that sends the CPU temperature (Linux only) to a Kafka topic

## Requirements

- Docker
- Docker Compose
- Linux (to read CPU temperature from `/sys/class/thermal`)

## Project Structure

```
.
├── docker-compose.yml
├── cpu-temp-app/
│   ├── CpuTempProducer.java
│   └── kafka-clients-3.7.0.jar
```

## How to Use

1. Make sure the logs directory exists:
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
- Java app sends a new temperature every 5 seconds.

## Stop Everything

```bash
docker-compose down
```

