package com.tk.cola.example.colaexample;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;


// @SpringBootApplication
public class ColaExampleApplication {

	static String connectioString = "Endpoint=sb://villa-module-azure.servicebus.windows.net/;SharedAccessKeyName=policy001;SharedAccessKey=Ec/E6aLTFxlGkFceCUuORr3vwmgKwxmEz+ASbGIsT50=;EntityPath=queue001";
	static String queueName = "queue001";

	public static void main(String[] args) {
		// SpringApplication.run(ColaExampleApplication.class, args);

		sendMessage();
	}

	static void sendMessage(){
		// send simple message to esb

		ServiceBusSenderClient serviceBusSenderClient = new ServiceBusClientBuilder().connectionString(connectioString).sender().queueName(queueName).buildClient();
		Random rand = new Random();
		var messageNumber = rand.nextInt(24);
		var message = "Hellow new message001 ";
		serviceBusSenderClient.sendMessage(new ServiceBusMessage(message+messageNumber));
		System.out.println(message+messageNumber);
	}

}
