package dev.vdokit.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import dev.vdokit.request.FormatRequest;
import dev.vdokit.core.ProcessRunner;


public class FormatPanel extends JPanel {

	private JButton videoFileChooser;
	private JButton formatButton;
	public JButton mainMenuButton;
	
	private JLabel videoFormatLabel;
	
	private JComboBox<String> videoFormatChooser;
	private JComboBox<String> resizeOutputVideoChooser;
	
	private JCheckBox resizeOutputVideoCheckbox;
	
	private String[] allFormats = {"mkv","mp4","m4v","mov","webm","avi","flv","wmv","asf","3gp","3g2","ts","m2ts","mts","mpeg","mpg","vob","ogv","f4v","rm","rmvb","divx","mxf","gxf","dv","nut","mjpeg","mjpg","y4m","gif"};
	private String[] allResolutions = {"256:144","426:240","640:360","854:480","960:540","1024:576","1280:720","1366:768","1600:900","1920:1080","2048:1080","2560:1440","3200:1800","3840:2160","4096:2160","5120:2880","7680:4320"};

	private CardLayout cardLayout;
	private JPanel container;
	private ProgressPanel progressPanel;

	//choice variables
	File videoFilePath;
	String videoFormatType;
	String resizeOutputVideo;
	
	public FormatPanel(CardLayout cardLayout, JPanel container, ProgressPanel progressPanel){
	
		this.cardLayout = cardLayout;
		this.container = container;
		this.progressPanel = progressPanel;
		
		setLayout(new BorderLayout());
		
		
		//----------------------Header Panel----------------------//
		
		
		JPanel headerPanel = new JPanel();
		
		JLabel panelTittle = new JLabel("Format");
		panelTittle.setFont(new Font("Roboto", Font.BOLD, 120));
		panelTittle.setForeground(new Color(35, 53, 79));
		panelTittle.setBorder(BorderFactory.createEmptyBorder(10, 10, 50, 10));
		
		headerPanel.add(panelTittle);
		add(headerPanel, BorderLayout.NORTH);
		
		
		//------------------------Center Panel--------------------//
		
		 
		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		
		centerPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints GBC = new GridBagConstraints();
		
		GBC.gridx = 0;
		GBC.gridy = 0;
		GBC.gridwidth = 3;
		GBC.gridheight = 3;
		GBC.fill = GridBagConstraints.BOTH;
		
		videoFileChooser = new JButton("Choose video");
		videoFileChooser.setForeground(new Color(230, 230, 235));
		videoFileChooser.setBackground(new Color(143, 164, 191));
		videoFileChooser.setFont(new Font("Roboto", Font.PLAIN, 18));
		videoFileChooser.setPreferredSize(new Dimension(160,40));
		centerPanel.add(videoFileChooser, GBC);
		
		GBC.gridy = 4;
		GBC.gridwidth = 1;
		GBC.gridheight = 1;
		GBC.fill = GridBagConstraints.NONE;
		
		videoFormatLabel = new JLabel("Select Video Format: ");
		videoFormatLabel.setLabelFor(videoFormatChooser);
		centerPanel.add(videoFormatLabel, GBC);
		
		GBC.gridx = 1;
		
		videoFormatChooser = new JComboBox<>(allFormats);
		videoFormatChooser.setSelectedIndex(0);
		videoFormatChooser.setForeground(new Color(230, 230, 235));
		videoFormatChooser.setBackground(new Color(143, 164, 191));
		videoFormatChooser.setFont(new Font("Roboto", Font.PLAIN, 16));
		videoFormatChooser.setPreferredSize(new Dimension(150, 36));
		centerPanel.add(videoFormatChooser, GBC);
		
		GBC.gridx = 0;
		GBC.gridy = 7;
		
		resizeOutputVideoCheckbox = new JCheckBox("Scale: ");
		resizeOutputVideoCheckbox.setHorizontalTextPosition(JCheckBox.LEADING);
		resizeOutputVideoCheckbox.setForeground(Color.BLACK);
		//resizeOutputVideoCheckbox.setBackground(new Color(143, 164, 191));
		resizeOutputVideoCheckbox.setFont(new Font("Roboto", Font.PLAIN, 16));
		centerPanel.add(resizeOutputVideoCheckbox, GBC);
		
		GBC.gridx = 1;
		
		resizeOutputVideoChooser = new JComboBox<>(allResolutions);
		resizeOutputVideoChooser.setSelectedIndex(0);
		resizeOutputVideoChooser.setVisible(false);
		resizeOutputVideoChooser.setForeground(new Color(230, 230, 235));
		resizeOutputVideoChooser.setBackground(new Color(143, 164, 191));
		resizeOutputVideoChooser.setFont(new Font("Roboto", Font.PLAIN, 16));
		resizeOutputVideoChooser.setPreferredSize(new Dimension(150, 36));
		centerPanel.add(resizeOutputVideoChooser, GBC);
		
		GBC.gridx = 0;
		GBC.gridy = 8;
		
		formatButton = new JButton("Convert");
		formatButton.setForeground(new Color(230, 230, 235));
		formatButton.setBackground(new Color(143, 164, 191));
		formatButton.setFont(new Font("Roboto", Font.PLAIN, 18));
		formatButton.setPreferredSize(new Dimension(180,45));
		centerPanel.add(formatButton, GBC);
		
		GBC.gridy = 9;
		GBC.weighty = 1;
		
		mainMenuButton = new JButton("Main Menu");
		mainMenuButton.setForeground(new Color(230, 230, 235));
		mainMenuButton.setBackground(new Color(143, 164, 191));
		mainMenuButton.setFont(new Font("Roboto", Font.PLAIN, 18));
		mainMenuButton.setPreferredSize(new Dimension(150, 36));
		centerPanel.add(mainMenuButton, GBC);		
								
		
		// button listener setup
		
		
		videoFileChooser.addActionListener(e -> {
		
			JFileChooser jFileChooser = new JFileChooser();
			int response = jFileChooser.showOpenDialog(this);
			if(response == JFileChooser.APPROVE_OPTION){
				videoFilePath = jFileChooser.getSelectedFile();
			}
			
		});
		
		
		resizeOutputVideoCheckbox.addItemListener(e -> {
		
			if(e.getStateChange() == ItemEvent.SELECTED){
				resizeOutputVideoChooser.setVisible(true);
			} else resizeOutputVideoChooser.setVisible(false);
		
		});
		
		
		formatButton.addActionListener(e -> {
		
			FormatRequest formatRequest = new FormatRequest();
			
			if(videoFilePath == null){
				JOptionPane.showMessageDialog(this, "Please select video first");
				return;
			}
			
			formatRequest.setVideoInputPath(videoFilePath.getAbsolutePath());
			videoFormatType = (String) videoFormatChooser.getSelectedItem();
			formatRequest.setVideoFormatType(videoFormatType);
			if(resizeOutputVideoChooser.isVisible()){
				resizeOutputVideo = (String) resizeOutputVideoChooser.getSelectedItem();
			} else {
				resizeOutputVideo = null;
			}
			formatRequest.setResizeOutputVideo(resizeOutputVideo);
			progressPanel.showProgress("Formatting video...");
			cardLayout.show(container, "PROGRESSPANEL");
			
			SwingWorker<Void, Void> swingWorker = new SwingWorker<>() {
			
			@Override
			protected Void doInBackground() throws Exception {
				ProcessRunner.run(formatRequest.buildCommand());
				return null;
			}
			
			@Override
			protected void done() {
				progressPanel.hideProgress();
				cardLayout.show(container, "FORMATPANEL");
				try{
					get();
					JOptionPane.showMessageDialog(FormatPanel.this, "Formtting completed!");
				} catch (Exception exception){
					exception.printStackTrace();
					JOptionPane.showMessageDialog(FormatPanel.this, "Error: " + exception.getMessage());
				}
			}
		};	
		swingWorker.execute();		
	});
		
		
		
		
		
		//-----------------------Footer Panel-----------------------//
		
		
		JPanel footerPanel = new JPanel();
		
		add(footerPanel, BorderLayout.SOUTH);
		
	}

}
