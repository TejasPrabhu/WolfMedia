package com.tejasprabhu.wolfmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
public class WolfmediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WolfmediaApplication.class, args);
	}

}
