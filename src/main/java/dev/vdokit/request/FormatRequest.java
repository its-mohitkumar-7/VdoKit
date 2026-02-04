package dev.vdokit.request;

import java.util.ArrayList;
import java.util.List;

import dev.vdokit.platform.PlatformDetector;

public class FormatRequest{
	
	private String videoFilePath;
	private String videoFormatType;
	
	public List<String> buildCommand(){
		
		List<String> cmd = new ArrayList<>();
		
		cmd.add(PlatformDetector.getBinary());
		cmd.add("-i");
		cmd.add(videoFilePath);
		cmd.add("VdoKit" + videoFilePath.substring(0, videoFilePath.lastIndexOf('.') + 1 ) + videoFormatType);
		
		return cmd;
	
	}
	
	
	
	//setters
	
	public void setVideoInputPath(String videoFilePath){
		this.videoFilePath = videoFilePath;
	}
	
	public void setVideoFormatType(String videoFormatType){
		this.videoFormatType = videoFormatType;
	}

}
