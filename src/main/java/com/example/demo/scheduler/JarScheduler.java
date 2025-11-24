package com.example.demo.scheduler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JarScheduler {

	@Scheduled(cron = "0 */1 * * * *")
	public void runExternalJar() {

		try {
			log.info("Starting external JAR at: {}", java.time.LocalDateTime.now());

			String jarPath = "C:\\Users\\Yamini Garudachalam\\Documents\\workspace-spring-tool-suite-4-4.28.0.RELEASE\\LOCAL_SpringBatch\\ExternalJarForActivity\\target\\externalJarForActivity-0.1-SNAPSHOT.jar";

			ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", jarPath,
					"--csv_file_name=large_sample_data_1000_rows.csv");

			processBuilder.redirectErrorStream(true);
			Process process = processBuilder.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				log.info("External JAR: {}", line);
			}

			int exitCode = process.waitFor();
			log.info("External jar completed with exit code: {}", exitCode);

		} catch (Exception e) {
			log.error("Error while running external jar", e);
		}
	}
}
