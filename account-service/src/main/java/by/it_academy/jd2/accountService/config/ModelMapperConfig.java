package by.it_academy.jd2.accountService.config;

import by.it_academy.jd2.accountService.dto.AccountDTO;
import by.it_academy.jd2.accountService.dto.OperationDTO;
import by.it_academy.jd2.accountService.repository.entity.AccountEntity;
import by.it_academy.jd2.accountService.repository.entity.OperationEntity;
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

        modelMapper.typeMap(OperationDTO.class, OperationEntity.class)
                .addMappings(mapper -> {
                    mapper.skip(OperationEntity::setUuid);
                    mapper.skip(OperationEntity::setDtCreate);
                    mapper.skip(OperationEntity::setDtUpdate);
                });

        modelMapper.typeMap(AccountDTO.class, AccountEntity.class)
                .addMappings(mapper -> {
                    mapper.skip(AccountEntity::setUuid);
                    mapper.skip(AccountEntity::setDtCreate);
                    mapper.skip(AccountEntity::setDtUpdate);
                });

        modelMapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull());
        return modelMapper;
    }
}