# java-kafka

Pour les exemples, creer un topic "java"

## Demarrer kafka

__Lancer Zookeeper__
`zookeeper-server-start.sh /home/maxime/datacloud/kafka/kafka_2.13-3.2.0/config/zookeeper.properties`

__Lancer Kafka broker__
`kafka-server-start.sh /home/maxime/datacloud/kafka/kafka_2.13-3.2.0/config/server.properties`

---

__Creer un topic kafka__
`kafka-topics.sh --create --topic nom_du_topic --bootstrap-server localhost:9092`
options:
`--create` pour ceer un topic
`--describe` pour avoir des informations sur le topic

__Lister les topics__
`kafka-topics.sh --list --bootstrap-server localhost:9092`

__Console producer__
`kafka-console-producer.sh --topic nom_du_topic --bootstrap-server localhost:9092`

__Console consumer__
`kafka-console-consumer.sh --topic nom_du_topic --from-beginning --bootstrap-server localhost:9092`