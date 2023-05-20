package com.tk.cola.example.colaexample;

import java.util.Random;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;

public class TopicExample {

    static String connectioString = "Endpoint=sb://villa-proyect-azure.servicebus.windows.net/;SharedAccessKeyName=policyTopic001;SharedAccessKey=TpBVm63wq3UJDGusZaBLYM8ViNFmViAF0+ASbH7xS0g=;EntityPath=topic001";
    static String topicName = "topic001";

    public static void main(String[] args) {
        sendMessage();
    }

    static void sendMessage() {
        // send simple message to esb

        ServiceBusSenderClient serviceBusSenderClient = new ServiceBusClientBuilder()
                .connectionString(connectioString)
                .sender().topicName(topicName).buildClient();

        Random rand = new Random();
        var messageNumber = rand.nextInt(24);
        var message = "Hellow new message001 ";
        serviceBusSenderClient.sendMessage(new ServiceBusMessage(message + messageNumber));
        System.out.println(message + messageNumber);

    }
}
