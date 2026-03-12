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
		panelTittle.setFont(new Font("Roboto", Font.BOLD, 100));
		panelTittle.setForeground(new Color(35, 53, 79));
		panelTittle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		headerPanel.add(panelTittle);
		add(headerPanel, BorderLayout.NORTH);
		
		
		//------------------------Center Panel--------------------//
		
		 
		JPanel centerPanel = new JPanel();
		videoFileChooser = new JButton("Choose video");
		formatButton = new JButton("Convert");
		mainMenuButton = new JButton("Main Menu");
		
		videoFormatChooser = new JComboBox<>(allFormats);
		videoFormatChooser.setSelectedIndex(0);
		
		resizeOutputVideoChooser = new JComboBox<>(allResolutions);
		resizeOutputVideoChooser.setSelectedIndex(0);
		resizeOutputVideoChooser.setVisible(false);
		
		resizeOutputVideoCheckbox = new JCheckBox("Scale");
		
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
		
		// panel attributes
		centerPanel.setLayout(new FlowLayout());
		centerPanel.add(videoFileChooser);
		centerPanel.add(videoFormatChooser);
		centerPanel.add(resizeOutputVideoCheckbox);
		centerPanel.add(resizeOutputVideoChooser);
		centerPanel.add(formatButton);
		centerPanel.add(mainMenuButton);
		add(centerPanel, BorderLayout.CENTER);
		
		
		//-----------------------Footer Panel-----------------------//
		
		
		JPanel footerPanel = new JPanel();
		
		add(footerPanel, BorderLayout.SOUTH);
		
	}

}
