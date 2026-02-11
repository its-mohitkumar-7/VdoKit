package dev.vdokit.ui;

import javax.swing.*;
import java.awt.*;

public class ProgressPanel extends JPanel {
	
	private JProgressBar progressBar;
	private JLabel progressBarLabel;
	
	public ProgressPanel(){
		
		progressBar = new JProgressBar();
		progressBarLabel = new JLabel("Processing...");
		
		progressBar.setIndeterminate(false);
		add(progressBar);
		add(progressBarLabel);
		setVisible(false);	
		
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
