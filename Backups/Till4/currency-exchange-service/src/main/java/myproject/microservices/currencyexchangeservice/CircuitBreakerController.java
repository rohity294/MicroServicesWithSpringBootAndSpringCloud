package myproject.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CircuitBreakerController {
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/sample-api")
	//@Retry(name="default") //by default it retries thrice
    //@Retry(name="my-sample-api-retry-profile")// in application.properties, this profile is customized for 5 retries
    //@Retry(name="my-sample-api-retry-profile",fallbackMethod ="hardcodedResponse")
	//@CircuitBreaker(name="my-sample-api-retry-profile",fallbackMethod ="hardcodedResponse")
	@RateLimiter(name="default")
	public String sampleApi() {
		logger.info("My Sample API call received..");
//		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url",String.class);
//		return forEntity.getBody();
		return "sample-api";
	}
	
	public String hardcodedResponse(Exception exception) {
		return "customized-fallback-response";
	}
}
