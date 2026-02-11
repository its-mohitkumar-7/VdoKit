 package dev.vdokit.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import dev.vdokit.request.CaptionRequest;
import dev.vdokit.core.ProcessRunner;

public class CaptionPanel extends JPanel{

	private JButton videoFileChooser;
	private JButton captionFileChooser;
	private JButton embedButton;
	public JButton mainMenuButton;
	
	private JRadioButton softEmbedding;
	private JRadioButton hardEmbedding;
	
	private String[] allFormats = {"mkv","mp4","m4v","mov","webm","avi","flv","wmv","asf","3gp","ts","mts","m2ts","mpeg","vob","ogv"};
	
	private JComboBox<String> outputVideoFormatChooser;
	
	private CardLayout cardLayout;
	private JPanel container;
	private ProgressPanel progressPanel;
	
	// choice variables
	private File videoFilePath;
	private File captionFilePath;
	private boolean hardEmbeddingEnabled;
	private String outputVideoFormat;
	
	
	public CaptionPanel(CardLayout cardLayout, JPanel container, ProgressPanel progressPanel){
	
		this.cardLayout = cardLayout;
		this.container = container;
		this.progressPanel = progressPanel;
		
		videoFileChooser = new JButton("Choose Video");
		captionFileChooser = new JButton("Choose Caption");
		embedButton = new JButton("Embed");
		mainMenuButton = new JButton("Main Menu");
		
		softEmbedding = new JRadioButton("Soft Embeding");
		hardEmbedding = new JRadioButton("Hard Embeding");
		ButtonGroup embeddingGroup = new ButtonGroup();
		embeddingGroup.add(softEmbedding);
		embeddingGroup.add(hardEmbedding);
		softEmbedding.setSelected(true);
		
		outputVideoFormatChooser = new JComboBox<>(allFormats);
		outputVideoFormatChooser.setSelectedIndex(0);
		outputVideoFormat = (String) outputVideoFormatChooser.getSelectedItem();
 
		
		
		// button listener setup
		videoFileChooser.addActionListener(e -> {
		
			JFileChooser jFileChooser = new JFileChooser();
			int response = jFileChooser.showOpenDialog(this);
			if(response == JFileChooser.APPROVE_OPTION){
				videoFilePath = jFileChooser.getSelectedFile();

			}
			
		});
		
		
		captionFileChooser.addActionListener(e -> {
		
			JFileChooser jFileChooser = new JFileChooser();
			int response = jFileChooser.showOpenDialog(this);
			if(response == JFileChooser.APPROVE_OPTION){
				captionFilePath = jFileChooser.getSelectedFile();
				
				
			}
			
		});
		
		
		softEmbedding.addActionListener(e -> {

    		for (int i = outputVideoFormatChooser.getItemCount() - 1; i >= 6; i--) {
        		outputVideoFormatChooser.removeItemAt(i);
    		}
    		hardEmbeddingEnabled = false;

		});
		
		
		hardEmbedding.addActionListener(e -> {
		
			outputVideoFormatChooser.removeAllItems();
			for(String format : allFormats){
				outputVideoFormatChooser.addItem(format);
			}
			hardEmbeddingEnabled = true;
				
		});
		
		
		outputVideoFormatChooser.addActionListener(e -> {
		
    		outputVideoFormat = (String) outputVideoFormatChooser.getSelectedItem();
    		
		});

		
		embedButton.addActionListener(e -> {
		
			CaptionRequest captionRequest = new CaptionRequest();
			
			if(videoFilePath == null){
				JOptionPane.showMessageDialog(this, "Please select video first");
				return;
			}
			if(captionFilePath == null){
				JOptionPane.showMessageDialog(this, "Please select caption file first");
				return;
			}
			
			captionRequest.setVideoFilePath(videoFilePath.getAbsolutePath());
			captionRequest.setCaptionFilePath(captionFilePath.getAbsolutePath());
			captionRequest.setHardEmbeddingEnabled(hardEmbeddingEnabled);
			captionRequest.setOutputVideoFormat(outputVideoFormat);
			
			progressPanel.showProgress("Embedding Captions...");
			cardLayout.show(container, "PROGRESSPANEL");
			
			SwingWorker<Void, Void> swingWorker = new SwingWorker<>() {
			
				@Override
                protected Void doInBackground() throws Exception {
                    ProcessRunner.run(captionRequest.buildCommand());
                    return null;
                }
                
                @Override
                protected void done() {
                    progressPanel.hideProgress();
                    cardLayout.show(container, "CAPTIONPANEL");
                    try {
                        get();
                        JOptionPane.showMessageDialog(CaptionPanel.this, "Embedding completed!");
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        JOptionPane.showMessageDialog(CaptionPanel.this, "Error: " + exception.getMessage());
                    }
                }
            };
            swingWorker.execute();
        });

		
		
		// panel attributes
		setLayout(new FlowLayout());
		add(videoFileChooser);
		add(captionFileChooser);
		add(softEmbedding);
		add(hardEmbedding);
		add(outputVideoFormatChooser);
		add(embedButton);
		add(mainMenuButton);
		
	}
}
