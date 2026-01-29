package com.gms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrievanceManagementSystemApplication {

	public static void main(String[] args) {
		// Catch all uncaught exceptions and errors (including StackOverflowError)
		Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
			System.err.println("Uncaught exception in thread " + thread.getName());
			throwable.printStackTrace(); // prints full stack trace to terminal
		});

		SpringApplication.run(GrievanceManagementSystemApplication.class, args);
	}
}
