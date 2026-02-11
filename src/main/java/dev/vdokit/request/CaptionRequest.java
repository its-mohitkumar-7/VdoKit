package dev.vdokit.request;

import java.util.List;
import java.util.ArrayList;

import dev.vdokit.platform.PlatformDetector;

public class CaptionRequest{
	
	private String videoFilePath;
	private String captionFilePath;
	private boolean hardEmbeddingEnabled;
	private String outputVideoFormat;
	
	
	public List<String> buildCommand(){
	
		List<String> cmd = new ArrayList<String>();
		
		if(hardEmbeddingEnabled){
			cmd.add(PlatformDetector.getBinary());
			cmd.add("-y");
			cmd.add("-i");
			cmd.add(videoFilePath);
			cmd.add("-vf");
			cmd.add("subtitles='" + captionFilePath + "'");
			cmd.add(videoFilePath.substring(0, videoFilePath.lastIndexOf('/') + 1) + "VdoKit_" + videoFilePath.substring(videoFilePath.lastIndexOf('/') + 1, videoFilePath.lastIndexOf('.')) + "." + outputVideoFormat);

		}
		
		else if(outputVideoFormat.toLowerCase().contains("mkv")){
			cmd.add(PlatformDetector.getBinary());
			cmd.add("-y");
			cmd.add("-i");
			cmd.add(videoFilePath);
			cmd.add("-i");
			cmd.add(captionFilePath);
			cmd.add("-c");
			cmd.add("copy");
			cmd.add(videoFilePath.substring(0, videoFilePath.lastIndexOf('/') + 1) + "VdoKit_" + videoFilePath.substring(videoFilePath.lastIndexOf('/') + 1, videoFilePath.lastIndexOf('.')) + "." + outputVideoFormat);

		}
		
		else if(outputVideoFormat.toLowerCase().contains("webm")){
			cmd.add(PlatformDetector.getBinary());
			cmd.add("-y");
			cmd.add("-i");
			cmd.add(videoFilePath);
			cmd.add("-i");
			cmd.add(captionFilePath);
			cmd.add("-c:v");
			cmd.add("libvpx-vp9");
			cmd.add("-c:a");
			cmd.add("libopus");
			cmd.add("-c:s");
			cmd.add("webvtt");
			cmd.add(videoFilePath.substring(0, videoFilePath.lastIndexOf('/') + 1) + "VdoKit_" + videoFilePath.substring(videoFilePath.lastIndexOf('/') + 1, videoFilePath.lastIndexOf('.')) + "." + outputVideoFormat);

		}
		
		else {
			cmd.add(PlatformDetector.getBinary());
			cmd.add("-y");
			cmd.add("-i");
			cmd.add(videoFilePath);
			cmd.add("-i");
			cmd.add(captionFilePath);
			cmd.add("-c");
			cmd.add("copy");
			cmd.add("-c:s");
			cmd.add("mov_text");
			cmd.add(videoFilePath.substring(0, videoFilePath.lastIndexOf('/') + 1) + "VdoKit_" + videoFilePath.substring(videoFilePath.lastIndexOf('/') + 1, videoFilePath.lastIndexOf('.')) + "." + outputVideoFormat);

	}
	
	return cmd;
	
}

	
	
	// setter methods
	
	public void setVideoFilePath(String videoFilePath){
		this.videoFilePath = videoFilePath;
	}
	
	
	public void setCaptionFilePath(String captionFilePath){
		this.captionFilePath = captionFilePath;
	}
	
	public void setOutputVideoFormat(String outputVideoFormat){
		this.outputVideoFormat = outputVideoFormat;
	}
	
	
	public void setHardEmbeddingEnabled(boolean hardEmbeddingEnabled){
		this.hardEmbeddingEnabled = hardEmbeddingEnabled;
	}

}
