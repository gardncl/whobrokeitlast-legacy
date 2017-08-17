package gardncl.whobrokeitlast;

import isaacearl.config.JSocialiteProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableConfigurationProperties({JSocialiteProperties.class})
public class WhoBrokeItLastApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhoBrokeItLastApplication.class, args);
	}
}
