package myproject.microservices.apigateway;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/get")
				.filters(f->f
						.addRequestHeader("myRequestAuthenticationHeaderKey","myRequestAuthenticationHeaderValue")
						.addRequestParameter("myRequestParameterKey","myRequestParameterValue")
						)
				.uri("http://httpbin.org:80"))
				
				.route(p->p.path("/currency-exchange/**")
				.uri("lb://mycurrency-exchange"))
				
				.route(p->p.path("/currency-conversion-feign/**")
						.uri("lb://mycurrency-conversion"))
				
				.route(p->p.path("/currency-conversion-feign-new/**")
						.filters(f->
							f.rewritePath(
									"currency-conversion-feign-new/(?<segment>.*)",
									"currency-conversion-feign/${segment}")
							)
						.uri("lb://mycurrency-conversion"))
				
				.build();
	}
}
