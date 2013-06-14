package main.java.com.photobay.xmppClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.java.com.photobay.jaxbfiles.PayloadMessage;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.packet.DiscoverInfo;
import org.jivesoftware.smackx.packet.DiscoverInfo.Identity;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.AccessModel;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.pubsub.FormType;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.PublishModel;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.Subscription;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

public class xmppConnectionHandler {

	private XMPPConnection xmppConn;
	private AccountManager accMan;
	private PubSubManager pubSubManager;
	private final String HOST = "localhost";
	private final int PORT = 9090;
	private PubSubManager pubsub_man;
	private final String NAMESPACE = "http://www.example.org/photoBay";
	
	public xmppConnectionHandler()
	{	
//	  SimplePayload bla = new SimplePayload(elementName, namespace, xmlPayload)
	}
	
	
	public boolean connect()
	{
		if(xmppConn != null && xmppConn.isConnected())
			return true;
		
		ConnectionConfiguration connConf = new ConnectionConfiguration(HOST, PORT);
		xmppConn = new XMPPConnection(connConf);
		accMan = new AccountManager(xmppConn);
		
		try
		{
			xmppConn.connect();
			pubsub_man = new PubSubManager(xmppConn, "pubsub."
                    + xmppConn.getHost());
		}
		catch(XMPPException ex)
		{
			return false;
		}
		return true;
	}
	
	public boolean register(String username, String password)
	{
		try
		{
			accMan.createAccount(username, password);
			return true;
		}
		catch(XMPPException ex){
			return false;
		}
	}
	
	public boolean login(String username, String password)
	{
		try
		{
			xmppConn.login(username, password);
			return true;
		}
		catch(XMPPException ex)
		{
			return false;
		}
	}
	
	public boolean disconnect()
	{
		if(xmppConn == null)
			return false;
			
		xmppConn.disconnect();
		return true;
	}
	
	public List<String> getAllNodes() {

        List<String> entries = new ArrayList<String>();

        try {
            DiscoverItems items = pubsub_man.discoverNodes(null);
            Iterator<DiscoverItems.Item> it = items.getItems();

            for (; it.hasNext();) {
                entries.add(it.next().getNode());
            }

        } catch (XMPPException e) {
            e.printStackTrace();
        }

        return entries;
    }
		
	/**
	 * In dieser Methode nach einem LeafNode gesucht. Sollte es bereits existieren, so wird eine LeafNode-Instanz zurückgeschickt.
	 * Wenn kein LeafNode mit der übergebenen ID existiert, wird dieses Node mit der übergebenen Konfiguration erstellt 
	 * @param nodeID
	 * @param conf
	 * @return LeafNode
	 */
	private LeafNode getLeafNode(String nodeID, ConfigureForm conf)
	{
		LeafNode node = null;
		try
		{
			node = pubsub_man.getNode(nodeID);
		}
		catch(XMPPException ex)
		{
			System.err.println("Node was not found!");

            if (ex.getXMPPError().getCode() == 404) {
            	try
            	{
            		node = pubsub_man.createNode(nodeID);
            		if(conf != null)
            			node.sendConfigurationForm(conf);
            	}
            	catch(XMPPException ex2)
            	{
            		  System.err.println("Node could not be created!");
                      return null;
            	}
			}
		}		
		return node;
	}
	
	public boolean assignPayloadToNode(String nodeID, ConfigureForm conf, String rootElement, PayloadMessage payloadMessage)
	{
		LeafNode node = getLeafNode(nodeID, conf);
		if(node != null) 
		{
			SimplePayload payload = new SimplePayload(rootElement, NAMESPACE, payloadMessage.toXMLString());
			PayloadItem<SimplePayload> item = new PayloadItem<SimplePayload>(null, payload);
			try
			{
				node.send(item);
			}
			catch(XMPPException ex)
			{
				System.err.println("Item could not be sent!");
                return false;
			}
		}
		return true;
	}
	
	public ConfigureForm createNodeConf(FormType type, boolean pers, boolean payload, PublishModel pm, AccessModel am) {
        ConfigureForm form = new ConfigureForm(type);
        form.setPersistentItems(pers);
        form.setDeliverPayloads(payload);
        form.setPublishModel(pm);
        form.setAccessModel(am);

        return form;
    }
}
