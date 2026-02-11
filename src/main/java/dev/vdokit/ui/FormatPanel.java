package dev.vdokit.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import dev.vdokit.request.FormatRequest;
import dev.vdokit.core.ProcessRunner;


public class FormatPanel extends JPanel {

	private JButton videoFileChooser;
	private JButton formatButton;
	public JButton mainMenuButton;
	private JComboBox<String> videoFormatChooser;
	private String[] allFormats = {"mkv","mp4","m4v","mov","webm","avi","flv","wmv","asf","3gp","3g2","ts","m2ts","mts","mpeg","mpg","vob","ogv","f4v","rm","rmvb","divx","mxf","gxf","dv","nut","mjpeg","mjpg","y4m","gif"};

	private CardLayout cardLayout;
	private JPanel container;
	private ProgressPanel progressPanel;

	//choice variables
	File videoFilePath;
	String videoFormatType;
	
	public FormatPanel(CardLayout cardLayout, JPanel container, ProgressPanel progressPanel){
	
		this.cardLayout = cardLayout;
		this.container = container;
		this.progressPanel = progressPanel;
		 
		videoFileChooser = new JButton("Choose video");
		formatButton = new JButton("Format");
		mainMenuButton = new JButton("Main Menu");
		
		videoFormatChooser = new JComboBox<>(allFormats);
		videoFormatChooser.setSelectedIndex(0);
		videoFormatType = (String) videoFormatChooser.getSelectedItem();
		
		
		
		// button listener setup
		
		videoFileChooser.addActionListener(e -> {
		
			JFileChooser jFileChooser = new JFileChooser();
			int response = jFileChooser.showOpenDialog(this);
			if(response == JFileChooser.APPROVE_OPTION){
				videoFilePath = jFileChooser.getSelectedFile();
			}
			
		});
		
		
		formatButton.addActionListener(e -> {
		
			FormatRequest formatRequest = new FormatRequest();
			
			if(videoFilePath == null){
				JOptionPane.showMessageDialog(this, "Please select video first");
				return;
			}
			
			formatRequest.setVideoInputPath(videoFilePath.getAbsolutePath());
			formatRequest.setVideoFormatType(videoFormatType);
			
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
		setLayout(new FlowLayout());
		add(videoFileChooser);
		add(videoFormatChooser);
		add(formatButton);
		add(mainMenuButton);
		
	}

}
