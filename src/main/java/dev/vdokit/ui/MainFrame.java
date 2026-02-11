package dev.vdokit.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	ImageIcon icon = new ImageIcon("src/resources/icons/logo.png");
	
	private CardLayout cardLayout;
	private JPanel container;
	
	private MainPanel mainPanel;
	private FormatPanel formatPanel;
	private CaptionPanel captionPanel;
	private ProgressPanel progressPanel;
	
	public MainFrame(){
	
		//cardlayout
		cardLayout = new CardLayout();
		container = new JPanel(cardLayout);
	
		//panels
		mainPanel = new MainPanel();
		progressPanel = new ProgressPanel();
		formatPanel = new FormatPanel(cardLayout, container, progressPanel);
		captionPanel = new CaptionPanel(cardLayout, container, progressPanel);
		
		container.add(mainPanel,"MAINPANEL");
		container.add(formatPanel,"FORMATPANEL");
		container.add(captionPanel,"CAPTIONPANEL");
		container.add(progressPanel, "PROGRESSPANEL");
		
		
		//button press logic
		mainPanel.formatButton.addActionListener(e -> {
            cardLayout.show(container, "FORMATPANEL");
        });
        
        mainPanel.captionButton.addActionListener(e -> {
        	cardLayout.show(container,"CAPTIONPANEL");
        });
        
        // main menu button
        
        formatPanel.mainMenuButton.addActionListener(e -> {
        	cardLayout.show(container,"MAINPANEL");
        });
        
        captionPanel.mainMenuButton.addActionListener(e -> {
        	cardLayout.show(container,"MAINPANEL");
        });
        
        // exit button setup
        mainPanel.exitButton.addActionListener(e -> {
        	System.exit(0);
        });
        
        //frame properties
		setTitle("VdoKit");
		setSize(800,600);
		setLayout(new FlowLayout());
		add(container);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(icon.getImage());
		setVisible(true);
	}
}
