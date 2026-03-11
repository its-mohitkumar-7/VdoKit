package dev.vdokit.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	ImageIcon icon = new ImageIcon("src/resources/icons/logo.png");
	
	
	
	private CardLayout cardLayout;
	private JPanel container;
	
	private MainMenuPanel mainMenuPanel;
	private FormatPanel formatPanel;
	private CaptionPanel captionPanel;
	private ProgressPanel progressPanel;
	
	public MainFrame(){
	
	
		//cardlayout
		cardLayout = new CardLayout();
		container = new JPanel(cardLayout);
		container.setOpaque(false);
	
		//panels
		mainMenuPanel = new MainMenuPanel();
		progressPanel = new ProgressPanel();
		formatPanel = new FormatPanel(cardLayout, container, progressPanel);
		captionPanel = new CaptionPanel(cardLayout, container, progressPanel);
		
		
		//panel properties
		mainMenuPanel.setOpaque(false);
		progressPanel.setOpaque(false);
		formatPanel.setOpaque(false);
		captionPanel.setOpaque(false);
		
		container.add(mainMenuPanel,"MAINMENUPANEL");
		container.add(formatPanel,"FORMATPANEL");
		container.add(captionPanel,"CAPTIONPANEL");
		container.add(progressPanel, "PROGRESSPANEL");
		
		
		//button press logic
		mainMenuPanel.formatButton.addActionListener(e -> {
            cardLayout.show(container, "FORMATPANEL");
        });
        
        mainMenuPanel.captionButton.addActionListener(e -> {
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
        mainMenuPanel.exitButton.addActionListener(e -> {
        	System.exit(0);
        });
        
        JPanel watermarkPanel = new JPanel(new BorderLayout()) {

            private Image watermark = icon.getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));
                g2.drawImage(watermark, 0, 0, getWidth(), getHeight(), this);
                g2.dispose();
            }
            
        };

        watermarkPanel.add(container, BorderLayout.CENTER);
        
        //frame properties
		setTitle("VdoKit");
		setSize(800,600);
		setLayout(new BorderLayout());
		add(watermarkPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(icon.getImage());
		setVisible(true);
	}
}
