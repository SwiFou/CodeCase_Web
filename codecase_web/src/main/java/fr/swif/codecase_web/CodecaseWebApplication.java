package fr.swif.codecase_web;

import fr.swif.codecase_web.config.CustomProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@RequiredArgsConstructor
@EnableConfigurationProperties(CustomProperties.class)
@SpringBootApplication
public class CodecaseWebApplication implements CommandLineRunner {

	private final CustomProperties props;

	public static void main(String[] args) {
		SpringApplication.run(CodecaseWebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(props.getApiUrl());
	}
}
