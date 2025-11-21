package com.example.demo.scheduler;

import java.time.LocalDateTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomerJobScheduler {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job runJob;

	@Scheduled(cron = "0 */1 * * * *")
	public void runJobScheduler() throws Exception {

		log.info("{}", LocalDateTime.now());
		JobParameters params = new JobParametersBuilder().addLong("run.id", System.currentTimeMillis())
				.toJobParameters();

		jobLauncher.run(runJob, params);

		log.info("Batch job triggered by scheduler... Time : {}", LocalDateTime.now());
	}
}
