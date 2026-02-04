package dev.vdokit.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CaptionPanel extends JPanel{

	public JButton videoFileChooser;
	public JButton captionFileChooser;
	public JRadioButton softEmbedding;
	public JRadioButton hardEmbedding;
	public String[] allFormats = {"mkv(recommended)","mp4","m4v","mov","webm","avi","flv","wmv","asf","3gp","ts","mts","m2ts","mpeg","vob","ogv"};
	public JComboBox<String> outputVideoFormat;
	public JButton embedButton;
	public JButton mainMenuButton;
	
	public CaptionPanel(){
		
		
		videoFileChooser = new JButton("Choose Video");
		captionFileChooser = new JButton("Choose Caption");
		softEmbedding = new JRadioButton("Soft Embeding");
		hardEmbedding = new JRadioButton("Hard Embeding");
		ButtonGroup embeddingGroup = new ButtonGroup();
		embeddingGroup.add(softEmbedding);
		embeddingGroup.add(hardEmbedding);
		outputVideoFormat = new JComboBox<>(allFormats); 
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
		
		
		softEmbedding.addActionListener(e -> {

    		for (int i = outputVideoFormat.getItemCount() - 1; i >= 6; i--) {
        		outputVideoFormat.removeItemAt(i);
    		}

		});
		
		hardEmbedding.addActionListener(e -> {
		
			outputVideoFormat.removeAllItems();
			for(String format : allFormats){
				outputVideoFormat.addItem(format);
			}
				
		
		});

		
		
		// panel attributes
		setLayout(new FlowLayout());
		add(videoFileChooser);
		add(captionFileChooser);
		add(softEmbedding);
		add(hardEmbedding);
		add(outputVideoFormat);
		add(embedButton);
		add(mainMenuButton);
		
	}
}
