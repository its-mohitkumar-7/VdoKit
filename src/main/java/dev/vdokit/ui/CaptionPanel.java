package dev.vdokit.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

import dev.vdokit.request.CaptionRequest;
import dev.vdokit.core.ProcessRunner;


public class CaptionPanel extends JPanel{


	private JButton videoFileChooser;
	private JButton captionFileChooser;
	private JButton embedButton;
	public JButton mainMenuButton;
	
	private JRadioButton softEmbedding;
	private JRadioButton hardEmbedding;
	
	private JCheckBox resizeOutputVideoCheckbox;
	
	private JComboBox<String> outputVideoFormatChooser;
	private JComboBox<String> resizeOutputVideoChooser;
	
	private JLabel embedTypeLabel;
	private JLabel videoFormatLabel;
	
	private JTextField videoLocationLabel;
	private JTextField captionLocationLabel;
	
	private CardLayout cardLayout;
	private JPanel container;
	private ProgressPanel progressPanel;
	
	private FileNameExtensionFilter typeVideo;
	private FileNameExtensionFilter typeCaption;
	
	private String[] allCaptionFormats = {"srt","vtt","ass","ssa","sub","idx","sbv","ttml","dfxp","xml","smi","rt"};
	private String[] allFormats = {"mkv","mp4","m4v","mov","webm","avi","flv","wmv","asf","3gp","ts","mts","m2ts","mpeg","vob","ogv"};
	private String[] allResolutions = {"256:144","426:240","640:360","854:480","960:540","1024:576","1280:720","1366:768","1600:900","1920:1080","2048:1080","2560:1440","3200:1800","3840:2160","4096:2160","5120:2880","7680:4320"};
	
	// choice variables
	private File videoFilePath;
	private File captionFilePath;
	private boolean hardEmbeddingEnabled;
	private String outputVideoFormat;
	private String resizeOutputVideo;	
	
	
	public CaptionPanel(CardLayout cardLayout, JPanel container, ProgressPanel progressPanel){
	
		setLayout(new BorderLayout());
			
		this.cardLayout = cardLayout;
		this.container = container;
		this.progressPanel = progressPanel;
		
		
		//-------------------Header Panel--------------------//
		
		
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new GridBagLayout());
		headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
		headerPanel.setBackground(new Color(181, 181, 181));
		add(headerPanel, BorderLayout.NORTH);
		
		GridBagConstraints GBCH = new GridBagConstraints();
		
		GBCH.anchor = GridBagConstraints.NORTHWEST;
		GBCH.gridx = 0;
		GBCH.weightx = 1.0;
		
		mainMenuButton = new JButton("☰ Main Menu");
		mainMenuButton.setFocusPainted(false);
		mainMenuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mainMenuButton.setForeground(new Color(230, 230, 235));
		mainMenuButton.setBackground(new Color(115, 114, 114));
		mainMenuButton.setFont(new Font("Roboto", Font.BOLD, 15));
		mainMenuButton.setPreferredSize(new Dimension(140, 42));
		headerPanel.add(mainMenuButton, GBCH);
		
		GBCH.gridx = 1;
		GBCH.weightx = 1.0;
		GBCH.weighty = 0;
		GBCH.anchor = GridBagConstraints.NORTHWEST;
		
