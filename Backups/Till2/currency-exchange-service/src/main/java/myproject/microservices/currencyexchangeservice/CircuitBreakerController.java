package myproject.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@RestController
public class CircuitBreakerController {
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/sample-api")
	//@Retry(name="default") //by default it retries thrice
    //@Retry(name="my-sample-api-retry-profile")// in application.properties, this profile is customized for 5 retries
    //@Retry(name="my-sample-api-retry-profile",fallbackMethod ="hardcodedResponse")
	//@CircuitBreaker(name="my-sample-api-retry-profile",fallbackMethod ="hardcodedResponse")
	//@RateLimiter(name="default")
	@Bulkhead(name="default")
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
