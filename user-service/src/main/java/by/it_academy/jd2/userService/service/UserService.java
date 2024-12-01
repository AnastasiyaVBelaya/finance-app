package by.it_academy.jd2.userService.service;

import by.it_academy.jd2.dto.PageOf;
import by.it_academy.jd2.dto.PaginationDTO;
import by.it_academy.jd2.exception.EmailAlreadyExistsException;
import by.it_academy.jd2.exception.UserNotFoundException;
import by.it_academy.jd2.model.EssenceType;
import by.it_academy.jd2.userService.dto.*;
import by.it_academy.jd2.userService.model.UserRole;
import by.it_academy.jd2.userService.model.UserStatus;
import by.it_academy.jd2.userService.repository.api.IUserProjection;
import by.it_academy.jd2.userService.repository.api.IUserRepository;
import by.it_academy.jd2.userService.repository.entity.UserEntity;
/*import by.it_academy.jd2.userService.service.api.AuditFeignClient;*/
import by.it_academy.jd2.userService.service.api.IUserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
/*    private final AuditFeignClient auditFeignClient;*/

    public UserService(IUserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       ModelMapper modelMapper/*, AuditFeignClient auditFeignClient*/) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
/*        this.auditFeignClient = auditFeignClient;*/
    }

    @Override
    @Transactional
    public void save(UserCreateDTO userCreateDTO) {
        UserEntity userEntity = modelMapper.map(userCreateDTO, UserEntity.class);
        userEntity.setUuid(UUID.randomUUID());
        userEntity.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        userEntity.setStatus(userCreateDTO.getStatus());
        if (userRepository.existsByMail(userCreateDTO.getMail())) {
            throw new EmailAlreadyExistsException("Пользователь с таким email уже существует");
        }
        userRepository.saveAndFlush(userEntity);
        /*UserAuditDTO auditUser = new UserAuditDTO(
                userEntity.getUuid(),
                userEntity.getMail(),
                userEntity.getFio(),
                userEntity.getRole()
        );

        logUserAction(auditUser, "Пользователь создан", EssenceType.USER, userEntity.getUuid().toString());
*/
    }

    @Override
    public PageOf<UserDTO> findAll(PaginationDTO paginationDTO) {
        Pageable pageable = PageRequest.of(paginationDTO.getPage(), paginationDTO.getSize());

        Page<IUserProjection> userProjections = userRepository.findUserProjections(pageable);

        List<UserDTO> userDTOS = userProjections.stream()
                .map(this::convertProjectionToDTO)
                .collect(Collectors.toList());

        return buildPageOfResponse(userProjections, userDTOS);
    }

    private UserDTO convertProjectionToDTO(IUserProjection projection) {
        return new UserDTO(
                projection.getUuid(),
                projection.getDtCreate(),
                projection.getDtUpdate(),
                projection.getMail(),
                projection.getFio(),
                UserRole.valueOf(projection.getRole()),
                UserStatus.valueOf(projection.getStatus())
        );
    }

    private PageOf<UserDTO> buildPageOfResponse(Page<IUserProjection> pageResult, List<UserDTO> userDTOS) {
        return PageOf.<UserDTO>builder()
                .number(pageResult.getNumber())
                .size(pageResult.getSize())
                .totalElements(pageResult.getTotalElements())
                .totalPages(pageResult.getTotalPages())
                .first(pageResult.isFirst())
                .numberOfElements(pageResult.getNumberOfElements())
                .last(pageResult.isLast())
                .content(userDTOS)
                .build();
    }

    @Override
    public UserDTO findById(UUID uuid) {
        UserEntity userEntity = userRepository.findById(uuid).orElseThrow(() ->
                new UserNotFoundException("Пользователь с таким UUID не найден: " + uuid));
        return convertEntityToDTO(userEntity);
    }

    private UserDTO convertEntityToDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }

    @Override
    @Transactional
    public void update(UUID uuid, LocalDateTime dtUpdate, UserCreateDTO userCreateDTO) {

        UserEntity userEntity = userRepository.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с таким UUID не найден: " + uuid));

        if (!userEntity.getDtUpdate().isEqual(dtUpdate)) {
            throw new OptimisticLockingFailureException("Данные были изменены другим пользователем.");
        }

        modelMapper.map(userCreateDTO, userEntity);
        userEntity.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        userRepository.saveAndFlush(userEntity);

       /* UserAuditDTO auditUser = new UserAuditDTO(
                userEntity.getUuid(),
                userEntity.getMail(),
                userEntity.getFio(),
                userEntity.getRole()
        );
        logUserAction(auditUser, "Информация о пользователе обновлена", EssenceType.USER, uuid.toString());*/
    }

    @Override
    public void update(UserEntity userEntity) {
        userRepository.saveAndFlush(userEntity);
    }

    @Override
    public UserEntity findByMail(String mail) {
        return userRepository.findByMail(mail)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
    }

/*    public void logUserAction(UserAuditDTO user, String text, EssenceType type, String entityId) {
        AuditDTO audit = new AuditDTO();

        audit.setUuid(UUID.randomUUID());
        audit.setDtCreate(LocalDateTime.now());
        audit.setDtUpdate(LocalDateTime.now());
        audit.setUser(user);
        audit.setText(text);
        audit.setType(type);
        audit.setId(entityId);
        auditFeignClient.sendAudit(audit);
    }*/
}
