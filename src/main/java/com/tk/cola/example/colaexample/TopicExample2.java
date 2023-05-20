package com.tk.cola.example.colaexample;

import java.util.Arrays;
import java.util.List;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusMessageBatch;
import com.azure.messaging.servicebus.ServiceBusSenderClient;

public class TopicExample2 {
    
    static String connectioString = "Endpoint=sb://villa-proyect-azure.servicebus.windows.net/;SharedAccessKeyName=policyTopic001;SharedAccessKey=TpBVm63wq3UJDGusZaBLYM8ViNFmViAF0+ASbH7xS0g=;EntityPath=topic001";
    static String topicName = "topic001";

    public static void main(String[] args) {
        sendMessageBatch();
    }

    static List<ServiceBusMessage> createMessage(){

        ServiceBusMessage[] messages = {
            new ServiceBusMessage("message A1"),
            new ServiceBusMessage("message B2"),
            new ServiceBusMessage("message C3")
        };
        return Arrays.asList(messages);
    }
    
    static void sendMessageBatch() {
        // send simple message to esb

        ServiceBusSenderClient serviceBusSenderClient = new ServiceBusClientBuilder()
                                                                .connectionString(connectioString)
                                                                .sender().topicName(topicName).buildClient();

        ServiceBusMessageBatch messageBatch = serviceBusSenderClient.createMessageBatch();
        List<ServiceBusMessage> listOfMessages = createMessage();
        for (ServiceBusMessage message : listOfMessages){
            messageBatch.tryAddMessage(message);
        }
        serviceBusSenderClient.sendMessages(messageBatch);
        System.out.println("#####% --->> sent completed");
        serviceBusSenderClient.close();

    }
}
