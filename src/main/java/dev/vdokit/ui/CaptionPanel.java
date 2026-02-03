package dev.vdokit.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CaptionPanel extends JPanel{

	public JButton videoFileChooser;
	public JButton captionFileChooser;
	public JComboBox<String> captionEmbedSelector;
	public JButton embedButton;
	public JButton mainMenuButton;
	
	public CaptionPanel(){
		
		
		videoFileChooser = new JButton("Choose Video");
		captionFileChooser = new JButton("Choose Caption");
		captionEmbedSelector = new JComboBox<>(new String[] {"Soft Embedding","Hard Embedding"});
		embedButton = new JButton("Embed");
		mainMenuButton = new JButton("Main Menu");
		
		
		// button listener setup
		videoFileChooser.addActionListener(e -> {
			JFileChooser jFileChooser = new JFileChooser();
			int response = jFileChooser.showOpenDialog(this);
			if(response == JFileChooser.APPROVE_OPTION){
				File videoFilePath = jFileChooser.getSelectedFile();
				System.out.println(videoFilePath);
			}
		});
		
		captionFileChooser.addActionListener(e -> {
			JFileChooser jFileChooser = new JFileChooser();
			int response = jFileChooser.showOpenDialog(this);
			if(response == JFileChooser.APPROVE_OPTION){
				File captionFilePath = jFileChooser.getSelectedFile();
				System.out.println(captionFilePath);
			}
		});
		
		captionEmbedSelector.addActionListener(e -> {
			System.out.println(captionEmbedSelector.getSelectedItem());
		});
		
		
		// panel attributes
		setLayout(new FlowLayout());
		add(videoFileChooser);
		add(captionFileChooser);
		add(captionEmbedSelector);
		add(embedButton);
		add(mainMenuButton);
		
	}
}
