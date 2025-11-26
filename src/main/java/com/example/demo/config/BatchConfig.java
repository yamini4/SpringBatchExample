package com.example.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.demo.service.JarExecutionTasklet;

@Configuration
public class BatchConfig {

	@Bean
	public Step jarStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
			JarExecutionTasklet tasklet) {

		return new StepBuilder("jarStep", jobRepository).tasklet(tasklet, transactionManager).build();
	}

	@Bean
	public Job jarExecutionJob(JobRepository jobRepository, Step jarStep) {
		return new JobBuilder("jarExecutionJob", jobRepository).start(jarStep).build();
	}
}
