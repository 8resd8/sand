package com.ssafy.sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import com.ssafy.sandbox.config.JpaConfig;

@SpringBootApplication
@EnableAspectJAutoProxy
@Import(JpaConfig.class)
public class SandboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SandboxApplication.class, args);
	}

}
