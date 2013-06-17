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
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import main.java.com.photobay.xmppClient.xmppConnectionHandler;

public class LoginFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private xmppConnectionHandler cn;

	private JPanel contentPane;
	private JTextField txtHost;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JTextField txtPort;

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

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
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
				try
				{ 
					int portInt = Integer.parseInt(txtPort.getText()); 
					cn = new xmppConnectionHandler(txtHost.getText(), portInt);
					String pw = "";
					for(char c : txtPassword.getPassword())
						pw += c;
					
					if(cn.isConnected())
					{
						if(cn.login(txtUsername.getText(), pw))
						{
							JOptionPane.showMessageDialog(LoginFrame.this, "Anmeldung erfolgreich."
									, "Anmeldung erfolgreich", JOptionPane.INFORMATION_MESSAGE);
						}
						else
							JOptionPane.showMessageDialog(LoginFrame.this, "Anmeldung fehlgeschlagen. Bitte Passwort und Username prüfen!"
									, "Anmeldung fehlgeschlagen", JOptionPane.ERROR_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(LoginFrame.this, "Keine Verbindung zum Server!"
								, "Keine Verbindung", JOptionPane.ERROR_MESSAGE);
				}
				catch (Exception ex) 
				{
					JOptionPane.showMessageDialog(LoginFrame.this, ex.getMessage(), "Error", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnOk.setBounds(245, 182, 89, 23);
		contentPane.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
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
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRegister.setBounds(10, 182, 100, 23);
		contentPane.add(btnRegister);
	}
}
