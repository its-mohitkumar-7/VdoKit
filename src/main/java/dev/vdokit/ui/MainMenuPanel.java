package dev.vdokit.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

public class MainMenuPanel extends JPanel {
	
	ImageIcon formatButtonIcon;
	ImageIcon captionButtonIcon;
	ImageIcon exitButtonIcon;
	ImageIcon appTittleIcon;
	
	JButton formatButton;
	JButton captionButton;
	JButton exitButton;
	
	
	public MainMenuPanel(){
	
		setLayout(new BorderLayout());
		
		
		//----------------Header Panel--------------------//
		
		
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new BorderLayout());
		headerPanel.setBackground(new Color(225, 225, 225));
		headerPanel.setOpaque(false);
		
		
		appTittleIcon = new ImageIcon("src/resources/icons/tittleIcon.png");
		JLabel appTittle = new JLabel(appTittleIcon);
		headerPanel.add(appTittle);
		
		
		add(headerPanel, BorderLayout.NORTH);
	
	
		//----------------Center Panel-----------------//
		
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		centerPanel.setOpaque(false);
		centerPanel.setBackground(new Color(225, 225, 225));
		GridBagConstraints GBC = new GridBagConstraints();
		GBC.insets = new Insets(10,10,10,10);
		
		
		//Format button setup
		formatButton = new JButton("Format");
		formatButtonIcon = new ImageIcon("src/resources/icons/formatButtonIcon.png");
		formatButton.setIcon(formatButtonIcon);
		formatButton.setHorizontalTextPosition(JButton.CENTER);
		formatButton.setVerticalTextPosition(JButton.BOTTOM);
		formatButton.setFont(new Font("Roboto",Font.BOLD,70));
		formatButton.setForeground(new Color(230, 230, 235));
		formatButton.setBackground(new Color(143, 164, 191));
		formatButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		formatButton.setPreferredSize(new Dimension(350,500));
		
		
		//Caption button setup
		captionButton = new JButton("Caption");
		captionButtonIcon = new ImageIcon("src/resources/icons/captionButtonIcon.png");
		captionButton.setIcon(captionButtonIcon);
		captionButton.setHorizontalTextPosition(JButton.CENTER);
		captionButton.setVerticalTextPosition(JButton.BOTTOM);
		captionButton.setFont(new Font("Inter",Font.BOLD,70));
		captionButton.setForeground(new Color(230, 230, 235));
		captionButton.setBackground(new Color(143, 164, 191));
		captionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		captionButton.setPreferredSize(new Dimension(350,500));


		//exit button setup
		exitButton = new JButton("Exit");
		exitButtonIcon = new ImageIcon("src/resources/icons/exitButtonIcon.gif");
		exitButton.setIcon(exitButtonIcon);
		exitButton.setHorizontalTextPosition(JButton.CENTER);
		exitButton.setVerticalTextPosition(JButton.BOTTOM);
		exitButton.setFont(new Font("Roboto",Font.BOLD,70));
		exitButton.setForeground(new Color(230, 230, 235));
		exitButton.setBackground(new Color(143, 164, 191));
		exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		exitButton.setPreferredSize(new Dimension(350,500));
	
	
		centerPanel.add(formatButton,GBC);
		centerPanel.add(captionButton,GBC);
		centerPanel.add(exitButton,GBC);
		add(centerPanel, BorderLayout.CENTER);
		
		
		//-------------------Footer Panel-------------------//
		
		
		JPanel footerPanel = new JPanel();
		footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));
		footerPanel.setOpaque(false);
		footerPanel.setBackground(new Color(225, 225, 225));
		footerPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		
		
		JLabel developerLabel = new JLabel("© 2026 its-mohitkumar-7 | Version 0.1.0");
		developerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		JLabel sourceCodeLabel = new JLabel("view source code on Github");
		sourceCodeLabel.setForeground(Color.BLUE);
		sourceCodeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		sourceCodeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		sourceCodeLabel.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent e){
				try{
					Desktop.getDesktop().browse(new URI("https://github.com/its-mohitkumar-7/VdoKit"));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
		});
		
		
		footerPanel.add(developerLabel);
		footerPanel.add(sourceCodeLabel);
		add(footerPanel, BorderLayout.SOUTH);
		
		
	}
	
}
