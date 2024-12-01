package by.it_academy.jd2.classifierService.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.jwt")
public class JWTProperty {
    private String secret;
    private String issuer;
}
