package security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
 
 
/**
 * Expose the Spring Security Configuration
 * 
 * @author malalanayake
 * 
 */
@Configuration
@ImportResource({ "classpath:webSecurityConfig.xml" })
@ComponentScan("security")
public class SpringSecurityConfig {
 
    public SpringSecurityConfig() {
        super();
    }
 
}