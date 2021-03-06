package main.java.com.photobay.gui;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.transform.stream.StreamSource;

import main.java.com.photobay.jaxbfiles.Job;
import main.java.com.photobay.jaxbfiles.Jobs;
import main.java.com.photobay.jaxbfiles.Jobs.JobRef;
import main.java.com.photobay.jaxbfiles.PayloadMessage;
import main.java.com.photobay.jaxbfiles.Photographer;
import main.java.com.photobay.jaxbfiles.Photographers;
import main.java.com.photobay.jaxbfiles.Photographers.PhotographerRef;
import main.java.com.photobay.jaxbfiles.Photos;
import main.java.com.photobay.jaxbfiles.PressAgency;
import main.java.com.photobay.util.ImagePanel;
import main.java.com.photobay.webservice.PhotoBayRessourceManager;
import main.java.com.photobay.xmppClient.XmppConnectionHandler;

import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.client.WebResource;

//import com.sun.jersey.api.client.ClientRequest;

public class PressAgencyFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField textFieldJobName;
	private JTextField textFieldUrgencyJob;
	private JTextField textFieldPaymentJob;
	private JTextField textFieldTitelPhoto;
	private JTextField textFieldEnterValueBid;
	private JList<String> listJobs;
	private Job job;
	private Jobs jobs;
	private String jobRef;
	private PressAgency pressAgency;
	private Photographer photographer;
	private WebResource webResource;
	private JComboBox<String> comboBoxStatusJob;
	private JComboBox<Integer> comboBoxDeadlineDay;
	private JComboBox<Integer> comboBoxDeadlineMonth;
	private JComboBox<Integer> comboBoxDeadlineYear;
	private JTextField textFieldPhotoPath;
	private int jobIndex;
	private JList<String> listPhotos;
	private JList<PayloadMessage> subscriptionsList;
	private JList<String> listPhotographers;
	private JList<String> listPhotoSells;
	private JScrollPane scrollPaneJobs;
	private JTextArea textAreaJobDescription;
	private String pressAgencyRef;
	private JButton btnUpdateJob;
	private JButton btnCreateJob;
	private JButton btnDeleteJob;
	private Photos myPhotos;
	private int selectedTab;
	private Photographers photographers;
	private XmppConnectionHandler cnHandler;
	private List<String> subscriptions;
	private JButton btnSubscribeToPhotographer;
	private JButton btnUnsubscribe;
	private Job selectedJob;
	DefaultListModel<PayloadMessage> payloadMessagemodel = new DefaultListModel<PayloadMessage>();
	DefaultListModel<Item> payloadMessagemodel2 = new DefaultListModel<Item>();
	private JTextArea txtMessage;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the Frame
	 * 
	 * @param userObject
	 */
	public PressAgencyFrame(PressAgency pressAgency, XmppConnectionHandler cn) {

		/**
		 * webResource variable.
		 */
		webResource = Client.create().resource(WebserviceConfig.WS_ADDRESS);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/**
		 * TabbedPane for the Press Agency. Will be showed, when a Press Agency
		 * is logged in.
		 */

		if(pressAgency != null)
		{
			this.pressAgency = pressAgency;
		}
		if(cn != null)
			this.cnHandler = cn;
		
		cnHandler.refreshSubs(setItemEvent());
		updateMySubscriptionsList();
		
		setTitle("Logged in as: "
				+ pressAgency.getGeneralPersonalData().getUsername() + ", ID: "
				+ pressAgency.getID());

		JTabbedPane pressAgencyTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		pressAgencyTabbedPane.setBounds(10, 11, 701, 410);
		
		pressAgencyTabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent p) {
				JTabbedPane pane = (JTabbedPane)p.getComponent();
				int selectedIndex = pane.getSelectedIndex();
				if(selectedIndex == 1 && selectedIndex != selectedTab)
				{
					updateJobsList();
				}
				if(selectedIndex == 3 && selectedIndex != selectedTab)
				{
					//updateMySubscriptionsList();
				}
				if(selectedIndex == 4 && selectedIndex != selectedTab)
				{
					updatePhotographersList();
				}
				selectedTab = selectedIndex;
			}
		});
		
		contentPane.add(pressAgencyTabbedPane);

		JPanel panelMyData = new JPanel();
		panelMyData.setToolTipText("");
		pressAgencyTabbedPane.addTab("My Data", null, panelMyData, null);
		panelMyData.setLayout(null);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblName.setBounds(10, 11, 46, 14);
		panelMyData.add(lblName);

		/**
		 * Get press agency's name.
		 */
		JLabel lblMyname = new JLabel(pressAgency.getName());
		lblMyname.setBounds(66, 11, 78, 14);
		panelMyData.add(lblMyname);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setBounds(170, 11, 67, 14);
		panelMyData.add(lblUsername);

		/**
		 * Get press agency's username.
		 */
		JLabel lblMyusername = new JLabel(pressAgency.getGeneralPersonalData()
				.getUsername());
		lblMyusername.setBounds(247, 11, 95, 14);
		panelMyData.add(lblMyusername);

		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLocation.setBounds(362, 11, 63, 14);
		panelMyData.add(lblLocation);

		/**
		 * get press agency's location.
		 */
		JLabel lblMylocation = new JLabel(pressAgency.getMainLocation());
		lblMylocation.setBounds(435, 11, 114, 14);
		panelMyData.add(lblMylocation);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 37, 676, 2);
		panelMyData.add(separator);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAddress.setBounds(10, 50, 61, 14);
		panelMyData.add(lblAddress);

		/**
		 * Get press agency's street name.
		 */
		JLabel lblMyaddress = new JLabel(pressAgency.getGeneralPersonalData()
				.getAddress().getStreet());
		lblMyaddress.setBounds(66, 50, 78, 14);
		panelMyData.add(lblMyaddress);

		JLabel lblHouseNr = new JLabel("House Nr:");
		lblHouseNr.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHouseNr.setBounds(170, 50, 67, 14);
		panelMyData.add(lblHouseNr);

		/**
		 * Get press agency's house number.
		 */
		JLabel lblMyhousenr = new JLabel(pressAgency.getGeneralPersonalData()
				.getAddress().getHouseNumber());
		lblMyhousenr.setBounds(232, 50, 67, 14);
		panelMyData.add(lblMyhousenr);

		JLabel lblPostalCode = new JLabel("Postal Code:");
		lblPostalCode.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPostalCode.setBounds(362, 50, 89, 14);
		panelMyData.add(lblPostalCode);

		/**
		 * Get press agency's postal code.
		 */
		JLabel lblMypostalcode = new JLabel(String.valueOf(pressAgency
				.getGeneralPersonalData().getAddress().getPostalCode()));
		lblMypostalcode.setBounds(454, 50, 78, 14);
		panelMyData.add(lblMypostalcode);

		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCity.setBounds(10, 75, 46, 14);
		panelMyData.add(lblCity);

		/**
		 * Get press agency's city.
		 */
		JLabel lblMycity = new JLabel(pressAgency.getGeneralPersonalData()
				.getAddress().getCity());
		lblMycity.setBounds(66, 75, 78, 14);
		panelMyData.add(lblMycity);

		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCountry.setBounds(170, 75, 67, 14);
		panelMyData.add(lblCountry);

		/**
		 * Get press agency's country.
		 */
		JLabel lblMycountry = new JLabel(pressAgency.getGeneralPersonalData()
				.getAddress().getCountry());
		lblMycountry.setBounds(232, 75, 72, 14);
		panelMyData.add(lblMycountry);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 101, 676, 2);
		panelMyData.add(separator_1);

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(10, 114, 46, 14);
		panelMyData.add(lblEmail);

		/**
		 * Get press agency's e-mail.
		 */
		JLabel lblMyemail = new JLabel(pressAgency.getGeneralPersonalData()
				.getEmail());
		lblMyemail.setBounds(66, 114, 126, 14);
		panelMyData.add(lblMyemail);

		JLabel lblPhoneNr = new JLabel("Phone Nr:");
		lblPhoneNr.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPhoneNr.setBounds(10, 139, 61, 14);
		panelMyData.add(lblPhoneNr);

		/**
		 * Get press agency's phone number.
		 */
		JLabel lblMyphonenr = new JLabel(pressAgency.getGeneralPersonalData()
				.getPhone());
		lblMyphonenr.setBounds(76, 139, 116, 14);
		panelMyData.add(lblMyphonenr);

		JLabel lblWebsite = new JLabel("Website:");
		lblWebsite.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWebsite.setBounds(10, 164, 61, 14);
		panelMyData.add(lblWebsite);

		/**
		 * Get press agency's website.
		 */
		JLabel lblMywebsite = new JLabel(pressAgency.getGeneralPersonalData()
				.getWebsite());
		lblMywebsite.setBounds(66, 164, 156, 14);
		panelMyData.add(lblMywebsite);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescription.setBounds(10, 189, 78, 14);
		panelMyData.add(lblDescription);

		/**
		 * Get press agency's description.
		 */
		JLabel lblMydescription = new JLabel(pressAgency
				.getGeneralPersonalData().getDescription());
		lblMydescription.setBounds(10, 214, 522, 116);
		panelMyData.add(lblMydescription);

		JButton btnUpdateData = new JButton("Update Data");
		btnUpdateData.setEnabled(false);
		btnUpdateData.setBounds(433, 348, 116, 23);
		panelMyData.add(btnUpdateData);

		JPanel panelMyJobs = new JPanel();
		
		pressAgencyTabbedPane.addTab("My Jobs", null, panelMyJobs, null);
		panelMyJobs.setLayout(null);

		scrollPaneJobs = new JScrollPane();
		scrollPaneJobs.setEnabled(false);
		scrollPaneJobs.setBounds(10, 11, 100, 360);
		panelMyJobs.add(scrollPaneJobs);
		
		listJobs = new JList<String>();
		listJobs.setEnabled(false);
		
		listJobs.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent sel) {

				String val = listJobs.getSelectedValue();
				JobRef ref = new JobRef();
				for(JobRef refItem : jobs.getJobRef())
				{
					if(refItem.getJobName() == val)
						ref = refItem;
				}
				ClientResponse response = webResource.path(ref.getUri().replaceFirst(".", "")).get(ClientResponse.class);
				if(response != null && response.hasEntity() && response.getClientResponseStatus() == Status.OK)
				{
					Job job = response.getEntity(Job.class);
					selectedJob = job;
					updateJob(job);
				}
			}
		});
		
		listJobs.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {  };

			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index];
			}
		});

		scrollPaneJobs.setViewportView(listJobs);

		JPanel panelJob = new JPanel();
		panelJob.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelJob.setBounds(120, 52, 566, 319);
		panelMyJobs.add(panelJob);
		panelJob.setLayout(null);

		btnCreateJob = new JButton("Create");
		btnCreateJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// validate Data
				if (validateJobData()) {
					Job readedJob = readJobFields();
					String ref = PressAgencyFrame.this.pressAgency.getRef();
					readedJob.setPressAgencyRef(ref);
					
					ClientResponse newJobResponse = webResource.path("/jobs")
							.entity(readedJob).post(ClientResponse.class, readedJob);
					
					if(newJobResponse != null) {
						JOptionPane.showMessageDialog(PressAgencyFrame.this,
								"Job was successfully created",
								"New job created", JOptionPane.INFORMATION_MESSAGE);
						
						textFieldJobName.setText(null);
						textFieldPaymentJob.setText(null);
						textFieldUrgencyJob.setText(null);
						textAreaJobDescription.setText(null);
						
						comboBoxStatusJob.setSelectedIndex(0);
						comboBoxDeadlineDay.setSelectedIndex(0);
						comboBoxDeadlineMonth.setSelectedIndex(0);
						comboBoxDeadlineYear.setSelectedIndex(0);
						
						updateJobsList();
				} else
					JOptionPane.showMessageDialog(PressAgencyFrame.this,
							"Incomplete data. Please fill out all fields.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		btnCreateJob.setBounds(269, 284, 89, 23);
		panelJob.add(btnCreateJob);

		btnUpdateJob = new JButton("Update");
		btnUpdateJob.setEnabled(false);
		btnUpdateJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				/**/
				
				Object[] options = { "Yes", "No", "Cancel" };
				int n = JOptionPane
						.showOptionDialog(PressAgencyFrame.this,
								"Do you really want to update this job?",
								"Update?", JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[2]);
				Job updatedJob = selectedJob;
				JobRef ref = new JobRef();
				if (n == 0) {
					String val = listJobs.getSelectedValue();
					
					for (JobRef jobRef : jobs.getJobRef()) {
						if (jobRef.getJobName() == val) {
							ref = jobRef;
							break;
						}
					}

					try {

						updatedJob.setDeadline(DatatypeFactory
								.newInstance()
								.newXMLGregorianCalendar(
										comboBoxDeadlineYear
												.getItemAt(comboBoxDeadlineYear
														.getSelectedIndex()),
										comboBoxDeadlineMonth
												.getItemAt(comboBoxDeadlineMonth
														.getSelectedIndex()),
										comboBoxDeadlineDay
												.getItemAt(comboBoxDeadlineDay
														.getSelectedIndex()),
										0, 0, 0, 0, 0));

					} catch (DatatypeConfigurationException ex) {
					}

					updatedJob.setDescription(textAreaJobDescription.getText());
					try {
						updatedJob.setPayment(BigInteger.valueOf(Long
								.parseLong(textFieldPaymentJob.getText())));
					} catch (NumberFormatException ex) {
					}
					updatedJob.setStatus(comboBoxStatusJob.getSelectedItem()
							.toString());
					updatedJob.setUrgency(textFieldUrgencyJob.getText());
				}
				System.out.println("/jobs/" + String.valueOf(updatedJob.getID()));
				
				ClientResponse response = webResource
						.path("/jobs/" + String.valueOf(updatedJob.getID())).type(MediaType.APPLICATION_XML)
						.entity(updatedJob)
						.put(ClientResponse.class, updatedJob);
				
				if (response != null
						&& response.getClientResponseStatus() == Status.OK) {
					JOptionPane.showMessageDialog(PressAgencyFrame.this,
							"Job updated!", "Updated!",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					int status = response.getClientResponseStatus().getStatusCode();
					JOptionPane.showMessageDialog(PressAgencyFrame.this, "Job could not be Updated, Status Code " + status,"Error" ,
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

		});
		
		btnUpdateJob.setBounds(368, 284, 89, 23);
		panelJob.add(btnUpdateJob);

		btnDeleteJob = new JButton("Delete");
		btnDeleteJob.setEnabled(false);
		btnDeleteJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				Object[] options = {"Yes","No","Cancel"};
				int n = JOptionPane.showOptionDialog(PressAgencyFrame.this,
					    "Would you realy like to delete this job?",
					    "Delete?",
					    JOptionPane.YES_NO_CANCEL_OPTION,
					    JOptionPane.QUESTION_MESSAGE,
					    null, options, options[2]);
				if(n == 0)
				{
					String val = listJobs.getSelectedValue();
					JobRef ref = new JobRef();
					for(JobRef jobRef : jobs.getJobRef())
					{
						if(jobRef.getJobName()== val)
						{
							ref = jobRef;
							break;
						}
					}
					ClientResponse response = webResource.path(ref.getUri().replaceFirst(".", "")).delete(ClientResponse.class);
					if(response != null && response.getClientResponseStatus() == Status.OK)
					{
						JOptionPane.showMessageDialog(PressAgencyFrame.this, "Job deleted!", "Deleted!", 
								JOptionPane.INFORMATION_MESSAGE);

						
						textFieldJobName.setText(null);
						textFieldPaymentJob.setText(null);
						textFieldUrgencyJob.setText(null);
						textAreaJobDescription.setText(null);
						
						comboBoxStatusJob.setSelectedIndex(0);
						comboBoxDeadlineDay.setSelectedIndex(0);
						comboBoxDeadlineMonth.setSelectedIndex(0);
						comboBoxDeadlineYear.setSelectedIndex(0);
						updateJobsList();
					} else
						JOptionPane.showMessageDialog(PressAgencyFrame.this, "Job could not be deleted","Error" ,
								JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnDeleteJob.setBounds(467, 284, 89, 23);
		panelJob.add(btnDeleteJob);

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

		comboBoxStatusJob = new JComboBox<String>();
		comboBoxStatusJob.setModel(new DefaultComboBoxModel<String>(new String[] {
				"new", "assigned", "closed" }));
		comboBoxStatusJob.setBounds(127, 108, 168, 20);
		panelJob.add(comboBoxStatusJob);

		comboBoxDeadlineDay = new JComboBox<Integer>();
		comboBoxDeadlineDay.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {
				1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
				13, 14, 15, 16, 17, 18, 19, 20, 21, 22,
				23, 24, 25, 26, 27, 28, 29, 30, 31 }));
		comboBoxDeadlineDay.setBounds(127, 58, 44, 20);
		panelJob.add(comboBoxDeadlineDay);

		comboBoxDeadlineMonth = new JComboBox<Integer>();
		comboBoxDeadlineMonth
				.setModel(new DefaultComboBoxModel<Integer>(new Integer[] { 1, 2,
						3, 4, 5, 6, 7, 8, 9, 10, 11, 12 }));
		comboBoxDeadlineMonth.setBounds(181, 58, 44, 20);
		panelJob.add(comboBoxDeadlineMonth);

		comboBoxDeadlineYear = new JComboBox<Integer>();
		comboBoxDeadlineYear.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {
				2013, 2014, 2015, 2016, 2017}));
		comboBoxDeadlineYear.setBounds(235, 58, 60, 20);
		panelJob.add(comboBoxDeadlineYear);
		
		textAreaJobDescription = new JTextArea();
		textAreaJobDescription.setBounds(10, 161, 546, 98);
		panelJob.add(textAreaJobDescription);
		
		JRadioButton rdbtnCreateJob = new JRadioButton("Create new job");
		rdbtnCreateJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				btnDeleteJob.setEnabled(false);
				btnUpdateJob.setEnabled(false);
				btnCreateJob.setEnabled(true);
				
				textFieldJobName.setText(null);
				textFieldPaymentJob.setText(null);
				textFieldUrgencyJob.setText(null);
				textAreaJobDescription.setText(null);
				comboBoxStatusJob.setSelectedIndex(0);
				comboBoxDeadlineDay.setSelectedIndex(0);
				comboBoxDeadlineMonth.setSelectedIndex(0);
				comboBoxDeadlineYear.setSelectedIndex(0);
				textFieldJobName.setEnabled(true);
				
				scrollPaneJobs.setEnabled(false);
				listJobs.setEnabled(false);
				
			}
		});
		rdbtnCreateJob.setBounds(120, 22, 109, 23);
		rdbtnCreateJob.setSelected(true);
		panelMyJobs.add(rdbtnCreateJob);
		
		JRadioButton rdbtnManageJobs = new JRadioButton("Manage my jobs");
		rdbtnManageJobs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnDeleteJob.setEnabled(true);
				btnUpdateJob.setEnabled(true);
				btnCreateJob.setEnabled(false);
				comboBoxStatusJob.setSelectedIndex(0);
				comboBoxDeadlineDay.setSelectedIndex(0);
				comboBoxDeadlineMonth.setSelectedIndex(0);
				comboBoxDeadlineYear.setSelectedIndex(0);
				textFieldJobName.setEnabled(false);
				
				scrollPaneJobs.setEnabled(true);
				listJobs.setEnabled(true);
			}
		});
		rdbtnManageJobs.setBounds(231, 22, 109, 23);
		panelMyJobs.add(rdbtnManageJobs);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnCreateJob);
		group.add(rdbtnManageJobs);

		/**
		 * Panel for the photos.
		 */

		JPanel panelMyPhotos = new JPanel();
		pressAgencyTabbedPane.addTab("My Photos", null, panelMyPhotos, null);
		pressAgencyTabbedPane.setEnabledAt(2, false);
		panelMyPhotos.setLayout(null);

		/**
		 * Get press agency's list of photos.
		 */
		myPhotos = PhotoBayRessourceManager.getPhotosList(pressAgency
				.getRef());

		JScrollPane scrollPanePhotos = new JScrollPane();
		scrollPanePhotos.setBounds(10, 11, 99, 360);
		panelMyPhotos.add(scrollPanePhotos);

		/* Hier Liste hinzufügen! */
		listPhotos = new JList<String>();
		listPhotos.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] { "Photo1", "Photo2", "Photo3",
					"EineKuh" };

			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
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
		panelPhoto.setBounds(10, 11, 344, 264);
		panelPhotoContainer.add(panelPhoto);
		panelPhoto.setLayout(null);

		/**
		 * Load an Image
		 */

		File file = new File("C:/Users/Roy/Desktop/flower.png");
		ImagePanel imgPanel = new ImagePanel(file);
		imgPanel.setLocation(0, 0);
		imgPanel.setSize(344, 264);
		imgPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPhoto.add(imgPanel);

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

		// TODO Delete photo resource
		JButton btnDeletePhoto = new JButton("Delete");
		btnDeletePhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Read URI from Photo

				// send delete request with the URI from the photo
			}
		});
		btnDeletePhoto.setBounds(463, 326, 89, 23);
		panelPhotoContainer.add(btnDeletePhoto);

		// TODO POST new photo resource
		JButton btnCreatePhoto = new JButton("Create");
		btnCreatePhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				// Read data from the text fields

				// validate data

				// send

			}
		});
		btnCreatePhoto.setBounds(364, 326, 89, 23);
		panelPhotoContainer.add(btnCreatePhoto);

		// TODO Put photo resource
		JButton btnUpdatePhoto = new JButton("Update");
		btnUpdatePhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Read data from the text fields

				// validate data

				// send
			}
		});
		btnUpdatePhoto.setBounds(265, 326, 89, 23);
		panelPhotoContainer.add(btnUpdatePhoto);

		JButton btnAddPhoto = new JButton("Add Photo");
		btnAddPhoto.setBounds(265, 292, 89, 23);
		panelPhotoContainer.add(btnAddPhoto);

		textFieldPhotoPath = new JTextField();
		textFieldPhotoPath.setBounds(152, 290, 89, 20);
		panelPhotoContainer.add(textFieldPhotoPath);
		textFieldPhotoPath.setColumns(10);
		
				JPanel panelMySubscriptions = new JPanel();
				panelMySubscriptions.setToolTipText("");
				pressAgencyTabbedPane.addTab("My Subscriptions", null,
						panelMySubscriptions, null);
				panelMySubscriptions.setLayout(null);
				
						JScrollPane scrollPane = new JScrollPane();
						scrollPane.setBounds(10, 11, 109, 360);
						panelMySubscriptions.add(scrollPane);
						
								subscriptionsList = new JList<PayloadMessage>();
								subscriptionsList.addListSelectionListener(new ListSelectionListener() {
									public void valueChanged(ListSelectionEvent arg0) {
										PayloadMessage mess = subscriptionsList.getSelectedValue();
										txtMessage.setText(mess.getMessage() + "\n" + mess.getUri());
									}
								});
								subscriptionsList.setModel(new AbstractListModel<PayloadMessage>() {
									/**
									 * 
									 */
									private static final long serialVersionUID = 1L;
									PayloadMessage[] values = new PayloadMessage[] {};
									public int getSize() {
										return values.length;
									}
									public PayloadMessage getElementAt(int index) {
										return values[index];
									}
								});
								scrollPane.setViewportView(subscriptionsList);
								
								txtMessage = new JTextArea();
								txtMessage.setBounds(159, 21, 527, 135);
								panelMySubscriptions.add(txtMessage);

		JPanel panelSearch = new JPanel();
		pressAgencyTabbedPane.addTab("Search", null, panelSearch, null);
		panelSearch.setLayout(null);

		JScrollPane scrollPanePhotographers = new JScrollPane();
		scrollPanePhotographers.setBounds(10, 36, 90, 335);
		panelSearch.add(scrollPanePhotographers);

		listPhotographers = new JList<String>();
		listPhotographers.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String selectedValue = listPhotographers.getSelectedValue();
				boolean check = false;
				if(subscriptions != null && subscriptions.size() > 0)
				{
					for(String sub : subscriptions)
					{
						if(sub.equals(selectedValue+"MainNode"))
						{
							check = true;
							break;
						}
					}
					if(check)
					{
						btnSubscribeToPhotographer.setEnabled(false);
						btnUnsubscribe.setEnabled(true);
					}
					else
					{
						btnSubscribeToPhotographer.setEnabled(true);
						btnUnsubscribe.setEnabled(false);
					}
				}
			}
		});
		listPhotographers.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {};

			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
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

		btnSubscribeToPhotographer = new JButton("Subscribe");
		btnSubscribeToPhotographer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				String selectedPhotographer = listPhotographers.getSelectedValue();
				if(selectedPhotographer != null && !selectedPhotographer.isEmpty())
				{
					List<String> nodes = cnHandler.getAllNodes();
					//boolean result = false;
					if(nodes != null && nodes.size() > 0)
					{
						for(String node : nodes)
						{
							if(node.equals(selectedPhotographer+"MainNode"))
							{
								if(cnHandler.subscribeToNode(node))
								{
									updateMySubscriptionsList();
									btnSubscribeToPhotographer.setEnabled(false);
									btnUnsubscribe.setEnabled(true);
									JOptionPane.showMessageDialog(PressAgencyFrame.this, "Subscribe success!", "Subscribe success!", 
											JOptionPane.INFORMATION_MESSAGE);
								}
								else
									JOptionPane.showMessageDialog(PressAgencyFrame.this, "Cannot subscribe!", "Subscription error!", 
											JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
			}
		});
		btnSubscribeToPhotographer.setBounds(210, 7, 100, 23);
		panelPhotographer.add(btnSubscribeToPhotographer);

		JPanel panelBids = new JPanel();
		panelBids.setFocusable(false);
		panelBids.setEnabled(false);
		panelBids.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelBids.setBounds(10, 36, 556, 313);
		panelPhotographer.add(panelBids);
		panelBids.setLayout(null);

		JScrollPane scrollPanePhotoSells = new JScrollPane();
		scrollPanePhotoSells.setBounds(10, 11, 97, 291);
		panelBids.add(scrollPanePhotoSells);

		listPhotoSells = new JList<String>();
		listPhotoSells.setEnabled(false);
		listPhotoSells.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] { };

			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
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

		JLabel lblStatusPhotoSell = new JLabel("Status");
		lblStatusPhotoSell.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatusPhotoSell.setBounds(342, 38, 46, 14);
		panelBids.add(lblStatusPhotoSell);

		JPanel panelPhotoSell = new JPanel();
		panelPhotoSell.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPhotoSell.setBounds(117, 88, 197, 214);
		panelPhotoSell.setLayout(null);
		panelBids.add(panelPhotoSell);

		/**
		 * Load PhotoSell Image
		 */

		File filePhotoSell = new File("C:/Users/Roy/Desktop/flower.png");
		ImagePanel imgPanelPhotoSell = new ImagePanel(filePhotoSell);
		imgPanelPhotoSell.setLocation(0, 0);
		imgPanelPhotoSell.setSize(197, 214);
		imgPanelPhotoSell.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPhotoSell.setLayout(null);
		panelPhotoSell.add(imgPanelPhotoSell);

		JLabel lblEnterValueBid = new JLabel("Enter value");
		lblEnterValueBid.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterValueBid.setBounds(342, 176, 90, 14);
		panelBids.add(lblEnterValueBid);

		textFieldEnterValueBid = new JTextField();
		textFieldEnterValueBid.setEditable(false);
		textFieldEnterValueBid.setEnabled(false);
		textFieldEnterValueBid.setBounds(342, 201, 86, 20);
		panelBids.add(textFieldEnterValueBid);
		textFieldEnterValueBid.setColumns(10);

		JButton btnOkBid = new JButton("Ok");
		btnOkBid.setEnabled(false);
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

		JLabel lblStatusPhotoSellValue = new JLabel("Status");
		lblStatusPhotoSellValue.setBounds(408, 38, 46, 14);
		panelBids.add(lblStatusPhotoSellValue);
		
		JList list = new JList();
		list.setBounds(442, 76, 1, 1);
		panelBids.add(list);

		btnUnsubscribe = new JButton("Unsubscribe");
		btnUnsubscribe.setEnabled(false);
		btnUnsubscribe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String selectedPhotographer = listPhotographers.getSelectedValue();
				if(selectedPhotographer != null && !selectedPhotographer.isEmpty())
				{
					List<String> nodes = cnHandler.getSubscribedNodes();
					if(nodes != null && nodes.size() > 0)
					{
						for(String node : nodes)
						{
							if(node.equals(selectedPhotographer+"MainNode"))
							{
								if(cnHandler.unSubscribeNode(node))
									JOptionPane.showMessageDialog(PressAgencyFrame.this, "Unsubscribe success!", "Unsubscribe success!", 
											JOptionPane.INFORMATION_MESSAGE);
								else
									JOptionPane.showMessageDialog(PressAgencyFrame.this, "Cannot unsubscribe!", "Unsubscription error!", 
											JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
			}
		});
		btnUnsubscribe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnUnsubscribe.setBounds(320, 7, 100, 23);
		panelPhotographer.add(btnUnsubscribe);

		JLabel lblPhotographer = new JLabel("Photographer");
		lblPhotographer.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPhotographer.setBounds(10, 11, 90, 14);
		panelSearch.add(lblPhotographer);

		/**
		 * Logout and dispose Frame if btnLogout is pressed.
		 */
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				PressAgencyFrame.this.setVisible(false);
				PressAgencyFrame.this.dispose();
			}
		});
		btnLogout.setBounds(622, 428, 89, 23);
		contentPane.add(btnLogout);
	}
	
	private boolean updateMySubscriptionsList()
	{
		try
		{
			subscriptions = cnHandler.getSubscribedNodes();
//			DefaultListModel<String> model = new DefaultListModel<String>();
//			if(subscriptions != null && subscriptions.size() > 0)
//			{
//				for(String sub : subscriptions)
//					model.addElement(sub);
//				subscriptionsList.setModel(model);
//			}
 			return true;
		}
		catch(Exception ex){return false;}
	}
	
	private Boolean updatePhotographersList()
	{
		
		try
		{
			ClientResponse response = webResource.path("/photographers").get(ClientResponse.class);
			if(response != null &&  response.hasEntity() && response.getClientResponseStatus() == Status.OK)
			{
				DefaultListModel<String> model = new DefaultListModel<String>();
				this.photographers = response.getEntity(Photographers.class);
				for(PhotographerRef ref : photographers.getPhotographerRef())
				{
					model.addElement(ref.getUsername());
				}
				
				listPhotographers.setModel(model);
			}
			return true;
		}
		catch(Exception ex) { return false; }
	}
	
	private Boolean updateJobsList(){
		
		try
		{
			ClientResponse response = webResource.queryParam("owner", this.pressAgency.getRef()).path("/jobs/query").get(ClientResponse.class);
			if(response != null &&  response.hasEntity() && response.getClientResponseStatus() == Status.OK)
			{
				DefaultListModel<String> model = new DefaultListModel<String>();
				this.jobs = response.getEntity(Jobs.class);
				for(JobRef ref : jobs.getJobRef())
				{
					model.addElement(ref.getJobName());
				}
				
				listJobs.setModel(model);
			}
			return true;
		}
		catch(Exception ex) { return false; }
		
	}
	
	
	/**
	 * Updates the JobPanel.
	 * 
	 * @param job
	 *            Job object.
	 */
	private void updateJob(Job job) {
		textFieldJobName.setText(job.getJobName());

		textFieldUrgencyJob.setText(job.getUrgency());

		textFieldPaymentJob.setText(String.valueOf(job.getPayment()));
		
		comboBoxDeadlineDay.setSelectedIndex(job.getDeadline().getDay()-1);
		comboBoxDeadlineMonth.setSelectedIndex(job.getDeadline().getMonth()-1);
		comboBoxDeadlineYear.setSelectedIndex(job.getDeadline().getYear()-2013);
		
		if (job.getStatus().equals("new"))
			comboBoxStatusJob.setSelectedIndex(0);
		else if (job.getStatus().equals("assigned"))
			comboBoxStatusJob.setSelectedIndex(1);
		else
			comboBoxStatusJob.setSelectedIndex(2);

		textAreaJobDescription.setText(job.getDescription());

	}

	private Boolean validateJobData() {
		Boolean valid = false;
		
			if (!textFieldJobName.getText().isEmpty()
				&& !textFieldUrgencyJob.getText().isEmpty()
				&& !textFieldPaymentJob.getText().isEmpty()
				&& !textAreaJobDescription.getText().isEmpty()){
				valid = true;
			}

		return valid;
	}

	private Job readJobFields() {
		Job readJob = new Job();
		try{
			
			readJob.setDeadline(DatatypeFactory.newInstance().newXMLGregorianCalendar(
					comboBoxDeadlineYear.getItemAt(comboBoxDeadlineYear.getSelectedIndex()), 
					comboBoxDeadlineMonth.getItemAt(comboBoxDeadlineMonth.getSelectedIndex()), 
					comboBoxDeadlineDay.getItemAt(comboBoxDeadlineDay.getSelectedIndex()), 0, 0, 0, 0, 0));
			
		} catch(DatatypeConfigurationException ex){}
		
		readJob.setDescription(textAreaJobDescription.getText());
		readJob.setJobName(textFieldJobName.getText());
		try {
			readJob.setPayment(BigInteger.valueOf(Long.parseLong(textFieldPaymentJob.getText())));
		} catch (NumberFormatException ex){}
		readJob.setStatus(comboBoxStatusJob.getSelectedItem().toString());
		readJob.setUrgency(textFieldUrgencyJob.getText());
		return readJob;
	}

	public void receivedMessages(PayloadMessage message) {
		if(!message.getMessage().isEmpty() && !message.getUri().isEmpty())
		{
			payloadMessagemodel.addElement(message);
			subscriptionsList.setModel(payloadMessagemodel);
		}
	}
	
	private ItemEventListener<Item> setItemEvent()
	{
		ItemEventListener<Item> listener = new ItemEventListener<Item>() {

			@Override
			public void handlePublishedItems(ItemPublishEvent<Item> items) {
				
				JOptionPane.showMessageDialog(PressAgencyFrame.this, "Es gibt neue Nachrichten", "Neue Nachrichten", 
						JOptionPane.INFORMATION_MESSAGE);
				
				if(items.getItems().size() > 0)
				{
					for(Item item : items.getItems())
					{
						String xml = ((PayloadItem<SimplePayload>)item).getPayload().toXML();
						try
						{
							xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + xml;
							JAXBContext context = JAXBContext.newInstance(PayloadMessage.class);
							Unmarshaller unmarshaller = context.createUnmarshaller();
							PayloadMessage message = (PayloadMessage)unmarshaller.unmarshal(new StreamSource(new StringReader(xml)), 
									PayloadMessage.class).getValue();
							System.out.println(message.getMessage());
							receivedMessages(message);
						}
						catch(Exception ex)
						{
							System.out.println("Error: CustomItemEventListener: " + ex.getMessage());
						}
					}
				}
			}
		};
		return listener;
	}
}
