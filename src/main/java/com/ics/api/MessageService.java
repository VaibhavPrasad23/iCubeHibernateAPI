package com.ics.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class MessageService {
	private static Map<Integer, Message> messages = new HashMap<>();

	public List<Message> getAllMessages() {
		Message m1 = new Message(13, "Hello World!", "Vaibhav");
		Message m2 = new Message(2, "Hello Jersey!", "Vaibhav");
		List<Message> messages = new ArrayList<>();
		messages.add(m1);
		messages.add(m2);
		System.out.println(m1);
		System.out.println(m2);
		return messages;

//		return new ArrayList<Message>(messages);
	}

	public Message getMessage(long id) {
		return messages.get(id);

	}

	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
//		message.setAuthor("ruskin bond");
//		message.setMessage("JungleBook");
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}

	@SuppressWarnings("unlikely-arg-type")
	public Message removeMessage(long id) {
		return messages.remove(id);
	}

}
