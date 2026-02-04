package dev.vdokit.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import dev.vdokit.request.FormatRequest;
import dev.vdokit.core.ProcessRunner;


public class FormatPanel extends JPanel {

	JButton videoFileChooser;
	JButton formatButton;
	JButton mainMenuButton;
	JComboBox<String> videoFormatChooser;
	String[] allFormats = {"mkv","mp4","m4v","mov","webm","avi","flv","wmv","asf","3gp","3g2","ts","m2ts","mts","mpeg","mpg","vob","ogv","f4v","rm","rmvb","divx","mxf","gxf","dv","nut","mjpeg","mjpg","y4m","gif"};

	
	File videoFilePath;
	String videoFormatType;
	
	public FormatPanel(){
		
		 
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
