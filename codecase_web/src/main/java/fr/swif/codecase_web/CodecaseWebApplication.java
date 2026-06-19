package fr.swif.codecase_web;

import fr.swif.codecase_web.config.CustomProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


// @RequiredArgsConstructor génère automatiquement un constructeur prenant en
// paramètre tous les champs final et @NotNull de la classe
@RequiredArgsConstructor
// @EnableConfigurationProperties sert à activer le binding de classes annotées
// avec @ConfigurationProperties. Ici la classe CustomProperties
@EnableConfigurationProperties(CustomProperties.class)
// 
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
