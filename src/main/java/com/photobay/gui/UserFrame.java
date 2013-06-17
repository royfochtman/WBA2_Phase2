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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField textFieldJobName;
	private JTextField textFieldUrgencyJob;
	private JTextField textFieldPaymentJob;
	private JTextField textFieldTitelPhoto;
	private JTextField textFieldEnterValueBid;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserFrame frame = new UserFrame();
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
		
		JPanel panelMyData = new JPanel();
		panelMyData.setToolTipText("");
		pressAgencyTabbedPane.addTab("My Data", null, panelMyData, null);
		panelMyData.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblName.setBounds(10, 11, 46, 14);
		panelMyData.add(lblName);
		
		JLabel lblMyname = new JLabel("myName");
		lblMyname.setBounds(66, 11, 78, 14);
		panelMyData.add(lblMyname);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setBounds(170, 11, 67, 14);
		panelMyData.add(lblUsername);
		
		JLabel lblMyusername = new JLabel("myUsername");
		lblMyusername.setBounds(247, 11, 95, 14);
		panelMyData.add(lblMyusername);
		
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLocation.setBounds(362, 11, 63, 14);
		panelMyData.add(lblLocation);
		
		JLabel lblMylocation = new JLabel("myLocation");
		lblMylocation.setBounds(435, 11, 114, 14);
		panelMyData.add(lblMylocation);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 37, 676, 2);
		panelMyData.add(separator);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAddress.setBounds(10, 50, 61, 14);
		panelMyData.add(lblAddress);
		
		JLabel lblMyaddress = new JLabel("myAddress");
		lblMyaddress.setBounds(66, 50, 78, 14);
		panelMyData.add(lblMyaddress);
		
		JLabel lblHouseNr = new JLabel("House Nr:");
		lblHouseNr.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHouseNr.setBounds(170, 50, 67, 14);
		panelMyData.add(lblHouseNr);
		
		JLabel lblMyhousenr = new JLabel("myHouseNr");
		lblMyhousenr.setBounds(232, 50, 67, 14);
		panelMyData.add(lblMyhousenr);
		
		JLabel lblPostalCode = new JLabel("Postal Code:");
		lblPostalCode.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPostalCode.setBounds(362, 50, 89, 14);
		panelMyData.add(lblPostalCode);
		
		JLabel lblMypostalcode = new JLabel("myPostalCode");
		lblMypostalcode.setBounds(454, 50, 78, 14);
		panelMyData.add(lblMypostalcode);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCity.setBounds(10, 75, 46, 14);
		panelMyData.add(lblCity);
		
		JLabel lblMycity = new JLabel("myCity");
		lblMycity.setBounds(66, 75, 78, 14);
		panelMyData.add(lblMycity);
		
		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCountry.setBounds(170, 75, 67, 14);
		panelMyData.add(lblCountry);
		
		JLabel lblMycountry = new JLabel("myCountry");
		lblMycountry.setBounds(232, 75, 72, 14);
		panelMyData.add(lblMycountry);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 101, 676, 2);
		panelMyData.add(separator_1);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(10, 114, 46, 14);
		panelMyData.add(lblEmail);
		
		JLabel lblMyemail = new JLabel("myEmail");
		lblMyemail.setBounds(66, 114, 126, 14);
		panelMyData.add(lblMyemail);
		
		JLabel lblPhoneNr = new JLabel("Phone Nr:");
		lblPhoneNr.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPhoneNr.setBounds(10, 139, 61, 14);
		panelMyData.add(lblPhoneNr);
		
		JLabel lblMyphonenr = new JLabel("myPhoneNr");
		lblMyphonenr.setBounds(76, 139, 116, 14);
		panelMyData.add(lblMyphonenr);
		
		JLabel lblWebsite = new JLabel("Website:");
		lblWebsite.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWebsite.setBounds(10, 164, 61, 14);
		panelMyData.add(lblWebsite);
		
		JLabel lblMywebsite = new JLabel("myWebsite");
		lblMywebsite.setBounds(66, 164, 156, 14);
		panelMyData.add(lblMywebsite);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescription.setBounds(10, 189, 78, 14);
		panelMyData.add(lblDescription);
		
		JLabel lblMydescription = new JLabel("myDescription");
		lblMydescription.setBounds(10, 214, 522, 116);
		panelMyData.add(lblMydescription);
		
		JButton btnUpdateData = new JButton("Update Data");
		btnUpdateData.setBounds(433, 348, 116, 23);
		panelMyData.add(btnUpdateData);
		
		JPanel panelMyJobs = new JPanel();
		pressAgencyTabbedPane.addTab("My Jobs", null, panelMyJobs, null);
		panelMyJobs.setLayout(null);
		
		JScrollPane scrollPaneJobs = new JScrollPane();
		scrollPaneJobs.setBounds(10, 11, 100, 360);
		panelMyJobs.add(scrollPaneJobs);
		
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
		scrollPaneJobs.setViewportView(listJobs);
		
		JPanel panelJob = new JPanel();
		panelJob.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelJob.setBounds(120, 11, 566, 360);
		panelMyJobs.add(panelJob);
		panelJob.setLayout(null);
		
		JButton btnCreateJob = new JButton("Create");
		btnCreateJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCreateJob.setBounds(368, 326, 89, 23);
		panelJob.add(btnCreateJob);
		
		JButton btnUpdateJob = new JButton("Update");
		btnUpdateJob.setBounds(269, 326, 89, 23);
		panelJob.add(btnUpdateJob);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
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
		
		JLabel lblDescriptionJob = new JLabel("Description:");
		lblDescriptionJob.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescriptionJob.setBounds(10, 136, 74, 14);
		panelJob.add(lblDescriptionJob);
		
		textFieldJobName = new JTextField();
		textFieldJobName.setBounds(127, 8, 168, 20);
		panelJob.add(textFieldJobName);
		textFieldJobName.setColumns(10);
		
		textFieldUrgencyJob = new JTextField();
		textFieldUrgencyJob.setColumns(10);
		textFieldUrgencyJob.setBounds(127, 36, 168, 20);
		panelJob.add(textFieldUrgencyJob);
		
		textFieldPaymentJob = new JTextField();
		textFieldPaymentJob.setColumns(10);
		textFieldPaymentJob.setBounds(127, 83, 168, 20);
		panelJob.add(textFieldPaymentJob);
		
		JComboBox comboBoxStatusJob = new JComboBox();
		comboBoxStatusJob.setModel(new DefaultComboBoxModel(new String[] {"new", "assigned", "closed"}));
		comboBoxStatusJob.setBounds(127, 108, 168, 20);
		panelJob.add(comboBoxStatusJob);
		
		JEditorPane dtrpnDescriptionJob = new JEditorPane();
		dtrpnDescriptionJob.setText("description");
		dtrpnDescriptionJob.setBounds(10, 155, 546, 160);
		panelJob.add(dtrpnDescriptionJob);
		
		JComboBox comboBoxDeadlineDay = new JComboBox();
		comboBoxDeadlineDay.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBoxDeadlineDay.setBounds(127, 58, 44, 20);
		panelJob.add(comboBoxDeadlineDay);
		
		JComboBox comboBoxDeadlineMonth = new JComboBox();
		comboBoxDeadlineMonth.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBoxDeadlineMonth.setBounds(181, 58, 44, 20);
		panelJob.add(comboBoxDeadlineMonth);
		
		JComboBox comboBoxDeadlineYear = new JComboBox();
		comboBoxDeadlineYear.setModel(new DefaultComboBoxModel(new String[] {"2013", "2014", "2015", "2016", "2017", "3020"}));
		comboBoxDeadlineYear.setBounds(235, 58, 60, 20);
		panelJob.add(comboBoxDeadlineYear);
		
		JPanel panelMyPhotos = new JPanel();
		pressAgencyTabbedPane.addTab("My Photos", null, panelMyPhotos, null);
		panelMyPhotos.setLayout(null);
		
		JScrollPane scrollPanePhotos = new JScrollPane();
		scrollPanePhotos.setBounds(10, 11, 99, 360);
		panelMyPhotos.add(scrollPanePhotos);
		
		JList listPhotos = new JList();
		listPhotos.setModel(new AbstractListModel() {
			String[] values = new String[] {"Photo1", "Photo2", "Photo3", "EineKuh"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPanePhotos.setViewportView(listPhotos);
		
		JPanel panelPhotoContainer = new JPanel();
		panelPhotoContainer.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPhotoContainer.setBounds(119, 11, 567, 360);
		panelMyPhotos.add(panelPhotoContainer);
		panelPhotoContainer.setLayout(null);
		
		JPanel panelPhoto = new JPanel();
		panelPhoto.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPhoto.setBounds(10, 11, 344, 299);
		panelPhotoContainer.add(panelPhoto);
		
		JLabel lblTitelPhoto = new JLabel("Titel");
		lblTitelPhoto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitelPhoto.setBounds(364, 11, 46, 14);
		panelPhotoContainer.add(lblTitelPhoto);
		
		textFieldTitelPhoto = new JTextField();
		textFieldTitelPhoto.setBounds(364, 36, 193, 20);
		panelPhotoContainer.add(textFieldTitelPhoto);
		textFieldTitelPhoto.setColumns(10);
		
		JLabel lblDescriptionPhoto = new JLabel("Description");
		lblDescriptionPhoto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescriptionPhoto.setBounds(364, 67, 74, 14);
		panelPhotoContainer.add(lblDescriptionPhoto);
		
		JEditorPane editorPaneDescriptionPhoto = new JEditorPane();
		editorPaneDescriptionPhoto.setBounds(364, 92, 193, 218);
		panelPhotoContainer.add(editorPaneDescriptionPhoto);
		
		JButton btnDeletePhoto = new JButton("Delete");
		btnDeletePhoto.setBounds(463, 326, 89, 23);
		panelPhotoContainer.add(btnDeletePhoto);
		
		JButton btnCreatePhoto = new JButton("Create");
		btnCreatePhoto.setBounds(364, 326, 89, 23);
		panelPhotoContainer.add(btnCreatePhoto);
		
		JButton btnUpdatePhoto = new JButton("Update");
		btnUpdatePhoto.setBounds(265, 326, 89, 23);
		panelPhotoContainer.add(btnUpdatePhoto);
		
		JPanel panelMySubscriptions = new JPanel();
		panelMySubscriptions.setToolTipText("");
		pressAgencyTabbedPane.addTab("My Subscriptions", null, panelMySubscriptions, null);
		
		JPanel panelSearch = new JPanel();
		pressAgencyTabbedPane.addTab("Search", null, panelSearch, null);
		panelSearch.setLayout(null);
		
		JScrollPane scrollPanePhotographers = new JScrollPane();
		scrollPanePhotographers.setBounds(10, 36, 90, 335);
		panelSearch.add(scrollPanePhotographers);
		
		JList listPhotographers = new JList();
		listPhotographers.setModel(new AbstractListModel() {
			String[] values = new String[] {"photographerA", "photographerB"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPanePhotographers.setViewportView(listPhotographers);
		
		JPanel panelPhotographer = new JPanel();
		panelPhotographer.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPhotographer.setBounds(110, 11, 576, 360);
		panelSearch.add(panelPhotographer);
		panelPhotographer.setLayout(null);
		
		JLabel lblFirstnameLastname = new JLabel("Firstname Lastname");
		lblFirstnameLastname.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFirstnameLastname.setBounds(10, 11, 201, 14);
		panelPhotographer.add(lblFirstnameLastname);
		
		JButton btnSubscribeToPhotographer = new JButton("Subscribe");
		btnSubscribeToPhotographer.setBounds(210, 7, 100, 23);
		panelPhotographer.add(btnSubscribeToPhotographer);
		
		JPanel panelBids = new JPanel();
		panelBids.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelBids.setBounds(10, 36, 556, 313);
		panelPhotographer.add(panelBids);
		panelBids.setLayout(null);
		
		JScrollPane scrollPanePhotoSells = new JScrollPane();
		scrollPanePhotoSells.setBounds(10, 11, 97, 291);
		panelBids.add(scrollPanePhotoSells);
		
		JList listPhotoSells = new JList();
		listPhotoSells.setModel(new AbstractListModel() {
			String[] values = new String[] {"PhotoSell1", "PhotoSell2"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPanePhotoSells.setViewportView(listPhotoSells);
		
		JLabel lblNamePhotoSell = new JLabel("Name");
		lblNamePhotoSell.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNamePhotoSell.setBounds(117, 13, 67, 14);
		panelBids.add(lblNamePhotoSell);
		
		JLabel lblBeginningPricePhotoSell = new JLabel("Beginning Price");
		lblBeginningPricePhotoSell.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBeginningPricePhotoSell.setBounds(117, 38, 97, 14);
		panelBids.add(lblBeginningPricePhotoSell);
		
		JLabel lblDescriptionPhotoSell = new JLabel("Description");
		lblDescriptionPhotoSell.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescriptionPhotoSell.setBounds(117, 63, 97, 14);
		panelBids.add(lblDescriptionPhotoSell);
		
		JLabel lblTopicsPhotoSell = new JLabel("Topics");
		lblTopicsPhotoSell.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTopicsPhotoSell.setBounds(342, 13, 46, 14);
		panelBids.add(lblTopicsPhotoSell);
		
		JLabel lblStatusPhotoSell = new JLabel("Status");
		lblStatusPhotoSell.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatusPhotoSell.setBounds(342, 38, 46, 14);
		panelBids.add(lblStatusPhotoSell);
		
		JPanel panelPhotoSell = new JPanel();
		panelPhotoSell.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPhotoSell.setBounds(117, 88, 197, 214);
		panelBids.add(panelPhotoSell);
		
		JLabel lblEnterValueBid = new JLabel("Enter value");
		lblEnterValueBid.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterValueBid.setBounds(342, 176, 90, 14);
		panelBids.add(lblEnterValueBid);
		
		textFieldEnterValueBid = new JTextField();
		textFieldEnterValueBid.setBounds(342, 201, 86, 20);
		panelBids.add(textFieldEnterValueBid);
		textFieldEnterValueBid.setColumns(10);
		
		JButton btnOkBid = new JButton("Ok");
		btnOkBid.setBounds(342, 232, 89, 23);
		panelBids.add(btnOkBid);
		
		JLabel lblLastPriceBid = new JLabel("Last Price");
		lblLastPriceBid.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLastPriceBid.setBounds(342, 88, 74, 14);
		panelBids.add(lblLastPriceBid);
		
		JLabel lblLastPriceBidValue = new JLabel("LastPriceValue");
		lblLastPriceBidValue.setBounds(342, 113, 74, 14);
		panelBids.add(lblLastPriceBidValue);
		
		JLabel lblNamePhotoSellValue = new JLabel("Name");
		lblNamePhotoSellValue.setBounds(167, 13, 46, 14);
		panelBids.add(lblNamePhotoSellValue);
		
		JLabel lblBeginningPricePhotoSellValue = new JLabel("BegPrice");
		lblBeginningPricePhotoSellValue.setBounds(213, 38, 46, 14);
		panelBids.add(lblBeginningPricePhotoSellValue);
		
		JLabel lblDesciptionPhotoSellValue = new JLabel("Desciption");
		lblDesciptionPhotoSellValue.setBounds(197, 63, 349, 14);
		panelBids.add(lblDesciptionPhotoSellValue);
		
		JLabel lblTopicsPhotoSellValue = new JLabel("Topics");
		lblTopicsPhotoSellValue.setBounds(398, 13, 46, 14);
		panelBids.add(lblTopicsPhotoSellValue);
		
		JLabel lblStatusPhotoSellValue = new JLabel("Status");
		lblStatusPhotoSellValue.setBounds(408, 38, 46, 14);
		panelBids.add(lblStatusPhotoSellValue);
		
		JButton btnUnsubscribe = new JButton("Unsubscribe");
		btnUnsubscribe.setBounds(320, 7, 100, 23);
		panelPhotographer.add(btnUnsubscribe);
		
		JLabel lblPhotographer = new JLabel("Photographer");
		lblPhotographer.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPhotographer.setBounds(10, 11, 90, 14);
		panelSearch.add(lblPhotographer);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(622, 428, 89, 23);
		contentPane.add(btnLogout);
	}
}
