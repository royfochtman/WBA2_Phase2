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

import main.java.com.photobay.jaxbfiles.Bid;
import main.java.com.photobay.jaxbfiles.Bids;
import main.java.com.photobay.jaxbfiles.Bids.BidRef;
import main.java.com.photobay.jaxbfiles.Job;
import main.java.com.photobay.jaxbfiles.Jobs;
import main.java.com.photobay.jaxbfiles.PayloadMessage;
import main.java.com.photobay.jaxbfiles.PhotoSell;
import main.java.com.photobay.jaxbfiles.PhotoSells;
import main.java.com.photobay.jaxbfiles.PhotoSells.PhotoSellRef;
import main.java.com.photobay.jaxbfiles.Photographer;
import main.java.com.photobay.jaxbfiles.PressAgency;
import main.java.com.photobay.util.ImageManipulation;
import main.java.com.photobay.util.ImagePanel;
import main.java.com.photobay.webservice.PhotoBayRessourceManager;
import main.java.com.photobay.xmppClient.XmppConnectionHandler;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileFilter;
import java.math.BigInteger;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.client.WebResource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

import org.jivesoftware.smackx.pubsub.AccessModel;
import org.jivesoftware.smackx.pubsub.FormType;
import org.jivesoftware.smackx.pubsub.PublishModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PhotographerFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel panelJobs;
	private JTextField txtPhotoSellName;
	private JTextField txtTitelPhoto;
	private Photographer photographer;
	private WebResource webResource;
	private JTextField txtPhotoPath;
	private JRadioButton rdbtnCreateANew;
	private JRadioButton rdbtnShowExistingPhoto;
	private JScrollPane scrollMyPhotoSells;
	private JButton btnChooseImg;
	private JLabel lblImagePath;
	private JButton btnUpdatePhotoSell;
	private JButton btnCreatePhotoSell;
	private JButton btnDeletePhotoSell;
	private JList<String> listMyPhotoSells;
	private JLabel lblMyname;
	private JSpinner spinPrice;
	private JLabel lblPhotoSellStatus;
	private JTextArea txtDescription;
	private File photoSellImgFile = null;
	private ImagePanel panelPhotoSellImg;
	private PhotoSells photoSells;
	private JLabel lblHighestBidValue;
	private JLabel lblBidFromUsername;
	private JLabel lblBidFromPressAgencyName;
	private XmppConnectionHandler cnHandler = null;
	private int selectedTab = 0;
	
	private Boolean validatePhotoSell()
	{
		if(txtPhotoSellName.getText().isEmpty() || txtDescription.getText().isEmpty() || lblImagePath.getText().isEmpty())
			return false;
		return true;
	}
	
	private Boolean updatePhotoSellsList()
	{ //Client.create().resource(WebserviceConfig.WS_ADDRESS).path(ref).get(ClientResponse.class);
		try
		{
			ClientResponse response = webResource.queryParam("owner", this.photographer.getRef()).path("/photoSells/query").get(ClientResponse.class);
			if(response != null &&  response.hasEntity() && response.getClientResponseStatus() == Status.OK)
			{
				DefaultListModel<String> model = new DefaultListModel<String>();
				this.photoSells = response.getEntity(PhotoSells.class);
				for(PhotoSellRef ref : photoSells.getPhotoSellRef())
				{
					model.addElement(ref.getPhotoSellName());
				}
				listMyPhotoSells.setModel(model);
			}
			return true;
		}
		catch(Exception ex) { return false; }
	}
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the Frame
	 * 
	 * @param userObject
	 */
	public PhotographerFrame(Photographer photographerObject, XmppConnectionHandler cn) {

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

		if(photographerObject != null)
			this.photographer = photographerObject;
		if(cn != null)
			this.cnHandler = cn;
		setTitle("Logged in as: "
				+ photographer.getGeneralPersonalData().getUsername() + ", ID: "
				+ photographer.getID());

		JTabbedPane photographerTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		photographerTabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent p) {
				JTabbedPane pane = (JTabbedPane)p.getComponent();
				int selectedIndex = pane.getSelectedIndex();
				if(selectedIndex == 1 && selectedIndex != selectedTab)
				{
					selectedTab = selectedIndex;
					updatePhotoSellsList();
				}
			}
		});
		photographerTabbedPane.setBounds(10, 11, 701, 410);
		contentPane.add(photographerTabbedPane);

		JPanel panelMyPhotoSells = new JPanel();
		/*OnMouseClicked --> get List*/
		
				JPanel panelMyData = new JPanel();
				panelMyData.setToolTipText("");
				photographerTabbedPane.addTab("My Data", null, panelMyData, null);
				panelMyData.setLayout(null);
				
				JLabel lblName = new JLabel("Name:");
				lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblName.setBounds(10, 11, 46, 14);
				panelMyData.add(lblName);
				lblMyname = new JLabel(photographer.getFirstname() + " " + photographer.getLastname());
				lblMyname.setBounds(81, 11, 189, 14);
				panelMyData.add(lblMyname);
						
				JLabel lblUsername = new JLabel("Username:");
				lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblUsername.setBounds(280, 11, 67, 14);
				panelMyData.add(lblUsername);
				JLabel lblMyusername = new JLabel(photographer.getGeneralPersonalData()
						.getUsername());
				lblMyusername.setBounds(357, 11, 132, 14);
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
												
				JLabel lblStreet = new JLabel("Street:");
				lblStreet.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblStreet.setBounds(10, 50, 61, 14);
				panelMyData.add(lblStreet);
				JLabel lblMyStreet = new JLabel(photographer.getGeneralPersonalData()
						.getAddress().getStreet());
				lblMyStreet.setBounds(81, 50, 189, 14);
				panelMyData.add(lblMyStreet);
														
				JLabel lblHouseNr = new JLabel("House Nr:");
				lblHouseNr.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblHouseNr.setBounds(280, 50, 67, 14);
				panelMyData.add(lblHouseNr);
				JLabel lblMyhousenr = new JLabel(photographer.getGeneralPersonalData()
						.getAddress().getHouseNumber());
				lblMyhousenr.setBounds(357, 50, 129, 14);
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
				lblMycity.setBounds(81, 75, 189, 14);
				panelMyData.add(lblMycity);
																				
				JLabel lblCountry = new JLabel("Country:");
				lblCountry.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblCountry.setBounds(280, 75, 67, 14);
				panelMyData.add(lblCountry);
				JLabel lblMycountry = new JLabel(photographer.getGeneralPersonalData()
						.getAddress().getCountry());
				lblMycountry.setBounds(357, 75, 129, 14);
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
				lblMyemail.setBounds(81, 114, 189, 14);
				panelMyData.add(lblMyemail);
				
				JLabel lblPhoneNr = new JLabel("Phone Nr:");
				lblPhoneNr.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblPhoneNr.setBounds(10, 139, 61, 14);
				panelMyData.add(lblPhoneNr);
				JLabel lblMyphonenr = new JLabel(photographer.getGeneralPersonalData()
						.getPhone());
				lblMyphonenr.setBounds(81, 139, 189, 14);
				panelMyData.add(lblMyphonenr);
						
				JLabel lblWebsite = new JLabel("Website:");
				lblWebsite.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblWebsite.setBounds(10, 164, 61, 14);
				panelMyData.add(lblWebsite);
				JLabel lblMywebsite = new JLabel(photographer.getGeneralPersonalData()
						.getWebsite());
				lblMywebsite.setBounds(81, 164, 189, 14);
				panelMyData.add(lblMywebsite);
								
				JLabel lblDescription = new JLabel("Description:");
				lblDescription.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblDescription.setBounds(10, 189, 78, 14);
				panelMyData.add(lblDescription);
				JLabel lblMydescription = new JLabel(photographer
						.getGeneralPersonalData().getDescription());
				lblMydescription.setVerticalAlignment(SwingConstants.TOP);
				lblMydescription.setBounds(80, 189, 606, 148);
				panelMyData.add(lblMydescription);
										
				JButton btnUpdateData = new JButton("Update Data");
				btnUpdateData.setEnabled(false);
				btnUpdateData.setVisible(false);
				btnUpdateData.setBounds(433, 348, 116, 23);
				panelMyData.add(btnUpdateData);
												
				JLabel lblEquipment = new JLabel("Equipment:");
				lblEquipment.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblEquipment.setBounds(280, 114, 67, 14);
				panelMyData.add(lblEquipment);
				
				JLabel lblMyEquipment = new JLabel(photographer.getEquipment());
				lblMyEquipment.setVerticalAlignment(SwingConstants.TOP);
				lblMyEquipment.setBounds(357, 114, 329, 64);
				panelMyData.add(lblMyEquipment);

		photographerTabbedPane.addTab("My Photo Sells", null, panelMyPhotoSells, null);
		panelMyPhotoSells.setLayout(null);

		scrollMyPhotoSells = new JScrollPane();
		scrollMyPhotoSells.setEnabled(false);
		scrollMyPhotoSells.setBounds(10, 37, 100, 334);
		panelMyPhotoSells.add(scrollMyPhotoSells);
		
		listMyPhotoSells = new JList<String>();
		listMyPhotoSells.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent sel) {
				String val = listMyPhotoSells.getSelectedValue();
				PhotoSellRef ref = new PhotoSellRef();
				for(PhotoSellRef refItem : photoSells.getPhotoSellRef())
				{
					if(refItem.getPhotoSellName() == val)
						ref = refItem;
				}
				ClientResponse response = webResource.path(ref.getUri().replaceFirst(".", "")).get(ClientResponse.class);
				if(response != null && response.hasEntity() && response.getClientResponseStatus() == Status.OK)
				{
					PhotoSell photoSell = response.getEntity(PhotoSell.class);
					txtDescription.setText(photoSell.getDescription());
					txtPhotoSellName.setText(photoSell.getName());
					lblPhotoSellStatus.setText(photoSell.getStatus());
					spinPrice.setValue(photoSell.getPrice());
					panelPhotoSellImg.setImage(ImageManipulation.toFile(photoSell.getPhoto()));
					response = webResource.path(photoSell.getBidsRef().replaceFirst(".", "")).get(ClientResponse.class);
					if(response != null && response.hasEntity() && response.getClientResponseStatus() == Status.OK)
					{
						Bids bids = response.getEntity(Bids.class);
						BidRef bid = new BidRef();
						for(BidRef bidRef : bids.getBidRef())
						{
							int compare = bidRef.getValue().compareTo(bid.getValue());
							if(compare == 1)
								bid = bidRef;
						}
						response = webResource.path(bid.getPressAgencyRef().replaceFirst(".", "")).get(ClientResponse.class);
						if(response != null && response.hasEntity() && response.getClientResponseStatus() == Status.OK)
						{
							PressAgency pressAgency = response.getEntity(PressAgency.class);
							lblHighestBidValue.setText(bid.getValue().toString());
							lblBidFromUsername.setText(pressAgency.getGeneralPersonalData().getUsername());
							lblBidFromPressAgencyName.setText(pressAgency.getName());
						}
					}
				}
					
			}
		});
		listMyPhotoSells.setEnabled(false);
		listMyPhotoSells.setModel(new AbstractListModel<String>() {
			String[] values = new String[] {  };

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
		
		panelPhotoSellImg = new ImagePanel();
		panelPhotoSellImg.setLocation(312, 108);
		panelPhotoSellImg.setSize(244, 182);
		panelPhotoSellImg.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMyPhotoSell.add(panelPhotoSellImg);

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
				Object[] options = {"Yes","No","Cancel"};
				int n = JOptionPane.showOptionDialog(PhotographerFrame.this,
					    "Would you realy like to delete this photo sell?",
					    "Delete?",
					    JOptionPane.YES_NO_CANCEL_OPTION,
					    JOptionPane.QUESTION_MESSAGE,
					    null, options, options[2]);
				if(n == 0)
				{
					String val = listMyPhotoSells.getSelectedValue();
					PhotoSellRef ref = new PhotoSellRef();
					for(PhotoSellRef photoSellRef : photoSells.getPhotoSellRef())
					{
						if(photoSellRef.getPhotoSellName() == val)
						{
							ref = photoSellRef;
							break;
						}
					}
					ClientResponse response = webResource.path(ref.getUri().replaceFirst(".", "")).delete(ClientResponse.class);
					if(response != null && response.hasEntity() && response.getClientResponseStatus() == Status.OK)
					{
						JOptionPane.showMessageDialog(PhotographerFrame.this, "Photo Sell deleted!", "Deleted!", 
								JOptionPane.INFORMATION_MESSAGE);
						txtDescription.setText("");
						txtPhotoSellName.setText("");
						lblImagePath.setText("");
						spinPrice.setValue(0);
						panelPhotoSellImg.clearImage();
						updatePhotoSellsList();
					}
				}
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

		txtDescription = new JTextArea();
		txtDescription.setBounds(10, 108, 285, 182);
		panelMyPhotoSell.add(txtDescription);
		
		spinPrice = new JSpinner();
		spinPrice.setModel(new SpinnerNumberModel(new Long(0), new Long(0), null, new Long(1)));
		spinPrice.setBounds(127, 33, 169, 20);
		panelMyPhotoSell.add(spinPrice);
		
		lblPhotoSellStatus = new JLabel("New");
		lblPhotoSellStatus.setBounds(127, 58, 46, 14);
		panelMyPhotoSell.add(lblPhotoSellStatus);
		
		JLabel lblHighestBid = new JLabel("Highest Bid:");
		lblHighestBid.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHighestBid.setBounds(312, 8, 74, 14);
		panelMyPhotoSell.add(lblHighestBid);
		
		lblHighestBidValue = new JLabel("");
		lblHighestBidValue.setBounds(396, 8, 160, 14);
		panelMyPhotoSell.add(lblHighestBidValue);
		
		JLabel lblBidFrom = new JLabel("Bid From:");
		lblBidFrom.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBidFrom.setBounds(312, 33, 74, 14);
		panelMyPhotoSell.add(lblBidFrom);
		
		lblBidFromUsername = new JLabel("");
		lblBidFromUsername.setBounds(396, 33, 160, 14);
		panelMyPhotoSell.add(lblBidFromUsername);
		
		lblBidFromPressAgencyName = new JLabel("");
		lblBidFromPressAgencyName.setBounds(396, 58, 160, 14);
		panelMyPhotoSell.add(lblBidFromPressAgencyName);
		
		JButton btnAcceptBid = new JButton("Accept Bid");
		btnAcceptBid.setEnabled(false);
		btnAcceptBid.setBounds(396, 79, 89, 23);
		panelMyPhotoSell.add(btnAcceptBid);
		
		lblImagePath = new JLabel("");
		lblImagePath.setBounds(127, 305, 330, 14);
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
				
				if(result == JFileChooser.APPROVE_OPTION)
				{
					photoSellImgFile = fileChooserImg.getSelectedFile();
					lblImagePath.setText(photoSellImgFile.getPath());
					panelPhotoSellImg.setImage(photoSellImgFile);
				}
			}
		});
		btnChooseImg.setBounds(10, 301, 107, 23);
		panelMyPhotoSell.add(btnChooseImg);
		
				// TODO Post new job's resource.
				btnCreatePhotoSell = new JButton("Create");
				btnCreatePhotoSell.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if(validatePhotoSell())
						{
							PhotoSell photoSell = new PhotoSell();
							photoSell.setName(txtPhotoSellName.getText());
							photoSell.setStatus(lblPhotoSellStatus.getText());
							photoSell.setDescription(txtDescription.getText());
							photoSell.setPhotographerRef(PhotographerFrame.this.photographer.getRef());
							photoSell.setPrice(BigInteger.valueOf((long)spinPrice.getValue()));
							
							if(photoSellImgFile != null)
							{
								String encodedFile = ImageManipulation.encodeImage(photoSellImgFile);
								if(encodedFile != null && !encodedFile.isEmpty())
									photoSell.setPhoto(ImageManipulation.decodeImage(encodedFile));
							}
							
							ClientResponse response = webResource.path("/photoSells").entity(photoSell).post(ClientResponse.class, photoSell);
							if(response != null && response.hasEntity() && response.getClientResponseStatus() == Status.OK)
							{
								photoSell = response.getEntity(PhotoSell.class);
								PayloadMessage message = new PayloadMessage();
								message.setMessage(photographer.getGeneralPersonalData().getUsername() + "has post a new photo Sell." + 
								"See link below.");
								message.setUri(photoSell.getRef());
								cnHandler.assignPayloadToNode(photographer.getGeneralPersonalData().getUsername(), 
										cnHandler.createNodeConf(FormType.submit, true, true, PublishModel.publishers, AccessModel.open)
										, "payloadMessage", message);
								JOptionPane.showMessageDialog(PhotographerFrame.this, "Photo Sell saved!", "Saved!", 
										JOptionPane.INFORMATION_MESSAGE);
								txtDescription.setText("");
								txtPhotoSellName.setText("");
								lblImagePath.setText("");
								spinPrice.setValue(0);
								panelPhotoSellImg.clearImage();
								updatePhotoSellsList();
							}
							
						}
						else
							JOptionPane.showMessageDialog(PhotographerFrame.this, "Incomplete data!", "Incomplete data!", 
									JOptionPane.ERROR_MESSAGE);
					}
				});
				btnCreatePhotoSell.setBounds(467, 301, 89, 23);
				panelMyPhotoSell.add(btnCreatePhotoSell);
		
		rdbtnCreateANew = new JRadioButton("Create a new Photo Sell");
		rdbtnCreateANew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollMyPhotoSells.setEnabled(false);
				listMyPhotoSells.setEnabled(false);
				btnChooseImg.setVisible(true);
				lblImagePath.setVisible(true);
				btnUpdatePhotoSell.setVisible(false);
				btnDeletePhotoSell.setVisible(false);
				btnCreatePhotoSell.setVisible(true);
			}
		});
		rdbtnCreateANew.setSelected(true);
		rdbtnCreateANew.setBounds(10, 7, 190, 23);
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
				btnCreatePhotoSell.setVisible(false);
			}
		});
		rdbtnShowExistingPhoto.setBounds(220, 7, 190, 23);
		panelMyPhotoSells.add(rdbtnShowExistingPhoto);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnCreateANew);
		group.add(rdbtnShowExistingPhoto);

		/**
		 * Panel for the photos.
		 */

		JPanel panelMyPhotos = new JPanel();
		panelMyPhotos.setEnabled(false);
		panelMyPhotos.setVisible(false);
		photographerTabbedPane.addTab("My Photos", null, panelMyPhotos, null);
		photographerTabbedPane.setEnabledAt(2, false);
		panelMyPhotos.setLayout(null);

		/**
		 * Get press agency's list of photos.
		 */
