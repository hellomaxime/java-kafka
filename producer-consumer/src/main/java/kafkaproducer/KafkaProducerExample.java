package kafkaproducer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

public class KafkaProducerExample {

    private final static String TOPIC = "java";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092,localhost:9093,localhost:9094";

    private static Producer<Long, String> createProducer() {
        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG,"JavaKafkaProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return new KafkaProducer<>(props);
    }

    static void runProducer(final int sendMessageCount) throws Exception {
        final Producer<Long, String> producer = createProducer();
        long time = System.currentTimeMillis();

        try {
            for(long index = time; index < time + sendMessageCount; index++) {
                final ProducerRecord<Long, String> record = new ProducerRecord<>(TOPIC, index, "Hello " + index);

                RecordMetadata metadata = producer.send(record).get();

                long elapsedTime = System.currentTimeMillis() - time;
                System.out.printf(
                  "sent record(key=%s value=%s) meta(partition=%d offset=%d) time=%d\n",
                  record.key(),
                  record.value(),
                  metadata.partition(),
                  metadata.offset(),
                  elapsedTime
                );
            }
        } finally {
            producer.flush();
            producer.close();
        }
    }

    public static void main(String[] args) throws Exception {
        runProducer(10);
    }

}
