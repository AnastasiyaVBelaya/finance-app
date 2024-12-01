package by.it_academy.jd2.classifierService.config;

import by.it_academy.jd2.classifierService.DTO.CurrencyDTO;
import by.it_academy.jd2.classifierService.repository.entity.CurrencyEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        modelMapper.typeMap(CurrencyDTO.class, CurrencyEntity.class)
                .addMappings(mapper ->
                        mapper.skip(CurrencyEntity::setUuid));
        return modelMapper;
    }
}
