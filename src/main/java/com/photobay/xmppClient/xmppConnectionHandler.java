package main.java.com.photobay.xmppClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import main.java.com.photobay.jaxbfiles.PayloadMessage;

import org.jivesoftware.smack.*;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.AccessModel;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.pubsub.FormType;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.NodeType;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.PublishModel;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.Subscription;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

public class XmppConnectionHandler {

	private XMPPConnection xmppConn;
	private AccountManager accMan;
	private String host = "localhost";
	private int port = 5222;
	private PubSubManager pubSubManager;
	private final String NAMESPACE = "http://www.example.org/photoBay";
	private ItemEventListener<Item> listener;
	
	/**
	 * Konstruktor, legt den Host und den Port fest und startet die Verbindung
	 * @param host
	 * @param port
	 * @throws XMPPException 
	 */
	public XmppConnectionHandler(String host, int port) throws XMPPException
	{	
		this.port = port;
		this.host = host;
		connect();
	}
	
	public boolean isConnected()
	{
		return xmppConn.isConnected();
	}
	
	/**
	 * Baut eine Verbindung zum angegeben Host über den angegeben Port auf
	 * @return
	 * @throws XMPPException 
	 */
	private boolean connect() throws XMPPException
	{
		if(xmppConn != null && xmppConn.isConnected())
			return true;
		
		ConnectionConfiguration connConf = new ConnectionConfiguration(host, port);
		SmackConfiguration.setPacketReplyTimeout(30000);
		xmppConn = new XMPPConnection(connConf);
		accMan = new AccountManager(xmppConn);
		
		try
		{
			xmppConn.connect();
			pubSubManager = new PubSubManager(xmppConn, "pubsub." + xmppConn.getHost());
		}
		catch(XMPPException ex)
		{
			throw ex;
		}
		return true;
	}
	
	/**
	 * Registriert einen neuen Benutzer auf dem Server
	 * @param username
	 * @param password
	 * @return
	 * @throws XMPPException 
	 */
	public boolean register(String username, String password, Map<String, String> attributes) throws XMPPException
	{
		try
		{
			accMan.createAccount(username, password, attributes);
			return true;
		}
		catch(XMPPException ex){
			throw ex;
		}
	}
	
	/**
	 * Meldet einen Benutzer am Server an
	 * @param username
	 * @param password
	 * @return 
	 * @throws XMPPException 
	 */
	public boolean login(String username, String password) throws XMPPException
	{
		try
		{
			xmppConn.login(username, password);
			return true;
		}
		catch(XMPPException ex)
		{
			throw ex;
		}
	}
	
	/**
	 * Beendet die Verbindung zum Server
	 * @return
	 */
	public boolean disconnect()
	{
		if(xmppConn == null)
			return false;
			
		xmppConn.disconnect();
		return true;
	}
	
