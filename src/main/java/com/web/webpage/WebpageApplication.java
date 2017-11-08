package com.web.webpage;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@Configurable
public class WebpageApplication  {

	public static void main(String[] args) {
		SpringApplication.run(WebpageApplication.class, args);
	}
}
