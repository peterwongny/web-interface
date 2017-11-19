package com.cs7s.webpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main webpage application class.
 */
@EnableAutoConfiguration
@SpringBootApplication
public class WebpageApplication  {

	/**
	 * The main function.
	 * @param args the arguments for the main function.
	 */
	public static void main(String[] args) {
		SpringApplication.run(WebpageApplication.class, args);
	}
}