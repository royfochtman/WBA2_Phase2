package main.java.com.photobay.gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.AbstractList;
import java.util.Collection;

import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.XMPPException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import main.java.com.photobay.jaxbfiles.Photographer;
import main.java.com.photobay.jaxbfiles.PressAgency;
import main.java.com.photobay.xmppClient.XmppConnectionHandler;


public class LoginFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	//private XmppConnectionHandler cn;

	private JPanel contentPane;
	private JTextField txtHost;
	public JTextField txtUsername;
	public JPasswordField txtPassword;
	private JTextField txtPort;
	private String password;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LoginFrame frame = new LoginFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	private String getPassword()
	{
		String pw = "";
		for(char c : txtPassword.getPassword())
			pw += c;
		
		return pw;
	}
	
	private XmppConnectionHandler connect()
	{
		XmppConnectionHandler cn;
		try
		{
			int portInt = Integer.parseInt(txtPort.getText()); 
			cn = new XmppConnectionHandler(txtHost.getText(), portInt);
			return cn;
		}
		catch(XMPPException ex)
		{
			JOptionPane.showMessageDialog(LoginFrame.this, ex.getMessage(), "Error", 
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
		catch(NumberFormatException ex)
		{
			JOptionPane.showMessageDialog(LoginFrame.this, "Wrong port number! Please insert a valid port number!", "Error", 
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
		

	}
	
	private Boolean login(XmppConnectionHandler cn)
	{
		try
		{
			return cn.login(txtUsername.getText(), password);
		}
		catch(XMPPException ex)
		{
			JOptionPane.showMessageDialog(LoginFrame.this, "Login failed. Please check password and username!\n" + 
					"Error message:" + ex.getMessage(), "Login failed", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XmppConnectionHandler cn = connect();
				password = getPassword();
				
				if(cn != null && cn.isConnected())
				{
					if(login(cn))
					{
						String ref = cn.getAttribute("name");
						if(ref.isEmpty())
							JOptionPane.showMessageDialog(LoginFrame.this, "Login failed. Can't get attribute name!"
									, "Login failed", JOptionPane.ERROR_MESSAGE);
						Photographer pho = null;
						PressAgency press = null;
						ref = ref.replaceFirst(".", "");
						ClientResponse res = Client.create().resource(WebserviceConfig.WS_ADDRESS).path(ref).get(ClientResponse.class);
						if(ref.contains("photographers"))
						{
							pho = res.getEntity(Photographer.class);
						}
						else if(ref.contains("pressAgencies"))
						{
							press = res.getEntity(PressAgency.class);
							PressAgencyFrame frame = new PressAgencyFrame(press);
							frame.setVisible(true);
						}
						
						JOptionPane.showMessageDialog(LoginFrame.this, "Anmeldung erfolgreich."
								, "Anmeldung erfolgreich", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else
					JOptionPane.showMessageDialog(LoginFrame.this, "Keine Verbindung zum Server!"
						, "Keine Verbindung", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnOk.setBounds(245, 182, 89, 23);
		contentPane.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame.this.dispose();
			}
		});
		btnCancel.setBounds(344, 182, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel lblHost = new JLabel("Host");
		lblHost.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHost.setBounds(10, 30, 46, 14);
		contentPane.add(lblHost);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPort.setBounds(217, 30, 46, 14);
		contentPane.add(lblPort);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(217, 116, 66, 14);
		contentPane.add(lblPassword);
		
		JLabel lblPassword_1 = new JLabel("Username");
		lblPassword_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword_1.setBounds(10, 116, 66, 14);
		contentPane.add(lblPassword_1);
		
		txtHost = new JTextField();
		txtHost.setHorizontalAlignment(SwingConstants.LEFT);
		txtHost.setBounds(86, 28, 86, 20);
		contentPane.add(txtHost);
		txtHost.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setHorizontalAlignment(SwingConstants.LEFT);
		txtUsername.setBounds(86, 114, 86, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 79, 411, 14);
		contentPane.add(separator);
		
		txtPassword = new JPasswordField();
		txtPassword.setHorizontalAlignment(SwingConstants.LEFT);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPassword.setBounds(314, 114, 86, 18);
		contentPane.add(txtPassword);
		
		txtPort = new JTextField();
		txtPort.setHorizontalAlignment(SwingConstants.LEFT);
		txtPort.setBounds(314, 28, 86, 20);
		contentPane.add(txtPort);
		txtPort.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				password = getPassword();
				if(!txtUsername.getText().isEmpty() && !txtHost.getText().isEmpty() && !txtPort.getText().isEmpty() && 
						!password.isEmpty())
				{
					XmppConnectionHandler cn = connect();
					if(cn.isConnected())
					{
						RegisterFrame frame = new RegisterFrame(cn, txtUsername.getText(), getPassword());
						frame.setVisible(true);
						LoginFrame.this.setVisible(false);
						LoginFrame.this.dispose();
					}
				}
				else
					JOptionPane.showMessageDialog(LoginFrame.this, "Incomplete data. Please fill out all fields.", "Error", 
							JOptionPane.ERROR_MESSAGE);
			}
		});
		btnRegister.setBounds(10, 182, 100, 23);
		contentPane.add(btnRegister);
	}
}
