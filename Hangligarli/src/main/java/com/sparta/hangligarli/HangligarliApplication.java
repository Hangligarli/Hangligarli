package com.sparta.hangligarli;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class HangligarliApplication {

	public static void main(String[] args) {
		SpringApplication.run(HangligarliApplication.class, args);
	}

}
