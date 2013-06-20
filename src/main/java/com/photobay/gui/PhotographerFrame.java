package main.java.com.photobay.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import main.java.com.photobay.jaxbfiles.Job;
import main.java.com.photobay.jaxbfiles.Jobs;
import main.java.com.photobay.jaxbfiles.Photographer;
import main.java.com.photobay.jaxbfiles.Photos;
import main.java.com.photobay.util.ImagePanel;
import main.java.com.photobay.webservice.PhotoBayRessourceManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;

public class PhotographerFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel panelBids;
	private JTextField txtPhotoSellName;
	private JTextField textFieldTitelPhoto;
	private JTextField textFieldEnterValueBid;
	private JList<String> listJobs;
	private Job job;
	private Jobs jobs;
	private Photographer photographer;
	private WebResource webResource;
	private JTextField textFieldPhotoPath;
	private int jobIndex;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the Frame
	 * 
	 * @param userObject
	 */
	public PhotographerFrame(Photographer photographer) {

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

		if(photographer != null)
		{
			this.photographer = photographer;
		}

		setTitle("Logged in as: "
				+ photographer.getGeneralPersonalData().getUsername() + ", ID: "
				+ photographer.getID());

		JTabbedPane pressAgencyTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		pressAgencyTabbedPane.setBounds(10, 11, 701, 410);
		contentPane.add(pressAgencyTabbedPane);

		JPanel panelMyPhotoSells = new JPanel();
		/*OnMouseClicked --> get List*/
		panelMyPhotoSells.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				
				/**
				 * Get jobs list from the press agency.
				 */

				ClientResponse jobsResponse = webResource.path("/jobs").get(
						ClientResponse.class);
				jobs = jobsResponse.getEntity(Jobs.class);

				String[] jobsListValues = new String[] {};

				for (int i = 0; i > jobs.getJobRef().size(); i++) {
					jobsListValues[i] = jobs.getJobRef().get(i).getJobName();
				}

				// Als Private globale variable.
				listJobs = new JList<String>(jobsListValues);
				
				listJobs.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						if (!event.getValueIsAdjusting()) {

							/**
							 * Index from the Element, which has been selected by the
							 * user.
							 */
							JList source = (JList) event.getSource();
							jobIndex = source.getSelectedIndex();

							/**
							 * String with the URI of the job, which is going to be
							 * requested.
							 */
							String jobRef = jobs.getJobRef().get(jobIndex).getUri();

							ClientResponse jobResponse = webResource.path(jobRef).get(
									ClientResponse.class);
							job = jobResponse.getEntity(Job.class);

							/**
							 * Update data from the selected job.
							 */

							//updateJob(job);
						}
					}
				});
				
			}
		});
		
				JPanel panelMyData = new JPanel();
				panelMyData.setToolTipText("");
				pressAgencyTabbedPane.addTab("My Data", null, panelMyData, null);
				panelMyData.setLayout(null);
				
				JLabel lblName = new JLabel("Name:");
				lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblName.setBounds(10, 11, 46, 14);
				panelMyData.add(lblName);
				JLabel lblMyname = new JLabel(photographer.getFirstname() +  " " + photographer.getLastname());
				lblMyname.setBounds(81, 11, 148, 14);
				panelMyData.add(lblMyname);
						
				JLabel lblUsername = new JLabel("Username:");
				lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblUsername.setBounds(239, 11, 67, 14);
				panelMyData.add(lblUsername);
				JLabel lblMyusername = new JLabel(photographer.getGeneralPersonalData()
						.getUsername());
				lblMyusername.setBounds(357, 11, 95, 14);
				panelMyData.add(lblMyusername);
								
				JLabel lblBirthdate = new JLabel("Birthdate:");
				lblBirthdate.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblBirthdate.setBounds(499, 11, 63, 14);
				panelMyData.add(lblBirthdate);
				JLabel lblMylocation = new JLabel("test");
				lblMylocation.setBounds(572, 11, 114, 14);
				panelMyData.add(lblMylocation);
										
				JSeparator separator = new JSeparator();
				separator.setBounds(10, 37, 676, 2);
				panelMyData.add(separator);
												
				JLabel lblAddress = new JLabel("Address:");
				lblAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblAddress.setBounds(10, 50, 61, 14);
				panelMyData.add(lblAddress);
				JLabel lblMyaddress = new JLabel(photographer.getGeneralPersonalData()
						.getAddress().getStreet());
				lblMyaddress.setBounds(81, 50, 78, 14);
				panelMyData.add(lblMyaddress);
														
				JLabel lblHouseNr = new JLabel("House Nr:");
				lblHouseNr.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblHouseNr.setBounds(239, 50, 67, 14);
				panelMyData.add(lblHouseNr);
				JLabel lblMyhousenr = new JLabel(photographer.getGeneralPersonalData()
						.getAddress().getHouseNumber());
				lblMyhousenr.setBounds(357, 50, 67, 14);
				panelMyData.add(lblMyhousenr);
																
				JLabel lblPostalCode = new JLabel("Postal Code:");
				lblPostalCode.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblPostalCode.setBounds(499, 50, 89, 14);
				panelMyData.add(lblPostalCode);
				JLabel lblMypostalcode = new JLabel(String.valueOf(photographer
						.getGeneralPersonalData().getAddress().getPostalCode()));
				lblMypostalcode.setBounds(572, 50, 114, 14);
				panelMyData.add(lblMypostalcode);
																		
				JLabel lblCity = new JLabel("City:");
				lblCity.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblCity.setBounds(10, 75, 46, 14);
				panelMyData.add(lblCity);
				JLabel lblMycity = new JLabel(photographer.getGeneralPersonalData()
						.getAddress().getCity());
				lblMycity.setBounds(81, 75, 78, 14);
				panelMyData.add(lblMycity);
																				
				JLabel lblCountry = new JLabel("Country:");
				lblCountry.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblCountry.setBounds(239, 75, 67, 14);
				panelMyData.add(lblCountry);
				JLabel lblMycountry = new JLabel(photographer.getGeneralPersonalData()
						.getAddress().getCountry());
				lblMycountry.setBounds(357, 75, 72, 14);
				panelMyData.add(lblMycountry);
																						
				JSeparator separator_1 = new JSeparator();
				separator_1.setBounds(10, 101, 676, 2);
				panelMyData.add(separator_1);
																								
				JLabel lblEmail = new JLabel("E-Mail:");
				lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblEmail.setBounds(10, 114, 46, 14);
				panelMyData.add(lblEmail);
				JLabel lblMyemail = new JLabel(photographer.getGeneralPersonalData()
						.getEmail());
				lblMyemail.setBounds(81, 114, 126, 14);
				panelMyData.add(lblMyemail);
				
				JLabel lblPhoneNr = new JLabel("Phone Nr:");
				lblPhoneNr.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblPhoneNr.setBounds(10, 139, 61, 14);
				panelMyData.add(lblPhoneNr);
				JLabel lblMyphonenr = new JLabel(photographer.getGeneralPersonalData()
						.getPhone());
				lblMyphonenr.setBounds(81, 139, 116, 14);
				panelMyData.add(lblMyphonenr);
						
				JLabel lblWebsite = new JLabel("Website:");
				lblWebsite.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblWebsite.setBounds(10, 164, 61, 14);
				panelMyData.add(lblWebsite);
				JLabel lblMywebsite = new JLabel(photographer.getGeneralPersonalData()
						.getWebsite());
				lblMywebsite.setBounds(81, 164, 156, 14);
				panelMyData.add(lblMywebsite);
								
				JLabel lblDescription = new JLabel("Description:");
				lblDescription.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblDescription.setBounds(10, 189, 78, 14);
				panelMyData.add(lblDescription);
				JLabel lblMydescription = new JLabel(photographer
						.getGeneralPersonalData().getDescription());
				lblMydescription.setBounds(80, 189, 606, 148);
				panelMyData.add(lblMydescription);
										
				JButton btnUpdateData = new JButton("Update Data");
				btnUpdateData.setBounds(433, 348, 116, 23);
				panelMyData.add(btnUpdateData);
												
				JLabel lblEquipment = new JLabel("Equipment:");
				lblEquipment.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblEquipment.setBounds(239, 114, 67, 14);
				panelMyData.add(lblEquipment);
												
				JTextArea txtEquipment = new JTextArea();
				txtEquipment.setBounds(357, 114, 329, 69);
				panelMyData.add(txtEquipment);

		pressAgencyTabbedPane.addTab("My Photo Sells", null, panelMyPhotoSells, null);
		panelMyPhotoSells.setLayout(null);

		JScrollPane scrollMyPhotoSells = new JScrollPane();
		scrollMyPhotoSells.setBounds(10, 11, 100, 360);
		panelMyPhotoSells.add(scrollMyPhotoSells);

		scrollMyPhotoSells.setViewportView(listJobs);

		JPanel panelMyPhotoSell = new JPanel();
		panelMyPhotoSell.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMyPhotoSell.setBounds(120, 11, 566, 360);
		panelMyPhotoSells.add(panelMyPhotoSell);
		panelMyPhotoSell.setLayout(null);

		// TODO Post new job's resource.
		JButton btnCreatePhotoSell = new JButton("Create");
		btnCreatePhotoSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			}
		});
		btnCreatePhotoSell.setBounds(368, 326, 89, 23);
		panelMyPhotoSell.add(btnCreatePhotoSell);

		JButton btnUpdatePhotoSell = new JButton("Update");
		btnUpdatePhotoSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

			}
		});
		
		
		
		btnUpdatePhotoSell.setBounds(269, 326, 89, 23);
		panelMyPhotoSell.add(btnUpdatePhotoSell);
		
		JButton btnDeletePhotoSell = new JButton("Delete");
		btnDeletePhotoSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String jobURI = jobs.getJobRef().get(jobIndex).getUri();
				
				Job deleteJobResponse = webResource.path(jobURI)
						.entity(job).delete(Job.class);
				
				
			}
		});
		btnDeletePhotoSell.setBounds(467, 326, 89, 23);
		panelMyPhotoSell.add(btnDeletePhotoSell);

		JLabel lblPhotoSellName = new JLabel("Photo Sell Name:");
		lblPhotoSellName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPhotoSellName.setBounds(10, 8, 107, 14);
		panelMyPhotoSell.add(lblPhotoSellName);

		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setBounds(10, 33, 60, 14);
		panelMyPhotoSell.add(lblPrice);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus.setBounds(10, 58, 60, 14);
		panelMyPhotoSell.add(lblStatus);

		JLabel lblDescriptionJob = new JLabel("Description:");
		lblDescriptionJob.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescriptionJob.setBounds(10, 83, 74, 14);
		panelMyPhotoSell.add(lblDescriptionJob);

		txtPhotoSellName = new JTextField();
		txtPhotoSellName.setBounds(127, 8, 168, 20);
		panelMyPhotoSell.add(txtPhotoSellName);
		txtPhotoSellName.setColumns(10);

		JEditorPane txtDescription = new JEditorPane();
		txtDescription.setText("description");
		txtDescription.setBounds(10, 108, 285, 207);
		panelMyPhotoSell.add(txtDescription);
		
		JSpinner spinPrice = new JSpinner();
		spinPrice.setModel(new SpinnerNumberModel(new Long(0), null, null, new Long(1)));
		spinPrice.setBounds(127, 33, 169, 20);
		panelMyPhotoSell.add(spinPrice);
		
		JLabel lblPhotoSellStatus = new JLabel("New");
		lblPhotoSellStatus.setBounds(127, 58, 46, 14);
		panelMyPhotoSell.add(lblPhotoSellStatus);

		/**
		 * Panel for the photos.
		 */

		JPanel panelMyPhotos = new JPanel();
		pressAgencyTabbedPane.addTab("My Photos", null, panelMyPhotos, null);
		panelMyPhotos.setLayout(null);

		/**
		 * Get press agency's list of photos.
		 */
		Photos myPhotos = PhotoBayRessourceManager.getPhotosList(photographer
				.getRef());

		JScrollPane scrollPanePhotos = new JScrollPane();
		scrollPanePhotos.setBounds(10, 11, 99, 360);
		panelMyPhotos.add(scrollPanePhotos);

		/* Hier Liste hinzufügen! */
		JList listPhotos = new JList();
		listPhotos.setModel(new AbstractListModel() {
			String[] values = new String[] { "Photo1", "Photo2", "Photo3",
					"EineKuh" };

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

				// valide data

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

				// valide data

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
		//textFieldPhotoPath.setColumns(Color(0, 0, 0)));
		
		JPanel panelMySubscriptions = new JPanel();
		panelMySubscriptions.setToolTipText("");
		pressAgencyTabbedPane.addTab("My Subscriptions", null,
				panelMySubscriptions, null);
		panelMySubscriptions.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 109, 360);
		panelMySubscriptions.add(scrollPane);

		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] { "sub1", "sub2", "sub3" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list);

		JPanel panelSearch = new JPanel();
		pressAgencyTabbedPane.addTab("Search", null, panelSearch, null);
		panelSearch.setLayout(null);

		JScrollPane scrollPanePhotographers = new JScrollPane();
		scrollPanePhotographers.setBounds(10, 36, 90, 335);
		panelSearch.add(scrollPanePhotographers);
		
		JList listPhotographers = new JList();
		listPhotographers.setModel(new AbstractListModel() {
			String[] values = new String[] { "photographerA", "photographerB" };

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
		
		panelBids = new JPanel();
		panelBids.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelBids.setBounds(10, 36, 556, 313);
		panelPhotographer.add(panelBids);
		panelBids.setLayout(null);
		
		JScrollPane scrollPanePhotoSells = new JScrollPane();
		scrollPanePhotoSells.setBounds(10, 11, 97, 291);
		panelBids.add(scrollPanePhotoSells);
		
		JList listPhotoSells = new JList();
		listPhotoSells.setModel(new AbstractListModel() {
			String[] values = new String[] { "PhotoSell1", "PhotoSell2" };

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

		/**
		 * Logout and dispose Frame if btnLogout is pressed.
		 */
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				PhotographerFrame.this.setVisible(false);
				PhotographerFrame.this.dispose();
			}
		});

		btnLogout.setBounds(622, 428, 89, 23);
		contentPane.add(btnLogout);

	}
}
