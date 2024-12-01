package by.it_academy.jd2.classifierService.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        javaTimeModule.addSerializer(LocalDateTime.class, new JsonSerializer<>() {
            @Override
            public void serialize(LocalDateTime value,
                                  JsonGenerator gen,
                                  SerializerProvider serializers)
                    throws IOException {
                long timestamp = value.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli();
                gen.writeNumber(timestamp);
            }
        });

        javaTimeModule.addDeserializer(LocalDateTime.class, new JsonDeserializer<>() {

            @Override
            public LocalDateTime deserialize(JsonParser parser,
                                             DeserializationContext context)
                    throws IOException {
                long timestamp = parser.getLongValue();
                return Instant.ofEpochMilli(timestamp).atOffset(ZoneOffset.UTC).toLocalDateTime();
            }
        });

        objectMapper.registerModule(javaTimeModule);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        return objectMapper;
    }
}
