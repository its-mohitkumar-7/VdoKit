package dev.vdokit.request;

import java.util.List;
import java.util.ArrayList;

import dev.vdokit.platform.PlatformDetector;

public class CaptionRequest{
	
	private String videoFilePath;
	private String captionFilePath;
	private boolean hardEmbeddingEnabled;
	private String resizeOutputVideo;
	private String outputVideoFormat;
	
	
	public List<String> buildCommand(){
	
		List<String> cmd = new ArrayList<String>();
		
		String outputVideoPath = videoFilePath.substring(0, videoFilePath.lastIndexOf('/') + 1) + "VdoKit_" + videoFilePath.substring(videoFilePath.lastIndexOf('/') + 1, videoFilePath.lastIndexOf('.')) + "." + outputVideoFormat;
		
		if(hardEmbeddingEnabled){
			cmd.add(PlatformDetector.getBinary());
			cmd.add("-y");
			cmd.add("-i");
			cmd.add(videoFilePath);
			String filter = "subtitles='" + captionFilePath + "'";
			if(resizeOutputVideo != null){
				filter += ",scale=" + resizeOutputVideo;
			}
			cmd.add("-vf");
			cmd.add(filter);
			cmd.add("-c:v");
			cmd.add("libx264");
			cmd.add("-c:a");
			cmd.add("copy");
			cmd.add(outputVideoPath);
			return cmd;

		}
		
		else if(outputVideoFormat.toLowerCase().contains("mkv")){
			cmd.add(PlatformDetector.getBinary());
			cmd.add("-y");
			cmd.add("-i");
			cmd.add(videoFilePath);
			cmd.add("-i");
			cmd.add(captionFilePath);
			if(resizeOutputVideo != null){
				cmd.add("-vf");
				cmd.add("scale=" + resizeOutputVideo);
				cmd.add("-c:v");
				cmd.add("libx264");
				cmd.add("-c:a");
				cmd.add("copy");
			} else {
			cmd.add("-c");
			cmd.add("copy");
			}
			cmd.add(outputVideoPath);

		}
		
		else if(outputVideoFormat.toLowerCase().contains("webm")){
			cmd.add(PlatformDetector.getBinary());
			cmd.add("-y");
			cmd.add("-i");
			cmd.add(videoFilePath);
			cmd.add("-i");
			cmd.add(captionFilePath);
			if(resizeOutputVideo != null){
				cmd.add("-vf");
				cmd.add("scale=" + resizeOutputVideo);
			}
			cmd.add("-c:v");
			cmd.add("libvpx-vp9");
			cmd.add("-c:a");
			cmd.add("libopus");
			cmd.add("-c:s");
			cmd.add("webvtt");
			cmd.add(outputVideoPath);

		}
		
		else {
			cmd.add(PlatformDetector.getBinary());
			cmd.add("-y");
			cmd.add("-i");
			cmd.add(videoFilePath);
			cmd.add("-i");
			cmd.add(captionFilePath);
			if(resizeOutputVideo != null){
				cmd.add("-vf");
				cmd.add("scale=" + resizeOutputVideo);
				cmd.add("-c:v");
				cmd.add("libx264");
				cmd.add("-c:a");
				cmd.add("copy");
				cmd.add("-c:s");
				cmd.add("mov_text");
			} else {
				cmd.add("-c");
				cmd.add("copy");
				cmd.add("-c:s");
				cmd.add("mov_text");
			}
			cmd.add(outputVideoPath);

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
	
	public void setResizeOutputVideo(String resizeOutputVideo){
		this.resizeOutputVideo = resizeOutputVideo;
	}

}
