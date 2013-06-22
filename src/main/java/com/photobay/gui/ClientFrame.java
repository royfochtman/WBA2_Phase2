package main.java.com.photobay.gui;

import org.jivesoftware.smackx.pubsub.Item;

import main.java.com.photobay.jaxbfiles.PayloadMessage;

public interface ClientFrame {

	public void receivedMessages(PayloadMessage messa);
	
}
