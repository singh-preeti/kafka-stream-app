### Download Kafka v3.1.0
https://archive.apache.org/dist/kafka/3.1.0/kafka_2.12-3.1.0.tgz

## Steps to start kafka
1. Extract into a folder
2. cd <path-of-kafka>
3. Start zookeeper (open cmd)
`call bin/windows/zookeeper-server-start.bat config/zookeeper.properties`
4. Start broker
`call bin/windows/kafka-server-start.bat config/server.properties`
5. Create topic for the example orders-topic
`call bin/windows/kafka-topics.bat --create --topic orders-topic --bootstrap-server localhost:9092`

## Start OrderStreamPipeline main class for demo

## In case of issue 
1. stop broker, zookeeper 
2. delete C:\tmp 
3. follow Steps to start kafka