package main.java.com.photobay.gui;


import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.pubsub.AccessModel;
import org.jivesoftware.smackx.pubsub.PublishModel;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;

import main.java.com.photobay.jaxbfiles.PayloadMessage;
import main.java.com.photobay.jaxbfiles.Photographer;
import main.java.com.photobay.jaxbfiles.PressAgency;
import main.java.com.photobay.jaxbfiles.SexEnum;
import main.java.com.photobay.xmppClient.XmppConnectionHandler;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import main.java.com.photobay.gui.WebserviceConfig;

public class RegisterFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFirstname;
	private JTextField txtLastname;
	private JTextField txtStreet;
	private JTextField txtHouseNumber;
	private JTextField txtPostalCode;
	private JTextField txtCity;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtWebsite;
	private JTextField txtName;
	private JTextField txtMainLocation;
	private JTextArea txtDescription;
	private JTextArea txtEquipment;
	private JPanel photographerPanel;
	private JPanel pressAgencyPanel;
	private JRadioButton rdbtnPhotographer;
	private JRadioButton rdbtnPressAgeny;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private JComboBox<Integer> cbDay;
	private JComboBox<Integer> cbMonth;
	private JComboBox<Integer> cbYear;
	private JComboBox<String> cbCountry;
	private JComboBox<Integer> cbYearOfEstablishment;
	private int postalCode = -1;
	private String username = null;
	private XmppConnectionHandler cnHandler = null;
	private String password = null;

	
	private Boolean register(XmppConnectionHandler cn, String ref)
	{
		try
		{
			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("name", ref);
			return cn.register(username, password, attributes);
		}
		catch(XMPPException ex)
		{
			JOptionPane.showMessageDialog(RegisterFrame.this, "Username is already in use!\nError message: " + ex.getMessage(), 
					"Registration Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	private Boolean login(XmppConnectionHandler cn)
	{
		try
		{
			return cn.login(username, password);
		}
		catch(XMPPException ex)
		{
			JOptionPane.showMessageDialog(RegisterFrame.this, "Login failed!\n" + 
					"Error message:" + ex.getMessage(), "Login failed", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	private Boolean validateData()
	{
		Boolean valid = false;
		if(rdbtnPhotographer.isSelected())
		{
			if((rdbtnMale.isSelected() || rdbtnFemale.isSelected()) && !txtFirstname.getText().isEmpty() 
					&& !txtLastname.getText().isEmpty())
				valid = true;
		}
		if(rdbtnPressAgeny.isSelected())
		{
			if(!txtName.getText().isEmpty() && !txtMainLocation.getText().isEmpty())
				valid = true;
		}
		
		if(txtStreet.getText().isEmpty() || txtHouseNumber.getText().isEmpty() || !parsePostalCode()
				|| txtCity.getText().isEmpty() || txtEmail.getText().isEmpty())
			valid = false;
		
		return valid;
	}
	
	private Boolean parsePostalCode()
	{
		try
		{
			String p = txtPostalCode.getText();
			if(p.isEmpty())
				return false;
			
			postalCode = Integer.parseInt(txtPostalCode.getText());
			if(postalCode > 0)
				return true;
			else
				return false;
		}
		catch(NumberFormatException ex)
		{				
			return false;
		}
	}
	
	/**
	 * Create the frame.
	 */
	public RegisterFrame(XmppConnectionHandler cn, String userName, String password) {
		this.cnHandler = cn;
		this.username = userName;
		setResizable(false);
		this.password = password;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Register");
		setBounds(100, 100, 450, 635);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		rdbtnPhotographer = new JRadioButton("Photographer");
		rdbtnPhotographer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				photographerPanel.setVisible(true);
				pressAgencyPanel.setVisible(false);
			}
		});
		rdbtnPhotographer.setSelected(true);
		rdbtnPhotographer.setBounds(6, 7, 109, 23);
		contentPane.add(rdbtnPhotographer);
		
		rdbtnPressAgeny = new JRadioButton("Press Ageny");
		rdbtnPressAgeny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				photographerPanel.setVisible(false);
				pressAgencyPanel.setVisible(true);
			}
		});
		rdbtnPressAgeny.setBounds(117, 7, 109, 23);
		contentPane.add(rdbtnPressAgeny);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnPressAgeny);
		group.add(rdbtnPhotographer);
		
		ButtonGroup sexGroup = new ButtonGroup();
		
		JLabel lblStreet = new JLabel("Street/No.:");
		lblStreet.setBounds(6, 277, 83, 14);
		contentPane.add(lblStreet);
		
		txtStreet = new JTextField();
		txtStreet.setBounds(117, 274, 244, 20);
		contentPane.add(txtStreet);
		txtStreet.setColumns(10);
		
		txtHouseNumber = new JTextField();
		txtHouseNumber.setBounds(371, 274, 53, 20);
		contentPane.add(txtHouseNumber);
		txtHouseNumber.setColumns(10);
		
		JLabel lblPostalCode = new JLabel("Postal Code/City:");
		lblPostalCode.setBounds(6, 302, 109, 14);
		contentPane.add(lblPostalCode);
		
		txtPostalCode = new JTextField();
		txtPostalCode.setBounds(117, 299, 93, 20);
		contentPane.add(txtPostalCode);
		txtPostalCode.setColumns(10);
		
		txtCity = new JTextField();
		txtCity.setBounds(220, 299, 141, 20);
		contentPane.add(txtCity);
		txtCity.setColumns(10);
		
		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setBounds(6, 324, 66, 14);
		contentPane.add(lblCountry);
		
		cbCountry = new JComboBox<String>();
		cbCountry.setModel(new DefaultComboBoxModel<String>(new String[] {"Germany", "USA", "Argentina"}));
		cbCountry.setBounds(117, 321, 93, 20);
		contentPane.add(cbCountry);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(6, 349, 46, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(117, 346, 307, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone*:");
		lblPhone.setBounds(6, 374, 46, 14);
		contentPane.add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(117, 371, 244, 20);
		contentPane.add(txtPhone);
		txtPhone.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description*:");
		lblDescription.setBounds(6, 424, 83, 14);
		contentPane.add(lblDescription);
		
		txtDescription = new JTextArea();
		txtDescription.setBounds(117, 419, 307, 98);
		contentPane.add(txtDescription);
		
		JLabel lblWebsite = new JLabel("Website*:");
		lblWebsite.setBounds(6, 399, 83, 14);
		contentPane.add(lblWebsite);
		
		txtWebsite = new JTextField();
		txtWebsite.setBounds(117, 396, 307, 20);
		contentPane.add(txtWebsite);
		txtWebsite.setColumns(10);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientResponse response = null;
				if(validateData())
				{
					if(rdbtnPhotographer.isSelected())
					{
						SexEnum sex = SexEnum.M;
						if(rdbtnMale.isSelected())
							sex = SexEnum.M;
						if(rdbtnFemale.isSelected())
							sex = SexEnum.W;
						int[] birthdate = {cbYear.getItemAt(cbYear.getSelectedIndex()), cbMonth.getItemAt(cbMonth.getSelectedIndex()),
								cbDay.getItemAt(cbDay.getSelectedIndex())};
						Photographer pho = new Photographer(sex, txtFirstname.getText(), txtLastname.getText(), username, birthdate,
								txtStreet.getText(), txtHouseNumber.getText(), postalCode, txtCity.getText(), 
								cbCountry.getItemAt(cbCountry.getSelectedIndex()), txtEmail.getText());
						
						pho.getGeneralPersonalData().setUsername(username);	
						pho.setEquipment(txtEquipment.getText());
						pho.getGeneralPersonalData().setPhone(txtPhone.getText());
						pho.getGeneralPersonalData().setWebsite(txtWebsite.getText());
						pho.getGeneralPersonalData().setDescription(txtDescription.getText());
						
						response = Client.create().resource(WebserviceConfig.WS_ADDRESS).path("/photographers").entity(pho).post(ClientResponse.class, pho);
						if(response != null && response.getClientResponseStatus() == Status.OK)
						{
							pho = response.getEntity(Photographer.class);
							if(register(cnHandler, pho.getRef()))
							{
								JOptionPane.showMessageDialog(RegisterFrame.this, "Registration successful!", "Registration successful!", 
										JOptionPane.INFORMATION_MESSAGE);
								if(login(cnHandler))
								{
									PhotographerFrame frame =  new PhotographerFrame(pho, cnHandler);
									frame.setVisible(true);
								}
							}
							
						}
						else
							JOptionPane.showMessageDialog(RegisterFrame.this, response.getClientResponseStatus()
									, "Fehler", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(rdbtnPressAgeny.isSelected())
					{
						PressAgency press = new PressAgency(username, txtName.getText(), txtMainLocation.getText(), 
								cbYearOfEstablishment.getItemAt(cbYearOfEstablishment.getSelectedIndex()), txtStreet.getText(), 
								txtHouseNumber.getText(), postalCode, txtCity.getText(),
								cbCountry.getItemAt(cbCountry.getSelectedIndex()), txtEmail.getText());
						
						press.getGeneralPersonalData().setUsername(username);	
						press.getGeneralPersonalData().setPhone(txtPhone.getText());
						press.getGeneralPersonalData().setWebsite(txtWebsite.getText());
						press.getGeneralPersonalData().setDescription(txtDescription.getText());
						
						response = Client.create().resource(WebserviceConfig.WS_ADDRESS).path("/pressAgencies").entity(press).post(ClientResponse.class, press);
						if(response != null && response.getClientResponseStatus() == Status.OK)
						{
							press = response.getEntity(PressAgency.class);
							if(register(cnHandler, press.getRef()))
							{
								JOptionPane.showMessageDialog(RegisterFrame.this, "Registration successful!", "Registration successful!", 
										JOptionPane.INFORMATION_MESSAGE);
								if(login(cnHandler))
								{
									PressAgencyFrame frame = new PressAgencyFrame(press, cnHandler);
									frame.setVisible(true);
								}
							}
							
						}
						else
							JOptionPane.showMessageDialog(RegisterFrame.this, response.getClientResponseStatus()
									, "Fehler", JOptionPane.INFORMATION_MESSAGE);
					}
					Boolean result = cnHandler.assignPayloadToNode(username+"MainNode", cnHandler.createNodeConf(PublishModel.publishers, 
							AccessModel.open), "payloadMessage", new PayloadMessage());
					if(result == true)
						JOptionPane.showMessageDialog(RegisterFrame.this, "Node erstellt", "Node erstellt", 
								JOptionPane.INFORMATION_MESSAGE);
					RegisterFrame.this.setVisible(false);
					RegisterFrame.this.dispose();
				}
			}
		});
		btnOk.setBounds(236, 563, 89, 23);
		contentPane.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterFrame.this.setVisible(false);
				RegisterFrame.this.dispose();
			}
		});
		btnCancel.setBounds(335, 563, 89, 23);
		contentPane.add(btnCancel);
						
						pressAgencyPanel = new JPanel();
						pressAgencyPanel.setVisible(false);
						pressAgencyPanel.setBounds(6, 37, 418, 229);
						contentPane.add(pressAgencyPanel);
						pressAgencyPanel.setLayout(null);
						
						JLabel lblName = new JLabel("Name:");
						lblName.setBounds(10, 11, 46, 14);
						pressAgencyPanel.add(lblName);
						
						txtName = new JTextField();
						txtName.setBounds(146, 8, 206, 20);
						pressAgencyPanel.add(txtName);
						txtName.setColumns(10);
						
						JLabel lblMainLocation = new JLabel("Main Location:");
						lblMainLocation.setBounds(10, 35, 103, 14);
						pressAgencyPanel.add(lblMainLocation);
						
						txtMainLocation = new JTextField();
						txtMainLocation.setBounds(146, 32, 206, 20);
						pressAgencyPanel.add(txtMainLocation);
						txtMainLocation.setColumns(10);
						
						JLabel lblYearOfEstablishment = new JLabel("Year of Establishment:");
						lblYearOfEstablishment.setBounds(10, 60, 134, 20);
						pressAgencyPanel.add(lblYearOfEstablishment);
						
						cbYearOfEstablishment = new JComboBox<Integer>();
						cbYearOfEstablishment.setBounds(146, 60, 67, 20);
						cbYearOfEstablishment.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {1990, 1991, 1992, 1993, 1994, 1995, 1996, 1997}));
						cbYearOfEstablishment.setSelectedIndex(0);
						pressAgencyPanel.add(cbYearOfEstablishment);
						
						photographerPanel = new JPanel();
						photographerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
						photographerPanel.setBounds(6, 37, 418, 229);
						contentPane.add(photographerPanel);
						photographerPanel.setLayout(null);
						
						JLabel lblFirstname = new JLabel("Firstname:");
						lblFirstname.setBounds(16, 40, 66, 14);
						photographerPanel.add(lblFirstname);
						
						JLabel lblLastname = new JLabel("Lastname:");
						lblLastname.setBounds(16, 65, 66, 14);
						photographerPanel.add(lblLastname);
						
						txtFirstname = new JTextField();
						txtFirstname.setBounds(118, 37, 179, 20);
						photographerPanel.add(txtFirstname);
						txtFirstname.setColumns(10);
						
						txtLastname = new JTextField();
						txtLastname.setBounds(118, 62, 179, 20);
						photographerPanel.add(txtLastname);
						txtLastname.setColumns(10);
						
						JLabel lblBirthdate = new JLabel("Birthdate:");
						lblBirthdate.setBounds(16, 90, 66, 14);
						photographerPanel.add(lblBirthdate);
						
						cbDay = new JComboBox<Integer>();
						cbDay.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31}));
						cbDay.setSelectedIndex(0);
						cbDay.setMaximumRowCount(31);
						cbDay.setBounds(118, 87, 51, 20);
						photographerPanel.add(cbDay);
						
						cbMonth = new JComboBox<Integer>();
						cbMonth.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}));
						cbMonth.setSelectedIndex(0);
						cbMonth.setMaximumRowCount(12);
						cbMonth.setBounds(179, 87, 51, 20);
						photographerPanel.add(cbMonth);
						
						cbYear = new JComboBox<Integer>();		
						cbYear.setBounds(240, 87, 57, 20);
						cbYear.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {1990, 1991, 1992, 1993, 1994, 1995, 1996, 1997}));
						cbYear.setSelectedIndex(0);
						photographerPanel.add(cbYear);
						
						rdbtnMale = new JRadioButton("Male");
						rdbtnMale.setBounds(16, 7, 95, 23);
						photographerPanel.add(rdbtnMale);
						
						rdbtnFemale = new JRadioButton("Female");
						rdbtnFemale.setBounds(115, 7, 109, 23);
						photographerPanel.add(rdbtnFemale);
						
								
								JLabel lblEquipment = new JLabel("Equipment*:");
								lblEquipment.setBounds(16, 114, 79, 14);
								photographerPanel.add(lblEquipment);
								
								txtEquipment = new JTextArea();
								txtEquipment.setBounds(118, 109, 179, 109);
								photographerPanel.add(txtEquipment);
								sexGroup.add(rdbtnMale);
								sexGroup.add(rdbtnFemale);
		
	}
}