		JLabel panelTittle = new JLabel("Caption");
		panelTittle.setFont(new Font("Roboto", Font.BOLD, 80));
		panelTittle.setForeground(new Color(35, 53, 79, 220));
		headerPanel.add(panelTittle, GBCH);
		
		
		//------------------Center Panel---------------------//
		
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 350, 40, 350));
		centerPanel.setBackground(new Color(230, 230, 230));
		add(centerPanel, BorderLayout.CENTER);
		
		GridBagConstraints GBCC = new GridBagConstraints();
		
		GBCC.gridx = 0;
		GBCC.gridy = 0;
		GBCC.anchor = GridBagConstraints.WEST;
		GBCC.insets = new Insets(16, 10, 16, 10);
		
		videoFileChooser = new JButton("Choose Video");
		videoFileChooser.setForeground(new Color(230, 230, 235));
		videoFileChooser.setBackground(new Color(143, 164, 191));
		videoFileChooser.setFont(new Font("Roboto", Font.BOLD, 20));
		videoFileChooser.setFocusable(false);
		videoFileChooser.setPreferredSize(new Dimension(215, 42));
		centerPanel.add(videoFileChooser, GBCC);
		
		GBCC.gridx = 1;
		GBCC.weightx = 1.0;
		GBCC.fill = GridBagConstraints.HORIZONTAL;
		
		videoLocationLabel = new JTextField(28);
		videoLocationLabel.setEditable(false);
		videoLocationLabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
		videoLocationLabel.setPreferredSize(new Dimension(0, 40));
		centerPanel.add(videoLocationLabel,GBCC);
		
		GBCC.gridx = 0;
		GBCC.gridy = 1;
		GBCC.weightx = 0;
		GBCC.fill = GridBagConstraints.NONE;
		
		captionFileChooser = new JButton("Choose Caption");
		captionFileChooser.setForeground(new Color(230, 230, 235));
		captionFileChooser.setBackground(new Color(143, 164, 191));
		captionFileChooser.setFont(new Font("Roboto", Font.BOLD, 20));
		captionFileChooser.setFocusable(false);
		captionFileChooser.setPreferredSize(new Dimension(215, 42));
		centerPanel.add(captionFileChooser, GBCC);
		
		GBCC.gridx = 1;
		GBCC.weightx = 1.0;
		GBCC.fill = GridBagConstraints.HORIZONTAL;
		
		captionLocationLabel = new JTextField(28);
		captionLocationLabel.setEditable(false);
		captionLocationLabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
		captionLocationLabel.setPreferredSize(new Dimension(0, 40));
		centerPanel.add(captionLocationLabel,GBCC);
		
		GBCC.gridx = 0;
		GBCC.gridy = 2;
		GBCC.weightx = 0;
		GBCC.fill = GridBagConstraints.NONE;
		
		embedTypeLabel = new JLabel("Select Embedding Mode: ");
		embedTypeLabel.setFont(new Font("Roboto", Font.BOLD, 20));
		centerPanel.add(embedTypeLabel, GBCC);
		
		GBCC.gridx = 1;
		
		softEmbedding = new JRadioButton("Soft Embeding");
		softEmbedding.setForeground(Color.BLACK);
		softEmbedding.setBackground(new Color(230, 230, 230));
		softEmbedding.setFont(new Font("Roboto", Font.BOLD, 20));
		centerPanel.add(softEmbedding, GBCC);
		
		GBCC.gridx = 1;
		GBCC.insets = new Insets(16, 250, 16, 10);
		
		hardEmbedding = new JRadioButton("Hard Embeding");
		hardEmbedding.setForeground(Color.BLACK);
		hardEmbedding.setBackground(new Color(230, 230, 230));
		hardEmbedding.setFont(new Font("Roboto", Font.BOLD, 20));
		centerPanel.add(hardEmbedding, GBCC);
		
		ButtonGroup embeddingGroup = new ButtonGroup();
		embeddingGroup.add(softEmbedding);
		embeddingGroup.add(hardEmbedding);
		softEmbedding.setSelected(true);
		
		GBCC.gridx = 0;
		GBCC.gridy = 3;
		GBCC.insets = new Insets(16, 10, 16, 10);
		
		resizeOutputVideoCheckbox = new JCheckBox("Scale: ");
		resizeOutputVideoCheckbox.setHorizontalTextPosition(JCheckBox.LEADING);
		resizeOutputVideoCheckbox.setForeground(Color.BLACK);
		resizeOutputVideoCheckbox.setBackground(new Color(230, 230, 230));
		resizeOutputVideoCheckbox.setFocusable(false);
		resizeOutputVideoCheckbox.setFont(new Font("Roboto", Font.BOLD, 20));
		centerPanel.add(resizeOutputVideoCheckbox, GBCC);
		
		GBCC.gridx = 1;
		
		resizeOutputVideoChooser = new JComboBox<>(allResolutions);
		resizeOutputVideoChooser.setSelectedIndex(0);
		resizeOutputVideoChooser.setVisible(false);
		resizeOutputVideoChooser.setForeground(new Color(230, 230, 235));
		resizeOutputVideoChooser.setBackground(new Color(143, 164, 191));
		resizeOutputVideoChooser.setFont(new Font("Roboto", Font.BOLD, 20));
		resizeOutputVideoChooser.setPreferredSize(new Dimension(150, 36));
		centerPanel.add(resizeOutputVideoChooser, GBCC);
		
		GBCC.gridx = 0;
		GBCC.gridy = 4;
		
		videoFormatLabel = new JLabel("Output Format: ");
		videoFormatLabel.setLabelFor(resizeOutputVideoChooser);
		videoFormatLabel.setFont(new Font("Roboto", Font.BOLD, 20));
		centerPanel.add(videoFormatLabel, GBCC);
		
		GBCC.gridx = 1;
		
		outputVideoFormatChooser = new JComboBox<>(allFormats);
		outputVideoFormatChooser.setSelectedIndex(0);
		outputVideoFormatChooser.setForeground(new Color(230, 230, 235));
		outputVideoFormatChooser.setBackground(new Color(143, 164, 191));
		outputVideoFormatChooser.setFont(new Font("Roboto", Font.BOLD, 20));
		outputVideoFormatChooser.setPreferredSize(new Dimension(150, 36));
		centerPanel.add(outputVideoFormatChooser, GBCC);
		
		GBCC.gridx = 1;
		GBCC.gridy = 5;
		GBCC.anchor = GridBagConstraints.CENTER;
		GBCC.insets = new Insets(30, 10, 16, 100);
		
		embedButton = new JButton("Embed");
		embedButton.setForeground(new Color(230, 230, 235));
		embedButton.setBackground(new Color(109, 124, 143));
		embedButton.setFont(new Font("Roboto", Font.BOLD, 40));
		embedButton.setFocusable(false);
		embedButton.setPreferredSize(new Dimension(300, 70));
		centerPanel.add(embedButton, GBCC);
		
		typeVideo = new FileNameExtensionFilter("video type file filter", allFormats);
		typeCaption = new FileNameExtensionFilter("caption type file filter", allCaptionFormats);
		
		// button listener setup
		videoFileChooser.addActionListener(e -> {
		
			JFileChooser jFileChooser = new JFileChooser();
			jFileChooser.setFileFilter(typeVideo);
			int response = jFileChooser.showOpenDialog(this);
			if(response == JFileChooser.APPROVE_OPTION){
				videoFilePath = jFileChooser.getSelectedFile();
				videoLocationLabel.setText(videoFilePath.getAbsolutePath());
			}
			
		});
		
		captionFileChooser.addActionListener(e -> {
		
			JFileChooser jFileChooser = new JFileChooser();
			jFileChooser.setFileFilter(typeCaption);
			int response = jFileChooser.showOpenDialog(this);
			if(response == JFileChooser.APPROVE_OPTION){
				captionFilePath = jFileChooser.getSelectedFile();
				captionLocationLabel.setText(captionFilePath.getAbsolutePath());	
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
		
		resizeOutputVideoCheckbox.addItemListener(e -> {
			
			resizeOutputVideoChooser.setVisible(resizeOutputVideoCheckbox.isSelected());
    		revalidate();
    		repaint();
			
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
			outputVideoFormat = (String) outputVideoFormatChooser.getSelectedItem();
			captionRequest.setOutputVideoFormat(outputVideoFormat);
			
			if (resizeOutputVideoCheckbox.isSelected()){
    			resizeOutputVideo = (String) resizeOutputVideoChooser.getSelectedItem();
			} else {
    			resizeOutputVideo = null;
			}
			
			captionRequest.setResizeOutputVideo(resizeOutputVideo);
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
                        
                        videoFilePath = null;
                        captionFilePath = null;
                        videoLocationLabel.setText("");
                        captionLocationLabel.setText("");
                        resizeOutputVideoCheckbox.setSelected(false);
                        resizeOutputVideoChooser.setSelectedIndex(0);
    					resizeOutputVideoChooser.setVisible(false);
                        
                        JOptionPane.showMessageDialog(CaptionPanel.this, "Embedding completed!");
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        JOptionPane.showMessageDialog(CaptionPanel.this, "Error: " + exception.getMessage());
                    }
                }
            };
            swingWorker.execute();
        });
        
        
        //------------------Footer Panel---------------------//
		
		
		JPanel footerPanel = new JPanel();
		footerPanel.setBackground(new Color(181, 181, 181));
		add(footerPanel, BorderLayout.SOUTH);
		
		JLabel footerLabel = new JLabel("Powered By FFMPEG");
		footerLabel.setFont(new Font("Roboto", Font.BOLD, 12));
		footerPanel.add(footerLabel);
		
		
	}
}
