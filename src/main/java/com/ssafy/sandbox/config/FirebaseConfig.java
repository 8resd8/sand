package com.ssafy.sandbox.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class FirebaseConfig {

	@Value("${firebase.service.account.path}")
	private Resource serviceAccount;

	@Value("${firebase.project.id}")
	private String projectId;

	@PostConstruct
	public void initialize() throws IOException {
		try (InputStream serviceAccountStream = serviceAccount.getInputStream()) {
			GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccountStream)
				.createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

			FirebaseOptions options = FirebaseOptions.builder()
				.setCredentials(credentials)
				.setProjectId(projectId)
				.build();

			FirebaseApp.initializeApp(options);
			log.info("Fcm Setting Completed");
		}
	}
}