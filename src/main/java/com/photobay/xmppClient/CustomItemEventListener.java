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

public class CustomItemEventListener implements ItemEventListener<Item>{

	private List<PayloadMessage> messageList;
	
	public CustomItemEventListener(List<PayloadMessage> messageList)
	{
		this.messageList = messageList;
	}
	
	@Override
	public void handlePublishedItems(ItemPublishEvent<Item> items) {
		for(Item item : items.getItems())
		{
			String xml = ((PayloadItem<SimplePayload>)item).getPayload().toXML();
			try
			{
				JAXBContext context = JAXBContext.newInstance(PayloadMessage.class);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				messageList.add((PayloadMessage)unmarshaller.unmarshal(new StreamSource(new StringReader(xml))));
			}
			catch(Exception ex)
			{
			}
		}
		
	}

}
