package dev.vdokit.ui;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
	
	ImageIcon formatButtonIcon;
	ImageIcon captionButtonIcon;
	
	JButton formatButton;
	JButton captionButton;
	JButton exitButton;
	
	
	public MainPanel(){
		//layout
		setLayout(new FlowLayout());
		
		//Format button setup
		formatButton = new JButton("Format");
		formatButtonIcon = new ImageIcon("src/resources/icons/formatButtonIcon.png");
		formatButton.setFocusable(false);
		formatButton.setIcon(formatButtonIcon);
		formatButton.setHorizontalTextPosition(JButton.CENTER);
		formatButton.setVerticalTextPosition(JButton.BOTTOM);
		
		//Caption button setup
		captionButton = new JButton("Caption");
		captionButtonIcon = new ImageIcon("src/resources/icons/captionButtonIcon.png");
		captionButton.setFocusable(false);
		captionButton.setIcon(captionButtonIcon);
		captionButton.setHorizontalTextPosition(JButton.CENTER);
		captionButton.setVerticalTextPosition(JButton.BOTTOM);
		
		//exit button setup
		exitButton = new JButton("Exit");
		exitButton.setFocusable(false);
		
		//add to panel
		add(formatButton);
		add(captionButton);
		add(exitButton);
		
	}
	
}
