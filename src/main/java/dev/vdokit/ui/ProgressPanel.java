package dev.vdokit.ui;

import javax.swing.*;
import java.awt.*;


public class ProgressPanel extends JPanel {

	
	private JProgressBar progressBar;
	private JLabel progressBarLabel;
	
	
	public ProgressPanel(){
	
		
		setLayout(new GridBagLayout());
		setVisible(false);
		setBackground(new Color(181, 181, 181));
		
		GridBagConstraints GBC = new GridBagConstraints();
		
		GBC.weightx = 1.0;
		GBC.weighty = 1.0;
		GBC.anchor = GridBagConstraints.CENTER;
		GBC.fill = GridBagConstraints.NONE;
		
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(false);
		progressBar.setBackground(new Color(115, 114, 114));
		progressBar.setForeground(new Color(230, 230, 235));
		progressBar.setPreferredSize(new Dimension(900, 50));
		add(progressBar, GBC);
		
		progressBarLabel = new JLabel("Processing...");
		add(progressBarLabel);
	
	}
	
	public void showProgress(String message){
	
		progressBarLabel.setText(message);
		progressBar.setIndeterminate(true);
		progressBar.setVisible(true);
			
	}
	
	
	public void hideProgress(){
		
		progressBar.setIndeterminate(false);
		progressBar.setVisible(false);
			
	}

}
