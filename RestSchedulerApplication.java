package com.example.demo;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.handler.MyStompSessionHandler;

import net.minidev.json.JSONObject;

/**
 * @author akarsh
 *
 */
@SpringBootApplication
@EnableScheduling
public class RestSchedulerApplication {

	public static void main(String[] args) {
		//		SpringApplication.run(RestSchedulerApplication.class, args);
		String URL="wss://ws.kite.trade?api_key=xxx&access_token=xxx"/*+stok.getInstrumentToken()+"/"+"minute"*/;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URL);
		//				.queryParam("access_token", "xxx")
		//				.queryParam("api_key", "xx");
		//				.queryParam("i", ins_token)
		//				.queryParam("X-Kite-Version", "3");
		WebSocketClient client = new StandardWebSocketClient();
		WebSocketStompClient stompClient = new WebSocketStompClient(client);

		stompClient.setMessageConverter(new MappingJackson2MessageConverter());

		JSONObject json=new JSONObject();
		json.put("a", "subscribe");
		int array[]= {408065,8847347};
		json.put("v", array);
		StompSessionHandler sessionHandler = new MyStompSessionHandler();
		stompClient.connect(URL, sessionHandler,json.toString());

		new Scanner(System.in).nextLine(); // Don't close immediately.
	}

	//Scheduled to call every 5 seconds.
	//	@Scheduled(/*cron = "0/10 * * * * ?"*/fixedRate = 5000)
	public void test() {
		//		RestTemplate restTemplate=new RestTemplate();
		//		String accessToken="add_here";
		//		String apiKey="add_here";
		//		String ins_token="add_here";
		//
		//		String url="https://api.kite.trade/quote"/*+stok.getInstrumentToken()+"/"+"minute"*/;
		//		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
		//				.queryParam("access_token", accessToken)
		//				.queryParam("api_key", apiKey)
		//				.queryParam("i", ins_token)
		//				.queryParam("X-Kite-Version", "3");
		//
		//		String json=null;
		//
		//		try {
		//
		//			json= restTemplate.getForObject(builder.toUriString(), String.class);
		//
		//			System.out.println(json);
		//
		//		}catch(Exception e) {
		//			System.out.println(e.getMessage());
		//		}


		//		SpringApplication.run(Application.class, args);
	}

}
