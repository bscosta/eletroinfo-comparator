package com.eletroinfo.eletroinfo.comparator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class EletroinfoComparatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EletroinfoComparatorApplication.class, args);
	}
}
