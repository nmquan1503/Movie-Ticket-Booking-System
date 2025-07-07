package com.nmquan1503.backend_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BackendSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendSpringbootApplication.class, args);
//		Logger logger = LoggerFactory.getLogger(BackendSpringbootApplication.class);
//		logger.debug("log Debug");
//		logger.info("log Info");
//		logger.warn("log Warn");
//		logger.error("log Error");
	}

}
