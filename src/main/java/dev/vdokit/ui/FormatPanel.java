package dev.vdokit.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import dev.vdokit.platform.PlatformDetector;


public class FormatPanel extends JPanel {

	JButton videoFileChooser;
	JComboBox<String> videoFormatChooser;
	JButton formatButton;
	JButton mainMenuButton;
	
	public FormatPanel(){
		
		 
		videoFileChooser = new JButton("Choose video");
		videoFormatChooser = new JComboBox<>(new String[] {"mp4","avi","mov"});
		formatButton = new JButton("Format");
		mainMenuButton = new JButton("Main Menu");	
		
		
		// button listener setup
		videoFileChooser.addActionListener(e -> {
			JFileChooser jFileChooser = new JFileChooser();
			int response = jFileChooser.showOpenDialog(this);
			if(response == JFileChooser.APPROVE_OPTION){
				File videoFilePath = jFileChooser.getSelectedFile();
			} else System.out.println("Please Select a video file");// else case
		});
		
		videoFormatChooser.addActionListener(e -> {
			System.out.println(videoFormatChooser.getSelectedItem());// jcombobox
		});
		
		formatButton.addActionListener(e -> {
			System.out.println(PlatformDetector.getBinary());
		});
		
		// panel attributes
		setLayout(new FlowLayout());
		add(videoFileChooser);
		add(videoFormatChooser);
		add(formatButton);
		add(mainMenuButton);
		
	}

}
