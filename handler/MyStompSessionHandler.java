package com.example.demo.handler;

import java.lang.reflect.Type;

import org.json.JSONObject;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {


	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		System.out.println("New session established : " + session.getSessionId());
		session.subscribe("/topic/messages", this);
		System.out.println("Subscribed to /topic/messages");
		//		session.send("/app/chat", getSampleMessage());
		//		System.out.println("Message sent to websocket server");
	}

	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
		System.out.println("Got an exception"+ exception);
	}

	@Override
	public Type getPayloadType(StompHeaders headers) {
		return JSONObject.class;
	}

	@Override
	public void handleFrame(StompHeaders headers, Object payload) {
		JSONObject msg = (JSONObject) payload;
		System.out.println("Received : " +msg);
	}

	/**
	 * A sample message instance.
	 * @return instance of <code>Message</code>
	 */
	private JSONObject getSampleMessage() {
		JSONObject msg = new JSONObject();
		//        msg.setFrom("Nicky");
		//        msg.setText("Howdy!!");
		return msg;
	}
}
