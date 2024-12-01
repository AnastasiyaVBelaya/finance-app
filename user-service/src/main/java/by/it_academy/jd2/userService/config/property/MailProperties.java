package by.it_academy.jd2.userService.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.mail")
@Getter
@Setter
public class MailProperties {

    private String host;
    private int port;
    private String username;
    private String password;
    private String from;
}
