package com.github.eostermueller.proc;

import java.io.IOException;
import java.io.File;
import java.lang.ProcessBuilder.Redirect;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {

        ProcessBuilder processBuilder = new ProcessBuilder();
		File currentDir = new File(".");
		System.out.println("Currnet dir [" + currentDir.getAbsolutePath() + "]");
		File baseDir = new File(  "../SpringBootExample");
		File springBootPom = new File( baseDir, "pom.xml");
		StringBuilder sb = new StringBuilder();
		sb.append("Found the pom? ");
		
		if (springBootPom.exists() )
			sb.append("yes!\n");
		else 
			sb.append("no :-(\n");
		
		System.out.println( sb.toString() );
		
        processBuilder.directory( baseDir );
		
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("mac") ) {
			processBuilder.command("mvn", "-X", "-e" , "-Dspring-boot.run.jvmArguments=-XX:MaxNewSize=512m -Xmx1g" , "spring-boot:run");			
		} else if (osName.contains("linux") ) {
			System.out.println("Don't do linux yet");
			System.exit(-1);
		} else if (osName.contains("win") ) {
			String winCommand = "mvn -e -X -Dspring-boot.run.fork=true -Dspring-boot.run.jvmArguments=\"-XX:MaxNewSize=512m -Xmx1g\" spring-boot:run";
			System.out.println("About to execute [" + winCommand + "]");
			processBuilder.command("cmd.exe", "/C", winCommand);
		} else {
			System.out.println("Don't do [" + osName + "] yet.");
			System.exit(-1);
		}

		File outputLog = new File(baseDir,"standard-output.log");
		File errorLog =  new File(baseDir,"error.log");
	 
		processBuilder.redirectOutput(Redirect.appendTo(outputLog));
		processBuilder.redirectError(Redirect.appendTo(errorLog));

        Process process = processBuilder.start();

        int ret = process.waitFor();

        System.out.printf("Program exited with code: %d", ret);
    }
}