	/**
	 * Gibt alle Nodes zurück
	 * @return
	 */
	public List<String> getAllNodes() {

        List<String> entries = new ArrayList<String>();

        try {
            DiscoverItems items = pubSubManager.discoverNodes(null);
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
	 * In dieser Methode wird nach einem LeafNode gesucht. Sollte es bereits existieren, so wird eine LeafNode-Instanz zurückgeschickt.
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
			node = (LeafNode)pubSubManager.getNode(nodeID);
		}
		catch(XMPPException ex)
		{
			System.err.println("Node was not found!");

            if (ex.getXMPPError().getCode() == 404) {
            	try
            	{
            		System.err.println("Try to create new node!");
            		node = (LeafNode)pubSubManager.createNode(nodeID, conf);
//            		if(conf != null)
//            			node.sendConfigurationForm(conf);
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
	
	/**
	 * Hängt Payload-Daten an ein Node an.
	 * @param nodeID
	 * @param conf
	 * @param rootElement
	 * @param payloadMessage
	 * @return
	 */
	public boolean assignPayloadToNode(String nodeID, ConfigureForm conf, String rootElement, PayloadMessage payloadMessage)
	{
		LeafNode node = getLeafNode(nodeID, conf);
		SimplePayload payload;
		PayloadItem<SimplePayload> item;
		if(node != null) 
		{
			if(payloadMessage!=null)
			{
				payload = new SimplePayload(rootElement, NAMESPACE, payloadMessage.toXMLString());
				item = new PayloadItem<SimplePayload>(nodeID + System.currentTimeMillis(), payload);
			}
			else
				item = new PayloadItem<SimplePayload>(nodeID + System.currentTimeMillis(), new SimplePayload("", "", ""));
			
			//node.publish(item);
			try
			{
				node.send(item);
				
			}
			catch(XMPPException ex)
			{
				System.err.println("Item could not be sent!\n" + ex.getMessage());
                return false;
			}
		}
		return true;
	}
	
	public boolean sendNodeWithoutPayload(String nodeID, ConfigureForm conf)
	{
		try
		{
			LeafNode node = getLeafNode(nodeID, conf);
			node.send();
			return true;
		}
		catch(XMPPException ex)
		{
			System.err.println("Item could not be sent!\n" + ex.getMessage());
            return false;
		}
	}
	
	/**
	 * 
	 * @param type FormType (cancel, form, result, submit)
	 * @param pers Sets whether items should be persisted in the node.
	 * @param payload Does the node deliver payloads with event notifications.
	 * @param pm Sets the publishing model for the node, which determines who may publish to it.
	 * <BLOCKQUOTE><em>open</em> = Anyone may publish, <br><em>publishers</em> = Only publishers may publish, 
	 * <br><em>subscribers</em> = Only subscribers may publish</BLOCKQUOTE>
	 * @param am  Sets the value of access model.<BLOCKQUOTE>
	 * <em>authorize</em> = Subscription request must be approved and only subscribers may retrieve items
	 * <br><em>open</em> = Anyone may subscribe and retrieve items
	 * <br><em>presence</em> = Anyone with a presence subscription of both or from may subscribe and retrieve items
	 * <br><em>roster</em> = Anyone in the specified roster group(s) may subscribe and retrieve items
	 * <br><em>whitelist</em> = Only those on a whitelist may subscribe and retrieve items</BLOCKQUOTE>
	 * @return ConfigureForm-Object
	 */
	public ConfigureForm createNodeConf(PublishModel pm, AccessModel am) {
        ConfigureForm form = new ConfigureForm(FormType.submit);
        form.setPersistentItems(true);
        form.setDeliverPayloads(true);
        form.setPublishModel(pm);
        form.setAccessModel(am);
        form.setNotifyRetract(true);
        form.setNotifyConfig(true);
        form.setSubscribe(true);
        form.setNodeType(NodeType.leaf);

        return form;
    }
	
	/**
	 * Subscribed zu einem bereits existierendem Node
	 * @param nodeID
	 * @return
	 */
	public boolean subscribeToNode(String nodeID)
	{
		LeafNode node = null;
		try
		{
			node = (LeafNode)pubSubManager.getNode(nodeID);
			node.subscribe(xmppConn.getUser() + "@" + host);
			node.addItemEventListener(listener);
		}
		catch(XMPPException ex)
		{
			System.err.println(ex.getMessage());
		}
		return true;
	}
	
	public boolean unSubscribeNode(String nodeID)
	{
		LeafNode node = null;
		try
		{
			node = (LeafNode)pubSubManager.getNode(nodeID);
			node.unsubscribe(xmppConn.getUser() + "@" + host);
			node.removeItemEventListener(listener);
			return true;
			
		}
		catch(XMPPException ex)
		{
			System.err.println(ex.getMessage());
			return false;
		}
	}
	
	/**
	 * Initialisiert einen Listener. Diese Methode muss nach einem Neustart des Clients aufgerufen werden, da
	 * der Listener an alle Nodes angehangen werden muss, die der User subscribed hat.
	 * @param listener
	 */
	public void setItemEventListener(ItemEventListener<Item> listener)
	{
		this.listener = listener;
		assignListenerToSubscribedNodes();
	}
	
	/**
	 * Fügt den Listener zu allen Nodes hinzu, welche der User subscribed hat
	 */
	private void assignListenerToSubscribedNodes()
	{
		List<Subscription> subscriptions = null;
		try
		{
			subscriptions = pubSubManager.getSubscriptions();
			for(Subscription sub : subscriptions)
				pubSubManager.getNode(sub.getNode()).addItemEventListener(listener);
		}
		catch(XMPPException ex)
		{
			System.err.println(ex.getMessage());
		}
	}
	
	public boolean deleteNode(String nodeID)
	{
		try
		{
			pubSubManager.deleteNode(nodeID);
			return true;
		}
		catch(XMPPException ex)
		{
			System.err.println(ex.getMessage());
			return false;
		}
	}
	
	public String getHost()
	{
		return xmppConn.getHost();
	}
	
	public String getAttribute(String attribute)
	{
		return accMan.getAccountAttribute(attribute);
	}
	
	public Collection<String> getAttributes()
	{
		return accMan.getAccountAttributes();
	}
	
	public List<String> getSubscribedNodes() {

        List<String> entries = new ArrayList<String>();

        try {
            List<Subscription> subscriptions = pubSubManager.getSubscriptions();

            for (Subscription sub : subscriptions) {
                entries.add(sub.getNode());
            }

        } catch (XMPPException e) {
            e.printStackTrace();
        }

        return entries;

    }
}
