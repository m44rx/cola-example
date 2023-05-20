package com.tk.cola.example.colaexample;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusErrorContext;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;

public class QueueReciveExample {
    
    static String connectioString = "Endpoint=sb://villa-module-azure.servicebus.windows.net/;SharedAccessKeyName=policy001;SharedAccessKey=Ec/E6aLTFxlGkFceCUuORr3vwmgKwxmEz+ASbGIsT50=;EntityPath=queue001";
	static String queueName = "queue001";

    public static void main(String[] args) throws InterruptedException {
        recieveMessages();
    }


    static void recieveMessages() throws InterruptedException{
        CountDownLatch countDownLatch= new CountDownLatch(1);
        ServiceBusProcessorClient processorClient = new ServiceBusClientBuilder()
        .connectionString(connectioString)
        .processor()
        .queueName(queueName)
        .processMessage(QueueReciveExample::processMessage)
        .processError(context -> processError(context, countDownLatch))
        .buildProcessorClient();

        System.out.println("Starting the processor");
        processorClient.start();
        TimeUnit.SECONDS.sleep(100);
        System.out.println("Stopping and closing the processor");
        processorClient.close();
        
    }

    private static void processMessage(ServiceBusReceivedMessageContext context){
        ServiceBusReceivedMessage message = context.getMessage();
        System.out.printf("Processing message. Session: %s, Sequence: %s. Contents: %s%n", 
        message.getMessageId(),
        message.getSequenceNumber(),
        message.getBody());
    }

    private static void processError(ServiceBusErrorContext context, CountDownLatch countDownLatch){

    }


}
