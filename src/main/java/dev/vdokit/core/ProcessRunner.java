package dev.vdokit.core;

import java.util.List;

public class ProcessRunner {
	
	public static void run(List<String> command) throws Exception {
	
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		processBuilder.inheritIO();
		Process process = processBuilder.start();
		process.waitFor();
		
	}
	
	
}
