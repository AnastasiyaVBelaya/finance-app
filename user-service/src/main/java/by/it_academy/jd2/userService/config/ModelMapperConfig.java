package by.it_academy.jd2.userService.config;

import by.it_academy.jd2.userService.dto.UserCreateDTO;
import by.it_academy.jd2.userService.repository.entity.UserEntity;
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

        modelMapper.typeMap(UserCreateDTO.class, UserEntity.class).
                addMappings(mapper -> {
                    mapper.skip(UserEntity::setUuid);
                    mapper.skip(UserEntity::setDtCreate);
                });
        return modelMapper;
    }
}
