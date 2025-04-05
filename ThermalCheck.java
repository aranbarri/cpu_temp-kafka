package caf.rt.services.screens.workers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class ThermalProducer 
{
	static int waitSeconds;
	
    public static double getCpuTemperature() 
    {
        try 
        {
            String tempPath = "/host_thermal/thermal_zone0/temp";
            String temp = Files.readString(Path.of(tempPath)).trim();
            return Double.parseDouble(temp) / 1000.0;
        } catch (Exception e) 
        {
            e.printStackTrace();
            return -1; // Valor indicando error
        }
    }

    public static void main(String[] args) throws Exception 
    {
        Properties props = new Properties();
        props.put("bootstrap.servers", "kafka:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        @SuppressWarnings("resource")
		KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        while (true) 
        {
            double cpuTemp = getCpuTemperature();

            if (cpuTemp != -1) 
            {
                producer.send(new ProducerRecord<>("cpu-temperature", "cpu", String.format("%.2f", cpuTemp)));
                waitSeconds=1;
            } else 
            {
                System.err.println("Error obteniendo temperatura");
                waitSeconds=5; //let's wait a little bit more
            }

            Thread.sleep(waitSeconds);
        }
    }
}
