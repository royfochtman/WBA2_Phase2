package main.java.com.photobay.webservice;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class WebserviceFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final static String HOST = "localhost";
	private final static  int PORT = 4456;
	private SelectorThread threadSelector = null;
	private JButton btnStartWebservice;
	private JButton btnStopWebservice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WebserviceFrame frame = new WebserviceFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void startWebservice()
	{
		String baseUri = "http://" + HOST + ":" + PORT + "/";
	  	final Map<String, String> initParams = new HashMap<String, String>();
	  	initParams.put("com.sun.jersey.config.property.packages", "main.java.com.photobay.webservice");
	  	
	  	try 
	  	{
	  		threadSelector = GrizzlyWebContainerFactory.create(baseUri, initParams);
	  		threadSelector.setKeepAliveTimeoutInSeconds(-1);
	
	  		JOptionPane.showMessageDialog(WebserviceFrame.this, "Webservice started and is available at " + baseUri + "\n" +
	  				"Click 'stop Webservice' to stop it!", "Webservice started!", JOptionPane.INFORMATION_MESSAGE);
	  		
	  		btnStartWebservice.setEnabled(false);
	  		btnStopWebservice.setEnabled(true);
  		} 
	  	catch (Exception ex) 
  		{
	  		JOptionPane.showMessageDialog(WebserviceFrame.this, ex.getMessage(), "Error", 
					JOptionPane.ERROR_MESSAGE);
  		} 
	}
	
	private void stopWebservice()
	{
		try
		{
			threadSelector.stopEndpoint();
			JOptionPane.showMessageDialog(WebserviceFrame.this, "Webserivce stopped!", "Webservice stopped!", JOptionPane.INFORMATION_MESSAGE);

	  		btnStartWebservice.setEnabled(true);
	  		btnStopWebservice.setEnabled(false);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(WebserviceFrame.this, ex.getMessage(), "Error", 
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Create the frame.
	 */
	public WebserviceFrame() {
		setTitle("Webservice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 306, 261);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnStartWebservice = new JButton("start Webservice");
		btnStartWebservice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startWebservice();
			}
		});
		btnStartWebservice.setBounds(46, 11, 178, 84);
		contentPane.add(btnStartWebservice);
		
		btnStopWebservice = new JButton("stop Webservice");
		btnStopWebservice.setEnabled(false);
		btnStopWebservice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stopWebservice();
			}
		});
		btnStopWebservice.setBounds(46, 128, 178, 84);
		contentPane.add(btnStopWebservice);
	}
}
