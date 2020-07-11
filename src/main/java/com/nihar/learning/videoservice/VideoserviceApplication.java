package com.nihar.learning.videoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class} )
public class VideoserviceApplication {

	public static void main(String[] args) {
		System.out.println(VideoService.uniqueIdentificationNumber);
		SpringApplication.run(VideoserviceApplication.class, args);
	}

}
