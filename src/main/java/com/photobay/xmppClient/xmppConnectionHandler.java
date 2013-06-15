package main.java.com.photobay.xmppClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.java.com.photobay.jaxbfiles.PayloadMessage;

import org.jivesoftware.smack.*;
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
	private String host = "localhost";
	private int port = 9090;
	private PubSubManager pubSubManager;
	private final String NAMESPACE = "http://www.example.org/photoBay";
	private ItemEventListener<Item> listener;
	
	/**
	 * Konstruktor, legt den Host und den Port fest und startet die Verbindung
	 * @param host
	 * @param port
	 */
	public xmppConnectionHandler(String host, int port)
	{	
		this.host = host;
		this.port = port;
		connect();
	}
	
	/**
	 * Baut eine Verbindung zum angegeben Host über den angegeben Port auf
	 * @return
	 */
	private boolean connect()
	{
		if(xmppConn != null && xmppConn.isConnected())
			return true;
		
		ConnectionConfiguration connConf = new ConnectionConfiguration(host, port);
		xmppConn = new XMPPConnection(connConf);
		accMan = new AccountManager(xmppConn);
		
		try
		{
			xmppConn.connect();
			pubSubManager = new PubSubManager(xmppConn, "pubsub."
                    + xmppConn.getHost());
		}
		catch(XMPPException ex)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Registriert einen neuen Benutzer auf dem Server
	 * @param username
	 * @param password
	 * @return
	 */
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
	
	/**
	 * Meldet einen Benutzer am Server an
	 * @param username
	 * @param password
	 * @return 
	 */
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
			node = pubSubManager.getNode(nodeID);
		}
		catch(XMPPException ex)
		{
			System.err.println("Node was not found!");

            if (ex.getXMPPError().getCode() == 404) {
            	try
            	{
            		node = pubSubManager.createNode(nodeID);
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
			node = pubSubManager.getNode(nodeID);
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
			node = pubSubManager.getNode(nodeID);
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
		LeafNode node = null;
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
}
