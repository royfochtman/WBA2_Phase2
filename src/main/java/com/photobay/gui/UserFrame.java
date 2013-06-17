package main.java.com.photobay.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToggleButton;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.Box;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JEditorPane;

public class UserFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField textFieldJobName;
	private JTextField textFieldUrgency;
	private JTextField textFieldPayment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserFrame frame = new UserFrame();
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
	public UserFrame() {
		setTitle("Logged in as: [username], [id]");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane pressAgencyTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		pressAgencyTabbedPane.setBounds(10, 11, 701, 410);
		contentPane.add(pressAgencyTabbedPane);
		
		JPanel myData = new JPanel();
		myData.setToolTipText("");
		pressAgencyTabbedPane.addTab("My Data", null, myData, null);
		myData.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblName.setBounds(10, 11, 46, 14);
		myData.add(lblName);
		
		JLabel lblMyname = new JLabel("myName");
		lblMyname.setBounds(66, 11, 78, 14);
		myData.add(lblMyname);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setBounds(170, 11, 67, 14);
		myData.add(lblUsername);
		
		JLabel lblMyusername = new JLabel("myUsername");
		lblMyusername.setBounds(247, 11, 95, 14);
		myData.add(lblMyusername);
		
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLocation.setBounds(362, 11, 63, 14);
		myData.add(lblLocation);
		
		JLabel lblMylocation = new JLabel("myLocation");
		lblMylocation.setBounds(435, 11, 114, 14);
		myData.add(lblMylocation);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 37, 676, 2);
		myData.add(separator);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAddress.setBounds(10, 50, 61, 14);
		myData.add(lblAddress);
		
		JLabel lblMyaddress = new JLabel("myAddress");
		lblMyaddress.setBounds(66, 50, 78, 14);
		myData.add(lblMyaddress);
		
		JLabel lblHouseNr = new JLabel("House Nr:");
		lblHouseNr.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHouseNr.setBounds(170, 50, 67, 14);
		myData.add(lblHouseNr);
		
		JLabel lblMyhousenr = new JLabel("myHouseNr");
		lblMyhousenr.setBounds(232, 50, 67, 14);
		myData.add(lblMyhousenr);
		
		JLabel lblPostalCode = new JLabel("Postal Code:");
		lblPostalCode.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPostalCode.setBounds(362, 50, 89, 14);
		myData.add(lblPostalCode);
		
		JLabel lblMypostalcode = new JLabel("myPostalCode");
		lblMypostalcode.setBounds(454, 50, 78, 14);
		myData.add(lblMypostalcode);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCity.setBounds(10, 75, 46, 14);
		myData.add(lblCity);
		
		JLabel lblMycity = new JLabel("myCity");
		lblMycity.setBounds(66, 75, 78, 14);
		myData.add(lblMycity);
		
		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCountry.setBounds(170, 75, 67, 14);
		myData.add(lblCountry);
		
		JLabel lblMycountry = new JLabel("myCountry");
		lblMycountry.setBounds(232, 75, 72, 14);
		myData.add(lblMycountry);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 101, 676, 2);
		myData.add(separator_1);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(10, 114, 46, 14);
		myData.add(lblEmail);
		
		JLabel lblMyemail = new JLabel("myEmail");
		lblMyemail.setBounds(66, 114, 126, 14);
		myData.add(lblMyemail);
		
		JLabel lblPhoneNr = new JLabel("Phone Nr:");
		lblPhoneNr.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPhoneNr.setBounds(10, 139, 61, 14);
		myData.add(lblPhoneNr);
		
		JLabel lblMyphonenr = new JLabel("myPhoneNr");
		lblMyphonenr.setBounds(76, 139, 116, 14);
		myData.add(lblMyphonenr);
		
		JLabel lblWebsite = new JLabel("Website:");
		lblWebsite.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWebsite.setBounds(10, 164, 61, 14);
		myData.add(lblWebsite);
		
		JLabel lblMywebsite = new JLabel("myWebsite");
		lblMywebsite.setBounds(66, 164, 156, 14);
		myData.add(lblMywebsite);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescription.setBounds(10, 189, 78, 14);
		myData.add(lblDescription);
		
		JLabel lblMydescription = new JLabel("myDescription");
		lblMydescription.setBounds(10, 214, 522, 116);
		myData.add(lblMydescription);
		
		JButton btnUpdateData = new JButton("Update Data");
		btnUpdateData.setBounds(433, 348, 116, 23);
		myData.add(btnUpdateData);
		
		JPanel myJobs = new JPanel();
		pressAgencyTabbedPane.addTab("My Jobs", null, myJobs, null);
		myJobs.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 100, 360);
		myJobs.add(scrollPane);
		
		JList listJobs = new JList();
		listJobs.setModel(new AbstractListModel() {
			String[] values = new String[] {"hier werden", "alle jobs", "\u00FCbergeben"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(listJobs);
		
		JPanel panelJob = new JPanel();
		panelJob.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelJob.setBounds(120, 11, 566, 360);
		myJobs.add(panelJob);
		panelJob.setLayout(null);
		
		JButton btnCreateJob = new JButton("Create");
		btnCreateJob.setBounds(368, 326, 89, 23);
		panelJob.add(btnCreateJob);
		
		JButton btnUpdateJob = new JButton("Update");
		btnUpdateJob.setBounds(269, 326, 89, 23);
		panelJob.add(btnUpdateJob);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(467, 326, 89, 23);
		panelJob.add(btnDelete);
		
		JLabel lblJobName = new JLabel("Job Name:");
		lblJobName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblJobName.setBounds(10, 11, 60, 14);
		panelJob.add(lblJobName);
		
		JLabel lblUrgency = new JLabel("Urgency:");
		lblUrgency.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUrgency.setBounds(10, 36, 60, 14);
		panelJob.add(lblUrgency);
		
		JLabel lblDeadline = new JLabel("Deadline:");
		lblDeadline.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDeadline.setBounds(10, 61, 60, 14);
		panelJob.add(lblDeadline);
		
		JLabel lblPayment = new JLabel("Payment:");
		lblPayment.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPayment.setBounds(10, 86, 60, 14);
		panelJob.add(lblPayment);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus.setBounds(10, 111, 60, 14);
		panelJob.add(lblStatus);
		
		JLabel lblDescription_1 = new JLabel("Description:");
		lblDescription_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescription_1.setBounds(10, 136, 74, 14);
		panelJob.add(lblDescription_1);
		
		textFieldJobName = new JTextField();
		textFieldJobName.setBounds(127, 8, 168, 20);
		panelJob.add(textFieldJobName);
		textFieldJobName.setColumns(10);
		
		textFieldUrgency = new JTextField();
		textFieldUrgency.setColumns(10);
		textFieldUrgency.setBounds(127, 36, 168, 20);
		panelJob.add(textFieldUrgency);
		
		textFieldPayment = new JTextField();
		textFieldPayment.setColumns(10);
		textFieldPayment.setBounds(127, 83, 168, 20);
		panelJob.add(textFieldPayment);
		
		JComboBox comboBoxStatus = new JComboBox();
		comboBoxStatus.setModel(new DefaultComboBoxModel(new String[] {"new", "assigned", "closed"}));
		comboBoxStatus.setBounds(127, 108, 168, 20);
		panelJob.add(comboBoxStatus);
		
		JEditorPane dtrpnDescription = new JEditorPane();
		dtrpnDescription.setText("description");
		dtrpnDescription.setBounds(10, 155, 546, 160);
		panelJob.add(dtrpnDescription);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox.setBounds(127, 58, 44, 20);
		panelJob.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBox_1.setBounds(181, 58, 44, 20);
		panelJob.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"2013", "2014", "2015", "2016", "2017", "3020"}));
		comboBox_2.setBounds(235, 58, 60, 20);
		panelJob.add(comboBox_2);
		
		JPanel myPhotos = new JPanel();
		pressAgencyTabbedPane.addTab("My Photos", null, myPhotos, null);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(622, 428, 89, 23);
		contentPane.add(btnLogout);
	}
}
