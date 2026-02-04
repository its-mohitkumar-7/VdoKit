package dev.vdokit.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import dev.vdokit.request.FormatRequest;
import dev.vdokit.core.ProcessRunner;


public class FormatPanel extends JPanel {

	JButton videoFileChooser;
	JComboBox<String> videoFormatChooser;
	JButton formatButton;
	JButton mainMenuButton;
	
	File videoFilePath;
	String videoFormatType;
	
	public FormatPanel(){
		
		 
		videoFileChooser = new JButton("Choose video");
		formatButton = new JButton("Format");
		mainMenuButton = new JButton("Main Menu");
		
		videoFormatChooser = new JComboBox<>(new String[] {"mp4","avi","mov"});
		videoFormatChooser.setSelectedIndex(0);
		videoFormatType = (String) videoFormatChooser.getSelectedItem();
		
		
		
		// button listener setup
		videoFileChooser.addActionListener(e -> {
			JFileChooser jFileChooser = new JFileChooser();
			int response = jFileChooser.showOpenDialog(this);
			if(response == JFileChooser.APPROVE_OPTION){
				videoFilePath = jFileChooser.getSelectedFile();
			} else System.out.println("Please Select a video file");// else case
		});
		
		
		formatButton.addActionListener(e -> {
		
			FormatRequest formatRequest = new FormatRequest();
			
			formatRequest.setVideoInputPath(videoFilePath.getAbsolutePath());
			formatRequest.setVideoFormatType(videoFormatType);
			
			try{
				ProcessRunner.run(formatRequest.buildCommand());
			} catch(Exception exception){
				exception.printStackTrace();
			}	
			
				
		});
		
		// panel attributes
		setLayout(new FlowLayout());
		add(videoFileChooser);
		add(videoFormatChooser);
		add(formatButton);
		add(mainMenuButton);
		
	}

}
