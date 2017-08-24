package gardncl.whobrokeitlast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
public class Config {

    @Autowired
    private static Environment env;

    static {
        System.out.println(env.getProperty("providers.github.clientId"));
    }

}
