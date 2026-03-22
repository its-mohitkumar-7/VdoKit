package dev.vdokit.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	
	private FileNameExtensionFilter typeVideo;
	
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
		headerPanel.setLayout(new GridBagLayout());
		headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
		headerPanel.setBackground(new Color(181, 181, 181));
		add(headerPanel, BorderLayout.NORTH);
		
		GridBagConstraints GBCH = new GridBagConstraints();
		
		GBCH.anchor = GridBagConstraints.NORTHWEST;
		GBCH.gridx = 0;
		GBCH.weightx = 1.0;
		
		mainMenuButton = new JButton("☰ Main Menu");
		mainMenuButton.setFocusPainted(false);
		mainMenuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mainMenuButton.setForeground(new Color(230, 230, 235));
		mainMenuButton.setBackground(new Color(115, 114, 114));
		mainMenuButton.setFont(new Font("Roboto", Font.BOLD, 15));
		mainMenuButton.setPreferredSize(new Dimension(140, 42));
		headerPanel.add(mainMenuButton, GBCH);
		
		GBCH.gridx = 1;
		GBCH.weightx = 1.0;
		GBCH.weighty = 0;
		GBCH.anchor = GridBagConstraints.NORTHWEST;
		
		JLabel panelTittle = new JLabel("Format");
		panelTittle.setFont(new Font("Roboto", Font.BOLD, 80));
		panelTittle.setForeground(new Color(35, 53, 79, 220));
		headerPanel.add(panelTittle, GBCH);
			
		
		//------------------------Center Panel--------------------//
		
		 
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 350, 40, 350));
		centerPanel.setBackground(new Color(230, 230, 230));
		add(centerPanel, BorderLayout.CENTER);
		
		centerPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints GBCC = new GridBagConstraints();
		
		GBCC.anchor = GridBagConstraints.WEST;
		
		GBCC.gridx = 0;
		GBCC.gridy = 0;
		GBCC.insets = new Insets(16, 10, 16, 10);
			
		videoFileChooser = new JButton("Choose video");
		videoFileChooser.setForeground(new Color(230, 230, 235));
		videoFileChooser.setBackground(new Color(143, 164, 191));
		videoFileChooser.setFont(new Font("Roboto", Font.BOLD, 20));
		videoFileChooser.setFocusable(false);
		videoFileChooser.setPreferredSize(new Dimension(200, 42));
		centerPanel.add(videoFileChooser, GBCC);
		
		GBCC.gridx = 1;
		GBCC.gridy = 0;
		GBCC.weightx = 1.0;
		GBCC.fill = GridBagConstraints.HORIZONTAL;
		
		JTextField videoLocationLabel = new JTextField(28);
		videoLocationLabel.setEditable(false);
		videoLocationLabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
		videoLocationLabel.setPreferredSize(new Dimension(0, 40));
		centerPanel.add(videoLocationLabel,GBCC);	
		
		GBCC.gridx = 0;
		GBCC.gridy = 1;
		GBCC.weightx = 0;
		GBCC.fill = GridBagConstraints.NONE;
		
		videoFormatLabel = new JLabel("Select Video Format: ");
		videoFormatLabel.setLabelFor(videoFormatChooser);
		videoFormatLabel.setFont(new Font("Roboto", Font.BOLD, 20));
		centerPanel.add(videoFormatLabel, GBCC);
		
		GBCC.gridx = 1;
		
		videoFormatChooser = new JComboBox<>(allFormats);
		videoFormatChooser.setSelectedIndex(0);
		videoFormatChooser.setForeground(new Color(230, 230, 235));
		videoFormatChooser.setBackground(new Color(143, 164, 191));
		videoFormatChooser.setFont(new Font("Roboto", Font.BOLD, 20));
		videoFormatChooser.setPreferredSize(new Dimension(150, 36));
		centerPanel.add(videoFormatChooser, GBCC);
		
		GBCC.gridx = 0;
		GBCC.gridy = 2;
		GBCC.insets = new Insets(10, 10, 10, 10);
		
		resizeOutputVideoCheckbox = new JCheckBox("Scale: ");
		resizeOutputVideoCheckbox.setHorizontalTextPosition(JCheckBox.LEADING);
		resizeOutputVideoCheckbox.setForeground(Color.BLACK);
		resizeOutputVideoCheckbox.setBackground(new Color(230, 230, 230));
		resizeOutputVideoCheckbox.setFocusable(false);
		resizeOutputVideoCheckbox.setFont(new Font("Roboto", Font.BOLD, 20));
		centerPanel.add(resizeOutputVideoCheckbox, GBCC);
		
		GBCC.gridx = 1;
		
		resizeOutputVideoChooser = new JComboBox<>(allResolutions);
		resizeOutputVideoChooser.setSelectedIndex(0);
		resizeOutputVideoChooser.setVisible(false);
		resizeOutputVideoChooser.setForeground(new Color(230, 230, 235));
		resizeOutputVideoChooser.setBackground(new Color(143, 164, 191));
		resizeOutputVideoChooser.setFont(new Font("Roboto", Font.BOLD, 20));
		resizeOutputVideoChooser.setPreferredSize(new Dimension(150, 36));
		centerPanel.add(resizeOutputVideoChooser, GBCC);
		
		GBCC.gridx = 1;
		GBCC.gridy = 3;
		GBCC.anchor = GridBagConstraints.CENTER;
		GBCC.insets = new Insets(30, 10, 10, 10);
		
		formatButton = new JButton("Convert");
		formatButton.setForeground(new Color(230, 230, 235));
		formatButton.setBackground(new Color(109, 124, 143));
		formatButton.setFont(new Font("Roboto", Font.BOLD, 40));
		formatButton.setFocusable(false);
		formatButton.setPreferredSize(new Dimension(300, 70));
		centerPanel.add(formatButton, GBCC);
		
		typeVideo = new FileNameExtensionFilter("video type file filter", allFormats);
		
		videoFileChooser.addActionListener(e -> {
		
			JFileChooser jFileChooser = new JFileChooser();
			jFileChooser.setFileFilter(typeVideo);
			int response = jFileChooser.showOpenDialog(this);
			if(response == JFileChooser.APPROVE_OPTION){
				videoFilePath = jFileChooser.getSelectedFile();
				videoLocationLabel.setText(videoFilePath.getAbsolutePath());
				
			}
			
		});
		
		
		resizeOutputVideoCheckbox.addItemListener(e -> {
		
			if(e.getStateChange() == ItemEvent.SELECTED){
				resizeOutputVideoChooser.setVisible(true);
			} else {
				resizeOutputVideoChooser.setVisible(false);
				}
				
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
					videoFilePath = null;
    				videoLocationLabel.setText("");
    				videoFormatChooser.setSelectedIndex(0);
    				resizeOutputVideoCheckbox.setSelected(false);
    				resizeOutputVideoChooser.setSelectedIndex(0);
    				resizeOutputVideoChooser.setVisible(false);
    				
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
		footerPanel.setBackground(new Color(181, 181, 181));
		
		JLabel footerLabel = new JLabel("Powered By FFMPEG");
		footerLabel.setFont(new Font("Roboto", Font.BOLD, 12));
		footerPanel.add(footerLabel);
		
		add(footerPanel, BorderLayout.SOUTH);
		
	}

}
