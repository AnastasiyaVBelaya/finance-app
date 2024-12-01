package by.itacademy.audit.config;

import by.itacademy.audit.dto.AuditDTO;
import by.itacademy.audit.repository.AuditEntity;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
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

        modelMapper.typeMap(AuditDTO.class, AuditEntity.class)
                .addMappings(mapper -> {
                    mapper.skip(AuditEntity::setUuid);
                    mapper.skip(AuditEntity::setDtCreate);
                    mapper.skip(AuditEntity::setDtUpdate);
                });

        modelMapper.typeMap(AuditDTO.class, AuditEntity.class)
                .addMappings(mapper -> {
                    mapper.skip(AuditEntity::setUuid);
                    mapper.skip(AuditEntity::setDtCreate);
                    mapper.skip(AuditEntity::setDtUpdate);
                });

        modelMapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull());
        return modelMapper;
    }
}