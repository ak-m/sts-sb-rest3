package com.sts.projs.rest3;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class StsSbRest3Application {

	public static void main(String[] args) {
		SpringApplication.run(StsSbRest3Application.class, args);
	}

}


//Rest controller
@RestController
@RequestMapping("/ping")
class PingTestController {
	public static final Logger log = LoggerFactory.getLogger(PingTestController.class);
	private AtomicInteger requestcount = new AtomicInteger(1);
	
	public PingTestController() {
		log.debug("{} is created by {}", PingTestController.class.getName(), Thread.currentThread().getName());
	}
	
	@Value("${spring.application.name}")
	private String appName;
	
	@GetMapping("/test")
	public String pingtest() {
		log.debug("{} request being processed", requestcount.getAndIncrement());
		String serverResponse = new StringBuilder().append("Application-Name ").append(appName)
		.append(" Responded@ ").append(LocalDateTime.now()).toString();
		return serverResponse;
	}
	
}