package by.frost.hibernate.starter.mapper;

import by.frost.hibernate.starter.dto.UserDto;
import by.frost.hibernate.starter.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserMapper implements Mapper<User, UserDto>{

    private final CompanyMapper companyMapper;

    @Override
    public UserDto mapFrom(User object) {
        return new UserDto(object.getId(),
                object.getPersonalInfo(),
                object.getUsername(),
                object.getRole(),
                companyMapper.mapFrom(object.getCompany()));
    }
}
