package by.frost.hibernate.starter.service;

import by.frost.hibernate.starter.dao.UserRepository;
import by.frost.hibernate.starter.dto.CreateUserDto;
import by.frost.hibernate.starter.dto.UserDto;
import by.frost.hibernate.starter.mapper.CreateUserMapper;
import by.frost.hibernate.starter.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

import javax.validation.*;
import java.util.Optional;

@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CreateUserMapper createUserMapper;

    public boolean delete (Integer id) {
        var maybeUser = userRepository.findById(id);
        maybeUser.ifPresent(user -> userRepository.delete(id));
        return maybeUser.isPresent();
    }

    public Optional<UserDto> findUserById(Integer id) {
        return userRepository.findById(id).map(userMapper::mapFrom);
    }

    public void create(CreateUserDto createUserDto) {
        var validatorFactory = Validation.buildDefaultValidatorFactory();
        var validator = validatorFactory.getValidator();
        var validatationResultSet = validator.validate(createUserDto);
        if(!validatationResultSet.isEmpty())
            throw new ConstraintViolationException(validatationResultSet);

        var user = createUserMapper.mapFrom(createUserDto);
        userRepository.save(user);
    }
}
