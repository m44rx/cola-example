package com.tk.cola.example.colaexample;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusMessageBatch;
import com.azure.messaging.servicebus.ServiceBusSenderClient;

public class ColaExampleBatch {

    static String connectioString = "Endpoint=sb://villa-module-azure.servicebus.windows.net/;SharedAccessKeyName=policy001;SharedAccessKey=Ec/E6aLTFxlGkFceCUuORr3vwmgKwxmEz+ASbGIsT50=;EntityPath=queue001";
	static String queueName = "queue001";

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
                                                                .sender().queueName(queueName).buildClient();

        ServiceBusMessageBatch messageBatch = serviceBusSenderClient.createMessageBatch();
        List<ServiceBusMessage> listOfMessages = createMessage();
        for (ServiceBusMessage message : listOfMessages){
            messageBatch.tryAddMessage(message);
        }
        serviceBusSenderClient.sendMessages(messageBatch);
        System.out.println("sent completed");
        serviceBusSenderClient.close();
    }
}