//		Photos myPhotos = PhotoBayRessourceManager.getPhotosList(photographer
//				.getRef());

		JScrollPane scrollPanePhotos = new JScrollPane();
		scrollPanePhotos.setBounds(10, 11, 99, 360);
		panelMyPhotos.add(scrollPanePhotos);

		/* Hier Liste hinzufügen! */
		JList<String> listPhotos = new JList<String>();
		listPhotos.setModel(new AbstractListModel<String>() {
			String[] values = new String[] { };

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
		photographerTabbedPane.addTab("My Subscriptions", null,
				panelMySubscriptions, null);
		panelMySubscriptions.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 109, 360);
		panelMySubscriptions.add(scrollPane);

		JList<String> listSubscriptions = new JList<String>();
		listSubscriptions.setModel(new AbstractListModel<String>() {
			String[] values = new String[] {};

			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(listSubscriptions);

		JPanel panelSearch = new JPanel();
		photographerTabbedPane.addTab("Search Jobs", null, panelSearch, null);
		panelSearch.setLayout(null);

		JScrollPane scrollPanePhotographers = new JScrollPane();
		scrollPanePhotographers.setBounds(10, 36, 90, 335);
		panelSearch.add(scrollPanePhotographers);
		
		JList<String> listPressAgencies = new JList<String>();
		listPressAgencies.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPressAgencies.setModel(new AbstractListModel<String>() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		scrollPanePhotographers.setViewportView(listPressAgencies);
		
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
		
		JList<String> listJobs = new JList<String>();
		listJobs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listJobs.setModel(new AbstractListModel<String>() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
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
