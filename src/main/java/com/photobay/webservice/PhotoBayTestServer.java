package main.java.com.photobay.webservice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;

public class PhotoBayTestServer {
	public static void main( String[] args ) throws Exception
	   {
	      String baseUri = ( args.length > 0 ) ? args[0] : "http://localhost:4434/";
	      //String sec = ( args.length > 1 ) ? args[1] : "10";
	  		final Map<String, String> initParams = new HashMap<String, String>();
	  		initParams.put("com.sun.jersey.config.property.packages", "main.java.com.photobay.webservice");

	      SelectorThread threadSelector = GrizzlyWebContainerFactory.create( baseUri, initParams );

//	      System.out.println( "URL: " + url );
//	      Thread.sleep( 1000 * Integer.parseInt( sec ) );
//	  	  System.in.read();
//	      srv.stopEndpoint();
	  		try {
		  		threadSelector = GrizzlyWebContainerFactory.create(baseUri, initParams);
		  		threadSelector.setKeepAliveTimeoutInSeconds(-1);
	
		  		System.out.println(String.format(
		  		"Jersey app started with WADL available at %sapplication.wadl\n" +
		  		"Hit enter to stop it...", baseUri, baseUri));
	
		  		System.in.read();
		  		threadSelector.stopEndpoint();

	  		} catch (IllegalArgumentException e) {
	  			e.printStackTrace();
	  		} catch (IOException e) {
	  			e.printStackTrace();
	  		}


	   }
}
