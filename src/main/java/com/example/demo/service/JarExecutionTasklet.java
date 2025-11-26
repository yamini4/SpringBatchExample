package com.example.demo.service;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class JarExecutionTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		String jarPath = "C:\\Users\\Yamini Garudachalam\\Documents\\workspace-spring-tool-suite-4-4.28.0.RELEASE\\LOCAL_SpringBatch\\ExternalJarForActivity\\target\\externalJarForActivity-0.1-SNAPSHOT.jar";
		String argument = "--csv_file_name=large_sample_data_1000_rows.csv";

		ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", jarPath, argument);
		processBuilder.inheritIO();
		Process process = processBuilder.start();

		int exitCode = process.waitFor();
		System.out.println("JAR finished with status: " + exitCode);

		return RepeatStatus.FINISHED;
	}
}
