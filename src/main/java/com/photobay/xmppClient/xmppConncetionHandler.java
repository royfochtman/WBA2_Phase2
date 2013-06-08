package main.java.com.photobay.xmppClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

public class xmppConncetionHandler {

	private XMPPConnection xmppConn;
	private AccountManager accMan;
	private PubSubManager pubSubManager;
	
	
	public xmppConncetionHandler()
	{	
//	  SimplePayload bla = new SimplePayload(elementName, namespace, xmlPayload)
	}
	
	
	public boolean connect(String host, int port)
	{
		if(xmppConn != null && xmppConn.isConnected())
			return true;
		
		ConnectionConfiguration connConf = new ConnectionConfiguration(host, port);
		xmppConn = new XMPPConnection(connConf);
		accMan = new AccountManager(xmppConn);
		
		try
		{
			xmppConn.connect();
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
}
