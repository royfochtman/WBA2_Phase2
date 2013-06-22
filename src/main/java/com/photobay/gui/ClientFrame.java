package main.java.com.photobay.gui;

import main.java.com.photobay.jaxbfiles.PayloadMessage;

public interface ClientFrame {

	public void receivedMessages(PayloadMessage message);
	
}
