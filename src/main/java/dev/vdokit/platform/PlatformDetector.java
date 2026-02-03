package dev.vdokit.platform;

public class PlatformDetector{
	
	public static String getBinary(){
	
		if(System.getProperty("os.name").toLowerCase().contains("linux"))
			return "src/resources/ffmpeg/linux/ffmpeg";
			
		else 
			return "src/resources/ffmpeg/windows/ffmpeg.exe";
		
	}	
}
