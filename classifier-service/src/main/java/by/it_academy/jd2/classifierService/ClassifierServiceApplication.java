package by.it_academy.jd2.classifierService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "by.it_academy.jd2.classifierService.repository")
@EnableTransactionManagement
@ConfigurationPropertiesScan("by.it_academy.jd2.classifierService.config")
public class ClassifierServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassifierServiceApplication.class, args);
	}
}
