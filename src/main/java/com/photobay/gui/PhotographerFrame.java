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
import javax.swing.JTextArea;
import main.java.com.photobay.jaxbfiles.Job;
import main.java.com.photobay.jaxbfiles.Jobs;
import main.java.com.photobay.jaxbfiles.Photographer;
import main.java.com.photobay.jaxbfiles.Photos;
import main.java.com.photobay.util.ImagePanel;
import main.java.com.photobay.webservice.PhotoBayRessourceManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileFilter;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

public class PhotographerFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel panelJobs;
	private JTextField txtPhotoSellName;
	private JTextField txtTitelPhoto;
	private Job job;
	private Jobs jobs;
	private Photographer photographer;
	private WebResource webResource;
	private JTextField txtPhotoPath;
	private int jobIndex;
	private JRadioButton rdbtnCreateANew;
	private JRadioButton rdbtnShowExistingPhoto;
	private JScrollPane scrollMyPhotoSells;
	private JButton btnChooseImg;
	private JLabel lblImagePath;
	private JButton btnUpdatePhotoSell;
	private JButton btnCreatePhotoSell;
	private JButton btnDeletePhotoSell;
	private JList<String> listMyPhotoSells;
	
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

		scrollMyPhotoSells = new JScrollPane();
		scrollMyPhotoSells.setEnabled(false);
		scrollMyPhotoSells.setBounds(10, 37, 100, 334);
		panelMyPhotoSells.add(scrollMyPhotoSells);
		
		listMyPhotoSells = new JList<String>();
		listMyPhotoSells.setModel(new AbstractListModel<String>() {
			String[] values = new String[] { "sub1", "sub2", "sub3" };

			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index];
			}
		});
		scrollMyPhotoSells.setViewportView(listMyPhotoSells);

		JPanel panelMyPhotoSell = new JPanel();
		panelMyPhotoSell.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMyPhotoSell.setBounds(120, 37, 566, 334);
		panelMyPhotoSells.add(panelMyPhotoSell);
		panelMyPhotoSell.setLayout(null);
		
		ImagePanel panelPhotoSellImg = new ImagePanel();
		panelPhotoSellImg.setLocation(312, 108);
		panelPhotoSellImg.setSize(244, 182);
		panelPhotoSellImg.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMyPhotoSell.add(panelPhotoSellImg);

		// TODO Post new job's resource.
		btnCreatePhotoSell = new JButton("Create");
		btnCreatePhotoSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			}
		});
		btnCreatePhotoSell.setBounds(467, 301, 89, 23);
		panelMyPhotoSell.add(btnCreatePhotoSell);

		btnUpdatePhotoSell = new JButton("Update");
		btnUpdatePhotoSell.setVisible(false);
		btnUpdatePhotoSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

			}
		});
		
		
		
		btnUpdatePhotoSell.setBounds(368, 301, 89, 23);
		panelMyPhotoSell.add(btnUpdatePhotoSell);
		
		btnDeletePhotoSell = new JButton("Delete");
		btnDeletePhotoSell.setVisible(false);
		btnDeletePhotoSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String jobURI = jobs.getJobRef().get(jobIndex).getUri();
				
				Job deleteJobResponse = webResource.path(jobURI)
						.entity(job).delete(Job.class);
				
				
			}
		});
		btnDeletePhotoSell.setBounds(467, 301, 89, 23);
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

		JTextArea txtDescription = new JTextArea();
		txtDescription.setText("description");
		txtDescription.setBounds(10, 108, 285, 182);
		panelMyPhotoSell.add(txtDescription);
		
		JSpinner spinPrice = new JSpinner();
		spinPrice.setModel(new SpinnerNumberModel(new Long(0), null, null, new Long(1)));
		spinPrice.setBounds(127, 33, 169, 20);
		panelMyPhotoSell.add(spinPrice);
		
		JLabel lblPhotoSellStatus = new JLabel("New");
		lblPhotoSellStatus.setBounds(127, 58, 46, 14);
		panelMyPhotoSell.add(lblPhotoSellStatus);
		
		JLabel lblHighestBid = new JLabel("Highest Bid:");
		lblHighestBid.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHighestBid.setBounds(312, 8, 74, 14);
		panelMyPhotoSell.add(lblHighestBid);
		
		JLabel lblNewLabel = new JLabel("HighestBidValue");
		lblNewLabel.setBounds(396, 8, 160, 14);
		panelMyPhotoSell.add(lblNewLabel);
		
		JLabel lblBidFrom = new JLabel("Bid From:");
		lblBidFrom.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBidFrom.setBounds(312, 33, 74, 14);
		panelMyPhotoSell.add(lblBidFrom);
		
		JLabel lblNewLabel_1 = new JLabel("BidFromUsername");
		lblNewLabel_1.setBounds(396, 33, 160, 14);
		panelMyPhotoSell.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PressAgencyName");
		lblNewLabel_2.setBounds(396, 58, 160, 14);
		panelMyPhotoSell.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Accept Bid");
		btnNewButton.setBounds(396, 79, 89, 23);
		panelMyPhotoSell.add(btnNewButton);
		
		lblImagePath = new JLabel("");
		lblImagePath.setBounds(127, 305, 168, 14);
		panelMyPhotoSell.add(lblImagePath);
		
		btnChooseImg = new JButton("Choose Img:");
		btnChooseImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooserImg = new JFileChooser();
				fileChooserImg.setDialogTitle("Choose your Image");
				fileChooserImg.setDialogType(JFileChooser.OPEN_DIALOG);
				fileChooserImg.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooserImg.setFileFilter(new javax.swing.filechooser.FileFilter() {
					
					@Override
					public String getDescription() {
						return "Images(*.jpg; *.jpeg; *.png)";
					}
					
					@Override
					public boolean accept(File file) {
						String filename = file.getName().toLowerCase();
						return filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith("png") || file.isDirectory();
					}
				});
				fileChooserImg.setVisible(true);
				int result = fileChooserImg.showOpenDialog(null);
			}
		});
		btnChooseImg.setBounds(10, 301, 107, 23);
		panelMyPhotoSell.add(btnChooseImg);
		
		rdbtnCreateANew = new JRadioButton("Create a new Photo Sell");
		rdbtnCreateANew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollMyPhotoSells.setEnabled(false);
				listMyPhotoSells.setEnabled(false);
				btnChooseImg.setVisible(true);
				lblImagePath.setVisible(true);
				btnUpdatePhotoSell.setVisible(false);
				btnDeletePhotoSell.setVisible(false);
			}
		});
		rdbtnCreateANew.setSelected(true);
		rdbtnCreateANew.setBounds(10, 7, 141, 23);
		panelMyPhotoSells.add(rdbtnCreateANew);
		
		rdbtnShowExistingPhoto = new JRadioButton("Manage existing Photo Sells");
		rdbtnShowExistingPhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollMyPhotoSells.setEnabled(true);
				listMyPhotoSells.setEnabled(true);
				btnChooseImg.setVisible(false);
				lblImagePath.setVisible(false);
				btnUpdatePhotoSell.setVisible(true);
				btnDeletePhotoSell.setVisible(true);
			}
		});
		rdbtnShowExistingPhoto.setBounds(153, 7, 170, 23);
		panelMyPhotoSells.add(rdbtnShowExistingPhoto);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnCreateANew);
		group.add(rdbtnShowExistingPhoto);

		/**
		 * Panel for the photos.
		 */

		JPanel panelMyPhotos = new JPanel();
		panelMyPhotos.setVisible(false);
		pressAgencyTabbedPane.addTab("My Photos", null, panelMyPhotos, null);
		pressAgencyTabbedPane.setEnabledAt(2, false);
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

		ImagePanel imgPanel = new ImagePanel();
		imgPanel.setLocation(0, 0);
		imgPanel.setSize(344, 264);
		imgPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPhoto.add(imgPanel);

		JLabel lblTitelPhoto = new JLabel("Titel");
		lblTitelPhoto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitelPhoto.setBounds(364, 11, 46, 14);
		panelPhotoContainer.add(lblTitelPhoto);

		txtTitelPhoto = new JTextField();
		txtTitelPhoto.setBounds(364, 36, 193, 20);
		panelPhotoContainer.add(txtTitelPhoto);
		txtTitelPhoto.setColumns(10);

		JLabel lblDescriptionPhoto = new JLabel("Description");
		lblDescriptionPhoto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescriptionPhoto.setBounds(364, 67, 74, 14);
		panelPhotoContainer.add(lblDescriptionPhoto);

		JTextArea txtDescriptionPhoto = new JTextArea();
		txtDescriptionPhoto.setBounds(364, 92, 193, 218);
		panelPhotoContainer.add(txtDescriptionPhoto);

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

		txtPhotoPath = new JTextField();
		txtPhotoPath.setBounds(152, 290, 89, 20);
		panelPhotoContainer.add(txtPhotoPath);
		//textFieldPhotoPath.setColumns(Color(0, 0, 0)));
		
		JPanel panelMySubscriptions = new JPanel();
		panelMySubscriptions.setToolTipText("");
		pressAgencyTabbedPane.addTab("My Subscriptions", null,
				panelMySubscriptions, null);
		panelMySubscriptions.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 109, 360);
		panelMySubscriptions.add(scrollPane);

		JList listSubscriptions = new JList();
		listSubscriptions.setModel(new AbstractListModel() {
			String[] values = new String[] { "sub1", "sub2", "sub3" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(listSubscriptions);

		JPanel panelSearch = new JPanel();
		pressAgencyTabbedPane.addTab("Search Jobs", null, panelSearch, null);
		panelSearch.setLayout(null);

		JScrollPane scrollPanePhotographers = new JScrollPane();
		scrollPanePhotographers.setBounds(10, 36, 90, 335);
		panelSearch.add(scrollPanePhotographers);
		
		JList listPessAgencies = new JList();
		listPessAgencies.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPessAgencies.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPanePhotographers.setViewportView(listPessAgencies);
		
		JPanel panelPressAgency = new JPanel();
		panelPressAgency.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPressAgency.setBounds(110, 11, 576, 360);
		panelSearch.add(panelPressAgency);
		panelPressAgency.setLayout(null);

		JLabel lblPressAgencyName = new JLabel("PressAgency Name");
		lblPressAgencyName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPressAgencyName.setBounds(10, 11, 201, 14);
		panelPressAgency.add(lblPressAgencyName);

		JButton btnSubscribeToPressAgency = new JButton("Subscribe");
		btnSubscribeToPressAgency.setBounds(210, 7, 100, 23);
		panelPressAgency.add(btnSubscribeToPressAgency);
		
		panelJobs = new JPanel();
		panelJobs.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelJobs.setBounds(10, 36, 556, 313);
		panelPressAgency.add(panelJobs);
		panelJobs.setLayout(null);
		
		JScrollPane scrollPanePhotoSells = new JScrollPane();
		scrollPanePhotoSells.setBounds(10, 11, 97, 291);
		panelJobs.add(scrollPanePhotoSells);
		
		JList listJobs = new JList();
		listJobs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listJobs.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPanePhotoSells.setViewportView(listJobs);
		
		JLabel lblJobName = new JLabel("Job Name:");
		lblJobName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblJobName.setBounds(117, 13, 67, 14);
		panelJobs.add(lblJobName);

		JLabel lblUrgency = new JLabel("Urgency:");
		lblUrgency.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUrgency.setBounds(117, 38, 97, 14);
		panelJobs.add(lblUrgency);

		JLabel lblJobDescriptionSell = new JLabel("Job Description:");
		lblJobDescriptionSell.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblJobDescriptionSell.setBounds(117, 63, 97, 14);
		panelJobs.add(lblJobDescriptionSell);

		JLabel lblJobStatus = new JLabel("Job Status:");
		lblJobStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblJobStatus.setBounds(347, 13, 78, 14);
		panelJobs.add(lblJobStatus);

		/**
		 * Load PhotoSell Image
		 */

		File filePhotoSell = new File("C:/Users/Roy/Desktop/flower.png");

		JButton btnApplyJob = new JButton("Apply");
		btnApplyJob.setBounds(457, 279, 89, 23);
		panelJobs.add(btnApplyJob);

		JLabel lblJobNamelValue = new JLabel("Name");
		lblJobNamelValue.setBounds(230, 13, 46, 14);
		panelJobs.add(lblJobNamelValue);

		JLabel lblUrgencyValue = new JLabel("Urgency");
		lblUrgencyValue.setBounds(230, 38, 46, 14);
		panelJobs.add(lblUrgencyValue);

		JLabel lblJobDesciptionValue = new JLabel("Desciption");
		lblJobDesciptionValue.setVerticalAlignment(SwingConstants.TOP);
		lblJobDesciptionValue.setVerticalTextPosition(SwingConstants.TOP);
		lblJobDesciptionValue.setBounds(230, 63, 316, 88);
		panelJobs.add(lblJobDesciptionValue);

		JLabel lblJobStatusValue = new JLabel("Status");
		lblJobStatusValue.setBounds(435, 13, 46, 14);
		panelJobs.add(lblJobStatusValue);
		
		JLabel lblDeadline = new JLabel("Deadline:");
		lblDeadline.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDeadline.setBounds(347, 38, 78, 14);
		panelJobs.add(lblDeadline);
		
		JLabel lblDeadlineValue = new JLabel("Deadline");
		lblDeadlineValue.setBounds(435, 38, 46, 14);
		panelJobs.add(lblDeadlineValue);
		
		JLabel lblPayment = new JLabel("Payment:");
		lblPayment.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPayment.setBounds(117, 162, 62, 14);
		panelJobs.add(lblPayment);
		
		JLabel lblPaymentValue = new JLabel("Payment");
		lblPaymentValue.setBounds(230, 162, 46, 14);
		panelJobs.add(lblPaymentValue);
		
		JLabel lblApplicationMessage = new JLabel("Application Message:");
		lblApplicationMessage.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblApplicationMessage.setBounds(117, 187, 142, 14);
		panelJobs.add(lblApplicationMessage);
		
		JTextArea txtApplicationMessage = new JTextArea();
		txtApplicationMessage.setBounds(269, 187, 277, 81);
		panelJobs.add(txtApplicationMessage);

		JButton btnUnsubscribe = new JButton("Unsubscribe");
		btnUnsubscribe.setBounds(320, 7, 100, 23);
		panelPressAgency.add(btnUnsubscribe);

		JLabel lblPressAgencies = new JLabel("Press Agencies");
		lblPressAgencies.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPressAgencies.setBounds(10, 11, 90, 14);
		panelSearch.add(lblPressAgencies);

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
