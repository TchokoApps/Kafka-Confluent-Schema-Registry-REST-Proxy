# Kafka-Confluent-Schema-Registry-REST-Proxy

## Example of Avro Schemas on the Web

Please find below some resources to learn how to create Avro Schemas and view existing ones:

Avro Documentation: [http://avro.apache.org/docs/current/spec.html](http://avro.apache.org/docs/current/spec.html)

Oracle Avro Getting Started: [https://docs.oracle.com/cd/E57769_01/html/GettingStartedGuide/avroschemas.html](https://docs.oracle.com/cd/E57769_01/html/GettingStartedGuide/avroschemas.html)

Avro Schemas used by Rabo Bank: [https://github.com/Axual/rabo-alerts-blog-post/tree/master/src/main/avro](https://github.com/Axual/rabo-alerts-blog-post/tree/master/src/main/avro)

Avro examples by Gwen Shapira: [https://github.com/gwenshap/kafka-examples](https://github.com/gwenshap/kafka-examples)

## How to install and run Confluent
Follow this link https://docs.confluent.io/platform/current/installation/installing_cp/zip-tar.html#prod-kafka-cli-install and read the instruction.

## Start Confluent Platform
Step 1: 
Change the listening port of the zookeeper
If you cannot start zookeeper on port 2181 to clientPort=8181 for instance 
Filename is: C:\softwares\confluent-6.2.0\etc\kafka\zookeeper.properties

Step 2:
Start ZooKeeper. Run this command in its own terminal
C:\softwares\confluent-6.2.0\bin>zookeeper-server-start ..\etc\kafka\zookeeper.properties

Step 3:
Change this parameter to: zookeeper.connect=localhost:8181
Location: C:\softwares\confluent-6.2.0\etc\kafka\server.properties

Step 4:
Start Kafka Server. Run this command in its own terminal
C:\softwares\confluent-6.2.0\bin>kafka-server-start ..\etc\kafka\server.properties


