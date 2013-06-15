package main.java.com.photobay.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;

import main.java.com.photobay.xmppClient.xmppConfig;
import main.java.com.photobay.xmppClient.xmppConnectionHandler;

public class login extends JFrame {
	
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnOk = new JButton("Ok");
//		btnOk.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				try
//					{ 
//					int portInt = Integer.parseInt(txtPort.getText()); 
//					
//					cn = new xmppConnectionHandler(txtHost.getText(), portInt);
//					}
//				catch (Exception ex) {
//					JOptionPane.showMessageDialog(this, "Bitte gültige Portnummer eingeben")
//							}
//			}
//		});
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
		txtHost.setBounds(86, 28, 86, 20);
		contentPane.add(txtHost);
		txtHost.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(86, 114, 86, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 79, 411, 14);
		contentPane.add(separator);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPassword.setBounds(314, 114, 86, 18);
		contentPane.add(txtPassword);
		
		txtPort = new JTextField();
		txtPort.setBounds(314, 28, 86, 20);
		contentPane.add(txtPort);
		txtPort.setColumns(10);
	}
}
