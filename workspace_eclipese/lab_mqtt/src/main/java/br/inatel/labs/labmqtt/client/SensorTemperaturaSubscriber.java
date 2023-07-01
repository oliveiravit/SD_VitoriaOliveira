package br.inatel.labs.labmqtt.client;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import java.util.UUID;

public class SensorTemperaturaSubscriber {

    public static void main(String[] args) throws MqttException {
        // Criando o subscriber
        String subsciberId = UUID.randomUUID().toString();
        IMqttClient subscriber = new MqttClient( MyConstants.URI_BROKER, subsciberId);

        // Conexão
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        subscriber.connect(options);

        // Recebe a mensagem do Broker
        subscriber.subscribe(MyConstants.TOPIC_1,
                (topic,msg) -> {
                    byte[] payload = msg.getPayload();
                    System.out.println("Payload recebido: " + payload);
                    System.out.println("Topico recebido: " + topic);
                });

        // Desconecta
        subscriber.disconnect();
    }
}