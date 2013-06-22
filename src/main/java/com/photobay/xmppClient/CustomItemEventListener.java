package main.java.com.photobay.xmppClient;
import java.io.File;
import java.io.InputStream;
import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import main.java.com.photobay.jaxbfiles.PayloadMessage;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import main.java.com.photobay.gui.ClientFrame;

public class CustomItemEventListener implements ItemEventListener<Item>{

	private ClientFrame frame;
	
	public CustomItemEventListener(ClientFrame clFrame)
	{
		this.frame = clFrame;
	}
	
	@Override
	public void handlePublishedItems(ItemPublishEvent<Item> items) {
		for(Item item : items.getItems())
		{
			String xml = ((PayloadItem<SimplePayload>)item).getPayload().toXML();
			try
			{
				//xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + xml;
				JAXBContext context = JAXBContext.newInstance(PayloadMessage.class);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				PayloadMessage message = (PayloadMessage)unmarshaller.unmarshal(new StreamSource(new StringReader(xml)), 
						PayloadMessage.class).getValue();
				frame.receivedMessages(message);
			}
			catch(Exception ex)
			{
				System.out.println("Error: CustomItemEventListener: " + ex.getMessage() + " " + ((PayloadItem<SimplePayload>)item).getPayload().toXML());
				try
				{
				JAXBContext context = JAXBContext.newInstance(PayloadMessage.class);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				PayloadMessage message = (PayloadMessage)unmarshaller.unmarshal(new StreamSource(new StringReader(xml)), 
						PayloadMessage.class).getValue();
				System.out.println(message.getMessage() + " " + message.getUri());
				frame.receivedMessages(message);
				}
				catch(Exception ex2) { System.out.println("kefnejn"); }
			}
		}
		
	}

}
