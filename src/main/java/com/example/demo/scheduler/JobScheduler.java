package com.example.demo.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobScheduler {

	private final JobLauncher jobLauncher;
	private final Job job;

	public JobScheduler(JobLauncher jobLauncher, Job job) {
		this.jobLauncher = jobLauncher;
		this.job = job;
	}

	@Scheduled(cron = "0 */1 * * * ?")
	public void runJob() throws Exception {

		System.out.println("Triggering batch job...");

		JobParameters params = new JobParametersBuilder().addLong("timestamp", System.currentTimeMillis())
				.toJobParameters();

		JobExecution execution = jobLauncher.run(job, params);
		System.out.println("Job Status: " + execution.getStatus());
	}
}
